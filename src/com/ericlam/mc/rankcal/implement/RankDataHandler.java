package com.ericlam.mc.rankcal.implement;

import com.ericlam.mc.rankcal.PlayerData;
import com.ericlam.mc.rankcal.RankData;
import com.ericlam.mc.rankcal.RankDataCalculator;
import com.ericlam.mc.rankcal.RankDataManager;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang.Validate;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public final class RankDataHandler implements RankDataManager {

    private TreeSet<RankData> rankData;
    private List<PlayerData> playerData;
    private Map<String, RankDataCalculator> calculatorMap;
    private ArrayCalculation arrayCalculation;
    private Map<UUID, RankData> rankDataMap = new ConcurrentHashMap<>();
    private Function<List<PlayerData>, CompletableFuture<Void>> saver;
    private String currentMethod = "z-score"; // Default Calculator

    RankDataHandler(TreeSet<RankData> rankData, List<PlayerData> playerData, Map<String, RankDataCalculator> calculatorMap, Function<List<PlayerData>, CompletableFuture<Void>> saver) {
        this.rankData = rankData;
        this.playerData = playerData;
        this.calculatorMap = calculatorMap;
        this.saver = saver;
        this.arrayCalculation = new ArrayCalculation(playerData.stream().mapToDouble(PlayerData::getScore).toArray());
    }

    @Override
    public CompletableFuture<ImmutableMap<UUID, RankData>> doCalculate(String method) {
        return CompletableFuture.supplyAsync(() -> {
            if (!calculatorMap.containsKey(method)) {
                throw new IllegalStateException("Unknown Calculation Method: " + method);
            }
            this.currentMethod = method;
            RankDataCalculator calculator = calculatorMap.get(method);
            playerData.forEach(data -> {
                UUID player = data.getPlayerUniqueId();
                RankData rankData = calculator.doCalculate(data, ImmutableList.copyOf(this.rankData), arrayCalculation);
                this.rankDataMap.put(player, rankData);
            });
            return ImmutableMap.copyOf(this.rankDataMap);
        });
    }

    @Override
    public void join(PlayerData data) {
        playerData.add(data);
    }

    @Nullable
    @Override
    public PlayerData getPlayerData(UUID uuid) {
        return playerData.stream().filter(data -> data.getPlayerUniqueId().equals(uuid)).findAny().orElse(null);
    }

    @Override
    public void update(UUID uuid) {
        PlayerData data = this.getPlayerData(uuid);
        if (data == null) {
            throw new IllegalStateException("Cannot get the player data of uuid: " + uuid.toString());
        }
        if (!calculatorMap.containsKey(currentMethod)) {
            throw new IllegalStateException("Unknown Calculation Method: " + currentMethod);
        }
        RankDataCalculator calculator = calculatorMap.get(currentMethod);
        RankData rankData = calculator.doCalculate(data, ImmutableList.copyOf(this.rankData), arrayCalculation);
        this.rankDataMap.put(uuid, rankData);
    }

    @Nullable
    @Override
    public RankData getRankData(UUID uuid) {
        return this.rankDataMap.get(uuid);
    }

    public ImmutableMap<UUID, RankData> getRankDataMap() {
        return ImmutableMap.copyOf(rankDataMap);
    }

    @Override
    public CompletableFuture<Void> savePlayerData() {
        Validate.notNull(saver, "Saving Mechanic is null");
        return saver.apply(playerData);
    }
}

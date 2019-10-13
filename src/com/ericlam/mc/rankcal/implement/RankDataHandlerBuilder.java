package com.ericlam.mc.rankcal.implement;

import com.ericlam.mc.rankcal.*;

import java.util.*;

public final class RankDataHandlerBuilder implements RankDataFactory {

    private TreeSet<RankData> rankData = new TreeSet<>();
    private List<PlayerData> playerData = new LinkedList<>();
    private Map<String, RankDataCalculator> calculatorMap = new HashMap<>();

    RankDataHandlerBuilder(Map<String, RankDataCalculator> calculatorMap) {
        this.calculatorMap = calculatorMap;
    }

    @Override
    public RankDataFactory registerRanks(TreeSet<RankData> rankData) {
        this.rankData.addAll(rankData);
        return this;
    }

    @Override
    public RankDataFactory addPlayers(List<PlayerData> data) {
        this.playerData.addAll(data);
        return this;
    }

    @Override
    public RankDataManager build() {
        return new RankDataHandler(rankData, playerData, calculatorMap);
    }
}

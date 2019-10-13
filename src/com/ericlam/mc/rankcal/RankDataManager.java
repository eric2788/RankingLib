package com.ericlam.mc.rankcal;

import com.google.common.collect.ImmutableMap;

import javax.annotation.Nullable;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * The class of managing the rank data of your plugin
 */
public interface RankDataManager {

    /**
     * Do the calculation
     *
     * @param method any method that registered
     * @return a Map of uuid : rank data
     */
    CompletableFuture<ImmutableMap<UUID, RankData>> doCalculate(String method);

    /**
     * add the player into the list
     * @param data player data
     */
    void join(PlayerData data);

    /**
     *
     * @param uuid player uuid
     * @return player data
     */
    @Nullable
    PlayerData getPlayerData(UUID uuid);

    /**
     * update the rank data of the player
     * @param uuid player uuid
     */
    void update(UUID uuid);

    /**
     * get the rank data of the player
     * @param uuid player uuid
     * @return rank data
     */
    @Nullable
    RankData getRankData(UUID uuid);

    /**
     * get the rank data map
     * @return rank data map with uneditable
     */
    ImmutableMap<UUID, RankData> getRankDataMap();

    /**
     * save the player data
     *
     * @return after saving
     */
    CompletableFuture<Void> savePlayerData();

}

package com.ericlam.mc.rankcal;

import com.ericlam.mc.rankcal.implement.RankDataHandler;

import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * The Factory which to build a RankDataManager
 */
public interface RankDataFactory {

    /**
     * @param rankData A Set of RankData which create by developer
     * @return this
     */
    RankDataFactory registerRanks(TreeSet<RankData> rankData);

    /**
     *
     * @param data A List of player data which created by developer
     * @return this
     */
    RankDataFactory addPlayers(List<PlayerData> data);

    /**
     * This is optional to register but if you try to use {@link RankDataHandler#savePlayerData()} without registered it may get an error.
     * @param saver a function that define how to save the player data
     * @return this
     */
    RankDataFactory registerSaveMechanic(Function<List<PlayerData>, CompletableFuture<Void>> saver);

    /**
     * @return RankDataManager instance
     */
    RankDataManager build();

}

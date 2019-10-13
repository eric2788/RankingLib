package com.ericlam.mc.rankcal;

import com.google.common.collect.ImmutableMap;

import javax.annotation.Nullable;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface RankDataManager {

    CompletableFuture<ImmutableMap<UUID, RankData>> doCalculate(String method);

    void join(PlayerData data);

    @Nullable
    PlayerData getPlayerData(UUID uuid);

    void update(UUID uuid);

    @Nullable
    RankData getRankData(UUID uuid);

    ImmutableMap<UUID, RankData> getRankDataMap();

}

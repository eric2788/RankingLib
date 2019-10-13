package com.ericlam.mc.rankcal;

import java.util.List;
import java.util.TreeSet;

public interface RankDataFactory {

    RankDataFactory registerRanks(TreeSet<RankData> rankData);

    RankDataFactory addPlayers(List<PlayerData> data);

    RankDataManager build();

}

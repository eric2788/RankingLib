package com.ericlam.mc.rankcal;

import java.util.List;

@FunctionalInterface
public interface RankDataCalculator {
    RankData doCalculate(final PlayerData playerData, List<RankData> ladderRanks, ArrayData arrayData);
}

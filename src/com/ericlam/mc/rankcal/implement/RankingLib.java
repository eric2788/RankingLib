package com.ericlam.mc.rankcal.implement;

import com.ericlam.mc.rankcal.RankDataCalculator;
import com.ericlam.mc.rankcal.RankDataFactory;
import com.ericlam.mc.rankcal.RankLibAPI;
import com.ericlam.mc.rankcal.utils.AdvMath;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class RankingLib extends JavaPlugin implements RankLibAPI {

    private static RankLibAPI api;
    private Map<String, RankDataCalculator> calculatorMap = new HashMap<>();

    public static RankLibAPI getRankAPI() {
        return api;
    }


    @Override
    public void onLoad() {
        api = this;

        //Default calculator
        api.registerCalculator("z-score", (playerData, ladderRanks, arrayData) -> {
            double v = playerData.getScore();
            double result = AdvMath.round(2, (v - arrayData.getMean()) / arrayData.getSd());
            int minScore = (int) -Math.floor((int) (ladderRanks.size() / 2));
            int scoreIndex = ((int) result) - minScore;
            if (scoreIndex < 0) {
                return ladderRanks.get(0);
            } else if (scoreIndex > ladderRanks.size()) {
                return ladderRanks.get(ladderRanks.size() - 1);
            }
            return ladderRanks.get(scoreIndex);
        });

        //Default calculator 2
        api.registerCalculator("min-max", (playerData, ladderRanks, arrayData) -> {
            double min = Arrays.stream(arrayData.getScores()).reduce(Math::min).orElseThrow(() -> new IllegalStateException("cannot find min score"));
            double max = Arrays.stream(arrayData.getScores()).reduce(Math::max).orElseThrow(() -> new IllegalStateException("cannot find max score"));
            double v = playerData.getScore();
            double result = (v - min) / (max - min) * (ladderRanks.size() - 1) + 0;
            return ladderRanks.get((int) Math.round(result));
        });
    }

    @Override
    public RankDataFactory getFactory() {
        return new RankDataHandlerBuilder(calculatorMap);
    }

    @Override
    public void registerCalculator(String method, RankDataCalculator calculator) {
        this.calculatorMap.put(method, calculator);
    }
}

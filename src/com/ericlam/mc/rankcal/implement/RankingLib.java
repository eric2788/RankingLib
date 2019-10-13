package com.ericlam.mc.rankcal.implement;

import com.ericlam.mc.rankcal.RankDataCalculator;
import com.ericlam.mc.rankcal.RankDataFactory;
import com.ericlam.mc.rankcal.RankLibAPI;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class RankingLib extends JavaPlugin implements RankLibAPI {

    private Map<String, RankDataCalculator> calculatorMap = new HashMap<>();
    private static RankLibAPI api;

    public static RankLibAPI getRankAPI(){
        return api;
    }


    @Override
    public void onLoad() {
        api = this;
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

package com.ericlam.mc.rankcal;

public interface RankLibAPI {

    RankDataFactory getFactory();

    void registerCalculator(String method, RankDataCalculator calculator);
}

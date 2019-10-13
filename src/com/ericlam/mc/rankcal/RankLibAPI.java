package com.ericlam.mc.rankcal;

/**
 * The Main API of RankingLib, you should call by RankingLib.getRankAPI()
 */
public interface RankLibAPI {

    /**
     * get the RankDataManager factory
     *
     * @return factory of building RankDataManager
     */
    RankDataFactory getFactory();

    /**
     *
     * @param method the method name
     * @param calculator the calculation handle
     */
    void registerCalculator(String method, RankDataCalculator calculator);
}

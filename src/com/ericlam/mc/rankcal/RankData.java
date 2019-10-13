package com.ericlam.mc.rankcal;

/**
 * The RankData which developer need to implement
 */
public interface RankData extends Comparable<RankData> {

    /**
     * @return the identifier of the rank
     */
    String getId();

    /**
     *
     * @return the display of the rank which shows to player
     */
    String getRankDisplay();

}

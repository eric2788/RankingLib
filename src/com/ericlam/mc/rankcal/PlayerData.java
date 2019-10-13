package com.ericlam.mc.rankcal;

import java.util.UUID;

/**
 * The Player Data which developer need to implement
 */
public interface PlayerData extends Comparable<PlayerData> {

    /**
     * @return the uuid of the player
     */
    UUID getPlayerUniqueId();

    /**
     *
     * @return the score of the player
     */
    double getScore();

}

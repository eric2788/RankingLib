package com.ericlam.mc.rankcal;

import java.util.UUID;

public interface PlayerData extends Comparable<PlayerData> {

    UUID getPlayerUniqueId();

    double getScore();

}

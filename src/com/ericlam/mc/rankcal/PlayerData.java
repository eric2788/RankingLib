package com.ericlam.mc.rankcal;

import org.bukkit.OfflinePlayer;

import java.util.UUID;

public interface PlayerData extends Comparable<PlayerData>{

    UUID getPlayerUniqueId();

    double getScore();

}

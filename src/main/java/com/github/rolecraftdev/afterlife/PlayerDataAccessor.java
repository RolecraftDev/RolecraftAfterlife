package com.github.rolecraftdev.afterlife;

import org.bukkit.entity.Player;

public class PlayerDataAccessor {

    private RolecraftAfterlife plugin;

    public PlayerDataAccessor(RolecraftAfterlife plugin) {
        this.plugin = plugin;
    }
    
    public void addKarma (final Player ply, final float amount)  {
        plugin.getCore().getDataManager().getPlayerData(ply.getUniqueId()).addKarma(amount);
    }
    
    public void deductKarma (final Player ply, final float amount) {
        plugin.getCore().getDataManager().getPlayerData(ply.getUniqueId()).subtractKarma(amount);
    }

}

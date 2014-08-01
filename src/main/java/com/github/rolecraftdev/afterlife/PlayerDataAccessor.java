/*
 * This file is part of RolecraftAfterlife.
 *
 * Copyright (c) ${inceptionYear} RolecraftDev <http://rolecraftdev.github.com>
 * RolecraftCore is licensed under the Creative Commons
 * Attribution-NonCommercial-NoDerivs 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-nc-nd/3.0
 *
 * As long as you follow the following terms, you are free to copy and redistribute
 * the material in any medium or format.
 *
 * You must give appropriate credit, provide a link to the license, and indicate
 * whether any changes were made to the material. You may do so in any reasonable
 * manner, but not in any way which suggests the licensor endorses you or your use.
 *
 * You may not use the material for commercial purposes.
 *
 * If you remix, transform, or build upon the material, you may not distribute the
 * modified material.
 *
 * You may not apply legal terms or technological measures that legally restrict
 * others from doing anything the license permits.
 *
 * DISCLAIMER: This is a human-readable summary of (and not a substitute for) the
 * license.
 */
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
        plugin.getCore().getDataManager().getPlayerData(ply.getUniqueId()).deductKarma(amount);
    }

}

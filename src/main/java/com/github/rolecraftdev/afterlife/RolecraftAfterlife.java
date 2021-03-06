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

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.rolecraftdev.RolecraftCore;
import com.github.rolecraftdev.afterlife.heaven.HeavenChunkGenerator;
import com.github.rolecraftdev.afterlife.hell.HellChunkGenerator;

public class RolecraftAfterlife extends JavaPlugin {
    
    private RolecraftCore corePlugin;
    
    private PlayerDataAccessor accessor;
    
    private World heaven;
    
    private World hell;
    
    @Override
    public void onLoad() {
        
    }
    
    @Override
    public void onEnable () {
        accessor = new PlayerDataAccessor(this);
        
        if(getServer().getWorld("Heaven") == null) {
            getServer().createWorld(new WorldCreator("Heaven")
                    .generateStructures(true)
                    .generator(new HeavenChunkGenerator()));
        }
        if(getServer().getWorld("Hell") == null) {
            getServer().createWorld(new WorldCreator("Hell")
            .generateStructures(true)
            .generator(new HellChunkGenerator()));
        }
    }
    
    @Override
    public void onDisable () {
        
    }
    
    public PlayerDataAccessor getAccessor() {
        return accessor;
    }
    
    public RolecraftCore getCore () {
        return corePlugin;
    }
}

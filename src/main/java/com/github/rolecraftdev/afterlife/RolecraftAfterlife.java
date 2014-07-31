package com.github.rolecraftdev.afterlife;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.rolecraftdev.RolecraftCore;

public class RolecraftAfterlife extends JavaPlugin {
    
    private RolecraftCore corePlugin;
    
    private PlayerDataAccessor accessor;
    
    @Override
    public void onLoad() {
        
    }
    
    @Override
    public void onEnable () {
        accessor = new PlayerDataAccessor(this);
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

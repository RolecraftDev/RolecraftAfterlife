package com.github.rolecraftdev.afterlife.listener;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import com.github.rolecraftdev.afterlife.RolecraftAfterlife;

public class PickupListener implements Listener {
    
    private RolecraftAfterlife plugin;
    
    private HashMap<UUID,Long> lastGift;
    
    public PickupListener (RolecraftAfterlife plugin ) {
        this.plugin = plugin;
        lastGift = new HashMap<UUID,Long>();
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPickup(PlayerPickupItemEvent event) {
        
        if(event.getItem().hasMetadata("dropper")) {
            Player player = Bukkit.getServer().getPlayer(
                    UUID.fromString(event.getItem().getMetadata("dropper")
                            .get(0).asString()));
            if(player != null) {
                if(lastGift.get(UUID.fromString(event.getItem().getMetadata("dropper") 
                            .get(0).asString())) + plugin.getConfig().getInt("giftcooldown")
                            * 1000 < System.currentTimeMillis()) {
                    plugin.getAccessor().addKarma(player,
                            (float) plugin.getConfig().getDouble("value.gift"));
                    player.sendMessage("You have given a gift to : " 
                            + event.getPlayer().getName() + " and recivied some bonus Karma!");
                    event.getPlayer().sendMessage(player.getName()+" has given you a gift!");
                    lastGift.put(UUID.fromString(event.getItem().getMetadata("dropper") 
                            .get(0).asString()), System.currentTimeMillis());
                }
            }
        }
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onDrop(PlayerDropItemEvent event) {
        event.getItemDrop().setMetadata("dropper", 
                new FixedMetadataValue(plugin, event.getPlayer().getUniqueId().toString()));
    }
}

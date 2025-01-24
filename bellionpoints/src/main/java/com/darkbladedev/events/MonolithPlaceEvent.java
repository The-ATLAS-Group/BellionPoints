package com.darkbladedev.events;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.Listener;

import com.ssomar.executableblocks.executableblocks.ExecutableBlock;

public class MonolithPlaceEvent implements Listener {

    public static Location monolithLocation;

    public static ExecutableBlock monolithBlock;

    // Gets the block type and location.
    @SuppressWarnings("unlikely-arg-type")
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getBlock().getType().toString().equals(monolithBlock.getItemName().toString().equalsIgnoreCase("&5&lMonolito"))) {
            // Handle the monolith placement event here
            monolithLocation = event.getBlock().getLocation();
        }
    }
    
}

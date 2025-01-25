package com.darkbladedev.events;

import java.util.Optional;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.Listener;

import com.darkbladedev.storage.StorageManager;
import com.ssomar.executableblocks.executableblocks.ExecutableBlock;
import com.ssomar.executableblocks.executableblocks.ExecutableBlocksManager;

public class MonolithPlaceEvent implements Listener {

    public static Location monolithLocation;

    public static ExecutableBlock monolithBlock;

    private final StorageManager storageManager;

    public MonolithPlaceEvent(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

    // Gets the block type and location.
    @SuppressWarnings("unlikely-arg-type")
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {

        Optional<ExecutableBlock> executableBlock = ExecutableBlocksManager.getInstance().getExecutableBlock((ItemStack) event.getBlock());

        if (event.getBlock().getType().toString().equals(monolithBlock.getItemName().toString().equalsIgnoreCase("&5&lMonolito"))) {
            
            // Handle the monolith placement event here
            monolithLocation = event.getBlock().getLocation();
            String monolithName = executableBlock.get().getName();
            String monolithID = UUID.randomUUID().toString();

            // Save the monolith data
            storageManager.saveMonolithData(event.getPlayer(), monolithID, monolithLocation, monolithName);
        }
    }
    
}

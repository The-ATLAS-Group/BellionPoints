package com.darkbladedev.events;

import org.bukkit.Location;
import org.bukkit.event.Listener;

import com.darkbladedev.storage.StorageManager;
import com.darkbladedev.utils.MessageUtils;
import com.ssomar.executableblocks.executableblocks.ExecutableBlock;
import com.ssomar.score.api.executableblocks.events.ExecutableBlockPlaceEvent;
import com.ssomar.score.api.executableblocks.config.placed.ExecutableBlockPlacedInterface;

public class MonolithPlaceEvent implements Listener {

    public static Location monolithLocation;

    private final StorageManager storageManager;

    public MonolithPlaceEvent(StorageManager storageManager) {
        this.storageManager = storageManager;
    }


    public void onMonolithPlace(ExecutableBlockPlaceEvent event) {
        ExecutableBlock monolithBlock = (ExecutableBlock) event.getExecutableBlockPlaced();
        ExecutableBlockPlacedInterface monolithBlockPlaced = event.getExecutableBlockPlaced();

        if (event.getExecutableBlockPlaced().getEB_ID().equals("monolith")) {
            String monolithID = monolithBlock.getId();
            String monolithName = monolithBlock.getName();
            monolithLocation = monolithBlockPlaced.getLocation();

            // Message the player
            event.getPlacer().sendMessage(MessageUtils.getColoredMessage("&aMonolito colocado con el ID: &6" + monolithID + " &anombrado como: &6" + monolithName));
            
            // Save the monolith data
            storageManager.saveMonolithData(event.getPlacer(), monolithID, monolithLocation, monolithName);
        }
    }
}

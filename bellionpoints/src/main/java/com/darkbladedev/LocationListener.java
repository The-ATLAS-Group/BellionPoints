package com.darkbladedev;

import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.darkbladedev.events.MonolithPlaceEvent;

public class LocationListener implements Listener {
    // Implement location event handling methods here
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location monolithLocation = MonolithPlaceEvent.monolithLocation;

        if (monolithLocation != null) {
            // Do something with the monolith location
            // For example, check if the player is near the monolith
            player.sendMessage("Estás a ", + player.getLocation().distance(monolithLocation) + " bloques del monolito.");
        }
    }
}
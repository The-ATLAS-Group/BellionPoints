package com.darkbladedev.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import com.darkbladedev.storage.StorageManager;
import com.darkbladedev.utils.MessageUtils;

public class TeleportCommand implements CommandExecutor {

    private final StorageManager storageManager;

    public TeleportCommand(StorageManager storageManager) {
        this.storageManager = storageManager;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 2) {
            sender.sendMessage("Uso: /bellion teleport [player] <ID>");
            return false;
        }

        String playerName = args[1];
        String id = args[2];
        Player player = sender.getServer().getPlayer(playerName);

        String locX = storageManager.getMonolithData(player.getName(), "location.x");
        String locY = storageManager.getMonolithData(player.getName(), "location.y");
        String locZ = storageManager.getMonolithData(player.getName(), "location.z");

        Location loc = new Location(player.getWorld(), Double.parseDouble(locX), Double.parseDouble(locY), Double.parseDouble(locZ));

        if (command.getName().equalsIgnoreCase("teleport")){
            if (playerName != null && locX != null && locY != null && locZ != null) {
                player.teleport(loc);
                sender.sendMessage("Jugador '" + playerName + "' teletransportado al punto de interés '" + id + "'.");
            } else if (player.getName() == null) {
                sender.sendMessage(MessageUtils.getColoredMessage("No se encontró ningún jugador con el nombre '" + playerName + "'."));
            } else {
                sender.sendMessage(MessageUtils.getColoredMessage("No se encontró ningún punto de interés con el ID '" + id + "'."));
            }
        }
        return true;
    }
}
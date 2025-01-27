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
        if (args.length < 3) {
            sender.sendMessage(MessageUtils.getColoredMessage("&cUso: /bellion teleport [player] <ID>"));
            return false;
        }

        String playerName = args[1];
        String id = args[2];
        Player player = sender.getServer().getPlayer(playerName);

        if (player == null) {
            sender.sendMessage(MessageUtils.getColoredMessage("&cNo se encontró ningún jugador con el nombre '&6" + playerName + "&c'."));
            return false;
        }

        String locX = storageManager.getMonolithData(player.getName(), "location.x");
        String locY = storageManager.getMonolithData(player.getName(), "location.y");
        String locZ = storageManager.getMonolithData(player.getName(), "location.z");

        if (locX == null || locY == null || locZ == null) {
            sender.sendMessage(MessageUtils.getColoredMessage("&cNo se encontró ningún punto de interés con las posiciones especificadas."));
            return false;
        }

        Location loc = new Location(player.getWorld(), Double.parseDouble(locX), Double.parseDouble(locY), Double.parseDouble(locZ));

        if (args[0].equalsIgnoreCase("teleport")){
            if (playerName != null && locX != null && locY != null && locZ != null) {
                player.teleport(loc);
                sender.sendMessage("Jugador '" + playerName + "' teletransportado al punto de interés '" + id + "'.");
                return true;
            } else if (player.getName() == null) {
                sender.sendMessage(MessageUtils.getColoredMessage("No se encontró ningún jugador con el nombre '" + playerName + "'."));
                return false;
            } else if (id == null) {
                sender.sendMessage(MessageUtils.getColoredMessage("No se encontró ningún punto de interés con el ID '" + id + "'."));
                return false;
            }
        }
        return true;
    }
}
package com.darkbladedev.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import com.darkbladedev.storage.StorageManager;
import com.darkbladedev.utils.MessageUtils;

public class LocateCommand implements CommandExecutor {

    private final StorageManager storageManager;

    public LocateCommand(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 2) {
            sender.sendMessage(MessageUtils.getColoredMessage("&cUso: /bellion locate <ID>"));
            return false;
        }

        String id = args[1];
        Player player = (Player) sender;

        String worldName = (String) storageManager.getMonolithData(player.getName(), "location.world", id);
        String locX = storageManager.getMonolithData(player.getName(), "location.x");
        String locY = storageManager.getMonolithData(player.getName(), "location.y");
        String locZ = storageManager.getMonolithData(player.getName(), "location.z");

        if (worldName == null || locX == null || locY == null || locZ == null) {
            sender.sendMessage(MessageUtils.getColoredMessage("&cNo se encontró ningún punto de interés con el ID '&6" + id + "'&c."));
            return false;
        }

        Location loc = new Location(Bukkit.getWorld(worldName), Double.parseDouble(locX), Double.parseDouble(locY), Double.parseDouble(locZ));

        if (args[0].equalsIgnoreCase("locate")) {

            if (loc != null || loc.toString().isEmpty() == false) {
                player.sendMessage(MessageUtils.getColoredMessage("&aEl punto de interés '&6" + id + "' &aestá en la posición &6" + locX + " " + locY + " " + locZ + " " + "."));
            }
        } else {
            player.sendMessage(MessageUtils.getColoredMessage("&cNo se encontró ningún punto de interés con el ID '&6" + id + "'&c."));
        }
        return true;
    }
}
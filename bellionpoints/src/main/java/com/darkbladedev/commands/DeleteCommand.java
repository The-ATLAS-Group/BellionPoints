package com.darkbladedev.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import com.darkbladedev.storage.StorageManager;
import com.darkbladedev.utils.MessageUtils;

public class DeleteCommand implements CommandExecutor {

    private final StorageManager storageManager;

    public DeleteCommand(StorageManager storageManager) {
        this.storageManager = storageManager;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 2) {
            sender.sendMessage("&cUso: /bellion delete-point <ID>");
            return false;
        }

        Player player = (Player) sender;
        String id = args[1];
        String locX = storageManager.getMonolithData(player.getName(), "location.x", id);
        String locY = storageManager.getMonolithData(player.getName(), "location.y", id);
        String locZ = storageManager.getMonolithData(player.getName(), "location.z", id);

        String checkedID = storageManager.getMonolithData(player.getName(), "id");
        
        if (checkedID == null || !checkedID.equals(id)) {
            sender.sendMessage(MessageUtils.getColoredMessage("&cNo se encontró ningún punto de interés con el ID '&6" + id + "&c'."));
            return false;
        }
        
        if (command.getName().equalsIgnoreCase("delete-point")) {
            if (locX != null && locY != null && locZ != null && checkedID.equals(id)) {
                sender.sendMessage(MessageUtils.getColoredMessage("&aPunto de interés '&6" + id + "' &aeliminado."));
                return true;
            } else {
                sender.sendMessage(MessageUtils.getColoredMessage("&cNo se encontró ningún punto de interés con el ID '&6" + id + "&c'."));
                return false;
            }
        }
        return true;
    }
}
package com.darkbladedev.commands.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.darkbladedev.storage.StorageManager;
import com.darkbladedev.utils.MessageUtils;

public class DeleteDataCommand implements CommandExecutor{
    
    private final StorageManager storageManager;

    public DeleteDataCommand(StorageManager storageManager) {
        this.storageManager = storageManager;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) {
            sender.sendMessage("Usage: /bellion-admin delete-data <player>");
            return false;
        }

        String playerName = args[1];

        if (args[0].equalsIgnoreCase("delete-data")) {
            if(storageManager.deletePlayerData(playerName)) {
                sender.sendMessage(MessageUtils.getColoredMessage("&aDatos borrados existosamente."));
            } else {
                sender.sendMessage(MessageUtils.getColoredMessage("&cDatos no encontrados."));
            }
        }

        return true;
    }
}

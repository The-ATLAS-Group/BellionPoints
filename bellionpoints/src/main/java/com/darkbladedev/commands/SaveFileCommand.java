package com.darkbladedev.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.darkbladedev.storage.StorageManager;
import com.darkbladedev.utils.MessageUtils;

public class SaveFileCommand implements CommandExecutor {

    private final StorageManager storageManager;

    public SaveFileCommand(StorageManager storageManager) {
        this.storageManager = storageManager;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(MessageUtils.getColoredMessage("&cUso: /bellion save-config"));
            return false;
        }

        if (args[0].equalsIgnoreCase("save-config")) {
            storageManager.saveConfig();
            sender.sendMessage(MessageUtils.getColoredMessage("&aConfiguración guardada."));
            return true;
        }

        return false;
    }
}

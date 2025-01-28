package com.darkbladedev.commands.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import com.darkbladedev.storage.StorageManager;
import com.darkbladedev.utils.MessageUtils;

public class BellionAdminCommands implements CommandExecutor {
    private final StorageManager storageManager;

    public BellionAdminCommands(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

        @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage(MessageUtils.getColoredMessage("Uso: /bellion-admin <save-data | delete-data>"));
            return false;
        }

        String subCommand = args[0].toLowerCase();

        switch (subCommand) {
            case "save-data":
                return new SaveDataCommand(storageManager).onCommand(sender, command, label, args);
            case "delete-data":
                return new DeleteDataCommand(storageManager).onCommand(sender, command, label, args);
            default:
                sender.sendMessage(MessageUtils.getColoredMessage("&cComando desconocido. Uso: /bellion-admin  <save-data | delete-data>"));
                return false;
        }
    }
}

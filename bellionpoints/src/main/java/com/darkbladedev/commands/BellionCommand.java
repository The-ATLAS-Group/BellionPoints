package com.darkbladedev.commands;

import com.darkbladedev.commands.admin.SaveDataCommand;
import com.darkbladedev.storage.StorageManager;
import com.darkbladedev.utils.MessageUtils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class BellionCommand implements CommandExecutor {
    private final StorageManager storageManager;

    public BellionCommand(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage(MessageUtils.getColoredMessage("Uso: /bellion <info | create-point | delete-point | locate | teleport>"));
            return false;
        }

        String subCommand = args[0].toLowerCase();

        switch (subCommand) {
            case "info":
                return new InfoCommand().onCommand(sender, command, label, args);
            case "create-point":
                return new CreateCommand(storageManager).onCommand(sender, command, label, args);
            case "delete-point":
                return new DeleteCommand(storageManager).onCommand(sender, command, label, args);
            case "locate":
                return new LocateCommand(storageManager).onCommand(sender, command, label, args);
            case "teleport":
                return new TeleportCommand(storageManager).onCommand(sender, command, label, args);
            case "save-data":
                return new SaveDataCommand(storageManager).onCommand(sender, command, label, args);
            default:
                sender.sendMessage(MessageUtils.getColoredMessage("&cComando desconocido. Uso: /bellion <info | create-point | delete-point | locate | teleport>"));
                return false;
        }
    }
}
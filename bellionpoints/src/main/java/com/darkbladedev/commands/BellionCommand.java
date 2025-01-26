package com.darkbladedev.commands;

import com.darkbladedev.storage.StorageManager;
import com.darkbladedev.utils.MessageUtils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class BellionCommand implements CommandExecutor {
    private final StorageManager idStorage;

    public BellionCommand(StorageManager idStorage) {
        this.idStorage = idStorage;
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
                return new CreateCommand(idStorage).onCommand(sender, command, label, args);
            case "delete-point":
                return new DeleteCommand().onCommand(sender, command, label, args);
            case "locate":
                return new LocateCommand().onCommand(sender, command, label, args);
            case "teleport":
                return new TeleportCommand().onCommand(sender, command, label, args);
            default:
                sender.sendMessage(MessageUtils.getColoredMessage("&cComando desconocido. Uso: /bellion <info | create-point | delete-point | locate | teleport>"));
                return false;
        }
    }
}
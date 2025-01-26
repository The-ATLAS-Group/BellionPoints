package com.darkbladedev.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import com.darkbladedev.utils.MessageUtils;

public class DeleteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 2) {
            sender.sendMessage("&cUso: /bellion delete-point <ID>");
            return false;
        }

        String id = args[1];
        Location loc = CreateCommand.getPointOfInterest(id);
        
        if (command.getName().equalsIgnoreCase("delete-point")) {
            if (loc != null) {
                CreateCommand.removePointOfInterest(id);
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
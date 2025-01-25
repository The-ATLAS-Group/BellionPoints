package com.darkbladedev.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import com.darkbladedev.utils.MessageUtils;

public class LocateCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 2) {
            sender.sendMessage(MessageUtils.getColoredMessage("Uso: /bellion locate <ID>"));
            return false;
        }

        String id = args[1];
        Player player = (Player) sender;
        Location loc = CreateCommand.getPointOfInterest(id);
        if (args[0].equalsIgnoreCase("locate")) {

            if (loc != null) {
                player.sendMessage(MessageUtils.getColoredMessage("El punto de interés '" + id + "' está en la posición " + loc.toString() + "."));
            }
        } else {
            player.sendMessage(MessageUtils.getColoredMessage("No se encontró ningún punto de interés con el ID '" + id + "'."));
        }
        return true;
    }
}
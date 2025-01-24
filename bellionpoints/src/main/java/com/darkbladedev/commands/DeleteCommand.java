package com.darkbladedev.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class DeleteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 2) {
            sender.sendMessage("Uso: /bellion delete-point <ID>");
            return false;
        }

        String id = args[1];
        Location loc = CreateCommand.getPointOfInterest(id);

        if (loc != null) {
            CreateCommand.removePointOfInterest(id);
            sender.sendMessage("Punto de interés '" + id + "' eliminado.");
        } else {
            sender.sendMessage("No se encontró ningún punto de interés con el ID '" + id + "'.");
        }

        return true;
    }
}
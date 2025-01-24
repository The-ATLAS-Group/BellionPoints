package com.darkbladedev.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeleportCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 2) {
            sender.sendMessage("Uso: /bellion teleport <player> <ID>");
            return false;
        }

        String playerName = args[0];
        String id = args[1];
        Player player = sender.getServer().getPlayer(playerName);
        Location loc = CreateCommand.getPointOfInterest(id);

        if (player != null && loc != null) {
            player.teleport(loc);
            sender.sendMessage("Jugador '" + playerName + "' teletransportado al punto de interés '" + id + "'.");
        } else if (player == null) {
            sender.sendMessage("No se encontró ningún jugador con el nombre '" + playerName + "'.");
        } else {
            sender.sendMessage("No se encontró ningún punto de interés con el ID '" + id + "'.");
        }

        return true;
    }
}
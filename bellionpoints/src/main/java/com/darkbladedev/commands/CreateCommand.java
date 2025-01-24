package com.darkbladedev.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CreateCommand implements CommandExecutor {

    private static final Map<String, Location> pointsOfInterest = new HashMap<>();
    private static Player player;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 4) {
            sender.sendMessage("Uso: /bellion create-point <ID> <type> <position> [world] [name]");
            return false;
        }

        String id = args[0];
        String type = args[1];
        String position = args[2];
        String name = args.length > 4 ? args[4] : "Punto de interes";
        String playerWorld = args.length > 5 ? args[5] : ((Player) sender).getWorld().getName();

        Location loc = null;

        if (type.equalsIgnoreCase("current")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                loc = player.getLocation();
            } else {
                sender.sendMessage("Solo los jugadores pueden usar su ubicación actual.");
                return false;
            }
        } else if (type.equalsIgnoreCase("specific")) {
            String[] posArray = position.split(",");
            if (posArray.length == 3) {
                try {
                    double x = Double.parseDouble(posArray[0]);
                    double y = Double.parseDouble(posArray[1]);
                    double z = Double.parseDouble(posArray[2]);

                    World world = Bukkit.getWorld(playerWorld);

                    if (world != null) {
                        loc = new Location(world, x, y, z);
                    } else {
                        sender.sendMessage("El mundo especificado no existe.");
                        return false;
                    }
                } catch (NumberFormatException e) {
                    sender.sendMessage("La posición debe estar en el formato x,y,z con números válidos.");
                    return false;
                }
            } else {
                sender.sendMessage("La posición debe estar en el formato x,y,z.");
                return false;
            }
        } else {
            sender.sendMessage("Tipo de ubicación no válido. Usa 'current' o 'specific'.");
            return false;
        }

        if (loc != null) {
            pointsOfInterest.put(id, loc);
            sender.sendMessage("Punto de interés '" + id + "' creado en la posición " + loc.toString() + ".");
        }

        return true;
    }

    public static Location getPointOfInterest(String id) {
        return pointsOfInterest.get(id);
    }

    public static void removePointOfInterest(String id) {
        pointsOfInterest.remove(id);
    }
}
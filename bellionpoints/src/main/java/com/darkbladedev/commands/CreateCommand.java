package com.darkbladedev.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CreateCommand implements CommandExecutor {

    private static final Map<String, Location> pointsOfInterest = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 4) {
            sender.sendMessage("Uso: /bellion create-point <ID> <type> <position> [name]");
            return false;
        }

        String id = args[1];
        String type = args[2];
        String position = args[3];
        String name = args.length > 4 ? args[4] : "";

        String[] posArray = position.split(",");
        if (posArray.length == 3) {
            try {
                double x = Double.parseDouble(posArray[0]);
                double y = Double.parseDouble(posArray[1]);
                double z = Double.parseDouble(posArray[2]);

                World world = Bukkit.getWorld("world"); // Reemplaza "world" con el nombre de tu mundo

                if (world != null) {
                    Location loc = new Location(world, x, y, z);
                    pointsOfInterest.put(id, loc);
                    sender.sendMessage("Punto de interés '" + id + "' creado en la posición " + position + ".");
                } else {
                    sender.sendMessage("El mundo especificado no existe.");
                }
            } catch (NumberFormatException e) {
                sender.sendMessage("La posición debe estar en el formato x,y,z con números válidos.");
            }
        } else {
            sender.sendMessage("La posición debe estar en el formato x,y,z.");
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
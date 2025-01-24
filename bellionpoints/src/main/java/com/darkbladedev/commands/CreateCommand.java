package com.darkbladedev.commands;

import com.darkbladedev.storage.IDStorage;
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
    private final IDStorage idStorage;

    public CreateCommand(IDStorage idStorage) {
        this.idStorage = idStorage;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Solo los jugadores pueden usar este comando.");
            return false;
        }

        if (args.length < 3) {
            sender.sendMessage("Uso: /bellion create-point <ID> <type> <position> [world] [name]");
            return false;
        }

        Player player = (Player) sender;
        String id = args[0];
        String type = args[1];
        String position = args[2];
        String name = args.length > 4 ? args[4] : "Punto de interes";
        String playerWorld = args.length > 5 ? args[5] : player.getWorld().getName();

        Location loc = null;

        if (type.equalsIgnoreCase("current")) {
            loc = player.getLocation();
        } else if (type.equalsIgnoreCase("specific")) {
            String[] posArray = position.split(",");
            if (posArray.length == 3) {
                try {
                    double x = Double.parseDouble(posArray[0]);
                    double y = Double.parseDouble(posArray[1]);
                    double z = Double.parseDouble(posArray[2]);
                    loc = new Location(player.getWorld(), x, y, z);
                } catch (NumberFormatException e) {
                    sender.sendMessage("Posición inválida.");
                    return false;
                }
            } else {
                sender.sendMessage("Posición inválida.");
                return false;
            }
        }

        if (loc != null) {
            // Guarda el ID en el archivo
            idStorage.addPlayerID(player.getUniqueId(), id);
            sender.sendMessage("Punto de interés creado con ID: " + id);
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
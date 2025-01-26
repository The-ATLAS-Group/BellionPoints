package com.darkbladedev.commands;

import com.darkbladedev.storage.StorageManager;
import com.darkbladedev.utils.MessageUtils;

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
    private final StorageManager idStorage;

    public CreateCommand(StorageManager idStorage) {
        this.idStorage = idStorage;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageUtils.getColoredMessage("&cSolo los jugadores pueden usar este comando."));
            return false;
        }

        if (args.length < 4) {
            sender.sendMessage(MessageUtils.getColoredMessage("&cUso: /bellion create-point <ID> <type> <position> [world] [name]"));
            return false;
        }

        Player player = (Player) sender;
        String id = args[1];
        String type = args[2];
        String position = args[3];
        String name = args.length < 4 ? args[4] : "Punto de interes";
        
        Location loc = null;
        Location coords = null;

        
        if (command.getName().equalsIgnoreCase("create-point")) {
            if (type.equalsIgnoreCase("current")) {
                coords = player.getLocation();
                World world = player.getWorld();

                loc = new Location(world, coords.getX(), coords.getY(), coords.getZ());


            } else if (type.equalsIgnoreCase("specific")) {
                String[] posArray = position.split(",");
                if (posArray.length == 3) {
                    try {
                        double x = Double.parseDouble(posArray[0]);
                        double y = Double.parseDouble(posArray[1]);
                        double z = Double.parseDouble(posArray[2]);
                        loc = new Location(player.getWorld(), x, y, z);

                    } catch (NumberFormatException e) {
                        sender.sendMessage(MessageUtils.getColoredMessage("&cPosición inválida."));
                        return false;
                    }
                } else {
                    sender.sendMessage(MessageUtils.getColoredMessage("&cPosición inválida."));
                    return false;
                }
            }

        }
        
        if (loc != null) {
            // Guarda el ID en el archivo
            idStorage.saveMonolithData(player, id, loc, name);
            sender.sendMessage(MessageUtils.getColoredMessage("&aPunto de interés creado con ID: &6" + id + "&anombrado como: &6" + name));
            
            return true;
        }
                
        return false;
    }

    public static Location getPointOfInterest(String id) {
        return pointsOfInterest.get(id);
    }

    public static void removePointOfInterest(String id) {
        pointsOfInterest.remove(id);
    }
}
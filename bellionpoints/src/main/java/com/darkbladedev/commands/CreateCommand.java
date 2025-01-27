package com.darkbladedev.commands;

import com.darkbladedev.storage.StorageManager;
import com.darkbladedev.utils.MessageUtils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CreateCommand implements CommandExecutor {

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
            sender.sendMessage(MessageUtils.getColoredMessage("&cUso: /bellion create-point <ID> <type> <position> [name]"));
            return false;
        }

        Player player = (Player) sender;
        String id = args[1];
        String type = args[2];
        //String world = args.length < 8 ? args[7] : player.getWorld().getName();
        
        Location loc = null;
        Location coords = null;

        
        if (command.getName().equalsIgnoreCase("create-point")) {
            if (type.equalsIgnoreCase("current")) {
                coords = player.getLocation();
                World playerWorld = player.getWorld();
                String name = args.length < 5 ? args[4] : "Punto de interes";

                loc = new Location(playerWorld, coords.getX(), coords.getY(), coords.getZ());

                if (loc != null) {
                    // Guarda el monolito en el archivo de datos
                    idStorage.saveMonolithData(player, id, loc, name);
                    sender.sendMessage(MessageUtils.getColoredMessage("&aPunto de interés creado con ID: &6" + id + "&anombrado como: &6" + name));
                    Bukkit.getLogger().info(MessageUtils.getColoredMessage("&aPunto de interés creado con ID: &6" + id + "&anombrado como: &6" + name + " &aa nombre de: &6" + player.getName()));
                    return true;
                }

            } else if (type.equalsIgnoreCase("specific")) {

            String posX = args[3];
            String posY = args[4];
            String posZ = args[5];
            String name = args.length < 7 ? args[6] : "Punto de interes";


                String[] posArray = {posX, posY, posZ};
                if (posArray.length == 3) {
                    try {
                        double x = Double.parseDouble(posArray[0]);
                        double y = Double.parseDouble(posArray[1]);
                        double z = Double.parseDouble(posArray[2]);
                        loc = new Location(player.getWorld(), x, y, z);

                        if (loc != null) {
                            // Guarda el ID en el archivo
                            idStorage.saveMonolithData(player, id, loc, name);
                            sender.sendMessage(MessageUtils.getColoredMessage("&aPunto de interés creado con ID: &6" + id + "&anombrado como: &6" + name));
                            Bukkit.getLogger().info(MessageUtils.getColoredMessage("&aPunto de interés creado con ID: &6" + id + "&anombrado como: &6" + name + " &aa nombre de: &6" + player.getName()));
                            return true;
                        }

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
        
                
    return true;
    }
}
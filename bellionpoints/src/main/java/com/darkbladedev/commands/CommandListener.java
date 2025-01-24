package com.darkbladedev.commands;

import org.jetbrains.annotations.NotNull;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandListener implements CommandExecutor {

    String guide = 
            """
            -- GUIA DE BELLION POINTS --
            Descripción de BellionPoints:
              BellionPoints es un plugin que agrega bloques de teletransporte localizables.
            
            Comandos disponibles:
              /bellion create-point <ID> <type> <position> [name]   Crea un punto de interés localizable
              /bellion delete-point <ID>    Elimina un punto de interés creado
              /bellion locate <ID>  Localiza un punto de interés creado
              /bellion teleport <player> <ID>   Teletransporta al jugador hacia el punto de interés creado
            """;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        String action = args[0];

        if (args.length > 0 && args[0].toString().equalsIgnoreCase("create-point")) {
            // Argimentos del comando create-point
            String id = args[1];
            String type = args[2];
            String position = args[3];
            String name = args[4];


            // Desglosar la posición en x, y, z
            String[] posArray = position.split(",");
            if (posArray.length == 3) {
                try {
                    double x = Double.parseDouble(posArray[0]);
                    double y = Double.parseDouble(posArray[1]);
                    double z = Double.parseDouble(posArray[2]);

                    World world = Bukkit.getWorld("world"); // Reemplaza "world" con el nombre de tu mundo

                    if (world != null) {
                        Location loc = new Location(world, x, y, z);
                        // Aquí puedes usar la ubicación 'loc' según sea necesario
                    } else {
                        sender.sendMessage("El mundo especificado no existe.");
                    }
                } catch (NumberFormatException e) {
                    sender.sendMessage("La posición debe estar en el formato x,y,z con números válidos.");
                }
            } else {
                sender.sendMessage("La posición debe estar en el formato x,y,z.");
            }

        
        } else {
            sender.sendMessage(guide);
            return true;
        }
                return false;
                

        
    }
}
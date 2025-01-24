package com.darkbladedev.commands;

import org.jetbrains.annotations.NotNull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class InfoCommand implements CommandExecutor {

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
        sender.sendMessage(guide);
        return true;
    }
}
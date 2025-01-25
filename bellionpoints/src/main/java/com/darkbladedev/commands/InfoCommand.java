package com.darkbladedev.commands;

import org.jetbrains.annotations.NotNull;

import com.darkbladedev.utils.MessageUtils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class InfoCommand implements CommandExecutor {

    String guide = 
            """
            -- GUIA DE BELLION POINTS --
            &fDescripción de BellionPoints:
              &5BellionPoints es un plugin que agrega bloques de teletransporte localizables.
            
            &fComandos disponibles:
              &6/bellion &acreate-point <ID> <type> <position> [name]   &6Crea un punto de interés localizable
              &6/bellion &adelete-point <ID>  &6Elimina un punto de interés creado
              &6/bellion &alocate <ID>  &6Localiza un punto de interés creado
              &6/bellion &ateleport <player> <ID>   &6Teletransporta al jugador hacia el punto de interés creado
            """;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (args[0].equalsIgnoreCase("help")) {   

      sender.sendMessage(MessageUtils.getColoredMessage(guide));
    }
      return true;
    }
}
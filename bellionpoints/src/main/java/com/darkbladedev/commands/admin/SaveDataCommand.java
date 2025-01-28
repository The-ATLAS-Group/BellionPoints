package com.darkbladedev.commands.admin;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.darkbladedev.storage.StorageManager;
import com.darkbladedev.utils.MessageUtils;

public class SaveDataCommand implements CommandExecutor {

    private final StorageManager storageManager;

    public SaveDataCommand(StorageManager storageManager) {
        this.storageManager = storageManager;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(MessageUtils.getColoredMessage("&cUso: /bellion-admin save-data"));
            return false;
        }
        
        if (args[0].equalsIgnoreCase("save-data")) {
            Location loc = null;
            Player player = (Player) sender;
            Location coords = null;
            coords = player.getLocation();

            World playerWorld = player.getWorld();
            loc = new Location(playerWorld, coords.getX(), coords.getY(), coords.getZ());
            //storageManager.createData();
            storageManager.saveMonolithData(player, "test", loc, "test");
            sender.sendMessage(MessageUtils.getColoredMessage("&aConfiguración creada."));
            return true;
        }

        return false;
    }
}

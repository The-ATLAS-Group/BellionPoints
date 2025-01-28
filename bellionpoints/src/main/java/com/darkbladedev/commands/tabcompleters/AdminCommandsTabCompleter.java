package com.darkbladedev.commands.tabcompleters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import com.darkbladedev.storage.StorageManager;

public class AdminCommandsTabCompleter implements TabCompleter {
    
    public AdminCommandsTabCompleter(StorageManager storageManager) {
    }
    
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        

        if (command.getName().equalsIgnoreCase("bellion-admin")) {
            if (args.length == 1) {
                completions.addAll(Arrays.asList("save-data", "delete-data"));
            }
            else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("delete-data")) {
                    // Add the player name target for the teleport command
                    sender.getServer().getOnlinePlayers().forEach(player -> completions.add(player.getName()));
                }
            }
            else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("save-data")) {
                    // Add the player name target for the teleport command
                    sender.getServer().getOnlinePlayers().forEach(player -> completions.add(player.getName()));
                }
            }
        }
        return completions;
    }
    
}

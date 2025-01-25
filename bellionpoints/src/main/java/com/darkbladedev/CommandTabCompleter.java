package com.darkbladedev;

import com.darkbladedev.commands.InfoCommand;
import com.darkbladedev.storage.IDStorage;
import com.darkbladedev.utils.MessageUtils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandTabCompleter implements TabCompleter {

    private final IDStorage idStorage;

    public CommandTabCompleter(IDStorage idStorage) {
        this.idStorage = idStorage;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        if (command.getName().equalsIgnoreCase("bellion")) {
            if (args.length == 1) {
                completions.addAll(Arrays.asList("info", "create-point", "delete-point", "locate", "teleport"));
            
            } else if (args[0].equalsIgnoreCase("create-point")) {
                if (args.length == 1) {
                    completions.add("ID");
                
                } else if (args.length == 2) {
                    completions.addAll(Arrays.asList("current", "specific"));
                
                } else if (args.length == 3) {
                    
                    if (args[3].equalsIgnoreCase("specific") && sender instanceof Player) {
                        Player player = (Player) sender;
                        if (args.length == 4) {
                            completions.add(String.valueOf(player.getLocation().getBlockX()));
                        } else if (args.length == 5) {
                            completions.add(String.valueOf(player.getLocation().getBlockY()));
                        } else if (args.length == 6) {
                            completions.add(String.valueOf(player.getLocation().getBlockZ()));
                        }

                    }
                    
                } else if (args.length == 4) {
                    completions.add("name");
                
                }

            } else if (args[0].equalsIgnoreCase("delete-point")) {
                    
                    // Add IDs for delete-point command
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        completions.addAll(idStorage.getPlayerIDs(player.getUniqueId()));
                    }
                }
            } else if (args.length == 3 && args[0].equalsIgnoreCase("teleport")) {
                // Add the player name target for the teleport command
                sender.getServer().getOnlinePlayers().forEach(player -> completions.add(player.getName()));
                // Add IDs for the teleport command
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    completions.addAll(idStorage.getPlayerIDs(player.getUniqueId()));
                }
            } else if (args[0].equalsIgnoreCase("locate")) {
                // Add IDs for the locate command
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    completions.addAll(idStorage.getPlayerIDs(player.getUniqueId()));
                }
            } else if (args.length == 0) {
                sender.sendMessage(MessageUtils.getColoredMessage(InfoCommand.guide));
            }
        return completions;
    }
}
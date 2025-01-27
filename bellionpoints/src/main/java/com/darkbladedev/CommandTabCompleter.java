package com.darkbladedev;

import com.darkbladedev.commands.InfoCommand;
import com.darkbladedev.storage.StorageManager;
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

    private final StorageManager storageManager;

    public CommandTabCompleter(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        
        List<String> completions = new ArrayList<>();
        if (command.getName().equalsIgnoreCase("bellion")) {
            if (args.length == 1) {
                completions.addAll(Arrays.asList("info", "create-point", "delete-point", "locate", "teleport"));
            
            } else if (args[0].equalsIgnoreCase("create-point")) {
                if (args.length == 2) {
                    completions.add("ID");
                
                } else if (args.length == 3) {
                    completions.addAll(Arrays.asList("current", "specific"));
                
                } else if (args.length == 4) {
                    
                    if (args[3].equalsIgnoreCase("specific") && sender instanceof Player) {
                        Player player = (Player) sender;
                        if (args.length == 5) {
                            completions.add(String.valueOf(player.getLocation().getBlockX()));
                        } else if (args.length == 6) {
                            completions.add(String.valueOf(player.getLocation().getBlockY()));
                        } else if (args.length == 7) {
                            completions.add(String.valueOf(player.getLocation().getBlockZ()));
                        }

                    }
                    
                } else if (args[3].equalsIgnoreCase("current") && args.length == 4) {
                    completions.add("name");
                
                }

            } else if (args[0].equalsIgnoreCase("delete-point")) {
                    if (args.length == 2) {
                        // Add IDs for delete-point command
                        if (sender instanceof Player) {
                            Player player = (Player) sender;
                            completions.addAll(storageManager.getMonolithIDs(player.getName()));
                        } else if (storageManager.getMonolithIDs(args[1]) == null) {
                            sender.sendMessage(MessageUtils.getColoredMessage("&cNo se encontró ningún punto de interés con el ID '&6" + args[1] + "'&c."));
                        }

                }
            } else if (args.length == 3 && args[0].equalsIgnoreCase("teleport")) {
                // Add the player name target for the teleport command
                sender.getServer().getOnlinePlayers().forEach(player -> completions.add(player.getName()));
                // Add IDs for the teleport command
                if (sender instanceof Player) {

                    Player player = (Player) sender;
                    
                    completions.addAll(storageManager.getMonolithIDs(player.getName()));
                }

            } else if (args[0].equalsIgnoreCase("locate")) {
                if (args.length == 2) {
                    // Add IDs for the locate command
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        List<String> monolithIDs = storageManager.getMonolithIDs(player.getName());
                        if (monolithIDs != null) {
                            completions.addAll(monolithIDs);
                            }
                        }
                    }
            } else if (args.length == 0) {
                sender.sendMessage(MessageUtils.getColoredMessage(InfoCommand.guide));
            }
        return completions;
        }
    return completions;
    }
}
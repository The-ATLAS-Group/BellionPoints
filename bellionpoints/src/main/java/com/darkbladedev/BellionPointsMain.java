package com.darkbladedev;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

import com.darkbladedev.commands.BellionCommand;
import com.darkbladedev.storage.StorageManager;

public class BellionPointsMain extends JavaPlugin {
    private StorageManager idStorage;

    public final String prefix = "&3[&cBellion &5Points&3]&a";

    @Override
    public void onEnable() {

        // Create the data folder if it doesn't exist
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        idStorage = new StorageManager(getDataFolder());

        registerCommands();

        Bukkit.getConsoleSender().sendMessage(
            ChatColor.translateAlternateColorCodes('&', String.format("%s ha sido activado!", prefix)));

        Plugin executableBlocks;
        executableBlocks = Bukkit.getPluginManager().getPlugin("ExecutableBlocks");
        if (executableBlocks != null && executableBlocks.isEnabled()) {
            Bukkit.getConsoleSender().sendMessage(
            ChatColor.translateAlternateColorCodes('&', String.format("%s ExecutableBlocks vinculado!", prefix)));
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(
            ChatColor.translateAlternateColorCodes('&', String.format("%s ha sido desactivado!", prefix)));
    }

    public void registerCommands() {
        getCommand("bellion").setExecutor(new BellionCommand(idStorage));

        // Register the tab completer
        getCommand("bellion").setTabCompleter(new CommandTabCompleter(idStorage));
    }
}
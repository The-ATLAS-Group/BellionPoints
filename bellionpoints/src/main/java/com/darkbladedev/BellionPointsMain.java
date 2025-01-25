package com.darkbladedev;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

import com.darkbladedev.commands.InfoCommand;
import com.darkbladedev.storage.IDStorage;

public class BellionPointsMain extends JavaPlugin {
    private IDStorage idStorage;

    public final String prefix = "&3[&cBellion &5Points&3]&a";

    @Override
    public void onEnable() {
        idStorage = new IDStorage(getDataFolder());

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
        getCommand("bellion").setExecutor(new InfoCommand());

        // Register the tab completer
        getCommand("bellion").setTabCompleter(new CommandTabCompleter(idStorage));
    }
}
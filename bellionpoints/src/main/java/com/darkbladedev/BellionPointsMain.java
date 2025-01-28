package com.darkbladedev;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

import com.darkbladedev.commands.BellionCommand;
import com.darkbladedev.commands.admin.DeleteDataCommand;
import com.darkbladedev.commands.tabcompleters.AdminCommandsTabCompleter;
import com.darkbladedev.commands.tabcompleters.CommandTabCompleter;
import com.darkbladedev.events.MonolithPlaceEvent;
import com.darkbladedev.storage.StorageManager;

public class BellionPointsMain extends JavaPlugin {
    private StorageManager storageManager;

    public final String prefix = "&3[&cBellion &5Points&3]&a";

    @Override
    public void onEnable() {

        // Create the data folder if it doesn't exist
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        storageManager = new StorageManager(getDataFolder());

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
        if (getCommand("bellion") != null) {
            getCommand("bellion").setExecutor(new BellionCommand(storageManager));
            // Register the tab completer
            getCommand("bellion").setTabCompleter(new CommandTabCompleter(storageManager));
        }

        if (getCommand("bellion-admin") != null) {
            getCommand("bellion-admin").setExecutor(new DeleteDataCommand(storageManager));
            // Register the tab completer
            getCommand("bellion-admin").setTabCompleter(new AdminCommandsTabCompleter(storageManager));
        }
        getPluginLoader().createRegisteredListeners(new MonolithPlaceEvent(storageManager), this);
    }
}
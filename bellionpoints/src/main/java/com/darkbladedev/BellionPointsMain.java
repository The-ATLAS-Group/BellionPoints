package com.darkbladedev;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.darkbladedev.commands.InfoCommand;
import com.darkbladedev.commands.CreateCommand;
import com.darkbladedev.commands.LocateCommand;
import com.darkbladedev.commands.DeleteCommand;
import com.darkbladedev.commands.TeleportCommand;
import com.darkbladedev.commands.CommandTabCompleter;
import com.darkbladedev.storage.IDStorage;

public class BellionPointsMain extends JavaPlugin {
    private IDStorage idStorage;

    @Override
    public void onEnable() {
        idStorage = new IDStorage(getDataFolder());

        registerCommands();

        getLogger().info("BellionPoints has been enabled!");

        Plugin executableBlocks;
        executableBlocks = Bukkit.getPluginManager().getPlugin("ExecutableBlocks");
        if (executableBlocks != null && executableBlocks.isEnabled()) {
            getServer().getLogger().info("[BellionPoints] ExecutableBlocks hooked !");
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("BellionPoints has been disabled!");
    }

    public void registerCommands() {
        getCommand("bellion").setExecutor(new InfoCommand());
        getCommand("bellion create-point").setExecutor(new CreateCommand(idStorage));
        getCommand("bellion locate").setExecutor(new LocateCommand());
        getCommand("bellion delete-point").setExecutor(new DeleteCommand());
        getCommand("bellion teleport").setExecutor(new TeleportCommand());

        // Register the tab completer
        getCommand("bellion").setTabCompleter(new CommandTabCompleter());
    }
}
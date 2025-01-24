package com.darkbladedev;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.darkbladedev.commands.CommandListener;
import com.darkbladedev.commands.CreateCommand;
import com.darkbladedev.commands.LocateCommand;
import com.darkbladedev.commands.DeleteCommand;
import com.darkbladedev.commands.TeleportCommand;

public class BellionPointsMain extends JavaPlugin {
    
    @Override
    public void onEnable() {

        registerCommands();

        getLogger().info("BellionPoints has been enabled!");
    
        Plugin executableBlocks;
        executableBlocks = Bukkit.getPluginManager().getPlugin("ExecutableBlocks");
        if(executableBlocks != null && executableBlocks.isEnabled()) {
            getServer().getLogger().info("[BellionPoints] ExecutableBlocks hooked !");
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("BellionPoints has been disabled!");
    }

    public void registerCommands() {
        getCommand("bellion").setExecutor(new CommandListener());
        getCommand("bellion create-point").setExecutor(new CreateCommand());
        getCommand("bellion locate").setExecutor(new LocateCommand());
        getCommand("bellion delete-point").setExecutor(new DeleteCommand());
        getCommand("bellion teleport").setExecutor(new TeleportCommand());
    }
}
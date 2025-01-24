package com.darkbladedev;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class BellionPointsMain extends JavaPlugin {
    
    @Override
    public void onEnable() {
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
}
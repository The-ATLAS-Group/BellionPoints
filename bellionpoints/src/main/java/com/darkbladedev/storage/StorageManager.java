package com.darkbladedev.storage;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class StorageManager {
    private final File file;
    private final FileConfiguration config;

    public StorageManager(File dataFolder) {
        this.file = new File(dataFolder, "points_data.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }


    public void saveMonolithData(Player player, String monolithID, Location location, String name) {
        String playerName = player.getName();
        UUID monolithUUID = UUID.randomUUID();

        String path = "players." + playerName.toString() + ".monoliths." + monolithUUID;

        config.set(path + ".id", monolithID);
        config.set(path + ".owner", playerName);
        config.set(path + ".name", name);
        config.set(path + ".location.world", location.getWorld().getName());
        config.set(path + ".location.x", location.getX());
        config.set(path + ".location.y", location.getY());
        config.set(path + ".location.z", location.getZ());

        saveConfig();
    }

    public Map<String, Object> getPlayerData(String playerName) {
        ConfigurationSection playerSection = config.getConfigurationSection("players." + playerName);
        if (playerSection != null) {
            return playerSection.getValues(true);
        }
        return null;
    }

    public Map<String, Object> getMonolithData(String playerName, String monolithID) {
        ConfigurationSection monolithSection = config.getConfigurationSection("players." + playerName + ".monoliths.");
        if (monolithSection != null) {
            for (String key : monolithSection.getKeys(false)) {
                if (monolithSection.getString(key + ".id").equals(monolithID)) {
                    return monolithSection.getConfigurationSection(key).getValues(true);
                }
            }
        }
        return null;
    }


    public String getMonolithOwner(String playerName) {
        ConfigurationSection monolithsSection = config.getConfigurationSection("players." + playerName + ".monoliths");
        if (monolithsSection != null) {
            for (String key : monolithsSection.getKeys(false)) {
                String monolithsPath = "players." + playerName + ".monoliths." + key;
                return (config.getString(monolithsPath + ".owner"));
            }
        }
            return null;
    }

    public List<String> getMonolithIDs(String playerName) {

        List<String> monolithIDs = new ArrayList<>();
        ConfigurationSection monolithSection = config.getConfigurationSection("players." + playerName + ".monoliths.");
        if (monolithSection != null) {
            for (String key : monolithSection.getKeys(false)) {
                if (monolithSection != null) {
                            monolithIDs.add(key);
                        }
                    }
                    return monolithIDs;
                }
                return null;
            }


    public void deletePlayerData(String playerName) {
        config.set("players." + playerName, null);
        saveConfig();
    }


    private void saveConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
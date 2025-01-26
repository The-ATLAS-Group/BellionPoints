package com.darkbladedev.storage;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
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

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }


    public void saveMonolithData(Entity player, String monolithID, Location location, String name) {
        String playerName = player.getName();
        String monolithUUID = UUID.randomUUID().toString();

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

    public String getMonolithData(String playerName, String key) {
        ConfigurationSection monolithSectionPath = config.getConfigurationSection("players." + playerName + ".monoliths.");
        if (monolithSectionPath != null) {
            for (String iteratorKey : monolithSectionPath.getKeys(false)) {
                if (monolithSectionPath.getString(iteratorKey + "." + key) != null) {
                    return String.valueOf(monolithSectionPath.getConfigurationSection(key).getValues(false));
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
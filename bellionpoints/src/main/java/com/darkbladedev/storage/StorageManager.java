package com.darkbladedev.storage;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;

import com.darkbladedev.utils.MessageUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        public void saveConfig() {
        try {
            config.save(file);
            Bukkit.getLogger().info(MessageUtils.getColoredMessage("&6Configuración guardada exitosamente!"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createData() {
        if (config.getConfigurationSection("players") == null) {
            config.createSection("players");
            ConfigurationSection configSection = config.getConfigurationSection("players");
            MemorySection.createPath(configSection, "players.DarkBladeDev.monoliths.1");
            saveConfig();

            Bukkit.getLogger().info(MessageUtils.getColoredMessage("&6Configuración creada exitosamente!"));
        }
    }


    private int getNextMonolithID(String playerName) {
        ConfigurationSection monolithsSection = config.getConfigurationSection("players." + playerName + ".monoliths");
        if (monolithsSection == null) {
            return 1;
        }
        return monolithsSection.getKeys(false).size() + 1;
    }


    public void saveMonolithData(Entity player, String monolithID, Location location, String name) {
        String playerName = player.getName();
        Integer monolithIDNumber = getNextMonolithID(playerName);

        String path = "players." + playerName.toString() + ".monoliths." + monolithIDNumber;
        config.createSection(path);
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

    public String getMonolithData(String playerName, String key, String monolithPath) {
        ConfigurationSection monolithSectionPath = null;
        boolean monolithIndexedSearch = false;

        if (monolithPath == null || monolithPath.isEmpty() == true) {
            monolithSectionPath = config.getConfigurationSection("players." + playerName + ".monoliths");
        } else {
            monolithSectionPath = config.getConfigurationSection("players." + playerName + ".monoliths." + monolithPath);
            monolithIndexedSearch = true;
        }
        if (monolithIndexedSearch == false) {
            for (String uuidString : monolithSectionPath.getKeys(false)) {
                ConfigurationSection monolithSection = monolithSectionPath.getConfigurationSection(uuidString);
                if (monolithSection != null && monolithSection.getString(key).isBlank() != true) {
                    return String.valueOf(monolithSectionPath.getConfigurationSection(key).getValues(false));
                }
            }
        } else if (monolithIndexedSearch == true){
            ConfigurationSection monolithSection = monolithSectionPath.getConfigurationSection(key);
            if (monolithSection.getString(key).isBlank() != true) {
                return String.valueOf(monolithSectionPath.getConfigurationSection(key).getValues(false));
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
                return monolithIDs;
            }


    public boolean deletePlayerData(String playerName) {
        try {
            config.set("players." + playerName, null);
            saveConfig();
            Bukkit.getLogger().info(MessageUtils.getColoredMessage("&6Datos borrados exitosamente!"));
            return true;
        } catch (Exception e) {
            Bukkit.getLogger().info(MessageUtils.getColoredMessage("&cNo se encontraron los datos solicitados."));
            return false;
        }
    }

    public String getMonolithData(String playerName, String key) {
        return getMonolithData(playerName, key, null);
    }



}
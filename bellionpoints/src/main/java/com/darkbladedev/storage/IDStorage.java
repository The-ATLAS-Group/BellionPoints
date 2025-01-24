package com.darkbladedev.storage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class IDStorage {
    private final File file;
    private final FileConfiguration config;

    public IDStorage(File dataFolder) {
        this.file = new File(dataFolder, "points_data.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public List<String> getPlayerIDs(UUID playerUUID) {
        return config.getStringList(playerUUID.toString());
    }

    public Map<String, String> getPointsNames(UUID playerUUID) {
        Map<String, String> pointsNames = new HashMap<>();
        List<String> ids = getPlayerIDs(playerUUID);
        for (String id : ids) {
            String name = config.getString(playerUUID.toString() + "." + id);
            if (name != null) {
                pointsNames.put(id, name);
            }
        }
        return pointsNames;
    }

    public void addPlayerData(UUID playerUUID, String id, String name) {
        List<String> ids = getPlayerIDs(playerUUID);
        if (!ids.contains(id)) {
            ids.add(id);
            config.set(playerUUID.toString(), ids);
            config.set(playerUUID.toString() + "." + id, name);
            saveConfig();
        }
    }

    private void saveConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
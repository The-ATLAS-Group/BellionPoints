package com.darkbladedev.storage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class IDStorage {
    private final File file;
    private final FileConfiguration config;

    public IDStorage(File dataFolder) {
        this.file = new File(dataFolder, "playerdata.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public List<String> getPlayerIDs(UUID playerUUID) {
        return config.getStringList(playerUUID.toString());
    }

    public void addPlayerID(UUID playerUUID, String id) {
        List<String> ids = getPlayerIDs(playerUUID);
        if (!ids.contains(id)) {
            ids.add(id);
            config.set(playerUUID.toString(), ids);
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
package me.foshou.plugins.anarchy.core;

import me.foshou.plugins.anarchy.core.features.Register;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static FileConfiguration config;
    public static Main plugin;

    @Override
    public void onLoad() {
        plugin = this;
        config = getConfig();
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        try {
            new Register().load();
        } catch (Exception ignored) {
            System.err.println(ignored.getMessage());
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

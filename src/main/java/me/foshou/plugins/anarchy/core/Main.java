package me.foshou.plugins.anarchy.core;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static final FileConfiguration config = Main.getProvidingPlugin(Main.class).getConfig();
    public static final Main plugin = (Main) Main.getProvidingPlugin(Main.class);

    @Override
    public void onEnable() {
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

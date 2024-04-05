package me.foshou.plugins.anarchy.core.features;

import me.foshou.plugins.anarchy.core.Main;
import me.foshou.plugins.anarchy.core.commands.StatisticsInfo;
import me.foshou.plugins.anarchy.core.commands.Suicide;
import me.foshou.plugins.anarchy.core.features.dupe.*;
import me.foshou.plugins.anarchy.core.features.limits.AntiFlight;
import me.foshou.plugins.anarchy.core.features.limits.ChunkEntities;
import me.foshou.plugins.anarchy.core.features.limits.CrystalSpeed;
import me.foshou.plugins.anarchy.core.features.limits.IllegalEnchantments;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;

public class Register {
    public void load() throws Exception {
        register(
                new ChickenDupe(), new CommandDupe(), new DonkeyDupe(),
                new ItemFrameDupe(), new ShulkerDupe()
        );
        register(
                new AntiFlight(), new ChunkEntities(), new CrystalSpeed(),
                new IllegalEnchantments()
        );
        registerCommand(
                new me.foshou.plugins.anarchy.core.commands.Main(),
                "anarchycore"
        );
        registerCommand(
                new StatisticsInfo(),
                "stats"
        );
        registerCommand(
                new Suicide(),
                "suicide"
        );
    }

    public void registerL(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, Main.plugin);
    }

    public void register(Listener... listener) {
        for (Listener a : listener) {
            registerL(a);
        }
    }

    public void registerCommand(CommandExecutor command, String name) throws Exception {
        if (command instanceof Listener) {
            registerL((Listener) command);
        }
        PluginCommand pc = Bukkit.getPluginCommand(name);
        if (pc == null) {
            throw new Exception("Error: The Command is not added into plugin.yml!");
        }
        pc.setExecutor(command);
    }


}

package me.foshou.plugins.anarchy.core.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Main implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        sender.sendMessage("AnarchyCore 0.1.0 by Sunshine_Light_");
        return true;
    }
}

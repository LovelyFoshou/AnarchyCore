package me.foshou.plugins.anarchy.core.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;

public class Suicide implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof HumanEntity) {
            ((HumanEntity) sender).damage(((HumanEntity) sender).getHealth()+1);
        }
        sender.sendMessage("");
        return true;
    }
}

package me.foshou.plugins.anarchy.core.features.dupe;

import me.foshou.plugins.anarchy.core.Main;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.Llama;
import org.bukkit.entity.Mule;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;

public class DonkeyDupe implements Listener {
    @EventHandler
    public void onKill(EntityDeathEvent event) {
        if (!(Main.config.getBoolean("dupe.donkey.enable"))) return;
        if (!(event.getEntity().getKiller().hasPermission("anarchy.dupe.donkey"))) return;
        if (event.getEntity() instanceof Donkey donkey) {
            for (int i = 0; i < donkey.getInventory().getSize(); i++) {
                donkey.getWorld().dropItem(donkey.getLocation(), Objects.requireNonNull(donkey.getInventory().getItem(i)));
            }
            System.out.println("A player using donkey to duping.");
        } else if (event.getEntity() instanceof Llama llama) {
            for (int i = 0; i < llama.getInventory().getSize(); i++) {
                llama.getWorld().dropItem(llama.getLocation(), Objects.requireNonNull(llama.getInventory().getItem(i)));
            }
        } else if (event.getEntity() instanceof Mule mule) {
            for (int i = 0; i < mule.getInventory().getSize(); i++) {
                mule.getWorld().dropItem(mule.getLocation(), Objects.requireNonNull(mule.getInventory().getItem(i)));
            }
        }
    }
}

package me.foshou.plugins.anarchy.core.features.limits;

import me.foshou.plugins.anarchy.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class IllegalEnchantments implements Listener {
    @EventHandler
    public void onGet(InventoryClickEvent event) {
        if (!(Main.config.getBoolean("limits.IllegalEnchantments.enable"))) return;
        if(event.getWhoClicked().hasPermission("anarchy.limits.bypass")) return;
        if (!Main.config.getBoolean("limits.IllegalEnchantments.DropperBypass")) {
            ItemStack i = event.getCurrentItem();
            i.getEnchantments().forEach(((enchantment, integer) -> {
                if (enchantment.getStartLevel()<integer) {
                    event.getCurrentItem().setType(Material.AIR);
                }
            }));
        } else {
            if (!event.getClickedInventory().getType().equals(InventoryType.DROPPER)) {
                ItemStack i = event.getCurrentItem();
                if (i != null) {
                    i.getEnchantments().forEach(((enchantment, integer) -> {
                        if (enchantment.getStartLevel()<integer) {
                            event.getCurrentItem().setType(Material.AIR);
                        }
                    }));
                }
            }
        }
    }
}

package me.foshou.plugins.anarchy.core.features.dupe;

import me.foshou.plugins.anarchy.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ChickenDupe implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEntityEvent event) {
        if (!(Main.config.getBoolean("dupe.chicken.enable"))) return;
        if (!(event.getPlayer().hasPermission("anarchy.dupe.chicken"))) return;
        if (!(event.getRightClicked().getType().equals(EntityType.CHICKEN) || event.getRightClicked() instanceof Chicken)) return;
        Player player = event.getPlayer();
        Chicken chicken = (Chicken) event.getRightClicked();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(!item.getType().toString().toLowerCase().contains("shulker_box")) return;

        chicken.setCustomName(ChatColor.translateAlternateColorCodes('&', "&6&lNow Duping: ")+Objects.requireNonNull(item.getItemMeta()).getDisplayName());
        Bukkit.getScheduler().runTaskTimer(
                Main.getProvidingPlugin(Main.class),
                new ChickenKitRunnable(chicken, item),
                10L,
                ((Double) Main.config.getDouble("dupe.chicken.period")).longValue()
        ); // package and unpack to cast
    }

    protected class ChickenKitRunnable implements Runnable {

        public static Chicken chicken;
        public static ItemStack item;
        public ChickenKitRunnable(Chicken ikun, ItemStack item) {
            chicken = ikun;
        }

        @Override
        public void run() {
            chicken.getWorld().dropItem(chicken.getLocation(), item);
        }
    }
}

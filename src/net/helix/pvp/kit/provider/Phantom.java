package net.helix.pvp.kit.provider;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.core.util.HelixCooldown;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.TimeUnit;

public class Phantom extends KitHandler {

    @Override
    public void execute(Player player) {
        super.execute(player);

        player.getInventory().setItem(1, new ItemBuilder(Material.BOOK)
                .displayName("§aPhantom")
                .nbt("cancel-drop")
                .nbt("kit-handler", "phantom")
                .toStack()
        );
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!event.hasItem() || !ItemBuilder.has(event.getItem(), "kit-handler", "phantom")) return;

        event.setCancelled(true);

        if (HelixCooldown.inCooldown(event.getPlayer().getName(), "kit-phantom")) {
            event.getPlayer().sendMessage("§cO kit Phantom está em cooldown.");
            return;
        }
        HelixCooldown.create(event.getPlayer().getName(), "kit-phantom", TimeUnit.SECONDS, 25);

        event.getPlayer().setAllowFlight(true);
        event.getPlayer().setFlying(true);

        new BukkitRunnable() {
            int duration = 10;

            @Override
            public void run() {
                duration--;

                if (duration <= 3 && duration >= 1) {
                    event.getPlayer().sendMessage("§cO phantom irá acabar em " + duration + " " + ((duration > 1) ? "segundos" : "segundo"));
                }

                if (duration <= 0) {
                    event.getPlayer().setAllowFlight(false);
                    event.getPlayer().setFlying(false);
                    cancel();
                }
            }
        }.runTaskTimer(HelixPvP.getInstance(), 0, 20L);


        event.getPlayer().sendMessage("§aPhantom ativado!");
    }
}
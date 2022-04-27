package net.helix.pvp.kit.provider;

import net.helix.core.util.HelixCooldown;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.concurrent.TimeUnit;

public class Hulk extends KitHandler {

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        if (!(event.getRightClicked() instanceof Player)) return;

        Player rightClicked = (Player) event.getRightClicked();
        if (!KitManager.getPlayer(rightClicked.getName()).hasKit()) return;
        if (!KitManager.getPlayer(event.getPlayer().getName()).hasKit(HelixKit.HULK)) return;
        if (HelixCooldown.inCooldown(event.getPlayer().getName(), "kit-hulk")) {
            event.getPlayer().sendMessage("§cO Kit Hulk esta em cooldown!");
            return;
        }

        HelixCooldown.create(event.getPlayer().getName(), "kit-hulk", TimeUnit.SECONDS, 5);
        event.getPlayer().setPassenger(rightClicked);
    }

    @EventHandler (ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player) ||
                !(event.getDamager() instanceof Player)) return;

        Player entity = (Player) event.getEntity();
        Player damager = (Player) event.getDamager();

        if (!KitManager.getPlayer(damager.getName()).hasKit(HelixKit.HULK)) return;
        Entity passenger = damager.getPassenger();

        if (passenger != null && passenger == entity) {
            passenger.leaveVehicle();
            passenger.setVelocity(passenger.getLocation().getDirection().setY(1.6f).multiply(1.0f));
        }
    }
}
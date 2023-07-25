package net.helix.pvp.kit.provider;


import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Turtle extends KitHandler {

    @EventHandler (ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player) ||
        !(event.getDamager() instanceof Player)) return;

        Player damager = (Player) event.getDamager();
        if (!KitManager.getPlayer(damager.getName()).hasKit(HelixKit.TURTLE) ||
            !damager.isSneaking()) return;

        event.setDamage(event.getDamage() - 3.0);

    }
}
package net.helixpvp.kit2;


import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitHandler2;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;

public class Anchor extends KitHandler2 {
	private ArrayList<UUID> anchoring;
	public Anchor() {
        this.anchoring = new ArrayList<UUID>();
    }
    
	@EventHandler
    public void onVelocity(final PlayerVelocityEvent event) {
        if (this.anchoring.contains(event.getPlayer().getUniqueId())) {
            event.setVelocity(new Vector());
            this.anchoring.remove(event.getPlayer().getUniqueId());
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onAnchor(final EntityDamageByEntityEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (!(event.getEntity() instanceof Player) || !(event.getDamager() instanceof Player)) {
            return;
        }
        final Player player = (Player)event.getEntity();
        final Player damager = (Player)event.getDamager();
        if (KitManager2.getPlayer(player.getName()).haskit2(this) || KitManager2.getPlayer(damager.getName()).haskit2(this)) {
            player.setVelocity(new Vector());
            this.anchoring.add(player.getUniqueId());
            player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1f, 1f);
            Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), () -> player.setVelocity(new Vector()));
        }
	}
}

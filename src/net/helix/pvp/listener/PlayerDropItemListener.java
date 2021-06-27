package net.helix.pvp.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.pvp.HelixPvP;

public class PlayerDropItemListener implements Listener {
	
	@EventHandler(ignoreCancelled = true)
	public void onDropItem(PlayerDropItemEvent event) {
		HelixBukkit.getExecutorService().submit(() -> {
			new BukkitRunnable() {
				@Override
				public void run() {
					event.getItemDrop().remove();
				}
			}.runTaskLater(HelixPvP.getInstance(), 10 * 20L);
		});
	}

}

package net.helix.pvp.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.helix.pvp.kit.KitManager;
import net.helix.pvp.warp.HelixWarp;

public class PlayerQuitListener implements Listener {
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		HelixWarp.removeHandle(player.getName());
		KitManager.getPlayer(player.getName()).removeKit();
	}

}

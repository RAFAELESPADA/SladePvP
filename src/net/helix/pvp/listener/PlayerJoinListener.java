package net.helix.pvp.listener;

import net.helix.pvp.warp.HelixWarp;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import net.helix.pvp.HelixPvP;

public class PlayerJoinListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		
		HelixPvP.getInstance().getScoreboardBuilder().build(player);
		HelixWarp.SPAWN.send(player, true);
		player.setFlying(false);
		player.setGameMode(GameMode.SURVIVAL);
	}
}

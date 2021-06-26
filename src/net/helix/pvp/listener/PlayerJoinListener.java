package net.helix.pvp.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.util.SpawnUtil;

public class PlayerJoinListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		
		HelixPvP.getInstance().getScoreboardBuilder().build(player);
		SpawnUtil.apply(player);
	}
}

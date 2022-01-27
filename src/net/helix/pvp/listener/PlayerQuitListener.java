package net.helix.pvp.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.helix.pvp.kit.Habilidade;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.provider.GladiatorListener;
import net.helix.pvp.warp.HelixWarp;

public class PlayerQuitListener implements Listener {
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		HelixWarp.removeHandle(player.getName());
		KitManager.remove(player.getName());
		Habilidade.removeAbility(player);
		 if (GladiatorListener.combateGlad.containsKey(player)) {
             final Player winner = GladiatorListener.combateGlad.get(player);
             final Player loser = player;
             GladiatorListener.resetGladiatorListenerByQuit(winner, loser);
             GladiatorListener.combateGlad.remove(winner);
             GladiatorListener.combateGlad.remove(loser);
         }
	}

}

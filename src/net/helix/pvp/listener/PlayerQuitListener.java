package net.helix.pvp.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import  net.helix.pvp.FakeAPI;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.pvp.command.Fake;
import net.helix.pvp.command.RDMAutomatic;
import net.helix.pvp.kit.Habilidade;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.provider.GladiatorListener;
import net.helix.pvp.warp.HelixWarp;

public class PlayerQuitListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		 

		 FakeAPI.applyFake(PlayerJoinListener.playerrealname.get(player), player);
		 Fake.playerfakename.remove(player);
		 Scoreboard s = player.getScoreboard();
		  if (player.getScoreboard().getObjective("pvp") == null && player.getScoreboard().getObjective("pvp2") == null && player.getScoreboard().getObjective("pvpg") == null && player.getScoreboard().getObjective("pvppt") == null && player.getScoreboard().getObjective("pvp3") == null && player.getScoreboard().getObjective("pvp4") == null && player.getScoreboard().getObjective("pvp5") == null && player.getScoreboard().getObjective("pvp6") == null  && player.getScoreboard().getObjective("pvp7") == null) {
				return;
			}
		  if (s.getTeam("nhide") == null) {
	      Team t = s.registerNewTeam("nhide");
		  
	      t.setNameTagVisibility(NameTagVisibility.NEVER);
	      if (t.hasEntry(player.getName())) {
	      t.removeEntry(player.getName());
	      }
		  }
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
		 if (RDMAutomatic.playersIN.contains(player.getName())) {
				RDMAutomatic.playersIN.remove(player.getName());
			}
		 else if (net.helixpvp.kit2.GladiatorListener.combateGlad.containsKey(player)) {
             final Player winner = GladiatorListener.combateGlad.get(player);
             Bukkit.broadcast(player.getName() + " deslogou no gladiator secundário!" , "kombo.cmd.report");
             for (Player p : Bukkit.getOnlinePlayers()) {
            	 if (net.helixpvp.kit2.GladiatorListener.combateGlad.containsKey(p)) {
            		 p.damage(p.getHealth()); 
            	 }
            	 
             }
         }
	}

}

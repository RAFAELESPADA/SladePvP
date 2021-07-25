package net.helix.pvp.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.pvp.PlayerPvP;
import net.helix.core.bukkit.format.HelixDecimalFormat;
import net.helix.pvp.HelixPvP;

public class ScoreboardBuilder {

	public ScoreboardBuilder(HelixPvP plugin) {
		new ScoreboardTask(this).runTaskTimer(plugin, 0, 3 * 20L);
	}
	
	public void build(Player player) {
		player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective objective = scoreboard.registerNewObjective("pvp", "dummy");
		
		objective.setDisplayName("§r  §b§lPVP");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		String l7 = "§2";
		String l6 = "§fKills: §7";
		String l5 = "§fDeaths: §7";
		String l4 = "§1";
		String l3 = "§fKillstreak: §b";
		String l2 = "§fCoins: §6";
		String l1 = "§0";
		String l0 = "§awww.redecentury.com";
		
		scoreboard.registerNewTeam("kills").addEntry(l6);
		scoreboard.registerNewTeam("deaths").addEntry(l5);
		scoreboard.registerNewTeam("killstreak").addEntry(l3);
		scoreboard.registerNewTeam("coins").addEntry(l2);
		
		objective.getScore(l7).setScore(7);
		objective.getScore(l6).setScore(6);
		objective.getScore(l5).setScore(5);
		objective.getScore(l4).setScore(4);
		objective.getScore(l3).setScore(3);
		objective.getScore(l2).setScore(2);
		objective.getScore(l1).setScore(1);
		objective.getScore(l0).setScore(0);
		
		player.setScoreboard(scoreboard);
		update(player);
	}
	
	public void update(Player player) {
		if (player.getScoreboard().getObjective("pvp") == null) {
			return;
		}
		PlayerPvP pvp = HelixBukkit.getInstance().getPlayerManager()
				.getPlayer(player.getName()).getPvp();
		Scoreboard scoreboard = player.getScoreboard();
		
		scoreboard.getTeam("kills").setSuffix(HelixDecimalFormat.format(pvp.getKills()));
		scoreboard.getTeam("deaths").setSuffix(HelixDecimalFormat.format(pvp.getDeaths()));
		scoreboard.getTeam("killstreak").setSuffix(HelixDecimalFormat.format(pvp.getKillstreak()));
		scoreboard.getTeam("coins").setSuffix(HelixDecimalFormat.format(pvp.getCoins()));
	}
}

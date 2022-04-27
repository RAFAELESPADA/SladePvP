package net.helix.pvp.scoreboard;

import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.account.provider.PlayerPvP;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.format.HelixDecimalFormat;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.listener.Ranking;

public class ScoreboardBuilder {

	public ScoreboardBuilder(HelixPvP plugin) {
		new ScoreboardTask(this).runTaskTimer(plugin, 0, 3 * 20L);
	}
	
	public void build(Player player) {
		player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective objective = scoreboard.registerNewObjective("pvp", "dummy");
		
		objective.setDisplayName("§r  §5§lSLOPER");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		String l10 = "§3";
		String l9 = "§f Grupo: ";
		String l8 = "§2";
		String l7 = "§a ▐ §fKills: §a";
		String l6 = "§c ▐ §fDeaths: §c";
		String l5 = "§3 ▐ §fStreak: §3";
		String l4 = "§1";
		String l3 = "§f Coins: §2";
		String l2 = "§f Rank: §b";
		String l1 = "§0";
		String l0 = "§7slopermc.com";
		
		scoreboard.registerNewTeam("groupPrefix").addEntry(l9);
		scoreboard.registerNewTeam("kills").addEntry(l7);
		scoreboard.registerNewTeam("deaths").addEntry(l6);
		scoreboard.registerNewTeam("killstreak").addEntry(l5);
		scoreboard.registerNewTeam("coins").addEntry(l3);
		scoreboard.registerNewTeam("rank").addEntry(l2);
		objective.getScore(l10).setScore(10);
		objective.getScore(l9).setScore(9);
		objective.getScore(l8).setScore(8);
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
		HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
				.getPlayer(player.getName());
		PlayerPvP pvp = helixPlayer.getPvp();
		Scoreboard scoreboard = player.getScoreboard();
		
		scoreboard.getTeam("groupPrefix").setSuffix(helixPlayer.getRole().getNameColor());
		scoreboard.getTeam("kills").setSuffix(HelixDecimalFormat.format(pvp.getKills()));
		scoreboard.getTeam("deaths").setSuffix(HelixDecimalFormat.format(pvp.getDeaths()));
		scoreboard.getTeam("killstreak").setSuffix(HelixDecimalFormat.format(pvp.getKillstreak()));
		scoreboard.getTeam("coins").setSuffix(HelixDecimalFormat.format(pvp.getCoins()));
		scoreboard.getTeam("rank").setSuffix((String.valueOf(Ranking.getRank(helixPlayer).getColoredName())));
	}
}

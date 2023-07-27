package net.helix.pvp.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.account.provider.PlayerPvP;
import net.helix.core.bukkit.format.HelixDecimalFormat;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.helix.pvp.listener.Ranking;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

public class ScoreboardBuilder {

	public ScoreboardBuilder(HelixPvP plugin) {
		new ScoreboardTask(this).runTaskTimer(plugin, 0, 3 * 20L);
	}
private static String text = "";
private static WaveAnimation waveAnimation;
public static void init() {
    waveAnimation = new WaveAnimation("KITPVP" , "§6§l" , "§f§l" , "§e§l");
    text = "KITPVP";
    Bukkit.getScheduler().runTaskTimer(HelixPvP.getInstance(), new Runnable() {
          public void run() {
            ScoreboardBuilder.text = ScoreboardBuilder.waveAnimation.next();
            for (Player onlines : Bukkit.getOnlinePlayers()) {
              if (onlines == null)
                continue; 
              if (!onlines.isOnline())
                continue; 
              if (onlines.isDead())
                continue; 
              Scoreboard score = onlines.getScoreboard();
              if (score == null)
                continue; 
              Objective objective = score.getObjective(DisplaySlot.SIDEBAR);
              if (objective == null)
                continue; 
              objective.setDisplayName(ScoreboardBuilder.text);
            } 
          }
        },  20L, 1L);
  }
	public void build(Player player) {
		 {
		player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective objective = scoreboard.registerNewObjective("pvp", "dummy");
		
		objective.setDisplayName("KITPVP");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		String l12 = "§3";
		String l11 = "§a❚ §fKills: §a";
		String l10 = "§c❚ §fDeaths: §c";
		String l9 = "§3❚ §fKillstreak: §3";
		String l8 = "§2";
		String l7 = "§fKit 1: §a";
		String l6 = "§fKit 2: §a";
	
		String l5 = "§1";
		String l4 = "§fCoins: §a";
		String l3 = "§fLiga: ";
		String l2 = "§0";
		String l1 = HelixPvP.getInstance().getConfig().getString("IPScore").replace("&", "§");
		
		scoreboard.registerNewTeam("kills").addEntry(l11);
		scoreboard.registerNewTeam("deaths").addEntry(l10);
		scoreboard.registerNewTeam("killstreak").addEntry(l9);
		scoreboard.registerNewTeam("kit").addEntry(l7);
		scoreboard.registerNewTeam("coins").addEntry(l4);
		scoreboard.registerNewTeam("rank").addEntry(l3);
		scoreboard.registerNewTeam("kit2").addEntry(l6);
		objective.getScore(l12).setScore(11);
		objective.getScore(l11).setScore(10);
		objective.getScore(l10).setScore(9);
		objective.getScore(l9).setScore(8);
		objective.getScore(l8).setScore(7);
		objective.getScore(l7).setScore(6);
		objective.getScore(l6).setScore(5);
		objective.getScore(l5).setScore(4);
		objective.getScore(l4).setScore(3);
		objective.getScore(l3).setScore(2);
		objective.getScore(l2).setScore(1);
		objective.getScore(l1).setScore(0);
		
		player.setScoreboard(scoreboard);
		update(player);
		}
	}
	
	public void update(Player player) {
		
		if (player.getScoreboard().getObjective("pvp") == null && player.getScoreboard().getObjective("pvp2") == null) {
			return;
		}
		HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
				.getPlayer(player.getName());
		PlayerPvP pvp = helixPlayer.getPvp();
		Scoreboard scoreboard = player.getScoreboard();
		 LuckPerms api = LuckPermsProvider.get();
		scoreboard.getTeam("kills").setSuffix(HelixDecimalFormat.format(pvp.getKills()));
		scoreboard.getTeam("deaths").setSuffix(HelixDecimalFormat.format(pvp.getDeaths()));
		scoreboard.getTeam("killstreak").setSuffix(HelixDecimalFormat.format(pvp.getKillstreak()));
		scoreboard.getTeam("coins").setSuffix(HelixDecimalFormat.format(pvp.getCoins()));
		scoreboard.getTeam("kit2").setSuffix(KitManager2.getPlayer(player.getName()).getkit2().getName());
		scoreboard.getTeam("kit").setSuffix(KitManager.getPlayer(player.getName()).getKit().getName());
		scoreboard.getTeam("rank").setSuffix((String.valueOf(Ranking.getRank(helixPlayer).getColoredName())));
	}
}

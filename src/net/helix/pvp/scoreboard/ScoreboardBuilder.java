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
import net.helix.pvp.evento.EventoUtils;
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
private static String text2 = "";
private static WaveAnimation waveAnimation;
private static WaveAnimation waveAnimation2;
public static void init() {
	
    waveAnimation = new WaveAnimation("KITPVP" , "§6§l" , "§f§l" , "§e§l");
    waveAnimation2 = new WaveAnimation("EVENTO" , "§b§l" , "§3§l" , "§f§l");
    text = "KITPVP";
    text2 = "EVENTO";
    Bukkit.getScheduler().runTaskTimer(HelixPvP.getInstance(), new Runnable() {
          public void run() {
            ScoreboardBuilder.text = ScoreboardBuilder.waveAnimation.next();
            ScoreboardBuilder.text2 = ScoreboardBuilder.waveAnimation2.next();
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
         
            	  if (EventoUtils.game.contains(onlines.getName())) {
            	  objective.setDisplayName(ScoreboardBuilder.text2); 
              }
             
            	  
              }
            } 
          
        },  20L, 1L);
  }
	public void build(Player player) {
		 {
			 
			 if (!EventoUtils.game.contains(player.getName())) {
					player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
					Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
					Objective objective = scoreboard.registerNewObjective("pvp", "dummy");
					
					objective.setDisplayName(text);
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
					return;
			 } else {
					player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
				 Scoreboard scoreboard2 = Bukkit.getScoreboardManager().getNewScoreboard();
					Objective objective2 = scoreboard2.registerNewObjective("pvp2", "dummy2");
					
					objective2.setDisplayName(text2);
					objective2.setDisplaySlot(DisplaySlot.SIDEBAR);
					
					String l8 = "§bVocê está no Evento";
					String ll = "§c";
					String l7 = "§fSala Aberta: ";
					String l6 = "§fPvP: ";
					String l5 = "§fDano: ";
					String l4 = "§fCoins: §a";
					String l3 = "§fLiga: ";
					String l2 = "§0";
					String l1 = HelixPvP.getInstance().getConfig().getString("IPScore").replace("&", "§"); 
					objective2.getScore(l8).setScore(8);
					scoreboard2.registerNewTeam("coins").addEntry(l4);
					scoreboard2.registerNewTeam("sala").addEntry(l7);
					scoreboard2.registerNewTeam("pvp").addEntry(l6);
					scoreboard2.registerNewTeam("dano").addEntry(l5);
					scoreboard2.registerNewTeam("rank").addEntry(l3);
					objective2.getScore(ll).setScore(7);
					objective2.getScore(l7).setScore(6);
					objective2.getScore(l6).setScore(5);
					objective2.getScore(l5).setScore(4);
					objective2.getScore(l4).setScore(3);
					objective2.getScore(l3).setScore(2);
					objective2.getScore(l2).setScore(1);
					objective2.getScore(l1).setScore(0);
					player.setScoreboard(scoreboard2);
					update(player);
				return;
			 }
			 
		
		
		 }
	}
	
	public void update(Player player) {
		
		if (player.getScoreboard().getObjective("pvp") == null && player.getScoreboard().getObjective("pvp2") == null) {
			return;
		}
		 if (EventoUtils.game.contains(player.getName())) {
			 HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
						.getPlayer(player.getName());
				PlayerPvP pvp = helixPlayer.getPvp();
				Scoreboard scoreboard = player.getScoreboard();
			scoreboard.getTeam("coins").setSuffix((String.valueOf(HelixDecimalFormat.format(pvp.getCoins()))));
			scoreboard.getTeam("dano").setSuffix((String.valueOf(EventoUtils.damage ? "§aAtivado" : "§cDesativado")));
			scoreboard.getTeam("sala").setSuffix((String.valueOf((EventoUtils.tp ? "§aSim" : "§cNão"))));
			scoreboard.getTeam("pvp").setSuffix((String.valueOf((EventoUtils.pvp ? "§aAtivado" : "§cDesativado"))));
			scoreboard.getTeam("rank").setSuffix((String.valueOf(Ranking.getRank(helixPlayer).getColoredName())));
		
		 } else {
			 HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
						.getPlayer(player.getName());
				PlayerPvP pvp = helixPlayer.getPvp();
				Scoreboard scoreboard = player.getScoreboard();
				 LuckPerms api = LuckPermsProvider.get();
				scoreboard.getTeam("kills").setSuffix(HelixDecimalFormat.format(pvp.getKills()));
				scoreboard.getTeam("deaths").setSuffix(HelixDecimalFormat.format(pvp.getDeaths()));
				scoreboard.getTeam("killstreak").setSuffix(HelixDecimalFormat.format(pvp.getKillstreak()));
				scoreboard.getTeam("coins").setSuffix(HelixDecimalFormat.format(pvp.getCoins()));
				scoreboard.getTeam("kit2").setSuffix(KitManager2.getPlayer(player.getName()).getkit2().toString());
				scoreboard.getTeam("kit").setSuffix(KitManager.getPlayer(player.getName()).getKit().getName());
				scoreboard.getTeam("rank").setSuffix((String.valueOf(Ranking.getRank(helixPlayer).getColoredName())));
		}
	}
	}


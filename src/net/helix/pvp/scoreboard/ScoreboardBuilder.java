package net.helix.pvp.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
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
import net.helix.pvp.kit.HelixKit2;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.helix.pvp.kit.provider.GladiatorListener;
import net.helix.pvp.listener.Ranking;
import net.helix.pvp.warp.HelixWarp;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

public class ScoreboardBuilder {

	public ScoreboardBuilder(HelixPvP plugin) {
		new ScoreboardTask(this).runTaskTimer(plugin, 0, 3 * 20L);
	}
private static String text = "";
private static String text2 = "";
private static String text3 = "";
private static String text4 = "";
private static String text5 = "";
private static String text6 = "";
private static String text7 = "";
private static String text9 = "";
private static String textg = "";
private static WaveAnimation waveAnimation;
private static WaveAnimation waveAnimation2;
private static WaveAnimation waveAnimation3;
private static WaveAnimation waveAnimation4;
private static WaveAnimation waveAnimation5;
private static WaveAnimation waveAnimation6;
private static WaveAnimation waveAnimation7;
private static WaveAnimation waveAnimation8;
private static WaveAnimation waveAnimation9;
public static void init() {
	
    waveAnimation = new WaveAnimation("KITPVP" , "§6§l" , "§f§l" , "§e§l");
    waveAnimation2 = new WaveAnimation("EVENTO" , "§b§l" , "§3§l" , "§f§l");
    waveAnimation3 = new WaveAnimation("FPS" , "§6§l" , "§f§l" , "§e§l");
    waveAnimation4 = new WaveAnimation("1V1" , "§6§l" , "§f§l" , "§e§l");
    waveAnimation5 = new WaveAnimation("LAVA" , "§6§l" , "§f§l" , "§e§l");
    waveAnimation6 = new WaveAnimation("SUMO" , "§6§l" , "§f§l" , "§e§l");
    waveAnimation7 = new WaveAnimation("KNOCKBACK" , "§3§l" , "§f§l" , "§b§l");
    waveAnimation8 = new WaveAnimation("GLADIATOR" , "§3§l" , "§f§l" , "§b§l");
    waveAnimation9 = new WaveAnimation("POTIONPVP" , "§4§l" , "§f§l" , "§c§l");
    text = "KITPVP";
    text2 = "EVENTO";
    text3 = "FPS";
    text6 = "SUMO";
    text7 = "KNOCKBACK";
    text9 = "POTIONPVP";
    textg = "GLADIATOR";
    text5 = "LAVA";
    text4 = "1V1";
    Bukkit.getScheduler().runTaskTimer(HelixPvP.getInstance(), new Runnable() {
          public void run() {
            ScoreboardBuilder.text = ScoreboardBuilder.waveAnimation.next();
            ScoreboardBuilder.text2 = ScoreboardBuilder.waveAnimation2.next();
            ScoreboardBuilder.text3 = ScoreboardBuilder.waveAnimation3.next();
            ScoreboardBuilder.text4 = ScoreboardBuilder.waveAnimation4.next();
            ScoreboardBuilder.text5 = ScoreboardBuilder.waveAnimation5.next();
            ScoreboardBuilder.text6 = ScoreboardBuilder.waveAnimation6.next();
            ScoreboardBuilder.text7 = ScoreboardBuilder.waveAnimation7.next();
            ScoreboardBuilder.textg = ScoreboardBuilder.waveAnimation8.next();
            ScoreboardBuilder.text9 = ScoreboardBuilder.waveAnimation9.next();
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
              if (HelixWarp.SPAWN.hasPlayer(onlines.getName()) && !EventoUtils.game.contains(onlines.getName())) {
              objective.setDisplayName(ScoreboardBuilder.text);
              }
              else if (HelixWarp.FPS.hasPlayer(onlines.getName())) {
                  objective.setDisplayName(ScoreboardBuilder.text3);
                  }
              else if (HelixWarp.ONE_VS_ONE.hasPlayer(onlines.getName())) {
                  objective.setDisplayName(ScoreboardBuilder.text4);
                  }
              else if (HelixWarp.LAVACHALLENGE.hasPlayer(onlines.getName())) {
                  objective.setDisplayName(ScoreboardBuilder.text5);
                  }
              else if (HelixWarp.SUMO.hasPlayer(onlines.getName())) {
                  objective.setDisplayName(ScoreboardBuilder.text6);
                  }
              else if (HelixWarp.GLADIATOR.hasPlayer(onlines.getName())) {
                  objective.setDisplayName(ScoreboardBuilder.textg);
                  }
              else if (HelixWarp.KNOCKBACK.hasPlayer(onlines.getName())) {
                  objective.setDisplayName(ScoreboardBuilder.text7);
                  }
              else if (HelixWarp.POTPVP.hasPlayer(onlines.getName())) {
                  objective.setDisplayName(ScoreboardBuilder.text9);
                  }
              else  if (EventoUtils.game.contains(onlines.getName()) && HelixWarp.SPAWN.hasPlayer(onlines.getName())) {
            	  objective.setDisplayName(ScoreboardBuilder.text2); 
              }
             
            	  
              }
            } 
          
        },  20L, 1L);
  }
	public void build(Player player) {
		 {
			 
			 if (HelixWarp.SPAWN.hasPlayer(player.getName()) && !EventoUtils.game.contains(player.getName())) {
				 player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
					Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
					Objective objective = scoreboard.registerNewObjective("pvp", "dummy");
					
					objective.setDisplayName(text);
					objective.setDisplaySlot(DisplaySlot.SIDEBAR);
					
					String l12 = "§3";
					String l11 = "§a❚ §fKills: §a";
					String l10 = "§c❚ §fDeaths: §c";
					String l9 = "§3❚ §fKillstreak: §3";
					
				
					String l5 = "§1";
					String l4 = "§fCoins: §a";
					String l3 = "§fRank: ";
					String lx = "§fXP: §a";
					String l2 = "§0";
					String l1 = HelixPvP.getInstance().getConfig().getString("IPScore").replace("&", "§");
					
					scoreboard.registerNewTeam("kills").addEntry(l11);
					scoreboard.registerNewTeam("deaths").addEntry(l10);
					scoreboard.registerNewTeam("killstreak").addEntry(l9);
				//	if (KitManager.getPlayer(player.getName()).hasKit()) {
		
					//}
					scoreboard.registerNewTeam("xp").addEntry(lx);
					scoreboard.registerNewTeam("coins").addEntry(l4);
					scoreboard.registerNewTeam("rank").addEntry(l3);
					objective.getScore(l12).setScore(12);
					objective.getScore(l11).setScore(12);
					objective.getScore(l10).setScore(10);
					objective.getScore(l9).setScore(9);
					
					objective.getScore(l5).setScore(5);
					objective.getScore(l4).setScore(4);
					objective.getScore(l3).setScore(3);
					objective.getScore(lx).setScore(2);
					objective.getScore(l2).setScore(1);
					objective.getScore(l1).setScore(0);
					
					player.setScoreboard(scoreboard);
					update(player);
					
			 } else if (EventoUtils.game.contains(player.getName()) && HelixWarp.SPAWN.hasPlayer(player.getName())) {
					player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
				 Scoreboard scoreboard2 = Bukkit.getScoreboardManager().getNewScoreboard();
					Objective objective2 = scoreboard2.registerNewObjective("pvp2", "dummy2");
					
					objective2.setDisplayName(text2);
					objective2.setDisplaySlot(DisplaySlot.SIDEBAR);
					String l9 = "§a";
					String l8 = "§bYou are in the event";
					String ll = "§c";
					String l7 = "§fRoom open: ";
					String l6 = "§fPvP: ";
					String l5 = "§fDamage: ";
					String l4 = "§fCoins: §a";
					String l3 = "§fRank: ";
					String l2 = "§0";
					String l1 = HelixPvP.getInstance().getConfig().getString("IPScore").replace("&", "§");
					scoreboard2.registerNewTeam("coins").addEntry(l4);
					scoreboard2.registerNewTeam("sala").addEntry(l7);
					scoreboard2.registerNewTeam("pvp").addEntry(l6);
					scoreboard2.registerNewTeam("dano").addEntry(l5);
					scoreboard2.registerNewTeam("rank").addEntry(l3);
					objective2.getScore(l9).setScore(9);
					objective2.getScore(l8).setScore(8);
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
				
			 }
			 else if (HelixWarp.FPS.hasPlayer(player.getName())) {
					player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
				 Scoreboard scoreboard2 = Bukkit.getScoreboardManager().getNewScoreboard();
					Objective objective2 = scoreboard2.registerNewObjective("pvp3", "dummy3");
					
					objective2.setDisplayName(text3);
					objective2.setDisplaySlot(DisplaySlot.SIDEBAR);
					
					String l10 = "§eStats of FPS";
					String l9 = "§c";
					String l8 = "§fKills: §a";
					String l7 = "§fDeaths: §c";
					String l6 = "§fKillStreak: §3";
					String l5 = "§fCoins: §a";
					String l4 = "§fRank: ";
					String l3 = "§fXP: §a";
					String l2 = "§0";
					String l1 = HelixPvP.getInstance().getConfig().getString("IPScore").replace("&", "§"); 
					objective2.getScore(l8).setScore(8);
					scoreboard2.registerNewTeam("killfps").addEntry(l8);
					scoreboard2.registerNewTeam("deathfps").addEntry(l7);
					scoreboard2.registerNewTeam("ks").addEntry(l6);
					scoreboard2.registerNewTeam("coins").addEntry(l5);
					scoreboard2.registerNewTeam("rank").addEntry(l4);
					scoreboard2.registerNewTeam("xp").addEntry(l3);
					objective2.getScore(l10).setScore(9);
					objective2.getScore(l9).setScore(8);
					objective2.getScore(l8).setScore(7);
					objective2.getScore(l7).setScore(6);
					objective2.getScore(l6).setScore(5);
					objective2.getScore(l5).setScore(4);
					objective2.getScore(l4).setScore(3);
					objective2.getScore(l3).setScore(2);
					objective2.getScore(l2).setScore(1);
					objective2.getScore(l1).setScore(0);
					player.setScoreboard(scoreboard2);
					update(player);
				
			 }
			 else if (HelixWarp.GLADIATOR.hasPlayer(player.getName())) {
					player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
				 Scoreboard scoreboard2 = Bukkit.getScoreboardManager().getNewScoreboard();
					Objective objective2 = scoreboard2.registerNewObjective("pvpg", "dummyg");
					
					objective2.setDisplayName(textg);
					objective2.setDisplaySlot(DisplaySlot.SIDEBAR);
					
					String l10 = "§eGladiator";
					String l9 = "§c";
					String l8 = "§fOponnent:";
					String l7 = "§fPing:";
					String l5 = "§fCoins: §a";
					String l4 = "§fRank: ";
					String l3 = "§fXP: §a";
					String l2 = "§0";
					String l1 = HelixPvP.getInstance().getConfig().getString("IPScore").replace("&", "§"); 
					objective2.getScore(l8).setScore(8);
					scoreboard2.registerNewTeam("oponente").addEntry(l8);
					scoreboard2.registerNewTeam("ping").addEntry(l7);
					scoreboard2.registerNewTeam("coins").addEntry(l5);
					scoreboard2.registerNewTeam("rank").addEntry(l4);
					scoreboard2.registerNewTeam("xp").addEntry(l3);
					objective2.getScore(l10).setScore(8);
					objective2.getScore(l9).setScore(7);
					objective2.getScore(l8).setScore(6);
					objective2.getScore(l7).setScore(5);
					objective2.getScore(l5).setScore(4);
					objective2.getScore(l4).setScore(3);
					objective2.getScore(l3).setScore(2);
					objective2.getScore(l2).setScore(1);
					objective2.getScore(l1).setScore(0);
					player.setScoreboard(scoreboard2);
					update(player);
				
			 }
			 else if (HelixWarp.KNOCKBACK.hasPlayer(player.getName())) {
					player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
				 Scoreboard scoreboard2 = Bukkit.getScoreboardManager().getNewScoreboard();
					Objective objective2 = scoreboard2.registerNewObjective("pvp7", "dummy7");
					
					objective2.setDisplayName(text7);
					objective2.setDisplaySlot(DisplaySlot.SIDEBAR);
					
					String l10 = "§eWarp knockback";
					String l9 = "§c";
					String l8 = "§fKills: §a";
					String l7 = "§fDeaths: §c";
					String l6 = "§fKillStreak: §3";
					String l5 = "§fCoins: §a";
					String l4 = "§fRank: ";
					String l3 = "§fXP: §a";
					String l2 = "§0";
					String l1 = HelixPvP.getInstance().getConfig().getString("IPScore").replace("&", "§"); 
					scoreboard2.registerNewTeam("kills").addEntry(l8);
					scoreboard2.registerNewTeam("deaths").addEntry(l7);
					scoreboard2.registerNewTeam("killstreak").addEntry(l6);
					scoreboard2.registerNewTeam("coins").addEntry(l5);
					scoreboard2.registerNewTeam("rank").addEntry(l4);
					scoreboard2.registerNewTeam("xp").addEntry(l3);
					objective2.getScore(l10).setScore(9);
					objective2.getScore(l9).setScore(8);
					objective2.getScore(l8).setScore(7);
					objective2.getScore(l7).setScore(6);
					objective2.getScore(l6).setScore(5);
					objective2.getScore(l5).setScore(4);
					objective2.getScore(l4).setScore(3);
					objective2.getScore(l3).setScore(2);
					objective2.getScore(l2).setScore(1);
					objective2.getScore(l1).setScore(0);
					player.setScoreboard(scoreboard2);
					update(player);
				
			 }
			 else if (HelixWarp.POTPVP.hasPlayer(player.getName())) {
					player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
				 Scoreboard scoreboard2 = Bukkit.getScoreboardManager().getNewScoreboard();
					Objective objective2 = scoreboard2.registerNewObjective("pvppt", "dummypt");
					
					objective2.setDisplayName(text9);
					objective2.setDisplaySlot(DisplaySlot.SIDEBAR);
					
					String l10 = "§eWarp potion";
					String l9 = "§c";
					String l8 = "§fKills: §a";
					String l7 = "§fDeaths: §c";
					String l6 = "§fKillStreak: §3";
					String l5 = "§fCoins: §a";
					String l4 = "§fRank: ";
					String l3 = "§fXP: §a";
					String l2 = "§0";
					String l1 = HelixPvP.getInstance().getConfig().getString("IPScore").replace("&", "§"); 
					scoreboard2.registerNewTeam("kills").addEntry(l8);
					scoreboard2.registerNewTeam("deaths").addEntry(l7);
					scoreboard2.registerNewTeam("killstreak").addEntry(l6);
					scoreboard2.registerNewTeam("coins").addEntry(l5);
					scoreboard2.registerNewTeam("rank").addEntry(l4);
					scoreboard2.registerNewTeam("xp").addEntry(l3);
					objective2.getScore(l10).setScore(9);
					objective2.getScore(l9).setScore(8);
					objective2.getScore(l8).setScore(7);
					objective2.getScore(l7).setScore(6);
					objective2.getScore(l6).setScore(5);
					objective2.getScore(l5).setScore(4);
					objective2.getScore(l4).setScore(3);
					objective2.getScore(l3).setScore(2);
					objective2.getScore(l2).setScore(1);
					objective2.getScore(l1).setScore(0);
					player.setScoreboard(scoreboard2);
					update(player);
				
			 }
			 else if (HelixWarp.LAVACHALLENGE.hasPlayer(player.getName())) {
					player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
				 Scoreboard scoreboard2 = Bukkit.getScoreboardManager().getNewScoreboard();
					Objective objective2 = scoreboard2.registerNewObjective("pvp4", "dummy4");
					
					objective2.setDisplayName(text4);
					objective2.setDisplaySlot(DisplaySlot.SIDEBAR);
					
					String l10 = "§eLava Challenge";
					String l9 = "§c";
					String l8 = "§fHealth: §c";
					String l5 = "§fCoins: §a";
					String l4 = "§fRank: ";
					String l3 = "§fXP: §a";
					String l2 = "§0";
					String l1 = HelixPvP.getInstance().getConfig().getString("IPScore").replace("&", "§"); 
					objective2.getScore(l8).setScore(8);
					scoreboard2.registerNewTeam("vida").addEntry(l8);
					scoreboard2.registerNewTeam("coins").addEntry(l5);
					scoreboard2.registerNewTeam("rank").addEntry(l4);
					scoreboard2.registerNewTeam("xp").addEntry(l3);
					objective2.getScore(l10).setScore(7);
					objective2.getScore(l9).setScore(6);
					objective2.getScore(l8).setScore(5);
					objective2.getScore(l5).setScore(4);
					objective2.getScore(l4).setScore(3);
					objective2.getScore(l3).setScore(2);
					objective2.getScore(l2).setScore(1);
					objective2.getScore(l1).setScore(0);
					player.setScoreboard(scoreboard2);
					update(player);
				
			 }
			 else if (HelixWarp.SUMO.hasPlayer(player.getName())) {
					player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
				 Scoreboard scoreboard2 = Bukkit.getScoreboardManager().getNewScoreboard();
					Objective objective2 = scoreboard2.registerNewObjective("pvp5", "dummy5");
					
					objective2.setDisplayName(text5);
					objective2.setDisplaySlot(DisplaySlot.SIDEBAR);
					
					String l9 = "§c";
					String l8 = "§fWins: §a";
					String l5 = "§fLoses: §c";
					String l4 = "§fWinStreak: §3";
					String l3 = "§fXP: §a";
					String l22 = "§fRank: ";
					String l2 = "§0";
					String l1 = HelixPvP.getInstance().getConfig().getString("IPScore").replace("&", "§"); 
					objective2.getScore(l8).setScore(8);
					scoreboard2.registerNewTeam("wins").addEntry(l8);
					scoreboard2.registerNewTeam("loses").addEntry(l5);
					scoreboard2.registerNewTeam("ws").addEntry(l4);
					scoreboard2.registerNewTeam("xp").addEntry(l3);
					scoreboard2.registerNewTeam("rank").addEntry(l22);
					objective2.getScore(l9).setScore(7);
					objective2.getScore(l8).setScore(6);
					objective2.getScore(l5).setScore(5);
					objective2.getScore(l4).setScore(4);
					objective2.getScore(l3).setScore(3);
					objective2.getScore(l22).setScore(2);
					objective2.getScore(l2).setScore(1);
					objective2.getScore(l1).setScore(0);
					player.setScoreboard(scoreboard2);
					update(player);
			
			 }
			 else if (HelixWarp.ONE_VS_ONE.hasPlayer(player.getName())) {
					player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
				 Scoreboard scoreboard2 = Bukkit.getScoreboardManager().getNewScoreboard();
					Objective objective2 = scoreboard2.registerNewObjective("pvp6", "dummy6");
					
					objective2.setDisplayName(text6);
					objective2.setDisplaySlot(DisplaySlot.SIDEBAR);
					
					String l9 = "§c";
					String l8 = "§fWins: §a";
					String l5 = "§fLoses: §c";
					String l4 = "§fWinStreak: §3";
					String l3 = "§fXP: §a";
					String l22 = "§fRank: ";
					String l2 = "§0";
					String l1 = HelixPvP.getInstance().getConfig().getString("IPScore").replace("&", "§"); 
					objective2.getScore(l8).setScore(8);
					scoreboard2.registerNewTeam("wins").addEntry(l8);
					scoreboard2.registerNewTeam("loses").addEntry(l5);
					scoreboard2.registerNewTeam("ws").addEntry(l4);
					scoreboard2.registerNewTeam("xp").addEntry(l3);
					scoreboard2.registerNewTeam("rank").addEntry(l22);
					objective2.getScore(l9).setScore(7);
					objective2.getScore(l8).setScore(6);
					objective2.getScore(l5).setScore(5);
					objective2.getScore(l4).setScore(4);
					objective2.getScore(l3).setScore(3);
					objective2.getScore(l22).setScore(2);
					objective2.getScore(l2).setScore(1);
					objective2.getScore(l1).setScore(0);
					player.setScoreboard(scoreboard2);
					update(player);
				
			 }
			 
		
		
		 }
	}
	
	public void update(Player player) {
		//pvppt
		if (player.getScoreboard().getObjective("pvp") == null && player.getScoreboard().getObjective("pvp2") == null && player.getScoreboard().getObjective("pvpg") == null && player.getScoreboard().getObjective("pvppt") == null && player.getScoreboard().getObjective("pvp3") == null && player.getScoreboard().getObjective("pvp4") == null && player.getScoreboard().getObjective("pvp5") == null && player.getScoreboard().getObjective("pvp6") == null  && player.getScoreboard().getObjective("pvp7") == null) {
			return;
		}
	
			
		  else if (HelixWarp.SPAWN.hasPlayer(player.getName()) && !EventoUtils.game.contains(player.getName())) {
			 HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
						.getPlayer(player.getName());
				PlayerPvP pvp = helixPlayer.getPvp();
				Scoreboard scoreboard = player.getScoreboard();
				String l8 = "§2";
				String l7 = "§fKit 1: §a";
				String l6 = "§fKit 2: §a";
				Objective objective = scoreboard.getObjective("pvp");
				if (KitManager.getPlayer(player.getName()).hasKit()) {
					objective.getScore(l7).setScore(7);
					objective.getScore(l8).setScore(8);
					if (scoreboard.getTeam("kit") == null) {
					scoreboard.registerNewTeam("kit").addEntry(l7);
					}
				}
					if (KitManager2.getPlayer(player.getName()).haskit2() && !KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.PVP)) {
					objective.getScore(l6).setScore(6);
					if (scoreboard.getTeam("kit2") == null) {
					scoreboard.registerNewTeam("kit2").addEntry(l6);
					}
					}
				if (KitManager.getPlayer(player.getName()).hasKit()) {
					scoreboard.getTeam("kit").setSuffix(KitManager.getPlayer(player.getName()).getKit().getName());
				}
				if (KitManager2.getPlayer(player.getName()).haskit2() && !KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.PVP)) {
					scoreboard.getTeam("kit2").setSuffix(KitManager2.getPlayer(player.getName()).getkit2().getName());
				}
				scoreboard.getTeam("kills").setSuffix(HelixDecimalFormat.format(pvp.getKills()));
				scoreboard.getTeam("deaths").setSuffix(HelixDecimalFormat.format(pvp.getDeaths()));
				scoreboard.getTeam("killstreak").setSuffix(HelixDecimalFormat.format(pvp.getKillstreak()));
				scoreboard.getTeam("coins").setSuffix(HelixDecimalFormat.format(pvp.getCoins()));
				
				
				
			
			
				
				scoreboard.getTeam("xp").setSuffix(HelixDecimalFormat.format(pvp.getXp()));
				scoreboard.getTeam("rank").setSuffix((String.valueOf(Ranking.getRank(helixPlayer).getColoredName())));
					}	else if (HelixWarp.FPS.hasPlayer(player.getName())) {
			 
			 HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
						.getPlayer(player.getName());
				PlayerPvP pvp = helixPlayer.getPvp();
				Scoreboard scoreboard = player.getScoreboard();
		
			 scoreboard.getTeam("killfps").setSuffix(HelixDecimalFormat.format(pvp.getKillsfps()));
				scoreboard.getTeam("deathfps").setSuffix(HelixDecimalFormat.format(pvp.getDeathsfps()));
				scoreboard.getTeam("ks").setSuffix(HelixDecimalFormat.format(pvp.getKillstreak()));
				scoreboard.getTeam("coins").setSuffix(HelixDecimalFormat.format(pvp.getCoins()));
				scoreboard.getTeam("xp").setSuffix(HelixDecimalFormat.format(pvp.getXp()));
				scoreboard.getTeam("rank").setSuffix((String.valueOf(Ranking.getRank(helixPlayer).getColoredName())));
				}
else if (HelixWarp.KNOCKBACK.hasPlayer(player.getName())) {
			 
			 HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
						.getPlayer(player.getName());
				PlayerPvP pvp = helixPlayer.getPvp();
				Scoreboard scoreboard = player.getScoreboard();
		
				scoreboard.getTeam("kills").setSuffix(HelixDecimalFormat.format(pvp.getKills()));
				scoreboard.getTeam("deaths").setSuffix(HelixDecimalFormat.format(pvp.getDeaths()));
				scoreboard.getTeam("killstreak").setSuffix(HelixDecimalFormat.format(pvp.getKillstreak()));
				scoreboard.getTeam("coins").setSuffix(HelixDecimalFormat.format(pvp.getCoins()));
				scoreboard.getTeam("xp").setSuffix(HelixDecimalFormat.format(pvp.getXp()));
				scoreboard.getTeam("rank").setSuffix((String.valueOf(Ranking.getRank(helixPlayer).getColoredName())));
				}
else if (HelixWarp.POTPVP.hasPlayer(player.getName())) {
	 
	 HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
				.getPlayer(player.getName());
		PlayerPvP pvp = helixPlayer.getPvp();
		Scoreboard scoreboard = player.getScoreboard();

		scoreboard.getTeam("kills").setSuffix(HelixDecimalFormat.format(pvp.getKills()));
		scoreboard.getTeam("deaths").setSuffix(HelixDecimalFormat.format(pvp.getDeaths()));
		scoreboard.getTeam("killstreak").setSuffix(HelixDecimalFormat.format(pvp.getKillstreak()));
		scoreboard.getTeam("coins").setSuffix(HelixDecimalFormat.format(pvp.getCoins()));
		scoreboard.getTeam("xp").setSuffix(HelixDecimalFormat.format(pvp.getXp()));
		scoreboard.getTeam("rank").setSuffix((String.valueOf(Ranking.getRank(helixPlayer).getColoredName())));
		}
else if (HelixWarp.GLADIATOR.hasPlayer(player.getName())) {
	 
	 HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
				.getPlayer(player.getName());
		PlayerPvP pvp = helixPlayer.getPvp();
		Scoreboard scoreboard = player.getScoreboard();

		final Player winner = GladiatorListener.combateGlad.get(player);
		String srt = winner == null ? " §7None" : "§b " + winner.getName();
		if(srt.length() > 13) {
		   srt = srt.substring(0,12);
		} 
		scoreboard.getTeam("oponente").setSuffix(srt);
		scoreboard.getTeam("ping").setSuffix(" §a" + ((CraftPlayer) player).getHandle().ping + " ms");
		scoreboard.getTeam("coins").setSuffix(HelixDecimalFormat.format(pvp.getCoins()));
		scoreboard.getTeam("xp").setSuffix(HelixDecimalFormat.format(pvp.getXp()));
		scoreboard.getTeam("rank").setSuffix((String.valueOf(Ranking.getRank(helixPlayer).getColoredName())));
		}
	 	else if (HelixWarp.LAVACHALLENGE.hasPlayer(player.getName())) {
	 		HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
					.getPlayer(player.getName());
			PlayerPvP pvp = helixPlayer.getPvp();
			Scoreboard scoreboard = player.getScoreboard();
	 		scoreboard.getTeam("vida").setSuffix(HelixDecimalFormat.format(player.getHealth()/2) + " §4\u2764");
			scoreboard.getTeam("coins").setSuffix(HelixDecimalFormat.format(pvp.getCoins()));
			scoreboard.getTeam("xp").setSuffix(HelixDecimalFormat.format(pvp.getXp()));
			scoreboard.getTeam("rank").setSuffix((String.valueOf(Ranking.getRank(helixPlayer).getColoredName())));
	 	} 
		 else if (HelixWarp.SUMO.hasPlayer(player.getName())) {
				HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
						.getPlayer(player.getName());
				PlayerPvP pvp = helixPlayer.getPvp();
				Scoreboard scoreboard = player.getScoreboard();
				scoreboard.getTeam("wins").setSuffix(HelixDecimalFormat.format(pvp.getWinssumo()));
				scoreboard.getTeam("loses").setSuffix(HelixDecimalFormat.format(pvp.getDeathssumo()));
				scoreboard.getTeam("ws").setSuffix(HelixDecimalFormat.format(pvp.getWinstreaksumo()));
				scoreboard.getTeam("xp").setSuffix(HelixDecimalFormat.format(pvp.getXp()));
				scoreboard.getTeam("rank").setSuffix((String.valueOf(Ranking.getRank(helixPlayer).getColoredName())));
		}
		 else if (HelixWarp.ONE_VS_ONE.hasPlayer(player.getName())) {
				HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
						.getPlayer(player.getName());
				PlayerPvP pvp = helixPlayer.getPvp();
				Scoreboard scoreboard = player.getScoreboard();
				scoreboard.getTeam("wins").setSuffix(HelixDecimalFormat.format(pvp.getWinsx1()));
				scoreboard.getTeam("loses").setSuffix(HelixDecimalFormat.format(pvp.getDeathsx1()));
				scoreboard.getTeam("ws").setSuffix(HelixDecimalFormat.format(pvp.getWinstreakx1()));
				scoreboard.getTeam("xp").setSuffix(HelixDecimalFormat.format(pvp.getXp()));
				scoreboard.getTeam("rank").setSuffix((String.valueOf(Ranking.getRank(helixPlayer).getColoredName())));
		}
		 else if (EventoUtils.game.contains(player.getName())) {
			 HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
						.getPlayer(player.getName());
				PlayerPvP pvp = helixPlayer.getPvp();
				Scoreboard scoreboard = player.getScoreboard();
			scoreboard.getTeam("coins").setSuffix((String.valueOf(HelixDecimalFormat.format(pvp.getCoins()))));
			scoreboard.getTeam("dano").setSuffix((String.valueOf(EventoUtils.damage ? "§aEnabled" : "§cDisabled")));
			scoreboard.getTeam("sala").setSuffix((String.valueOf((EventoUtils.tp ? "§aYes" : "§cNo"))));
			scoreboard.getTeam("pvp").setSuffix((String.valueOf((EventoUtils.pvp ? "§aEnabled" : "§cDisabled"))));
			scoreboard.getTeam("rank").setSuffix((String.valueOf(Ranking.getRank(helixPlayer).getColoredName())));
		 }
	}
	}

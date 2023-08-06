package net.helix.pvp.command;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.inventivetalent.bossbar.BossBarAPI;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.util.HelixCooldown2;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.evento.EventoUtils;
import net.helix.pvp.kit.Habilidade;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.helix.pvp.kit.provider.EnderMageReal;
import net.helix.pvp.kit.provider.GladiatorListener;
import net.helix.pvp.listener.PlayerJoinListener;
import net.helix.pvp.warp.HelixWarp;
import net.helix.pvp.warp.WarpDuoBattleHandle;
import net.helix.pvp.warp.provider.OneVsOne;
import net.helix.pvp.warp.provider.Sumo;

public class SpawnCMD extends WarpDuoBattleHandle implements Listener, CommandExecutor {
	
	public SpawnCMD() {
		 super("spawn_pos1", "spawn_pos2");
		// TODO Auto-generated constructor stub
	}

	public static ArrayList<String> indo = new ArrayList<String>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		Player player = (Player) sender;
		if (HelixWarp.SPAWN.hasPlayer(p.getName()) && !KitManager.getPlayer(p.getName()).hasKit() && !EventoUtils.game.contains(player.getName())) {
			p.sendMessage(ChatColor.RED + "Você já está no spawn.");
			return true;
		}
		else if (player.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura") && PlayerJoinListener.fall.contains(player)  && EnderMageReal.isSpawn(p.getLocation())) {
	      	player.sendMessage("§cVocê já está no spawn!");
	  		return true;
	  	 }
		 if (GladiatorListener.combateGlad.containsKey(p)) {
             final Player winner = GladiatorListener.combateGlad.get(p);
             final Player loser = p;
             GladiatorListener.resetGladiatorListenerBySpawn(winner, loser);
             GladiatorListener.combateGlad.remove(winner);
             GladiatorListener.combateGlad.remove(loser);
             Habilidade.removeAbility(p);
				HelixWarp.SPAWN.send(p, true);
				p.setFlying(false);
				indo.remove(p.getName());
				p.setGameMode(GameMode.SURVIVAL);
			    HelixCooldown2.removeCooldown(player, KitManager2.getPlayer(player.getName()).getkit2().getName());
			    net.helix.pvp.cooldown1.HelixCooldown2.removeCooldown(player, KitManager.getPlayer(player.getName()).getKit().getName());
				if (!HelixPvP.euforia) {
				BossBarAPI.removeAllBars(p);
				}
				if (!PlayerJoinListener.fall.contains(p)) {
			    	PlayerJoinListener.fall.add(p);
			    	p.sendMessage(ChatColor.GREEN + "Você recebeu a proteção do spawn");
			    }
				  HelixPlayer ph = HelixBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
				  HelixPlayer pk = HelixBukkit.getInstance().getPlayerManager().getPlayer(winner.getName());
				ph.getPvp().addDeaths(1);
				pk.getPvp().addKills(1);
				p.setLevel(0);
				HelixBukkit.getInstance().getPlayerManager().getController().save(ph);
				HelixBukkit.getInstance().getPlayerManager().getController().save(pk);
				p.sendMessage("§cVoce recebeu uma morte por ter dado /spawn no Gladiator!");
				winner.sendMessage("§cVoce recebeu uma kill por seu oponente ter dado /spawn no Gladiator!");
         }
		 if (net.helixpvp.kit2.GladiatorListener.combateGlad.containsKey(p)) {
				p.sendMessage("§eComando bloqueado no Gladiator!");
				p.playSound(p.getLocation(), Sound.GHAST_SCREAM, 10f, 10f);
				return true;
         }
		 if (Sumo.fastChallenge.contains(p)) {
			 Sumo.fastChallenge.remove(p);
			 p.sendMessage(ChatColor.GREEN + "Você saiu da fila da Sumo");
		 }
		 if (OneVsOne.fastChallenge.contains(p)) {
			 OneVsOne.fastChallenge.remove(p);
			 p.sendMessage(ChatColor.GREEN + "Você saiu da fila da 1v1");
		 }
		 if (KitManager.getPlayer(p.getName()).hasKit() && !p.hasPermission("kombo.cmd.report")) {
			 indo.add(p.getName());
			 p.sendMessage("§eTeleportando em 3 segundos!");
			 p.sendMessage("§eNão se mova!");
			 Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable()
 			{
       public void run()
       {
			 if (!indo.contains(p.getName())) {
				 p.sendMessage("§cVocê se moveu e o teleporte foi cancelado!");
				 return;
			 }
			 else {
				 if (!PlayerJoinListener.fall.contains(p)) {
				    	PlayerJoinListener.fall.add(p);
				    	p.sendMessage(ChatColor.GREEN + "Você recebeu a proteção do spawn");
				    }
				Habilidade.removeAbility(p);
				HelixWarp.SPAWN.send(p, true);
				p.setFlying(false);
				indo.remove(p.getName());
				p.setLevel(0);
				HelixCooldown2.removeCooldown(p , "Kit");
				BossBarAPI.removeAllBars(p);
				p.setGameMode(GameMode.SURVIVAL);
				return;
       } }}, 60L);
		 }
		 else {
		Habilidade.removeAbility(p);
		HelixWarp.SPAWN.send(p, true);
		p.setFlying(false);
		if (!PlayerJoinListener.fall.contains(p)) {
	    	PlayerJoinListener.fall.add(p);
	    	p.sendMessage(ChatColor.GREEN + "Você recebeu a proteção do spawn");
	    }
	    HelixCooldown2.removeCooldown(player, KitManager2.getPlayer(player.getName()).getkit2().getName());
	    net.helix.pvp.cooldown1.HelixCooldown2.removeCooldown(player, KitManager.getPlayer(player.getName()).getKit().getName());
		BossBarAPI.removeAllBars(p);
		p.setGameMode(GameMode.SURVIVAL);
		p.setLevel(0);
		return true;
	}
		return false;
}
	@EventHandler
    public void PlayerMove(PlayerMoveEvent event) {
        if (event.isCancelled()) return;
        Player player = event.getPlayer();
        if (!KitManager.getPlayer(player.getName()).hasKit()) {
        	return;
        }
        else if (indo.contains(player.getName())) {
        	indo.remove(player.getName());
        }
}
}
package net.helix.pvp.command;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.Habilidade;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.provider.GladiatorListener;
import net.helix.pvp.warp.HelixWarp;
import net.helix.pvp.warp.WarpDuoBattleHandle;
import net.helix.pvp.warp.provider.OneVsOne;

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
				p.sendMessage("§9Enviado para o spawn!");
				p.setGameMode(GameMode.SURVIVAL);
				if (OneVsOne.fastChallenge.contains(p)) {
					OneVsOne.fastChallenge.remove(p);
					p.sendMessage("§cTe removendo da 1v1!");
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
		 if (KitManager.getPlayer(p.getName()).hasKit() && !p.hasPermission("")) {
			 indo.add(p.getName());
			 p.sendMessage("§eEnviando você para o spawn em 3 segundos!");
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
				Habilidade.removeAbility(p);
				HelixWarp.SPAWN.send(p, true);
				p.setFlying(false);
				indo.remove(p.getName());
				p.setLevel(0);
				if (OneVsOne.fastChallenge.contains(p)) {
				OneVsOne.fastChallenge.remove(p);
				p.sendMessage("§cTe removendo da 1v1!");
				}
				p.sendMessage("§9Enviado para o spawn!");
				p.setGameMode(GameMode.SURVIVAL);
				return;
       } }}, 60L);
		 }
		 else {
		Habilidade.removeAbility(p);
		HelixWarp.SPAWN.send(p, true);
		p.setFlying(false);
		if (OneVsOne.fastChallenge.contains(p)) {
			OneVsOne.fastChallenge.remove(p);
			p.sendMessage("§cTe removendo da 1v1!");
			}
		p.sendMessage("§9Enviado para o spawn!");
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
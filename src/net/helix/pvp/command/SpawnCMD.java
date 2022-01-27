package net.helix.pvp.command;

import java.util.ArrayList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import net.helix.pvp.kit.Habilidade;
import net.helix.pvp.kit.provider.GladiatorListener;
import net.helix.pvp.warp.HelixWarp;
import net.helix.pvp.warp.WarpDuoBattleHandle;
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
		 if (GladiatorListener.combateGlad.containsKey(p)) {
             final Player winner = GladiatorListener.combateGlad.get(p);
             final Player loser = p;
             GladiatorListener.resetGladiatorListenerBySpawn(winner, loser);
             GladiatorListener.combateGlad.remove(winner);
             GladiatorListener.combateGlad.remove(loser);
         }
		 Habilidade.removeAbility(p);
		HelixWarp.SPAWN.send(p, true);
		p.sendMessage("§9Enviado para o spawn!");
		return true;
	}
}

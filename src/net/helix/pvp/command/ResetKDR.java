package net.helix.pvp.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;

public class ResetKDR
  implements Listener, CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (cmd.getName().equalsIgnoreCase("resetkdr"))
    {
      if (!(sender instanceof Player)) {
        return true;
      }
      Player p = (Player)sender;
      if (args.length == 0) {
    	  p.sendMessage(ChatColor.RED + "Utilize /resetkdr <nick>");
        return true;
      }
      else if (!sender.hasPermission("admin.cmd.resetkdr")) {
          sender.sendMessage("§cVocê não tem permissão");
          return true;
      }
      Player t = Bukkit.getPlayer(args[0]);
      if (args.length == 1) {
    	  if (t == null) {
              sender.sendMessage("§cO Player precisa estar online");
              return true;  
    	  }
    	  HelixPlayer pk = HelixBukkit.getInstance().getPlayerManager().getPlayer(t.getName());
			pk.getPvp().setDeaths(0);
			pk.getPvp().setKills(0);
			pk.getPvp().setCoins(0);
			pk.getPvp().setKillstreak(0);
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ajl removeplayer " + t.getName() + " helixpvp2_player_kills");
			if (t != null) {
			t.kickPlayer("§cSua conta e status foi resetada no servidor!");
			}
			Bukkit.broadcast("§4§lRESETKDR §a" + p.getName() + " §fresetou o KDR do Jogador " + t.getName(), "kombo.cmd.report");
      }
    }
    return true;
  }
}


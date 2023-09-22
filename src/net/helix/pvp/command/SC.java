package net.helix.pvp.command;



import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.pvp.listener.Medals;
import net.helix.pvp.listener.Ranking;
import net.luckperms.api.LuckPerms;




public class SC
  implements Listener, CommandExecutor
{
	public static ArrayList<String> staffchat = new ArrayList<>();
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
	  
    if (!(sender instanceof Player))
    {
      sender.sendMessage("§cThis command is only for players!");
      return true;
    }
    if (cmd.getName().equalsIgnoreCase("sc"))
    {
      if (args.length == 0) {
    	  if (!sender.hasPermission("kombo.cmd.sc"))
          {
            sender.sendMessage("§c§lERRO §fVoce nao tem permissão para executar esse comando");
            return true;
          }
  			if (!staffchat.contains(sender.getName())) {
  				staffchat.add(sender.getName());
  				sender.sendMessage("§aChat toggled on staffchat!");
  			} else {
  				staffchat.remove(sender.getName());
  				sender.sendMessage("§aStaffChat untoggled!");
  			}
      }
      if (args.length > 0)
      {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
          string.append(args[i] + " ");
        }
        String mensagem = string.toString();
        for (Player arrayOfPlayer : Bukkit.getOnlinePlayers())
        {
        	  RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
  			if (provider != null) {
  				LuckPerms api = provider.getProvider();
          Player staff = arrayOfPlayer;
          if (staff.hasPermission("kombo.cmd.sc")) {
        	  staff.sendMessage("§c§l[SC] §c- §f" +  api.getUserManager().getUser(((Player) sender).getUniqueId()).getCachedData().getMetaData().getPrefix().replace("&", "§") + sender.getName() + " §8> §f" + mensagem.replace("&", "§"));
          }
        }
      }
    }
    return false;
  }
	return false;
  }

 
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		  Medals[] values;
		  RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
			if (provider != null) {
	        LuckPerms api = provider.getProvider();
		  HelixPlayer hp = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
		  Medals medal = Medals.getMedals(player);
		  Ranking rank = Ranking.getRank(hp);
		  if (Fake.playerfakename.get(player) != null) {
			  event.setFormat("§7[" + rank.getColoredSymbol() + "§7] " + "§7" + Fake.playerfakename.get(player) + "§7: " + event.getMessage().replace("&", "§"));
		  }	  
		  else if (player.hasPermission("kombo.doublexp")) {
			event.setFormat("§7[" + rank.getColoredSymbol() + "§7] " + api.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix().replace("&", "§") + player.getName() + "§f: " + event.getMessage().replace("&", "§").replace("%", "%%"));
		}
		else {
			event.setFormat("§7[" + rank.getColoredSymbol() + "§7] " + api.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix().replace("&", "§") + player.getName() + "§7: " + event.getMessage().replace("%", "%%"));
		}
		if (staffchat.contains(player.getName())) {
			for (Player staff : Bukkit.getOnlinePlayers()) {
				if (staff.hasPermission("kombo.cmd.sc")) {
					staff.sendMessage("§c§l[SC] §c- §f" +  api.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix().replace("&", "§") + player.getName() + " §8> §f" + event.getMessage().replace("%", "%%").replace("&", "§"));
					event.setCancelled(true);
				}
			}
		}
		if (!ChatCommand.chat) {
			if (!player.hasPermission("kombo.cmd.report")) {
				player.sendMessage("§fThe chat is currently §cdisabled§f!");
				event.setCancelled(true);
				return;
			}
}
  }
	}
}

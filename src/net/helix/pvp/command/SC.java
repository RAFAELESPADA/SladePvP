package net.helix.pvp.command;



import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;




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
  				sender.sendMessage("§aChat travado no staffchat!");
  			} else {
  				staffchat.remove(sender.getName());
  				sender.sendMessage("§aChat destravado no staffchat!");
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
          Player staff = arrayOfPlayer;
          if (staff.hasPermission("kombo.cmd.sc")) {
            staff.sendMessage("§6§l[STAFFCHAT] §a" + sender.getName() + ": §f" + mensagem.replace("&", "§"));
          }
        }
      }
    }
    return false;
  }

 
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		if (staffchat.contains(player.getName())) {
			for (Player staff : Bukkit.getOnlinePlayers()) {
				if (staff.hasPermission("kombo.cmd.sc")) {
					staff.sendMessage("§c§l[SC] §c- §f" + player.getName() + " §8> §f" + event.getMessage().replace("%", "%%").replace("&", "§"));
					event.setCancelled(true);
				}
			}
		}
		if (!ChatCommand.chat) {
			if (!player.hasPermission("kombo.cmd.report")) {
				player.sendMessage("§fO chat está §cdesabilitado§f!");
				event.setCancelled(true);
			}
}
  }
  }

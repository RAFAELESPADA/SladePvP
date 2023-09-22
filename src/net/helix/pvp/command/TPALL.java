package net.helix.pvp.command;



import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;


public class TPALL
  implements Listener, CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    Player p = (Player)sender;
    if (!p.hasPermission("kombo.cmd.tpall"))
    {
    	p.sendMessage("§c§lERRO §fYou do not have permission");
      return true;
    }
    for (Player p2 : Bukkit.getOnlinePlayers()) {
      if (p2 != p)
      {
        p2.teleport(p);
        p2.sendMessage("§7 " + p.getName() + " puxou todos ate ele !");
      }
    }
    p.sendMessage("§aEveryone is at your location !");
    return false;
  }
}


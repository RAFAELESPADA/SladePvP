package net.helix.pvp.command;



import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class SC
  implements Listener, CommandExecutor
{
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
        sender.sendMessage("§cUse: §7/sc <MENSAGEM>");
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
          if (!sender.hasPermission("kombo.cmd.sc"))
          {
            sender.sendMessage("§c§lERRO §fVoce nao tem permiss§o para executar esse comando");
            return true;
          }
          if (staff.hasPermission("kombo.cmd.sc")) {
            staff.sendMessage("§6§l[STAFFCHAT] §a" + sender.getName() + ": §f" + mensagem.replace("&", "§"));
          }
        }
      }
    }
    return false;
  }
}

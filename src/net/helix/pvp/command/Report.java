package net.helix.pvp.command;


import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import net.helix.pvp.HelixPvP;

public class Report
  implements CommandExecutor
{
  private static ArrayList<String> delay = new ArrayList();
  
  public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args)
  {
    if (!(sender instanceof Player)) {
      return true;
    }
    if (cmd.getName().equalsIgnoreCase("report"))
    {
      final Player p = (Player)sender;
      if (args.length <= 1)
      {
        p.sendMessage("§cUse: /report <player> <motivo>");
        return true;
      }
      if (delay.contains(p.getName()))
      {
        p.sendMessage("§cAguarde para poder reportar novamente.");
        return true;
      }
      delay.add(p.getName());
      Bukkit.getScheduler().runTaskLater(HelixPvP.getInstance(), new BukkitRunnable()
      {
        public void run()
        {
          Report.delay.remove(p.getName());
          p.sendMessage("§eVocê pode reportar novamente");
        }
      }, 1200L);
      String reportado = args[0];
      if (p.getName().equalsIgnoreCase(reportado)) {
    	  p.sendMessage("§cVocê não pode se reportar.");
          return true;
        
      }
      StringBuilder sb = new StringBuilder();
      for (int i = 1; i < args.length; i++)
      {
        sb.append(args[i]);
        sb.append(" ");
      }
      String motivo = sb.toString();
      p.sendMessage(" \n §aJogador foi reportado com sucesso!");
      p.sendMessage("§a§l* §7O uso indevido ou exagerado do /report pode resultar em punição! \n ");
      for (Player all : Bukkit.getOnlinePlayers()) {
        if (all.hasPermission("kombo.cmd.report"))
        {
          all.playSound(all.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
          all.sendMessage(" \n §a========§e§lREPORT§a========== \n  §eReporter: §7" + p.getName() + " \n  §eJogador reportado: §7" + reportado + " \n  §eMotivo:§7 " + motivo + " \n §a========§e§lREPORT§a========== \n ");
        }
      }
    }
    return false;
  }
}


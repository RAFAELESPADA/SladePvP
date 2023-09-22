package net.helix.pvp.command;


import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.helix.pvp.HelixPvP;

public class Report
  implements CommandExecutor
{
  private static ArrayList<String> delay = new ArrayList();
  public static HashMap<String, String> toggle = new HashMap();
  
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
        p.sendMessage("§cUse: /report <player> <reason>");
        return true;
      }
      if (delay.contains(p.getName()))
      {
        p.sendMessage("§cWait a little to report again.");
        return true;
      }
      delay.add(p.getName());
      Bukkit.getScheduler().runTaskLater(HelixPvP.getInstance(), new BukkitRunnable()
      {
        public void run()
        {
          Report.delay.remove(p.getName());
          p.sendMessage("§eYou can report again");
        }
      }, 1200L);
      String reportado = args[0];
      if (p.getName().equalsIgnoreCase(reportado)) {
    	  p.sendMessage("§cYou cant report yourself.");
          return true;
        
      }
      if (reportado == null) {
    	  p.sendMessage("§cThis player is offline.");
          return true;
        
      }
      StringBuilder sb = new StringBuilder();
      for (int i = 1; i < args.length; i++)
      {
        sb.append(args[i]);
        sb.append(" ");
      }
      String motivo = sb.toString();
      p.sendMessage("§aPlayer report with sucess!");
      p.sendMessage("\n §a§l* §7Dont abuse this command or else you will be punished! \n");
      Player reported = Bukkit.getPlayer(reportado);
      for (Player all : Bukkit.getOnlinePlayers()) {
        if (all.hasPermission("kombo.cmd.report")) {
        	if (toggle.containsKey(all.getName()))
        		return true;
        {
          all.playSound(all.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
          all.sendMessage(" \n§a========§e§lNEW REPORT§a========== \n  §eReporter: §7" + p.getName() + " \n  §ePlayer: §7" + reportado + " \n  §eReason:§7 " + motivo + " \n §a========§e§lNOVO REPORT§a========== \n ");
        }
        }
      }
    }
    
    return false;
  }

}


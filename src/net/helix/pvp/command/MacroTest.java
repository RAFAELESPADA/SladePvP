package net.helix.pvp.command;


import net.helix.pvp.HelixPvP;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class MacroTest implements Listener, CommandExecutor {
  static final HashMap<String, Integer> Clicks = new HashMap<>();
  
  static final ArrayList<String> macro = new ArrayList<>();
  
  public static void testeMAcro(final Player p, final Player t) {
    p.sendMessage("§6§lMACROTEST §eTestando macro no(a) jogador(a) §b"  + t.getName());
    macro.add(t.getName());
    Clicks.put(t.getName(), Integer.valueOf(0));
    Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)HelixPvP.getInstance(), new Runnable() {
          public void run() {
            p.sendMessage("");
            p.sendMessage("        §6§lMACROTEST                      ");
            p.sendMessage("§6Jogador §b" + t.getName());
            p.sendMessage("§6Total De Clicks: §b" + Clicks.get(t.getName()));
            p.sendMessage("§6Clicks 5s:§b " + Clicks.get(t.getName()) / 2);
            p.sendMessage("§6Clicks 1s:§b " + Clicks.get(t.getName()) / 10);
            p.sendMessage("");
            macro.remove(t.getName());
            if (Clicks.get(t.getName()).intValue() >= 300 && !t.hasPermission("kombo.cmd.report")) {
              Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "tempban " + p.getName() + " 5d [AutoBan] Uso de Macro/AutoClicker"); 
          }
          }
        }, 200L);
  }
  
  @EventHandler
  public void macro(PlayerInteractEvent e) {
    Player p = e.getPlayer();
    if (macro.contains(p.getName()) && (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK))
      Clicks.put(p.getName(), Integer.valueOf(((Integer)Clicks.get(p.getName())).intValue() + 1)); 
  }
  
  public final boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
    Player p = (Player)Sender;
    if (p.hasPermission("kombo.cmd.report")) {
      if (Args.length == 0) {
        p.sendMessage("§6§lMACROTEST §b> utilize a sintaxe correta: /macrotest [Jogador(a)]");
        return true;
      } 
      Player t = Bukkit.getPlayer(Args[0]);
      if (t != null)
        testeMAcro(p, t);
      else {
    	  p.sendMessage("§cEsse jogador está offline");
      }
    } 
    else {
    	p.sendMessage("§cVocê não tem autorização.");
    }
    return false;
  }
}


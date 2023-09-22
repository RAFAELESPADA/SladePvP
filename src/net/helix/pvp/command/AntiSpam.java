package net.helix.pvp.command;


import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;



  
  

  public class AntiSpam
    implements Listener
  {
    public static HashMap<Player, Long> chat = new HashMap();
    
    @EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled=false)
    public void aAntiSpam(AsyncPlayerChatEvent e)
    {
      Player p = e.getPlayer();
      if (!chat.containsKey(p))
      {
        int zeitconfig = 2000;
        long time = System.currentTimeMillis() + zeitconfig;
        if (p.hasPermission("kombo.antispam")) {
          return;
        }
        chat.put(p, Long.valueOf(time));
      }
      else if (((Long)chat.get(p)).longValue() <= System.currentTimeMillis())
      {
        if (p.hasPermission("kombo.antispam")) {
          return;
        }
        int zeitconfig = 2000;
        long time = System.currentTimeMillis() + zeitconfig;
        chat.put(p, Long.valueOf(time));
      }
      else
      {
        if ((((Long)chat.get(p)).longValue() < System.currentTimeMillis()) || 
          (p.hasPermission("kombo.antispam"))) {
          return;
        }
        p.sendMessage(("§c§lSPAM §fDont spam dude!"));
        e.setCancelled(true);
      }
    }
  }
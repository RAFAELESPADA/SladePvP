package net.helix.pvp.command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.helix.pvp.HelixPvP;
import net.helix.pvp.evento.EventoType;
import net.helix.pvp.evento.EventoUtils;
import net.helix.pvp.warp.HelixWarp;

public class LavaIniciar
implements CommandExecutor
{
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
{
  Player p = (Player)sender;
  if (label.equalsIgnoreCase("lavainiciar"))
  {
	  if (!p.hasPermission("kombo.cmd.evento")) {
		  p.sendMessage("§cVocê não tem permissão!");
		  return true;
	  }
	  else if (EventoUtils.evento) {
		  p.sendMessage("A event is already occouring!");
		  return true;
	  }
	  p.sendMessage("§aStarting lava event"); 
	  EventoUtils.evento = true;
	  EventoUtils.tp = true;
	  Bukkit.broadcastMessage("§cThe lava event started.");
      Bukkit.broadcastMessage("§cUse /event join to join");
      Bukkit.broadcastMessage("§cThe event will start in 5 minutes");
      for (Player p1 : Bukkit.getOnlinePlayers()) {
      	p1.playSound(p1.getLocation(), Sound.LEVEL_UP, 1f, 1f);
      }
      Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				Bukkit.broadcastMessage("§cThe event will start in 4 minutes");
				Bukkit.broadcastMessage("§cPlayers in event: " + EventoUtils.getEventoPlayers().size());
				 for (Player p1 : Bukkit.getOnlinePlayers()) {
				      	p1.playSound(p1.getLocation(), Sound.LEVEL_UP, 1f, 1f);
				      }
			}
		}, 1200L);
      Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				Bukkit.broadcastMessage("§cThe event will start in 3 minutes");
				Bukkit.broadcastMessage("§cPlayers in event: " + EventoUtils.getEventoPlayers().size());
				 for (Player p1 : Bukkit.getOnlinePlayers()) {
				      	p1.playSound(p1.getLocation(), Sound.LEVEL_UP, 1f, 1f);
				      }
			}
		}, 2400L);
      Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				Bukkit.broadcastMessage("§cThe event will start in 2 minutes");
				Bukkit.broadcastMessage("§cPlayers in event: " + EventoUtils.getEventoPlayers().size());
			}
		}, 3600L);
      Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				Bukkit.broadcastMessage("§cThe event will start in 1 minutes");
				Bukkit.broadcastMessage("§cPlayers in event: " + EventoUtils.getEventoPlayers().size());
			}
		}, 4800L);
		Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				 EventoType ev = EventoType.getEventoByName("Lava");
			 	 Location evt = ev.getLocation();
			 	 EventoUtils.getEventoPlayers().forEach(p2 -> {
                 	p2.teleport(evt);
			      for (Player p1 : Bukkit.getOnlinePlayers()) {
			        	p1.playSound(p1.getLocation(), Sound.LEVEL_UP, 1f, 1f);
			        }
			});
			 	Bukkit.broadcastMessage("§cTeleporting people to the event!");
			 	EventoType evento = EventoType.getEventoByName("Lava");
			    Bukkit.broadcastMessage("§aStarting the explanation of the event §e" + evento.getName().toUpperCase() + "§a...");
                EventoType.explicarEvento(evento);
                EventoUtils.started = true;
			}}, 6000L);
		Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				Bukkit.broadcastMessage("§c§lThe event started!");
				Bukkit.broadcastMessage("§c§lGood luck!");
				EventoUtils.getEventoPlayers().forEach(p2 -> {
		EventoUtils.pvp = false;
     	EventoUtils.damage = true;
     	EventoUtils.build = false;
     	EventoUtils.tp = false;
     	p2.playSound(p2.getLocation(), Sound.ENDERDRAGON_GROWL, 1f, 1f);
    		
    		
    		p2.getInventory().setHeldItemSlot(0);
    		ItemStack vermelho = new ItemStack(Material.RED_MUSHROOM, 64);
    		
  		  
  		  ItemStack marrom = new ItemStack(Material.BROWN_MUSHROOM, 64);
  		  
  		  ItemStack item = new ItemStack(Material.BOWL, 64);
  		  p2.getInventory().setItem(14, vermelho);
  		  p2.getInventory().setItem(15, marrom);
  		  p2.getInventory().setItem(13, item);
  		p2.getInventory().setItem(23, vermelho);
		  p2.getInventory().setItem(24, marrom);
		  p2.getInventory().setItem(22, item);
  		  for (int i = 0; i < 33; i++) {
  				p2.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
  			}
    		p2.updateInventory();
			}); {
			}}}, 7200L);
	}
  Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
		public void run() {
			if (!EventoUtils.evento || !EventoUtils.started) {
				  return;
			  }
			EventoUtils.getEventoPlayers().forEach(p ->  {
				if (p != null && EventoUtils.getEventoPlayers().size() != 0) {
            	net.helix.pvp.evento.EventoUtils.setEvento(false, p);
                HelixWarp.SPAWN.send(p);
                p.chat("/spawn");
                p.getActivePotionEffects().forEach(ef -> p.removePotionEffect(ef.getType()));
                
                if (EventoUtils.getEventoPlayers().size() == 1) {

					for(Player p1 : Bukkit.getOnlinePlayers()){
						p1.sendMessage("               §b§lEVENT               ");
						p1.sendMessage("");
						p1.sendMessage("§aPLAYER: " + EventoUtils.getEventoPlayersNames() + " wins the event");
						p1.sendMessage("");
						p1.sendMessage("               §b§lEVENT               ");
					}
                	Bukkit.broadcastMessage("§aEvent winner: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aEvent winner: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aEvent winner: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aEvent winner: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aEvent winner: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aEvent winner: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aEvent winner: " + EventoUtils.getEventoPlayersNames());
                	p.setHealth(20);
                	p.getWorld().strikeLightning(p.getLocation());
            		p.getWorld().strikeLightning(p.getLocation());
            		p.getWorld().strikeLightning(p.getLocation());
            		p.getWorld().strikeLightning(p.getLocation());
                }
                p.sendMessage("§cThe event ended because the timer runs out.");
                p.sendMessage("§cEvent duration: §a40 minutes");
			}
            });;
            EventoUtils.resetEventoClass();
		}}, 48000L);
  
return false;
      };
      {

}
{

}
}

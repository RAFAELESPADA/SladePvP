package net.helix.pvp.command;


import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.api.event.player.PlayerLoadEvent;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.evento.EventoType;
import net.helix.pvp.evento.EventoUtils;
import net.helix.pvp.warp.HelixWarp;

public class StaffVsPlayers
implements CommandExecutor, Listener
{
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
{
  Player p = (Player)sender;
  if (label.equalsIgnoreCase("svpiniciar"))
  {
	  if (!p.hasPermission("kombo.cmd.evento")) {
		  p.sendMessage("§cYou dont have permission!");
		  return true;
	  }
	  else if (EventoUtils.evento) {
		  p.sendMessage("A event is already occouring!");
		  return true;
	  }
	  p.sendMessage("§aStarting staff vs players event"); 
	  EventoUtils.evento = true;
	  EventoUtils.tp = true;
	  Bukkit.broadcastMessage("§cThe Staff vs Players event will start soon.");
      Bukkit.broadcastMessage("§cUse /event join to join it");
  	Bukkit.broadcastMessage("§cThe arena event will start in 5 minutes");
      for (Player p1 : Bukkit.getOnlinePlayers()) {
      	p1.playSound(p1.getLocation(), Sound.LEVEL_UP, 1f, 1f);
      }
      Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				  if (!EventoUtils.evento) {
					  return;
				  }
					Bukkit.broadcastMessage("§cThe Staff vs Players event will start in 4 minutes");
				Bukkit.broadcastMessage("§cPlayers in the event: " + EventoUtils.getEventoPlayers().size());
				 for (Player p1 : Bukkit.getOnlinePlayers()) {
				      	p1.playSound(p1.getLocation(), Sound.LEVEL_UP, 1f, 1f);
				      }
			}
		}, 600L);
      Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				Bukkit.broadcastMessage("§cThe Staff vs Players event will start in 3 minutes");
				Bukkit.broadcastMessage("§cPlayers in the event: " + EventoUtils.getEventoPlayers().size());
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
				Bukkit.broadcastMessage("§cThe Staff vs Players event will start in 2 minutes");
				Bukkit.broadcastMessage("§cPlayers in the event: " + EventoUtils.getEventoPlayers().size());
			}
		}, 1800L);
      Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				Bukkit.broadcastMessage("§cThe Staff vs Players event will start in 1 minute");
				Bukkit.broadcastMessage("§cPlayers in the event: " + EventoUtils.getEventoPlayers().size());
			}
		}, 2400L);
		Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				 EventoType ev = EventoType.getEventoByName("StaffVsPlayers");
			 	 Location evt = ev.getLocation();
			 	 EventoUtils.getEventoPlayers().forEach(p2 -> {
                 	p2.teleport(evt);
			      for (Player p1 : Bukkit.getOnlinePlayers()) {
			        	p1.playSound(p1.getLocation(), Sound.LEVEL_UP, 1f, 1f);
			        }
			});
			 	Bukkit.broadcastMessage("§cTeleporting people to the event!");
			 	EventoType evento = EventoType.getEventoByName("StaffVsPlayers");
			    Bukkit.broadcastMessage("§aStarting explanation of the event §e" + evento.getName().toUpperCase() + "§a...");
                EventoType.explicarEvento(evento);
                EventoUtils.started = true;
			}}, 3000L);
		Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				Bukkit.broadcastMessage("§c§lThe event started!");
				Bukkit.broadcastMessage("§c§lGood luck!");
				EventoUtils.getEventoPlayers().forEach(p2 -> {
		EventoUtils.pvp = true;
		p2.playSound(p2.getLocation(), Sound.ENDERDRAGON_GROWL, 1f, 1f);
     	EventoUtils.damage = true;
     	EventoUtils.build = true;
     	EventoUtils.staff = true;
     	EventoUtils.tp = false;
     	ItemStack helmet = new ItemStack(Material.IRON_HELMET);
		ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack boost = new ItemStack(Material.IRON_BOOTS);
		ItemStack helmet2 = new ItemStack(Material.DIAMOND_HELMET);
		ItemStack chest2 = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemStack leg2 = new ItemStack(Material.DIAMOND_LEGGINGS);
		ItemStack boost2 = new ItemStack(Material.DIAMOND_BOOTS);
    		
    		if (!p2.hasPermission("kombo.cmd.report")) {
    		p2.getInventory().setHeldItemSlot(0);
    		/* 348 */       
    		p2.getInventory().setItem(0, new ItemBuilder("§7Sword", Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1).addEnchant(Enchantment.DURABILITY, 3)
    				.nbt("cancel-drop")
    				.toStack()
    		);
    		p2.getInventory().setHelmet(helmet);
    		p2.getInventory().setChestplate(chest);
    		p2.getInventory().setLeggings(leg);
    		p2.getInventory().setBoots(boost);
    		p2.getInventory().setItem(9, helmet);
    		p2.getInventory().setItem(10, chest);
    		p2.getInventory().setItem(11, leg);
    		p2.getInventory().setItem(12, boost);
    		p2.getInventory().setItem(18, helmet);
    		p2.getInventory().setItem(19, chest);
    		p2.getInventory().setItem(20, leg);
    		p2.getInventory().setItem(21, boost);
    		Dye d = new Dye();
    		p2.teleport(new Location(p2.getWorld(), 156155.510, 66, 158182.357));
    		
    	    d.setColor(DyeColor.BROWN);
    	    ItemStack di = d.toItemStack();
    	    di.setAmount(64);
    		p2.getInventory().setItem(13, new ItemStack(Material.BOWL, 64));
    		p2.getInventory().setItem(22, new ItemStack(Material.BOWL, 64));
    		p2.getInventory().setItem(14, di);
    		p2.getInventory().setItem(23, di);
    		p2.getInventory().setItem(16, new ItemStack(Material.COBBLE_WALL, 64));
    		p2.getInventory().setItem(2, new ItemStack(Material.WOOD, 64));
    		p2.getInventory().setItem(8, new ItemStack(Material.WEB, 8));
    		p2.getInventory().setItem(17, new ItemStack(Material.STONE_PICKAXE, 1));
    		p2.getInventory().setItem(26, new ItemStack(Material.STONE_AXE, 1));
    		p2.getInventory().setItem(10, chest);
    		p2.getInventory().setItem(11, leg);
    		p2.getInventory().setItem(12, boost);
    		p2.getInventory().setItem(18, helmet);
    		p2.getInventory().setItem(19, chest);
    		p2.getInventory().setItem(20, leg);
    		p2.getInventory().setItem(21, boost);
    		p2.setGameMode(GameMode.SURVIVAL);
    		for (int i = 0; i < 17; i++) {
    			p2.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));    			
    		}
    		p2.updateInventory();
    		} else {
    			p2.getInventory().setHeldItemSlot(0);
        		/* 348 */  
    			p2.teleport(new Location(p2.getWorld(), 156155.049, 66, 158122.259));
        		p2.getInventory().setItem(0, new ItemBuilder("§7Sword", Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1)
        				.nbt("cancel-drop")
        				.toStack()
        		);
        		p2.getInventory().setHelmet(helmet2);
        		p2.getInventory().setChestplate(chest2);
        		p2.getInventory().setLeggings(leg2);
        		p2.getInventory().setBoots(boost2);
        		p2.getInventory().setItem(9, helmet2);
        		p2.getInventory().setItem(10, chest2);
        		p2.getInventory().setItem(11, leg2);
        		p2.getInventory().setItem(18, helmet2);
        		p2.getInventory().setItem(19, chest2);
        		p2.getInventory().setItem(20, leg2);
        		p2.getInventory().setItem(21, boost2);
        		Dye d = new Dye();
        	    d.setColor(DyeColor.BROWN);
        	    ItemStack di = d.toItemStack();
        	    di.setAmount(64);
        		p2.getInventory().setItem(13, new ItemStack(Material.BOWL, 64));
        		p2.getInventory().setItem(22, new ItemStack(Material.BOWL, 64));
        		p2.getInventory().setItem(14, di);
        		p2.getInventory().setItem(23, di);
        		p2.getInventory().setItem(16, new ItemStack(Material.COBBLE_WALL, 64));
        		p2.getInventory().setItem(2, new ItemStack(Material.WOOD, 64));
        		p2.getInventory().setItem(8, new ItemStack(Material.WEB, 8));
        		p2.getInventory().setItem(17, new ItemStack(Material.STONE_PICKAXE, 1));
        		p2.getInventory().setItem(26, new ItemStack(Material.STONE_AXE, 1));
        		p2.getInventory().setItem(10, chest);
        		p2.getInventory().setItem(11, leg);
        		p2.getInventory().setItem(12, boost);
        		p2.getInventory().setItem(18, helmet);
        		p2.getInventory().setItem(19, chest);
        		p2.getInventory().setItem(20, leg);
        		p2.getInventory().setItem(21, boost);
    		}
    		for (int i = 0; i < 17; i++) {
    			p2.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));    			
    		}
			});
				{
			}}}, 3600L);
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
                p.sendMessage("§cEvent duration: §a50 minutes");
			}
            });;
            EventoUtils.resetEventoClass();
		}}, 64000L);
  
return false;
      };
      {

}
 
      
      
      }







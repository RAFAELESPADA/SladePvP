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
import org.bukkit.potion.PotionEffectType;

import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.api.event.player.PlayerLoadEvent;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.evento.EventoType;
import net.helix.pvp.evento.EventoUtils;
import net.helix.pvp.warp.HelixWarp;
import net.helixpvp.kit2.Kangaroo;

public class MDRIniciar
implements CommandExecutor, Listener
{
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
{
  Player p = (Player)sender;
  if (label.equalsIgnoreCase("mdriniciar"))
  {
	  if (!p.hasPermission("kombo.cmd.evento")) {
		  p.sendMessage("§cYou dont have permission!");
		  return true;
	  }
	  else if (EventoUtils.evento) {
		  p.sendMessage("A event is already occouring!");
		  return true;
	  }
	  p.sendMessage("§aStarting MDR event"); 
	  EventoUtils.evento = true;
	  EventoUtils.tp = true;
	  Bukkit.broadcastMessage("§cThe MDR event will start soon.");
      Bukkit.broadcastMessage("§cUse /event join to join it");
  	Bukkit.broadcastMessage("§cThe MDR event will start in 5 minutes");
      for (Player p1 : Bukkit.getOnlinePlayers()) {
      	p1.playSound(p1.getLocation(), Sound.LEVEL_UP, 1f, 1f);
      }
      Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				  if (!EventoUtils.evento) {
					  return;
				  }
					Bukkit.broadcastMessage("§cThe MDR event will start in 4 minutes");
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
				Bukkit.broadcastMessage("§cThe MDR event will start in 3 minutes");
				Bukkit.broadcastMessage("§cPlayers in the event: " + EventoUtils.getEventoPlayers().size());
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
				Bukkit.broadcastMessage("§cThe MDR event will start in 2 minutes");
				Bukkit.broadcastMessage("§cPlayers in the event: " + EventoUtils.getEventoPlayers().size());
			}
		}, 3600L);
      Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				Bukkit.broadcastMessage("§cThe MDR event will start in 1 minute");
				Bukkit.broadcastMessage("§cPlayers in the event: " + EventoUtils.getEventoPlayers().size());
			}
		}, 4800L);
		Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				 EventoType ev = EventoType.getEventoByName("Mdr");
			 	 Location evt = ev.getLocation();
			 	 EventoUtils.getEventoPlayers().forEach(p2 -> {
                 	p2.teleport(evt);
			      for (Player p1 : Bukkit.getOnlinePlayers()) {
			        	p1.playSound(p1.getLocation(), Sound.LEVEL_UP, 1f, 1f);
			        }
			});
			 	Bukkit.broadcastMessage("§cTeleporting people to the event!");
			 	EventoType evento = EventoType.getEventoByName("Mdr");
			    Bukkit.broadcastMessage("§aStarting explanation of the event §e" + evento.getName().toUpperCase() + "§a...");
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
		EventoUtils.pvp = true;
		p2.playSound(p2.getLocation(), Sound.ENDERDRAGON_GROWL, 1f, 1f);
     	EventoUtils.damage = true;
     	EventoUtils.build = false;
     	EventoUtils.tp = false;
     	ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack boost = new ItemStack(Material.LEATHER_BOOTS);
		ItemStack helmet2 = new ItemStack(Material.DIAMOND_HELMET);
		ItemStack chest2 = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemStack leg2 = new ItemStack(Material.DIAMOND_LEGGINGS);
		ItemStack boost2 = new ItemStack(Material.DIAMOND_BOOTS);
    		
    		if (!p2.hasPermission("kombo.cmd.evento")) {
    		p2.getInventory().setHeldItemSlot(0);
    		p2.teleport(new Location(p2.getWorld(),326877.559, 70.0000000000, 345987.734));
    		p2.getInventory().setItem(0, new ItemStack(Material.GOLDEN_APPLE, 64));
    		p2.updateInventory();
    		} else {
    			p2.getInventory().setHeldItemSlot(0);
        		/* 348 */  
        		p2.teleport(new Location(p2.getWorld(),326874.197, 67.000000, 345994.691));
        		p2.getInventory().setItem(0, new ItemBuilder("§7Sword", Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 5)
        				.nbt("cancel-drop")
        				.toStack()
        		);
        		Kangaroo.darEfeito(p2, PotionEffectType.INCREASE_DAMAGE, 9999, 10);
        		p2.getInventory().setHelmet(helmet);
        		p2.getInventory().setChestplate(chest);
        		p2.getInventory().setLeggings(leg);
        		p2.getInventory().setBoots(boost);
        		p2.getInventory().setItem(9, helmet);
        		p2.getInventory().setItem(10, chest);
        		p2.getInventory().setItem(11, leg);
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
			});
				{
			}}}, 7200L);
	}
  
      {

      }
	return false;}
 
      
     
      }







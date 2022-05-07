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
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.core.bukkit.util.DamageUtil;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.evento.EventoType;
import net.helix.pvp.evento.EventoUtils;
import net.helix.pvp.evento.SoupTypeGUI;
import net.helix.pvp.warp.HelixWarp;

public class Arena
implements CommandExecutor
{
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
{
  Player p = (Player)sender;
  if (label.equalsIgnoreCase("arenainiciar"))
  {
	  if (!p.hasPermission("kombo.cmd.evento")) {
		  p.sendMessage("§cVocê não tem permissão!");
		  return true;
	  }
	  else if (EventoUtils.evento) {
		  p.sendMessage("Um evento ja esta ocorrendo!");
		  return true;
	  }
	  p.sendMessage("§aIniciando evento Arena"); 
	  EventoUtils.evento = true;
	  EventoUtils.tp = true;
	  Bukkit.broadcastMessage("§cO evento Arena acabou de iniciar.");
      Bukkit.broadcastMessage("§cUtilize /evento entrar");
      Bukkit.broadcastMessage("§cO evento começara automaticamente em 5 minutos");
      for (Player p1 : Bukkit.getOnlinePlayers()) {
      	p1.playSound(p1.getLocation(), Sound.LEVEL_UP, 1f, 1f);
      }
      Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				  if (!EventoUtils.evento) {
					  return;
				  }
				Bukkit.broadcastMessage("§cO evento Arena começara automaticamente em 4 minutos");
				Bukkit.broadcastMessage("§cPlayers no evento: " + EventoUtils.getEventoPlayers().size());
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
				Bukkit.broadcastMessage("§cO evento Arena começara automaticamente em 3 minutos");
				Bukkit.broadcastMessage("§cPlayers no evento: " + EventoUtils.getEventoPlayers().size());
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
				Bukkit.broadcastMessage("§cO evento Arena começara automaticamente em 2 minutos");
				Bukkit.broadcastMessage("§cPlayers no evento: " + EventoUtils.getEventoPlayers().size());
			}
		}, 3600L);
      Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				Bukkit.broadcastMessage("§cO evento Arena começara automaticamente em 1 minuto");
				Bukkit.broadcastMessage("§cPlayers no evento: " + EventoUtils.getEventoPlayers().size());
			}
		}, 4800L);
		Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				 EventoType ev = EventoType.getEventoByName("ArenaPvP");
			 	 Location evt = ev.getLocation();
			 	 EventoUtils.getEventoPlayers().forEach(p2 -> {
                 	p2.teleport(evt);
			      for (Player p1 : Bukkit.getOnlinePlayers()) {
			        	p1.playSound(p1.getLocation(), Sound.LEVEL_UP, 1f, 1f);
			        }
			});
			 	Bukkit.broadcastMessage("§cTeleportando as pessoas para o Evento!");
			 	EventoType evento = EventoType.getEventoByName("ArenaPvP");
			    Bukkit.broadcastMessage("§aIniciando explicação do evento §e" + evento.getName().toUpperCase() + "§a...");
                EventoType.explicarEvento(evento);
			}}, 6000L);
		Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				Bukkit.broadcastMessage("§c§lO Evento Começou!");
				Bukkit.broadcastMessage("§c§lBoa Sorte!");
				EventoUtils.getEventoPlayers().forEach(p2 -> {
		EventoUtils.pvp = true;
		p2.playSound(p2.getLocation(), Sound.ENDERDRAGON_GROWL, 1f, 1f);
     	EventoUtils.damage = true;
     	EventoUtils.build = true;
     	EventoUtils.tp = false;
     	EventoUtils.started = true;
     	ItemStack helmet = new ItemStack(Material.IRON_HELMET);
		ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack boost = new ItemStack(Material.IRON_BOOTS);
    		
    		
    		p2.getInventory().setHeldItemSlot(0);
    		/* 348 */       
    		p2.getInventory().setItem(0, new ItemBuilder("§7Espada de Diamante", Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1)
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
    	    d.setColor(DyeColor.BROWN);
    	    ItemStack di = d.toItemStack();
    	    di.setAmount(64);
    		p2.getInventory().setItem(13, new ItemStack(Material.BOWL, 64));
    		p2.getInventory().setItem(22, new ItemStack(Material.BOWL, 64));
    		p2.getInventory().setItem(14, di);
    		p2.getInventory().setItem(23, di);
    		p2.getInventory().setItem(15, new ItemStack(Material.WATER_BUCKET, 1));
    		p2.getInventory().setItem(24, new ItemStack(Material.LAVA_BUCKET, 1));
    		p2.getInventory().setItem(16, new ItemStack(Material.COBBLE_WALL, 32));
    		p2.getInventory().setItem(25, new ItemStack(Material.WOOD, 32));
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
    		for (int i = 0; i < 19; i++) {
    			p2.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));    			
    		}
    		p2.updateInventory();
    		DamageUtil.allowAllDamage(p2.getName());
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
                	Bukkit.broadcastMessage("§aVencedor do Evento: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aVencedor do Evento: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aVencedor do Evento: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aVencedor do Evento: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aVencedor do Evento: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aVencedor do Evento: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aVencedor do Evento: " + EventoUtils.getEventoPlayersNames());
                	p.setHealth(20);
                	p.getWorld().strikeLightning(p.getLocation());
            		p.getWorld().strikeLightning(p.getLocation());
            		p.getWorld().strikeLightning(p.getLocation());
            		p.getWorld().strikeLightning(p.getLocation());
                }
                p.sendMessage("§cO evento foi finalizado automaticamente porque o tempo expirou.");
                p.sendMessage("§cO evento durou: §a20 minutos");
			}
            });;
            EventoUtils.resetEventoClass();
		}}, 24000L);
  
return false;
      };
      {

}
{

}
}

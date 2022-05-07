package net.helix.pvp.command;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
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
		  p.sendMessage("Um evento ja esta ocorrendo!");
		  return true;
	  }
	  p.sendMessage("§aIniciando evento Lava"); 
	  EventoUtils.evento = true;
	  EventoUtils.tp = true;
	  Bukkit.broadcastMessage("§cO evento Lava acabou de iniciar.");
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
				Bukkit.broadcastMessage("§cO evento Lava começara automaticamente em 4 minutos");
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
				Bukkit.broadcastMessage("§cO evento Lava começara automaticamente em 3 minutos");
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
				Bukkit.broadcastMessage("§cO evento Lava começara automaticamente em 2 minutos");
				Bukkit.broadcastMessage("§cPlayers no evento: " + EventoUtils.getEventoPlayers().size());
			}
		}, 3600L);
      Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				Bukkit.broadcastMessage("§cO evento Lava começara automaticamente em 1 minuto");
				Bukkit.broadcastMessage("§cPlayers no evento: " + EventoUtils.getEventoPlayers().size());
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
			 	Bukkit.broadcastMessage("§cTeleportando as pessoas para o Evento!");
			 	EventoType evento = EventoType.getEventoByName("Lava");
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
		EventoUtils.pvp = false;
     	EventoUtils.damage = true;
     	EventoUtils.build = false;
     	EventoUtils.tp = false;
     	EventoUtils.started = true;
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

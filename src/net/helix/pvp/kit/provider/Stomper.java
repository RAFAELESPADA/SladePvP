package net.helix.pvp.kit.provider;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;
import net.md_5.bungee.api.ChatColor;


public class Stomper extends KitHandler {
	
	@EventHandler(ignoreCancelled = true)
	public void onDamage(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getEntity();
		
		if (!KitManager.getPlayer(player.getName()).hasKit(this) 
				|| !event.getCause().equals(DamageCause.FALL)) {
			return;
		}

		/*     */       {
		/*  49 */         for (Entity ent : player.getNearbyEntities(5.0, 5.0, 5.0)) {
		/*  50 */           if ((ent instanceof Player))
		/*     */           {
		/*  52 */             Player plr = (Player)ent;
		if (KitManager.getPlayer(plr.getName()).hasKit(HelixKit.ANTISTOMPER)) {
			plr.sendMessage(ChatColor.RED + "Your antistomper protected you.");
			player.sendMessage(ChatColor.RED + "Your stomper kit failed because someone has antistomper nearby you.");
			event.setCancelled(true);
			return;
		}
		else if (!KitManager.getPlayer(plr.getName()).hasKit()) {
			return;
		}
		/*  58 */             if (plr.isSneaking())
		/*     */             {
		/*  60 */               plr.damage(6.0D, player);
		/*  61 */               plr.sendMessage(ChatColor.GRAY + "You get stomped by: " + ChatColor.AQUA + player.getName());
		/*     */             }
		/*     */             else
		/*     */             {
			plr.damage(event.getDamage(), player);
		    plr.damage(player.getFallDistance());
		/*  66 */               plr.sendMessage(ChatColor.GRAY + "You get stomped by: " + ChatColor.AQUA +  player.getName());
		/*     */             }
		/*     */           }
		/*     */         }
		/*  71 */         player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1.0F, 1.0F);
		/*  72 */         event.setDamage(4.0D);
		/*  73 */         return;
		/*     */       }
		/*     */     }
		/*     */   }
		
		

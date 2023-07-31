package net.helixpvp.kit2;


import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.scheduler.BukkitRunnable;

import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.HelixKit2;
import net.helix.pvp.kit.KitHandler2;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.md_5.bungee.api.ChatColor;


public class Stomper extends KitHandler2 {
	private ArrayList<String> fall = new ArrayList<String>();
	@EventHandler(ignoreCancelled = true)
	public void onDamage(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getEntity();
		
		if (!KitManager2.getPlayer(player.getName()).haskit2(this) 
				|| !event.getCause().equals(DamageCause.FALL)) {
			return;
		}

		/*     */       {
		/*  49 */         for (Entity ent : player.getNearbyEntities(5.0, 5.0, 5.0)) {
		/*  50 */           if ((ent instanceof Player))
		/*     */           {
		/*  52 */             Player plr = (Player)ent;
		if (KitManager.getPlayer(plr.getName()).hasKit(HelixKit.ANTISTOMPER) || KitManager2.getPlayer(plr.getName()).haskit2(HelixKit2.ANTISTOMPER)) {
			plr.sendMessage(ChatColor.RED + "Seu antistomper te protegeu.");
			player.sendMessage(ChatColor.RED + "Seu kit stomper falhou porque um inimigo estava de antistomper.");
			event.setCancelled(true);
			return;
		}
		if (fall.contains(player.getName())) {
			return;
		}
		else if (!KitManager.getPlayer(plr.getName()).hasKit()) {
			return;
		}
		/*  58 */             if (plr.isSneaking())
		/*     */             {
		/*  60 */               plr.damage(6.0D, player);
		/*  61 */               plr.sendMessage(ChatColor.GRAY + "Você foi stompado por: " + ChatColor.AQUA + player.getName());
		/*     */             }
		/*     */             else
		/*     */             {
			plr.damage(event.getDamage(), player);
		    plr.damage(player.getFallDistance());
		/*  66 */               plr.sendMessage(ChatColor.GRAY + "Você foi stompado por: " + ChatColor.AQUA +  player.getName());
		/*     */             }
		/*     */           }
		/*     */         }
		/*  71 */         player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1.0F, 1.0F);
		/*  72 */         event.setDamage(4.0D);
		/*  73 */         return;
		/*     */       }
		/*     */     }
	 @EventHandler
		public void RemoverDano(EntityDamageEvent e) 
		{
		   if (!(e.getEntity() instanceof Player)) {
	           return;
	       }
		   
			Player p = (Player) e.getEntity();
			if (!KitManager2.getPlayer(p.getName()).haskit2(this)) {
				return;
			}
			if (p.getLocation().getY() < 95) {
				return;
			}
			if (e.getCause() == EntityDamageEvent.DamageCause.FALL && this.fall.contains(p.getName())) 
			{
				this.fall.remove(p.getName());
				
			
			}
			else if(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK)
			{
				this.fall.add(p.getName());
				 Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance() , new BukkitRunnable() {
			         @Override
			         public void run() {
			        	fall.remove(p.getName());
			             }
			         }
			     , 120);
		
			}
		}
		/*     */   }
		
		


package net.helix.pvp.kit.provider;

<<<<<<< HEAD
import org.bukkit.GameMode;
=======
>>>>>>> f05bd700d45c8e03aaa6b381c9b7db29eecb9123
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

import net.helix.pvp.kit.Habilidade;
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

<<<<<<< HEAD
		/*     */       {
		/*  49 */         for (Entity ent : player.getNearbyEntities(5.0, 5.0, 5.0)) {
		/*  50 */           if ((ent instanceof Player))
		/*     */           {
		/*  52 */             Player plr = (Player)ent;
		if (Habilidade.getAbility(plr) == "AntiStomper") {
			plr.sendMessage(ChatColor.RED + "Seu kit antistomper te protegou do stomper.");
			player.sendMessage(ChatColor.RED + "O stomper não funcionou porque voce stompou alguem com o kit AntiStomper escolhido.");
			event.setCancelled(true);
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
		/*  66 */               plr.sendMessage(ChatColor.GRAY + "Voce foi stompado por: " + ChatColor.AQUA + player.getName());
		/*     */             }
		/*     */           }
		/*     */         }
		/*  71 */         player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1.0F, 1.0F);
		/*  72 */         event.setDamage(4.0D);
		/*  73 */         return;
		/*     */       }
		/*     */     }
		/*     */   }
		
		
=======
		if (event.getDamage() >= 4.0D) {
			player.getNearbyEntities(5.0, 5.0, 5.0).stream().filter(
					entity -> entity.getType().equals(EntityType.PLAYER) 
						&& KitManager.getPlayer(entity.getName()).hasKit()
			).forEach(entity -> {
				Player target = (Player) entity;
				if (Habilidade.getAbility(target) == "SteelHead") {
					player.playSound(player.getLocation(), Sound.ANVIL_LAND, 15.0f, 15.0f);
					target.playSound(player.getLocation(), Sound.ANVIL_LAND, 15.0f, 15.0f);
					player.sendMessage(ChatColor.AQUA + "Você stompou um NEO e nao surtiu efeito nele");
					target.sendMessage(ChatColor.AQUA + "Seu kit NEO o protegeu contra o stomper");
					return;
				}
				else if (player.getFallDistance() >= 20 && !target.isSneaking()) {
					target.setHealth(0);
					player.playSound(player.getLocation(), Sound.ANVIL_LAND, 15.0f, 15.0f);
					target.playSound(player.getLocation(), Sound.ANVIL_LAND, 15.0f, 15.0f);
					target.sendMessage(ChatColor.RED + "Você foi stompado por " + player.getName());
				}
				else if (player.getItemInHand().equals(Material.STONE_SWORD) || player.getItemInHand().equals(Material.IRON_SWORD)) {
					target.damage(target.isSneaking() ? 4.0D : event.getDamage() * player.getFallDistance() * 5.3, player);
					target.sendMessage(ChatColor.RED + "Você foi stompado por " + player.getName());
				}
				target.damage(target.isSneaking() ? 4.0D : event.getDamage() * player.getFallDistance() * 1.7, player);
				player.playSound(player.getLocation(), Sound.ANVIL_LAND, 15.0f, 15.0f);
				target.playSound(player.getLocation(), Sound.ANVIL_LAND, 15.0f, 15.0f);
				target.sendMessage(ChatColor.AQUA + "Você foi stompado por " + player.getName());
			});
		}
		player.playSound(player.getLocation(), Sound.ANVIL_LAND, 15.0f, 15.0f);
		event.setDamage(3.0D);
	}
}

>>>>>>> f05bd700d45c8e03aaa6b381c9b7db29eecb9123

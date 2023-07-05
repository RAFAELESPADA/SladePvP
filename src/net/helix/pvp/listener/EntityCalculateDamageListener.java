package net.helix.pvp.listener;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.helix.pvp.HelixPvP;
import net.md_5.bungee.api.ChatColor;

public class EntityCalculateDamageListener implements Listener {
	
	@EventHandler
	public void onDano(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		Player t = (Player) e.getDamager();
		if (t.getItemInHand().getType() == Material.STONE_SWORD) {
			e.setDamage(e.getDamage() - 3.2);
		}else if (t.getItemInHand().getType() == Material.STONE_SWORD) {
			if (t.getItemInHand().getEnchantments() == Enchantment.DAMAGE_ALL) {
				e.setDamage(e.getDamage() - 3.2 + (0.6 * t.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)));
			} else if (t.getItemInHand().getEnchantments() == Enchantment.DAMAGE_ALL && t.getFallDistance() > 0) {
				e.setDamage(e.getDamage() - 2.7 + (0.7 * t.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)));
			}
		}else if (t.getItemInHand().getType() == Material.IRON_SWORD) {
				e.setDamage(e.getDamage() - 2.0);
				if (t.getItemInHand().getEnchantments() == Enchantment.DAMAGE_ALL) {
					e.setDamage(e.getDamage() - 2.0 + (0.6 * t.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)));
				}
		} else if (t.getItemInHand().getEnchantments() == Enchantment.DAMAGE_ALL && t.getFallDistance() > 0) {
			e.setDamage(e.getDamage() - 2.0 + (0.7 * t.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)));
			}
	else if (t.getItemInHand().getType() == Material.IRON_AXE) {
		e.setDamage(e.getDamage() - 2.0);
		if (t.getItemInHand().getEnchantments() == Enchantment.DAMAGE_ALL) {
			e.setDamage(e.getDamage() - 2.0 + (0.6 * t.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)));
		}
} else if (t.getItemInHand().getEnchantments() == Enchantment.DAMAGE_ALL && t.getFallDistance() > 0) {
	e.setDamage(e.getDamage() - 2.0 + (0.7 * t.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)));
	}
	else if (t.getItemInHand().getType() == Material.DIAMOND_SWORD) {
		e.setDamage(e.getDamage() - 1.0);
		if (t.getItemInHand().getEnchantments() == Enchantment.DAMAGE_ALL) {
			e.setDamage(e.getDamage() - (1.0) * 0.6 * t.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL));
		}
	} else if (t.getItemInHand().getEnchantments() == Enchantment.DAMAGE_ALL && t.getFallDistance() > 0) {
		e.setDamage(e.getDamage() - 1.0 + (0.7 * t.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)));
		}
		if (t.getItemInHand().hasItemMeta() && t.getItemInHand().getType() == Material.QUARTZ) {
			e.setDamage(3.7);
		}
	}
	@EventHandler
	public void onEspada(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getDamager();
		Player player = (Player) e.getDamager();
		Player player2 = (Player) e.getEntity();
		if (p.getItemInHand().getType() == Material.STONE_SWORD) {
			p.getItemInHand().setDurability((short)0);
			p.updateInventory();
		}
		if (e.isCancelled()) {
			return;
		}
          
		if (p.getMaximumNoDamageTicks() > 35) {
			p.setMaximumNoDamageTicks(20);
		}
          }


	 


	@EventHandler
	public void otnShot(EntityDamageByEntityEvent e) {
		
			
			if (e.getEntity() instanceof Player && e.getDamager() instanceof Arrow) {
				
				Player damagedPlayer = (Player) e.getEntity();
				Arrow arrow = (Arrow) e.getDamager();
				
				if (arrow.getShooter() != null && arrow.getShooter() instanceof Player) {
					
					Player shooter = (Player) arrow.getShooter();
					
					// ARROW HEALTH MESSAGE
					
					if (damagedPlayer.getName() != shooter.getName()) {
						
						new BukkitRunnable() {
							
							@Override
							public void run() {
								
								double health = Math.round(damagedPlayer.getHealth() * 10.0) / 10.0;
								
									if (health != 20.0) {	
										
										shooter.sendMessage(damagedPlayer.getName() + ChatColor.YELLOW + " is with " + health + " of line");									
									}						
																}							
							}
							
						.runTaskLater(HelixPvP.getInstance(), 2L);
						
					}
				}
			}
		}
	
	
	
	
	 @EventHandler
	    public void Quebrar(final PlayerMoveEvent e) {
	        final Player p = e.getPlayer();
	        final ItemStack item1 = p.getItemInHand();
	        final Material material1 = Material.getMaterial(item1.getTypeId());
	        if (!material1.isBlock() && (!(material1 == Material.LEATHER_LEGGINGS)) && (!(material1 == Material.LEATHER_HELMET)) && (!(material1 == Material.IRON_HELMET)) && (!(material1 == Material.IRON_CHESTPLATE)) && (!(material1 == Material.IRON_BOOTS)) && (!(material1 == Material.IRON_LEGGINGS)) && (!(material1 == Material.LEATHER_BOOTS)) && (!(material1 == Material.GOLD_CHESTPLATE	        	)) && (!(material1 == Material.GOLD_HELMET)) && (!(material1 == Material.CHAINMAIL_CHESTPLATE)) && (!(material1 == Material.CHAINMAIL_HELMET)) && (!(material1 == Material.CHAINMAIL_BOOTS)) && (!(material1 == Material.CHAINMAIL_LEGGINGS)) && material1.getMaxDurability() >= 1 && item1.getDurability() != 0) {
	            p.getItemInHand().setDurability((short)0);
	            p.updateInventory();
	        }
	        
	        }
	 }	    

                            
                        
                

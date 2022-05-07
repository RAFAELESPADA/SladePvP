package net.helix.pvp.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class EntityCalculateDamageListener implements Listener {
	
	@EventHandler
	public void onDano(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		Player t = (Player) e.getDamager();
		if (t.getItemInHand().getType() == Material.STONE_SWORD) {
			e.setDamage(e.getDamage() - 2.0);
		}else if (t.getItemInHand().getType() == Material.STONE_SWORD) {
			if (t.getItemInHand().getEnchantments() == Enchantment.DAMAGE_ALL) {
				e.setDamage(e.getDamage() - 2.0 + (0.6 * t.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)));
			} else if (t.getItemInHand().getEnchantments() == Enchantment.DAMAGE_ALL && t.getFallDistance() > 0) {
				e.setDamage(e.getDamage() - 1.5 + (0.7 * t.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)));
			}
		}else if (t.getItemInHand().getType() == Material.IRON_SWORD) {
				e.setDamage(e.getDamage() - 1.5);
				if (t.getItemInHand().getEnchantments() == Enchantment.DAMAGE_ALL) {
					e.setDamage(e.getDamage() - 1.5 + (0.6 * t.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)));
				}
		} else if (t.getItemInHand().getEnchantments() == Enchantment.DAMAGE_ALL && t.getFallDistance() > 0) {
			e.setDamage(e.getDamage() - 1.5 + (0.7 * t.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)));
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
			e.setDamage(4.5);
		}
	}
	@EventHandler
	public void onEspada(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getDamager();
		if (p.getItemInHand().getType() == Material.STONE_SWORD) {
			p.getItemInHand().setDurability((short)0);
			p.updateInventory();
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

                            
                        
                
    

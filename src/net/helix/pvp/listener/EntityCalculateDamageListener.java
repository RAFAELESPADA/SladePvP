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
			e.setDamage(3.3D);
		}else if (t.getItemInHand().getType() == Material.STONE_SWORD) {
			if (t.getItemInHand().getEnchantments() == Enchantment.DAMAGE_ALL) {
				e.setDamage(3.9D);
			}
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
	        if (!material1.isBlock() && material1.getMaxDurability() >= 1 && item1.getDurability() != 0) {
	            p.getItemInHand().setDurability((short)0);
	            p.updateInventory();
	        }
	        final PlayerInventory item2 = p.getInventory();
	        if (p.getInventory().getHelmet() != null) {
	            final Material material2 = Material.getMaterial(item2.getHelmet().getTypeId());
	            if (!material2.isBlock() && material2.getMaxDurability() >= 1 && item2.getHelmet().getDurability() != 0) {
	                p.getInventory().getHelmet().setDurability((short)1);
	            }
	        }
	        if (p.getInventory().getChestplate() != null) {
	            final Material material2 = Material.getMaterial(item2.getChestplate().getTypeId());
	            if (!material2.isBlock() && material2.getMaxDurability() >= 1 && item2.getChestplate().getDurability() != 0) {
	                p.getInventory().getChestplate().setDurability((short)1);
	            }
	        }
	        if (p.getInventory().getLeggings() != null) {
	            final Material material2 = Material.getMaterial(item2.getLeggings().getTypeId());
	            if (!material2.isBlock() && material2.getMaxDurability() >= 1 && item2.getLeggings().getDurability() != 0) {
	                p.getInventory().getLeggings().setDurability((short)1);
	            }
	        }
	        if (p.getInventory().getBoots() != null) {
	            final Material material2 = Material.getMaterial(item2.getBoots().getTypeId());
	            if (!material2.isBlock() && material2.getMaxDurability() >= 1 && item2.getBoots().getDurability() != 0) {
	                p.getInventory().getBoots().setDurability((short)1);
	            }
	        }
	 }	    
}
                            
                        
                
    

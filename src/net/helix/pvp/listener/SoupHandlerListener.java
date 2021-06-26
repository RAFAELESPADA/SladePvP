package net.helix.pvp.listener;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SoupHandlerListener implements Listener {
	
	@EventHandler
	public void onTomarSopa(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
			if (p.getItemInHand().getType() == Material.MUSHROOM_SOUP) {
				if (!net.helix.pvp.kit.All.q.contains(p.getName())) {
					if ((p.getHealth() < 20.0D) && (p.getItemInHand().getType() == Material.MUSHROOM_SOUP)) {
						e.setCancelled(true);
						
						
						p.setHealth(p.getHealth() + 7 >= 20.0D ? 20.0D : p.getHealth() + 7);
						p.setFoodLevel(20);
						p.getWorld().playEffect(p.getLocation().add(0.0D, 1.5D, 0.0D), Effect.HEART, 7);
						p.setItemInHand(new ItemStack(Material.BOWL));
						p.updateInventory();
				}
			}else {
				if ((p.getHealth() < 20.0D) && (p.getItemInHand().getType() == Material.MUSHROOM_SOUP)) {
					e.setCancelled(true);
					
					p.setHealth(p.getHealth() + 7 >= 20.0D ? 20.0D : p.getHealth() + 7);
					p.setFoodLevel(20);
					p.getWorld().playEffect(p.getLocation().add(0.0D, 1.5D, 0.0D), Effect.HEART, 7);
					p.setItemInHand(new ItemStack(Material.AIR));
					p.updateInventory();
				}
			}
			}
		}
	}
				@EventHandler
				public void onTomarPOT(PlayerInteractEvent e) {
					Player p = e.getPlayer();

					
					if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
						if (p.getItemInHand().getType() == Material.POTION) {
								if ((p.getHealth() < 20.0D) && (p.getItemInHand().getType() == Material.POTION)) {
									e.setCancelled(true);
									
									p.playSound(p.getLocation(), Sound.DRINK, 1.0F, 1.0F);
									p.setHealth(p.getHealth() + 7 >= 20.0D ? 20.0D : p.getHealth() + 7);
									p.setFoodLevel(20);
									p.getWorld().playEffect(p.getLocation().add(0.0D, 1.5D, 0.0D), Effect.HEART, 7);
									p.setItemInHand(new ItemStack(Material.AIR));
									p.updateInventory();
							}
			}
		}
	}

}

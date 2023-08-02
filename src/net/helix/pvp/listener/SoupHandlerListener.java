package net.helix.pvp.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.HelixKit2;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.helix.pvp.kit.provider.HelixActionBar;

public class SoupHandlerListener implements Listener {
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onTomarSopa(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
			if (p.getItemInHand().getType() == Material.MUSHROOM_SOUP) {
				if ((p.getHealth() < 20.0D)) {
					e.setCancelled(true);
					p.setHealth(p.getHealth() + 7 >= 20.0D ? 20.0D : p.getHealth() + 7);
					p.setFoodLevel(20);
					p.setItemInHand(KitManager.getPlayer(p.getName()).hasKit(HelixKit.QUICKDROPPER) || KitManager2.getPlayer(p.getName()).haskit2(HelixKit2.QUICKDROPPER) ? new ItemStack(Material.AIR) : new ItemStack(Material.BOWL));
					HelixActionBar.send(p, "§c+" + (p.getHealth() + 7 <= 20.0D ? 3.5 : 3.5) + " §4§l❤️");
					p.updateInventory();
				}
			}
			}
		}
		
	


public static void setOffhandItem(Player p, ItemStack item) {
		
		if (versionToNumber() == 18) {
			p.setItemInHand(item);
		} else if (versionToNumber() > 18) {
			p.setItemInHand(item);
		} else {
			p.setItemInHand(item);
		}
		
	}
public static int versionToNumber() {

	String version = Bukkit.getVersion();

	if (version.contains("1.8")) {
		return 18;
	} else if (version.contains("1.9")) {
		return 19;
	} else if (version.contains("1.10")) {
		return 110;
	} else if (version.contains("1.11")) {
		return 111;
	} else if (version.contains("1.12")) {
		return 112;
	} else if (version.contains("1.13")) {
		return 113;
	} else if (version.contains("1.14")) {
		return 114;
	} else if (version.contains("1.15")) {
		return 115;
	} else if (version.contains("1.16")) {
		return 116;
	} else if (version.contains("1.17")) {
		return 117;
	} else if (version.contains("1.18")) {
		return 118;
	}
		return 500;
		
	}
}

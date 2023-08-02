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

	
	@EventHandler(priority = EventPriority.LOW)
    public void onPlayerInteract(final PlayerInteractEvent event) {
		if (event.getItem() == null) {
			return;
		}
        if (event.getItem().getType() != Material.MUSHROOM_SOUP) {
            return;
        }
        
        event.setCancelled(true);
        final Player player = event.getPlayer();
        final double beforeHealth = player.getHealth();
        if (beforeHealth < player.getMaxHealth()) {
            if (beforeHealth + 7.0 > player.getMaxHealth()) {
                player.setHealth(player.getMaxHealth());
                if (player.getFoodLevel() < 20) {
                    final int i = this.toInt(beforeHealth) + 7 - this.toInt(player.getMaxHealth());
                    player.setFoodLevel(Math.min(player.getFoodLevel() + i, 20));
                    player.setSaturation(3.0f);
             
                }
            }
            else {
                player.setHealth(player.getHealth() + 7.0);
            }
            HelixActionBar.send(player, "§c+3,5 §4\u2764");   
            player.setItemInHand(KitManager.getPlayer(player.getName()).hasKit(HelixKit.QUICKDROPPER) || (KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.QUICKDROPPER)) ?  new ItemStack(Material.AIR) : new ItemStack(Material.BOWL));
        }
        else if (player.getFoodLevel() < 20) {
            event.getPlayer().setFoodLevel(event.getPlayer().getFoodLevel() + 7);
            player.setSaturation(3.0f);
            player.setItemInHand(new ItemStack(Material.BOWL));
        }
        
        
        }

       
		
private int toInt(final Double d) {
    return d.intValue();
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

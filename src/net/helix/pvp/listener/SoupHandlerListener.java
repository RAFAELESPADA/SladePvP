package net.helix.pvp.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.HelixKit2;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.helix.pvp.kit.provider.HelixActionBar;

public class SoupHandlerListener implements Listener {

	

        
        
	@EventHandler(priority = EventPriority.MONITOR)
    private void onPlayerInteract(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        HelixPlayer playerData = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
        if (playerData == null) {
        	player.kickPlayer(ChatColor.RED + "UM ERRO OCORREU! RELOGUE PARA ARRUMAR");
        	return;
        }
        if (event.hasItem()) {
            if (event.getMaterial() == Material.MUSHROOM_SOUP && player.getHealth() != player.getMaxHealth()) {
            	if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                event.setCancelled(true);
            	player.setHealth((player.getHealth() < player.getMaxHealth() - 7.0) ? (player.getHealth() + 7.0) : player.getMaxHealth());
                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.QUICKDROPPER) || (KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.QUICKDROPPER))) {
                    player.setItemInHand(new ItemStack(Material.AIR));
                    player.getWorld().dropItem(player.getEyeLocation().multiply(3), new ItemStack(Material.BOWL));
                    }   else {
                    	player.getItemInHand().setType(Material.BOWL);	
                    }
                player.updateInventory();
                HelixActionBar.send(player, "§c+3,5 §4\u2764");
            }
            }
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

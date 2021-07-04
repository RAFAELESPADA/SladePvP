package net.helix.pvp.inventory.listener;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.format.HelixDecimalFormat;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.inventory.ShopInventory;
import net.helix.pvp.kit.HelixKit;

public class BuyKitListener implements Listener {
	
	@EventHandler
	public void onInvClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(ShopInventory.getInventoryName())) {
			return;
		}
		
		event.setCancelled(true);
		if (!ItemBuilder.has(event.getCurrentItem(), "shop-kit")) {
			return;
		}
		
		HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
				.getPlayer(player.getName());
		String kitName = ItemBuilder.getString(event.getCurrentItem(), "shop-kit");
		
		
		HelixKit.findKit(kitName).ifPresent(kit -> {
			player.closeInventory();
			
			if (helixPlayer.getPvp().getCoins() < kit.getPrice()) {
				int remaingCoins = kit.getPrice() - helixPlayer.getPvp().getCoins();
				player.sendMessage("§cVocê precisa de " + HelixDecimalFormat.format(remaingCoins) + " coins para comprar este kit.");
				return;
			}
			
			helixPlayer.getPvp().setCoins(helixPlayer.getPvp().getCoins() - kit.getPrice());
			HelixBukkit.getInstance().getPlayerManager().getData().save(helixPlayer);
			
			player.playSound(player.getLocation(), Sound.LEVEL_UP, 10.0f, 10.0f);
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set helix.kit." + kit.toString().toLowerCase() + " pvp");
			
			Bukkit.broadcastMessage(helixPlayer.getTag().getColor() + player.getName() + " §fcomprou o kit §d" + kit.getName() + "§f!");
			player.sendMessage("§aVocê comprou o kit §f" + kit.getName() + " §apor §f" + kit.getPrice() + " coins§a!");
		});
	}

}

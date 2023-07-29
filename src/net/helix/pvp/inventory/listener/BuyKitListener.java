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
import net.helix.pvp.inventory.ShopInventory2;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.HelixKit2;

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
			if (player.hasPermission("kombo.kit." + kit.getName())) {
				player.playSound(player.getLocation(), Sound.NOTE_BASS_DRUM, 10.0f, 10.0f);
				player.sendMessage("§cVocê já possui este kit!");
				return;
			}
			if (helixPlayer.getPvp().getCoins() < kit.getPrice()) {
				int remaingCoins = kit.getPrice() - helixPlayer.getPvp().getCoins();
				player.playSound(player.getLocation(), Sound.NOTE_BASS_DRUM, 10.0f, 10.0f);
				player.sendMessage("§cVocê precisa de " + HelixDecimalFormat.format(remaingCoins) + " coins para comprar esse kit primário");
				return;
			}
			
			helixPlayer.getPvp().removeCoins(kit.getPrice());
			HelixBukkit.getInstance().getPlayerManager().getController().save(helixPlayer);
			
			player.playSound(player.getLocation(), Sound.LEVEL_UP, 10.0f, 10.0f);
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set kombo.kit." + kit.toString().toLowerCase() + " pvp");

			player.sendMessage("§aVocê comprou o kit" + kit.getName() + " por " + kit.getPrice() + " coins");
		});
	}

@EventHandler
public void onInvClick2(InventoryClickEvent event) {
	Player player = (Player) event.getWhoClicked();
	
	if (!event.getView().getTitle().equals(ShopInventory2.getInventoryName())) {
		return;
	}
	
	event.setCancelled(true);
	if (!ItemBuilder.has(event.getCurrentItem(), "shop-kit")) {
		return;
	}
	
	HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
			.getPlayer(player.getName());
	String kitName = ItemBuilder.getString(event.getCurrentItem(), "shop-kit");
	
	
	HelixKit2.findKit(kitName).ifPresent(kit -> {
		player.closeInventory();
		if (player.hasPermission("kombo.kit2." + kit.getName())) {
			player.playSound(player.getLocation(), Sound.NOTE_BASS_DRUM, 10.0f, 10.0f);
			player.sendMessage("§cVocê já possui este kit!");
			return;
		}
		if (helixPlayer.getPvp().getCoins() < kit.getPrice()) {
			int remaingCoins = kit.getPrice() - helixPlayer.getPvp().getCoins();
			player.sendMessage("§cVocê precisa de " + HelixDecimalFormat.format(remaingCoins) + " coins para comprar esse kit secundário");
			player.playSound(player.getLocation(), Sound.NOTE_BASS_DRUM, 10.0f, 10.0f);
			return;
		}
		helixPlayer.getPvp().removeCoins(kit.getPrice());
		HelixBukkit.getInstance().getPlayerManager().getController().save(helixPlayer);
		
		player.playSound(player.getLocation(), Sound.LEVEL_UP, 10.0f, 10.0f);
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set kombo.kit2." + kit.toString().toLowerCase() + " pvp");

		player.sendMessage("§aVocê comprou o kit " + kit.getName() + " por " + kit.getPrice() + " coins");
	});
}
}

package net.helix.pvp.listener;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.core.bukkit.util.BuildUtil;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.inventory.KitsInventory;
import net.helix.pvp.inventory.KitsInventory2;
import net.helix.pvp.inventory.ShopGUI;
import net.helix.pvp.inventory.StatusGUI;
import net.helix.pvp.inventory.WarpsInventory;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.provider.EnderMageReal;
import net.helix.pvp.warp.HelixWarp;


public class OpenSpawnItemsListener implements Listener {
	
	

	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (!event.hasItem() || !ItemBuilder.has(event.getItem(), "spawn-item")) {
			return;
		}
		switch (ItemBuilder.getString(event.getItem(), "spawn-item")) {
			case "kits":
				KitsInventory.open(player);
				break;
			case "kits2":
				KitsInventory2.open(player);
				break;
			case "shop":
				ShopGUI.open(player);
				break;
			case "status":
				StatusGUI.openGUI(player, player);
				break;
			case "1v1":
				WarpsInventory.open(player);
				break;
		}
	}
	
@EventHandler
public void onInteractt(PlayerPickupItemEvent event) {
	Player player = event.getPlayer();
	if (HelixWarp.SPAWN.hasPlayer(player.getName()) && EnderMageReal.isSpawn(player.getLocation()) && player.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura")) {
	event.setCancelled(true);
	}
}
	@EventHandler
	public void onInteracttf(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		if (HelixWarp.SPAWN.hasPlayer(player.getName()) && EnderMageReal.isSpawn(player.getLocation()) && player.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura")) {
		event.setCancelled(true);
		}
	}
		@EventHandler
		public void onInteracttc(ItemSpawnEvent event) {
			Item player = event.getEntity();
			if (EnderMageReal.isSpawn(player.getLocation()) && player.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura")) {
			event.setCancelled(true);
			}
}}



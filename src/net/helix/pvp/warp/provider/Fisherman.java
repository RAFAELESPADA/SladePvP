package net.helix.pvp.warp.provider;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.helix.pvp.warp.HelixWarp;
import net.helix.pvp.warp.WarpHandle;

public class Fisherman extends WarpHandle {
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		
		player.getInventory().setItem(0, new ItemBuilder("§fVara", Material.FISHING_ROD)
				.nbt("cancel-drop").nbt("fishermanwarp")
				.toStack()
		);
		KitManager.getPlayer(player.getName()).removeKit();
		KitManager2.getPlayer(player.getName()).removekit2();
	}

@EventHandler
public void onFish(PlayerFishEvent event) {
	if (!(event.getCaught() instanceof Player) 
			|| !HelixWarp.FISHERMAN.hasPlayer(event.getPlayer().getName()) 
			|| !ItemBuilder.has(event.getPlayer().getItemInHand(), "fishermanwarp")) {
	
	
		return;
	}
	
	Entity caught = event.getCaught();
	caught.teleport(event.getPlayer());
}
}
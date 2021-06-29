package net.helix.pvp.util;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.api.HelixTitle;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.warp.HelixWarp;

public class SpawnUtil {
	
	public static void apply(Player player) {
		Location spawnLocation = HelixBukkit.getInstance().getWarpManager().findWarp("spawn").isPresent() ?
				HelixBukkit.getInstance().getWarpManager().findWarp("spawn").get().getLocation() : player.getWorld().getSpawnLocation();
		player.teleport(spawnLocation);
		
		DamageUtil.denyAllDamage(player.getName());
		HelixWarp.removeHandle(player.getName());
		KitManager.getPlayer(player.getName()).removeKit();
		HelixTitle.clearTitle(player);
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		player.setGameMode(GameMode.ADVENTURE);
		player.setMaxHealth(20);
		player.setHealth(player.getMaxHealth());
		player.getActivePotionEffects().forEach(potion -> player.removePotionEffect(potion.getType()));
		player.setFireTicks(0);
		player.setFoodLevel(20);
		player.setAllowFlight(player.hasPermission("helix.cmd.fly"));
		player.setFlying(player.hasPermission("helix.cmd.fly"));
		
		player.getInventory().setItem(0, new ItemBuilder("§6Kits", Material.ENDER_CHEST)
				.nbt("spawn-item", "kits")
				.nbt("cancel-drop")
				.nbt("cancel-click")
				.toStack()
		);
		
		player.getInventory().setItem(1, new ItemBuilder("§bWarps", Material.COMPASS)
				.nbt("spawn-item", "warps")
				.nbt("cancel-drop")
				.nbt("cancel-click")
				.toStack()
		);
		
		player.getInventory().setItem(8, new ItemBuilder("§eLoja", Material.GOLD_INGOT)
				.nbt("spawn-item", "shop")
				.nbt("cancel-drop")
				.nbt("cancel-click")
				.toStack()
		);
	}
}

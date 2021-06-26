package net.helix.pvp.warp;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public abstract class WarpHandle {

	public void execute(Player player) {
		player.setGameMode(GameMode.ADVENTURE);
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		player.setAllowFlight(false);
		player.setFlying(false);
		player.getInventory().setHeldItemSlot(0);
		player.getActivePotionEffects().forEach(potion -> player.removePotionEffect(potion.getType()));
	}
}

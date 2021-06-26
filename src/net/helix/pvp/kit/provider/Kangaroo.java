package net.helix.pvp.kit.provider;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;

public class Kangaroo extends KitHandler {
	
	private final static List<String> cooldown = new ArrayList<>();
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		
		player.getInventory().setItem(1, new ItemBuilder("§aPular!", Material.FIREWORK)
				.nbt("kit-handler", "kangaroo")
				.nbt("cancel-drop")
				.toStack()
		);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		if (!KitManager.getPlayer(player.getName()).hasKit(this)
				|| !event.hasItem() || !ItemBuilder.has(event.getItem(), "kit-handler", "kangaroo")
				|| cooldown.contains(player.getName())) {
			return;
		}
		event.setCancelled(true);
		
		cooldown.add(player.getName());
		player.setFallDistance(1.0F);
		Vector vector = player.getEyeLocation().getDirection();
		
		if (player.isSneaking()) {
			vector.multiply(2.3F);
			vector.setY(0.5D);
		}else {
			vector.multiply(0.5F);
			vector.setY(1.0D);
		}
		player.setVelocity(vector);
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Block block = player.getLocation().getBlock();
		
		if (!KitManager.getPlayer(player.getName()).hasKit(this) 
				|| !cooldown.contains(player.getName())) {
			return;
		}
		
		if (!block.getRelative(BlockFace.DOWN).getType().equals(Material.AIR)) {
			cooldown.remove(player.getName());
		}
	}

}

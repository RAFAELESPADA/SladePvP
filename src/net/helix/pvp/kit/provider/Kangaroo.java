package net.helix.pvp.kit.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.core.util.HelixCooldown;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;

public class Kangaroo extends KitHandler {
	
	private final static List<String> cooldown = new ArrayList<>();
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		
		player.getInventory().setItem(1, new ItemBuilder("�aPular!", Material.FIREWORK)
				.nbt("kit-handler", "kangaroo")
				.nbt("cancel-drop")
				.toStack()
		);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		if (!KitManager.getPlayer(player.getName()).hasKit(this)
				|| !event.hasItem() || !ItemBuilder.has(event.getItem(), "kit-handler", "kangaroo")) {
			return;
		}
		event.setCancelled(true);
		
		if (HelixCooldown.inCooldown(player.getName(), "kangaroo-hit")) {
			player.sendMessage("�cVoc� recebeu dano recentemente, aguarde " + HelixCooldown.getTime(player.getName(), "kangaroo-hit") + "s para utilizar o Kangaroo novamente.");
			return;
		}
		
		if (!cooldown.contains(player.getName())) {
			cooldown.add(player.getName());
			Vector vector = player.getEyeLocation().getDirection();
			
			if (player.isSneaking()) {
				vector.multiply(2.7F);
				vector.setY(0.5D);
			}else {
				vector.multiply(0.5F);
				vector.setY(1.0D);
			}
			player.setFallDistance(-1.0F);
			player.setVelocity(vector);
		}
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
	
	@EventHandler
	public void onFallDamage(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getEntity();
		
		if (!KitManager.getPlayer(player.getName()).hasKit(this) 
				|| !event.getCause().equals(DamageCause.FALL)) {
			return;
		}
		
		HelixCooldown.create(player.getName(), "kangaroo-hit", TimeUnit.SECONDS, 5);
		if (event.getDamage() > 7.0D) {
			event.setDamage(7.0D);
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player victim = event.getEntity();
		
		cooldown.remove(victim.getName());
		HelixCooldown.delete(victim.getName(), "kangaroo-hit");
	}
}

package net.helix.pvp.kit.provider;

import java.util.concurrent.TimeUnit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import net.helix.core.util.HelixCooldown;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;

public class Thor extends KitHandler {
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		
		player.getInventory().setItem(1, new ItemBuilder("§eCaboom!", Material.GOLD_AXE)
				.nbt("cancel-drop")
				.nbt("kit-handler", "thor")
				.toStack()
		);
	}
	 @EventHandler
	 /*     */   public void OnBlock(BlockIgniteEvent e)
	 /*     */   {
	 /*  95 */     if (e.getCause() == BlockIgniteEvent.IgniteCause.LIGHTNING) {
	 /*  96 */       e.setCancelled(true);
	 }
	 /*     */   }
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		if (!KitManager.getPlayer(player.getName()).hasKit(this) 
				|| !event.hasItem() || !ItemBuilder.has(event.getItem(), "kit-handler", "thor") 
				|| event.getClickedBlock() == null || event.getClickedBlock().getType().equals(Material.AIR)) {
			return;
		}
		event.setCancelled(true);
		
		if (HelixCooldown.inCooldown(player.getName(), "thor")) {
			player.sendMessage("§cAguarde " + HelixCooldown.getTime(player.getName(), "thor") + "s para utilizar este kit novamente.");
			return;
		}
		
		HelixCooldown.create(player.getName(), "thor", TimeUnit.SECONDS, 10);
		player.getWorld().strikeLightning(event.getClickedBlock().getLocation());
		
	}
	
	@EventHandler
	public void onBlockExplode(BlockExplodeEvent event) {
		event.setCancelled(true);
	}
}

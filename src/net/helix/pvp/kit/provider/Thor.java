package net.helix.pvp.kit.provider;

import java.util.concurrent.TimeUnit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
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
		if (inCooldown(player) && KitManager.getPlayer(player.getName()).hasKit(this)) {
			sendMessageCooldown(player);
			return;
		}
		if (!KitManager.getPlayer(player.getName()).hasKit(this) 
				|| !event.hasItem() || !ItemBuilder.has(event.getItem(), "kit-handler", "thor") 
				|| event.getClickedBlock() == null || event.getClickedBlock().getType().equals(Material.AIR)) {
			return;
		}
		else if (player.getLocation().getY() < 191 && player.getLocation().getY() > 170 && player.getLocation().getX() > -1230 && player.getLocation().getX() < -1200) {
			player.sendMessage("§cPule do Spawn para usar o kit Thor");
			return;
		}
		
		event.setCancelled(true);
		
		
		
		addCooldown(event.getPlayer(), 5);
		player.getWorld().strikeLightning(event.getClickedBlock().getLocation());
		player.getWorld().strikeLightning(event.getClickedBlock().getLocation());
	}
	 @EventHandler
	 /*     */   public void OnBlockBB(EntityDamageEvent e)
	 /*     */   {
	 /* 110 */     if (!(e.getEntity() instanceof Player)) {
	 /* 111 */       return;
	 /*     */     }
	 /* 113 */     Player p = (Player)e.getEntity();
	 /* 114 */     if ((p.getLocation().getBlockY() > 172) && p.getLocation().getBlockY() < 179 && p.getLocation().getX() > -1300 && p.getLocation().getBlockX() < -1100) {
	 /* 115 */       e.setCancelled(true);
	 for (PotionEffect effect : p.getActivePotionEffects()) {
		 /*  70 */         p.removePotionEffect(effect.getType());
		 /*     */       }
	               }
	 /*     */   }
	 /*     */ 
	
	@EventHandler
	public void onBlockExplode(BlockExplodeEvent event) {
		event.setCancelled(true);
	}
}

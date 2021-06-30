package net.helix.pvp.warp.provider;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemFlag;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.util.DamageUtil;
import net.helix.pvp.warp.HelixWarp;
import net.helix.pvp.warp.WarpHandle;

public class Knockback extends WarpHandle {
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		DamageUtil.allowAllDamage(player.getName());
		
		player.getInventory().setHeldItemSlot(4);
		player.getInventory().setItem(4, new ItemBuilder("§aVarinha Mágica", Material.STICK)
				.lore("§cEste item não causa dano.")
				.addEnchant(Enchantment.KNOCKBACK, 4)
				.addFlags(ItemFlag.HIDE_ENCHANTS)
				.nbt("cancel-drop")
				.toStack()
		);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onEntityDamage(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player) 
				|| (!(event.getDamager() instanceof Player))) {
			return;
		}
		Player victim = (Player) event.getEntity();
		Player damager = (Player) event.getDamager();
		HelixWarp.findWarp("knockback").ifPresent(warp -> {
			if (warp.hasPlayer(victim.getName()) && warp.hasPlayer(damager.getName())) {
				event.setDamage(0L);
				victim.setHealth(victim.getMaxHealth());
			}
		});
	}
}

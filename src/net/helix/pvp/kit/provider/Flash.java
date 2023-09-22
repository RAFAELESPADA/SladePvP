package net.helix.pvp.kit.provider;

import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;

public class Flash extends KitHandler {

	   @Override
	    public void execute(Player player) {
	        super.execute(player);

	        player.getInventory().setItem(1, new ItemBuilder("�aFlash!", Material.REDSTONE_TORCH_ON)
	                .addEnchant(Enchantment.KNOCKBACK, 2).addFlags(ItemFlag.HIDE_ENCHANTS)
	                .nbt("cancel-drop").nbt("kit-handler", "flash")
	                .toStack());
	   }
@SuppressWarnings("deprecation")
@EventHandler(priority = EventPriority.LOWEST)
public void onInteract(PlayerInteractEvent event) {
	Player p = event.getPlayer();
	if (p.getNearbyEntities(5, 5, 5).size() > 0 && p.getNearbyEntities(5, 5, 5) instanceof Player) {
		p.sendMessage("§eNão use o flash aqui!");
		return;
	}
	 ItemStack sopa = new ItemStack(Material.REDSTONE_TORCH_ON);
	if (!KitManager.getPlayer(p.getName()).hasKit(HelixKit.FLASH)) return; {
		if (!event.hasItem() || !ItemBuilder.has(event.getItem(), "kit-handler", "flash")) return; 
		if (!event.getAction().toString().contains("RIGHT")) 
		return; {
			event.setCancelled(true);
			if (GladiatorListener.combateGlad.containsKey(p) || net.helixpvp.kit2.GladiatorListener.combateGlad.containsKey(p)) {
				p.sendMessage("§cYou cant use flash on gladiator.");
				event.setCancelled(true);
				return;
			}
			if (!inCooldown(event.getPlayer())) {
				Block target = p.getTargetBlock((HashSet<Byte>) null, 200);
				if (target.getType() != Material.AIR && target.getType() != Material.GLASS) {
					if (target.getRelative(BlockFace.UP).getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura") - 8) {
						p.sendMessage("§cPoint the flash more below.");
						p.sendMessage("§cIt will reach spawn.");
						return;
					}
					addCooldown(p, 35);
					p.getWorld().strikeLightningEffect(p.getLocation());
					p.teleport(target.getRelative(BlockFace.UP).getLocation());
				}
			} else {
				sendMessageCooldown(event.getPlayer());
			}
		}
	}
}
}

package net.helix.pvp.kit.provider;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.core.util.HelixCooldown;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;

public class Monk extends KitHandler {
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		
		player.getInventory().setItem(1, new ItemBuilder("§eEmbaralhar!", Material.BLAZE_ROD)
				.nbt("kit-handler", "monk")
				.nbt("cancel-drop")
				.toStack()
		);
	}
	
	@EventHandler
	public void onEntityDamageByEntity(PlayerInteractAtEntityEvent event) {
		if (!(event.getRightClicked() instanceof Player)) {
			return;
		}
		Player player = event.getPlayer();
		Player rightClicked = (Player) event.getRightClicked();
		
		if (!KitManager.getPlayer(player.getName()).hasKit(this) 
				|| !ItemBuilder.has(player.getItemInHand(), "kit-handler", "monk")) {
			return;
		}
		
		if (HelixCooldown.inCooldown(player.getName(), "monk")) {
			player.sendMessage("§cAguarde " + HelixCooldown.getTime(player.getName(), "monk") + "s para utilizar este kit novamente.");
			return;
		}

		HelixCooldown.create(player.getName(), "monk", TimeUnit.SECONDS, 15);
		int randomSlot = new Random().nextInt(20 + 1 - 1) + 1;
		ItemStack itemInHand = rightClicked.getItemInHand();
		ItemStack itemInSlot = rightClicked.getInventory().getItem(randomSlot);
		
		rightClicked.getInventory().setItem(randomSlot, itemInHand);
		if (itemInSlot != null && !itemInSlot.getType().equals(Material.AIR)) {
			rightClicked.setItemInHand(itemInSlot);
		}
		rightClicked.getInventory().setHeldItemSlot(8);
		rightClicked.updateInventory();
		
		rightClicked.sendMessage("§6Você teve seu inventário embaralhado pelo §f" + player.getName() + "§6.");
		player.sendMessage("§6Você embaralhou o inventário de §f" + rightClicked.getName() + "§6.");
	}

}

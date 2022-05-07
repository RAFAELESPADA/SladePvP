package net.helix.pvp.kit;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.Dye;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.core.bukkit.util.DamageUtil;
import net.helix.core.bukkit.util.DamageUtil.DamageType;
import net.helix.core.util.HelixCooldown2;
import net.helix.pvp.command.DarKit;
import net.helix.pvp.evento.SoupTypeGUI;


public abstract class KitHandler implements Listener {

	public void execute(Player player) {
		player.setGameMode(GameMode.SURVIVAL);
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		player.setAllowFlight(false);
		player.setFlying(false);
		DamageUtil.allowDamage(player.getName(), DamageType.PLAYER, true);
		player.getActivePotionEffects().forEach(potion -> player.removePotionEffect(potion.getType()));
		player.getInventory().setHeldItemSlot(0);
		/* 348 */       
		player.getInventory().setItem(0, new ItemBuilder("§7Espada de Pedra", Material.STONE_SWORD)
				.nbt("cancel-drop")
				.toStack()
		);
		HelixPlayer p = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
		 if (!KitManager.getPlayer(p.getName()).hasKit(HelixKit.NEO)) {
			 Habilidade.removeAbility(player);
			}
		 if (!SoupTypeGUI.compass.containsKey(p.getName())) {
		player.getInventory().setItem(8, new ItemBuilder("§9Jogadores próximos", Material.COMPASS)
				.nbt("kit-handler", "search-players")
				.nbt("cancel-drop")
				.toStack()
		);
		 }
		
	if (!SoupTypeGUI.savecocoa.containsKey(p.getName())) {
		player.getInventory().setItem(13, new ItemStack(Material.BOWL, 32));
		player.getInventory().setItem(14, new ItemStack(Material.RED_MUSHROOM, 32));
		player.getInventory().setItem(15, new ItemStack(Material.BROWN_MUSHROOM, 32));
	}	else {
		Dye d = new Dye();
	    d.setColor(DyeColor.BROWN);
	    ItemStack d1 = d.toItemStack();
	    d1.setAmount(32);
		player.getInventory().setItem(13, new ItemStack(Material.BOWL, 32));
		player.getInventory().setItem(14, new ItemStack(d1));
	}
		for (int i = 0; i < 36; i++) {
			player.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
	}

protected boolean hasCooldown(Player player) {
    return HelixCooldown2.hasCooldown(player, "Kit");
}

protected boolean hasCooldown(Player player, String cooldown) {
    return HelixCooldown2.hasCooldown(player, cooldown);
}

protected boolean inCooldown(Player player, String cooldown) {
    return HelixCooldown2.inCooldown(player, cooldown);
}

protected boolean inCooldown(Player player) {
    return HelixCooldown2.inCooldown(player, "Kit");
}

protected void sendMessageCooldown(Player player) {
	HelixCooldown2.sendMessage(player, "Kit");
}

protected void sendMessageCooldown(Player player, String cooldown) {
	HelixCooldown2.sendMessage(player, cooldown);
}

protected void addCooldown(Player player, String cooldownName, long time) {
    if (HelixCooldown2.hasCooldown(player, cooldownName)) {
        HelixCooldown2.removeCooldown(player, cooldownName);
    }
    HelixCooldown2.addCooldown(player, new net.helix.core.util.HelixCooldownAPI(cooldownName, time));
}

protected void addCooldown(Player player, long time) {
    if (HelixCooldown2.hasCooldown(player, "Kit")) {
        HelixCooldown2.removeCooldown(player, "Kit");
    }
    HelixCooldown2.addCooldown(player, new net.helix.core.util.HelixCooldownAPI("Kit", time));
}

protected void addItemCooldown(Player player, ItemStack item, String cooldownName, long time) {
    if (HelixCooldown2.hasCooldown(player, cooldownName)) {
        HelixCooldown2.removeCooldown(player, cooldownName);
    }
    HelixCooldown2.addCooldown(player, new net.helix.core.util.ItemCooldown(item, cooldownName, time));
}
}
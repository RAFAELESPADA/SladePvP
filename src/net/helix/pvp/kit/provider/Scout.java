package net.helix.pvp.kit.provider;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;

public class Scout extends KitHandler {

    @Override
    public void execute(Player player) {
        super.execute(player);

        player.getInventory().setItem(1, new ItemBuilder(Material.POTION, 8226)
                .amount(1)
                .nbt("cancel-drop").nbt("kit-handler", "scout")
                .toStack()
        );
    }


@EventHandler
public void onInteract(PlayerInteractEvent event) {
    if (!event.hasItem() || !ItemBuilder.has(event.getItem(), "kit-handler", "scout")) return;

    event.setCancelled(true);
    if (inCooldown(event.getPlayer()) && KitManager.getPlayer(event.getPlayer().getName()).hasKit(this)) {
		sendMessageCooldown(event.getPlayer());
		return;
	}
    addCooldown(event.getPlayer(), HelixPvP.getInstance().getConfig().getInt("ScoutCooldown"));
    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30 * 20, 1));
    event.getPlayer().sendMessage("§aScout aplicado!");
}
}
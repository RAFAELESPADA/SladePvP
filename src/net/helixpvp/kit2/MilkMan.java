package net.helixpvp.kit2;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.core.util.HelixCooldown;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitManager2;

import java.util.concurrent.TimeUnit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitHandler2;
import net.helix.pvp.kit.KitManager2;

public class MilkMan extends KitHandler2 {

    @Override
    public void execute(Player player) {
        super.execute(player);

        player.getInventory().setItem(2, new ItemBuilder(Material.MILK_BUCKET)
                .displayName("§fMilk")
                .nbt("cancel-drop")
                .nbt("kit-handler", "milkman")
                .toStack()
        );
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!event.hasItem() || !ItemBuilder.has(event.getItem(), "kit-handler", "milkman")) return;

        
        if (inCooldown(event.getPlayer()) && KitManager2.getPlayer(event.getPlayer().getName()).haskit2(this)) {
			sendMessageCooldown(event.getPlayer());
			return;
		}
        if (HelixCooldown.has(event.getPlayer().getName(), "milk-ability")) {
        	event.getPlayer().sendMessage("§cWait to use again");
        	return;
        }
        event.setCancelled(true);
        HelixCooldown.create(event.getPlayer().getName(), "milk-ability", TimeUnit.SECONDS, HelixPvP.getInstance().getConfig().getInt("MilkManCooldown"));
        addCooldown(event.getPlayer(), HelixPvP.getInstance().getConfig().getInt("MilkManCooldown"));
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 5 * 20, 0));
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10 * 20, 0));
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5 * 20, 0));
        event.getPlayer().sendMessage("§aMilkman applied!");
    }
}
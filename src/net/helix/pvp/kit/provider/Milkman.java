package net.helix.pvp.kit.provider;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.core.util.HelixCooldown;
import net.helix.pvp.kit.KitHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.concurrent.TimeUnit;

public class Milkman extends KitHandler {

    @Override
    public void execute(Player player) {
        super.execute(player);

        player.getInventory().setItem(1, new ItemBuilder(Material.MILK_BUCKET)
                .displayName("§fBalde de Leite")
                .nbt("cancel-drop")
                .nbt("kit-handler", "milkman")
                .toStack()
        );
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!event.hasItem() || !ItemBuilder.has(event.getItem(), "kit-handler", "milkman")) return;

        event.setCancelled(true);

        if (HelixCooldown.inCooldown(event.getPlayer().getName(), "kit-milkman")) {
            event.getPlayer().sendMessage("§cO kit Milkman está em cooldown.");
            return;
        }

        HelixCooldown.create(event.getPlayer().getName(), "kit-milkman", TimeUnit.SECONDS, 15);
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 5 * 20, 0));
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10 * 20, 0));
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5 * 20, 0));
        event.getPlayer().sendMessage("§aMilkman aplicado!");
    }
}
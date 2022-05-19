package net.helix.pvp.kit.provider;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.KitHandler;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class Grandpa extends KitHandler {

    @Override
    public void execute(Player player) {
        super.execute(player);

        player.getInventory().setItem(1, new ItemBuilder("§aGrandpa!", Material.STICK)
                .addEnchant(Enchantment.KNOCKBACK, 2)
                .nbt("cancel-drop")
                .toStack()
        );
    }
}
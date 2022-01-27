package net.helix.pvp.kit.provider;


import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.KitHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Scout extends KitHandler {

    @Override
    public void execute(Player player) {
        super.execute(player);

        player.getInventory().setItem(1, new ItemBuilder(Material.POTION, 8226)
                .amount(5)
                .nbt("cancel-drop")
                .toStack()
        );
    }
}
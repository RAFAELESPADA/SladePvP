package net.helix.pvp.kit.provider;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.KitHandler;

public class Switcher extends KitHandler {


    @Override
    public void execute(Player player) {
        super.execute(player);

        player.getInventory().setItem(1, new ItemBuilder(Material.SNOW_BALL)
                .amount(16)
                .nbt("cancel-drop")
                .toStack()
        );
    }
        
@EventHandler
public void snowball(final EntityDamageByEntityEvent e) {
    if (e.getDamager() instanceof Snowball && e.getEntity() instanceof Player) {
        final Snowball s = (Snowball)e.getDamager();
        if (s.getShooter() instanceof Player) {
            final Player shooter = (Player)s.getShooter();
            final Player p = (Player)e.getEntity();
            final Location shooterLoc = shooter.getLocation();
            shooter.teleport(e.getEntity().getLocation());
            p.teleport(shooterLoc);
            shooter.playSound(shooter.getLocation(), Sound.ENDERMAN_TELEPORT, 10.0F, 10.0F);
            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10.0F, 10.0F);
        }
    }
}
}

package net.helix.pvp.kit.provider;

import java.util.concurrent.TimeUnit;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.core.util.HelixCooldown;
import net.helix.pvp.kit.Habilidade;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;
import net.md_5.bungee.api.ChatColor;

public class Switcher extends KitHandler {


    @Override
    public void execute(Player player) {
        super.execute(player);

        player.getInventory().setItem(1, new ItemBuilder(Material.SNOW_BALL)
                .amount(1)
                .nbt("cancel-drop")
                .toStack()
        );
    }
        
@EventHandler
public void snowball(final ProjectileLaunchEvent e) {
    if (e.getEntity() instanceof Snowball) {
    	if (e.getEntity().getShooter() instanceof Player) {
        final Player p = (Player) e.getEntity().getShooter();
            if (!KitManager.getPlayer(p.getName()).hasKit(this)) {
            	return;
            }
            if (inCooldown(p) && KitManager.getPlayer(p.getName()).hasKit(this)) {
             e.setCancelled(true);
             sendMessageCooldown(p);
             p.getInventory().addItem(new ItemBuilder(Material.SNOW_BALL)
                     .amount(1)
                     .nbt("cancel-drop")
                     .toStack());
             return;
            }
            p.getInventory().addItem( new ItemBuilder(Material.SNOW_BALL)
                    .amount(1)
                    .nbt("cancel-drop")
                    .toStack());
            addCooldown(p , 7);
        }
    
    }
}
    	  @EventHandler
    	  public void snowball(EntityDamageByEntityEvent e) {
    	    if (e.getDamager() instanceof Snowball && e.getEntity() instanceof Player) {
    	    	
    	      Snowball s = (Snowball)e.getDamager();
    	      if (s.getShooter() instanceof Player) {
    	        Player shooter = (Player)s.getShooter();
    	        if (!KitManager.getPlayer(shooter.getName()).hasKit(this)) {
                	return;
                }
    	        Player p = (Player)e.getEntity();
    	        if (Habilidade.getAbility(p) == "SteelHead") {
					p.playSound(p.getLocation(), Sound.NOTE_BASS_DRUM, 15.0f, 15.0f);
					shooter.sendMessage(ChatColor.AQUA + "Você nao pode usar o Switcher em " + p.getName() + " porque ele esta com o kit NEO");
					return;
				}
    	        if (!KitManager.getPlayer(p.getName()).hasKit()) {
    	          return; 
    	        }
    	        Location shooterLoc = shooter.getLocation();
    	        shooter.teleport(e.getEntity().getLocation());
    	        p.teleport(shooterLoc);
    	        shooter.playSound(shooter.getLocation(), Sound.ENDERMAN_TELEPORT, 10.0F, 10.0F);
    	        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10.0F, 10.0F);
    	      } 
    }
}
}


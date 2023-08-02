package net.helixpvp.kit2;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.HelixKit2;
import net.helix.pvp.kit.KitHandler2;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.helix.pvp.kit.provider.EnderMageReal;
import net.helix.pvp.kit.provider.GladiatorListener;
import net.md_5.bungee.api.ChatColor;

public class Switcher extends KitHandler2 {


    @Override
    public void execute(Player player) {
        super.execute(player);

        player.getInventory().setItem(2, new ItemBuilder(Material.SNOW_BALL)
                .amount(2)
                .nbt("cancel-drop").nbt("kit-handler", "switcher")
                .toStack()
        );
    }
@EventHandler
public void snowball(final ProjectileLaunchEvent e) {
    if (e.getEntity() instanceof Snowball) {
    	if (e.getEntity().getShooter() instanceof Player) {
        final Player p = (Player) e.getEntity().getShooter();
            if (!KitManager2.getPlayer(p.getName()).haskit2(this) || !p.getItemInHand().equals(new ItemStack(Material.SNOW_BALL))) {
            	return;
            }
            if (GladiatorListener.combateGlad.containsKey(p) || net.helixpvp.kit2.GladiatorListener.combateGlad.containsKey(p)) {
                p.sendMessage("§cVoc\u00ea não pode usar o switcher no gladiator!");
                e.setCancelled(true);
                return;
            }
            if (inCooldown(p) && KitManager2.getPlayer(p.getName()).haskit2(this)) {
             e.setCancelled(true);
             sendMessageCooldown(p);
             p.getInventory().addItem(new ItemBuilder(Material.SNOW_BALL)
                     .amount(1)
                     .nbt("cancel-drop").nbt("kit-handler", "switcher")
                     .toStack());
             return;
            }
            p.getInventory().addItem( new ItemBuilder(Material.SNOW_BALL)
                    .amount(1)
                    .nbt("cancel-drop").nbt("kit-handler", "switcher")
                    .toStack());
            addCooldown(p , HelixPvP.getInstance().getConfig().getInt("SwitcherCooldown"));
        
    	}
    }
    
}
    	  @EventHandler
    	  public void snowball(EntityDamageByEntityEvent e) {
    	    if (e.getDamager() instanceof Snowball && e.getEntity() instanceof Player) {
    	    	
    	      Snowball s = (Snowball)e.getDamager();
    	      if (s.getShooter() instanceof Player) {
    	        Player shooter = (Player)s.getShooter();
    	        if (!KitManager2.getPlayer(shooter.getName()).haskit2(this)) {
                	return;
                }
    	        else if (shooter.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura") && EnderMageReal.isSpawn(shooter.getLocation())) {
    	        	shooter.sendMessage("§cNão use o seu poder no spawn!");
    	    		return;
    	       }
    	        Player p = (Player)e.getEntity();
    	        if (KitManager.getPlayer(p.getName()).hasKit(HelixKit.NEO) || KitManager2.getPlayer(p.getName()).haskit2(HelixKit2.NEO)) {
					p.playSound(p.getLocation(), Sound.NOTE_BASS_DRUM, 15.0f, 15.0f);
					shooter.sendMessage(ChatColor.AQUA + "You cant use switcher on " + p.getName() + " because he has kit NEO selected");
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


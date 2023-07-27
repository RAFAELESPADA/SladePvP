package net.helixpvp.kit2;


import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitHandler2;
import net.helix.pvp.kit.KitManager2;
import net.helix.pvp.kit.provider.Raios;

public class Firebender extends KitHandler2 {

	@Override
    public void execute(Player player) {
        super.execute(player);

        player.getInventory().setItem(2, new ItemBuilder("§cFireBender!", Material.REDSTONE_BLOCK)
                .addEnchant(Enchantment.KNOCKBACK, 1)
        				.addFlags(ItemFlag.HIDE_ENCHANTS)
                .nbt("cancel-drop")
                .toStack()
        );
	}
  public static ArrayList<String> fireattack = new ArrayList<>();
  
  @EventHandler
  public void PlayerInteractEvt(PlayerInteractEntityEvent e) {
    if (!(e.getRightClicked() instanceof Player))
      return; 
    Player p = e.getPlayer();
    final Player ent = (Player)e.getRightClicked();
    if (KitManager2.getPlayer(p.getName()).haskit2(this) && p.getItemInHand().getType() == Material.REDSTONE_BLOCK) {
      e.setCancelled(true);
      if (hasCooldown(p)) {
        sendMessageCooldown(p);
        return;
      } 
      fireattack.add(ent.getName());
      createSpiralAroundPlayer(ent);
      addCooldown(p, 20);
      ent.setFireTicks(120);
      Bukkit.getScheduler().scheduleAsyncDelayedTask((Plugin)HelixPvP.getInstance(), new Runnable() {
            public void run() {
              Firebender.fireattack.remove(ent.getName());
            }
          },  40L);
    } 
  }
  public static void createSpiralAroundPlayer(Player player) {
		 Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance() , new BukkitRunnable() {
	         @Override
	         public void run() {
	        	 Location location = player.getLocation();
	             for (int degree = 0; degree < 360; degree++) {
	                 double radians = Math.toRadians(degree);
	                 double x = Math.cos(radians);
	                 double z = Math.sin(radians);
	                 location.add(x, 0, z);
	                 location.getWorld().playEffect(location, Effect.FLAME, 1);
	                 location.subtract(x, 0, z);
	             }
	         }
	     }, 10);
		 Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance() , new BukkitRunnable() {
	         @Override
	         public void run() {
	        	 Location location = player.getLocation();
	             for (int degree = 0; degree < 360; degree++) {
	                 double radians = Math.toRadians(degree);
	                 double x = Math.cos(radians);
	                 double z = Math.sin(radians);
	                 location.add(x, 0, z);
	                 location.getWorld().playEffect(location, Effect.FLAME, 1);
	                 location.subtract(x, 0, z);
	             }
	         }
	     }, 20);
		 Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance() , new BukkitRunnable() {
	         @Override
	         public void run() {
	        	 Location location = player.getLocation();
	             for (int degree = 0; degree < 360; degree++) {
	                 double radians = Math.toRadians(degree);
	                 double x = Math.cos(radians);
	                 double z = Math.sin(radians);
	                 location.add(x, 0, z);
	                 location.getWorld().playEffect(location, Effect.FLAME, 1);
	                 location.subtract(x, 0, z);
	             }
	         }
	     }, 30);
		 Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance() , new BukkitRunnable() {
	         @Override
	         public void run() {
	        	 Location location = player.getLocation();
	             for (int degree = 0; degree < 360; degree++) {
	                 double radians = Math.toRadians(degree);
	                 double x = Math.cos(radians);
	                 double z = Math.sin(radians);
	                 location.add(x, 0, z);
	                 location.getWorld().playEffect(location, Effect.FLAME, 1);
	                 location.subtract(x, 0, z);
	             }
	         }
	     }, 40);
	    }

  
  @EventHandler
  public void PlayerMov(PlayerMoveEvent e) {
    Player p = e.getPlayer();
    if (fireattack.contains(p.getName()))
      p.teleport((Entity)p); 
  }
}
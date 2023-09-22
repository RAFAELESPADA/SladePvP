package net.helix.pvp.kit.provider;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemFlag;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.warp.HelixWarp;


public class TimeLord extends KitHandler {

	@Override
    public void execute(Player player) {
        super.execute(player);

        player.getInventory().setItem(1, new ItemBuilder("§bPare o Tempo!", Material.WATCH)
                .addEnchant(Enchantment.KNOCKBACK, 1)
        				.addFlags(ItemFlag.HIDE_ENCHANTS)
                .nbt("cancel-drop")
                .toStack()
        );
        
    }
/*    */ 
/* 28 */   public static ArrayList<String> playercongelados = new ArrayList();
/*    */   
/*    */   @EventHandler
/*    */   public void onTimerLord(PlayerInteractEvent e)
/*    */   {
/* 33 */     final Player p = e.getPlayer();
if (inCooldown(p) && KitManager.getPlayer(p.getName()).hasKit(this)) {
	sendMessageCooldown(p);
	return;
}
/* 34 */     if (KitManager.getPlayer(p.getName()).hasKit(this) && ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && (p.getInventory().getItemInHand().getType() == org.bukkit.Material.WATCH)) {

/* 39 */       addCooldown(p, HelixPvP.getInstance().getConfig().getInt("TimeLordCooldown"));
/* 40 */       p.sendMessage("§aYou freeze players nearby you!");
/* 41 */       for (final Entity pertos : p.getNearbyEntities(6.0, 6.0, 6.0)) {
	if (pertos instanceof Player) {
	    if (!KitManager.getPlayer(pertos.getName()).hasKit()) {
	    	return;
	    }
	    p.playSound(p.getLocation(), Sound.WITHER_SHOOT, 1f, 1f);
/* 42 */         playercongelados.add(((Player)pertos).getName());
/* 43 */         ((Player)pertos).sendMessage("§cYou got frozen by a timelord.");
((Player) pertos).playSound((Location)pertos.getLocation(), Sound.WITHER_SHOOT, 1f, 1f);
/* 45 */         org.bukkit.Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable()
/*    */         {
/*    */           public void run() {
/* 48 */             TimeLord.playercongelados.remove(((Player)pertos).getName());
/* 49 */             ((Player)pertos).sendMessage("§aYou got unfrozen.");
/*    */           }
/* 51 */         }, 80L);
/*    */       {
/* 53 */       org.bukkit.Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable()
/*    */       {
/*    */         public void run() {
/* 56 */           p.sendMessage("§cCooldown ended");
/*    */         }
/* 58 */       }, 30 * 20);
/*    */     }
}
}
}
/*    */   }
/*   
/*    */   
/*    */   @EventHandler
/*    */   public void onTimerLordado(PlayerMoveEvent e) {
/* 64 */     Player p = e.getPlayer();
/* 65 */     if (playercongelados.contains(p.getName()) && p.getLocation().getY() < HelixPvP.getInstance().getConfig().getInt("SpawnAltura")) {
/* 66 */       e.setTo(p.getLocation());
/*    */     }
/*    */   }
/*    */   @EventHandler
/*    */   public void onTimerLordad2(PlayerMoveEvent e) {
/* 64 */     Player p = e.getPlayer();
if (!KitManager.getPlayer(p.getName()).hasKit() && p.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura")) {
	e.setCancelled(false);
}
}
/*    */   @EventHandler
/*    */   public void onTimerLordad3(PlayerMoveEvent e) {
/* 64 */     Player p = e.getPlayer();
if (p.getLocation().getY() > (HelixPvP.getInstance().getConfig().getInt("SpawnAltura") - 13) && p.getLocation().getY() <= HelixPvP.getInstance().getConfig().getInt("SpawnAltura") && p.getLocation().getZ() > (HelixPvP.getInstance().getConfig().getInt("SpawnAltura") - 42) && p.getLocation().getZ() < (HelixPvP.getInstance().getConfig().getInt("SpawnAltura") - 32)) {
	HelixWarp.SPAWN.send(p, true);
	  p.sendMessage("§cVocê bugou no bloco e foi enviado para o spawn para desbugar");
}
}
}
/*    */ 

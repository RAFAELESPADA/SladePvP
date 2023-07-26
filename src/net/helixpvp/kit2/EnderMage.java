package net.helixpvp.kit2;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.util.Vector;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitHandler2;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.helix.pvp.kit.provider.GladiatorListener;
import net.helix.pvp.kit.provider.Kangaroo;
import net.minecraft.server.v1_8_R3.DamageSource;

public class EnderMage extends KitHandler2 {
	 @Override
	    public void execute(Player player) {
	        super.execute(player);

	        player.getInventory().setItem(2, new ItemBuilder("§aVacuum", Material.ENDER_PORTAL_FRAME)
	                .addEnchant(Enchantment.KNOCKBACK, 1)
	        				.addFlags(ItemFlag.HIDE_ENCHANTS)
	                .nbt("cancel-drop")
	                .toStack()
	        );
	        
	    }
@EventHandler
public void flash(PlayerInteractEvent e) {
  Player p = e.getPlayer();
  if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && 
    p.getItemInHand().getType() == Material.ENDER_PORTAL_FRAME) {
	  if (!KitManager2.getPlayer(p.getName()).haskit2(this)) {
		  return;
	  }
    if (hasCooldown(p)) {
        sendMessageCooldown(p);
        return;
    }
      e.setCancelled(true);
      p.updateInventory();
      if (GladiatorListener.combateGlad.containsKey(p) || net.helixpvp.kit2.GladiatorListener.combateGlad.containsKey(p)) {
    		/*  62 */         p.sendMessage(String.valueOf("§cVocê esta no Gladiator e recebeu efeito de speed"));
    		/*     */         
    		/*  64 */        Kangaroo.darEfeito(p, org.bukkit.potion.PotionEffectType.SPEED, 10, 1);
    		/*     */       }
      else if (p.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura")) {
			p.sendMessage("§cNão use o vacuum no spawn!");
			return;
		 }
    
  
  	   for (final Entity pertos : p.getNearbyEntities(10, 10 , 10)) {
      	  if (pertos instanceof Player) {
        if (KitManager.getPlayer(pertos.getName()).hasKit())	{
        Player per = (Player)pertos;
      	  pertos.setVelocity(new Vector(10 , 1 , 13));
        per.damage(12);
        pertos.setFireTicks(55);
        e.setCancelled(true);
        p.sendMessage(ChatColor.GREEN + "Players arrastados pelo seu vacuum!");
        per.sendMessage(ChatColor.RED + "Você foi atingido pelo Vacuum de " + p.getName());
        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10.0F, 10.0F);
        addCooldown(p, 45);
        return;
      }
        
      	  }}}
      }
      

public static List<Entity> getNearbyEntities(Location where, int range) {
List<Entity> found = new ArrayList<Entity>();
 
for (Entity entity : where.getWorld().getEntities()) {
if (isInBorder(where, entity.getLocation(), range)) {
found.add(entity);
}
}
return found;
}
public static boolean isInBorder(Location center, Location notCenter, int range) {
int x = center.getBlockX(), z = center.getBlockZ();
int x1 = notCenter.getBlockX(), z1 = notCenter.getBlockZ();
 
if (x1 >= (x + range) || z1 >= (z + range) || x1 <= (x - range) || z1 <= (z - range)) {
return false;
}
return true;
}
}
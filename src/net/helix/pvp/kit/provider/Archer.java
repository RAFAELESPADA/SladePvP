package net.helix.pvp.kit.provider;


/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
import java.util.concurrent.TimeUnit;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.core.util.HelixCooldown;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;

/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Effect;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
/*     */ import org.bukkit.scheduler.BukkitScheduler;
/*     */ import org.bukkit.util.Vector;

/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Archer extends KitHandler 
/*     */ {
	@Override
	public void execute(Player player) {
		super.execute(player);
		
		player.getInventory().setItem(2, new ItemBuilder("§aArco!", Material.BOW)
				.nbt("kit-handler", "arco").addEnchant(Enchantment.ARROW_INFINITE, 1)
				.nbt("cancel-drop")
				.toStack());
				player.getInventory().setItem(10, new ItemBuilder("§aFlecha!", Material.ARROW)
						.nbt("kit-handler", "flecha").addEnchant(Enchantment.DAMAGE_ALL, 1)
						.nbt("cancel-drop").addFlags(
								ItemFlag.HIDE_ENCHANTS)
						.toStack()
		);
	}


	@EventHandler
<<<<<<< HEAD

=======
>>>>>>> 0eed44f403c021b9b086d025911e9957a356a0a9
    public void bowUseEvent(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
      
            Player player = (Player) event.getEntity();
            if (!KitManager.getPlayer(event.getEntity().getName()).hasKit(this)) {
            	return;
            }
          if (HelixCooldown.has(player.getName(), "archer")) {
        	  sendMessageCooldown(player);
        	  event.setCancelled(true);
        	  return;
          }
          if (!hasCooldown(player)) {
        	
                addCooldown(player, 4);
            }
          HelixCooldown.create(player.getName(), "archer", TimeUnit.SECONDS, 4);
          Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance() , new BukkitRunnable() {
		         @Override
		         public void run() {
		        	HelixCooldown.delete(player.getName(), "archer");
		             }
		         }
		     , 80);
        }
}
<<<<<<< HEAD
}	




=======
>>>>>>> 0eed44f403c021b9b086d025911e9957a356a0a9

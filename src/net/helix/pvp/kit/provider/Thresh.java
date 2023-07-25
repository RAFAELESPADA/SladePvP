package net.helix.pvp.kit.provider;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.bukkit.entity.*;
import org.bukkit.entity.Snowball;
import java.util.HashMap;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import org.bukkit.Location;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;

public class Thresh extends KitHandler {
	public static HashMap<String, Snowball> tiros = new HashMap<>();
	@Override
	public void execute(Player player) {
		super.execute(player);
		
		player.getInventory().setItem(1, new ItemBuilder("�eEmbaralhar!", Material.BLAZE_ROD)
				.nbt("kit-handler", "monk")
				.nbt("cancel-drop")
				.toStack()
		);
	}

@EventHandler
public void disparar(PlayerInteractEvent e) {
  Player p = e.getPlayer();
  if (!KitManager.getPlayer(p.getName()).hasKit(this) 
			|| !e.hasItem() || !ItemBuilder.has(e.getItem(), "kit-handler", "thresh")) {
		return;
	}
  else if (e.getPlayer().getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura") && KitManager.getPlayer(e.getPlayer().getName()).hasKit(this)) {
      	e.getPlayer().sendMessage("§cNão use o thresh no spawn!");
      	e.setCancelled(true);
			return;
		}
	  if (!inCooldown(p)) {
      e.setCancelled(true);
      p.updateInventory();
      Snowball tiro = (Snowball)p.launchProjectile(Snowball.class);
      Vector vec = p.getLocation().getDirection();
      tiro.setVelocity(new Vector(vec.getX() * 1.0D * 3.5D, vec.getY() * 1.0D * 4.0D, vec.getZ() * 1.0D * 3.5D));
      tiros.put(p.getName(), tiro);
      addCooldown(p , 15);
      p.playSound(p.getLocation(), Sound.GLASS, 1.0F, 1.0F);
      p.sendMessage(ChatColor.GREEN + "Você usou seu Thresh.");
    } 
	  else {
		  sendMessageCooldown(e.getPlayer());
			return;  
	  }
  }



  


@EventHandler
public void onEntityDamagerByEntity(EntityDamageByEntityEvent e) {
  Entity ent = e.getEntity();
  Entity damager = e.getDamager();
  if (ent instanceof Player) {
    Player hit = (Player)ent;
    if (damager instanceof Snowball) {
      Snowball snowball = (Snowball)damager;
      if (snowball.getShooter() instanceof Player) {
        Player shooter = (Player)snowball.getShooter();
        if (!KitManager.getPlayer(shooter.getName()).hasKit(HelixKit.THRESH)) {
          return; 
        }
        Location ploc = shooter.getLocation();
        hit.teleport(ploc);
        hit.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 1));
        hit.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));
        hit.sendMessage(ChatColor.RED + "Você foi pego por um Thresh.");
      } 
    }
  }
}
}
    

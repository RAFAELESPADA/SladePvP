package net.helix.pvp.kit.provider;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;

public class Tank  extends KitHandler {

	public static HashMap<String, Location> saveworld = new HashMap();

@EventHandler
public void onDamage1(EntityDamageEvent event) {
    if (event.getEntity() instanceof Player) {
        Player p = (Player)event.getEntity();
        if ((event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) || (event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)) {
        	if (KitManager.getPlayer(p.getName()).hasKit(this)) {
        	    event.setCancelled(true);
        	}
        }
    }
}
@EventHandler
public void NoExplodeMyMapPlease(EntityExplodeEvent e)
{
  e.setCancelled(true);
	  }
@EventHandler(priority = EventPriority.MONITOR)
public void onDeath(PlayerDeathEvent event) {
	final Player morreu = event.getEntity();
			
	
	
	
	 if (!KitManager.getPlayer(morreu.getName()).hasKit(this)) {
		 return;
	 }
     
		morreu.getLocation().getWorld().playEffect(morreu.getLocation(), Effect.EXPLOSION_LARGE, 40);
		morreu.playSound(morreu.getLocation(), Sound.EXPLODE, 1.0f, 1.0f);
		morreu.sendMessage(ChatColor.GREEN + "Você criou uma explosão com seu kit Creeper e matou 1 inimigo (+1 kill)");
		Location deathLoc = morreu.getLocation();
		saveworld.put(morreu.getName(), deathLoc);
		morreu.spigot().respawn();
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
		  public void run() {
			  morreu.getWorld().createExplosion(saveworld.get(morreu.getKiller().getName()), 20.0F);
		  }
		}, 1);
		morreu.getWorld().createExplosion(saveworld.get(morreu.getKiller().getName()), 20.0F);
		for (final Entity pertos : morreu.getKiller().getNearbyEntities(4.0, 4.0, 4.0)) {
			if (pertos instanceof Player) {
				((Player) pertos).damage(19.5D);
				morreu.getWorld().createExplosion(morreu.getKiller().getLocation(), 20.0F);
				morreu.getWorld().strikeLightning(pertos.getLocation());
				morreu.getWorld().strikeLightning(pertos.getLocation());
				morreu.getWorld().strikeLightning(pertos.getLocation());
				  HelixPlayer ph = HelixBukkit.getInstance().getPlayerManager().getPlayer(morreu.getName());
				  ph.getPvp().addKills(1);
				morreu.getWorld().strikeLightning(pertos.getLocation());
				morreu.getWorld().strikeLightning(pertos.getLocation());
				((Player) pertos).playSound(pertos.getLocation(), Sound.EXPLODE, 1.0f, 1.0f);
	}
}
}
}
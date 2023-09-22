package net.helix.pvp.kit.provider;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;


public class Tornado extends KitHandler {


	
	
	@Override
    public void execute(Player player) {
        super.execute(player);

        player.getInventory().setItem(1, new ItemBuilder(Material.HOPPER)
                .displayName("§cTornado")
                .nbt("cancel-drop")
                .nbt("kit-handler", "tornado")
                .toStack()
        );
    }
	
	
	
	  @EventHandler
			/*     */   public void pular(PlayerInteractEvent event)
			/*     */   {
				
			/*  56 */     Player p = event.getPlayer();


			/*  57 */     if (!KitManager.getPlayer(p.getName()).hasKit(this)
					|| !event.hasItem() || !ItemBuilder.has(event.getItem(), "kit-handler", "tornado")) {
				return;
			}
			if (inCooldown(p) && KitManager.getPlayer(p.getName()).hasKit(this)) {
				sendMessageCooldown(p);
				return;
			}
			if (p.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura")) {
				p.sendMessage("DONT USE THE TORNADO HERE.");
				return;
			}
float max_height = 15;
float max_radius = 10;
float lines = 4;
int angle = 0;
float height_increasement = (float)0.5;
float radius_increasement = max_radius / max_height; {
	Bukkit.broadcastMessage(ChatColor.BLUE + p.getName() + " Spawned a tornado!");
for (int l = 0; l < lines; l++) {
      for (float y = 0; y < max_height; y+=height_increasement ) {
          float radius = y * radius_increasement;
          double x = Math.cos(Math.toRadians(360/lines*l + y*25 - angle)) * radius;
          double z = Math.sin(Math.toRadians(360/lines*l + y*25 - angle)) * radius;
          Location location = p.getLocation();

          for (Player p1 : Bukkit.getOnlinePlayers()) {
          p1.playSound(p1.getLocation(), Sound.GHAST_SCREAM, 1.0f, 1.0f);
          }
          location.getWorld().playEffect(location, Effect.CLOUD, 10);
          location.getWorld().playEffect(location.clone().add(x,y,z), Effect.CLOUD, 10);
          location.getWorld().playEffect(location.clone().add(1,2,3), Effect.CLOUD, 10);
          location.getWorld().playEffect(location.clone().add(4,5,6), Effect.CLOUD, 10);
          location.getWorld().playEffect(location.clone().add(4,2,3), Effect.CLOUD, 10);
          location.getWorld().playEffect(location, Effect.CLOUD, 10);
          location.getWorld().playEffect(location.clone().add(x,y,z), Effect.EXPLOSION_LARGE, 10);
          location.getWorld().playEffect(location.clone().add(1,2,3), Effect.EXPLOSION_LARGE, 10);
          location.getWorld().playEffect(location.clone().add(4,5,6), Effect.EXPLOSION_LARGE, 10);
          location.getWorld().playEffect(location.clone().add(4,2,3), Effect.EXPLOSION_LARGE, 10);
          location.getWorld().playEffect(location.clone().add(x,y,z), Effect.COLOURED_DUST, 10);
          location.getWorld().playEffect(location.clone().add(1,2,3), Effect.COLOURED_DUST, 10);
          location.getWorld().playEffect(location.clone().add(4,5,6), Effect.COLOURED_DUST, 10);
          location.getWorld().playEffect(location.clone().add(4,2,3), Effect.COLOURED_DUST, 10);
            	  for (final Entity pertos : p.getNearbyEntities(10.0, 10.0, 10.0)) {
            			if (pertos instanceof Player) {
            				((Player) pertos).damage(12.0D);
            				pertos.setFireTicks(400);
            		          location.getWorld().playEffect(pertos.getLocation(), Effect.CLOUD, 10);
            		          location.getWorld().playEffect(pertos.getLocation(), Effect.EXPLOSION_LARGE, 10);
            		          location.getWorld().playEffect(pertos.getLocation(), Effect.EXPLOSION_LARGE, 10);
            		          location.getWorld().playEffect(pertos.getLocation(), Effect.EXPLOSION_LARGE, 10);
            		          location.getWorld().playEffect(pertos.getLocation(), Effect.EXPLOSION_LARGE, 10);
            		          location.getWorld().playEffect(pertos.getLocation(), Effect.COLOURED_DUST, 10);
            		          location.getWorld().playEffect(pertos.getLocation(), Effect.COLOURED_DUST, 10);
            		          location.getWorld().playEffect(pertos.getLocation(), Effect.COLOURED_DUST, 10);
            		          location.getWorld().playEffect(pertos.getLocation(), Effect.COLOURED_DUST, 10);
            				pertos.setVelocity(p.getLocation().getDirection().multiply(8));
            				pertos.sendMessage("You get hit by " + p.getName() + " tornado");
            			}      
          
   }
   angle++;
   }
   }
addCooldown(p, HelixPvP.getInstance().getConfig().getInt("TornadoCooldown"));
}

}
}
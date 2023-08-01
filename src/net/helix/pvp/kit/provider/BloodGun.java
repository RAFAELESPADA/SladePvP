package net.helix.pvp.kit.provider;

import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;
public class BloodGun extends KitHandler {

	@EventHandler
	  public void BloodClick(PlayerInteractEvent event) {
	    final Player p = event.getPlayer();
	    if (!KitManager.getPlayer(event.getPlayer().getName()).hasKit(this)) {
	    	return;
	    }
	    if (!(event.getPlayer().getItemInHand().getType() == Material.WOOD_HOE)) {
	    	return;
	    }
	    			
	      if (event.getAction() == Action.RIGHT_CLICK_BLOCK || 
	        event.getAction() == Action.RIGHT_CLICK_AIR || 
	        event.getAction() == Action.LEFT_CLICK_AIR || 
	        event.getAction() == Action.LEFT_CLICK_BLOCK) {
	        event.setCancelled(true); 
	      }
	      if (inCooldown(p)) {
		       sendMessageCooldown(p);
		       return;
		      }

	       else if (p.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura") && EnderMageReal.isSpawn(p.getLocation())) {
	        	p.sendMessage("§cNão use o seu poder no spawn!");
	    		return;
	       }
	        Vector velo1 = p.getLocation().getDirection().normalize().multiply(10);
	        Fireball boladenve = (Fireball)p.launchProjectile(Fireball.class);
	        boladenve.setIsIncendiary(true);
	        boladenve.setYield(0.0F);
	        boladenve.setMetadata("hadouken", new FixedMetadataValue(HelixPvP.getInstance(), Boolean.valueOf(true)));
	        boladenve.setVelocity(velo1);
	        Location location = p.getEyeLocation();
	        BlockIterator blocksToAdd = new BlockIterator(location, 0.0D, 30);
	        while (blocksToAdd.hasNext()) {
	          Location blockToAdd = blocksToAdd.next().getLocation();
	          Effect a = Effect.STEP_SOUND;
	          p.getWorld().playEffect(blockToAdd, a, 152);
	          addCooldown(p , 10);
	        }
	          Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
	                public void run() {
	               p.sendMessage(ChatColor.GREEN + "O Cooldown do BloodGun expirou");
	                }
	              },  200L);
	      } 
	
	        @EventHandler
	  	  public void dano(EntityDamageByEntityEvent e)
	  	  {
	  	    if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Fireball)))
	  	    {
	  	      Fireball s = (Fireball)e.getDamager();
	  	      if (s.hasMetadata("hadouken")) {
	  	        e.setDamage(e.getDamage() + 5.0D);
	  	      }
	      }
	    }
	}

	      

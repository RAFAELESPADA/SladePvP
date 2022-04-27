package net.helix.pvp.listener;

import java.util.ArrayList;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.KitManager;




public class Jump implements Listener {
	
	private ArrayList<String> fall = new ArrayList<String>();
	
	public void Atirar(Player p) {
		int y = 8;
		Block block = p.getLocation().getBlock().getRelative(0, -1, 0);
		if (block.getType() == Material.SPONGE) {
			Vector vector = new Vector(0, y, 0);
			p.setVelocity(vector);
			this.fall.remove(p.getName());
			 p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 4.0F, 4.0F);
			p.getPlayer().getWorld().playEffect(p.getPlayer().getLocation(), Effect.MOBSPAWNER_FLAMES, 10);
			this.fall.add(p.getName());
		}
	}
	public void Atirar2(Player p) {
		final Location loc = p.getEyeLocation();
    
        final Vector sponge = p.getLocation().getDirection().multiply(3.8).setY(0.45);
		Block block = p.getLocation().getBlock().getRelative(0, -1, 0);
		if (block.getType() == Material.SLIME_BLOCK) {
			p.setVelocity(sponge);
		    p.playEffect(loc, Effect.MOBSPAWNER_FLAMES, (Object)null);
			this.fall.remove(p.getName());
			 p.playSound(p.getLocation(), Sound.SLIME_WALK, 4.0F, 4.0F);
			this.fall.add(p.getName());
		}
	}
	@EventHandler
	private void Jumps(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Atirar(p);
		Atirar2(p);
	}
	@EventHandler
	public void RemoverDan2o(EntityDamageEvent e) 
	{
	   if (!(e.getEntity() instanceof Player)) {
           return;
       }
	   if (e.getCause() == EntityDamageEvent.DamageCause.FALL && !KitManager.getPlayer(e.getEntity().getName()).hasKit() && this.fall.contains(e.getEntity().getName()))  {
		   e.setCancelled(true);
		   HelixKit.findKit("PvP").ifPresent(kit -> {
				kit.send((Player)e.getEntity());
		   });
	   }
	   else if (e.getCause() == EntityDamageEvent.DamageCause.FALL && !KitManager.getPlayer(e.getEntity().getName()).hasKit() && e.getEntity().getLocation().getX() < 1000 && e.getEntity().getLocation().getX() > -1000)  {
		   e.setCancelled(true);
		   HelixKit.findKit("PvP").ifPresent(kit -> {
				kit.send((Player)e.getEntity());
		   });
	   }
	}
	 
	   @EventHandler
		public void RemoverDano(EntityDamageEvent e) 
		{
		   if (!(e.getEntity() instanceof Player)) {
	           return;
	       }
			Player p = (Player) e.getEntity();
			if (e.getCause() == EntityDamageEvent.DamageCause.FALL && this.fall.contains(p.getName())) 
			{
				this.fall.remove(p.getName());
				e.setCancelled(true);

			}
			else if(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK)
			{
				this.fall.remove(p.getName());
			}
		}
		
	}


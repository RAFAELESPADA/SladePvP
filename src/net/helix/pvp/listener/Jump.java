package net.helix.pvp.listener;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;


public class Jump implements Listener {
	
	private ArrayList<String> jump = new ArrayList<String>();
	
	@EventHandler
	private void onBlockJump(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == org.bukkit.Material.SPONGE) {
			jump.remove(p.getName());
			Vector v = p.getLocation().getDirection().multiply(0).setY(5.5F);
			p.setVelocity(v);
			jump.add(p.getName());
		}
		if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SLIME_BLOCK) {
			if (!net.helix.pvp.kit.All.kit.contains(p)) {
				Vector vector = p.getEyeLocation().getDirection();
				
				p.setFallDistance(-1.0F);
				vector.multiply(2.3F);
				vector.setY(0.5D);
				p.setVelocity(vector);
			}
		}
	}
	@EventHandler
	private void Jumpppp(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getEntity();
		
		if (e.getCause() == DamageCause.FALL) {
			if (jump.contains(p.getName())) {
				e.setCancelled(true);
				jump.remove(p.getName());
			}
		}
	}

}

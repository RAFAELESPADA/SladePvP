package net.helix.pvp.listener;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import net.helix.pvp.HelixPvP;
import net.helix.pvp.evento.EventoUtils;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.HelixKit2;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitHandler2;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.helix.pvp.warp.HelixWarp;





public class Jump implements Listener {
	
	private ArrayList<String> fall = new ArrayList<String>();
	public KitHandler obj = new KitHandler();
	public KitHandler2 obj2 = new KitHandler2();
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
		Player player = e.getPlayer();
		Atirar(p);
		Atirar2(p);
	if (player.getLocation().getBlockY() < HelixPvP.getInstance().getConfig().getInt("SpawnAltura") && (!KitManager.getPlayer(player.getName()).hasKit() && !KitManager2.getPlayer(player.getName()).haskit2()) && !EventoUtils.tp && !HelixWarp.ONE_VS_ONE.hasPlayer(p.getName()) && !HelixWarp.FPS.hasPlayer(p.getName()) && !HelixWarp.SUMO.hasPlayer(p.getName()) && !HelixWarp.LAVACHALLENGE.hasPlayer(p.getName())) {
		HelixKit.findKit("PvP").ifPresent(kit -> {
			player.closeInventory();
			kit.send(player);
		});
		HelixKit2.findKit("PvP").ifPresent(kit -> {
			player.closeInventory();
			kit.send(player);
		});
	}
	if (player.getLocation().getBlockY() < HelixPvP.getInstance().getConfig().getInt("SpawnAltura") && (KitManager.getPlayer(player.getName()).hasKit() && !KitManager2.getPlayer(player.getName()).haskit2()) && !EventoUtils.tp && !HelixWarp.ONE_VS_ONE.hasPlayer(p.getName()) && !HelixWarp.FPS.hasPlayer(p.getName()) && !HelixWarp.SUMO.hasPlayer(p.getName()) && !HelixWarp.LAVACHALLENGE.hasPlayer(p.getName())) {
		HelixKit.findKit(KitManager.getPlayer(player.getName()).getKit().toString()).ifPresent(kit -> {
			player.closeInventory();
			kit.send(player);
		});
		HelixKit2.findKit("PvP").ifPresent(kit -> {
			player.closeInventory();
			kit.send(player);
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
				EntityDamageEvent event = new EntityDamageEvent(p, EntityDamageEvent.DamageCause.FALL, 1.0F);
				p.setLastDamageCause(event);
				Bukkit.getServer().getPluginManager().callEvent(event);
				p.damage(1F);;
			}
			else if(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK)
			{
				this.fall.remove(p.getName());
			}
		}
		
	}


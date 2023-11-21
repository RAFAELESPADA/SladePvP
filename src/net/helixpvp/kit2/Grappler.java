package net.helixpvp.kit2;

import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.Ejectable;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitHandler2;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.helix.pvp.kit.provider.EnderMageReal;
import net.helix.pvp.kit.provider.GladiatorListener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.server.v1_8_R3.EntityFishingHook;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntitySnowball;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftSnowball;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Grappler extends KitHandler2 implements Ejectable {


private Map<UUID, Hook> hooks = new HashMap<>();


public static int getDistance(Player e){
    Location loc = e.getLocation().clone();
    double y = loc.getBlockY();
    int distance = 0;
    for (double i = y; i >= 0; i--){
        loc.setY(i);
       if(loc.getBlock().getType().isSolid())break;
        distance++;
    }
    return distance;
}
@EventHandler
public void usarf(PlayerMoveEvent e) {
	Player p = e.getPlayer();
	if ((p.getItemInHand().getType().equals(Material.LEASH))) {
 			if (KitManager2.getPlayer(e.getPlayer().getName()).haskit2(this)) {
 				
 				Player target = p;
 		 	   if ((p.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura") - 12) && EnderMageReal.isSpawn(p.getLocation())  && !GladiatorListener.combateGlad.containsKey(target) && !net.helixpvp.kit2.GladiatorListener.combateGlad.containsKey(target)) {
 					p.sendMessage("§cDo not use grappler near spawn!");
			Location tp = p.getLocation();

			tp.setY(tp.getY()-getDistance(p));

			p.teleport(tp);
			return;
		
 	   }}}}
@EventHandler(priority = EventPriority.LOWEST)
public void onPlayerInteract(PlayerInteractEvent event) {
	if (!KitManager2.getPlayer(event.getPlayer().getName()).haskit2(this))
		return;
	if (event.getItem() == null)
		return;
	Action a = event.getAction();
	Player p = event.getPlayer();
	ItemStack item = p.getItemInHand();
	if (!event.getPlayer().getItemInHand().getType().equals(Material.LEASH))
		return;
	if (a.name().contains("RIGHT")) {
		event.setCancelled(true);
	}
	p.updateInventory();
	if (hasCooldown(p)) {
		p.playSound(p.getLocation(), Sound.IRONGOLEM_HIT, 0.5F, 1.0F);
		sendMessageCooldown(p);
		return;
	}
	Player target = p;
	if ((p.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura") - 12) && EnderMageReal.isSpawn(p.getLocation()) && !GladiatorListener.combateGlad.containsKey(target) && !net.helixpvp.kit2.GladiatorListener.combateGlad.containsKey(target)) {
			p.sendMessage("§cDo not use the grappler near spawn!");
			Location tp = p.getLocation();

			tp.setY(tp.getY()-getDistance(p));

			p.teleport(tp);
			return;
		} 
	if (event.getAction().name().contains("LEFT")) {
		eject(p);
		Hook hook = new Hook(p.getWorld(), ((CraftPlayer) p).getHandle());
		Vector direction = p.getLocation().getDirection();
		hook.spawn(p.getEyeLocation().add(direction.getX(), direction.getY(), direction.getZ()));
		hook.move(direction.getX() * 5.0D, direction.getY() * 5.0D, direction.getZ() * 5.0D);
		hooks.put(p.getUniqueId(), hook);
	} else if (event.getAction().name().contains("RIGHT")) {
		if (hooks.containsKey(p.getUniqueId())) {
			if (!hooks.get(p.getUniqueId()).isHooked())
				return;
			Hook hook = hooks.get(p.getUniqueId());
			Location loc = hook.getBukkitEntity().getLocation();
			Location pLoc = p.getLocation();
			double d = loc.distance(p.getLocation());
			double t = d;
			double v_x = (1.0D + 0.04000000000000001D * t)
					* ((isNear(loc, pLoc) ? 0 : loc.getX() - pLoc.getX()) / t);
			double v_y = (0.9D + 0.03D * t) * ((isNear(loc, pLoc) ? 0.1 : loc.getY() - pLoc.getY()) / t);
			double v_z = (1.0D + 0.04000000000000001D * t)
					* ((isNear(loc, pLoc) ? 0 : loc.getZ() - pLoc.getZ()) / t);
			Vector v = p.getVelocity();
			v.setX(v_x);
			v.setY(v_y);
			v.setZ(v_z);
			p.setVelocity(v.multiply((((double) 12) / 10)));
			double playerY = p.getLocation().getY();
			double grapplerY = hook.getBukkitEntity().getLocation().getY();
			if (playerY < grapplerY || playerY > grapplerY)
				p.setFallDistance(0);
			p.getWorld().playSound(p.getLocation(), Sound.STEP_GRAVEL, 1.0F, 1.0F);
		}
	}
}

@EventHandler
public void onPlayerItemHeldListener(PlayerItemHeldEvent e) {
	if (hooks.containsKey(e.getPlayer().getUniqueId())) {
		hooks.get(e.getPlayer().getUniqueId()).remove();
		hooks.remove(e.getPlayer().getUniqueId());
	}
}

@EventHandler
public void onPlayerQuitListener(PlayerQuitEvent e) {
	if (hooks.containsKey(e.getPlayer().getUniqueId())) {
		hooks.remove(e.getPlayer().getUniqueId());
		hooks.get(e.getPlayer().getUniqueId()).remove();
	}
}

@EventHandler(priority = EventPriority.HIGHEST)
public void onPlayerLeash(PlayerLeashEntityEvent event) {
	if (!KitManager2.getPlayer(event.getPlayer().getName()).haskit2(this))
		return;
	Player p = event.getPlayer();
	if (p.getItemInHand() == null)
		return;
	ItemStack item = p.getItemInHand();
	if (!event.getPlayer().getItemInHand().getType().equals(Material.LEASH))
		return;
	event.setCancelled(true);
	if (hooks.containsKey(p.getUniqueId())) {
		if (hooks.get(p.getUniqueId()).isHooked()) {
			Hook hook = hooks.get(p.getUniqueId());
			Location loc = hook.getBukkitEntity().getLocation();
			Location playerLoc = p.getLocation();
			double d = loc.distance(playerLoc);
			double t = d;
			double v_x = (1.0D + 0.04000000000000001D * t)
					* ((isNear(loc, playerLoc) ? 0 : loc.getX() - playerLoc.getX()) / t);
			double v_y = (0.9D + 0.03D * t) * ((isNear(loc, playerLoc) ? 0.1 : loc.getY() - playerLoc.getY()) / t);
			double v_z = (1.0D + 0.04000000000000001D * t)
					* ((isNear(loc, playerLoc) ? 0 : loc.getZ() - playerLoc.getZ()) / t);
			Vector v = p.getVelocity();
			v.setX(v_x);
			v.setY(v_y);
			v.setZ(v_z);
			p.setVelocity(v.multiply((((double) 12) / 10)));
			double player = p.getLocation().getY();
			double grappler = hook.getBukkitEntity().getLocation().getY();
			if (player < grappler || player > grappler)
				p.setFallDistance(0);
			p.getWorld().playSound(playerLoc, Sound.STEP_GRAVEL, 1.0F, 1.0F);
		}
	}
}

@EventHandler
public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
    if (event.isCancelled()) {
        return;
    }
    if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
        final Player player = (Player) event.getEntity();
        
        if (KitManager2.getPlayer(player.getName()).haskit2(this)) {
        	addCooldown(player , HelixPvP.getInstance().getConfig().getInt("GrapplerCooldown"));
        }
    }
}

private boolean isNear(Location loc, Location playerLoc) {
	return loc.distance(playerLoc) < 1.5;
}

@Override
public void eject(Player player) {
	Hook hook = hooks.get(player.getUniqueId());
	if (hook != null) {
		hook.remove();
		hooks.remove(player.getUniqueId());
	}
}

class Hook extends EntityFishingHook {

	private Snowball sb;
	private EntitySnowball controller;
	public int a;
	public EntityHuman owner;
	public Entity hooked;
	public boolean lastControllerDead;
	public boolean isHooked;

	public Hook(org.bukkit.World world, EntityHuman entityhuman) {
		super(((CraftWorld) world).getHandle(), entityhuman);
		this.owner = entityhuman;
	}

	@Override
	public void t_() {
		this.lastControllerDead = this.controller.dead;
		for (Entity entity : this.controller.world.getWorld().getEntities()) {
			if (entity instanceof Firework)
				continue;
			if (entity instanceof Snowball)
				continue;
			if (entity.getEntityId() == getBukkitEntity().getEntityId())
				continue;
			if (entity.getEntityId() == this.owner.getBukkitEntity().getEntityId())
				continue;
			if (entity.getEntityId() == this.controller.getBukkitEntity().getEntityId())
				continue;
			if (entity.getLocation().distance(this.controller.getBukkitEntity().getLocation()) > 2.0D)
				continue;
			this.controller.die();
			this.hooked = entity;
			this.isHooked = true;
			this.locX = entity.getLocation().getX();
			this.locY = entity.getLocation().getY();
			this.locZ = entity.getLocation().getZ();
			this.motX = 0.0D;
			this.motY = 0.04D;
			this.motZ = 0.0D;
		}
		try {
			this.locX = this.hooked.getLocation().getX();
			this.locY = this.hooked.getLocation().getY();
			this.locZ = this.hooked.getLocation().getZ();
			this.motX = 0.0D;
			this.motY = 0.04D;
			this.motZ = 0.0D;
			this.isHooked = true;
		} catch (Exception e) {
			if (this.controller.dead) {
				this.isHooked = true;
			}
			this.locX = this.controller.locX;
			this.locY = this.controller.locY;
			this.locZ = this.controller.locZ;
		}
	}

	public void die() {
	}

	public void remove() {
		super.die();
	}

	public void spawn(Location location) {
		this.sb = (Snowball) this.owner.getBukkitEntity().launchProjectile(Snowball.class);
		this.controller = ((CraftSnowball) this.sb).getHandle();
		PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(new int[] { this.controller.getId() });
		for (Player p : Bukkit.getOnlinePlayers()) {
			((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
		}
		((CraftWorld) location.getWorld()).getHandle().addEntity(this);
	}

	public boolean isHooked() {
		return this.isHooked;
	}

	public void setHookedEntity(Entity damaged) {
		this.hooked = damaged;
	}
}
}

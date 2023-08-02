package net.helix.pvp.kit.provider;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftSnowball;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.util.Vector;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;
import net.minecraft.server.v1_8_R3.EntityFishingHook;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntitySnowball;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;

public class Grappler extends KitHandler {

	 @Override
	    public void execute(Player player) {
	        super.execute(player);

	        player.getInventory().setItem(1, new ItemBuilder("�aLa�o!", Material.LEASH)
	                .addEnchant(Enchantment.KNOCKBACK, 1)
	        				.addFlags(ItemFlag.HIDE_ENCHANTS)
	                .nbt("cancel-drop")
	                .toStack()
	        );
	        
	    }
	

private Map<Player, Cordinha> hooks = new HashMap<>();
private HashMap<Player, Long> leftClickGrappler = new HashMap<Player, Long>();
private HashMap<Player, Long> rightClickGrappler = new HashMap<Player, Long>();

@EventHandler
public void removerCordaAoTrocarSlot(PlayerItemHeldEvent e) {
	if (hooks.containsKey(e.getPlayer())) {
 	    ((Cordinha)hooks.get(e.getPlayer())).remove();
 	    hooks.remove(e.getPlayer());
	}
}
 	  
@EventHandler
public void GrapplerFallNerf(EntityDamageEvent e) {
	if (!(e.getEntity() instanceof Player))
 	      return;
	
	Player player = (Player)e.getEntity();
	if (!e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
		return;
	}
	
 	if ((hooks.containsKey(player)) && (((Cordinha)hooks.get(player)).isHooked()) && (e.getDamage() > 5.0D)) {
 	     e.setDamage(0.0D);   
 	}
}
 	  
@EventHandler
public void removeCorda(PlayerMoveEvent e) {
	if ((hooks.containsKey(e.getPlayer())) && (!e.getPlayer().getItemInHand().getType().equals(Material.LEASH))) {
 	    ((Cordinha)hooks.get(e.getPlayer())).remove();
 	     hooks.remove(e.getPlayer());
	}
}
 	  
@EventHandler
public void onLeash(PlayerLeashEntityEvent e) {
	if (KitManager.getPlayer(e.getPlayer().getName()).hasKit()) {
		e.setCancelled(true);
		e.getPlayer().updateInventory();
	}
}
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
public void usar(PlayerInteractEvent e) {
	Player p = e.getPlayer();
	
 	if ((p.getItemInHand().getType().equals(Material.LEASH)))
 			if (KitManager.getPlayer(e.getPlayer().getName()).hasKit(this)) {
 				
 	    if (inCooldown(p) && KitManager.getPlayer(p.getName()).hasKit(this)) {
 	   	sendMessageCooldown(p);
 	   	return;
 	   }
 	   else if ((p.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura") - 12) && EnderMageReal.isSpawn(p.getLocation())) {
			p.sendMessage("§cNão use o grappler perto no spawn!");
			Location tp = p.getLocation();

			tp.setY(tp.getY()-getDistance(p));

			p.teleport(tp);
			return;
		}
 			
 	     
 	     if ((e.getAction() == Action.LEFT_CLICK_AIR) || (e.getAction() == Action.LEFT_CLICK_BLOCK)) {
			  if (leftClickGrappler.containsKey(p) && leftClickGrappler.get(p) > System.currentTimeMillis())
				  return;
			  e.setCancelled(true);
 	          if (hooks.containsKey(p)) 
 	            ((Cordinha)hooks.get(p)).remove();
 	         
 	          Cordinha nmsHook = new Cordinha(p.getWorld(), ((CraftPlayer)p).getHandle());
 	          nmsHook.spawn(p.getEyeLocation().add(p.getLocation().getDirection().getX() > 20 ? 20 : p.getLocation().getDirection().getX() , p.getLocation().getDirection().getY() > 20 ? 20 : p.getLocation().getDirection().getY(), p.getLocation().getDirection().getZ() > 20 ? p.getLocation().getDirection().getZ() : 20));
 	          nmsHook.move(p.getLocation().getDirection().getX() > 14 ? 14 : p.getLocation().getDirection().getX() , p.getLocation().getDirection().getY() > 14 ? 14 : p.getLocation().getDirection().getY(), p.getLocation().getDirection().getZ() > 14 ? 14 : p.getLocation().getDirection().getZ());
 	          hooks.put(p, nmsHook);
 			  leftClickGrappler.put(p, System.currentTimeMillis() + 250L);
 	     } else {
 	    	 if (!hooks.containsKey(p))
 	             return;
 	    	e.setCancelled(true);
		     if (rightClickGrappler.containsKey(p) && rightClickGrappler.get(p) > System.currentTimeMillis())
				 return;
 	    	 
 	         if (!((Cordinha)hooks.get(p)).isHooked()) 
 	             return;
 	          
 	         //DamageListener.addBypassVelocity(p);
 	         rightClickGrappler.put(p, System.currentTimeMillis() + 150L);
 	         double d = ((Cordinha)hooks.get(p)).getBukkitEntity().getLocation().distance(p.getLocation());
 	         double t = d;
 	         double v_x = (1.0D + 0.07000000000000001D * t) * (((Cordinha)hooks.get(p)).getBukkitEntity().getLocation().getX() - p.getLocation().getX()) / t;
 	         double v_y = (1.0D + 0.03D * t) * (((Cordinha)hooks.get(p)).getBukkitEntity().getLocation().getY() - p.getLocation().getY()) / t;
 	         double v_z = (1.0D + 0.07000000000000001D * t) * (((Cordinha)hooks.get(p)).getBukkitEntity().getLocation().getZ() - p.getLocation().getZ()) / t;
 	         Vector v = p.getVelocity();
 	         v.setX(v_x);
 	         v.setY(v_y);
 	         v.setZ(v_z);
 	         p.setVelocity(v);
 	         p.playSound(p.getLocation(), Sound.STEP_GRAVEL, 10.0F, 10.0F);
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
        
        if (KitManager.getPlayer(player.getName()).hasKit(this)) {
        	addCooldown(player , HelixPvP.getInstance().getConfig().getInt("GrapplerCooldown"));
        }
    }
}

public class Cordinha extends EntityFishingHook {

	private Snowball sb;
    private EntitySnowball controller;
    public EntityHuman owner;
    public Entity hooked;
    public boolean lastControllerDead, isHooked;
  
    public Cordinha(org.bukkit.World world, EntityHuman entityhuman) {
    	super(((CraftWorld)world).getHandle(), entityhuman);
        this.owner = entityhuman;
    }
  
    protected void c() {}
  
    public void t_() {
    	
    if ((!this.lastControllerDead) && (this.controller.dead))
         ((Player)this.owner.getBukkitEntity()).sendMessage("§aSua cordinha prendeu em algo.");
    
    this.lastControllerDead = this.controller.dead;
    for (Entity entity : this.controller.world.getWorld().getEntities()) {
         if (!(entity instanceof LivingEntity)) {
        	  continue;
         }	 
         if ((!(entity instanceof Firework)) && (entity.getEntityId() != getBukkitEntity().getEntityId()) && 
         (entity.getEntityId() != this.owner.getBukkitEntity().getEntityId()) && 
         (entity.getEntityId() != this.controller.getBukkitEntity().getEntityId()) && (
         (entity.getLocation().distance(this.controller.getBukkitEntity().getLocation()) < 2.0D) || (((entity instanceof Player)) && 
         (((Player)entity).getEyeLocation().distance(this.controller.getBukkitEntity().getLocation()) < 2.0D)))) {
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
    	if (this.controller.dead)
            this.isHooked = true;
        this.locX = this.controller.locX;
        this.locY = this.controller.locY;
        this.locZ = this.controller.locZ;
    }
    }
  
    public void die() {}
  
    public void remove() {
    	super.die();
    }
  
    public void spawn(Location location) {
    	this.sb = ((Snowball)this.owner.getBukkitEntity().launchProjectile(Snowball.class));
        this.sb.setVelocity(this.sb.getVelocity().multiply(2.25D));
        this.controller = ((CraftSnowball)this.sb).getHandle();
    
        PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(new int[] { this.controller.getId() });
    
        for (Player p : Bukkit.getOnlinePlayers()) 
             ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);

        ((CraftWorld)location.getWorld()).getHandle().addEntity(this);
    }
  
    public boolean isHooked() {
    	return this.isHooked;
    }
  
    public void setHookedEntity(Entity damaged) {
    	this.hooked = damaged;
    }
}
}

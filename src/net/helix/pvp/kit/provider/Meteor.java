package net.helix.pvp.kit.provider;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.Habilidade;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.md_5.bungee.api.ChatColor;

public class Meteor extends KitHandler {

ArrayList<String> danometeor = new ArrayList();
ArrayList<Player> subiu = new ArrayList();
@EventHandler
/*     */   public void BotaStomper2(EntityDamageEvent e)
/*     */   {
	if (!(e.getEntity() instanceof Player)) {
		return;
	}
	if (!KitManager.getPlayer(e.getEntity().getName()).hasKit(this)) {
		return;
	}
	if (!danometeor.contains(e.getEntity().getName())) {
		return;
	}
	if (!(e.getCause() == DamageCause.FALL)) {
		return;
	}
	e.setCancelled(true);
	Player p = (Player)e.getEntity();
	for (Entity ent : p.getNearbyEntities(5, 5, 5)) {
		if (!(ent instanceof Player)) {
			return;
		}
			Player p2 = (Player)ent;
			if (!KitManager.getPlayer(p2.getName()).hasKit()) {
				return;
			}
			  else if (p2.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura")) {
					return;
				 }
			p2.damage(10);
			p2.getWorld().strikeLightning(p2.getLocation());
			p2.setFireTicks(80);
			p2.sendMessage(ChatColor.RED + "Você foi atingido por um Meteor!");
			danometeor.remove(e.getEntity().getName());
		}
	}
	

@EventHandler
/*     */   public void BotaStomper(PlayerInteractEvent e)
/*     */   {
/*  84 */     final Player p = e.getPlayer();
/*  85 */     if (!KitManager.getPlayer(e.getPlayer().getName()).hasKit(this) && (e.getAction() == Action.RIGHT_CLICK_BLOCK) || (e.getAction() == Action.RIGHT_CLICK_AIR))
/*     */     {
	if ((e.getPlayer().getItemInHand().getType() != Material.FIREBALL)) {
		return;
	}
/*  87 */       e.setCancelled(true);
/*  88 */       p.updateInventory();
/*  89 */       if (hasCooldown(p))
/*     */       {
/*  91 */         sendMessageCooldown(p);
/*  92 */         return;
/*     */       }
else if (p.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura") && KitManager.getPlayer(e.getPlayer().getName()).hasKit(this)) {
	p.sendMessage("§cNão use o seu poder no spawn!");
	return;
 }
if (!subiu.contains(p)) {
/*  94 */       Vector vector = p.getEyeLocation().getDirection();
/*  95 */       vector.multiply(0.0F);
/*  96 */       vector.setY(6.0F);
/*  97 */       p.setVelocity(vector);
/*  98 */       Location loc = p.getLocation();
/*  99 */       p.playSound(loc, Sound.FIREWORK_BLAST, 1.0F, 1.0F);
Location location = p.getLocation();
for (final Entity pertos : p.getNearbyEntities(20, 20 , 20)) {
	  if (pertos instanceof Player) {
		  ((Player) pertos).playSound((Location)pertos.getLocation(), Sound.FIREWORK_LAUNCH, 1f, 1f);
}

location.getWorld().playEffect(location, Effect.FIREWORKS_SPARK, 15);

Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 106 */          p.playSound(loc, Sound.FIREWORK_BLAST, 1.0F, 1.0F);
location.getWorld().playEffect(location, Effect.FIREWORKS_SPARK, 15);

/*     */         }
/* 109 */       }, 20L);
Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 106 */          p.playSound(loc, Sound.FIREWORK_BLAST, 1.0F, 1.0F);
location.getWorld().playEffect(location, Effect.FIREWORKS_SPARK, 15);

/*     */         }
/* 109 */       }, 40L);
Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 106 */          p.playSound(loc, Sound.FIREWORK_BLAST, 1.0F, 1.0F);

location.getWorld().playEffect(location, Effect.FIREWORKS_SPARK, 15);
/*     */         }
/* 109 */       }, 60L);
Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 106 */          p.playSound(loc, Sound.FIREWORK_BLAST, 1.0F, 1.0F);

location.getWorld().playEffect(location, Effect.FIREWORKS_SPARK, 15);
/*     */         }
/* 109 */       }, 80L);
Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 106 */          p.playSound(loc, Sound.FIREWORK_BLAST, 1.0F, 1.0F);

location.getWorld().playEffect(location, Effect.FIREWORKS_SPARK, 15);
/*     */         }
/* 109 */       }, 100L);
Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 106 */          p.playSound(loc, Sound.FIREWORK_BLAST, 1.0F, 1.0F);

location.getWorld().playEffect(location, Effect.FIREWORKS_SPARK, 15);
/*     */         }
/* 109 */       }, 120L);
Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 106 */          p.playSound(loc, Sound.FIREWORK_BLAST, 1.0F, 1.0F);

location.getWorld().playEffect(location, Effect.FIREWORKS_SPARK, 15);
/*     */         }
/* 109 */       }, 130L);
Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 106 */          p.playSound(loc, Sound.FIREWORK_BLAST, 1.0F, 1.0F);

location.getWorld().playEffect(location, Effect.FIREWORKS_SPARK, 15);
/*     */         }
/* 109 */       }, 140L);
Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 106 */          p.playSound(loc, Sound.FIREWORK_BLAST, 1.0F, 1.0F);

location.getWorld().playEffect(location, Effect.FIREWORKS_SPARK, 15);
/*     */         }
/* 109 */       }, 155L);
Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 106 */          p.playSound(loc, Sound.FIREWORK_BLAST, 1.0F, 1.0F);

location.getWorld().playEffect(location, Effect.FIREWORKS_SPARK, 15);
/*     */         }
/* 109 */       }, 160L);
Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 106 */          p.playSound(loc, Sound.FIREWORK_BLAST, 1.0F, 1.0F);

/*     */         }
/* 109 */       }, 200L);
}

subiu.add(p);
}} else {
	if (!KitManager.getPlayer(e.getPlayer().getName()).hasKit(this)) {
		return;
	}
	if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK) || (e.getAction() == Action.RIGHT_CLICK_AIR)  || (e.getAction() == Action.LEFT_CLICK_AIR) || (e.getAction() == Action.LEFT_CLICK_BLOCK)) {
		return;
}
	if ((e.getPlayer().getItemInHand().getType() != Material.FIREBALL)) {
		return;
	}
	int l = (int)p.getEyeLocation().getDirection().multiply(6).add(new Vector(0, 0, 0)).getY();
	if(p.getLocation().getPitch() >= -90 && p.getLocation().getPitch() <= -10) {
        p.sendMessage(ChatColor.RED + "Você só pode usar o meteor para baixo");
        return;
    }
	if (hasCooldown(p))
	/*     */       {
	/*  91 */         sendMessageCooldown(p);
	/*  92 */         return;
	/*     */       }
	 p.playSound(p.getLocation(), Sound.FIREWORK_BLAST, 1.0F, 1.0F);
	/*  76 */       p.setVelocity(p.getEyeLocation().getDirection().multiply(6).add(new Vector(0, 0, 0)));
	danometeor.add(p.getName());
	addCooldown(p , 40);
	subiu.remove(p);

/* 102 */       Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 106 */           if (KitManager2.getPlayer(p.getName()).haskit2()) {
    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
/* 107 */           p.sendMessage(ChatColor.GREEN + "Você pode usar o meteor novamente");
/*     */         }
}
}

/* 109 */       , 800L);
/*     */     }
/*     */   }
}

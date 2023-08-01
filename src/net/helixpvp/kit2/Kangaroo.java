package net.helixpvp.kit2;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitHandler2;
import net.helix.pvp.kit.KitManager2;
import net.helix.pvp.kit.provider.EnderMageReal;
import net.helix.pvp.kit.provider.GladiatorListener;

public class Kangaroo extends KitHandler2 {
	
	private final static List<String> cooldown = new ArrayList<>();
	ArrayList<String> tempo = new ArrayList();

	/*  51 */   ArrayList<String> naofugir = new ArrayList();
	@Override
	public void execute(Player player) {
		super.execute(player);
		
		player.getInventory().setItem(2, new ItemBuilder("§aPular!", Material.FIREWORK)
				.nbt("kit-handler", "kangaroo")
				.nbt("cancel-drop")
				.toStack()
		);
	}
	

		/*  49 */   
		/*     */   
		/*     */   @EventHandler
		/*     */   public void pular(PlayerInteractEvent event)
		/*     */   {
			
		/*  56 */     Player p = event.getPlayer();


		/*  57 */     if (!KitManager2.getPlayer(p.getName()).haskit2()
				|| !event.hasItem() || !ItemBuilder.has(event.getItem(), "kit-handler", "kangaroo")) {
			return;
		}
		else if (p.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura") && EnderMageReal.isSpawn(p.getLocation())) {
			p.sendMessage("§cNão use o kangaroo no spawn!");
			event.setCancelled(true);
			return;
		}
		/*     */     {
		/*  60 */       event.setCancelled(true);

		/*  61 */       if (GladiatorListener.combateGlad.containsKey(p)) {
		/*  62 */         p.sendMessage(String.valueOf("§cVocê esta no Gladiator e recebeu efeito de speed"));
		/*     */         
		/*  64 */         darEfeito(p, org.bukkit.potion.PotionEffectType.SPEED, 10, 1);
		/*     */       }
		/*     */       else
		/*     */       {
		/*  68 */         event.setCancelled(true);
		/*  69 */         Vector vector = p.getEyeLocation().getDirection();
		/*  70 */         if (!this.naofugir.contains(p.getName()))
		/*     */         {
		/*  72 */           if (!this.tempo.contains(p.getName()))
		/*     */           {
		/*  74 */             this.tempo.add(p.getName());
		/*  75 */             if (!p.isSneaking())
		/*     */             {
		/*  77 */               p.setFallDistance(-1.0F);
		/*  78 */               vector.multiply(1.0F);
		/*  79 */               vector.setY(1.0D);
		/*  80 */               p.setVelocity(vector);
		/*     */             }
		/*     */             else
		/*     */             {
		/*  84 */               p.setFallDistance(-1.0F);
		/*  85 */               vector.multiply(2.0F);
		/*  86 */               vector.setY(0.5D);
		/*  87 */               p.setVelocity(vector);
		/*     */             }
		/*     */           }
		/*     */         }
		/*  91 */         else if (!this.tempo.contains(p.getName()))
		/*     */         {
		/*  93 */           this.tempo.add(p.getName());
		/*     */         }
		/*     */         
		/* 100 */         if (this.naofugir.contains(p.getName())) {
		/* 101 */           return;
		/*     */         }
		/*     */       }
		/*     */     }
		/*     */   }
		public static void darEfeito(Player p, PotionEffectType tipo, int duracao, int level)
		/*     */   {
		/* 349 */     p.addPotionEffect(new PotionEffect(tipo, 20 * duracao, level));
		/*     */   }
		/*     */   
		/*     */   @EventHandler
		/*     */   public void onDamageds(EntityDamageEvent event)
		/*     */   {
		/* 110 */     Entity e = event.getEntity();
		/* 111 */     if ((e instanceof Player))
		/*     */     {
		/* 113 */       Player player = (Player)e;
		/* 114 */       if (((event.getEntity() instanceof Player)) && 
		/* 115 */         (event.getCause() == EntityDamageEvent.DamageCause.FALL) && 
		/* 116 */         (player.getInventory().contains(Material.FIREWORK)) && 
		/* 117 */         (event.getDamage() >= 7.0D)) {
		/* 118 */         event.setDamage(7.0D);
		/*     */       }
		/*     */     }
		/*     */   }
		 @EventHandler
		 /*     */   public void onDrop(PlayerDropItemEvent event)
		 /*     */   {
		 /* 116 */     if (event.getItemDrop().getItemStack().getType() == Material.STONE_SWORD || event.getItemDrop().getItemStack().getType() == Material.STONE_PICKAXE) {
		 /* 117 */       event.setCancelled(true);
		 /*     */     }
		 }
		/*     */   
		/*     */   @EventHandler
		/*     */   public void removertempo(PlayerMoveEvent event)
		/*     */   {
		/* 126 */     Player p = event.getPlayer();
		/* 127 */     if (this.tempo.contains(p.getName()))
		/*     */     {
		/* 129 */       Block b = p.getLocation().getBlock();
		/* 130 */       if ((b.getType() != Material.AIR) || (b.getRelative(BlockFace.DOWN).getType() != Material.AIR)) {
		/* 131 */         this.tempo.remove(p.getName());
		/*     */       }
		/*     */     }
		/*     */   }
		/*     */   
		/*     */   @EventHandler
		/*     */   public void adicionartempo(EntityDamageByEntityEvent event)
		/*     */   {
		/* 139 */     if (!(event.getEntity() instanceof Player)) {
		/* 140 */       return;
		/*     */     }
		/* 142 */     final Player kangaroo = (Player)event.getEntity();
		/* 143 */     if ((event.getDamager() instanceof Player))
		/*     */     {
		/* 145 */       Player p = (Player)event.getDamager();
		/* 146 */       if (((kangaroo instanceof Player)) && ((p instanceof Player)) && 
		/* 147 */         (kangaroo.getInventory().contains(Material.FIREWORK)))
		/*     */       {
		/* 149 */         this.naofugir.add(kangaroo.getName());
		/* 150 */         org.bukkit.Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable()
		/*     */         {
		/*     */           public void run()
		/*     */           {
		/* 154 */             Kangaroo.this.naofugir.remove(kangaroo.getName());
		/*     */           }
		/* 156 */         }, 60L);
		/*     */       }
		/*     */     }
		/*     */   }
}


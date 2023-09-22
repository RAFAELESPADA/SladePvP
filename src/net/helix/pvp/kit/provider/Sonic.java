package net.helix.pvp.kit.provider;

/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Effect;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
/*     */ import org.bukkit.util.Vector;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.core.util.HelixCooldown;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;

/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Sonic extends KitHandler 
/*     */ {
	@Override
	public void execute(Player player) {
		super.execute(player);
		
		player.getInventory().setItem(1, new ItemBuilder("§aSonic!", Material.LAPIS_BLOCK)
				.nbt("kit-handler", "sonic")
				.nbt("cancel-drop")
				.toStack()
		);
	}
/*  44 */   public int boost = Integer.valueOf(6).intValue();
/*  45 */  
/*  46 */   static ArrayList<String> fall = new ArrayList();
/*  47 */   public static HashMap<String, ItemStack[]> Armadura = new HashMap();
/*  48 */   public static HashMap<String, ItemStack[]> saveinv = new HashMap();
/*  49 */   public static HashMap<String, ItemStack[]> armadura = new HashMap();
/*  50 */   public static HashMap<String, ItemStack[]> Armadura2 = new HashMap();
/*  51 */   
/*     */   public static HelixPvP plugin;
/*     */   
/*     */   @EventHandler
/*     */   public void DeshClick(PlayerInteractEvent event)
/*     */   {
/*  57 */     
/*     */     
/*  59 */     final Player p = event.getPlayer();
/*  60 */     if ((event.getPlayer().getItemInHand().getType() == Material.LAPIS_BLOCK) && 
/*  61 */       (KitManager.getPlayer(p.getName()).hasKit(this)))
/*     */     {
/*  63 */       if ((event.getAction() == Action.LEFT_CLICK_AIR) || 
/*  64 */         (event.getAction() == Action.LEFT_CLICK_BLOCK) || 
/*  65 */         (event.getAction() == Action.RIGHT_CLICK_BLOCK) || 
/*  66 */         (event.getAction() == Action.RIGHT_CLICK_AIR)) {
/*  67 */         event.setCancelled(true);
/*     */       }

if (inCooldown(event.getPlayer()) && KitManager.getPlayer(event.getPlayer().getName()).hasKit(this)) {
	sendMessageCooldown(event.getPlayer());
	return;
}

/*  74 */     	addCooldown(event.getPlayer(), 30);
/*  75 */       fall.add(p.getName());
/*  76 */       p.setVelocity(p.getEyeLocation().getDirection().multiply(this.boost).add(new Vector(0, 0, 0)));
/*  77 */       p.getPlayer().getWorld().playEffect(p.getPlayer().getLocation(), Effect.SMOKE, 10, 0);
/*  78 */       Location loc = p.getLocation();
/*  79 */       for (Entity pertos : p.getNearbyEntities(8.0D, 8.0D, 8.0D)) {
/*  80 */         if ((pertos instanceof Player))
/*     */         {
	if (pertos.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura") || !KitManager.getPlayer(pertos.getName()).hasKit()) {
		p.sendMessage("Dont use the sonic on spawn!");
		return;
	}
/*  82 */           Player perto = (Player)pertos;
/*  83 */           ((Player)pertos).damage(10.0D);
/*  84 */           pertos.setVelocity(new Vector(0.1D, 0.0D, 0.1D));
/*  85 */           darEfeito(((Player)pertos), org.bukkit.potion.PotionEffectType.POISON, 6, 1);
/*     */         }
/*     */       }
/*  88 */       ItemStack Capacete = new ItemStack(Material.LEATHER_HELMET);
/*  89 */       LeatherArmorMeta kCapacete = (LeatherArmorMeta)Capacete.getItemMeta();
/*  90 */       kCapacete.setColor(Color.BLUE);
/*  91 */       Capacete.setItemMeta(kCapacete);
/*  92 */       Capacete.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
/*  93 */       ItemStack Peitoral = new ItemStack(Material.LEATHER_CHESTPLATE);
/*  94 */       LeatherArmorMeta kPeitoral = (LeatherArmorMeta)Peitoral.getItemMeta();
/*  95 */       kPeitoral.setColor(Color.BLUE);
/*  96 */       Peitoral.setItemMeta(kPeitoral);
/*  97 */       Peitoral.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
/*  98 */       ItemStack Calss = new ItemStack(Material.LEATHER_LEGGINGS);
/*  99 */       LeatherArmorMeta kCalss = (LeatherArmorMeta)Calss.getItemMeta();
/* 100 */       kCalss.setColor(Color.BLUE);
/* 101 */       Calss.setItemMeta(kCalss);
/* 102 */       Calss.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
/* 103 */       ItemStack Bota = new ItemStack(Material.LEATHER_BOOTS);
/* 104 */       LeatherArmorMeta kBota = (LeatherArmorMeta)Capacete.getItemMeta();
/* 105 */       kBota.setColor(Color.BLUE);
/* 106 */       Bota.setItemMeta(kBota);
/* 107 */       Bota.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
/* 108 */       Armadura.put(p.getName(), p.getInventory().getArmorContents());
/*     */       
/* 110 */       p.getInventory().setHelmet(Capacete);
/* 111 */       p.getInventory().setChestplate(Peitoral);
/* 112 */       p.getInventory().setLeggings(Calss);
/* 113 */       p.getInventory().setBoots(Bota);
/* 114 */       p.updateInventory();
/*     */       
/* 116 */       Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 120 */           ItemStack capacete0 = new ItemStack(Material.AIR);
/*     */           
/* 122 */           ItemStack peitoral0 = new ItemStack(Material.AIR);
/*     */           
/* 124 */           ItemStack calca0 = new ItemStack(Material.AIR);
/*     */           
/* 126 */           ItemStack Bota0 = new ItemStack(Material.AIR);
/* 127 */           p.getInventory().setHelmet(capacete0);
/* 128 */           p.getInventory().setChestplate(peitoral0);
/* 129 */           p.getInventory().setLeggings(calca0);
/* 130 */           p.getInventory().setBoots(Bota0);
/* 131 */           p.updateInventory();
/* 132 */           Sonic.fall.remove(p.getName());
/*     */         }
/* 134 */       }, 60L);
}
}
/*     */   public static void darEfeito(Player p, PotionEffectType tipo, int duracao, int level)
/*     */   {
/* 349 */     p.addPotionEffect(new PotionEffect(tipo, 20 * duracao, level));
/*     */   }



@EventHandler
public void aoinv(final InventoryClickEvent e) {
    try {
        final Player p = (Player)e.getWhoClicked();
        if (fall.contains(p.getName()) && e.getSlotType().equals((Object)InventoryType.SlotType.ARMOR)) {
            e.setCancelled(true);
        }
    }
    catch (Exception ex) {}
}
}

/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\SpecialAbility\Basic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\SpecialAbility\Deshfire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */


package net.helix.pvp.kit.provider;

import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;
import net.helixpvp.kit2.ItemManager;

import java.util.Arrays;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;



public class Avatar extends KitHandler {
  
  public static ItemStack getItem(ItemStack item) {
    ItemManager itemmanager = new ItemManager(Material.WOOL, "§aAvatar");
    if (item.getType() == Material.BEACON) {
      itemmanager.setMaterial(Material.WOOL);
      itemmanager.setNome("§fAr");
      item.setType(Material.WOOL);
      return item;
    } 
    if (item.getType() == Material.WOOL) {
      itemmanager.setMaterial(Material.REDSTONE_BLOCK);
      itemmanager.setNome("§cFogo");
      item.setType(Material.REDSTONE_BLOCK);
      return item;
    } 
    if (item.getType() == Material.REDSTONE_BLOCK) {
      itemmanager.setMaterial(Material.LAPIS_BLOCK);
      itemmanager.setNome("§bÁgua");
      item.setType(Material.LAPIS_BLOCK);
      return item;
    } 
    if (item.getType() == Material.LAPIS_BLOCK) {
      itemmanager.setMaterial(Material.GRASS);
      itemmanager.setNome("§6Terra");
      item.setType(Material.GRASS);
      return item;
    } 
    if (item.getType() == Material.GRASS) {
      itemmanager.setMaterial(Material.WOOL);
      itemmanager.setNome("§fAr");
      item.setType(Material.WOOL);
      return item;
    } 
    itemmanager.setNome("§aAvatar");
    return item;
  }
  @EventHandler
  public void asd(PlayerDropItemEvent e) {
	  if (KitManager.getPlayer(e.getPlayer().getName()).hasKit(this)) {
		  Item item = e.getItemDrop();
		  if (item == new ItemStack(Material.LAPIS_BLOCK)) {
			  e.setCancelled(true);
		  }
		  if (item == new ItemStack(Material.REDSTONE_BLOCK)) {
			  e.setCancelled(true);
		  }
		  if (item == new ItemStack(Material.GRASS)) {
			  e.setCancelled(true);
		  }
		  if (item == new ItemStack(Material.WOOL)) {
			  e.setCancelled(true);
		  }
	  }
  }
  @EventHandler
  public void asd(PlayerInteractEvent e) {
    Player player = e.getPlayer();
    if (KitManager.getPlayer(player.getName()).hasKit(this)) {
      if (player.getItemInHand().getType() == Material.AIR)
        return; 
      if (player.getItemInHand() == null)
        return; 
      if (player.getItemInHand().getType() == Material.AIR)
        return; 
      if (!player.getItemInHand().hasItemMeta())
        return; 
      if (!player.getItemInHand().getItemMeta().hasDisplayName())
        return; 
      if (!player.getItemInHand().getItemMeta().getDisplayName().equals("§bAvatar!"))
        return;
      else if (player.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura")) {
      	player.sendMessage("§cNão use o seu poder no spawn!");
  		return;
  	 }
   
      if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
        player.setItemInHand(getItem(player.getItemInHand()));
        
      } else {
    	  if (net.helix.pvp.cooldown1.HelixCooldown2.hasCooldown(player, KitManager.getPlayer(player.getName()).getKit().getName())) {
  	        sendMessageCooldown(player);
  	        return;
  	      }
        addCooldown(player, 30);
        e.setCancelled(true);
        Material item = player.getItemInHand().getType();
        if (item == Material.WOOL) {
          Vector Ferro = player.getLocation().getDirection().normalize().multiply(55);
          Snowball FerroH = (Snowball)player.launchProjectile(Snowball.class);
          FerroH.setVelocity(Ferro);
          FerroH.setMetadata("Vento", (MetadataValue)new FixedMetadataValue(HelixPvP.getInstance(), Boolean.valueOf(true)));
          Location pegou = player.getEyeLocation();
          BlockIterator Ferrao = new BlockIterator(pegou, 0.0D, 40);
          while (Ferrao.hasNext()) {
            Location Ferrao2 = Ferrao.next().getLocation();
            Effect camelo = Effect.STEP_SOUND;
            player.playSound(player.getLocation(), Sound.STEP_WOOL, 5.5F, 5.5F);
            player.playSound(player.getLocation(), Sound.STEP_WOOL, 1.5F, 1.5F);
            player.playSound(player.getLocation(), Sound.STEP_WOOL, 2.5F, 2.5F);
            player.playSound(player.getLocation(), Sound.STEP_WOOL, 3.5F, 3.5F);
            player.playSound(player.getLocation(), Sound.STEP_WOOL, 4.5F, 4.5F);
            player.getWorld().playEffect(Ferrao2, camelo, 35);
          } 
        } else if (item == Material.LAPIS_BLOCK) {
          Vector Ferro = player.getLocation().getDirection().normalize().multiply(55);
          Snowball FerroH = (Snowball)player.launchProjectile(Snowball.class);
          FerroH.setVelocity(Ferro);
          FerroH.setMetadata("Agua", (MetadataValue)new FixedMetadataValue(HelixPvP.getInstance(), Boolean.valueOf(true)));
          Location pegou = player.getEyeLocation();
          BlockIterator Ferrao = new BlockIterator(pegou, 0.0D, 40);
          while (Ferrao.hasNext()) {
            Location Ferrao2 = Ferrao.next().getLocation();
            Effect camelo = Effect.WATERDRIP;
            player.playSound(player.getLocation(), Sound.STEP_WOOD, 5.5F, 5.5F);
            player.playSound(player.getLocation(), Sound.STEP_WOOD, 1.5F, 1.5F);
            player.playSound(player.getLocation(), Sound.STEP_WOOD, 2.5F, 2.5F);
            player.playSound(player.getLocation(), Sound.STEP_WOOD, 3.5F, 3.5F);
            player.playSound(player.getLocation(), Sound.STEP_WOOD, 4.5F, 4.5F);
            player.getWorld().playEffect(Ferrao2, camelo, 35);
          } 
        } else if (item == Material.REDSTONE_BLOCK) {
          Vector Ferro = player.getLocation().getDirection().normalize().multiply(55);
          Snowball FerroH = (Snowball)player.launchProjectile(Snowball.class);
          FerroH.setVelocity(Ferro);
          FerroH.setMetadata("Fogo", (MetadataValue)new FixedMetadataValue(HelixPvP.getInstance(), Boolean.valueOf(true)));
          Location pegou = player.getEyeLocation();
          BlockIterator Ferrao = new BlockIterator(pegou, 0.0D, 40);
          while (Ferrao.hasNext()) {
            Location Ferrao2 = Ferrao.next().getLocation();
            Effect camelo = Effect.LAVADRIP;
            player.playSound(player.getLocation(), Sound.FIRE, 5.5F, 5.5F);
            player.playSound(player.getLocation(), Sound.FIRE, 1.5F, 1.5F);
            player.playSound(player.getLocation(), Sound.FIRE, 2.5F, 2.5F);
            player.playSound(player.getLocation(), Sound.FIRE, 3.5F, 3.5F);
            player.playSound(player.getLocation(), Sound.FIRE, 4.5F, 4.5F);
            player.getWorld().playEffect(Ferrao2, camelo, 35);
          } 
        } else if (item == Material.GRASS) {
          Vector Ferro = player.getLocation().getDirection().normalize().multiply(55);
          Snowball FerroH = (Snowball)player.launchProjectile(Snowball.class);
          FerroH.setVelocity(Ferro);
          FerroH.setMetadata("Terra", (MetadataValue)new FixedMetadataValue(HelixPvP.getInstance(), Boolean.valueOf(true)));
          Location pegou = player.getEyeLocation();
          BlockIterator Ferrao = new BlockIterator(pegou, 0.0D, 40);
          while (Ferrao.hasNext()) {
            Location Ferrao2 = Ferrao.next().getLocation();
            Effect camelo = Effect.EXTINGUISH;
            player.playSound(player.getLocation(), Sound.STEP_GRASS, 5.5F, 5.5F);
            player.playSound(player.getLocation(), Sound.STEP_GRASS, 1.5F, 1.5F);
            player.playSound(player.getLocation(), Sound.STEP_GRASS, 2.5F, 2.5F);
            player.playSound(player.getLocation(), Sound.STEP_GRASS, 3.5F, 3.5F);
            player.playSound(player.getLocation(), Sound.STEP_GRASS, 4.5F, 4.5F);
            player.getWorld().playEffect(Ferrao2, camelo, 35);
          } 
        } 
      } 
    } 
  }
  
  @EventHandler
  public void asd(EntityDamageByEntityEvent e) {
    Entity entity = e.getEntity();
    if (e.getDamager() instanceof Snowball) {
      Snowball snow = (Snowball)e.getDamager();
      if (snow.hasMetadata("Terra")) {
        ((LivingEntity)entity).damage(10.0D);
        Vector vector = entity.getLocation().getDirection();
        vector.multiply(-3.5F);
        entity.setVelocity(vector);
      } else if (snow.hasMetadata("Fogo")) {
        ((LivingEntity)entity).damage(9.0D);
        entity.setFireTicks(200);
      } else if (snow.hasMetadata("Agua")) {
        ((LivingEntity)entity).damage(8.0D);
        ((LivingEntity)entity).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 3));
        Vector vector = entity.getLocation().getDirection();
        vector.multiply(-1.0F);
        entity.setVelocity(vector);
      } else if (snow.hasMetadata("Vento")) {
        ((LivingEntity)entity).damage(12.0D);
        ((LivingEntity)entity).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 3));
      } 
    } 
  }
}
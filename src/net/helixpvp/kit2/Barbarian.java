package net.helixpvp.kit2;


import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.Habilidade;
import net.helix.pvp.kit.Habilidade2;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.HelixKit2;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitHandler2;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.sk89q.worldedit.entity.Entity;

public class Barbarian extends KitHandler2 {
	@Override
	public void execute(Player player) {
		super.execute(player);
 
		player.getInventory().setItem(0, new ItemBuilder("§fEspada", Material.WOOD_SWORD)
				.nbt("cancel-drop")
				.toStack());
		Habilidade.setAbility(player, "Barbarian2");
		}
		  @EventHandler
	  public void onKill(PlayerDeathEvent e) {
	    if (e.getEntity().getKiller() instanceof Player) {
	    	if (e.getEntity() instanceof Player) {
	      Player k = e.getEntity().getKiller();
	      if (Habilidade2.getAbility(k) != "Barbarian2") {
	    	  Bukkit.getConsoleSender().sendMessage(k.getName() + " matou " + e.getEntity().getName());
	    	  return;
	      }
	       k.sendMessage(ChatColor.DARK_AQUA + "You killed a player using barbarian");
	        if (k.getInventory().getItemInHand().getType() == Material.WOOD_SWORD) {
	          k.setItemInHand(new ItemStack(Material.STONE_SWORD));
	          k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
	          k.sendMessage(ChatColor.GREEN + "UPGRADE! Level " + ChatColor.WHITE + "1");
	        } else if (k.getInventory().getItemInHand().getType() == Material.STONE_SWORD) {
	          k.getInventory().setItemInHand(new ItemStack(Material.IRON_SWORD));
	          k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
	          k.sendMessage(ChatColor.GREEN + "UPGRADE! Level " + ChatColor.WHITE + "2");
	        } else if (k.getInventory().getItemInHand().getType() == Material.IRON_SWORD) {
	          k.getInventory().setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
	          k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
	          k.sendMessage(ChatColor.GREEN + "UPGRADE! Level " + ChatColor.WHITE + "3");
	        } else if (k.getInventory().getItemInHand().getType() == Material.DIAMOND_SWORD) {
	          int max = 5;
	          if (k.getInventory().getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
	            int lvl = k.getInventory().getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL);
	            if (lvl + 1 <= max) {
	              k.getInventory().getItemInHand().removeEnchantment(Enchantment.DAMAGE_ALL);
	              k.getInventory().getItemInHand().addEnchantment(Enchantment.DAMAGE_ALL, lvl + 1);
	              k.updateInventory();
	              k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
	              if (lvl + 1 == 2) {
	                k.sendMessage(ChatColor.GREEN + "UPGRADE! Level " + ChatColor.WHITE + "5");
	              } else if (lvl + 1 == 3) {
	                k.sendMessage(ChatColor.GREEN + "UPGRADE! Level " + ChatColor.WHITE + "6");
	              } else if (lvl + 1 == 4) {
	                k.sendMessage(ChatColor.GREEN + "UPGRADE! Level " + ChatColor.WHITE + "7");
	              } else if (lvl + 1 == 5) {
	                k.sendMessage(ChatColor.GREEN + "UPGRADE! Level " + ChatColor.WHITE + "8");
	              } 
	            } else {
	            	 k.setItemInHand(new ItemStack(Material.WOOD_SWORD));
	                 k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
	              k.sendMessage(ChatColor.GOLD + "Voce esta no level maximo!");
	              k.sendMessage(ChatColor.GOLD + "Sua espada resetou!");
	            } 
	          } else {
	            k.getInventory().getItemInHand().addEnchantment(Enchantment.DAMAGE_ALL, 1);
	            k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
	            k.sendMessage(ChatColor.GREEN + "UPGRADE! Level " + ChatColor.WHITE + "4");
	          } 
	        }  
	    } 
	    }}}
	  

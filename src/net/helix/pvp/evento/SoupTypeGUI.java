package net.helix.pvp.evento;


import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.helix.pvp.HelixPvP;

public class SoupTypeGUI implements Listener {
	public static HashMap<String, Boolean> savecocoa = new HashMap();
	public static HashMap<String, Boolean> compass = new HashMap();
	public static HashMap<String, Boolean> blood = new HashMap();
    public static final ItemStack ICON = ItemUtils.getCustomItemStack(Material.valueOf(HelixPvP.getInstance().getConfig().getString("OptionsItem")), "§6Options", (String) null);


    

    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getCurrentItem() != null && event.getCurrentItem().getItemMeta() != null && event.getInventory().getName().contains("§6Options")) {
            if (event.getCurrentItem().getType() != Material.AIR) {
                event.setCancelled(true);
                player.closeInventory();
                if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Mushroom")) {
                    if (!savecocoa.containsKey(player.getName())) {
                        player.sendMessage("§cYour soup style is already mushroom.");
                    } else {
                        savecocoa.remove(player.getName());
                        player.sendMessage("§aYou change your style to: §eMUSHROOM§a.");
                    }
                }
                    else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Cocoa Beans")) {
                    if (savecocoa.containsKey(player.getName())) {
                        player.sendMessage("§cYour soup style is already cocoa beans.");
                    } else {
                    	savecocoa.put(player.getName(), true);
                         player.sendMessage("§aYou change your style to: §eCOCOA BEAN§a.");
                    }
                    }
                         else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Compass")) { 
                	  if (!compass.containsKey(player.getName())) {
                		  player.sendMessage("§cThe compass has been disabled.");
                		  compass.put(player.getName(), true);
                	  } else {
                		  player.sendMessage("§aThe compass has been enabled.");
                		  compass.remove(player.getName());
                }
                         }  else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Blood")) { 
                    	  if (!blood.containsKey(player.getName())) {
                    		  player.sendMessage("§aThe blood has been enabled.");
                    		  blood.put(player.getName(), true);
                    	  } else {
                    		  player.sendMessage("§cThe blood has been disabled.");
                    		  blood.remove(player.getName());
                    }
            }
            }
                }
                
            
        }
        
       
    

    public static void openGUI(Player player) {

        final Inventory inv = Bukkit.createInventory(null, 9, "§6Options");
        if (!savecocoa.containsKey(player.getName())) {
            inv.setItem(3, ItemUtils.getCustomItemStack(Material.RED_MUSHROOM, "§aMushroom", Arrays.asList("§7You will receive mushrooms.", " ", "§a§oSelected")));
            inv.setItem(5, ItemUtils.editItemStack(ItemUtils.cocoa, "§aCocoa Beans", Arrays.asList("§7You will receive cocoa beans.", " ", "§c§oNot Selected")));
        } else {
            inv.setItem(3, ItemUtils.getCustomItemStack(Material.RED_MUSHROOM, "§cMushroom", Arrays.asList("§7You will receive mushrooms.", " ", "§c§oNot Selected")));
            inv.setItem(5, ItemUtils.editItemStack(ItemUtils.cocoa, "§aCocoa Beans", Arrays.asList("§7You will receive cocoa beans.", " ", "§a§oSelected")));
        }
        if (!compass.containsKey(player.getName())) {
        	inv.setItem(4, ItemUtils.getCustomItemStack(Material.COMPASS, "§cCompass", Arrays.asList("§7Compass.", " ", "§aEnabled")));
        } else {
        	inv.setItem(4, ItemUtils.getCustomItemStack(Material.COMPASS, "§cCompass", Arrays.asList("§7Compass.", " ", "§cDisabled")));
        }
        if (blood.containsKey(player.getName())) {
        	inv.setItem(8, ItemUtils.getCustomItemStack(Material.REDSTONE, "§cBlood", Arrays.asList("§7Violence is active.", " ", "§aEnabled")));
        } else {
        	inv.setItem(8, ItemUtils.getCustomItemStack(Material.YELLOW_FLOWER, "§cBlood", Arrays.asList("§aViolence is not active", " ", "§cDisabled")));
        }
        player.openInventory(inv);
    }

}
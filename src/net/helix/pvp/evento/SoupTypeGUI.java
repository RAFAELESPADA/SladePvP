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
    public static final ItemStack ICON = ItemUtils.getCustomItemStack(Material.valueOf(HelixPvP.getInstance().getConfig().getString("OptionsItem")), "§6Opções", (String) null);

    @EventHandler
    private void onInteractSoupType(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.hasItem() && event.getItem().isSimilar(SoupTypeGUI.ICON)) {
            SoupTypeGUI.openGUI(player);
        }
    }

    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getCurrentItem() != null && event.getCurrentItem().getItemMeta() != null && event.getInventory().getName().contains("§6Opções")) {
            if (event.getCurrentItem().getType() != Material.AIR) {
                event.setCancelled(true);
                player.closeInventory();
                if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Cogumelos")) {
                    if (!savecocoa.containsKey(player.getName())) {
                        player.sendMessage("§cO seu estilo de sopa cogumelo já é o selecionado.");
                    } else {
                        savecocoa.remove(player.getName());
                        player.sendMessage("§aVocê alterou seu estilo de sopa para: §eCOGUMELOS§a.");
                    }
                }
                    else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Cocoa Beans")) {
                    if (savecocoa.containsKey(player.getName())) {
                        player.sendMessage("§cO seu estilo de sopa cocoa já é o selecionado.");
                    } else {
                    	savecocoa.put(player.getName(), true);
                         player.sendMessage("§aVocê alterou seu estilo de sopa para: §eCOCOA BEAN§a.");
                    }
                    }
                         else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Bússola")) { 
                	  if (!compass.containsKey(player.getName())) {
                		  player.sendMessage("§cA Bússola foi desativada.");
                		  compass.put(player.getName(), true);
                	  } else {
                		  player.sendMessage("§aA Bússola foi ativada.");
                		  compass.remove(player.getName());
                }
                         }  else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Sangue")) { 
                    	  if (!blood.containsKey(player.getName())) {
                    		  player.sendMessage("§aO Sangue foi ativado.");
                    		  blood.put(player.getName(), true);
                    	  } else {
                    		  player.sendMessage("§cO Sangue foi desativado.");
                    		  blood.remove(player.getName());
                    }
            }
            }
                }
                
            
        }
        
       
    

    public static void openGUI(Player player) {

        final Inventory inv = Bukkit.createInventory(null, 9, "§6Opções");
        if (!savecocoa.containsKey(player.getName())) {
            inv.setItem(3, ItemUtils.getCustomItemStack(Material.RED_MUSHROOM, "§aCogumelos", Arrays.asList("§7Você receberá cogumelos.", " ", "§a§oSelecionado")));
            inv.setItem(5, ItemUtils.editItemStack(ItemUtils.cocoa, "§cCocoa Beans", Arrays.asList("§7Você receberá cocoa beans.", " ", "§c§oNão selecionado")));
        } else {
            inv.setItem(3, ItemUtils.getCustomItemStack(Material.RED_MUSHROOM, "§cCogumelos", Arrays.asList("§7Você receberá cogumelos.", " ", "§c§oNão selecionado")));
            inv.setItem(5, ItemUtils.editItemStack(ItemUtils.cocoa, "§aCocoa Beans", Arrays.asList("§7Você receberá cocoa beans.", " ", "§a§oSelecionado")));
        }
        if (!compass.containsKey(player.getName())) {
        	inv.setItem(4, ItemUtils.getCustomItemStack(Material.COMPASS, "§cBússola", Arrays.asList("§7Bússola.", " ", "§aAtivado")));
        } else {
        	inv.setItem(4, ItemUtils.getCustomItemStack(Material.COMPASS, "§cBússola", Arrays.asList("§7Bússola.", " ", "§cDesativado")));
        }
        if (blood.containsKey(player.getName())) {
        	inv.setItem(8, ItemUtils.getCustomItemStack(Material.REDSTONE, "§cSangue", Arrays.asList("§7Violência está ativa.", " ", "§aAtivado")));
        } else {
        	inv.setItem(8, ItemUtils.getCustomItemStack(Material.YELLOW_FLOWER, "§cSangue", Arrays.asList("§aViolência não está ativa", " ", "§cDesativado")));
        }
        player.openInventory(inv);
    }

}
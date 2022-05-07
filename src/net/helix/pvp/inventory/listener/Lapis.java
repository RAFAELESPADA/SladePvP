package net.helix.pvp.inventory.listener;

import org.bukkit.DyeColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

import net.helix.pvp.HelixPvP;

public class Lapis implements Listener {
  private HelixPvP plugin;
  
  private ItemStack lapis;
  
  public Lapis(HelixPvP plugin) {
    this.plugin = plugin;
    Dye d = new Dye();
    d.setColor(DyeColor.BLUE);
    this.lapis = d.toItemStack();
    this.lapis.setAmount(3);
  }
  
  @EventHandler
  public void openInventoryEvent(InventoryOpenEvent e) {
    if (e.getInventory() instanceof EnchantingInventory) {
      e.getInventory().setItem(1, this.lapis);
      this.plugin.inventories.add((EnchantingInventory)e
          .getInventory());
    } 
  }
  
  @EventHandler
  public void closeInventoryEvent(InventoryCloseEvent e) {
    if (e.getInventory() instanceof EnchantingInventory && 
      this.plugin.inventories.contains(e
        .getInventory())) {
      e.getInventory().setItem(1, null);
      this.plugin.inventories.remove(e
          .getInventory());
    } 
  }
  
  @EventHandler
  public void inventoryClickEvent(InventoryClickEvent e) {
    if (e.getClickedInventory() instanceof EnchantingInventory && 
      this.plugin.inventories.contains(e
        .getInventory()) && 
      e.getSlot() == 1)
      e.setCancelled(true); 
  }
  
  @EventHandler
  public void enchantItemEvent(EnchantItemEvent e) {
    if (this.plugin.inventories.contains(e
        .getInventory()))
      e.getInventory().setItem(1, this.lapis); 
  }
}
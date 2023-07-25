package net.helix.pvp.command;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.helix.pvp.HelixPvP;

public class AutoSoup implements CommandExecutor, Listener {
  public static HashMap<String, ItemStack[]> saveinv = (HashMap)new HashMap<>();
  
  public static HashMap<String, ItemStack[]> armadura = (HashMap)new HashMap<>();
  
  ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
  
  ItemMeta msopa = this.sopa.getItemMeta();
  
  ItemStack sopa1 = new ItemStack(Material.MUSHROOM_SOUP);
  
  ItemMeta msopa1 = this.sopa.getItemMeta();
  
  ItemStack sopa2 = new ItemStack(Material.MUSHROOM_SOUP);
  
  ItemMeta msopa2 = this.sopa.getItemMeta();
  
  public AutoSoup(HelixPvP main) {}
  
  public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
    Player p = (Player)sender;
    if (cmd.getName().equalsIgnoreCase("autosoup")) {
      if (!p.hasPermission("kombo.cmd.report")) {
        p.sendMessage("Você não tem permissão.");
        return true;
      } 
      final Player testando = p.getServer().getPlayer(args[0]);
      p.openInventory((Inventory)testando.getInventory());
      saveinv.put(testando.getName(), testando.getInventory().getContents());
      armadura.put(testando.getName(), testando.getInventory().getArmorContents());
      testando.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 70, 999));
      testando.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 70, 999));
      this.sopa.setItemMeta(this.msopa);
      Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)HelixPvP.getInstance(), new Runnable() {
            public void run() {
              testando.getInventory().clear();
              testando.setHealth(5.0D);
              testando.getInventory().setItem(10, AutoSoup.this.sopa);
              testando.getInventory().setItem(11, AutoSoup.this.sopa1);
              testando.getInventory().setItem(12, AutoSoup.this.sopa2);
            }
          }, 20L);
      Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)HelixPvP.getInstance(), new Runnable() {
            public void run() {
              testando.getInventory().clear();
              testando.getInventory().setContents(AutoSoup.saveinv.get(testando.getName()));
              testando.getInventory().setArmorContents(AutoSoup.armadura.get(testando.getName()));
              testando.setHealth(testando.getMaxHealth());
            }
          },  50L);
    } 
    return false;
  }
}


package net.helix.pvp.kit.provider;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.HelixKit2;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;

public class HotPotato extends KitHandler {
  public static ArrayList<String> emhotpotato = new ArrayList<>();
  
  @EventHandler
  public void onInteract(PlayerInteractEntityEvent e) {
    final Player p = e.getPlayer();
    if (e.getRightClicked() instanceof Player) {
      final Player k = (Player)e.getRightClicked();
      if (p.getItemInHand().getType().equals(Material.BAKED_POTATO) && KitManager.getPlayer(e.getPlayer().getName()).hasKit(HelixKit.HOTPOTATO)) {
    	  if (inCooldown(p) && KitManager.getPlayer(p.getName()).hasKit(this)) {
    	 	   	sendMessageCooldown(p);
    	 	   	return;
    	 	   }
    	  else if (p.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura")) {
  			p.sendMessage("§cDont use the hotpotato on spawn!");
  			return;
  		}
    	  else if (KitManager.getPlayer(k.getName()).hasKit(HelixKit.NEO) || KitManager2.getPlayer(k.getName()).haskit2(HelixKit2.NEO)) {
				p.playSound(p.getLocation(), Sound.NOTE_BASS_DRUM, 15.0f, 15.0f);
				p.sendMessage(ChatColor.RED + "You cant use hotpotato on " + ChatColor.DARK_RED + k.getName() + ChatColor.RED + " because he has the kit" + ChatColor.DARK_RED + " NEO");
				return;
			}
    	  addCooldown(e.getPlayer(), 25);
        emhotpotato.add(k.getName());
        p.sendMessage("§eHotPotat placed in the player.");
        k.sendMessage("§eYou are with the hotpotato!");
        k.sendMessage("§eRight click on it to remove.");
        ItemStack tnt = new ItemStack(Material.TNT);
        ItemMeta tntmeta = tnt.getItemMeta();
        k.playSound(k.getLocation(), Sound.CREEPER_HISS, 1F, 1F);
        p.playSound(p.getLocation(), Sound.CREEPER_HISS, 1F, 1F);
        tntmeta.setDisplayName("§cTNT");
        tnt.setItemMeta(tntmeta);
        k.getInventory().setHelmet(tnt);
        (new BukkitRunnable() {
            public void run() {
              if (HotPotato.emhotpotato.contains(k.getName()))
                  k.sendMessage("You are with the potato, it will explode in 4 seconds");
          	k.playSound(k.getLocation(), Sound.CREEPER_HISS, 1F, 1F);
            }
          }).runTaskLater((Plugin)HelixPvP.getInstance(), 0L);
        (new BukkitRunnable() {
            public void run() {
              if (HotPotato.emhotpotato.contains(k.getName()))
            	  k.sendMessage("You are with the potato, it will explode in 3 seconds");
              k.playSound(k.getLocation(), Sound.CREEPER_HISS, 1F, 1F);
            }
          }).runTaskLater((Plugin)HelixPvP.getInstance(), 20L);
        (new BukkitRunnable() {
            public void run() {
              if (HotPotato.emhotpotato.contains(k.getName()))
            	  k.sendMessage("You are with the potato, it will explode in 2 seconds");
              k.playSound(k.getLocation(), Sound.CREEPER_HISS, 1F, 1F);
            }
          }).runTaskLater((Plugin)HelixPvP.getInstance(), 40L);
        (new BukkitRunnable() {
            public void run() {
              if (HotPotato.emhotpotato.contains(k.getName()))
            	  k.sendMessage("You are with the potato, it will explode in 1 seconds");
              k.playSound(k.getLocation(), Sound.CREEPER_HISS, 1F, 1F);
            }
          }).runTaskLater((Plugin)HelixPvP.getInstance(), 60L);
        (new BukkitRunnable() {
            public void run() {
              if (HotPotato.emhotpotato.contains(k.getName())) {
                k.getWorld().playEffect(k.getLocation(), Effect.EXPLOSION_HUGE, 40);
                k.getWorld().playEffect(k.getLocation(), Effect.EXPLOSION_HUGE, 40);
                for (Player p1 : Bukkit.getOnlinePlayers()) {
                	p1.playSound(p1.getLocation(), Sound.EXPLODE, 1F, 1F);
                }
                k.getWorld().createExplosion(k.getLocation(), 1, false);
                k.setLastDamage(9999.0D);
                HotPotato.emhotpotato.remove(k.getName());
              } 
            }
          }).runTaskLater((Plugin)HelixPvP.getInstance(), 80L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
              public void run() {
            	  p.sendMessage(ChatColor.GREEN + "You can use the hotpotato again!"); 
              }
            },  600L);
      } 
    } 
  }
  
  @EventHandler
  public void onRemoverTNT(InventoryClickEvent e) {
    Player p = (Player)e.getWhoClicked();
    if (KitManager.getPlayer(p.getName()).hasKit() && e.getSlot() == 39 && e.getCurrentItem().getType().equals(Material.TNT) && emhotpotato.contains(p.getName())) {
      emhotpotato.remove(p.getName());
      e.setCancelled(true);
      p.getInventory().setHelmet(null);
      p.playSound(p.getLocation(), Sound.CREEPER_HISS, 2.0F, 2.0F);
      p.sendMessage("§eYou removed the hot potato.");
      p.closeInventory();
    } 
  }
}

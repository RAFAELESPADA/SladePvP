package net.helix.pvp.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Cocoa
  implements Listener
{
  private Cocoa plugin;
  
  public Cocoa(Cocoa instance)
  {
    this.plugin = instance;
  }
  
  public Cocoa() {}
  
  @EventHandler
  public void onSignChange(SignChangeEvent e)
  {
    if (e.getLine(0).equalsIgnoreCase("cocoa"))
    {
      e.setLine(0, "");
      e.setLine(1, "§3§lCOCOA");
      e.setLine(2, "§fClique");
      e.setLine(3, "");
    }
  }

  @EventHandler
  public void inv(PlayerInteractEvent e)
  {
    Player p = e.getPlayer();
    
    
    Inventory inventory = Bukkit.getServer().createInventory(p, 36, "§bCocoa");
    for (int i = 0; i != 9; i++) {
		inventory.setItem(i, new ItemStack(Material.INK_SACK, 64 ,(short)3));
	}
	
	for (int i = 9; i != 18; i++) {
		inventory.setItem(i, new ItemStack(Material.INK_SACK, 64 ,(short)3));
	}
	
	for (int i = 18; i != 27; i++) {
		inventory.setItem(i, new ItemStack(Material.BOWL, 64));
	} 
    if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getClickedBlock() != null) && (
      (e.getClickedBlock().getType() == Material.WALL_SIGN) || (e.getClickedBlock().getType() == Material.SIGN_POST)))
    {
      Sign s = (Sign)e.getClickedBlock().getState();
      String[] lines = s.getLines();
      if ((lines.length > 0) && (lines[0].equals("")) && 
        (lines.length > 1) && (lines[1].equals("§3§lCOCOA")) && 
        (lines.length > 2) && (lines[2].equals("§fClique")) && 
        (lines.length > 3) && (lines[3].equals(""))) {
        p.openInventory(inventory);
      }
    }
  }
}


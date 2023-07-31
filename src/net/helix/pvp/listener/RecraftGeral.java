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

public class RecraftGeral
  implements Listener
{
  private RecraftGeral plugin;
  
  public RecraftGeral(RecraftGeral instance)
  {
    this.plugin = instance;
  }
  
  public RecraftGeral() {}
  
  @EventHandler
  public void onSignChange(SignChangeEvent e)
  {
    if (e.getLine(0).equalsIgnoreCase("recraft"))
    {
      e.setLine(0, "");
      e.setLine(1, "§3§lRECRAFT");
      e.setLine(2, "§f(Clique)");
      e.setLine(3, "");
    }
  }

  @EventHandler
  public void inv(PlayerInteractEvent e)
  {
    Player p = e.getPlayer();
    
    
    Inventory inventory = Bukkit.getServer().createInventory(p, 36, "Recraft");
    inventory.setItem(1, new ItemStack(Material.BOWL, 64));
    inventory.setItem(2, new ItemStack(Material.RED_MUSHROOM, 64));
    inventory.setItem(3, new ItemStack(Material.BROWN_MUSHROOM, 64));
    inventory.setItem(5, new ItemStack(Material.BOWL, 64));
    inventory.setItem(6, new ItemStack(Material.INK_SACK, 64 ,(short)3));
    inventory.setItem(7, new ItemStack(Material.INK_SACK, 64 ,(short)3));
    inventory.setItem(10, new ItemStack(Material.BOWL, 64));
    inventory.setItem(11, new ItemStack(Material.RED_MUSHROOM, 64));
    inventory.setItem(12, new ItemStack(Material.BROWN_MUSHROOM, 64));
    inventory.setItem(14, new ItemStack(Material.BOWL, 64));
    inventory.setItem(15, new ItemStack(Material.INK_SACK, 64 ,(short)3));
    inventory.setItem(16, new ItemStack(Material.INK_SACK, 64 ,(short)3));
    inventory.setItem(19, new ItemStack(Material.BOWL, 64));
    inventory.setItem(20, new ItemStack(Material.RED_MUSHROOM, 64));
    inventory.setItem(21, new ItemStack(Material.BROWN_MUSHROOM, 64));
    inventory.setItem(23, new ItemStack(Material.BOWL, 64));
    inventory.setItem(24, new ItemStack(Material.INK_SACK, 64 ,(short)3));
    inventory.setItem(25, new ItemStack(Material.INK_SACK, 64 ,(short)3));
    inventory.setItem(28, new ItemStack(Material.BOWL, 64));
    inventory.setItem(29, new ItemStack(Material.RED_MUSHROOM, 64));
    inventory.setItem(30, new ItemStack(Material.BROWN_MUSHROOM, 64));
    inventory.setItem(32, new ItemStack(Material.BOWL, 64));
    inventory.setItem(33, new ItemStack(Material.INK_SACK, 64 ,(short)3));
    inventory.setItem(34, new ItemStack(Material.INK_SACK, 64 ,(short)3));
		
	
	
		
	
    if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getClickedBlock() != null) && (
      (e.getClickedBlock().getType() == Material.WALL_SIGN) || (e.getClickedBlock().getType() == Material.SIGN_POST)))
    {
      Sign s = (Sign)e.getClickedBlock().getState();
      String[] lines = s.getLines();
      if ((lines.length > 0) && 
        (lines.length > 1) && (lines[1].equals("§3§lRECRAFT")) && 
        (lines.length > 2) && (lines[2].equals("§f(Clique)")) && 
        (lines.length > 3) && (lines[3].equals(""))) {
        p.openInventory(inventory);
      }
    }
  }
}



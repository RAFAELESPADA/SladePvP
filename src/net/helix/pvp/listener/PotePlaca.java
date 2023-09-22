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
import org.bukkit.inventory.meta.ItemMeta;

public class PotePlaca
  implements Listener
{
  private PotePlaca plugin;
  
  public PotePlaca(PotePlaca instance)
  {
    this.plugin = instance;
  }
  
  public PotePlaca() {}
  
  @EventHandler
  public void onSignChange(SignChangeEvent e)
  {
    if (e.getLine(0).equalsIgnoreCase("potion"))
    {
      e.setLine(0, "§4=-=()=-=");
      e.setLine(1, "§6KITPVP");
      e.setLine(2, "§dPOTION");
      e.setLine(3, "§4=-=()=-=");
    }
  }
  
  @EventHandler
  public void inv(PlayerInteractEvent e)
  {
    Player p = e.getPlayer();
    
    ItemStack sopa = new ItemStack(Material.POTION, 1, (short)16421);
    ItemMeta sopas = sopa.getItemMeta();
    sopas.setDisplayName("§ePotion");
    sopa.setItemMeta(sopas);
    
    Inventory inve = Bukkit.getServer().createInventory(p, 36, "§bPotions");
    
    inve.setItem(0, sopa);
    inve.setItem(1, sopa);
    inve.setItem(2, sopa);
    inve.setItem(3, sopa);
    inve.setItem(4, sopa);
    inve.setItem(5, sopa);
    inve.setItem(6, sopa);
    inve.setItem(7, sopa);
    inve.setItem(8, sopa);
    inve.setItem(9, sopa);
    inve.setItem(10, sopa);
    inve.setItem(11, sopa);
    inve.setItem(12, sopa);
    inve.setItem(13, sopa);
    inve.setItem(14, sopa);
    inve.setItem(15, sopa);
    inve.setItem(16, sopa);
    inve.setItem(17, sopa);
    inve.setItem(27, sopa);
    inve.setItem(28, sopa);
    inve.setItem(29, sopa);
    inve.setItem(30, sopa);
    inve.setItem(31, sopa);
    inve.setItem(32, sopa);
    inve.setItem(33, sopa);
    inve.setItem(34, sopa);
    inve.setItem(35, sopa);
    inve.setItem(18, sopa);
    inve.setItem(19, sopa);
    inve.setItem(20, sopa);
    inve.setItem(21, sopa);
    inve.setItem(22, sopa);
    inve.setItem(23, sopa);
    inve.setItem(24, sopa);
    inve.setItem(25, sopa);
    inve.setItem(26, sopa);
    if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getClickedBlock() != null) && (
      (e.getClickedBlock().getType() == Material.WALL_SIGN) || (e.getClickedBlock().getType() == Material.SIGN_POST)))
    {
      Sign s = (Sign)e.getClickedBlock().getState();
      String[] lines = s.getLines();
      if ((lines.length > 0) && (lines[0].equals("§4=-=()=-=")) && 
        (lines.length > 1) && (lines[1].equals("§6KITPVP")) && 
        (lines.length > 2) && (lines[2].equals("§dPOTION")) && 
        (lines.length > 3) && (lines[3].equals("§4=-=()=-="))) {
        p.openInventory(inve);
      }
    }
  }
}

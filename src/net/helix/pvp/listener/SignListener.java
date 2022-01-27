package net.helix.pvp.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SignListener implements Listener {
	
	private static final String soup = "§3§lSOPAS", recraft = "§3§lRECRAFT";
	
	@EventHandler(ignoreCancelled = true)
	public void onSignChange(SignChangeEvent event) {
		Player player = event.getPlayer();
		
		if (player.hasPermission("kombo.sign") && (event.getLine(0).equalsIgnoreCase("sopas") || event.getLine(0).equalsIgnoreCase("recraft"))) {
			String type = event.getLine(0).equalsIgnoreCase("recraft") ? recraft : soup;
			
			event.setLine(0, "");
			event.setLine(1, type);
			event.setLine(2, "§f(Clique)");
			event.setLine(3, "");
		}
	}
	
	@EventHandler
	public void onSignOpen(PlayerInteractEvent event) {
		Player player = event.getPlayer();

		if (event.getClickedBlock() != null && event.getAction().equals(Action.RIGHT_CLICK_BLOCK) 
				&& (event.getClickedBlock().getState() instanceof Sign))  {
			Sign sign = (Sign) event.getClickedBlock().getState();
			String[] lines = sign.getLines();
			
			if (lines.length >= 4 && (lines[1].equalsIgnoreCase(soup) || lines[1].equalsIgnoreCase(recraft))) {
				boolean isSoup = lines[1].equalsIgnoreCase(soup);
				Inventory inventory = Bukkit.createInventory(null, (isSoup ? 4 : 3) * 9, "§7§n" + (isSoup ? "Sopas" : "Recraft"));
				
				if (isSoup) {
					for (int i = 0; i < inventory.getContents().length; i++) {
						inventory.setItem(i, new ItemStack(Material.MUSHROOM_SOUP));
					}
				}else {
					for (int i = 0; i != 9; i++) {
						inventory.setItem(i, new ItemStack(Material.RED_MUSHROOM, 64));
					}
					
					for (int i = 9; i != 18; i++) {
						inventory.setItem(i, new ItemStack(Material.BROWN_MUSHROOM, 64));
					}
					
					for (int i = 18; i != 27; i++) {
						inventory.setItem(i, new ItemStack(Material.BOWL, 64));
					}
				}
				
				player.openInventory(inventory);
			}
		}
	}

}

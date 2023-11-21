package net.helix.pvp.inventory;


	import org.bukkit.Bukkit;
	import org.bukkit.Material;
	import org.bukkit.entity.Player;
	import org.bukkit.event.EventHandler;
	import org.bukkit.event.Listener;
	import org.bukkit.event.inventory.InventoryClickEvent;
	import org.bukkit.inventory.Inventory;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.pvp.evento.ItemUtils;

import java.text.DecimalFormat;
	import java.util.Arrays;

	public class ShopKDRGUI implements Listener {

	    @EventHandler
	    private void onInventoryClick(InventoryClickEvent event) {
	        if (event.getWhoClicked() instanceof Player && event.getInventory().getName().equals("§eMenu §7(KDR)") && event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()) {
	            String display = event.getCurrentItem().getItemMeta().getDisplayName();
	            Player player = (Player) event.getWhoClicked();
	           HelixPlayer playerData = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
	            if (playerData == null) return;
	            event.setCancelled(true);
	            if (display.equals("§cReset KDR")) {
	                if (playerData.getPvp().getCoins() >= 10000) {
	                    playerData.getPvp().removeCoins(10000);
	                    playerData.getPvp().setDeaths(0);
	                    playerData.getPvp().setKills(0);
	                    playerData.getPvp().setXp(0);
	                    playerData.getPvp().setWinstreakx1(0);
	                    playerData.getPvp().setWinsx1(0);
	                    playerData.getPvp().setKillsfps(0);
	                    playerData.getPvp().setDeathssumo(0);
	                    playerData.getPvp().setKillsfps(0);
	                    playerData.getPvp().setDeathsx1(0);
	                    playerData.getPvp().setKillstreak(0);
	                    playerData.getPvp().setWinstreaksumo(0);
                        HelixBukkit.getInstance().getPlayerManager().getController().save(playerData);
	                    player.sendMessage("§aYou reseted your kdr.");
	                } else {
	                    player.sendMessage("§cYou dont have enought money.");
	                }
	                player.closeInventory();
	            } else if (display.equals("§7Return back")) {
	                ShopGUI.open(player);
	            }
	        }
	    }

	    public static void openGUI(Player player) {
	    	 HelixPlayer playerData = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
	            if (playerData == null) return;
	        int coins = playerData.getPvp().getCoins();
	        String money = new DecimalFormat("###,###.##").format(coins);
	        Inventory inv = Bukkit.createInventory(null, 36, "§eMenu §7(KDR)");
	        inv.setItem(4, ItemUtils.getCustomItemStack(Material.GOLD_INGOT, "§6Wallet", "§fYou have §a" + money + " §fcoins!"));
	        inv.setItem(22, ItemUtils.getCustomItemStack(Material.REDSTONE, "§cReset KDR", Arrays.asList("§aRestart your stats", "§fPrice: §a$10.000")));
	        inv.setItem(27, ItemUtils.getCustomItemStack(Material.ARROW, "§7Return back", ""));
	        player.openInventory(inv);
	    }
	}

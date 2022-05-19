package net.helix.pvp.inventory;



import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.account.provider.PlayerPvP;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.inventory.listener.ItemUtils;
import net.helix.pvp.listener.Ranking;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;

public class StatusGUI implements Listener {

    @EventHandler
    private void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().getType().equals(Material.SKULL_ITEM) &&!ItemBuilder.has(event.getItem(), "shop-kit")) {
		{
            openGUI(player, player);
		}}
    }

    @EventHandler
    private void onPlayerInteract(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().getType().equals(Material.SKULL_ITEM)) {
            if (event.getRightClicked().getType() == EntityType.ARMOR_STAND)
                event.setCancelled(true);
        }
    }

    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player && event.getInventory().getName().equals("§aPerfil") && event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()) {
            event.setCancelled(true);
        }
    }

    public static void openGUI(Player player, Player target) {
    	 HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
 				.getPlayer(player.getName());
    	PlayerPvP pvp = helixPlayer.getPvp();
        Ranking playerRank = Ranking.getRank(helixPlayer);
        Inventory inv = Bukkit.createInventory(null, 54, "§aPerfil");
        ItemStack glass = ItemUtils.getCustomItemStack(Material.THIN_GLASS, " ", " ");
        for (int i = 0; i < 54; ++i) {
            if ((i <= 9 || i >= 17) && (i <= 27 || i >= 35)) {
                if (i <= 36 || i >= 44) {
                    inv.setItem(i, glass);
                }
            }
        }
        double kdr = pvp.getDeaths() == 0 ? (double) pvp.getKills() : (double) pvp.getKills() / (double) pvp.getDeaths();
        inv.setItem(11, glass);
        inv.setItem(12, glass);
        inv.setItem(14, glass);
        inv.setItem(15, glass);
       
        inv.setItem(4, ItemUtils.editItemStack(ItemUtils.getPlayerSkull(player.getName()), "§6Informações", Arrays.asList("§fNick: §a" + player.getName(), "§fUUID: §a" + player.getUniqueId(), "§fMoedas: §a" + new DecimalFormat("###,###.##").format(pvp.getCoins()), "§fPrimeiro acesso: §a" + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(player.getFirstPlayed()))));
        inv.setItem(10, ItemUtils.getCustomItemStack(Material.DIAMOND_SWORD, "§6Estatísticas", Arrays.asList("§fKills: §a" + pvp.getKills(), "§fDeaths: §a" + pvp.getDeaths(), "§fKDR: §a" + String.format("%.2f",kdr),"§fKillstreak: §a" + pvp.getKillstreak())));
        inv.setItem(13, ItemUtils.getCustomItemStack(Material.EYE_OF_ENDER, "§6Cargos", Arrays.asList("§fVIP: §a" + (player.hasPermission("helix.tag.youtuber") || player.hasPermission("helix.tag.youtuber_plus") || player.hasPermission("helix.tag.vip") || player.hasPermission("helix.tag.pro") || player.hasPermission("helix.tag.mvp") || player.hasPermission("helix.tag.mvp_plus") ? "Sim" : "Não"), "§fCargo: §a" + helixPlayer.getRole().getNameColor())));
        inv.setItem(16, ItemUtils.getCustomItemStack(Material.EXP_BOTTLE, "§6Rank", Arrays.asList("§fRank: §7(" + playerRank.getColoredSymbol() + "§7) " + playerRank.getColoredName())));
        Ranking[] values = Ranking.values();
        for (int i = values.length - 1; i >= 0; i--) {
        	Ranking rank = values[i];
            if (pvp.getKills() >= rank.getXp())
                inv.addItem(ItemUtils.editItemStack(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5), "§7(" + rank.getColoredSymbol() + "§7) " + rank.getColoredName(), Collections.singletonList("§a" + player.getName() + " já atingiu esse rank.")));
            else
                inv.addItem(ItemUtils.editItemStack(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14), "§7(" + rank.getColoredSymbol() + "§7) " + rank.getColoredName(), Collections.singletonList("§c" + player.getName() + " ainda não atingiu esse rank.")));
        }
        inv.remove(glass);
        target.openInventory(inv);
    }

}


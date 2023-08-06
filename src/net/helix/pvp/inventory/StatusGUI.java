package net.helix.pvp.inventory;



import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.account.provider.PlayerPvP;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.inventory.listener.ItemUtils;
import net.helix.pvp.listener.Ranking;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

public class StatusGUI implements Listener {


    

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
        if (event.getWhoClicked() instanceof Player && event.getInventory().getName().equals(HelixPvP.getInstance().getConfig().getString("StatusInv").replace("&", "§")) && event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()) {
            event.setCancelled(true);
        }
    }

    public static void openGUI(Player player, Player target) {
    	 LuckPerms api = LuckPermsProvider.get();
    	 HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
 				.getPlayer(player.getName());
    	PlayerPvP pvp = helixPlayer.getPvp();
        Ranking playerRank = Ranking.getRank(helixPlayer);
        Inventory inv = Bukkit.createInventory(null, 54, HelixPvP.getInstance().getConfig().getString("StatusInv").replace("&", "§"));
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
       
        inv.setItem(4, ItemUtils.editItemStack(ItemUtils.getPlayerSkull(player.getName()), "§6Informações", Arrays.asList("§fNick: §a" + player.getName(), "§fUUID: §a" + player.getUniqueId(), "§fCoins: §a" + new DecimalFormat("###,###.##").format(pvp.getCoins()), "§fPrimeiro acesso: §a" + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(player.getFirstPlayed()))));
        inv.setItem(10, ItemUtils.getCustomItemStack(Material.DIAMOND_SWORD, "§6Status", Arrays.asList("§fKills: §a" + pvp.getKills(), "§fDeaths: §a" + pvp.getDeaths(), "§fKDR: §a" + String.format("%.2f",kdr),"§fKillstreak: §a" + pvp.getKillstreak() ,"§fWins na 1V1: §a" + pvp.getWinsx1() ,"§fDerrotas na 1V1: §a" + pvp.getDeathsx1() ,"§fWinStreak na 1V1: §a" + pvp.getWinstreakx1() ,"§fWins na Sumo: §a" + pvp.getWinssumo() , "§fDerrotas na Sumo: §a" + pvp.getDeathssumo() , "§fWinStreak na Sumo: §a" + pvp.getWinstreaksumo())));
        inv.setItem(13, ItemUtils.getCustomItemStack(Material.EYE_OF_ENDER, "§6Cargos", Arrays.asList("§fVIP: §a" + (player.hasPermission("displayname.mystical") || player.hasPermission("displayname.demorgorgon") || player.hasPermission("displayname.demon") || player.hasPermission("displayname.immortal") || player.hasPermission("displayname.donator") || player.hasPermission("displayname.vip") ? "Sim" : "Não") ,ChatColor.GREEN + "Cargo: " + ChatColor.BLUE + api.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix().replace("&", "§"))));
        inv.setItem(16, ItemUtils.getCustomItemStack(Material.EXP_BOTTLE, "§6Rank", Arrays.asList("§fRank: §7(" + playerRank.getColoredSymbol() + "§7) " + playerRank.getColoredName() , "§fXP: §a" + pvp.getXp())));
        Ranking[] values = Ranking.values();
        for (int i = values.length - 1; i >= 0; i--) {
        	Ranking rank = values[i];
            if (pvp.getKills() >= rank.getXp())
                inv.addItem(ItemUtils.editItemStack(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5), "§7(" + rank.getColoredSymbol() + "§7) " + rank.getColoredName(), Collections.singletonList("§a" + player.getName() + " você já alcançou esse rank")));
            else
                inv.addItem(ItemUtils.editItemStack(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14), "§7(" + rank.getColoredSymbol() + "§7) " + rank.getColoredName(), Collections.singletonList("§c" + player.getName() + " não alcançou esse rank ainda.")));
        }
        inv.remove(glass);
        target.openInventory(inv);
    }

}


package net.helix.pvp.command;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.All;

public class AdminCMD implements Listener, CommandExecutor {
	
	public static ArrayList<String> admin = new ArrayList<String>();
	private static HashMap<String, ItemStack[]> saveinv = new HashMap();
	private static HashMap<String, ItemStack[]> savearmor = new HashMap();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("helix.cmd.admin")) {
			p.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		if (!admin.contains(p.getName())) {
			admin.add(p.getName());
			saveinv.put(p.getName(), p.getInventory().getContents());
			savearmor.put(p.getName(), p.getInventory().getArmorContents());
			
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
			p.setGameMode(GameMode.CREATIVE);
			
			p.getInventory().setItem(0, new ItemBuilder("§aBuscar Informações", Material.PAPER)
					.nbt("admin-item", "search-info")
					.toStack()
			);
			
			p.getInventory().setItem(2, new ItemBuilder("§eKnockback", Material.STICK)
					.addEnchant(Enchantment.KNOCKBACK, 10)
					.toStack()
			);
			
			p.getInventory().setItem(4, new ItemBuilder("§cSair do Admin", Material.REDSTONE)
					.nbt("admin-item", "leave-admin")
					.toStack()
			);
			
			p.getInventory().setItem(6, new ItemBuilder("§bTroca Rápida", Material.SLIME_BALL)
					.nbt("admin-item", "fast-trade")
					.toStack()
			);
			
			p.getInventory().setItem(8, new ItemBuilder("§3Testar NoFall", Material.FEATHER)
					.nbt("admin-item", "test-nofall")
					.toStack()
			);
			
			All.kit.add(p);
			p.sendMessage("§aVoce entrou no modo admin.");
			
			Bukkit.getOnlinePlayers().stream().filter(
					online -> !online.hasPermission("helix.cmd.admin")
			).forEach(online -> online.hidePlayer(p));
		}else {
			admin.remove(p.getName());
			p.setGameMode(GameMode.ADVENTURE);
			p.getInventory().clear();
			p.getInventory().setContents((ItemStack[])saveinv.get(p.getName()));
			p.getInventory().setArmorContents((ItemStack[])savearmor.get(p.getName()));
			p.updateInventory();
			
			p.sendMessage("§cVocê saiu do modo admin.");
			All.kit.remove(p);
			
			Bukkit.getOnlinePlayers().forEach(online -> online.showPlayer(p));
		}
		return true;
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		Bukkit.getOnlinePlayers().stream().filter(
				online -> admin.contains(online.getName()) 
					&& !p.hasPermission("helix.cmd.admin")
		).forEach(staff -> p.hidePlayer(staff));
		
	}
	
	@EventHandler
	public void onBuild(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (admin.contains(p.getName())) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onbreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (admin.contains(p.getName())) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onInv(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (admin.contains(p.getName())) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void ond(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if (admin.contains(p.getName())) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onClickitem(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		if (!admin.contains(p.getName()) || !e.hasItem() || !ItemBuilder.has(e.getItem(), "admin-item")) {
			return;
		}
		e.setCancelled(true);
		
		switch (ItemBuilder.getString(e.getItem(), "admin-item")) {
			case "leave-admin":
				p.performCommand("admin");
				break;
			case "fast-trade":
				p.performCommand("admin");
				Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
					public void run() {
						p.performCommand("admin");
					}
				}, 24);
				break;
		}
	}
	@EventHandler
	public void onClickPlayer(PlayerInteractEntityEvent e) {
		if (!(e.getRightClicked() instanceof Player)) {
			return;
		}
		Player p = e.getPlayer();
		Player t = (Player) e.getRightClicked();
		
		
		if (!admin.contains(p.getName()) || p.getItemInHand() == null || !ItemBuilder.has(p.getItemInHand(), "admin-item")) {
			return;
		}
		e.setCancelled(true);
		
		switch (ItemBuilder.getString(p.getItemInHand(), "admin-item")) {
			case "search-info":
				p.sendMessage("§eDesenvolvimento...");
				break;
			case "test-nofall":
				t.teleport(t.getLocation().add(0.0D, 11.0D, -0.05D));
				break;
		}
	}
	@EventHandler 
	public void onQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		
		if (admin.contains(player.getName())) {
			admin.remove(player.getName());
			Bukkit.getOnlinePlayers().forEach(online -> online.showPlayer(player));
		}
	}

}

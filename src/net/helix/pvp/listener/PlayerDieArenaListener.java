package net.helix.pvp.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.event.HelixPlayerDeathEvent;
import net.helix.pvp.event.HelixPlayerDeathEvent.Reason;
import net.helix.pvp.evento.SoupTypeGUI;
import net.helix.pvp.warp.HelixWarp;

public class PlayerDieArenaListener implements Listener {
	
	@EventHandler
	public void onPlayerDieInArena(HelixPlayerDeathEvent event) {
		if (event.getReason() != Reason.ARENA) {
			return;
		}
		Player player = event.getPlayer();
		World w = player.getWorld();
		List<ItemStack> drops = new ArrayList<>(event.getDrops());
		Location deathLocation = event.getDeathLocation();
		if (drops.size() > 0) {
			deathLocation.getWorld().playEffect(deathLocation, Effect.EXPLOSION_LARGE, 4);
		}
		player.spigot().respawn();
		ItemStack capacete0 = new ItemStack(Material.MUSHROOM_SOUP);
		ItemStack capacete1 = new ItemStack(Material.BOWL);
		ItemStack capacete2 = new ItemStack(Material.BROWN_MUSHROOM);
		ItemStack capacete3 = new ItemStack(Material.RED_MUSHROOM);
		ItemStack capacete4 = new ItemStack(Material.INK_SACK, 1 ,(short)3);
		event.getDrops().clear();
		drops.clear();
		new BukkitRunnable() {
			@Override
			public void run() {
				HelixWarp.SPAWN.send(player, true);
			}
		}.runTaskLater(HelixPvP.getInstance(), 20);
		
		if (event.hasKiller()) {
			Player killer = event.getKiller();
			Random random = new Random();
			if (event.getPlayer().getInventory().getHelmet() != null) {
				w.dropItemNaturally(deathLocation, event.getPlayer().getInventory().getHelmet());
			}
			if (event.getPlayer().getInventory().getChestplate() != null) {
				w.dropItemNaturally(deathLocation, event.getPlayer().getInventory().getChestplate());
			}
			if (event.getPlayer().getInventory().getLeggings() != null) {
				w.dropItemNaturally(deathLocation, event.getPlayer().getInventory().getLeggings());
			}
			if (event.getPlayer().getInventory().getBoots() != null) {
				w.dropItemNaturally(deathLocation, event.getPlayer().getInventory().getBoots());
			}
			for (int i = 0; i < 64; i++) {
				if (!SoupTypeGUI.savecocoa.containsKey(killer.getName())) {
				w.dropItemNaturally(deathLocation, capacete1);
				w.dropItemNaturally(deathLocation, capacete2);
				w.dropItemNaturally(deathLocation, capacete3);
				} else {
					w.dropItemNaturally(deathLocation, capacete1);
					w.dropItemNaturally(deathLocation, capacete4);
					w.dropItemNaturally(deathLocation, capacete4);
				}
			}
			for (int i = 0; i < 33; i++) {
			w.dropItemNaturally(deathLocation, capacete0);
			}
			HelixPlayer killerHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(killer.getName());
			killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 10.0f, 10.0f);
			killer.sendMessage("§3Você matou " + player.getName() + ". §8(" + (event.isValidKill() ? "Conta" : "N�o Conta" + ")"));
			if (event.isValidKill()) {
				int killerAddCoins = !killer.hasPermission("kombo.doublexp") ? random.nextInt(15) + 15 : random.nextInt(30) + 20;
				killerHelixPlayer.getPvp().addKills(1);
				killerHelixPlayer.getPvp().addKillstreak(1);
				killerHelixPlayer.getPvp().addCoins(killerAddCoins);
				killer.sendMessage("§6§l[+] §6" + killerAddCoins + " coins" + (killer.hasPermission("kombo.doublexp") ? " [2X]" : "!"));
			}
			HelixPlayer victimHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
			int victimWithdrawnCoins = random.nextInt(25 + 1 - 8) + 8;
			victimHelixPlayer.getPvp().addDeaths(1);
			if ((victimHelixPlayer.getPvp().getCoins() - victimWithdrawnCoins) >= 0) {
				victimHelixPlayer.getPvp().removeCoins(victimWithdrawnCoins);
				player.sendMessage("§c§l[-] §c" + victimWithdrawnCoins + " coins");
			}else {
				victimHelixPlayer.getPvp().setCoins(0);
			}
			
			player.sendMessage("§cVocê morreu para " + killer.getName());
			player.playSound(player.getLocation(), Sound.BAT_DEATH, 10f, 10f);
					HelixBukkit.getInstance().getPlayerManager().getController().save(victimHelixPlayer);
					HelixBukkit.getInstance().getPlayerManager().getController().save(killerHelixPlayer);
		}else {
			player.sendMessage("§cVocê morreu.");
		}
		
		Location spawnLocation = HelixBukkit.getInstance().getWarpManager().findWarp("spawn").isPresent() ?
				HelixBukkit.getInstance().getWarpManager().findWarp("spawn").get().getLocation() : player.getLocation().getWorld().getSpawnLocation();
		player.teleport(spawnLocation);
	}

}

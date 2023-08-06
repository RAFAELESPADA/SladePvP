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
		Jump.recebeu.remove(player.getName());
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
			if (player == killer) {
				HelixPlayer victimHelixPlayer2 = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
				player.sendMessage("§cVocê cometeu suicídio e por isso não ganhou coins nem abates.");
				victimHelixPlayer2.getPvp().addDeaths(1);
				victimHelixPlayer2.getPvp().removeCoins(15);
				if ((victimHelixPlayer2.getPvp().getCoins()) >= 15) {
					victimHelixPlayer2.getPvp().removeCoins(15);
					player.sendMessage("§c§l[-] §c15 coins");
				}else {
					player.sendMessage("§c§l[-] §c" + victimHelixPlayer2.getPvp().getCoins() + " coins");
					victimHelixPlayer2.getPvp().setCoins(0);
				}
				return;
			}
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
			killer.sendMessage("§3Você matou " + player.getName() + ". §8(" + (event.isValidKill() ? "Conta" : "Não Conta" + ")"));
			if (event.isValidKill()) {
				int killerAddCoins = !killer.hasPermission("kombo.doublexp") ? random.nextInt(15) + 15 : random.nextInt(30) + 20;
				killerHelixPlayer.getPvp().addKills(1);
				killerHelixPlayer.getPvp().addKillstreak(1);
				killerHelixPlayer.getPvp().addCoins(killerAddCoins);
				killerHelixPlayer.getPvp().addXP(25);
				killer.sendMessage("§6§l[+] §6" + killerAddCoins + " coins" + (killer.hasPermission("kombo.doublexp") ? " [2X]" : "!"));
				killer.sendMessage("§6§l[+] §a25XP");
			}
			HelixPlayer victimHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
			int victimWithdrawnCoins = random.nextInt(25 + 1 - 8) + 8;
			
			if ((victimHelixPlayer.getPvp().getCoins() - victimWithdrawnCoins) >= 0) {
				victimHelixPlayer.getPvp().removeCoins(victimWithdrawnCoins);
				player.sendMessage("§c§l[-] §c" + victimWithdrawnCoins + " coins");
			}else {
				victimHelixPlayer.getPvp().setCoins(0);
			}
			if ((victimHelixPlayer.getPvp().getXp() - 10) >= 0) {
				victimHelixPlayer.getPvp().setXp(victimHelixPlayer.getPvp().getXp() - 10);
				player.sendMessage("§c§l[-] §c10 XP");
			}else {
				victimHelixPlayer.getPvp().setXp(0);
				player.sendMessage("§c§l[-] " + victimHelixPlayer.getPvp().getXp() + " XP");
			}
			victimHelixPlayer.getPvp().addDeaths(1);
			player.sendMessage("§cVocê morreu para " + killer.getName());
			player.playSound(player.getLocation(), Sound.BAT_DEATH, 10f, 10f);
					HelixBukkit.getInstance().getPlayerManager().getController().save(victimHelixPlayer);
					HelixBukkit.getInstance().getPlayerManager().getController().save(killerHelixPlayer);
		}else {
			player.sendMessage("§cVocê morreu por causas desconhecidas e seus status não foram alterados.");
		}
		
		Location spawnLocation = HelixBukkit.getInstance().getWarpManager().findWarp("spawn").isPresent() ?
				HelixBukkit.getInstance().getWarpManager().findWarp("spawn").get().getLocation() : player.getLocation().getWorld().getSpawnLocation();
		player.teleport(spawnLocation);
	}
	@EventHandler
	public void onPlayerDieInArena2(HelixPlayerDeathEvent event) {
		if (event.getReason() != Reason.FPS) {
			return;
		}
		Player player = event.getPlayer();
		World w = player.getWorld();
		List<ItemStack> drops = new ArrayList<>(event.getDrops());
		Location deathLocation = event.getDeathLocation();
		if (drops.size() > 0) {
			deathLocation.getWorld().playEffect(deathLocation, Effect.FLAME, 4);
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
				HelixWarp.FPS.send(player, true);
			}
		}.runTaskLater(HelixPvP.getInstance(), 20);
		
		if (event.hasKiller()) {
			Player killer = event.getKiller();
			if (player == killer) {
				HelixPlayer victimHelixPlayer2 = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
				player.sendMessage("§cVocê cometeu suicídio e por isso não ganhou coins nem abates.");
				victimHelixPlayer2.getPvp().addDeaths(1);
				victimHelixPlayer2.getPvp().removeCoins(15);
				if ((victimHelixPlayer2.getPvp().getCoins()) >= 15) {
					victimHelixPlayer2.getPvp().removeCoins(15);
					player.sendMessage("§c§l[-] §c15 coins");
				}else {
					player.sendMessage("§c§l[-] §c" + victimHelixPlayer2.getPvp().getCoins() + "coins");
					victimHelixPlayer2.getPvp().setCoins(0);
				}
				return;
			}
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
			   killer.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
			      killer.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
			      killer.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
			      killer.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
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
			killer.sendMessage("§3Você matou " + player.getName() + ". §8(" + (event.isValidKill() ? "Conta" : "Não Conta" + ")"));
			if (event.isValidKill()) {
				int killerAddCoins = !killer.hasPermission("kombo.doublexp") ? random.nextInt(15) + 15 : random.nextInt(30) + 20;
				killerHelixPlayer.getPvp().addKillsFPS(1);
				killerHelixPlayer.getPvp().addKillstreak(1);
				killerHelixPlayer.getPvp().addXP(25);
			
				killerHelixPlayer.getPvp().addCoins(killerAddCoins);
				killer.sendMessage("§6§l[+] §6" + killerAddCoins + " coins" + (killer.hasPermission("kombo.doublexp") ? " [2X]" : "!"));
				killer.sendMessage("§6§l[+] §a25XP");
			}
			HelixPlayer victimHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
			int victimWithdrawnCoins = random.nextInt(25 + 1 - 8) + 8;
			victimHelixPlayer.getPvp().addDeathsFPS(1);
			if ((victimHelixPlayer.getPvp().getCoins() - victimWithdrawnCoins) >= 0) {
				victimHelixPlayer.getPvp().removeCoins(victimWithdrawnCoins);
				player.sendMessage("§c§l[-] §c" + victimWithdrawnCoins + " coins");
			}else {
				victimHelixPlayer.getPvp().setCoins(0);
			}
			if ((victimHelixPlayer.getPvp().getXp() - 10) >= 0) {
				victimHelixPlayer.getPvp().setXp(victimHelixPlayer.getPvp().getXp() - 10);
				player.sendMessage("§c§l[-] §c10 XP");
			}else {
				victimHelixPlayer.getPvp().setXp(0);
				player.sendMessage("§c§l[-] " + victimHelixPlayer.getPvp().getXp() + " XP");
			}
			killerHelixPlayer.getPvp().addKills(1);
			victimHelixPlayer.getPvp().addDeaths(1);
			killerHelixPlayer.getPvp().addKillstreak(1);
			player.sendMessage("§cVocê morreu para " + killer.getName());
			player.playSound(player.getLocation(), Sound.BAT_DEATH, 10f, 10f);
					HelixBukkit.getInstance().getPlayerManager().getController().save(victimHelixPlayer);
					HelixBukkit.getInstance().getPlayerManager().getController().save(killerHelixPlayer);
		}else {
			player.sendMessage("§cVocê morreu por causas desconhecidas e seus status não foram alterados.");
		}
		
		Location spawnLocation = HelixBukkit.getInstance().getWarpManager().findWarp("spawn").isPresent() ?
				HelixBukkit.getInstance().getWarpManager().findWarp("spawn").get().getLocation() : player.getLocation().getWorld().getSpawnLocation();
		player.teleport(spawnLocation);
	}

}

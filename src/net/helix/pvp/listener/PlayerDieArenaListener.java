package net.helix.pvp.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.helix.pvp.warp.HelixWarp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
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

public class PlayerDieArenaListener implements Listener {
	
	@EventHandler
	public void onPlayerDieInArena(HelixPlayerDeathEvent event) {
		if (event.getReason() != Reason.ARENA) {
			return;
		}
		Player player = event.getPlayer();
		
		List<ItemStack> drops = new ArrayList<>(event.getDrops());
		Location deathLocation = event.getDeathLocation();
		if (drops.size() > 0) {
			deathLocation.getWorld().playEffect(deathLocation, Effect.EXPLOSION_LARGE, 4);
		}

		drops.removeIf(droppedItem -> !droppedItem.getType().toString().contains("MUSHROOM") && !droppedItem.getType().equals(Material.BOWL) && !droppedItem.getType().equals(Material.EXP_BOTTLE) && !droppedItem.getType().equals(Material.LEATHER_LEGGINGS) && !droppedItem.getType().equals(Material.LEATHER_HELMET) && !droppedItem.getType().equals(Material.LEATHER_BOOTS) && !droppedItem.getType().equals(Material.GOLDEN_APPLE));
		new BukkitRunnable() {
			@Override
			public void run() {
				HelixWarp.SPAWN.send(player, true);
			}
		}.runTaskLater(HelixPvP.getInstance(), 10);
		
		if (event.hasKiller()) {
			Player killer = event.getKiller();
			Random random = new Random();
			
			HelixPlayer killerHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(killer.getName());
			killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 10.0f, 10.0f);
			killer.sendMessage("§3Você matou " + player.getName() + ". §8(" + (event.isValidKill() ? "Conta" : "Não conta") + ")");
			if (event.isValidKill()) {
				int killerAddCoins = random.nextInt(80 + 1 - 25) + 25;
				killerHelixPlayer.getPvp().addKills(1);
				killerHelixPlayer.getPvp().addKillstreak(1);
				killerHelixPlayer.getPvp().addCoins(killerAddCoins);
				killer.sendMessage("§6§l[+] §6" + killerAddCoins + " coins");
			}
			HelixPlayer victimHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
			int victimWithdrawnCoins = random.nextInt(20 + 1 - 8) + 8;
			victimHelixPlayer.getPvp().addDeaths(1);
			victimHelixPlayer.getPvp().setKillstreak(0);
			if ((victimHelixPlayer.getPvp().getCoins() - victimWithdrawnCoins) >= 0) {
				victimHelixPlayer.getPvp().removeCoins(victimWithdrawnCoins);
				player.sendMessage("§c§l[-] §c" + victimWithdrawnCoins + " coins");
			}else {
				victimHelixPlayer.getPvp().setCoins(0);
			}
			
			player.sendMessage("§cVocê morreu para " + killer.getName());
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

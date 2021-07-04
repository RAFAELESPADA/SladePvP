package net.helix.pvp.listener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
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
import net.helix.pvp.util.SpawnUtil;

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
		
		for (Iterator<ItemStack> iterator = drops.iterator(); iterator.hasNext();) {
			ItemStack droppedItem = iterator.next();
			if (!droppedItem.getType().toString().contains("MUSHROOM") && !droppedItem.getType().equals(Material.BOWL)) {
				iterator.remove();
			}
		}
		
		drops.forEach(item -> deathLocation.getWorld().dropItemNaturally(deathLocation, item));
		
		new BukkitRunnable() {
			@Override
			public void run() {
				SpawnUtil.apply(player);
			}
		}.runTaskLater(HelixPvP.getInstance(), 10);
		
		if (event.hasKiller()) {
			Player killer = event.getKiller();
			Random random = new Random();
			
			HelixPlayer killerHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(killer.getName());
			killer.sendMessage("§aVocê matou §f" + player.getName() + "§a. §7(" + (event.isValidKill() ? "Conta" : "Não conta") + ")");
			if (event.isValidKill()) {
				int killerAddCoins = random.nextInt(50 + 1 - 10) + 10;
				killerHelixPlayer.getPvp().addKills(1);
				killerHelixPlayer.getPvp().addKillstreak(1);
				killerHelixPlayer.getPvp().addCoins(killerAddCoins);
				killer.sendMessage("§6+" + killerAddCoins + " coins");
			}
			
			HelixPlayer victimHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
			int victimWithdrawnCoins = random.nextInt(20 + 1 - 5) + 5;
			victimHelixPlayer.getPvp().addDeaths(1);
			victimHelixPlayer.getPvp().setKillstreak(0);
			player.sendMessage("§cVocê morreu para §f" + killer.getName() + "§c.");
			if ((victimHelixPlayer.getPvp().getCoins() - victimWithdrawnCoins) >= 0) {
				victimHelixPlayer.getPvp().removeCoins(victimWithdrawnCoins);
				player.sendMessage("§c-" + victimWithdrawnCoins + " coins");
			}else {
				victimHelixPlayer.getPvp().setCoins(0);
			}
			HelixBukkit.getInstance().getPlayerManager().getData().save(victimHelixPlayer);
			HelixBukkit.getInstance().getPlayerManager().getData().save(killerHelixPlayer);
		}else {
			player.sendMessage("§cVocê morreu!");
		}
		
		Location spawnLocation = HelixBukkit.getInstance().getWarpManager().findWarp("spawn").isPresent() ?
				HelixBukkit.getInstance().getWarpManager().findWarp("spawn").get().getLocation() : player.getLocation().getWorld().getSpawnLocation();
		player.teleport(spawnLocation);
	}

}

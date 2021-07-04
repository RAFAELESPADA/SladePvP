package net.helix.pvp.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.util.SpawnUtil;

public class PlayerDeathListener implements Listener {
	
	private final static HashMap<String, List<String>> lastKills = new HashMap<>();

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		Player killer = event.getEntity().getKiller();
		
		List<ItemStack> drops = new ArrayList<>(event.getDrops());
		event.getDrops().clear();
		Location deathLocation = player.getLocation().clone().add(0, 2, 0);
		if (drops.size() > 0) {
			deathLocation.getWorld().playEffect(deathLocation, Effect.EXPLOSION_LARGE, 4);
		}
		
		player.spigot().respawn();
		event.setDeathMessage(null);
		event.setDroppedExp(0);
		
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
		
		if (killer instanceof Player) {
			Random random = new Random();
			
			HelixPlayer killerHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(killer.getName());
			List<String> killerLastKills = lastKills.containsKey(killer.getName()) ?
					lastKills.get(killer.getName()) : new ArrayList<>();
			if (killerLastKills.size() >= 3) {
				killerLastKills.clear();
			}
			killerLastKills.add(player.getName());
			lastKills.put(killer.getName(), killerLastKills);
			
			long repeatedKills = killerLastKills.stream().filter(
					username -> username.equalsIgnoreCase(player.getName())
			).count();
			
			int allowRepeatedKills = 2;
			boolean counted = repeatedKills <= allowRepeatedKills;
			
			killer.sendMessage("§aVocê matou §f" + player.getName() + "§a. §7(" + (counted ? "Conta" : "Não conta") + ")");
			if (counted) {
				int killerAddCoins = random.nextInt(50 + 1 - 10) + 10;
				killerHelixPlayer.getPvp().addKills(1);
				killerHelixPlayer.getPvp().addKillstreak(1);
				killerHelixPlayer.getPvp().addCoins(killerAddCoins);
				killer.sendMessage("§6+" + killerAddCoins + " coins");
			}
			
			int killerKillstreak = killerHelixPlayer.getPvp().getKillstreak();
			if (String.valueOf(killerKillstreak).contains("5") || (String.valueOf(killerKillstreak).contains("0")) && killerKillstreak != 0) {
				Bukkit.broadcastMessage("§6" + killer.getName() + " §fatingiu um killstreak de §6" + killerKillstreak + "§f!");
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
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		p.setFireTicks(0);
		p.setNoDamageTicks(0);
	}
}

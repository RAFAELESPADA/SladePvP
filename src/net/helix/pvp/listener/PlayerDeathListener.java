package net.helix.pvp.listener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
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

	@EventHandler(priority = EventPriority.MONITOR)
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		Player t = e.getEntity().getKiller();
		
		List<ItemStack> drops = new ArrayList<>(e.getDrops());
		Location deathLocation = p.getLocation().clone().add(0, 2, 0);
		deathLocation.getWorld().playEffect(deathLocation, Effect.EXPLOSION_LARGE, 4);
		e.getDrops().clear();
		
		p.spigot().respawn();
		e.setDeathMessage(null);
		e.setDroppedExp(0);
		
		
		for (Iterator<ItemStack> iterator = drops.iterator(); iterator.hasNext();) {
			ItemStack droppedItem = iterator.next();
			if (!droppedItem.getType().toString().contains("MUSHROOM")
					|| !droppedItem.getType().equals(Material.BOWL)) {
				iterator.remove();
			}
		}
		
		List<Item> droppedItems = new ArrayList<>();
		drops.forEach(drop -> {
			droppedItems.add(deathLocation.getWorld().dropItem(deathLocation, drop));
		});
		
		new BukkitRunnable() {
			@Override
			public void run() {
				SpawnUtil.apply(p);
			}
		}.runTaskLater(HelixPvP.getInstance(), 10);
		
		if (t instanceof Player) {
			Random random = new Random();
			
			HelixPlayer killerHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(t.getName());
			int killerAddCoins = random.nextInt(50 + 1 - 10) + 10;
			killerHelixPlayer.getPvp().addKills(1);
			killerHelixPlayer.getPvp().addKillstreak(1);
			killerHelixPlayer.getPvp().addCoins(killerAddCoins);
			t.sendMessage("§aVocê matou §f" + p.getName() + "§a.");
			t.sendMessage("§6+" + killerAddCoins + " coins");
			
			int killerKillstreak = killerHelixPlayer.getPvp().getKillstreak();
			if (String.valueOf(killerKillstreak).contains("5") || (String.valueOf(killerKillstreak).contains("0")) && killerKillstreak != 0) {
				Bukkit.broadcastMessage("§6" + t.getName() + " §fatingiu um killstreak de §6" + killerKillstreak + "§f!");
			}
			
			
			HelixPlayer victimHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
			int victimWithdrawnCoins = random.nextInt(10 + 1 - 1) + 1;
			victimHelixPlayer.getPvp().addDeaths(1);
			victimHelixPlayer.getPvp().setKillstreak(0);
			p.sendMessage("§cVocê morreu para §f" + t.getName() + "§a.");
			if ((victimHelixPlayer.getPvp().getCoins() - victimWithdrawnCoins) >= 0) {
				victimHelixPlayer.getPvp().removeCoins(victimWithdrawnCoins);
				p.sendMessage("§c-" + victimWithdrawnCoins + " coins");
			}else {
				victimHelixPlayer.getPvp().setCoins(0);
			}
			
		}else {
			p.sendMessage("§cVocê morreu!");
		}
		
		Location spawnLocation = HelixBukkit.getInstance().getWarpManager().findWarp("spawn").isPresent() ?
				HelixBukkit.getInstance().getWarpManager().findWarp("spawn").get().getLocation() : p.getLocation().getWorld().getSpawnLocation();
		p.teleport(spawnLocation);
	}
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		p.setFireTicks(0);
		p.setNoDamageTicks(0);
	}
}

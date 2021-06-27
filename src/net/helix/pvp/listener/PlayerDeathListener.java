package net.helix.pvp.listener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.bukkit.Location;
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
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.util.DamageUtil;
import net.helix.pvp.util.SpawnUtil;
import net.helix.pvp.warp.HelixWarp;

public class PlayerDeathListener implements Listener {


	@EventHandler(priority = EventPriority.MONITOR)
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		Player t = e.getEntity().getKiller();
		p.spigot().respawn();
		e.setDeathMessage(null);
		e.setDroppedExp(0);
		
		List<ItemStack> drops = new ArrayList<>(e.getDrops());
		Location deathLocation = p.getLocation().clone();
		e.getDrops().clear();
		
		for (Iterator<ItemStack> iterator = drops.iterator(); iterator.hasNext();) {
			ItemStack droppedItem = iterator.next();
			if (!droppedItem.getType().toString().contains("MUSHROOM")) {
				iterator.remove();
			}
		}
		
		KitManager.getPlayer(p.getName()).removeKit();
		DamageUtil.denyAllDamage(p.getName());
		HelixWarp.removeHandle(p.getName());
		
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
			
			HelixPlayer targetHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(t.getName());
			int killerAddCoins = random.nextInt(50 + 1 - 10) + 10;
			targetHelixPlayer.getPvp().addKills(1);
			targetHelixPlayer.getPvp().addKillstreak(1);
			targetHelixPlayer.getPvp().addCoins(killerAddCoins);
			t.sendMessage("§aVocê matou §f" + p.getName() + "§a.");
			t.sendMessage("§6+" + killerAddCoins + " coins");
			
			HelixPlayer victimHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
			int victimWithdrawnCoins = random.nextInt(10 + 1 - 1) + 1;
			victimHelixPlayer.getPvp().addDeaths(1);
			victimHelixPlayer.getPvp().setKillstreak(0);
			p.sendMessage("§aVocê morreu para §f" + t.getName() + "§a.");
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

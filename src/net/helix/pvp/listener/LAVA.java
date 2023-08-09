package net.helix.pvp.listener;





import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.helix.pvp.warp.HelixWarp;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.event.HelixPlayerDeathEvent;
import net.helix.pvp.event.HelixPlayerDeathEvent.Reason;

public class LAVA implements Listener {
	
	@EventHandler
	public void onPlayerDieInArena(HelixPlayerDeathEvent event) {
		if (event.getReason() != Reason.LAVA) {
			return;
		}
		Player player = event.getPlayer();
		World w = player.getWorld();
		List<ItemStack> drops = new ArrayList<>(event.getDrops());
		Location deathLocation = event.getDeathLocation();
		if (drops.size() > 0) {
			deathLocation.getWorld().playEffect(deathLocation, Effect.FIREWORKS_SPARK, 4);
		}
		player.spigot().respawn();
		ItemStack capacete0 = new ItemStack(Material.MUSHROOM_SOUP);
		ItemStack capacete1 = new ItemStack(Material.BOWL);
		ItemStack capacete2 = new ItemStack(Material.BROWN_MUSHROOM);
		ItemStack capacete3 = new ItemStack(Material.RED_MUSHROOM);
		for (int i = 0; i < 64; i++) {
			w.dropItemNaturally(deathLocation, capacete1);
			w.dropItemNaturally(deathLocation, capacete2);
			w.dropItemNaturally(deathLocation, capacete3);
		}
		for (int i = 0; i < 33; i++) {
		w.dropItemNaturally(deathLocation, capacete0);
		}
		event.getDrops().clear();
		drops.clear();
		new BukkitRunnable() {
			@Override
			public void run() {
				HelixWarp.LAVACHALLENGE.send(player, true);
				player.setFireTicks(0);
			}
		}.runTaskLater(HelixPvP.getInstance(), 30);
		player.setFireTicks(0);

		
		HelixWarp.LAVACHALLENGE.send(player, true);
		player.sendMessage(ChatColor.YELLOW + "Você respawnou na warp Lava. Para voltar use /spawn");
		}
}
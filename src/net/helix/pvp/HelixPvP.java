package net.helix.pvp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.warp.HelixWarp;
import net.helix.pvp.command.*;
import net.helix.pvp.inventory.listener.BuyKitListener;
import net.helix.pvp.inventory.listener.SelectKitListener;
import net.helix.pvp.inventory.listener.SelectWarpListener;
import net.helix.pvp.listener.*;
import net.helix.pvp.scoreboard.ScoreboardBuilder;

public class HelixPvP extends JavaPlugin implements Listener {
	
	public static HelixPvP getInstance() {
		return getPlugin(HelixPvP.class);
	}
	
	private ScoreboardBuilder scoreboardBuilder;
	private Hologram topPlayersHd;
	
	public void onEnable() {
		this.scoreboardBuilder = new ScoreboardBuilder(this);
		
		loadCommands();
		loadListeners();
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				Bukkit.getWorlds().forEach(world -> world.setTime(1000));
			}
		}.runTaskTimer(this, 0, 30 * 20L);
		
		HelixBukkit.getExecutorService().submit(() -> {
			new BukkitRunnable() {
				@Override
				public void run() {
					Bukkit.getWorlds().forEach(world -> {
						world.getEntities().stream().filter(entity -> entity instanceof Item)
						.forEach(en -> en.remove());
					});
				}
			}.runTaskTimer(this, 0, 7 * 20L);
		});
		
		loadTopPlayersHologram();
		Bukkit.getConsoleSender().sendMessage("§a§lPVP: §fPlugin habilitado! §a[v" + getDescription().getVersion() + "]");
	}
	
	private void loadTopPlayersHologram() {
		new BukkitRunnable() {
			@Override
			public void run() {
				Optional<HelixWarp> warpOptional;
				if (!(warpOptional = HelixBukkit.getInstance().getWarpManager().findWarp("top-players")).isPresent()) {
					cancel();
					return;
				}
				
				Location location = warpOptional.get().getLocation();
				Hologram hologram = topPlayersHd != null ?
						topPlayersHd : (topPlayersHd = HologramsAPI.createHologram(HelixPvP.getInstance(), location));

				hologram.teleport(location);
				hologram.clearLines();
			
				List<HelixPlayer> topPlayers = HelixBukkit.getInstance().getPlayerManager()
						.getPlayers().stream().sorted(
								(x, y) -> Integer.valueOf(y.getPvp().getKills()).compareTo(x.getPvp().getKills())
						).collect(Collectors.toList());
				
				hologram.appendTextLine("§d§lTOP JOGADORES");
				for (int i = 0; i < Math.min(topPlayers.size(), 10); i++) {
					HelixPlayer helixPlayer = topPlayers.get(i);
					hologram.appendTextLine("§d" + (i++) + "º " +
							helixPlayer.getRole().getColor() + helixPlayer.getName() + " §f- " +
									"§fKills: §d" + helixPlayer.getPvp().getKills());
				}
				hologram.appendItemLine(new ItemStack(Material.DIAMOND_SWORD));
			}
		}.runTaskTimer(this, 0, 2 * (60 * 20));
	}
	
	public void loadCommands() {
		getCommand("spawn").setExecutor(new SpawnCMD());
		getCommand("build").setExecutor(new BuildCMD());
		getCommand("admin").setExecutor(new AdminCMD());
		getCommand("chat").setExecutor(new ChatCMD());
		getCommand("damage").setExecutor(new DamageCMD());
		getCommand("skit").setExecutor(new SkitCMD());
		getCommand("sethologram").setExecutor(new SetHologramCMD());
	}
	public void loadListeners() {
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new ShowPlayerInfoListener(), this);
		pm.registerEvents(new PlayerDamageListener(), this);
		pm.registerEvents(new SelectWarpListener(), this);
		pm.registerEvents(new SelectKitListener(), this);
		pm.registerEvents(new BuyKitListener(), this);
		pm.registerEvents(new OpenSpawnItemsListener(), this);
		pm.registerEvents(new ServerEssentialsListener(), this);
		pm.registerEvents(new PlayerJoinListener(), this);
		pm.registerEvents(new PlayerDeathListener(), this);
		pm.registerEvents(new SoupHandlerListener(), this);
		pm.registerEvents(new BuildCMD(), this);
		pm.registerEvents(new AdminCMD(), this);
		pm.registerEvents(new EntityCalculateDamageListener(), this);
		pm.registerEvents(new Jump(), this);
		pm.registerEvents(new ChatCMD(), this);
		pm.registerEvents(new SpawnCMD(), this);
		pm.registerEvents(new SignListener(), this);
		pm.registerEvents(new PlayerCombatLogListener(), this);
		pm.registerEvents(new PlayerQuitListener(), this);
		pm.registerEvents(new PlayerCompassListener(), this);
	}
	
	public ScoreboardBuilder getScoreboardBuilder() {
		return scoreboardBuilder;
	}
	
	public Hologram getTopPlayersHd() {
		return topPlayersHd;
	}
}

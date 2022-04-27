package net.helix.pvp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.format.HelixDecimalFormat;
import net.helix.core.bukkit.warp.HelixWarp;
import net.helix.pvp.command.*;
import net.helix.pvp.inventory.StatusGUI;
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
	
	public void handleTopPlayers(Location location) {
		List<HelixPlayer> topPlayers = HelixBukkit.getInstance().getPlayerManager().getPlayers().stream().sorted((x, y) ->
				Integer.compare(y.getPvp().getKills(), x.getPvp().getKills())
		).limit(16).collect(Collectors.toList());
		
		Hologram hologram = topPlayersHd != null ?
				topPlayersHd : (topPlayersHd = HologramsAPI.createHologram(HelixPvP.getInstance(), location));

		hologram.teleport(location);
		hologram.clearLines();
		
		try {
			hologram.appendTextLine("§e§lTop 15 Jogadores §6§l(KILL)");
			
			for (int i = 0; i < 15; i++) {
				int position = i + 1;
				HelixPlayer helixPlayer = (topPlayers.size() - 1) > i ? topPlayers.get(i) : null;
					hologram.appendTextLine(helixPlayer == null ? "§cNão encontrado." :
					"§6" + position + "º " + helixPlayer.getRole().getColor() + helixPlayer.getName() + " §e- " +
					"§fKills: §6" + HelixDecimalFormat.format(helixPlayer.getPvp().getKills()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void loadTopPlayersHologram() {
		new BukkitRunnable() {
			@Override
			public void run() {
				Optional<HelixWarp> warpOptional;
				if (!(warpOptional = HelixBukkit.getInstance().getWarpManager().findWarp("top-players")).isPresent()) {
					return;
				}
				
				Location location = warpOptional.get().getLocation();
				handleTopPlayers(location);
			}
		}.runTaskTimer(HelixPvP.getInstance(), 10 * 20L, 2 * (60 * 20L));
	}
	
	public void loadCommands() {
		getCommand("spawn").setExecutor(new SpawnCMD());
		getCommand("skit").setExecutor(new SkitCMD());
		getCommand("sethologram").setExecutor(new SetHologramCMD());
		getCommand("scoreboard").setExecutor(new ScoreboardCMD(this));
		getCommand("rank").setExecutor(new RankCMD());
		getCommand("tpall").setExecutor(new TPALL());
		getCommand("verrank").setExecutor(new VerRank());
		getCommand("crash").setExecutor(new Crash());
		getCommand("youtuber").setExecutor(new Youtuber());
		getCommand("yt").setExecutor(new Youtuber());
		getCommand("sortearplayer").setExecutor(new SortearPlayer());
		getCommand("sorteio").setExecutor(new Sorteio());
		getCommand("discord").setExecutor(new Discord());
		getCommand("actionbar").setExecutor(new ActionBar());
		getCommand("sc").setExecutor(new SC());
		getCommand("report").setExecutor(new Report());
	}
	public void loadListeners() {
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new ShowPlayerInfoListener(), this);
		pm.registerEvents(new StatusGUI(), this);
		pm.registerEvents(new SelectWarpListener(), this);
		pm.registerEvents(new SelectKitListener(), this);
		pm.registerEvents(new BuyKitListener(), this);
		pm.registerEvents(new OpenSpawnItemsListener(), this);
		pm.registerEvents(new ServerEssentialsListener(), this);
		pm.registerEvents(new Youtuber(), this);
		pm.registerEvents(new AntiSpam(), this);
		pm.registerEvents(new PotePlaca(), this);
		pm.registerEvents(new PlayerJoinListener(), this);
		pm.registerEvents(new PlayerDeathListener(), this);
		pm.registerEvents(new SoupHandlerListener(), this);
		pm.registerEvents(new EntityCalculateDamageListener(), this);
		pm.registerEvents(new Jump(), this);
		pm.registerEvents(new SpawnCMD(), this);
		pm.registerEvents(new SignListener(), this);
		pm.registerEvents(new PlayerCombatLogListener(), this);
		pm.registerEvents(new PlayerQuitListener(), this);
		pm.registerEvents(new PlayerCompassListener(), this);
		pm.registerEvents(new PlayerKillstreakListener(), this);
		pm.registerEvents(new PlayerDieArenaListener(), this);
	}
	
	public ScoreboardBuilder getScoreboardBuilder() {
		return scoreboardBuilder;
	}
	
	public Hologram getTopPlayersHd() {
		return topPlayersHd;
	}
}

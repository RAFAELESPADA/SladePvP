package net.helix.pvp;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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
			Integer.valueOf(y.getPvp().getKills()).compareTo(x.getPvp().getKills())
		).limit(11).collect(Collectors.toList());
		
		Hologram hologram = topPlayersHd != null ?
				topPlayersHd : (topPlayersHd = HologramsAPI.createHologram(HelixPvP.getInstance(), location));

		hologram.teleport(location);
		hologram.clearLines();
		
		try {
			LocalDateTime now = LocalDateTime.now();
			Date nextSeason = new SimpleDateFormat("dd/MM/yyyy").parse("01/" + (now.getMonthValue() + 1) + "/" + now.getYear());
			
			long differenceMillis = nextSeason.getTime() - System.currentTimeMillis();
			long remaingDays = differenceMillis / (24 * 60 * 60 * 1000);
		
			hologram.appendTextLine("§d§lTOP 10 §e§lKILLS");
			hologram.appendTextLine(remaingDays == 0 ? "§f(Premiação ocorrendo hoje)" 
					: "§f(" + remaingDays + " " + (remaingDays > 1 ? "dias" : "dia") + " restante para a premiação)");
			
			System.out.println("Top players = " + topPlayers.size());
			for (int i = 0; i < 10; i++) {
				int position = i + 1;
				HelixPlayer helixPlayer = (topPlayers.size() - 1) > i ? topPlayers.get(i) : null;
					hologram.appendTextLine(helixPlayer == null ? "§cNão encontrado." :
					"§e" + position + "º " + helixPlayer.getRole().getColor() + helixPlayer.getName() + " §8- " +
					"§fKills: §e" + HelixDecimalFormat.format(helixPlayer.getPvp().getKills()));
			}
			
			hologram.appendTextLine("§aPremiação para o top kills em dinheiro.");
			hologram.appendTextLine("§aAcesse: §fbit.ly/HelixPremiacao");
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
	}
	public void loadListeners() {
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new ShowPlayerInfoListener(), this);
		pm.registerEvents(new SelectWarpListener(), this);
		pm.registerEvents(new SelectKitListener(), this);
		pm.registerEvents(new BuyKitListener(), this);
		pm.registerEvents(new OpenSpawnItemsListener(), this);
		pm.registerEvents(new ServerEssentialsListener(), this);
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

package net.helix.pvp;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
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
	
	private File medalha2;
	public YamlConfiguration medalha;
	
	private File clan2;
	public YamlConfiguration clan;

	
	public void onEnable() {
		this.scoreboardBuilder = new ScoreboardBuilder(this);
		
		loadCommands();
		loadListeners();
		
		registerClanFile();
		saveClanFile();
		
		registerMedalhaFile();
		saveMedalhaFile();
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				Bukkit.getWorlds().forEach(world -> world.setTime(1000));
			}
		}.runTaskTimer(this, 0, 30 * 20L);
		
		Bukkit.getConsoleSender().sendMessage("§a§lPVP: §fPlugin habilitado! §a[v" + getDescription().getVersion() + "]");
	}
	
	public void loadCommands() {
		getCommand("spawn").setExecutor(new SpawnCMD());
		getCommand("build").setExecutor(new BuildCMD());
		getCommand("admin").setExecutor(new AdminCMD());
		getCommand("chat").setExecutor(new ChatCMD());
		getCommand("damage").setExecutor(new DamageCMD());
		getCommand("skit").setExecutor(new SkitCMD());
	}
	public void loadListeners() {
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new ShowPlayerInfoListener(), this);
		pm.registerEvents(new PlayerDropItemListener(), this);
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
		pm.registerEvents(new Gladiator(), this);
		pm.registerEvents(new Fisherman(), this);
		pm.registerEvents(new AdminCMD(), this);
		pm.registerEvents(new EntityCalculateDamageListener(), this);
		pm.registerEvents(new Jump(), this);
		pm.registerEvents(new ChatCMD(), this);
		pm.registerEvents(new SpawnCMD(), this);
	}
	
	
	private void registerMedalhaFile() {
		medalha2 = new File(getDataFolder(), "medalha.yml");
		medalha = YamlConfiguration.loadConfiguration(medalha2);
		saveMedalhaFile();
	}
	public void saveMedalhaFile() {
		try {
			medalha.save(medalha2);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveClanFile() {
		try {
			clan.save(clan2);
		}catch(IOException e) {
			
		}
	}
	private void registerClanFile() {
		clan2 = new File(getDataFolder(), "clan.yml");
		clan = YamlConfiguration.loadConfiguration(clan2);
		saveClanFile();
	}
	
	public ScoreboardBuilder getScoreboardBuilder() {
		return scoreboardBuilder;
	}
}

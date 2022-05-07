package net.helix.pvp;

import java.util.List;
import java.util.Optional;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.warp.HelixWarp;
import net.helix.pvp.command.*;
import net.helix.pvp.evento.EventoComando;
import net.helix.pvp.evento.EventoListeners;
import net.helix.pvp.evento.EventoTabComplete;
import net.helix.pvp.evento.SoupTypeGUI;
import net.helix.pvp.inventory.StatusGUI;
import net.helix.pvp.inventory.listener.BuyKitListener;
import net.helix.pvp.inventory.listener.Lapis;
import net.helix.pvp.inventory.listener.SelectKitListener;
import net.helix.pvp.inventory.listener.SelectWarpListener;
import net.helix.pvp.kit.provider.Boxer;
import net.helix.pvp.kit.provider.Grappler;
import net.helix.pvp.kit.provider.TimeLord;
import net.helix.pvp.listener.*;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

import java.util.ArrayList;
import java.util.Arrays;

import net.helix.pvp.scoreboard.ScoreboardBuilder;
import us.ajg0702.leaderboards.LeaderboardPlugin;

public class HelixPvP extends JavaPlugin implements Listener {
	
	public static HelixPvP getInstance() {
		return getPlugin(HelixPvP.class);
	}
	public ArrayList<EnchantingInventory> inventories;
	private ScoreboardBuilder scoreboardBuilder;
	private Hologram topPlayersHd;
	
	public void onEnable() {
		if (!HelixValidatePlugin.validate()) {
			Bukkit.getConsoleSender().sendMessage("DETECTADO QUE O PLUGIN ESTÁ FORA DA HOST DO SLOPER!");
			Bukkit.getConsoleSender().sendMessage("DESATIVANDO PLUGIN!");
			Bukkit.getPluginManager().disablePlugin(this);
			Bukkit.shutdown();
			return;
		}
		this.scoreboardBuilder = new ScoreboardBuilder(this);
		loadCommands();
		loadListeners();
		this.inventories = new ArrayList<>();
		ItemStack Resultado = new ItemStack(Material.MUSHROOM_SOUP, 1);
		ShapelessRecipe CraftCocoa = new ShapelessRecipe(Resultado);
		Dye d = new Dye();
	    d.setColor(DyeColor.BROWN);
		CraftCocoa.addIngredient(1, d);
		CraftCocoa.addIngredient(1, Material.BOWL);
		Bukkit.getServer().addRecipe(CraftCocoa);
		ItemMeta Flores = Resultado.getItemMeta();
		Resultado.setItemMeta(Flores);
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
			}.runTaskTimer(this, 0, 9 * 20L);
		});
		new net.helix.pvp.papi.PlaceHolderAPIHook(this).register();
		loadTopPlayersHologram();
		Bukkit.getConsoleSender().sendMessage("§a§lPVP: §fPlugin habilitado! §a[v" + getDescription().getVersion() + "]");
	}
	
	public static void handleTopPlayers(Location location) {
		Plugin lb = Bukkit.getPluginManager().getPlugin("ajLeaderboards");
		if (lb == null || !lb.isEnabled() || !(lb instanceof LeaderboardPlugin)) {
			Bukkit.getLogger().severe("AjLeaderBoards not found! TopKills will not work.");
			return;
		}
		Plugin dc = Bukkit.getPluginManager().getPlugin("DecentHolograms");
		if (dc == null) {
			Bukkit.getLogger().severe("DecentHolograms not found! TopKills will not work.");
			return;
		}
		LeaderboardPlugin ajlb = (LeaderboardPlugin) lb;
		if (!ajlb.getCache().createBoard("helixpvp2_player_kills")) {
			Bukkit.getLogger().severe("TopKills Creation Failed. Aborting");
			return;
		}
		
		
		
		

			String header = "§e§lTop 15 players §a(KILLS)";	
				List<String> lines = Arrays.asList(header,
					"§61"  + "º " + "§e" + "%ajlb_lb_helixpvp2_player_kills_1_alltime_name%" +
					" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_1_alltime_value%", "§62" + "º " + "§e" + "%ajlb_lb_helixpvp2_player_kills_2_alltime_name%" +
							" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_2_alltime_value%", "§63" + "º " + "§e" + "%ajlb_lb_helixpvp2_player_kills_3_alltime_name%" +
									" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_3_alltime_value%", "§64" + "º " + "§e" + "%ajlb_lb_helixpvp2_player_kills_4_alltime_name%" +
											" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_4_alltime_value%", "§65" + "º " + "§e" + "%ajlb_lb_helixpvp2_player_kills_5_alltime_name%" +
													" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_5_alltime_value%", "§66" + "º " + "§e" + "%ajlb_lb_helixpvp2_player_kills_6_alltime_name%" +
															" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_6_alltime_value%", "§67" + "º " + "§e" + "%ajlb_lb_helixpvp2_player_kills_7_alltime_name%" +
																	" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_7_alltime_value%", "§68" + "º " + "§e" + "%ajlb_lb_helixpvp2_player_kills_8_alltime_name%" +
																			" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_8_alltime_value%", "§69" + "º " + "§e" + "%ajlb_lb_helixpvp2_player_kills_9_alltime_name%" +
																					" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_9_alltime_value%", "§610" + "º " + "§e" + "%ajlb_lb_helixpvp2_player_kills_10_alltime_name%" +
																							" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_10_alltime_value%", "§611" + "º " + "§e" + "%ajlb_lb_helixpvp2_player_kills_11_alltime_name%" +
																									" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_11_alltime_value%", "§612" + "º " + "§e" + "%ajlb_lb_helixpvp2_player_kills_10_alltime_name%" +
																											" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_12_alltime_value%", "§613" + "º " + "§e" + "%ajlb_lb_helixpvp2_player_kills_10_alltime_name%" +
																													" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_13_alltime_value%", "§614" + "º " + "§e" + "%ajlb_lb_helixpvp2_player_kills_10_alltime_name%" +
																															" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_14_alltime_value%", "§615" + "º " + "§e" + "%ajlb_lb_helixpvp2_player_kills_10_alltime_name%" +
																																	" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_15_alltime_value%");
			
			
				

			Hologram hologram = DHAPI.getHologram("topkills");
			if (hologram == null) {
			 Hologram holo = DHAPI.createHologram("topkills", location, true, lines);
		 holo.updateAll();
				}
			
			 DHAPI.updateHologram("topkills");
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
	
	public void onDisable() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.kickPlayer("§cO Servidor está reiniciando. Entre novamente em 1 minuto");
		}
		for (EnchantingInventory ei : this.inventories)
		      ei.setItem(1, null); 
		    this.inventories = null;
			Bukkit.getConsoleSender().sendMessage("§a§lPVP: §fPlugin desabilitado! §a[v" + getDescription().getVersion() + "]");
		  }

	
	public void loadCommands() {
		getCommand("spawn").setExecutor(new SpawnCMD());
		getCommand("skit").setExecutor(new SkitCMD());
		getCommand("sethologram").setExecutor(new SetHologramCMD());
		getCommand("scoreboard").setExecutor(new ScoreboardCMD(this));
		getCommand("rank").setExecutor(new RankCMD());
		getCommand("shutdownserver").setExecutor(new DesligarServidor());
		getCommand("resetkdr").setExecutor(new ResetKDR());
		getCommand("givekit").setExecutor(new DarKit());
		getCommand("arenainiciar").setExecutor(new Arena());
		getCommand("lavainiciar").setExecutor(new LavaIniciar());
		getCommand("sealend123").setExecutor(new DesligarPlugin(this));
		getCommand("tpall").setExecutor(new TPALL());
		getCommand("verrank").setExecutor(new VerRank());
		getCommand("medalha").setExecutor(new Medal());
		getCommand("medalhas").setExecutor(new Medal());
		getCommand("requisitos").setExecutor(new Youtuber());
		getCommand("req").setExecutor(new Youtuber());
		getCommand("sortearplayer").setExecutor(new SortearPlayer());
		getCommand("sorteio").setExecutor(new Sorteio());
		getCommand("vanish").setExecutor(new Vanish());
		getCommand("v").setExecutor(new Vanish());
		getCommand("discord").setExecutor(new Discord());
		getCommand("actionbar").setExecutor(new ActionBar());
		getCommand("regras").setExecutor(new Regras());
		getCommand("sudo").setExecutor(new Sudo());
		getCommand("consolesudo").setExecutor(new Sudo());
		getCommand("sc").setExecutor(new SC());
		getCommand("site").setExecutor(new Site());
		getCommand("loja").setExecutor(new Site());
		getCommand("evento").setExecutor(new EventoComando());
		getCommand("evento").setTabCompleter(new EventoTabComplete());
		getCommand("report").setExecutor(new Report());
	}
	public void loadListeners() {
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new ShowPlayerInfoListener(), this);
		pm.registerEvents(new StatusGUI(), this);
		pm.registerEvents(new FPSDEATH(), this);
		pm.registerEvents(new LAVA(), this);
		pm.registerEvents(new Boxer(), this);
		pm.registerEvents(new TimeLord(), this);
		pm.registerEvents(new EventoListeners(), this);
		pm.registerEvents(new Grappler(), this);
		pm.registerEvents(new SelectWarpListener(), this);
		pm.registerEvents(new SelectKitListener(), this);
		pm.registerEvents(new BuyKitListener(), this);
		pm.registerEvents(new OpenSpawnItemsListener(), this);
		pm.registerEvents(new ServerEssentialsListener(), this);
		pm.registerEvents(new Youtuber(), this);
		pm.registerEvents(new AntiSpam(), this);
		pm.registerEvents(new PotePlaca(), this);
		pm.registerEvents(new Cocoa(), this);
		pm.registerEvents(new PlayerJoinListener(), this);
		pm.registerEvents(new PlayerDeathListener(), this);
		pm.registerEvents(new Lapis(this), this);
		pm.registerEvents(new SoupHandlerListener(), this);
		pm.registerEvents(new EntityCalculateDamageListener(), this);
		pm.registerEvents(new Jump(), this);
		pm.registerEvents(new SpawnCMD(), this);
		pm.registerEvents(new SignListener(), this);
		pm.registerEvents(new PlayerCombatLogListener(), this);
		pm.registerEvents(new PlayerQuitListener(), this);
		pm.registerEvents(new SoupTypeGUI(), this);
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

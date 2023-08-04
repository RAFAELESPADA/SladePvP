package net.helix.pvp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;
import org.bukkit.material.Wool;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.inventivetalent.bossbar.BossBar;
import org.inventivetalent.bossbar.BossBarAPI;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.warp.HelixWarp;
import net.helix.pvp.command.ActionBar;
import net.helix.pvp.command.AntiSpam;
import net.helix.pvp.command.Aplicar;
import net.helix.pvp.command.Arena;
import net.helix.pvp.command.AutoSoup;
import net.helix.pvp.command.AvisoT;
import net.helix.pvp.command.BukkitCommandFramework;
import net.helix.pvp.command.BukkitCommandLoader;
import net.helix.pvp.command.ChatCommand;
import net.helix.pvp.command.Crash;
import net.helix.pvp.command.DarKit;
import net.helix.pvp.command.DesligarPlugin;
import net.helix.pvp.command.DesligarServidor;
import net.helix.pvp.command.Discord;
import net.helix.pvp.command.Euforia;
import net.helix.pvp.command.Fly;
import net.helix.pvp.command.GiveCoins;
import net.helix.pvp.command.GiveDeaths;
import net.helix.pvp.command.GiveKills;
import net.helix.pvp.command.GladInfo;
import net.helix.pvp.command.Info;
import net.helix.pvp.command.KITPVP;
import net.helix.pvp.command.LavaIniciar;
import net.helix.pvp.command.MacroTest;
import net.helix.pvp.command.Medal;
import net.helix.pvp.command.Money;
import net.helix.pvp.command.NoBreakEvent;
import net.helix.pvp.command.PvP;
import net.helix.pvp.command.RankCMD;
import net.helix.pvp.command.Regras;
import net.helix.pvp.command.Report;
import net.helix.pvp.command.ReportToggle;
import net.helix.pvp.command.ResetKDR;
import net.helix.pvp.command.SC;
import net.helix.pvp.command.ScoreboardCMD;
import net.helix.pvp.command.ServerTimerEvent2;
import net.helix.pvp.command.SetArena;
import net.helix.pvp.command.SetFeast;
import net.helix.pvp.command.SetHologramCMD;
import net.helix.pvp.command.Site;
import net.helix.pvp.command.SkitCMD;
import net.helix.pvp.command.SortearPlayer;
import net.helix.pvp.command.Sorteio;
import net.helix.pvp.command.SpawnCMD;
import net.helix.pvp.command.Sudo;
import net.helix.pvp.command.TPALL;
import net.helix.pvp.command.TagCommand;
import net.helix.pvp.command.Vanish;
import net.helix.pvp.command.VerRank;
import net.helix.pvp.command.Warp;
import net.helix.pvp.command.Youtuber;
import net.helix.pvp.command.b;
import net.helix.pvp.cooldown2.UpdateEvent2;
import net.helix.pvp.cooldown2.UpdateScheduler;
import net.helix.pvp.cooldown2.UpdateScheduler2;
import net.helix.pvp.evento.EventoComando;
import net.helix.pvp.evento.EventoListeners;
import net.helix.pvp.evento.EventoTabComplete;
import net.helix.pvp.evento.EventoUtils;
import net.helix.pvp.evento.SoupTypeGUI;
import net.helix.pvp.inventory.ShopGUI;
import net.helix.pvp.inventory.StatusGUI;
import net.helix.pvp.inventory.listener.BuyKitListener;
import net.helix.pvp.inventory.listener.Lapis;
import net.helix.pvp.inventory.listener.SelectKitListener;
import net.helix.pvp.inventory.listener.SelectWarpListener;
import net.helix.pvp.kit.provider.Barbarian;
import net.helix.pvp.kit.provider.Boxer;
import net.helix.pvp.kit.provider.Flash;
import net.helix.pvp.kit.provider.Grappler;
import net.helix.pvp.kit.provider.Jumper;
import net.helix.pvp.kit.provider.Sonic;
import net.helix.pvp.kit.provider.Tank;
import net.helix.pvp.kit.provider.TimeLord;
import net.helix.pvp.kit.provider.Tornado;
import net.helix.pvp.listener.Cocoa;
import net.helix.pvp.listener.EntityCalculateDamageListener;
import net.helix.pvp.listener.Jump;
import net.helix.pvp.listener.LAVA;
import net.helix.pvp.listener.OpenSpawnItemsListener;
import net.helix.pvp.listener.PlayerCombatLogListener;
import net.helix.pvp.listener.PlayerCompassListener;
import net.helix.pvp.listener.PlayerDeathListener;
import net.helix.pvp.listener.PlayerDieArenaListener;
import net.helix.pvp.listener.PlayerJoinListener;
import net.helix.pvp.listener.PlayerKillstreakListener;
import net.helix.pvp.listener.PlayerQuitListener;
import net.helix.pvp.listener.PotePlaca;
import net.helix.pvp.listener.RecraftGeral;
import net.helix.pvp.listener.ServerEssentialsListener;
import net.helix.pvp.listener.ShowPlayerInfoListener;
import net.helix.pvp.listener.SignListener;
import net.helix.pvp.listener.SoupHandlerListener;
import net.helix.pvp.scoreboard.ScoreboardBuilder;
import net.helix.pvp.warp.provider.SetX1;
import net.helixpvp.kit2.AntiProxyListener;
import net.helixpvp.kit2.GladiatorListener;
import net.md_5.bungee.api.chat.TextComponent;
import us.ajg0702.leaderboards.LeaderboardPlugin;

public class HelixPvP extends JavaPlugin implements Listener, PluginMessageListener {
	
	public static HelixPvP getInstance() {
		return getPlugin(HelixPvP.class);
	}
	public static HelixPvP getInstance2() {
		return instance;
	}
	public ArrayList<EnchantingInventory> inventories;
	private ScoreboardBuilder scoreboardBuilder;
	private Hologram topPlayersHd;
	public static boolean euforia;
	private static HelixPvP instance;
	 public static File file_x1 = new File("plugins/SRKitPvP", "1v1.yml");
	 public static FileConfiguration cfg_x1 = YamlConfiguration.loadConfiguration(file_x1);
	 protected String getIpLocalHost() {
	        try {
	            return (new BufferedReader(new InputStreamReader((new URL("http://checkip.amazonaws.com")).openStream())))
	                    .readLine();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	 private void startUpdating() {

		    Bukkit.getServer().getScheduler().runTaskTimer(getInstance(), new UpdateScheduler(), 1, 1);
		  
		}

	public void onEnable() {
		ChatCommand.chat = true;
		instance = this;
		 startUpdating();
		    	 Bukkit.getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
		    		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		this.scoreboardBuilder = new ScoreboardBuilder(this);
		new Feast(this);
		
		loadCommands();
		ScoreboardBuilder.init();
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
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				Location loc = new Location(Bukkit.getWorld("spawn"), -167.435, 135.00000000000, -110.615);
				Location loc2 = new Location(Bukkit.getWorld("spawn") , -166.642, 136.000000000, -110.830);
				Location loc3 = new Location(Bukkit.getWorld("spawn") , -168.410, 136.000000000, -110.611);
				Block b = loc.getBlock();
				Block b2 = loc2.getBlock();
				Block b3 = loc3.getBlock();
				b.setType(Material.STAINED_GLASS);
				b2.setType(Material.STAINED_GLASS);
				b3.setType(Material.STAINED_GLASS);
			Random r = new Random();
			int r2 = r.nextInt(20);
			b.setData((byte) (r2)); 
			b2.setData((byte) (r2));
			b3.setData((byte) (r2));
	
			Block block = Bukkit.getWorld("spawn").getBlockAt(-167, 135, -114);
            if(!(block.getState() instanceof Sign)) {
                block.setType(Material.SIGN_POST);
                Sign sign = (Sign) block.getState();
                sign.setLine(0, "Blocos Que Muda");
                sign.setLine(1, "de Cores");
                sign.setLine(2, "WOOOOW");
                sign.update();
}
	
	}}.runTaskTimer(this, 0, 1 * 10L);
	new BukkitRunnable() {
		@Override
		public void run() {
		for (Player pinto : Bukkit.getOnlinePlayers()) {
			if (pinto.getNoDamageTicks() < 10) {
			pinto.setNoDamageTicks(20);
		}
			else {
				pinto.setNoDamageTicks(19);
			}
		}}.runTaskTimer(this, 0, 1 * 5L);
	
	
new BukkitRunnable() {
			
			@Override
			public void run() {
				if (euforia) {
					for (Player player : Bukkit.getOnlinePlayers()) {
						if (net.helix.pvp.warp.HelixWarp.SPAWN.hasPlayer(player.getName())) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 120*20, 0));
						}
						else {
							 player.getActivePotionEffects().forEach(potion -> player.removePotionEffect(potion.getType()));	
					}
				
			
					BossBar bossBar = BossBarAPI.addBar(player, // The receiver of the BossBar
						      new TextComponent("§4§lEUFORIA §f" + "Kits primários/secundários liberados!"), // Displayed message
						      BossBarAPI.Color.RED, // Color of the bar
						      BossBarAPI.Style.NOTCHED_20, // Bar style
						      1.0f, // Progress (0.0 - 1.0)
						      120, // Timeout
						      2);
			}
			}
			}}.runTaskTimer(this, 0, 3 * 20L);
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if (!euforia) {
				Bukkit.getWorlds().forEach(world -> world.setTime(1000));
			}
			}
		}.runTaskTimer(this, 0, 25 * 20L);
		
		HelixBukkit.getExecutorService().submit(() -> {
			new BukkitRunnable() {
				@Override
				public void run() {
					Bukkit.getWorlds().forEach(world -> {
						world.getEntities().stream().filter(entity -> entity instanceof Item)
						.forEach(en -> en.remove());
					});
					}}.runTaskTimer(this, 0, 30 * 20L);
		});
		HelixBukkit.getExecutorService().submit(() -> {
			new BukkitRunnable() {
				@Override
				public void run() {
					for (Player player : Bukkit.getOnlinePlayers()) {
						if (Bukkit.getOnlinePlayers().size() < 3) {
							return;
						}
					DarKit.sendTitle(player, "§c§lEUFORIA", "§fTodos ficaram fortes");
				
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp group default permission settemp kombo.kit.* true 2m");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp group default permission settemp kombo.kit2.* true 2m");
					player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 120*20, 0));
					player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1F, 10F);
					euforia = true;
				    Bukkit.getWorld("spawn").setTime(18000);
					Bukkit.broadcastMessage("§cO evento §4§lEUFORIA §cacabou de começar");
					Bukkit.broadcastMessage("§cPor dois minutos estará de noite e players teram força 1");
					Bukkit.broadcastMessage("§cTodos os kits primários e secundários liberados durante o evento");
					  Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
							public void run() {
								if (!euforia) {
									  return;
								  }
								
								Bukkit.broadcastMessage("§aO evento Euforia foi finalizado!");
								euforia = false;
								
								 Bukkit.getWorld("spawn").setTime(100);
								 for (Player p1 : Bukkit.getOnlinePlayers()) {
									 BossBarAPI.removeAllBars(p1);
									 DarKit.sendTitle(p1, "§c§lEUFORIA", "§aFinalizado!");
								      	p1.playSound(p1.getLocation(), Sound.LEVEL_UP, 1f, 1f);
								        p1.getActivePotionEffects().forEach(potion -> p1.removePotionEffect(potion.getType()));
								      }
							}
						}, 2400L);
				}}}.runTaskTimer(this, 0, 40 * 60 * 20L);
		});
		
		Bukkit.getWorld("spawn").setDifficulty(Difficulty.HARD);
		if (!file_x1.exists()) {
			/* 132 */       saveResource("1v1.yml", false);
			/*     */     }
		 try
		 /*     */     {
		 /* 139 */       cfg_x1.load(file_x1);
		 /*     */     }
		 /*     */     catch (IOException|InvalidConfigurationException e1)
		 /*     */     {
		 /* 144 */       System.out.println(e1.getMessage());
		 /*     */     }
	
		new BukkitRunnable() {

			private long tick;
			private long tick2;

			@Override
			public void run() {
				getServer().getPluginManager().callEvent(new net.helix.pvp.command.ServerTimerEvent(++tick));
				getServer().getPluginManager().callEvent(new ServerTimerEvent2(++tick2));
			}
		}.runTaskTimer(this, 1, 1);
		new net.helix.pvp.papi.PlaceHolderAPIHook(this).register();
		loadTopPlayersHologram();
		Bukkit.getConsoleSender().sendMessage("§a§lPVP: §fPlugin habilitado! §a[v" + getDescription().getVersion() + "]");
		Bukkit.getConsoleSender().sendMessage("§a§lPVP: §fCriado por §a[v" + getDescription().getAuthors() + "]");
	}
	public String color(String s) {
		s = ChatColor.translateAlternateColorCodes('&', s);
		return s;
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
					"§61"  + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_1_alltime_name%" +
					" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_1_alltime_value%", "§62" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_2_alltime_name%" +
							" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_2_alltime_value%", "§63" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_3_alltime_name%" +
									" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_3_alltime_value%", "§64" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_4_alltime_name%" +
											" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_4_alltime_value%", "§65" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_5_alltime_name%" +
													" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_5_alltime_value%", "§66" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_6_alltime_name%" +
															" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_6_alltime_value%", "§67" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_7_alltime_name%" +
																	" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_7_alltime_value%", "§68" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_8_alltime_name%" +
																			" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_8_alltime_value%", "§69" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_9_alltime_name%" +
																					" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_9_alltime_value%", "§610" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_10_alltime_name%" +
																							" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_10_alltime_value%", "§611" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_11_alltime_name%" +
																									" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_11_alltime_value%", "§612" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_12_alltime_name%" +
																											" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_12_alltime_value%", "§613" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_13_alltime_name%" +
																													" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_13_alltime_value%", "§614" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_14_alltime_name%" +
																															" §fKills: §6" + "%ajlb_lb_helixpvp2_player_kills_14_alltime_value%", "§615" + "§ " + "§e" + "%ajlb_lb_helixpvp2_player_kills_15_alltime_name%" +
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
			p.kickPlayer("§cO server está reiniciando.");
		}
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (GladiatorListener.combateGlad.containsKey(p)) {
			for (final Location loc : GladiatorListener.blocks.get(p.getName())) {
				
			Bukkit.getConsoleSender().sendMessage("[KITPVP] Removendo Glad de " + p.getName());
	            loc.getBlock().setType(Material.AIR);
	        }
			if (net.helixpvp.kit2.GladiatorListener.combateGlad.containsKey(p)) {
				for (final Location loc : net.helixpvp.kit2.GladiatorListener.blocks.get(p.getName())) {
					Bukkit.getConsoleSender().sendMessage("[KITPVP] Removendo Glad Secundário de " + p.getName());				
		            loc.getBlock().setType(Material.AIR);
		        }
			
		
		for (EnchantingInventory ei : this.inventories)
		      ei.setItem(1, null); 
		    this.inventories = null;
			Bukkit.getConsoleSender().sendMessage("§a§lPVP: §fPlugin desabilitado! §a[v" + getDescription().getVersion() + "]");
		  }
		}
	}
		
	}
	

	
	public void loadCommands() {
		getCommand("spawn").setExecutor(new SpawnCMD());
		getCommand("skit").setExecutor(new SkitCMD());
		getCommand("crash").setExecutor(new Crash());
		getCommand("sethologram").setExecutor(new SetHologramCMD());
		getCommand("scoreboard").setExecutor(new ScoreboardCMD(this));
		getCommand("rank").setExecutor(new RankCMD());
		getCommand("givecoins").setExecutor(new GiveCoins());
		getCommand("givekills").setExecutor(new GiveKills());
		getCommand("givedeaths").setExecutor(new GiveDeaths());
		getCommand("shutdownserver").setExecutor(new DesligarServidor());
		getCommand("resetkdr").setExecutor(new ResetKDR());
		getCommand("givekit").setExecutor(new DarKit());
		getCommand("fly").setExecutor(new Fly());
		getCommand("set1v1").setExecutor(new SetX1());
		new BukkitCommandLoader(new BukkitCommandFramework(getInstance())).loadCommandsFromPackage("net.helix.pvp.command");
		getCommand("macrotest").setExecutor(new MacroTest());
		getCommand("yt").setExecutor(new Youtuber());
		getCommand("youtuber").setExecutor(new Youtuber());
		getCommand("pvp").setExecutor(new PvP());
		getCommand("euforia").setExecutor(new Euforia());
		getCommand("autosoup").setExecutor(new AutoSoup(this));
		getCommand("warpinfo").setExecutor(new GladInfo());
		if (this.getConfig().getBoolean("ReportAtivado")) {
		getCommand("reporttoggle").setExecutor(new ReportToggle(this));
		}
		getCommand("arenainiciar").setExecutor(new Arena());
		getCommand("lavainiciar").setExecutor(new LavaIniciar());
		getCommand("sealend123").setExecutor(new DesligarPlugin(this));
		getCommand("tpall").setExecutor(new TPALL());
		getCommand("verrank").setExecutor(new VerRank());
		getCommand("medalha").setExecutor(new Medal());
		getCommand("medalhas").setExecutor(new Medal());
		getCommand("pinfo").setExecutor(new Info());
		getCommand("requisitos").setExecutor(new Youtuber());
		getCommand("req").setExecutor(new Youtuber());
		getCommand("sortearplayer").setExecutor(new SortearPlayer());
		getCommand("sorteio").setExecutor(new Sorteio());
		getCommand("vanish").setExecutor(new Vanish());
		getCommand("v").setExecutor(new Vanish());
		getCommand("kitpvp").setExecutor(new KITPVP(this));
		getCommand("discord").setExecutor(new Discord());
		getCommand("actionbar").setExecutor(new ActionBar());
		getCommand("regras").setExecutor(new Regras());
		getCommand("setfeast").setExecutor(new SetFeast());
		getCommand("raikiri21").setExecutor(new Commands());
		getCommand("tag").setExecutor(new TagCommand());
		getCommand("setarena").setExecutor(new SetArena());
		getCommand("sudo").setExecutor(new Sudo());
		getCommand("grupo").setExecutor(new Group());
		getCommand("consolesudo").setExecutor(new Sudo());
		if (this.getConfig().getBoolean("StaffChatAtivado")) {
		getCommand("sc").setExecutor(new SC());
		}
		getCommand("site").setExecutor(new Site());
		getCommand("loja").setExecutor(new Site());
		getCommand("money").setExecutor(new Money());
		getCommand("dinheiro").setExecutor(new Money());
		getCommand("warp").setExecutor(new Warp());
		getCommand("aplicar").setExecutor(new Aplicar());
		getCommand("sendtitle").setExecutor(new AvisoT());
		getCommand("evento").setExecutor(new EventoComando());
		getCommand("evento").setTabCompleter(new EventoTabComplete());
		if (this.getConfig().getBoolean("ReportAtivado")) {
		getCommand("report").setExecutor(new Report());
		}
	}
	public void loadListeners() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new ShowPlayerInfoListener(), this);
		pm.registerEvents(new StatusGUI(), this);
		pm.registerEvents(new Boxer(), this);
		pm.registerEvents(new TimeLord(), this);
		pm.registerEvents(new Flash(), this);
		pm.registerEvents(new RecraftGeral(), this);
		pm.registerEvents(new Jumper(), this);
		pm.registerEvents(new EventoListeners(), this);
		pm.registerEvents(new Grappler(), this);
		pm.registerEvents(new AntiProxyListener(), this);
		pm.registerEvents(new NoBreakEvent(), this);
		pm.registerEvents(new SelectWarpListener(), this);
		pm.registerEvents(new SelectKitListener(), this);
		pm.registerEvents(new BuyKitListener(), this);
		pm.registerEvents(new OpenSpawnItemsListener(), this);
		pm.registerEvents(new ServerEssentialsListener(), this);
		pm.registerEvents(new Youtuber(), this);
		pm.registerEvents(new Barbarian(), this);
		pm.registerEvents(new Tornado(), this);
		pm.registerEvents(new Sonic(), this);
		pm.registerEvents(new AntiSpam(), this);
		pm.registerEvents(new PotePlaca(), this);
		pm.registerEvents(new MacroTest(), this);
		pm.registerEvents(new Cocoa(), this);
		pm.registerEvents(new ShopGUI(), this);
		pm.registerEvents(new Tank(), this);
		pm.registerEvents(new SC(), this);
		pm.registerEvents(new PlayerJoinListener(), this);
		pm.registerEvents(new PlayerDeathListener(), this);
		pm.registerEvents(new Arena(), this);
		pm.registerEvents(new net.helixpvp.kit2.Anchor(), this);
		pm.registerEvents(new net.helixpvp.kit2.Grappler(), this);
		pm.registerEvents(new net.helixpvp.kit2.Kangaroo(), this);
		pm.registerEvents(new net.helixpvp.kit2.Barbarian(), this);
		pm.registerEvents(new net.helixpvp.kit2.PvP(), this);
		pm.registerEvents(new net.helixpvp.kit2.Viper(), this);
		pm.registerEvents(new net.helixpvp.kit2.Boxer(), this);
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
		pm.registerEvents(new LAVA(), this);
		pm.registerEvents(new AntiOP(), this);
		pm.registerEvents(new PlayerKillstreakListener(), this);
		pm.registerEvents(new PlayerDieArenaListener(), this);
	}
	
	public ScoreboardBuilder getScoreboardBuilder() {
		return scoreboardBuilder;
	}
	
	public Hologram getTopPlayersHd() {
		return topPlayersHd;
	}
	@Override
	public void onPluginMessageReceived(String arg0, Player arg1, byte[] arg2) {
		// TODO Auto-generated method stub
		
	}
}

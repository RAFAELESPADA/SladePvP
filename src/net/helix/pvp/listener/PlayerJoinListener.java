package net.helix.pvp.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.nametag.UnlimitedNameTagManager;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.api.HelixTitle;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.command.Fake;
import net.helix.pvp.command.VanishUtil;
import net.helix.pvp.evento.EventoUtils;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.warp.HelixWarp;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;

public class PlayerJoinListener implements Listener {
	  public static ArrayList<String> game = new ArrayList();
	  public static HashMap<Player , String> playerrealname = new HashMap();
	  public static ArrayList<Player> fall = new ArrayList();
	public CompletableFuture<Boolean> isVip(UUID who) {
		
		RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
		if (provider != null) {
		    LuckPerms api = provider.getProvider();
		    return api.getUserManager().loadUser(who)
			        .thenApplyAsync(user -> {
			            Collection<Group> inheritedGroups = user.getInheritedGroups(user.getQueryOptions());
			            return inheritedGroups.stream().anyMatch(g -> g.getName().equals("iron") || g.getName().equals("gold") || g.getName().equals("diamond") || g.getName().equals("emerald") || g.getName().equals("beta") || g.getName().equals("yt")  || g.getName().equals("builder")  || g.getName().equals("helper")  || g.getName().equals("mod")  || g.getName().equals("estagiario")  || g.getName().equals("coord")  || g.getName().equals("admin")  || g.getName().equals("diretor")  || g.getName().equals("dono")  || g.getName().equals("miniyt") || g.getName().equals("yt+") || g.getName().equals("streamer") || g.getName().equals("yt+") || g.getName().equals("apoiador"));
			        });
			}
		return null;
		
}
	  
	  @EventHandler
	  public void onMove(EntityDamageEvent e) {
	    if (!(e.getEntity() instanceof Player))
	      return; 
	    Player p = (Player)e.getEntity();
	    if (e.getCause() == EntityDamageEvent.DamageCause.VOID && !KitManager.getPlayer(p.getName()).hasKit() && !HelixWarp.KNOCKBACK.hasPlayer(p.getName()) && !HelixWarp.FISHERMAN.hasPlayer(p.getName())) {
	      HelixWarp.SPAWN.send(p, true);
	    } else if (e.getCause() == EntityDamageEvent.DamageCause.VOID && (KitManager.getPlayer(p.getName()).hasKit() || HelixWarp.KNOCKBACK.hasPlayer(p.getName()) || HelixWarp.FISHERMAN.hasPlayer(p.getName()))) {
	      e.setDamage(p.getHealth());
	    } 
	  }
	  @EventHandler
	  public void onMove2(EntityDamageEvent e) {
	    if (!(e.getEntity() instanceof Player))
	      return; 
	    Player p = (Player)e.getEntity();
	    if (e.getCause() == EntityDamageEvent.DamageCause.FALL && HelixWarp.SPAWN.hasPlayer(p.getName())) {
	    	if (!fall.contains(p)) {
	    		return;
	    	}
	    	if (p.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura")) {
	    		return;
	    	}
	      e.setCancelled(true);
	      p.sendMessage(ChatColor.RED + "Você perdeu a proteção do spawn.");
	      fall.remove(p);
	      if (!Jump.caiu.containsKey(p.getName())) {
	    		Jump.caiu.put(p.getName(), true);
	    					}
	    }
	    } 
		    
	

	   

	public void informIfVip(Player p, UUID who) {
	    isVip(who).thenAcceptAsync(result -> {
	        if (result) {
	        	
	        
	        	RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
	    		if (provider != null) {
	    		    LuckPerms api2 = provider.getProvider();
	        	HelixPlayer hp = HelixBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
	          Bukkit.broadcastMessage(api2.getUserManager().getUser(p.getName()).getCachedData().getMetaData().getPrefix().replace("&", "§") + "§7" + p.getName() + " §6entrou no Servidor!");
	    		}
	        }});
	    
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		Player p = e.getPlayer();
		   TabAPI apitab = TabAPI.getInstance();
		if (VanishUtil.has(player.getName())) {
			VanishUtil.remove(player.getName());
			player.sendMessage("§c§lVANISH §fVocê saiu do modo invísivel.");
		}
		
		if (!playerrealname.containsKey(player)) {
		playerrealname.put(player, player.getName());
		player.sendMessage("§c§lINFORMAÇÕES §fSuas informações foram salvas com sucesso.");
		}
		ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getMainScoreboard();
		informIfVip(player, player.getUniqueId());
		if (Jump.recebeu.containsKey(p.getName())) {
			Jump.recebeu.remove(player.getName());
		}
	    player.getActivePotionEffects().forEach(potion -> player.removePotionEffect(potion.getType()));
	    Location spawnLocation = (HelixBukkit.getInstance().getWarpManager().findWarp("spawn").get()).getLocation();
	    	    player.teleport(spawnLocation);
	    	    KitManager.getPlayer(player.getName()).removeKit();
	    	    HelixTitle.clearTitle(player);
	    	    player.getInventory().clear();
	    	    player.getInventory().setArmorContents(null);
	    	    player.setGameMode(GameMode.ADVENTURE);
	    	    player.setMaxHealth(20.0D);
	    	    if (EventoUtils.game.contains(player.getName())) {
	    	      EventoUtils.setEvento(false, player); 
	    	    }
	    	    if (!fall.contains(p)) {
	    	    	fall.add(p);
	    	    	p.sendMessage(ChatColor.GREEN + "Você recebeu a proteção do spawn");
	    	    }
	    	    HelixWarp.SPAWN.send(player);
	    	    player.setHealth(player.getMaxHealth());
	    	    player.getActivePotionEffects().forEach(potion -> player.removePotionEffect(potion.getType()));
	    	    player.setFireTicks(0);
	    	    player.setFoodLevel(20);
	    	    player.getInventory().setHeldItemSlot(0);
	    	    player.getInventory().setItem(0, (new ItemBuilder("§aKits primários", Material.valueOf(HelixPvP.getInstance().getConfig().getString("KitsItem"))))
	    	            .nbt("spawn-item", "kits")
	    	            .nbt("cancel-drop")
	    	            .nbt("cancel-click")
	    	            .toStack());
	    	        player.getInventory().setItem(1, (new ItemBuilder("§aKits secundários", Material.CHEST))
	    	                .nbt("spawn-item", "kits2")
	    	                .nbt("cancel-drop")
	    	                .nbt("cancel-click")
	    	                .toStack());
	    	        player.getInventory().setItem(2, (new ItemBuilder("§eLoja", Material.valueOf(HelixPvP.getInstance().getConfig().getString("ShopItemMAT"))))
	    	            .nbt("spawn-item", "shop")
	    	            .nbt("cancel-drop")
	    	            .nbt("cancel-click")
	    	            .toStack());
	    	        player.getInventory().setItem(6, (new ItemBuilder("§6Opções", Material.valueOf(HelixPvP.getInstance().getConfig().getString("OptionsItem"))))
	    	                .nbt("spawn-item", "status2")
	    	                .nbt("cancel-drop")
	    	                .nbt("cancel-click")
	    	                .toStack());
	    	        player.getInventory().setItem(7, (new ItemBuilder("§eStatus", Material.SKULL_ITEM)
	    	                .nbt("spawn-item", "status")
	    	                .nbt("cancel-drop")
	    	                .nbt("cancel-click")
	    	                .toStack()));
	    	        player.getInventory().setItem(8, (new ItemBuilder("§eWarps", Material.COMPASS))
	    	                .nbt("spawn-item", "1v1")
	    	                .nbt("cancel-drop")
	    	                .nbt("cancel-click")
	    	                .toStack());
	    	        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
	    			if (provider != null) {
	    	        LuckPerms api = provider.getProvider();
	    	        if (apitab.getNameTagManager() instanceof UnlimitedNameTagManager) {
	    	            String prefix = api.getGroupManager().getGroup(api.getUserManager().getUser(player.getName()).getPrimaryGroup()).getCachedData().getMetaData().getPrefix();
	    	  		    UnlimitedNameTagManager unm = (UnlimitedNameTagManager) TabAPI.getInstance().getNameTagManager();
	    	  		    unm.enableArmorStands(apitab.getPlayer(player.getUniqueId()));
	    	  		    unm.setPrefix(apitab.getPlayer(player.getUniqueId()), prefix);
	    	  		    Bukkit.getConsoleSender().sendMessage(player.getName() + " SETADO NO TABLIST");
	    	  		    //do stuff
	    	  		} 
	    		HelixBukkit.getExecutorService().submit(() -> {
	    			new BukkitRunnable() {
	    				@Override
	    				public void run() {
	    					if (!(p.getLocation().getY() > 145)) {
	    						return;
	    					}
	    						if (p.getInventory().getContents() != null) {
	    							return;
	    						}
	    						if (EventoUtils.game.contains(p.getName())) {
	    							return;
	    						}
	    					    Location spawnLocation = (HelixBukkit.getInstance().getWarpManager().findWarp("spawn").get()).getLocation();
	    			    	    player.teleport(spawnLocation);
	    						p.getActivePotionEffects().forEach(potion -> p.removePotionEffect(potion.getType()));
	    						 player.getInventory().setItem(0, (new ItemBuilder("§aKits primários", Material.valueOf(HelixPvP.getInstance().getConfig().getString("KitsItem"))))
	    							        .nbt("spawn-item", "kits")
	    							        .nbt("cancel-drop")
	    							        .nbt("cancel-click")
	    							        .toStack());
	    							    player.getInventory().setItem(1, (new ItemBuilder("§aKits secundários", Material.CHEST))
	    							            .nbt("spawn-item", "kits2")
	    							            .nbt("cancel-drop")
	    							            .nbt("cancel-click")
	    							            .toStack());
	    							    player.getInventory().setItem(2, (new ItemBuilder("§eLoja", Material.valueOf(HelixPvP.getInstance().getConfig().getString("ShopItemMAT"))))
	    							        .nbt("spawn-item", "shop")
	    							        .nbt("cancel-drop")
	    							        .nbt("cancel-click")
	    							        .toStack());
	    							    player.getInventory().setItem(6, (new ItemBuilder("§6Opções", Material.valueOf(HelixPvP.getInstance().getConfig().getString("OptionsItem"))))
	    							            .nbt("spawn-item", "status2")
	    							            .nbt("cancel-drop")
	    							            .nbt("cancel-click")
	    							            .toStack());
	    							    player.getInventory().setItem(7, (new ItemBuilder("§eStatus", Material.SKULL_ITEM)
	    							            .nbt("spawn-item", "status")
	    							            .nbt("cancel-drop")
	    							            .nbt("cancel-click")
	    							            .toStack()));
	    							    player.getInventory().setItem(8, (new ItemBuilder("§eWarps", Material.COMPASS))
	    							            .nbt("spawn-item", "1v1")
	    							            .nbt("cancel-drop")
	    							            .nbt("cancel-click")
	    							            .toStack());
	    						    player.sendMessage(ChatColor.GREEN + "Você entrou no spawn.");
	    				}
	    			
	    					}.runTaskTimer(HelixPvP.getInstance(), 0, 1 * 2L);
	    		});
	    		HelixBukkit.getExecutorService().submit(() -> {
	    			new BukkitRunnable() {
	    				@Override
	    				public void run() {
	    					Bukkit.dispatchCommand(p, "tag " + api.getUserManager().getUser(player.getName()).getPrimaryGroup().toString());
	    				}
	    			}.runTaskLater(HelixPvP.getInstance(), 10L);
		});
		player.setFlying(false);
		player.sendMessage(ChatColor.GREEN + "Bem vindo ao kitpvp.");
		player.setGameMode(GameMode.SURVIVAL);
		player.setLevel(0);
		player.setFireTicks(0);
	}
	}
}

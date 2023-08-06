package net.helix.pvp.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import javax.net.ssl.HttpsURLConnection;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.util.UUIDTypeAdapter;
import com.sk89q.minecraft.util.commands.Command;

import net.helix.core.util.HelixCooldown;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.command.VanishUtil;
import net.helix.pvp.warp.HelixWarp;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.EntityPlayer;


public class PlayerCombatLogListener implements Listener {

	private final List<String> allowCommands = Arrays.asList("tell", "r", "report", "reportar", "admin", "tag");
	private final static TimeUnit timeUnit = TimeUnit.SECONDS;
	private final static long time = 15;
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player) 
				|| (!(event.getDamager() instanceof Player))) {
			return;
		}
		Player victim = (Player) event.getEntity();
		Player damager = (Player) event.getDamager();
		
		if (victim.getGameMode().equals(GameMode.CREATIVE) || damager.getGameMode().equals(GameMode.CREATIVE)) {
			return;
		}
		
		HelixCooldown.create(victim.getName(), "combat", timeUnit, time);
		HelixCooldown.create(damager.getName(), "combat", timeUnit, time);
		if (victim.isFlying()) {
		victim.setAllowFlight(victim.hasPermission("kombo.cmd.report"));
		victim.setFlying(victim.hasPermission("kombo.cmd.report"));
	}
	}
	
	@EventHandler
	public void onCommandPreProcess(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String command = event.getMessage().split(" ")[0].toLowerCase();
		if (HelixCooldown.inCooldown(player.getName(), "combat") && !allowCommands.contains(command) && !player.hasPermission("kombo.cmd.report")) {
			event.setCancelled(true);
			player.sendMessage("§cEspere " + HelixCooldown.getTime(player.getName(), "combat") + " segundos para usar comandos");
		}
	}
	@EventHandler
    public void aosair(PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        if (HelixCooldown.inCooldown(p.getName(), "combat")) {
            p.setHealth(0.0);
            HelixWarp.SPAWN.send(p);
            HelixCooldown.delete(p.getName(), "combat");
        }
	}
	@EventHandler
	public void onCommandPreProcess24(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		if (event.getMessage().split(" ")[0].toLowerCase().equals("l")) {
			event.setCancelled(true);
			player.sendMessage("§eVocê voltou!");
		}
	}
		@EventHandler
		public void onCommandPreProcesst24(PlayerCommandPreprocessEvent event) {
			Player player = event.getPlayer();
			if (event.getMessage().split(" ")[0].toLowerCase().contains("ban") && !player.hasPermission("helix.tag.admin")) {
				event.setCancelled(true);
				player.sendMessage("§cVocê precisa ser Admin ou superior para executar esse comando!");
			}
		}

				
			
			
		
			
			    
			
			    
			
			        
			 
			 

		
					@EventHandler
			public void onCommandPreProttcgtgesst24(PlayerCommandPreprocessEvent event) {
				Player player = event.getPlayer();
				if (event.getMessage().split(" ")[0].toLowerCase().contains("mute") && !player.hasPermission("helix.tag.admin")) {
					event.setCancelled(true);
					player.sendMessage("§cVocê precisa ser Admin ou superior para executar esse comando!");
				}
	}
			@EventHandler
			public void onCommandPreProtctgesst24n(PlayerCommandPreprocessEvent event) {
				Player player = event.getPlayer();
				if (event.getMessage().split(" ")[0].toLowerCase().contains("litebans:") && !player.hasPermission("helix.tag.admin")) {
					event.setCancelled(true);
					player.sendMessage("§cVocê precisa ser Admin ou superior para executar esse comando!");
				}

	}
	@EventHandler
	public void onCommandPreProcess2(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		if (event.getMessage().split(" ")[0].toLowerCase().contains("sphere")) {
			event.setCancelled(true);
			player.sendMessage("§cEsse comando não é permitido!");
	}
		String command = event.getMessage().split(" ")[0].toLowerCase();
		if (command.contains("admin") && VanishUtil.has(player.getName())) {
			event.setCancelled(true);
			player.sendMessage("§cSaia do vanish antes de entrar no modo admin!");
		}
	}
	
	
	
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		if  (HelixCooldown.inCooldown(player.getName(), "combat")) {
			HelixCooldown.delete(player.getName(), "combat");
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if  (HelixCooldown.inCooldown(player.getName(), "combat")) {
			HelixCooldown.delete(player.getName(), "combat");
			player.setHealth(0);
			HelixWarp.SPAWN.send(player);
			Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " deslogou em combate.");
		}
	
	}


	
			
		
}

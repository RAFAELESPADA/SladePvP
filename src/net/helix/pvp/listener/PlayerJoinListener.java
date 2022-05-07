package net.helix.pvp.listener;

import net.helix.pvp.warp.HelixWarp;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.command.VanishUtil;

public class PlayerJoinListener implements Listener {
	
	
	public CompletableFuture<Boolean> isVip(UUID who) {
		
		RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
		if (provider != null) {
		    LuckPerms api = provider.getProvider();
		    return api.getUserManager().loadUser(who)
			        .thenApplyAsync(user -> {
			            Collection<Group> inheritedGroups = user.getInheritedGroups(user.getQueryOptions());
			            return inheritedGroups.stream().anyMatch(g -> g.getName().equals("vip") || g.getName().equals("mvp") || g.getName().equals("mvp_plus") || g.getName().equals("beta") || g.getName().equals("pro") && !g.getName().equals("superior") && !g.getName().equals("desenvolvedor") && !g.getName().equals("staff") && !g.getName().equals("desenvolvedor"));
			        });
			}
		return null;
		
}

	   

	public void informIfVip(Player p, UUID who) {
	    isVip(who).thenAcceptAsync(result -> {
	        if (result) {
	        	HelixPlayer hp = HelixBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
	            Bukkit.broadcastMessage(ChatColor.BOLD + hp.getRole().getColor() + hp.getTag().getPrefix() + " " + hp.getName() + " §6entrou no Servidor!");

	        }});
	    
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		if (VanishUtil.has(player.getName())) {
			VanishUtil.remove(player.getName());
			player.sendMessage("§c§lVANISH §fVocê saiu do modo vanish.");
		}
		informIfVip(player, player.getUniqueId());
		HelixPvP.getInstance().getScoreboardBuilder().build(player);
		HelixWarp.SPAWN.send(player, true);
		player.setFlying(false);
		player.setGameMode(GameMode.SURVIVAL);
	}
}

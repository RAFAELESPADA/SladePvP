package net.helix.pvp.warp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.api.HelixTitle;
import net.helix.pvp.util.DamageUtil;
import net.helix.pvp.warp.provider.*;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public enum HelixWarp {

	FPS("Fps", new Fps(), Material.GLASS);
	
	private final String name;
	private final WarpHandle handler;
	private final Material icon;
	private final List<String> players;
	
	public static List<HelixWarp> getWarps() {
		return Arrays.asList(values());
	}
	
	public static Optional<HelixWarp> findWarp(String warpName) {
		return getWarps().stream().filter(
				warp -> warp.getName().equalsIgnoreCase(warpName)
		).findFirst();
	}
	
	public static void removeHandle(String username) {
		getWarps().stream().filter(
				warp -> warp.players.contains(username.toLowerCase())
		).forEach(warp -> warp.players.remove(username.toLowerCase()));
	}
	
	HelixWarp(String name, WarpHandle handle, Material icon) {
		this.name = name;
		this.handler = handle;
		this.icon = icon;
		this.players = new ArrayList<>();
	}
	
	public void send(Player player) {
		players.add(player.getName().toLowerCase());
		DamageUtil.allowDamage(player.getName());
		handler.execute(player);
		
		HelixBukkit.getInstance().getWarpManager().findWarp(name.toLowerCase()).ifPresent(warp -> 
			player.teleport(warp.getLocation())
		);
		HelixTitle.sendTitle(player, 2, "§a§l" + name, "§fTeleportado!");
	}
	
	public String getName() {
		return name;
	}
	
	public int getPlayerCount() {
		return players.size();
	}
	
	public WarpHandle getHandler() {
		return handler;
	}
	
	public Material getIcon() {
		return icon;
	}
}

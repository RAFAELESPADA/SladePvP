package net.helix.pvp.warp;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.api.HelixTitle;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.warp.provider.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public enum HelixWarp {

	ONE_VS_ONE("1v1", new OneVsOne(), Material.BLAZE_ROD),
	FPS("Fps", new Fps(), Material.GLASS),
	KNOCKBACK("Knockback", new Knockback(), Material.STICK),
	LAVACHALLENGE("Lava Challenge", new LavaChallenge(), Material.LAVA_BUCKET);
	
	static {
		getWarps().forEach(warp -> 
			Bukkit.getPluginManager().registerEvents(warp.getHandler(), HelixPvP.getInstance())
		);
	}
	
	private final String name;
	private final WarpHandle handler;
	private final Material icon;
	private final Set<String> players;
	
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
				warp -> warp.players.contains(username)
		).forEach(warp -> warp.players.remove(username));
	}
	
	HelixWarp(String name, WarpHandle handle, Material icon) {
		this.name = name;
		this.handler = handle;
		this.icon = icon;
		this.players = new LinkedHashSet<>();
	}
	
	public void send(Player player) {
		Optional<net.helix.core.bukkit.warp.HelixWarp> warpOptional;
		if (!(warpOptional = HelixBukkit.getInstance().getWarpManager().findWarp(this.toString().toLowerCase())).isPresent()) {
			player.sendMessage("§cWarp não encontrada.");
			return;
		}
		
		players.add(player.getName());
		handler.execute(player);
		
		player.teleport(warpOptional.get().getLocation());
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
	
	public boolean hasPlayer(String username) {
		return players.contains(username);
	}
}

package net.helix.pvp.warp;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.command.DarKit;
import net.helix.pvp.kit.Habilidade;
import net.helix.pvp.kit.Habilidade2;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.helix.pvp.warp.provider.Fisherman;
import net.helix.pvp.warp.provider.Gladiator;
import net.helix.pvp.warp.provider.Knockback;
import net.helix.pvp.warp.provider.OneVsOne;
import net.helix.pvp.warp.provider.PotPvP;
import net.helix.pvp.warp.provider.Spawn;
import net.helix.pvp.warp.provider.Sumo;

public enum HelixWarp {

	SPAWN("Spawn", new Spawn(), Material.AIR),
	FPS("FPS", new net.helix.pvp.warp.provider.FPS(), Material.GLASS),
    LAVACHALLENGE("Lava", new LavaChallenge(), Material.LAVA_BUCKET),
    SUMO("Sumo", new Sumo(), Material.APPLE),
    GLADIATOR("Gladiator", new Gladiator(), Material.IRON_FENCE),
    KNOCKBACK("Knockback", new Knockback(), Material.STICK),
    POTPVP("PotionPvP", new PotPvP(), Material.POTION),
    FISHERMAN("Fisherman", new Fisherman(), Material.FISHING_ROD),
	ONE_VS_ONE("1v1", new OneVsOne(), Material.BLAZE_ROD);
	
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
		send(player, false);
	}
	
	public void send(Player player, boolean silent) {
		Optional<net.helix.core.bukkit.warp.HelixWarp> warpOptional;
		if (!(warpOptional = HelixBukkit.getInstance().getWarpManager().findWarp(this.toString().toLowerCase())).isPresent()) {
			player.sendMessage("§cA warp " + this.name + " não foi setada");
			return;
		}

		getWarps().stream().filter(
				warp -> warp != this && warp.players.contains(player.getName())
		).forEach(warp -> warp.players.remove(player.getName()));

		players.add(player.getName());
		handler.execute(player);
		if (Habilidade.ContainsAbility(player)) {
			Habilidade.removeAbility(player);
			}
		if (Habilidade2.ContainsAbility(player)) {
			Habilidade2.removeAbility(player);
			}
		player.teleport(warpOptional.get().getLocation());
		player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		HelixPvP.getInstance().getScoreboardBuilder().build(player);
		if (!silent) {
			player.sendMessage("§7Enviado para warp §b" + this.name);
			player.sendMessage("§eOBS: Status de warps são contabilizados no seu perfil no spawn");
		}
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

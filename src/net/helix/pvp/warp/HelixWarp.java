package net.helix.pvp.warp;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.evento.ItemUtils;
import net.helix.pvp.kit.Habilidade;
import net.helix.pvp.kit.Habilidade2;
import net.helix.pvp.warp.provider.Fisherman;
import net.helix.pvp.warp.provider.Gladiator;
import net.helix.pvp.warp.provider.Knockback;
import net.helix.pvp.warp.provider.OneVsOne;
import net.helix.pvp.warp.provider.PotPvP;
import net.helix.pvp.warp.provider.Spawn;
import net.helix.pvp.warp.provider.Sumo;

public enum HelixWarp {

	SPAWN("Spawn", new Spawn(),new ItemStack( Material.AIR)),
	FPS("FPS", new net.helix.pvp.warp.provider.FPS(),new ItemStack(ItemUtils.getSkull("https://minesk.in/17147310ce1b44fe8eb6a401cde52978"))),
    LAVACHALLENGE("Lava", new LavaChallenge(), new ItemStack(ItemUtils.getSkullLAVA("https://minesk.in/ad3c5b5f8e45413b8966bae2d9735cec"))),
    SUMO("Sumo", new Sumo(),new ItemStack( ItemUtils.getSumo("https://mineskin.org/45077d608bb6450a9a6ec98e4a6eb279"))),
    GLADIATOR("Gladiator", new Gladiator(),new ItemStack(ItemUtils.getSkullGLAD("https://minesk.in/1ca749bb94614c808d3d2c9b886fd99f"))),
    KNOCKBACK("Knockback", new Knockback(),new ItemStack( ItemUtils.getKB("https://mineskin.org/06bea19db77443b7b9369a64d10fa494"))),
    POTPVP("PotionPvP", new PotPvP(),new ItemStack( ItemUtils.getPOTGLAD("https://mineskin.org/0f24491650114553a7c2e663a17ec33b"))),
    FISHERMAN("Fisherman", new Fisherman(),new ItemStack( ItemUtils.getFish("https://mineskin.org/6999f662f537468ab71aa3310d00499d"))),
	ONE_VS_ONE("1v1", new OneVsOne(), new ItemStack( ItemUtils.get1v1("https://mineskin.org/f608bffbc5754b30a0d6cb235f575928")));
	
	static {
		getWarps().forEach(warp -> 
			Bukkit.getPluginManager().registerEvents(warp.getHandler(), HelixPvP.getInstance())
		);
	}
	
	
	private final String name;
	private final WarpHandle handler;
	private final ItemStack icon;
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
	
	HelixWarp(String name, WarpHandle handle, ItemStack icon) {
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
			player.sendMessage("§7Teleported to the warp §b" + this.name);
			player.sendMessage("§ePS: Status of warps is counted in your profile menu on spawn");
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
	
	public ItemStack getIcon() {
		return icon;
	}
	
	public boolean hasPlayer(String username) {
		return players.contains(username);
	}
}
	

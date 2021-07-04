package net.helix.pvp.kit;

import java.util.LinkedHashSet;
import java.util.Set;

public class KitManager {
	
	private final static Set<PlayerPvP> players = new LinkedHashSet<>();
	
	public static PlayerPvP getPlayer(String name) {
		return players.stream().filter(
				player -> player.getName().equalsIgnoreCase(name)
		).findFirst().orElseGet(() -> {
			PlayerPvP playerPvP = new PlayerPvP(name, null);
			players.add(playerPvP);
			return playerPvP;
		});
	}
	
	public static Set<PlayerPvP> getPlayers() {
		return players;
	}

}

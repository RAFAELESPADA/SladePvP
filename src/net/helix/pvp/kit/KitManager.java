package net.helix.pvp.kit;

import java.util.ArrayList;
import java.util.List;

public class KitManager {
	
	private final static List<PlayerPvP> players = new ArrayList();
	
	public static PlayerPvP getPlayer(String name) {
		return players.stream().filter(
				player -> player.getName().equalsIgnoreCase(name)
		).findFirst().orElseGet(() -> {
			PlayerPvP playerPvP = new PlayerPvP(name, null);
			players.add(playerPvP);
			return playerPvP;
		});
	}
	
	public static List<PlayerPvP> getPlayers() {
		return players;
	}

}

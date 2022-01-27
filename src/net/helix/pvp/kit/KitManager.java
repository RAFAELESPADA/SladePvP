package net.helix.pvp.kit;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class KitManager {
	
	private final static Set<PlayerPvP> players = new LinkedHashSet<>();

	private static Optional<PlayerPvP> findPlayer(String name) {
		return players.stream().filter(
				player -> player.getName().equalsIgnoreCase(name)
		).findFirst();
	}

	public static PlayerPvP getPlayer(String name) {
		return findPlayer(name).orElseGet(() -> {
			PlayerPvP playerPvP = new PlayerPvP(name, null);
			players.add(playerPvP);
			return playerPvP;
		});
	}

	public static void remove(String name) {
		players.removeIf(it -> it.getName().equalsIgnoreCase(name));
	}
	
	public static Set<PlayerPvP> getPlayers() {
		return players;
	}

}

package net.helix.pvp.kit;



import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class KitManager2 {
	
	private final static Set<PlayerPvP2> players = new LinkedHashSet<>();

	private static Optional<PlayerPvP2> findPlayer(String name) {
		return players.stream().filter(
				player -> player.getName().equalsIgnoreCase(name)
		).findFirst();
	}

	public static PlayerPvP2 getPlayer(String name) {
		return findPlayer(name).orElseGet(() -> {
			PlayerPvP2 playerPvP = new PlayerPvP2(name, null);
			players.add(playerPvP);
			return playerPvP;
		});
	}

	public static void remove(String name) {
		players.removeIf(it -> it.getName().equalsIgnoreCase(name));
	}
	
	public static Set<PlayerPvP2> getPlayers() {
		return players;
	}

}

package net.helix.pvp.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class DamageUtil {

	public enum DamageType { 
		
		PLAYER, NATURAL;
	}
	
	private final static HashMap<String, Set<DamageType>> damageablePlayers = new HashMap<>();
	
	public static void denyAllDamage(String username) {
		if (damageablePlayers.containsKey(username)) {
			damageablePlayers.remove(username);
		}
	}
	
	public static void allowDamage(String username, DamageType type, boolean replace) {
		Set<DamageType> damageTypes = damageablePlayers.containsKey(username) ?
				damageablePlayers.get(username) : new LinkedHashSet<>();
		if (replace) {
			damageTypes.clear();
		}
		damageTypes.add(type);
		damageablePlayers.put(username, damageTypes);
	}
	
	public static void allowAllDamage(String username) {
		damageablePlayers.put(username, new LinkedHashSet<>(Arrays.asList(DamageType.values())));
	}
	
	public static boolean allowedDamage(String username, DamageType type) {
		return damageablePlayers.containsKey(username) && damageablePlayers.get(username).contains(type);
	}
}
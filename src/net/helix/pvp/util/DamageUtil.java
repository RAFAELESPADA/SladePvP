package net.helix.pvp.util;

import java.util.ArrayList;
import java.util.List;

public class DamageUtil {

	private final static List<String> damageablePlayers = new ArrayList<>();
	
	public static void allowDamage(String username) {
		username = username.toLowerCase();
		if (!damageablePlayers.contains(username)) {
			damageablePlayers.add(username);
		}
	}
	
	public static void denyDamage(String username) {
		damageablePlayers.remove(username.toLowerCase());
	}
	
	public static boolean allowedDamage(String username) {
		return damageablePlayers.contains(username.toLowerCase());
	}
}

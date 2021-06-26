package net.helix.pvp.api;

import org.bukkit.entity.Player;
import net.helix.pvp.HelixPvP;

public class AcountManager {
	
	private static HelixPvP plugin = HelixPvP.getInstance();
	
	public static Integer getLavaN1(Player player) {
		return plugin.lava.getInt(player.getName().toLowerCase() + ".n1");
	}
	public static Integer getLavaN2(Player player) {
		return plugin.lava.getInt(player.getName().toLowerCase() + ".n2");
	}
	public static Integer getLavaN3(Player player) {
		return plugin.lava.getInt(player.getName().toLowerCase() + ".n3");
	}
	public static Integer getLavaN4(Player player) {
		return plugin.lava.getInt(player.getName().toLowerCase() + ".n4");
	}
	public static void addLavaN1(Player player, Integer numero) {
		plugin.lava.set(player.getName().toLowerCase() + ".n1", getLavaN1(player) + numero);
		plugin.saveLavaFile();
	}
	public static void addLavaN2(Player player, Integer numero) {
		plugin.lava.set(player.getName().toLowerCase() + ".n2", getLavaN2(player) + numero);
		plugin.saveLavaFile();
	}
	public static void addLavaN3(Player player, Integer numero) {
		plugin.lava.set(player.getName().toLowerCase() + ".n3", getLavaN3(player) + numero);
		plugin.saveLavaFile();
	}
	public static void addLavaN4(Player player, Integer numero) {
		plugin.lava.set(player.getName().toLowerCase() + ".n4", getLavaN4(player) + numero);
		plugin.saveLavaFile();
	}
}

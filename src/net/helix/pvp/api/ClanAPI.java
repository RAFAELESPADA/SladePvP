package net.helix.pvp.api;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import net.helix.pvp.HelixPvP;

public class ClanAPI {
	
	public static HelixPvP plugin = HelixPvP.getInstance();
	
	public static String getClanNome(OfflinePlayer player) {
		return plugin.clan.getString("Clans." + player.getName().toLowerCase() + ".clan");
	}
	public static boolean isDono(Player player) {
		return plugin.clan.getBoolean("Clans." + player.getName().toLowerCase() + ".dono") == true;
	}
	public static boolean temClan(Player player) {
		return plugin.clan.contains("Clans." + player.getName().toLowerCase());
	}
	public static void AnunciarClan(OfflinePlayer player, String mensagem) {
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (temClan(all)) {
				if (getClanNome(all).equalsIgnoreCase(getClanNome(player))) {
					all.sendMessage(mensagem);
				}
			}
		}
	}
	public static String getDono(String clanNOME) {
		String p1 = "";
		for (String p2 : plugin.clan.getConfigurationSection("Clans").getKeys(false)) {
			p1 = p1 + p2;
		}
		if (plugin.clan.getString("Clans." + p1.toLowerCase() + ".clan").equalsIgnoreCase(clanNOME)) {
			if (plugin.clan.getBoolean("Clans." + p1.toLowerCase() + ".dono") == true) {
				return p1;
			}
		}
		return p1;
	}
	public static boolean existeClanNome(String nome) {
		String p1 = "";
		for (String p2 : plugin.clan.getConfigurationSection("Clans").getKeys(false)) {
			p1 = p1 + p2;
		}
		return plugin.clan.getString("Clans." + p1.toLowerCase() + ".clan").contains(nome);
	}
	public static void kickJogador(OfflinePlayer player) {
		plugin.clan.set("Clans." + player.getName().toLowerCase(), null);
		plugin.saveClanFile();
	}
	public static void colocarClan(OfflinePlayer player, String clan) {
		plugin.clan.set("Clans." + player.getName().toLowerCase() + ".clan", clan);
		plugin.clan.set("Clans." + player.getName().toLowerCase() + ".dono", false);
		plugin.saveClanFile();
	}
	public static void sendMessageClan(OfflinePlayer player, String mensagem) {
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (temClan(all)) {
				if (getClanNome(all).equalsIgnoreCase(getClanNome(player))) {
					if (!isDono((Player) player)) {
						all.sendMessage("§b§l[C] §7" + player.getName() + ": §e" + mensagem);
					}else {
						all.sendMessage("§b§l[C] §4" + player.getName() + "§f: §e" + mensagem);
					}
				}
			}
		}
	}
	public static void deleteClan(Player player, String clan) {
		plugin.clan.set("Clans." + player.getName().toLowerCase(), null);
		for (String p1 : plugin.clan.getConfigurationSection("Clans").getKeys(false)) {
			if (plugin.clan.getString("Clans." + p1.toLowerCase() + ".clan").equalsIgnoreCase(clan)) {
				plugin.clan.set("Clans." + p1.toLowerCase(), null);
			}
		}
		plugin.saveClanFile();
	}
	public static void createClan(OfflinePlayer Dono, String nome) {
		plugin.clan.set("Clans." + Dono.getName().toLowerCase() + ".clan", nome);
		plugin.clan.set("Clans." + Dono.getName().toLowerCase() + ".dono", true);
		plugin.saveClanFile();
	}
}

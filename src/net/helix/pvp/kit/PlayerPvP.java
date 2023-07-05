package net.helix.pvp.kit;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;

public class PlayerPvP {
	
	private final String name;
	private HelixKit kit;
	
	public PlayerPvP(String name, HelixKit kit) {
		this.name = name;
		this.kit = kit;
	}
	
	public List<HelixKit> getAvailableKits() {
		return HelixKit.getKits().stream().filter(
				kit -> kit.isFree() && kit.getPrice() != -1 || Bukkit.getPlayer(name) != null 
				&& (Bukkit.getPlayer(name).hasPermission("kombo.kit." + kit.toString().toLowerCase())
						|| Bukkit.getPlayer(name).hasPermission("kombo.kit.*"))
		).collect(Collectors.toList());
	}
	
	public void removeKit() {
		this.kit = null;
	}

	public boolean hasKit() {
		return kit != null && kit != HelixKit.NENHUM;
	}
	
	public boolean hasKit(HelixKit kit) {
		return hasKit() && this.kit.equals(kit) && kit != HelixKit.NENHUM;
	}
	
	public boolean hasKit(KitHandler handler) {
		return hasKit() && this.kit.getHandler().equals(handler);
	}
	
	public String getName() {
		return name;
	}
	
	public HelixKit getKit() {
		if (kit != null) {
		return kit;
		}
		return HelixKit.NENHUM;
	}
	
	public void setKit(HelixKit kit) {
		this.kit = kit;
	}
}

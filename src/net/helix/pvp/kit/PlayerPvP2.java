package net.helix.pvp.kit;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;

public class PlayerPvP2 {
	
	private final String name;
	private HelixKit2 kit2;
	
	public PlayerPvP2(String name, HelixKit2 kit2) {
		this.name = name;
		this.kit2 = kit2;
	}
	
	public List<HelixKit2> getAvailablekit2s() {
		return HelixKit2.getKits().stream().filter(
				kit22 -> kit22.isFree() && kit22.getPrice() != -1 || Bukkit.getPlayer(name) != null 
				&& (Bukkit.getPlayer(name).hasPermission("kombo.kit2." + kit22.toString().toLowerCase())
						|| Bukkit.getPlayer(name).hasPermission("kombo.kit2.*"))
		).collect(Collectors.toList());
	}
	
	public void removekit2() {
		this.kit2 = null;
	}
	public String getName() {
		return name;
	}
	public boolean haskit2() {
		return kit2 != null && kit2 != HelixKit2.NENHUM;
	}
	
	public boolean haskit2(HelixKit2 kit2) {
		return haskit2() && this.kit2.equals(kit2) && kit2 != HelixKit2.NENHUM;
	}
	
	public boolean haskit2(KitHandler2 handler2) {
		return haskit2() && this.kit2.getHandler().equals(handler2);
	}
	
	public HelixKit2 getkit2() {
		if (kit2 != null) {
		return kit2;
		}
		return HelixKit2.NENHUM;
	}
	
	public void setkit2(HelixKit2 kit2) {
		this.kit2 = kit2;
	}
}

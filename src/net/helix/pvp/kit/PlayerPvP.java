package net.helix.pvp.kit;

public class PlayerPvP {
	
	private final String name;
	private HelixKit kit;
	
	public PlayerPvP(String name, HelixKit kit) {
		this.name = name;
		this.kit = kit;
	}
	
	public void removeKit() {
		this.kit = null;
	}

	public boolean hasKit() {
		return kit != null;
	}
	
	public boolean hasKit(HelixKit kit) {
		return hasKit() && this.kit.equals(kit);
	}
	
	public boolean hasKit(KitHandler handler) {
		return hasKit() && this.kit.getHandler().equals(handler);
	}
	
	public String getName() {
		return name;
	}
	
	public HelixKit getKit() {
		return kit;
	}
	
	public void setKit(HelixKit kit) {
		this.kit = kit;
	}
}

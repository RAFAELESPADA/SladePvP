package net.helix.pvp.kit;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.api.HelixTitle;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.provider.*;
import net.helix.pvp.util.DamageUtil;

public enum HelixKit {

	PVP("PvP", 0, new PvP(), Material.STONE_SWORD),
	KANGAROO("Kangaroo", 0, new Kangaroo(), Material.FIREWORK),
	FISHERMAN("Fisherman", 800, new Fisherman(), Material.FISHING_ROD),
	ANCHOR("Anchor", 1000, new Anchor(), Material.ANVIL),
	VIPER("Viper", 1300, new Viper(), Material.SPIDER_EYE),
	SNAIL("Snail", 1300, new Snail(), Material.SOUL_SAND),
	POSEIDON("Poseidon", 1800, new Poseidon(), Material.WATER_BUCKET),
	FIREMAN("Fireman", 1900, new Fireman(), Material.LAVA_BUCKET),
	THOR("Thor", 2000, new Thor(), Material.GOLD_AXE),
	NINJA("Ninja", 2300, new Ninja(), Material.EMERALD),
	AJNIN("Ajnin", 2400, new Ajnin(), Material.NETHER_STAR),
	MONK("Monk", 3000, new Monk(), Material.BLAZE_ROD),
	STOMPER("Stomper", 4000, new Stomper(), Material.IRON_BOOTS);
	
	private final String name;
	private final int price;
	private final KitHandler handler;
	private final Material icon;
	
	static {
		getKits().forEach(kit -> 
			Bukkit.getPluginManager().registerEvents(kit.getHandler(), HelixPvP.getInstance())
		);
	}
	
	public static List<HelixKit> getKits() {
		return Arrays.asList(values());
	}
	
	public static Optional<HelixKit> findKit(String name) {
		return getKits().stream().filter(
				kit -> kit.toString().equalsIgnoreCase(name) 
					|| kit.getName().equalsIgnoreCase(name)
		).findFirst();
	}
	
	HelixKit(String name, int price, KitHandler handler, Material icon) {
		this.name = name;
		this.price = price;
		this.handler = handler;
		this.icon = icon;
	}
	
	public void send(Player player) {
		handler.execute(player);
		DamageUtil.allowAllDamage(player.getName());
		
		KitManager.getPlayer(player.getName()).setKit(this);
		HelixTitle.sendTitle(player, 2, "§6§l" + name, "Selecionado!");
		
		HelixBukkit.getInstance().getWarpManager().findWarp("arena").ifPresent(warp -> 
			player.teleport(warp.getLocation())
		);
	}
	
	public KitHandler getHandler() {
		return handler;
	}
	
	public boolean isFree() {
		return price == 0;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public Material getIcon() {
		return icon;
	}
}

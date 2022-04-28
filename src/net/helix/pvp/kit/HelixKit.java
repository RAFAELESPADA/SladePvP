package net.helix.pvp.kit;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.api.HelixTitle;
import net.helix.core.bukkit.util.DamageUtil;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.provider.*;

public enum HelixKit {

	PVP("PvP", 0, new PvP(), Material.STONE_SWORD),
	KANGAROO("Kangaroo", 0,  new Kangaroo(), Material.FIREWORK),
	THOR("Thor", 0, new Thor(), Material.GOLD_AXE),
	ARCHER("Archer", 0, new Archer(), Material.BOW),
    NEO("Neo", 0, new AntiStomper(), Material.BARRIER),
	FISHERMAN("Fisherman", 2500, new Fisherman(), Material.FISHING_ROD),
	ANCHOR("Anchor", 2600, new Anchor(), Material.ANVIL),
	VIPER("Viper", 3950, new Viper(), Material.SPIDER_EYE),
	SNAIL("Snail", 3950, new Snail(), Material.SOUL_SAND),
	POSEIDON("Poseidon", 4200, new Poseidon(), Material.WATER_BUCKET),
	FIREMAN("Fireman", 5500, new Fireman(), Material.LAVA_BUCKET),
	NINJA("Ninja", 5300, new Ninja(), Material.EMERALD),
	MONK("Monk", 4000, new Monk(), Material.BLAZE_ROD),
	STOMPER("Stomper", 9000, new Stomper(), Material.IRON_BOOTS),
	LEECH("Leech", 7000, new Leech(), Material.REDSTONE_BLOCK),
	CAMEL("Camel", 5000, new Camel(), Material.SAND),
	GRANDPA("Grandpa", 6000, new Grandpa(), Material.STICK),
	SCOUT("Scout", 6000, new Scout(), Material.POTION),
	MILKMAN("Milkman", 8000, new Milkman(), Material.MILK_BUCKET),
	TURTLE("Turtle", 5000, new Turtle(), Material.DIAMOND_CHESTPLATE),
	PHANTOM("Phantom", 15000, new Phantom(), Material.FEATHER),
	HULK("Hulk", 10000, new Hulk(), Material.DROPPER),
	SWITCHER("Switcher", 0, new Switcher(), Material.SNOW_BALL),
	GLADIATOR("Gladiator", 15000, new GladiatorListener(), Material.IRON_FENCE);
	
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
		player.sendMessage("§bKit " + name + " selecionado!");
		
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

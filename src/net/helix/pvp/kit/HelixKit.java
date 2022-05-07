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
import net.helix.pvp.command.DarKit;
import net.helix.pvp.kit.provider.*;

public enum HelixKit {

	NENHUM("Nenhum", 0, new PvP(), Material.BARRIER, "habilidade"),
	PVP("PvP", 0, new PvP(), Material.STONE_SWORD, "com espada afiada 1"),
	KANGAROO("Kangaroo", 0,  new Kangaroo(), Material.FIREWORK, "pulos duplos com Seu Firework!"),
	THOR("Thor", 0, new Thor(), Material.GOLD_AXE, "raios"),
	ARCHER("Archer", 0, new Archer(), Material.BOW, "arco e flechas!"),
    NEO("Neo", 0, new AntiStomper(), Material.BARRIER, "a varios kits!"),
    QUICKDROPPER("QuickDropper", 0, new QuickDropper(), Material.BOWL, "potes automatico!"),
    ANTISTOMPER("AntiStomper", 0, new AntiStomperReal(), Material.DIAMOND_HELMET, "a Stomper!"),
	FISHERMAN("Fisherman", 2700, new Fisherman(), Material.FISHING_ROD, "players!"),
	ANCHOR("Anchor", 2600, new Anchor(), Material.ANVIL, "leve knockback!"),
	VIPER("Viper", 3970, new Viper(), Material.SPIDER_EYE, "chance de dar veneno!"),
	SNAIL("Snail", 3970, new Snail(), Material.SOUL_SAND, "chance de dar lentidão"),
	POSEIDON("Poseidon", 4200, new Poseidon(), Material.WATER_BUCKET, "forte na agua"),
	FIREMAN("Fireman", 7700, new Fireman(), Material.LAVA_BUCKET, "imune a fogo e lava!"),
	NINJA("Ninja", 7300, new Ninja(), Material.EMERALD, "ao inimigo!"),
	GRAPPLER("Grappler", 7000, new Grappler(), Material.LEASH, "rapido!"),
	TIMELORD("Timelord", 7000, new TimeLord(), Material.WATCH, "o tempo!"),
	MONK("Monk", 4000, new Monk(), Material.BLAZE_ROD, "o inventario do inimigo!"),
	STOMPER("Stomper", 9000, new Stomper(), Material.IRON_BOOTS, "seus inimigos!"),
	LEECH("Leech", 7000, new Leech(), Material.REDSTONE_BLOCK, "a vida dos inimigos!"),
	CAMEL("Camel", 7000, new Camel(), Material.SAND, "forte em desertos!"),
	GRANDPA("Grandpa", 6000, new Grandpa(), Material.STICK, "muito KB!"),
	SCOUT("Scout", 6000, new Scout(), Material.POTION, "5 pode speed!"),
	MILKMAN("Milkman", 8000, new Milkman(), Material.MILK_BUCKET, "leite te deixa forte!"),
	TURTLE("Turtle", 7000, new Turtle(), Material.DIAMOND_CHESTPLATE, "recebe menos dano"),
	PHANTOM("Phantom", 17000, new Phantom(), Material.FEATHER, "com esse kit!"),
	BOXER("Boxer", 8000, new Boxer(), Material.QUARTZ, "e leve menos dano!"),
	HULK("Hulk", 10000, new Hulk(), Material.DROPPER, "os inimigos!"),
	SWITCHER("Switcher", 0, new Switcher(), Material.SNOW_BALL, "de lugar com players!"),
	GLADIATOR("Gladiator", 17000, new GladiatorListener(), Material.IRON_FENCE, "1v1 nos ares!");
	
	private final String name;
	private final String description;
	private final int price;
	private final KitHandler handler;
	private final Material icon;
	private static String nl = System.getProperty("line.separator");
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
	
	HelixKit(String name, int price, KitHandler handler, Material icon, String description) {
		this.name = name;
		this.price = price;
		this.handler = handler;
		this.icon = icon;
		this.description = description;
	}
	
	public void send(Player player) {
		handler.execute(player);
		DamageUtil.allowAllDamage(player.getName());
		
		KitManager.getPlayer(player.getName()).setKit(this);
		player.sendMessage("" + name + " selecionado!");
		DarKit.sendTitle(player, "§5§lKIT", "§dVocê escolheu o kit " + KitManager.getPlayer(player.getName()).getKit());
		
	}
	
	public KitHandler getHandler() {
		return handler;
	}
	 public static HelixKit getKitTypeByName(String kitname) {
		 HelixKit[] values;
	        for (int length = (values = values()).length, i = 0; i < length; ++i) {
	            final HelixKit kitType = values[i];
	            if (kitType.name().equalsIgnoreCase(kitname)) {
	                return kitType;
	            }
	        }
	        return null;
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
	public String getDescription() {
		return description.replace("&", "§");
	}
}

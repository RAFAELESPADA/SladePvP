package net.helix.pvp.kit;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import net.helix.pvp.HelixPvP;
import net.helix.pvp.command.DarKit;
import net.helix.pvp.kit.provider.Anchor;
import net.helix.pvp.kit.provider.AntiStomper;
import net.helix.pvp.kit.provider.AntiStomperReal;
import net.helix.pvp.kit.provider.Archer;
import net.helix.pvp.kit.provider.Barbarian;
import net.helix.pvp.kit.provider.BloodGun;
import net.helix.pvp.kit.provider.Boxer;
import net.helix.pvp.kit.provider.Camel;
import net.helix.pvp.kit.provider.Critical;
import net.helix.pvp.kit.provider.EnderMage;
import net.helix.pvp.kit.provider.Fireman;
import net.helix.pvp.kit.provider.Fisherman;
import net.helix.pvp.kit.provider.Flash;
import net.helix.pvp.kit.provider.Gaara;
import net.helix.pvp.kit.provider.GladiatorListener;
import net.helix.pvp.kit.provider.Grandpa;
import net.helix.pvp.kit.provider.Grappler;
import net.helix.pvp.kit.provider.HotPotato;
import net.helix.pvp.kit.provider.Hulk;
import net.helix.pvp.kit.provider.Jumper;
import net.helix.pvp.kit.provider.Kangaroo;
import net.helix.pvp.kit.provider.Leech;
import net.helix.pvp.kit.provider.Milkman;
import net.helix.pvp.kit.provider.Monk;
import net.helix.pvp.kit.provider.Nenhum;
import net.helix.pvp.kit.provider.Ninja;
import net.helix.pvp.kit.provider.Phantom;
import net.helix.pvp.kit.provider.Poseidon;
import net.helix.pvp.kit.provider.PvP;
import net.helix.pvp.kit.provider.QuickDropper;
import net.helix.pvp.kit.provider.Scout;
import net.helix.pvp.kit.provider.Snail;
import net.helix.pvp.kit.provider.Sonic;
import net.helix.pvp.kit.provider.Stomper;
import net.helix.pvp.kit.provider.Switcher;
import net.helix.pvp.kit.provider.Thor;
import net.helix.pvp.kit.provider.Thresh;
import net.helix.pvp.kit.provider.TimeLord;
import net.helix.pvp.kit.provider.Tornado;
import net.helix.pvp.kit.provider.Turtle;
import net.helix.pvp.kit.provider.Viper;

public enum HelixKit {


	NENHUM("None", 0, 0 , new Nenhum(), Material.ACACIA_FENCE, HelixPvP.getInstance().getConfig().getString("NenhumLore")),
	PVP("PvP", HelixPvP.getInstance().getConfig().getInt("PvPPrice"), 0, new PvP(), Material.STONE_SWORD, HelixPvP.getInstance().getConfig().getString("PvPLore")),
	KANGAROO("Kangaroo", HelixPvP.getInstance().getConfig().getInt("KangarooPrice"), 0,  new Kangaroo(), Material.FIREWORK, HelixPvP.getInstance().getConfig().getString("KangarooLore")),
	THOR("Thor", HelixPvP.getInstance().getConfig().getInt("ThorPrice"), 0, new Thor(), Material.GOLD_AXE, HelixPvP.getInstance().getConfig().getString("ThorLore")),
	ARCHER("Archer",HelixPvP.getInstance().getConfig().getInt("ArcherPrice"), 0, new Archer(), Material.BOW, HelixPvP.getInstance().getConfig().getString("ArcherLore")),
    NEO("Neo", HelixPvP.getInstance().getConfig().getInt("NeoPrice"), 0, new AntiStomper(), Material.BARRIER, HelixPvP.getInstance().getConfig().getString("NeoLore")),
    QUICKDROPPER("QuickDropper", HelixPvP.getInstance().getConfig().getInt("QuickDropperPrice"), 0, new QuickDropper(), Material.BOWL, HelixPvP.getInstance().getConfig().getString("QuickDropperLore")),
    ANTISTOMPER("AntiStomper", HelixPvP.getInstance().getConfig().getInt("AntiStomperPrice"), 0, new AntiStomperReal(), Material.DIAMOND_HELMET, HelixPvP.getInstance().getConfig().getString("AntiStomperLore")),
    BARBARIAN("Barbarian", 18000, 0, new Barbarian(), Material.WOOD_SWORD, "Sua espada aumenta a cada kill!"),
    VACUUM("Vacuum", 0, 10000, new EnderMage(), Material.ENDER_PORTAL_FRAME, "Arraste seus inimigos!"),
    BLOODGUN("BloodGun", 10000, 0, new BloodGun(), Material.WOOD_HOE, "Atire tiros de sangue!"),
   
	FISHERMAN("Fisherman", HelixPvP.getInstance().getConfig().getInt("FishermanPrice"), 150, new Fisherman(), Material.FISHING_ROD, HelixPvP.getInstance().getConfig().getString("FishermanLore")),
	ANCHOR("Anchor", HelixPvP.getInstance().getConfig().getInt("AnchorPrice"), 250, new Anchor(), Material.ANVIL, HelixPvP.getInstance().getConfig().getString("AnchorLore")),
	TORNADO("Tornado", HelixPvP.getInstance().getConfig().getInt("TornadoPrice"), 1500, new Tornado(), Material.HOPPER, HelixPvP.getInstance().getConfig().getString("TornadoLore")),
	VIPER("Viper", HelixPvP.getInstance().getConfig().getInt("ViperPrice"), 150, new Viper(), Material.SPIDER_EYE, HelixPvP.getInstance().getConfig().getString("ViperLore")),
	SNAIL("Snail", HelixPvP.getInstance().getConfig().getInt("SnailPrice"), 150, new Snail(), Material.SOUL_SAND, HelixPvP.getInstance().getConfig().getString("SnailLore")),
	POSEIDON("Poseidon", HelixPvP.getInstance().getConfig().getInt("PoseidonPrice"), 150, new Poseidon(), Material.WATER_BUCKET, HelixPvP.getInstance().getConfig().getString("PoseidonLore")),
	FIREMAN("Fireman", HelixPvP.getInstance().getConfig().getInt("FiremanPrice"), 150, new Fireman(), Material.LAVA_BUCKET, HelixPvP.getInstance().getConfig().getString("FiremanLore")),
	NINJA("Ninja", HelixPvP.getInstance().getConfig().getInt("NinjaPrice"), 200, new Ninja(), Material.EMERALD, HelixPvP.getInstance().getConfig().getString("NinjaLore")),
	GRAPPLER("Grappler", HelixPvP.getInstance().getConfig().getInt("GrapplerPrice"), 200, new Grappler(), Material.LEASH, HelixPvP.getInstance().getConfig().getString("GrapplerLore")),
	TIMELORD("Timelord", HelixPvP.getInstance().getConfig().getInt("TimeLordPrice"), 200, new TimeLord(), Material.WATCH, HelixPvP.getInstance().getConfig().getString("TimelordLore")),
	MONK("Monk", HelixPvP.getInstance().getConfig().getInt("MonkPrice"), 200, new Monk(), Material.BLAZE_ROD, HelixPvP.getInstance().getConfig().getString("MonkLore")),
	STOMPER("Stomper", HelixPvP.getInstance().getConfig().getInt("StomperPrice"), 200, new Stomper(), Material.IRON_BOOTS, HelixPvP.getInstance().getConfig().getString("StomperLore")),
	LEECH("Leech", HelixPvP.getInstance().getConfig().getInt("LeechPrice"), 200, new Leech(), Material.REDSTONE_BLOCK, HelixPvP.getInstance().getConfig().getString("LeechLore")),
	CAMEL("Camel", HelixPvP.getInstance().getConfig().getInt("CamelPrice"), 200, new Camel(), Material.SAND, HelixPvP.getInstance().getConfig().getString("CamelLore")),
	GRANDPA("Grandpa", HelixPvP.getInstance().getConfig().getInt("GrandpaPrice"), 150, new Grandpa(), Material.STICK, HelixPvP.getInstance().getConfig().getString("GrandpaLore")),
	SCOUT("Scout", HelixPvP.getInstance().getConfig().getInt("ScoutPrice"), 150, new Scout(), Material.POTION, HelixPvP.getInstance().getConfig().getString("ScoutLore")),
	HOTPOTATO("HotPotato", 17650, 150, new HotPotato(), Material.BAKED_POTATO, "Exploda o inimigo com sua batata"),
	THRESH("Thresh", 15000, 150, new Thresh(), Material.LEVER, "Atire uma flecha poderosa"),
	CRITICAL("Critical", HelixPvP.getInstance().getConfig().getInt("CriticalPrice"), 150, new Critical(), Material.REDSTONE_BLOCK, HelixPvP.getInstance().getConfig().getString("CriticalLore")),
	MILKMAN("Milkman", HelixPvP.getInstance().getConfig().getInt("MilkmanPrice"), 250, new Milkman(), Material.MILK_BUCKET, HelixPvP.getInstance().getConfig().getString("MilkmanLore")),
	TURTLE("Turtle", HelixPvP.getInstance().getConfig().getInt("TurtlePrice"), 200, new Turtle(), Material.DIAMOND_CHESTPLATE, HelixPvP.getInstance().getConfig().getString("TurtleLore")),
	PHANTOM("Phantom", HelixPvP.getInstance().getConfig().getInt("PhantomPrice"), 520, new Phantom(), Material.FEATHER, HelixPvP.getInstance().getConfig().getString("PhantomLore")),
	BOXER("Boxer", HelixPvP.getInstance().getConfig().getInt("BoxerPrice"), 1500, new Boxer(), Material.QUARTZ, HelixPvP.getInstance().getConfig().getString("BoxerLore")),
	HULK("Hulk", HelixPvP.getInstance().getConfig().getInt("HulkPrice"), 420, new Hulk(), Material.DROPPER, HelixPvP.getInstance().getConfig().getString("HulkLore")),
	SWITCHER("Switcher", HelixPvP.getInstance().getConfig().getInt("SwitcherPrice"), 0, new Switcher(), Material.SNOW_BALL, HelixPvP.getInstance().getConfig().getString("SwitcherLore")),
	JUMPER("Jumper", HelixPvP.getInstance().getConfig().getInt("JumperPrice"), 250, new Jumper(), Material.EYE_OF_ENDER, HelixPvP.getInstance().getConfig().getString("JumperLore")),
	FLASH("Flash", 15000, 500, new Flash(), Material.REDSTONE_TORCH_ON, "Teleporte para onde você clicar"),
    GLADIATOR("Gladiator", 15000, 500, new GladiatorListener(), Material.IRON_FENCE, "Faça 1v1 nos inimigos"),
	SONIC("Sonic", 15000, 500, new Sonic(), Material.LAPIS_BLOCK, "De um desh e coloque veneno");
	
	private final String name;
	private final String description;
	private final int price;
	private final int pricecash;
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
	
	HelixKit(String name, int price, int pricecash, KitHandler handler, Material icon, String description) {
		this.name = name;
		this.price = price;
		this.pricecash = pricecash;
		this.handler = handler;
		this.icon = icon;
		this.description = description;
	}
	
	public void send(Player player) {
		if (KitManager2.getPlayer(player.getName()).getkit2().getName() == KitManager.getPlayer(player.getName()).getKit().getName()) {
			player.sendMessage("§cVocê já tem esse kit escolhido como secundário!");
			return;
		}
		player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
		KitManager.getPlayer(player.getName()).setKit(this);
		KitManager2.getPlayer(player.getName()).removekit2();
		player.sendMessage("§b" + name + " escolhido como primário!");
		DarKit.sendTitle(player, "§a§lKIT", "§fVocê selecionou " + KitManager.getPlayer(player.getName()).getKit());		
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
	public int getPriceCash() {
		return pricecash;
	}
	
	public Material getIcon() {
		return icon;
	}
	public String getDescription() {
		return description.replace("&", "§");
	}
}

package net.helix.pvp.kit;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.command.DarKit;
import net.helixpvp.kit2.Anchor;
import net.helixpvp.kit2.AntiStomperReal;
import net.helixpvp.kit2.Archer;
import net.helixpvp.kit2.Boxer;
import net.helixpvp.kit2.Camel;
import net.helixpvp.kit2.Critical;
import net.helixpvp.kit2.EnderMage;
import net.helixpvp.kit2.Firebender;
import net.helixpvp.kit2.Fireman;
import net.helixpvp.kit2.Barbarian;
import net.helixpvp.kit2.Fisherman;
import net.helixpvp.kit2.GladiatorListener;
import net.helixpvp.kit2.Grappler;
import net.helixpvp.kit2.Kangaroo;
import net.helixpvp.kit2.Leech;
import net.helixpvp.kit2.Meteor;
import net.helixpvp.kit2.Sight;
import net.helixpvp.kit2.MilkMan;
import net.helixpvp.kit2.Gaara;
import net.helixpvp.kit2.Monk;
import net.helixpvp.kit2.Nenhum;
import net.helixpvp.kit2.Ninja;
import net.helixpvp.kit2.PvP;
import net.helixpvp.kit2.QuickDropper;
import net.helixpvp.kit2.Scout;
import net.helixpvp.kit2.Stomper;
import net.helixpvp.kit2.Switcher;
import net.helixpvp.kit2.Thor;
import net.helixpvp.kit2.Viper;
import net.helixpvp.kit2.WaterBender;


public enum HelixKit2  {
	NENHUM("Nenhum2", 0, 0 , new Nenhum() , new ItemStack(Material.BARRIER), "Sem habilidade" , 1),
	PVP("PvP", 0, 0 , new PvP() , new ItemStack(Material.STONE_SWORD), "Receba espada afiada 1" , 1),
	ANCHOR("Anchor", 10000, 0, new Anchor() , new ItemStack(Material.ANVIL), "Não de ou receba KB" , 1),
	ANTISTOMPER("AntiStomper", 8000, 0, new AntiStomperReal() , new ItemStack(Material.DIAMOND_HELMET), "Imune a stompers" , 1),
	NINJA("Ninja", 10000, 0, new Ninja() , new ItemStack(Material.EMERALD), "Teleporte-se ao inimigo", 1),
	LEECH("Leech", 10000, 0, new Leech() , new ItemStack(Material.REDSTONE), "Roube a vida dos seus inimigos", 1),
	ARCHER("Archer", 0, 0, new Archer() , new ItemStack(Material.BOW), "Ganhe arco e flecha!", 1),
	BOXER("Boxer", 15000, 0, new Boxer() , new ItemStack(Material.QUARTZ), "De mais dano e leve menos.", 1),
    VIPER("Viper", 10000, 0, new Viper() , new ItemStack(Material.SPIDER_EYE), "De veneno", 1),
    SIGHT("Sight", 10000, 0, new Sight(), new ItemStack(Material.INK_SACK, 1 , (short)1), "De cegueira nos inimigos!", 1),
    NEO("Neo", 8000, 0, new net.helixpvp.kit2.NEO() , new ItemStack(Material.BARRIER), "Seja imune a varios kits", 1),
	QUICKDROPPER("QuickDropper", 2500, 2500, new QuickDropper() , new ItemStack(Material.BOWL), "Drope potes automaticamente", 1),
	KANGAROO("Kangaroo", 0, 0, new Kangaroo() , new ItemStack(Material.FIREWORK), "De doublejumps", 1),
	VACUUM("Vacuum", 10000, 10000, new EnderMage() , new ItemStack(Material.ENDER_PORTAL_FRAME), "Arraste seus inimigos", 1),
	CRITICAL("Critical", 9000, 0, new Critical() , new ItemStack(Material.REDSTONE_BLOCK), "De criticos automaticamente", 1),
	STOMPER("Stomper", 15000, 0, new Stomper() , new ItemStack(Material.IRON_BOOTS), "Esmague seus inimigos.", 1),
	THOR("Thor", 2500, 2500, new Thor() , new ItemStack(Material.GOLD_AXE), "Lance raios.", 1),
	WATERBENDER("Waterbender", 12000, 0, new WaterBender(), new ItemStack(Material.LAPIS_ORE), "Coloque o inimigo em uma prisão de água", 1),
	FIREBENDER("Firebender", 12000, 0, new Firebender(), new ItemStack(Material.REDSTONE_ORE), "Coloque o inimigo em uma prisão de fogo", 1),
	GLADIATOR("Gladiator", 15000, 0, new GladiatorListener() , new ItemStack(Material.IRON_FENCE), "Puxe seu inimigo.", 1),
	CAMEL("Camel", 9000, 9000, new Camel() , new ItemStack(Material.SAND), "Ganhe poderes na areia.", 1),
	BARBARIAN("Barbarian", 18000, 0, new Barbarian(), new ItemStack(Material.WOOD_SWORD), "Sua espada aumenta a cada kill!", 1),
	SNAIL("Snail", 10000, 0, new Scout() , new ItemStack(Material.FERMENTED_SPIDER_EYE), "De lentidao a cada hit.", 1),
	FIREMAN("Fireman", 10000, 0, new Fireman() , new ItemStack(Material.LAVA_BUCKET), "De fogo a cada hit.", 1),
	MONK("Monk", 9000, 0, new Monk() , new ItemStack(Material.BLAZE_ROD), "Embaralhe o inventario do inimigo.", 1),
	FISHERMAN("Fisherman", 9000, 0, new Fisherman() , new ItemStack(Material.FISHING_ROD), "Pesque players.", 1),
	SWITCHER("Switcher", 8000, 0, new Switcher() , new ItemStack(Material.SNOW_BALL), "Troque de lugar com o inimigo.",  1),
	METEOR("Meteor", 8000, 0, new Meteor() , new ItemStack(Material.FIREBALL), "Seja um meteor.",  1),
	GRAPPLER("Grappler", 10000, 0, new Grappler() , new ItemStack(Material.LEASH), "Voe com sua corda", 1);
	
	private final String name;
	private final String description;
	private final int price;
	private final int pricecash;
	private final KitHandler2 handler2;
	private final ItemStack icon;
	private final int page;
	private static String nl = System.getProperty("line.separator");
	static {
		getKits().forEach(kit -> 
			Bukkit.getPluginManager().registerEvents(kit.getHandler(), HelixPvP.getInstance())
		);
	}
	
	public static List<HelixKit2> getKits() {
		return Arrays.asList(values());
	}
	
	public static Optional<HelixKit2> findKit(String name) {
		return getKits().stream().filter(
				kit -> kit.toString().equalsIgnoreCase(name) 
					|| kit.getName().equalsIgnoreCase(name)
		).findFirst();
	}
	
	HelixKit2(String name, int price, int pricecash, KitHandler2 handler2, ItemStack icon, String description , int page) {
		this.name = name;
		this.price = price;
		this.pricecash = pricecash;
		this.handler2 = handler2;
		this.icon = icon;
		this.description = description;
		this.page = page;
	}
	
	public void send(Player player) {
		KitManager2.getPlayer(player.getName()).setkit2(this);
		player.sendMessage("§b" + name + " selecionado!");
		
		}
	
	public KitHandler2 getHandler() {
		return handler2;
	}
	 public static HelixKit2 getKitTypeByName(String kitname) {
		 HelixKit2[] values;
	        for (int length = (values = values()).length, i = 0; i < length; ++i) {
	            final HelixKit2 kitType = values[i];
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
	public int getPage() {
		return page;
	}
	public int getPriceCash() {
		return pricecash;
	}
	
	public ItemStack getIcon() {
		return icon;
	}
	public String getDescription() {
		return description.replace("&", "§");
	}
}

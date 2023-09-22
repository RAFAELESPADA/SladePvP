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
import net.helix.pvp.kit.provider.EnderMageReal;
import net.helix.pvp.listener.PlayerJoinListener;
import net.helixpvp.kit2.Anchor;
import net.helixpvp.kit2.AntiStomperReal;
import net.helixpvp.kit2.Archer;
import net.helixpvp.kit2.Boxer;
import net.helixpvp.kit2.Camel;
import net.helixpvp.kit2.Critical;
import net.helixpvp.kit2.Deshfire;
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
import net.helixpvp.kit2.Snail;
import net.helixpvp.kit2.MilkMan;
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
	NENHUM("None2", 0, 0 , new Nenhum() , new ItemStack(Material.BARRIER), "No habilities" , 1),
	PVP("PvP-2", 0, 0 , new PvP() , new ItemStack(Material.STONE_SWORD), "Has sharp 1 in sword" , 1),
	ANCHOR("Anchor", 10000, 0, new Anchor() , new ItemStack(Material.ANVIL), "Dont receive knockback" , 1),
	ANTISTOMPER("AntiStomper", 8000, 0, new AntiStomperReal() , new ItemStack(Material.DIAMOND_HELMET), "Immune to stompers" , 1),
	NINJA("Ninja", 10000, 0, new Ninja() , new ItemStack(Material.EMERALD), "Teleport to enemies", 1),
	LEECH("Leech", 10000, 0, new Leech() , new ItemStack(Material.REDSTONE), "Steal life from enemies", 1),
	ARCHER("Archer", 0, 0, new Archer() , new ItemStack(Material.BOW), "Receives bow and arrow!", 1),
	BOXER("Boxer", 15000, 0, new Boxer() , new ItemStack(Material.QUARTZ), "Gives more damage.", 1),
    VIPER("Viper", 10000, 0, new Viper() , new ItemStack(Material.SPIDER_EYE), "Gives poison", 1),
    SIGHT("Sight", 10000, 0, new Sight(), new ItemStack(Material.INK_SACK, 1 , (short)1), "Gives blidness!", 1),
    NEO("Neo", 8000, 0, new net.helixpvp.kit2.NEO() , new ItemStack(Material.BARRIER), "Immune to various kits", 1),
	QUICKDROPPER("QuickDropper", 2500, 2500, new QuickDropper() , new ItemStack(Material.BOWL), "Drop bowls automatically", 1),
	DESHFIRE("Deshfire", 2500, 2500, new Deshfire() , new ItemStack(Material.REDSTONE_BLOCK), "Give a desh and put fire", 1),
	KANGAROO("Kangaroo", 0, 0, new Kangaroo() , new ItemStack(Material.FIREWORK), "Do doublejumps", 1),
	VACUUM("Vacuum", 10000, 10000, new EnderMage() , new ItemStack(Material.ENDER_PORTAL_FRAME), "Create a vacuum", 1),
	CRITICAL("Critical", 9000, 0, new Critical() , new ItemStack(Material.REDSTONE_BLOCK), "Gives critical hits", 1),
	STOMPER("Stomper", 15000, 0, new Stomper() , new ItemStack(Material.IRON_BOOTS), "Smash your enemies.", 1),
	THOR("Thor", 2500, 2500, new Thor() , new ItemStack(Material.GOLD_AXE), "Throw thunders.", 1),
	WATERBENDER("Waterbender", 12000, 0, new WaterBender(), new ItemStack(Material.LAPIS_ORE), "Create a water prison", 1),
	FIREBENDER("Firebender", 12000, 0, new Firebender(), new ItemStack(Material.REDSTONE_ORE), "Create a fire prison", 1),
	GLADIATOR("Gladiator", 15000, 0, new GladiatorListener() , new ItemStack(Material.IRON_FENCE), "Challenge your enemy.", 1),
	CAMEL("Camel", 9000, 9000, new Camel() , new ItemStack(Material.SAND), "Get stronger on deserts.", 1),
	BARBARIAN("Barbarian", 18000, 0, new Barbarian(), new ItemStack(Material.WOOD_SWORD), "Your sword envolves on each kill!", 1),
	SNAIL("Snail", 10000, 0, new Snail() , new ItemStack(Material.FERMENTED_SPIDER_EYE), "Gives slowness on each hit.", 1),
	FIREMAN("Fireman", 10000, 0, new Fireman() , new ItemStack(Material.LAVA_BUCKET), "Gives fire on each hit.", 1),
	MONK("Monk", 9000, 0, new Monk() , new ItemStack(Material.BLAZE_ROD), "Messes with enemy inventory.", 1),
	FISHERMAN("Fisherman", 9000, 0, new Fisherman() , new ItemStack(Material.FISHING_ROD), "Pulls players to you wih fishing rod.", 1),
	SWITCHER("Switcher", 8000, 0, new Switcher() , new ItemStack(Material.SNOW_BALL), "Swith places with enemies.",  1),
	METEOR("Meteor", 8000, 0, new Meteor() , new ItemStack(Material.FIREBALL), "Be a meteor.",  1),
	GRAPPLER("Grappler", 10000, 0, new Grappler() , new ItemStack(Material.LEASH), "Fly around", 1);
	
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
		if (!(player.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura") && PlayerJoinListener.fall.contains(player)  && EnderMageReal.isSpawn(player.getLocation()))) {
	      	player.closeInventory();
	  		return;
	  	 }
		if (KitManager2.getPlayer(player.getName()).getkit2().getName() == KitManager.getPlayer(player.getName()).getKit().getName()) {
			player.sendMessage("§cYou alreadu has this kit selected was primary!");
			player.closeInventory();
			return;
		}
		if (KitManager2.getPlayer(player.getName()).getkit2().toString() == KitManager.getPlayer(player.getName()).getKit().toString()) {
			if (KitManager2.getPlayer(player.getName()).getkit2() == HelixKit2.NENHUM && KitManager.getPlayer(player.getName()).getKit() == HelixKit.NENHUM) {
				KitManager2.getPlayer(player.getName()).setkit2(this);
				player.sendMessage("§b" + name + " selected!");
				return;
			}
			if (KitManager2.getPlayer(player.getName()).getkit2() == HelixKit2.PVP && KitManager.getPlayer(player.getName()).getKit() == HelixKit.PVP) {
				KitManager2.getPlayer(player.getName()).setkit2(this);
				player.sendMessage("§b" + name + " selected!");
				return;
			}
			player.sendMessage("§cYou alreadu has this kit selected was primary!");
			player.playSound(player.getLocation(), Sound.GHAST_MOAN, 1f, 1f);
			player.closeInventory();
			return;
		}
		KitManager2.getPlayer(player.getName()).setkit2(this);
		if (KitManager2.getPlayer(player.getName()).haskit2(PVP)) {
		
		player.sendMessage("§bPvP selected!");
		} else {
			player.sendMessage("§b" + name + " selected!");
		
		}
		
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

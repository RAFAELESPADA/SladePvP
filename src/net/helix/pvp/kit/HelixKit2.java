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
	NENHUM("None2", 0, 0 , new Nenhum() , new ItemStack(Material.ACACIA_FENCE), "Sem habilidade"),
	PVP("PvP", 0, 0 , new PvP() , new ItemStack(Material.STONE_SWORD), "Receba espada afiada 1"),
	ANCHOR("Anchor", 10000, 0, new Anchor() , new ItemStack(Material.ANVIL), "Não de ou receba KB"),
	ANTISTOMPER("AntiStomper", 8000, 0, new AntiStomperReal() , new ItemStack(Material.DIAMOND_HELMET), "Imune a stompers"),
	NINJA("Ninja", 10000, 0, new Ninja() , new ItemStack(Material.EMERALD), "Teleporte-se ao inimigo"),
	LEECH("Leech", 10000, 0, new Leech() , new ItemStack(Material.REDSTONE), "Roube a vida dos seus inimigos"),
	BOXER("Boxer", 15000, 0, new Boxer() , new ItemStack(Material.QUARTZ), "De mais dano e leve menos."),
    VIPER("Viper", 10000, 0, new Viper() , new ItemStack(Material.SPIDER_EYE), "De veneno"),
    SIGHT("Sight", 10000, 0, new Sight(), new ItemStack(Material.INK_SACK, 1 , (short)1), "De cegueira nos inimigos!"),
    NEO("Neo", 8000, 0, new net.helixpvp.kit2.NEO() , new ItemStack(Material.BARRIER), "Seja imune a varios kits"),
	QUICKDROPPER("QuickDropper", 2500, 2500, new QuickDropper() , new ItemStack(Material.BOWL), "Drope potes automaticamente"),
	KANGAROO("Kangaroo", 0, 0, new Kangaroo() , new ItemStack(Material.FIREWORK), "De doublejumps"),
	VACUUM("Vacuum", 10000, 10000, new EnderMage() , new ItemStack(Material.ENDER_PORTAL_FRAME), "Arraste seus inimigos"),
	CRITICAL("Critical", 9000, 0, new Critical() , new ItemStack(Material.REDSTONE_BLOCK), "De criticos automaticamente"),
	STOMPER("Stomper", 15000, 0, new Stomper() , new ItemStack(Material.IRON_BOOTS), "Esmague seus inimigos."),
	THOR("Thor", 2500, 2500, new Thor() , new ItemStack(Material.GOLD_AXE), "Lance raios."),
	WATERBENDER("Waterbender", 12000, 0, new WaterBender(), new ItemStack(Material.LAPIS_ORE), "Coloque o inimigo em uma prisão de água"),
	FIREBENDER("Firebender", 12000, 0, new Firebender(), new ItemStack(Material.REDSTONE_ORE), "Coloque o inimigo em uma prisão de fogo"),
	GLADIATOR("Gladiator", 15000, 0, new GladiatorListener() , new ItemStack(Material.IRON_FENCE), "Puxe seu inimigo."),
	CAMEL("Camel", 9000, 9000, new Camel() , new ItemStack(Material.SAND), "Ganhe poderes na areia."),
	BARBARIAN("Barbarian", 18000, 0, new Barbarian(), new ItemStack(Material.WOOD_SWORD), "Sua espada aumenta a cada kill!"),
	SNAIL("Snail", 10000, 0, new Scout() , new ItemStack(Material.FERMENTED_SPIDER_EYE), "De lentidao a cada hit."),
	FIREMAN("Fireman", 10000, 0, new Fireman() , new ItemStack(Material.LAVA_BUCKET), "De fogo a cada hit."),
	MONK("Monk", 9000, 0, new Monk() , new ItemStack(Material.BLAZE_ROD), "Embaralhe o inventario do inimigo."),
	FISHERMAN("Fisherman", 9000, 0, new Fisherman() , new ItemStack(Material.FISHING_ROD), "Pesque players."),
	SWITCHER("Switcher", 8000, 0, new Switcher() , new ItemStack(Material.SNOW_BALL), "Troque de lugar com o inimigo."),
	GRAPPLER("Grappler", 10000, 0, new Grappler() , new ItemStack(Material.LEASH), "Voe com sua corda");
	
	private final String name;
	private final String description;
	private final int price;
	private final int pricecash;
	private final KitHandler2 handler2;
	private final ItemStack icon;
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
	
	HelixKit2(String name, int price, int pricecash, KitHandler2 handler2, ItemStack icon, String description) {
		this.name = name;
		this.price = price;
		this.pricecash = pricecash;
		this.handler2 = handler2;
		this.icon = icon;
		this.description = description;
	}
	
	public void send(Player player) {
		if (!KitManager.getPlayer(player.getName()).hasKit()) {
			player.sendMessage("§cEscolha primeiro o kit primário!");
			return;
		}
		handler2.execute(player);
		if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.SONIC)) {
			player.getInventory().setItem(1, new ItemBuilder("§aSonic!", Material.LAPIS_BLOCK)
					.nbt("kit-handler", "sonic")
					.nbt("cancel-drop")
					.toStack()
			);
			Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed sonic kit!");
		}
		if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.VACUUM)) {
			player.getInventory().setItem(1, new ItemBuilder("§aVacuum!", Material.ENDER_PORTAL_FRAME)
					.nbt("kit-handler", "vacuum")
					.nbt("cancel-drop")
					.toStack()
			);
			Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed VACUUM kit!");
		}
		if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.FIREBENDER)) {
			player.getInventory().setItem(1, new ItemBuilder("§cFireBender!", Material.REDSTONE_BLOCK)
	                .addEnchant(Enchantment.KNOCKBACK, 1)
	        				.addFlags(ItemFlag.HIDE_ENCHANTS)
	                .nbt("cancel-drop")
	                .toStack()
	        );
			Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed FIREBENDER kit!");
		}
		if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.WATERBENDER)) {
			player.getInventory().setItem(1, new ItemBuilder("§bWaterBender!", Material.LAPIS_BLOCK)
	                .addEnchant(Enchantment.KNOCKBACK, 1)
	        				.addFlags(ItemFlag.HIDE_ENCHANTS)
	                .nbt("cancel-drop")
	                .toStack()
	        );
			Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed WATERBENDER kit!");
		}
		if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.ENDERMAGE)) {
			player.getInventory().setItem(1, new ItemBuilder("§bEndermage!", Material.ENDER_STONE)
	                .addEnchant(Enchantment.KNOCKBACK, 1)
	        				.addFlags(ItemFlag.HIDE_ENCHANTS)
	                .nbt("cancel-drop")
	                .toStack()
	        );
			Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed ENDERMAGE kit!");
		}
		if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.DESHFIRE)) {
			player.getInventory().setItem(1, new ItemBuilder("§4Desh Fire!", Material.REDSTONE_BLOCK)
	                .addEnchant(Enchantment.KNOCKBACK, 1)
	        				.addFlags(ItemFlag.HIDE_ENCHANTS)
	                .nbt("cancel-drop")
	                .toStack()
	        );
			Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed ENDERMAGE kit!");
		}
		if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.AVATAR)) {
			player.getInventory().setItem(1, new ItemBuilder("§bAvatar!", Material.BEACON)
	                .addEnchant(Enchantment.KNOCKBACK, 1)
	        				.addFlags(ItemFlag.HIDE_ENCHANTS)
	                .nbt("cancel-drop")
	                .toStack()
	        );
			Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed Avatar kit!");
		}
		if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.ARCHER)) {
			player.getInventory().setItem(1, new ItemBuilder("§aBow!", Material.BOW)
					.nbt("kit-handler", "arco").addEnchant(Enchantment.ARROW_INFINITE, 1)
					.nbt("cancel-drop")
					.toStack());
					player.getInventory().setItem(10, new ItemBuilder("§aArrow!", Material.ARROW)
							.nbt("kit-handler", "flecha").addEnchant(Enchantment.DAMAGE_ALL, 1).addEnchant(Enchantment.ARROW_DAMAGE, 1)
							.nbt("cancel-drop").addFlags(
									ItemFlag.HIDE_ENCHANTS)
							.toStack()
			);
					Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed archer kit!");
		}
		//if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.GAARA)) {
			//player.getInventory().setItem(1, new ItemBuilder("§aCaixão de areia!", Material.SAND)
				//	.nbt("kit-handler", "gaara")
					//.nbt("cancel-drop")
					//.toStack()
		//	);
			//		Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed gaara kit!");
	//	}
		if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.BLOODGUN)) {
			player.getInventory().setItem(1, new ItemBuilder("§aArma de sangue", Material.WOOD_HOE)
					.nbt("kit-handler", "bloodgun")
					.nbt("cancel-drop")
					.toStack());
				
					Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed archer kit!");
		}
					if (KitManager.getPlayer(player.getName()).hasKit( HelixKit.BOXER)) {
						Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed boxer kit!");
					}
					if (KitManager.getPlayer(player.getName()).hasKit( HelixKit.HOTPOTATO)) {
						player.getInventory().setItem(1, new ItemBuilder("§4HotPotato!", Material.BAKED_POTATO)
								.nbt("kit-handler", "hotpotato")
								.nbt("cancel-drop")
								.toStack()
						);
						Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed hotpotato kit!");
					}
					if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.THRESH)) {
						player.getInventory().setItem(1, new ItemBuilder("§2Thresh!", Material.LEVER)
								.nbt("kit-handler", "thresh")
								.nbt("cancel-drop")
								.toStack()
						);
						Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed thresh kit!");
					}
						if (KitManager.getPlayer(player.getName()).hasKit( HelixKit.FISHERMAN)) {
						player.getInventory().setItem(1, new ItemBuilder("§aFisgar!", Material.FISHING_ROD)
								.nbt("kit-handler", "fisherman")
								.nbt("cancel-drop")
								.toStack()
						);
						Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed fisherman kit!");
						}
						if (KitManager.getPlayer(player.getName()).hasKit( HelixKit.TIMELORD)) {
							 player.getInventory().setItem(1, new ItemBuilder("§bStop the time!", Material.WATCH)
						                .addEnchant(Enchantment.KNOCKBACK, 1)
						        				.addFlags(ItemFlag.HIDE_ENCHANTS)
						                .nbt("cancel-drop")
						                .toStack()
						        );
							Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed TIMELORD kit!");
							}
						
						if (KitManager.getPlayer(player.getName()).hasKit( HelixKit.PHANTOM)) {
							 player.getInventory().setItem(1, new ItemBuilder(Material.BOOK)
						                .displayName("§aPhantom")
						                .nbt("cancel-drop")
						                .nbt("kit-handler", "phantom")
						                .toStack()
						        );
							Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed PHANTOM kit!");
							}
						if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.FLASH)) {
							 player.getInventory().setItem(1, new ItemBuilder("§aFlash!", Material.REDSTONE_TORCH_ON)
						                .addEnchant(Enchantment.KNOCKBACK, 2).addFlags(ItemFlag.HIDE_ENCHANTS)
						                .nbt("cancel-drop").nbt("kit-handler", "flash")
						                .toStack());
								Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed flash kit!");
							}
						if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.GRANDPA)) {
						player.getInventory().setItem(1, new ItemBuilder("§aGrandpa!", Material.STICK)
				                .addEnchant(Enchantment.KNOCKBACK, 2)
				                .nbt("cancel-drop")
				                .toStack()
				        );
						Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed grandpa kit!");
						}
						if (KitManager.getPlayer(player.getName()).hasKit( HelixKit.GRAPPLER)) {
							player.getInventory().setItem(1, new ItemBuilder("§aLaço!", Material.LEASH)
					                .addEnchant(Enchantment.KNOCKBACK, 1)
					        				.addFlags(ItemFlag.HIDE_ENCHANTS)
					                .nbt("cancel-drop")
					                .toStack()
					        );
							Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed grappler kit!");
							}
						if (KitManager.getPlayer(player.getName()).hasKit( HelixKit.GLADIATOR)) {
							player.getInventory().setItem(1, new ItemBuilder("§bPuxar!", Material.IRON_FENCE)
									.nbt("kit-handler", "glad")
									.nbt("cancel-drop")
									.toStack()
							);
							Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed gladiator kit!");
							}
						if (KitManager.getPlayer(player.getName()).hasKit( HelixKit.JUMPER)) {
							player.getInventory().setItem(1, new ItemBuilder(Material.EYE_OF_ENDER)
					                .displayName("§aJumper")
					                .nbt("cancel-drop")
					                .nbt("kit-handler", "jumper")
					                .toStack()
					        );
							}
						if (KitManager.getPlayer(player.getName()).hasKit( HelixKit.KANGAROO)) {
							player.getInventory().setItem(1, new ItemBuilder("§aPular!", Material.FIREWORK)
									.nbt("kit-handler", "kangaroo")
									.nbt("cancel-drop")
									.toStack()
							);
							Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed kangaroo kit!");
							}
						if (KitManager.getPlayer(player.getName()).hasKit( HelixKit.MILKMAN)) {
							player.getInventory().setItem(1, new ItemBuilder(Material.MILK_BUCKET)
					                .displayName("§fMilk Bucket")
					                .nbt("cancel-drop")
					                .nbt("kit-handler", "milkman")
					                .toStack()
					        );
							Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed milkman kit!");
							}
						if (KitManager.getPlayer(player.getName()).hasKit( HelixKit.MONK)) {
						player.getInventory().setItem(1, new ItemBuilder("§eEmbaralhar!", Material.BLAZE_ROD)
								.nbt("kit-handler", "monk")
								.nbt("cancel-drop")
								.toStack()
						);
						Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed monk kit!");
						}
						if (KitManager.getPlayer(player.getName()).hasKit( HelixKit.PVP)) {
						player.getInventory().setItem(0, new ItemBuilder("§fSword", Material.STONE_SWORD)
								.addEnchant(Enchantment.DAMAGE_ALL, 1)
								.nbt("cancel-drop")
								.toStack()
						);
						Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed pvp kit!");
						}
						if (KitManager.getPlayer(player.getName()).hasKit( HelixKit.SCOUT)) {
							player.getInventory().setItem(1, new ItemBuilder(Material.POTION, 8226)
					                .amount(1)
					                .nbt("cancel-drop").nbt("kit-handler", "scout")
					                .toStack()
					        );
							Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed scout kit!");
							}
						if (KitManager.getPlayer(player.getName()).hasKit( HelixKit.SWITCHER)) {
							player.getInventory().addItem(new ItemBuilder(Material.SNOW_BALL)
				                     .amount(2)
				                     .nbt("cancel-drop").nbt("kit-handler", "switcher")
				                     .toStack());
							Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed switcher kit!");
						}
						if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.THOR)) {
						player.getInventory().setItem(1, new ItemBuilder("§eCaboom!", Material.GOLD_AXE)
								.nbt("cancel-drop")
								.nbt("kit-handler", "thor")
								.toStack()
						);
						Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed thor kit!");
						}
						if (KitManager.getPlayer(player.getName()).hasKit( HelixKit.TORNADO)) {
							player.getInventory().setItem(1, new ItemBuilder(Material.HOPPER)
					                .displayName("§cTornado")
					                .nbt("cancel-drop")
					                .nbt("kit-handler", "tornado")
					                .toStack()
					        );
							Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed tornado kit!");
							}
					
		
		player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
		KitManager2.getPlayer(player.getName()).setkit2(this);
		player.sendMessage("§b" + name + " escolhido como secundário!");
		DarKit.sendTitle(player, "§a§lKIT", "§fVocê escolheu " + KitManager2.getPlayer(player.getName()).getkit2());
		
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

package net.helix.pvp.listener;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;
import org.bukkit.util.Vector;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.evento.EventoUtils;
import net.helix.pvp.evento.SoupTypeGUI;
import net.helix.pvp.kit.Habilidade;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.HelixKit2;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitHandler2;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.helix.pvp.warp.HelixWarp;





public class Jump implements Listener {
	public static HashMap<String, Boolean> recebeu = new HashMap();
	public static HashMap<String, Boolean> caiu = new HashMap();
	private ArrayList<String> fall = new ArrayList<String>();
	public KitHandler obj = new KitHandler();
	public KitHandler2 obj2 = new KitHandler2();
	public void Atirar(Player p) {
		int y = 8;
		Block block = p.getLocation().getBlock().getRelative(0, -1, 0);
		if (block.getType() == Material.SPONGE) {
			Vector vector = new Vector(0, y, 0);
			p.setVelocity(vector);
			this.fall.remove(p.getName());
			 p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 4.0F, 4.0F);
			p.getPlayer().getWorld().playEffect(p.getPlayer().getLocation(), Effect.MOBSPAWNER_FLAMES, 10);
			this.fall.add(p.getName());
		}
	}
	public void Atirar2(Player p) {
		final Location loc = p.getEyeLocation();
    
        final Vector sponge = p.getLocation().getDirection().multiply(3.8).setY(0.45);
		Block block = p.getLocation().getBlock().getRelative(0, -1, 0);
		if (block.getType() == Material.SLIME_BLOCK) {
			p.setVelocity(sponge);
		    p.playEffect(loc, Effect.MOBSPAWNER_FLAMES, (Object)null);
			this.fall.remove(p.getName());
			 p.playSound(p.getLocation(), Sound.SLIME_WALK, 4.0F, 4.0F);
			this.fall.add(p.getName());
		}
	}
	@EventHandler
	private void Jumps(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Player player = e.getPlayer();
		Atirar(p);
		Atirar2(p);	
		if (player.getLocation().getBlockY() < HelixPvP.getInstance().getConfig().getInt("SpawnAltura") && (!KitManager.getPlayer(player.getName()).hasKit() && !KitManager2.getPlayer(player.getName()).haskit2()) && !EventoUtils.tp && !HelixWarp.ONE_VS_ONE.hasPlayer(p.getName()) && !HelixWarp.FPS.hasPlayer(p.getName()) && !HelixWarp.SUMO.hasPlayer(p.getName()) && !HelixWarp.LAVACHALLENGE.hasPlayer(p.getName()) && !recebeu.containsKey(player.getName())) {
			HelixKit.findKit("PvP").ifPresent(kit -> {
				player.closeInventory();
				kit.send(player);
			});
			HelixKit2.findKit("PvP").ifPresent(kit -> {
				player.closeInventory();
				kit.send(player);
			});
		Items(player);	
Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed pvp kit! (Pulou do spawn)");
						recebeu.put(player.getName(), true);
						}			
						else if (player.getLocation().getBlockY() < HelixPvP.getInstance().getConfig().getInt("SpawnAltura") && (KitManager.getPlayer(player.getName()).hasKit() && !KitManager2.getPlayer(player.getName()).haskit2()) && !EventoUtils.tp && !HelixWarp.ONE_VS_ONE.hasPlayer(p.getName()) && !HelixWarp.FPS.hasPlayer(p.getName()) && !HelixWarp.SUMO.hasPlayer(p.getName()) && !HelixWarp.LAVACHALLENGE.hasPlayer(p.getName())  && !recebeu.containsKey(player.getName())) {
							HelixKit2.findKit("PvP").ifPresent(kit -> {
								player.closeInventory();
								kit.send(player);
							});
	Items(player);
	recebeu.put(player.getName(), true);					
}
		
						else if (player.getLocation().getBlockY() < HelixPvP.getInstance().getConfig().getInt("SpawnAltura") && (!KitManager.getPlayer(player.getName()).hasKit() && KitManager2.getPlayer(player.getName()).haskit2()) && !EventoUtils.tp && !HelixWarp.ONE_VS_ONE.hasPlayer(p.getName()) && !HelixWarp.FPS.hasPlayer(p.getName()) && !HelixWarp.SUMO.hasPlayer(p.getName()) && !HelixWarp.LAVACHALLENGE.hasPlayer(p.getName())  && !recebeu.containsKey(player.getName())) {
							
							HelixKit.findKit("PvP").ifPresent(kit -> {
								player.closeInventory();
								kit.send(player);
							});	
							Items(player);
							
							
							
							recebeu.put(player.getName(), true);
										Bukkit.getConsoleSender().sendMessage(player.getName() + " Escolheu apenas o kit secundário " + KitManager2.getPlayer(player.getName()).getkit2().getName());
						}
						else if (player.getLocation().getBlockY() < HelixPvP.getInstance().getConfig().getInt("SpawnAltura") && (KitManager.getPlayer(player.getName()).hasKit() && KitManager2.getPlayer(player.getName()).haskit2()) && !EventoUtils.tp && !HelixWarp.ONE_VS_ONE.hasPlayer(p.getName()) && !HelixWarp.FPS.hasPlayer(p.getName()) && !HelixWarp.SUMO.hasPlayer(p.getName()) && !HelixWarp.LAVACHALLENGE.hasPlayer(p.getName())  && !recebeu.containsKey(player.getName())) {
		                Items(player);
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.JUMPER) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.GLADIATOR)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.NEO) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.STOMPER)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.STOMPER) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.NEO)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.TORNADO) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.GRAPPLER)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.STOMPER) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.KANGAROO)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.GRAPPLER) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.STOMPER)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.STOMPER) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.GRAPPLER)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }

		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.ARCHER) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.KANGAROO)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.JUMPER) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.STOMPER)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.KANGAROO) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.ARCHER)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.KANGAROO) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.STOMPER)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.STOMPER) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.NINJA)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.NINJA) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.STOMPER)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.STOMPER) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.METEOR)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.METEOR) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.STOMPER)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.HULK) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.GLADIATOR)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.ANTISTOMPER) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.STOMPER)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.STOMPER) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.ANTISTOMPER)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.PHANTOM) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.STOMPER)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.PHANTOM) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.STOMPER)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.TORNADO) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.GRAPPLER)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.SONIC) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.STOMPER)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.FLASH) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.GRAPPLER)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.FLASH) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.STOMPER)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.TORNADO) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.MONK)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.TORNADO) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.NINJA)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.FLASH) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.KANGAROO)) {
		                	HelixWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você estava com uma combinação proibida e foi mandado de volta pro spawn");
		                	return;
		                }
		 					Bukkit.getConsoleSender().sendMessage(player.getName() + " escolheu o kit 1: " + KitManager.getPlayer(player.getName()).getKit().toString());
							Bukkit.getConsoleSender().sendMessage(player.getName() + " escolheu o kit 2: " + KitManager2.getPlayer(player.getName()).getkit2().toString());						
							recebeu.put(player.getName(), true);
		}
	}
	
	
	

public void Items(Player player) {
	player.setGameMode(GameMode.SURVIVAL);
	player.getInventory().clear();
	player.getInventory().setArmorContents(null);
	player.setAllowFlight(false);
	player.setFlying(false);
	player.getInventory().setItem(1 , new ItemStack(Material.MUSHROOM_SOUP));
	player.getInventory().setItem(2 , new ItemStack(Material.MUSHROOM_SOUP));
	player.getActivePotionEffects().forEach(potion -> player.removePotionEffect(potion.getType()));
	player.getInventory().setHeldItemSlot(0);
	/* 348 */  
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
	if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.METEOR)) {
		player.getInventory().setItem(1, new ItemBuilder("§cMeteoro", Material.FIREBALL)
                .addEnchant(Enchantment.KNOCKBACK, 1)
        				.addFlags(ItemFlag.HIDE_ENCHANTS)
                .nbt("cancel-drop")
                .toStack()
        );
		Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed METEOR kit!");
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
				.nbt("kit-handler", "arco").addEnchant(Enchantment.ARROW_INFINITE, 1).addEnchant(Enchantment.ARROW_DAMAGE, 1)
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
					
					if (KitManager.getPlayer(player.getName()).hasKit( HelixKit.SCOUT)) {
						player.getInventory().setItem(1, new ItemBuilder(Material.POTION, 8226)
				                .amount(1)
				                .nbt("cancel-drop").nbt("kit-handler", "scout")
				                .toStack()
				        );
						Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed scout kit!");
						}
					if (KitManager.getPlayer(player.getName()).hasKit( HelixKit.SWITCHER)) {
						player.getInventory().setItem(1, new ItemBuilder(Material.SNOW_BALL)
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
					}
						if (KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.ARCHER)) {
							player.getInventory().setItem(2, new ItemBuilder("§aArco!", Material.BOW)
									.nbt("kit-handler", "arco").addEnchant(Enchantment.ARROW_INFINITE, 1).addEnchant(Enchantment.ARROW_DAMAGE, 1)
									.nbt("cancel-drop")
									.toStack());
									player.getInventory().setItem(10, new ItemBuilder("§aFlecha!", Material.ARROW)
											.nbt("kit-handler", "flecha").addEnchant(Enchantment.DAMAGE_ALL, 1)
											.nbt("cancel-drop").addFlags(
													ItemFlag.HIDE_ENCHANTS)
											.toStack()
							);
						}
									if (KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.BARBARIAN)) {
										player.getInventory().setItem(0, new ItemBuilder("§fEspada", Material.WOOD_SWORD)
												.nbt("cancel-drop")
												.toStack());
										}
							
							if (KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.VACUUM)) {
							player.getInventory().setItem(2, new ItemBuilder("§aVacuum", Material.ENDER_PORTAL_FRAME)
					                .addEnchant(Enchantment.KNOCKBACK, 1)
					        				.addFlags(ItemFlag.HIDE_ENCHANTS)
					                .nbt("cancel-drop")
					                .toStack()
					        );
							}
							if (KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.FIREBENDER)) {
							 player.getInventory().setItem(2, new ItemBuilder("§cFireBender!", Material.REDSTONE_BLOCK)
						                .addEnchant(Enchantment.KNOCKBACK, 1)
						        				.addFlags(ItemFlag.HIDE_ENCHANTS)
						                .nbt("cancel-drop")
						                .toStack()
						        );
							}
							if (KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.FISHERMAN)) {
								player.getInventory().setItem(2, new ItemBuilder("§aFisgar!", Material.FISHING_ROD)
										.nbt("kit-handler", "fisherman")
										.nbt("cancel-drop")
										.toStack()
								);
							}
								if (KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.GLADIATOR)) {
									player.getInventory().setItem(2, new ItemBuilder("§bPuxar!", Material.IRON_FENCE)
											.nbt("kit-handler", "glad")
											.nbt("cancel-drop")
											.toStack()
									);
								}
								if (KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.GRAPPLER)) {
								 player.getInventory().setItem(2, new ItemBuilder("§aLaço!", Material.LEASH)
							                .addEnchant(Enchantment.KNOCKBACK, 1)
							        				.addFlags(ItemFlag.HIDE_ENCHANTS)
							                .nbt("cancel-drop")
							                .toStack()
							        );
								}
								if (KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.KANGAROO)) {
									player.getInventory().setItem(2, new ItemBuilder("§aPular!", Material.FIREWORK)
											.nbt("kit-handler", "kangaroo")
											.nbt("cancel-drop")
											.toStack()
									);
									}
								if (KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.METEOR)) {
									player.getInventory().setItem(2, new ItemBuilder("§cMeteor", Material.FIREBALL)
											.nbt("kit-handler", "meteor")
											.nbt("cancel-drop")
											.toStack()
									);
									}
								if (KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.MONK)) {
									player.getInventory().setItem(2, new ItemBuilder("§eEmbaralhar!", Material.BLAZE_ROD)
											.nbt("kit-handler", "monk")
											.nbt("cancel-drop")
											.toStack()
									);
									}
								if (!KitManager.getPlayer(player.getName()).hasKit(HelixKit.PVP) && KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.PVP)) {
									player.getInventory().setItem(0, new ItemBuilder("§fEspada", Material.STONE_SWORD)
											.addEnchant(Enchantment.DAMAGE_ALL, 1)
											.nbt("cancel-drop")
											.toStack()
									);
								}
									if (KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.SWITCHER)) {
									 player.getInventory().setItem(2, new ItemBuilder(Material.SNOW_BALL)
								                .amount(2)
								                .nbt("cancel-drop").nbt("kit-handler", "switcher")
								                .toStack()
								        );
									}
									if (KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.THOR)) {
										player.getInventory().setItem(2, new ItemBuilder("§eCaboom!", Material.GOLD_AXE)
												.nbt("cancel-drop")
												.nbt("kit-handler", "thor")
												.toStack()
										);
										}
									if (KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.WATERBENDER)) {
									player.getInventory().setItem(2, new ItemBuilder("§bWaterBender!", Material.LAPIS_BLOCK)
								            .addEnchant(Enchantment.KNOCKBACK, 1)
								    				.addFlags(ItemFlag.HIDE_ENCHANTS)
								            .nbt("cancel-drop")
								            .toStack()
								    );
								}
	if (KitManager.getPlayer(player.getName()).hasKit(HelixKit.BARBARIAN) || KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.BARBARIAN)) {
	player.getInventory().setItem(0, new ItemBuilder("§7Espada", Material.WOOD_SWORD)
			.nbt("cancel-drop")
			.toStack()
	);
	}
	else {
		player.getInventory().setItem(0, new ItemBuilder("§7Espada", Material.STONE_SWORD)
				.nbt("cancel-drop")
				.toStack()
		);
	}
	
	 if (!KitManager.getPlayer(player.getName()).hasKit(HelixKit.NEO)) {
		 Habilidade.removeAbility(player);
		}
	 if (!SoupTypeGUI.compass.containsKey(player.getName())) {
	player.getInventory().setItem(8, new ItemBuilder("§9Compass", Material.COMPASS)
			.nbt("kit-handler", "search-players")
			.nbt("cancel-drop")
			.toStack());
	 }  else {
		player.getInventory().setItem(8 , new ItemStack(Material.MUSHROOM_SOUP));
	  
	 }
	
if (!SoupTypeGUI.savecocoa.containsKey(player.getName())) {
	player.getInventory().setItem(13, new ItemStack(Material.BOWL, 32));
	player.getInventory().setItem(14, new ItemStack(Material.RED_MUSHROOM, 32));
	player.getInventory().setItem(15, new ItemStack(Material.BROWN_MUSHROOM, 32));
}	else {
	Dye d = new Dye();
    d.setColor(DyeColor.BROWN);
    ItemStack d1 = d.toItemStack();
    d1.setAmount(32);
	player.getInventory().setItem(13, new ItemStack(Material.BOWL, 32));
	player.getInventory().setItem(14, new ItemStack(d1));
}
player.getInventory().setItem(3 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(4 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(5 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(6 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(7 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(9 , new ItemStack(Material.MUSHROOM_SOUP));
		if (!KitManager.getPlayer(player.getName()).hasKit(HelixKit.ARCHER) && !KitManager2.getPlayer(player.getName()).haskit2(HelixKit2.ARCHER)) {
		player.getInventory().setItem(10 , new ItemStack(Material.MUSHROOM_SOUP));
		}
		if (KitManager.getPlayer(player.getName()).hasKit( HelixKit.PVP) || KitManager2.getPlayer(player.getName()).haskit2( HelixKit2.PVP)) {
			player.getInventory().setItem(0, new ItemBuilder("§fEspada", Material.STONE_SWORD)
					.addEnchant(Enchantment.DAMAGE_ALL, 1)
					.nbt("cancel-drop")
					.toStack()
			);
			Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed pvp kit!");
			}
		player.getInventory().setItem(11 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(12 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(16 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(17 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(18 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(19 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(20 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(21 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(22 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(23 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(24 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(25 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(26 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(27 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(28 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(29 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(30 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(31 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(32 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(33 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(34 , new ItemStack(Material.MUSHROOM_SOUP));
		player.getInventory().setItem(35 , new ItemStack(Material.MUSHROOM_SOUP));
}
	

@EventHandler(priority = EventPriority.HIGHEST)
public void RemoverDanoEspomka(EntityDamageEvent e) 
{
	if (e.getCause() == EntityDamageEvent.DamageCause.FALL) { 
	{
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player p = (Player)e.getEntity();
		Block block = p.getLocation().getBlock().getRelative(0, -1, 0);
		if (block.getType() == Material.SPONGE) {
			e.setCancelled(true);
			return;
		}
		else if (!caiu.containsKey(p.getName())) {
			e.setCancelled(true);
			return;
		}
		else {
			e.setCancelled(e.isCancelled());
			return;
	}
	}
	}
}
	 
	   @EventHandler
		public void RemoverDano(EntityDamageEvent e) 
		{
		   if (!(e.getEntity() instanceof Player)) {
	           return;
	       }
			Player p = (Player) e.getEntity();
			if (e.getCause() == EntityDamageEvent.DamageCause.FALL && this.fall.contains(p.getName())) 
			{
				this.fall.remove(p.getName());
				e.setCancelled(true);
				EntityDamageEvent event = new EntityDamageEvent(p, EntityDamageEvent.DamageCause.FALL, 1.0F);
				p.setLastDamageCause(event);
				Bukkit.getServer().getPluginManager().callEvent(event);
				if (!caiu.containsKey(p.getName())) {
	caiu.put(p.getName(), true);
				}
			}
			else if(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK)
			{
				if (e.isCancelled()) {
					return;
				}
				if (!caiu.containsKey(p.getName())) {
					caiu.put(p.getName(), true);
					p.sendMessage(ChatColor.RED + "Você agora pode levar dano de queda novamente.");
				}
				this.fall.remove(p.getName());
			}
		}
		
	}


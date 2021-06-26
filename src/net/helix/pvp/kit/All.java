package net.helix.pvp.kit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import Kits.RTP;
import me.smart.HelixPVP.*;
import net.helix.pvp.Main;


public class All implements Listener, CommandExecutor {
	
	public static ArrayList<Player> kit = new ArrayList<Player>();
	
	public static ArrayList<String> ninja = new ArrayList<String>();
	public static ArrayList<String> ninjaDelay = new ArrayList<String>();
	public static HashMap<Player, Player> ninjaPlayer = new HashMap<Player, Player>();
	
	public static ArrayList<String> thor = new ArrayList<String>();
	public static ArrayList<String> thorDelay = new ArrayList<String>();
	
	public static ArrayList<String> anchor = new ArrayList<String>();
	
	public static ArrayList<String> viper = new ArrayList<String>();
	
	public static ArrayList<String> fireman = new ArrayList<String>();
	
	public static ArrayList<String> snail = new ArrayList<String>();
	
	public static ArrayList<String> confuser = new ArrayList<String>();
	
	public static ArrayList<String> stomper = new ArrayList<String>();
	
	public static ArrayList<String> poseidon = new ArrayList<String>();
	
	public static ArrayList<String> q = new ArrayList<String>();
	
	public static ArrayList<String> ajnin = new ArrayList<String>();
	public static ArrayList<String> ajninDelay = new ArrayList<String>();
	public static HashMap<Player, Player> ajninPlayer = new HashMap<Player, Player>();
	
	public static ArrayList<String> kDelay = new ArrayList<String>();
	
	public static HashMap<String, String> kitName = new HashMap<String, String>();
	public static HashMap<String, String> kit2Name = new HashMap<String, String>();
	
	public static ArrayList<String> glad = new ArrayList<String>();
	public static ArrayList<String> gladKIT = new ArrayList<String>();
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("kit")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§c§lKIT §fComando apenas para jogadores.");
				return true;
			}
			Player p = (Player) sender;
			if (args.length == 0) {
				p.sendMessage("§3§lKIT §fUtilize §3/kit <nome do kit>");
				return true;
			}
			if (args.length >= 1) {
				if (!kit.contains(p)) {
					World w = Bukkit.getWorld("Lobby");
					if (args[0].equalsIgnoreCase("pvp")) {
						p.teleport(w.getSpawnLocation());
						p.setAllowFlight(false);
						p.setFlying(false);
						RTP.TeleportArenaRandom(p);
						kit.add(p);
						p.getInventory().clear();
						p.getInventory().setArmorContents(null);
						kitName.put(p.getName(), "PvP");
						p.sendMessage("§6§lKIT §fVocê selecionou o kit §6PvP§f.");
						
						ItemStack espadaPvP = new ItemStack(Material.IRON_SWORD);
						ItemMeta espadaPvPMeta = espadaPvP.getItemMeta();
						espadaPvPMeta.setDisplayName("§2Kit: §7(§2PvP§7)");
						espadaPvPMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
							
						ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
						ItemMeta sopaMeta = sopa.getItemMeta();
						sopaMeta.setDisplayName("§3Sopa");
						
						ItemStack peitoral = new ItemStack(Material.IRON_CHESTPLATE);
						ItemMeta peitoralMeta = peitoral.getItemMeta();
						peitoralMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
						peitoralMeta.setDisplayName("§cArmadura");
						
						ItemStack pote = new ItemStack(Material.BOWL, 64);
						ItemMeta poteMeta = pote.getItemMeta();
						poteMeta.setDisplayName("§6Recraft");
						
						ItemStack cogu = new ItemStack(Material.RED_MUSHROOM, 64);
						ItemMeta coguMeta = cogu.getItemMeta();
						coguMeta.setDisplayName("§6Recraft");
						
						ItemStack cogu2 = new ItemStack(Material.BROWN_MUSHROOM, 64);
						ItemMeta cogu2Meta = cogu2.getItemMeta();
						cogu2Meta.setDisplayName("§6Recraft");
						
						ItemStack compass = new ItemStack(Material.COMPASS);
						ItemMeta compassMeta = compass.getItemMeta();
						compassMeta.setDisplayName("§eBússola");
						
						compass.setItemMeta(compassMeta);
						pote.setItemMeta(poteMeta);
						cogu.setItemMeta(coguMeta);
						cogu2.setItemMeta(cogu2Meta);
						
						ItemStack capacete = new ItemStack(Material.IRON_HELMET);
						ItemMeta capaceteMeta = capacete.getItemMeta();
						capaceteMeta.setDisplayName("§eArmadura");
						
						ItemStack calca = new ItemStack(Material.IRON_LEGGINGS);
						ItemMeta calcaMeta = calca.getItemMeta();
						calcaMeta.setDisplayName("§eArmadura");
						
						ItemStack bota = new ItemStack(Material.IRON_BOOTS);
						ItemMeta botaMeta = bota.getItemMeta();
						botaMeta.setDisplayName("§eArmadura");
						
						bota.setItemMeta(botaMeta);
						calca.setItemMeta(calcaMeta);
						peitoral.setItemMeta(peitoralMeta);
						capacete.setItemMeta(capaceteMeta);
						
						p.getInventory().setHelmet(capacete);
						p.getInventory().setChestplate(peitoral);
						p.getInventory().setLeggings(calca);
						p.getInventory().setBoots(bota);

						sopa.setItemMeta(sopaMeta);
						espadaPvP.setItemMeta(espadaPvPMeta);
	
						p.getInventory().setChestplate(peitoral);
						p.getInventory().setItem(1, sopa);
						p.getInventory().setItem(2, sopa);
						p.getInventory().setItem(3, sopa);
						p.getInventory().setItem(4, sopa);
						p.getInventory().setItem(5, sopa);
						p.getInventory().setItem(6, sopa);
						p.getInventory().setItem(7, sopa);
						p.getInventory().setItem(8, compass);
						p.getInventory().setItem(9, sopa);
						p.getInventory().setItem(10, sopa);
						p.getInventory().setItem(11, sopa);
						p.getInventory().setItem(12, sopa);
						p.getInventory().setItem(13, pote);
						p.getInventory().setItem(14, cogu);
						p.getInventory().setItem(15, cogu2);
						p.getInventory().setItem(16, sopa);
						p.getInventory().setItem(17, sopa);
						p.getInventory().setItem(18, sopa);
						p.getInventory().setItem(19, sopa);
						p.getInventory().setItem(20, sopa);
						p.getInventory().setItem(21, sopa);
						p.getInventory().setItem(22, sopa);
						p.getInventory().setItem(23, sopa);
						p.getInventory().setItem(24, sopa);
						p.getInventory().setItem(25, sopa);
						p.getInventory().setItem(26, sopa);
						p.getInventory().setItem(27, sopa);
						p.getInventory().setItem(28, sopa);
						p.getInventory().setItem(29, sopa);
						p.getInventory().setItem(30, sopa);
						p.getInventory().setItem(31, sopa);
						p.getInventory().setItem(32, sopa);
						p.getInventory().setItem(33, sopa);
						p.getInventory().setItem(34, sopa);
						p.getInventory().setItem(35, sopa);
							
							
						p.getInventory().setItem(0, espadaPvP);
					}else if (args[0].equalsIgnoreCase("arqueiro")) {
						p.teleport(w.getSpawnLocation());
						kitName.put(p.getName(), "Arqueiro");
						p.setAllowFlight(false);
						p.setFlying(false);
						kit.add(p);
						p.getInventory().clear();
						p.getInventory().setArmorContents(null);
						p.sendMessage("§6§lKIT §fVocê selecionou o kit §6Arqueiro§f.");
						RTP.TeleportArenaRandom(p);
						ItemStack espadaPvP = new ItemStack(Material.IRON_SWORD);
						ItemMeta espadaPvPMeta = espadaPvP.getItemMeta();
						espadaPvPMeta.setDisplayName("§2Kit: §7(§2Arqueiro§7)");
							
						ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
						ItemMeta sopaMeta = sopa.getItemMeta();
						sopaMeta.setDisplayName("§3Sopa");
						
						ItemStack arco = new ItemStack(Material.BOW);
						ItemMeta arcoMeta = arco.getItemMeta();
						arcoMeta.setDisplayName("§2Kit: §7(§2Arqueiro§7)");
						arcoMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
						
						ItemStack flecha = new ItemStack(Material.ARROW, 64);
						ItemMeta flechaMeta = flecha.getItemMeta();
						flechaMeta.setDisplayName("§2Flechas");
						
						ItemStack pote = new ItemStack(Material.BOWL, 64);
						ItemMeta poteMeta = pote.getItemMeta();
						poteMeta.setDisplayName("§6Recraft");
						
						ItemStack cogu = new ItemStack(Material.RED_MUSHROOM, 64);
						ItemMeta coguMeta = cogu.getItemMeta();
						coguMeta.setDisplayName("§6Recraft");
						
						ItemStack cogu2 = new ItemStack(Material.BROWN_MUSHROOM, 64);
						ItemMeta cogu2Meta = cogu2.getItemMeta();
						cogu2Meta.setDisplayName("§6Recraft");
						
						ItemStack compass = new ItemStack(Material.COMPASS);
						ItemMeta compassMeta = compass.getItemMeta();
						compassMeta.setDisplayName("§eBússola");
						
						ItemStack capacete = new ItemStack(Material.IRON_HELMET);
						ItemMeta capaceteMeta = capacete.getItemMeta();
						capaceteMeta.setDisplayName("§eArmadura");
						
						ItemStack peitoral = new ItemStack(Material.IRON_CHESTPLATE);
						ItemMeta peitoralMeta = peitoral.getItemMeta();
						peitoralMeta.setDisplayName("§eArmadura");
						
						ItemStack calca = new ItemStack(Material.IRON_LEGGINGS);
						ItemMeta calcaMeta = calca.getItemMeta();
						calcaMeta.setDisplayName("§eArmadura");
						
						ItemStack bota = new ItemStack(Material.IRON_BOOTS);
						ItemMeta botaMeta = bota.getItemMeta();
						botaMeta.setDisplayName("§eArmadura");
						
						bota.setItemMeta(botaMeta);
						calca.setItemMeta(calcaMeta);
						peitoral.setItemMeta(peitoralMeta);
						capacete.setItemMeta(capaceteMeta);
						
						p.getInventory().setHelmet(capacete);
						p.getInventory().setChestplate(peitoral);
						p.getInventory().setLeggings(calca);
						p.getInventory().setBoots(bota);
						
						compass.setItemMeta(compassMeta);						
						pote.setItemMeta(poteMeta);
						cogu.setItemMeta(coguMeta);
						cogu2.setItemMeta(cogu2Meta);
						flecha.setItemMeta(flechaMeta);
						arco.setItemMeta(arcoMeta);
						sopa.setItemMeta(sopaMeta);
						espadaPvP.setItemMeta(espadaPvPMeta);
	
						p.getInventory().setItem(1, arco);
						p.getInventory().setItem(2, sopa);
						p.getInventory().setItem(3, sopa);
						p.getInventory().setItem(4, sopa);
						p.getInventory().setItem(5, sopa);
						p.getInventory().setItem(6, sopa);
						p.getInventory().setItem(7, sopa);
						p.getInventory().setItem(8, compass);
						p.getInventory().setItem(9, flecha);
						p.getInventory().setItem(10, sopa);
						p.getInventory().setItem(11, sopa);
						p.getInventory().setItem(12, sopa);
						p.getInventory().setItem(13, pote);
						p.getInventory().setItem(14, cogu);
						p.getInventory().setItem(15, cogu2);
						p.getInventory().setItem(16, sopa);
						p.getInventory().setItem(17, sopa);
						p.getInventory().setItem(18, sopa);
						p.getInventory().setItem(19, sopa);
						p.getInventory().setItem(20, sopa);
						p.getInventory().setItem(21, sopa);
						p.getInventory().setItem(22, sopa);
						p.getInventory().setItem(23, sopa);
						p.getInventory().setItem(24, sopa);
						p.getInventory().setItem(25, sopa);
						p.getInventory().setItem(26, sopa);
						p.getInventory().setItem(27, sopa);
						p.getInventory().setItem(28, sopa);
						p.getInventory().setItem(29, sopa);
						p.getInventory().setItem(30, sopa);
						p.getInventory().setItem(31, sopa);
						p.getInventory().setItem(32, sopa);
						p.getInventory().setItem(33, sopa);
						p.getInventory().setItem(34, sopa);
						p.getInventory().setItem(35, sopa);
							
							
						p.getInventory().setItem(0, espadaPvP);
						
					}else if (args[0].equalsIgnoreCase("ninja")) {
						p.teleport(w.getSpawnLocation());
						kitName.put(p.getName(), "Ninja");
						ninja.add(p.getName());
						p.setAllowFlight(false);
						p.setFlying(false);
						kit.add(p);
						p.getInventory().clear();
						p.getInventory().setArmorContents(null);
						p.sendMessage("§6§lKIT §fVocê selecionou o kit §6Ninja§f.");
	
						ItemStack espadaPvP = new ItemStack(Material.IRON_SWORD);
						ItemMeta espadaPvPMeta = espadaPvP.getItemMeta();
						espadaPvPMeta.setDisplayName("§2Kit: §7(§2Ninja§7)");
						RTP.TeleportArenaRandom(p);
						ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
						ItemMeta sopaMeta = sopa.getItemMeta();
						sopaMeta.setDisplayName("§3Sopa");
						
						ItemStack pote = new ItemStack(Material.BOWL, 64);
						ItemMeta poteMeta = pote.getItemMeta();
						poteMeta.setDisplayName("§6Recraft");
						
						ItemStack cogu = new ItemStack(Material.RED_MUSHROOM, 64);
						ItemMeta coguMeta = cogu.getItemMeta();
						coguMeta.setDisplayName("§6Recraft");
						
						ItemStack cogu2 = new ItemStack(Material.BROWN_MUSHROOM, 64);
						ItemMeta cogu2Meta = cogu2.getItemMeta();
						cogu2Meta.setDisplayName("§6Recraft");
						
						ItemStack compass = new ItemStack(Material.COMPASS);
						ItemMeta compassMeta = compass.getItemMeta();
						compassMeta.setDisplayName("§eBússola");
						
						compass.setItemMeta(compassMeta);
						pote.setItemMeta(poteMeta);
						cogu.setItemMeta(coguMeta);
						cogu2.setItemMeta(cogu2Meta);
						
						sopa.setItemMeta(sopaMeta);
						espadaPvP.setItemMeta(espadaPvPMeta);
						
						ItemStack capacete = new ItemStack(Material.IRON_HELMET);
						ItemMeta capaceteMeta = capacete.getItemMeta();
						capaceteMeta.setDisplayName("§eArmadura");
						
						ItemStack peitoral = new ItemStack(Material.IRON_CHESTPLATE);
						ItemMeta peitoralMeta = peitoral.getItemMeta();
						peitoralMeta.setDisplayName("§eArmadura");
						
						ItemStack calca = new ItemStack(Material.IRON_LEGGINGS);
						ItemMeta calcaMeta = calca.getItemMeta();
						calcaMeta.setDisplayName("§eArmadura");
						
						ItemStack bota = new ItemStack(Material.IRON_BOOTS);
						ItemMeta botaMeta = bota.getItemMeta();
						botaMeta.setDisplayName("§eArmadura");
						
						bota.setItemMeta(botaMeta);
						calca.setItemMeta(calcaMeta);
						peitoral.setItemMeta(peitoralMeta);
						capacete.setItemMeta(capaceteMeta);
						
						p.getInventory().setHelmet(capacete);
						p.getInventory().setChestplate(peitoral);
						p.getInventory().setLeggings(calca);
						p.getInventory().setBoots(bota);
	
						p.getInventory().setItem(1, sopa);
						p.getInventory().setItem(2, sopa);
						p.getInventory().setItem(3, sopa);
						p.getInventory().setItem(4, sopa);
						p.getInventory().setItem(5, sopa);
						p.getInventory().setItem(6, sopa);
						p.getInventory().setItem(7, sopa);
						p.getInventory().setItem(8, compass);
						p.getInventory().setItem(9, sopa);
						p.getInventory().setItem(10, sopa);
						p.getInventory().setItem(11, sopa);
						p.getInventory().setItem(12, sopa);
						p.getInventory().setItem(13, pote);
						p.getInventory().setItem(14, cogu);
						p.getInventory().setItem(15, cogu2);
						p.getInventory().setItem(16, sopa);
						p.getInventory().setItem(17, sopa);
						p.getInventory().setItem(18, sopa);
						p.getInventory().setItem(19, sopa);
						p.getInventory().setItem(20, sopa);
						p.getInventory().setItem(21, sopa);
						p.getInventory().setItem(22, sopa);
						p.getInventory().setItem(23, sopa);
						p.getInventory().setItem(24, sopa);
						p.getInventory().setItem(25, sopa);
						p.getInventory().setItem(26, sopa);
						p.getInventory().setItem(27, sopa);
						p.getInventory().setItem(28, sopa);
						p.getInventory().setItem(29, sopa);
						p.getInventory().setItem(30, sopa);
						p.getInventory().setItem(31, sopa);
						p.getInventory().setItem(32, sopa);
						p.getInventory().setItem(33, sopa);
						p.getInventory().setItem(34, sopa);
						p.getInventory().setItem(35, sopa);
							
							
						p.getInventory().setItem(0, espadaPvP);
						
					}else if (args[0].equalsIgnoreCase("quickdrop")) {
						p.teleport(w.getSpawnLocation());
						kitName.put(p.getName(), "Quickdrop");
						q.add(p.getName());
						p.setAllowFlight(false);
						p.setFlying(false);
						kit.add(p);
						p.getInventory().clear();
						p.getInventory().setArmorContents(null);
						p.sendMessage("§6§lKIT §fVocê selecionou o kit §6Quickdrop§f.");
						RTP.TeleportArenaRandom(p);
						ItemStack espadaPvP = new ItemStack(Material.IRON_SWORD);
						ItemMeta espadaPvPMeta = espadaPvP.getItemMeta();
						espadaPvPMeta.setDisplayName("§2Kit: §7(§2Quickdrop§7)");
							
						ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
						ItemMeta sopaMeta = sopa.getItemMeta();
						sopaMeta.setDisplayName("§3Sopa");
						
						ItemStack pote = new ItemStack(Material.BOWL, 64);
						ItemMeta poteMeta = pote.getItemMeta();
						poteMeta.setDisplayName("§6Recraft");
						
						ItemStack cogu = new ItemStack(Material.RED_MUSHROOM, 64);
						ItemMeta coguMeta = cogu.getItemMeta();
						coguMeta.setDisplayName("§6Recraft");
						
						ItemStack cogu2 = new ItemStack(Material.BROWN_MUSHROOM, 64);
						ItemMeta cogu2Meta = cogu2.getItemMeta();
						cogu2Meta.setDisplayName("§6Recraft");
						
						ItemStack compass = new ItemStack(Material.COMPASS);
						ItemMeta compassMeta = compass.getItemMeta();
						compassMeta.setDisplayName("§eBússola");
						
						ItemStack capacete = new ItemStack(Material.IRON_HELMET);
						ItemMeta capaceteMeta = capacete.getItemMeta();
						capaceteMeta.setDisplayName("§eArmadura");
						
						ItemStack peitoral = new ItemStack(Material.IRON_CHESTPLATE);
						ItemMeta peitoralMeta = peitoral.getItemMeta();
						peitoralMeta.setDisplayName("§eArmadura");
						
						ItemStack calca = new ItemStack(Material.IRON_LEGGINGS);
						ItemMeta calcaMeta = calca.getItemMeta();
						calcaMeta.setDisplayName("§eArmadura");
						
						ItemStack bota = new ItemStack(Material.IRON_BOOTS);
						ItemMeta botaMeta = bota.getItemMeta();
						botaMeta.setDisplayName("§eArmadura");
						
						bota.setItemMeta(botaMeta);
						calca.setItemMeta(calcaMeta);
						peitoral.setItemMeta(peitoralMeta);
						capacete.setItemMeta(capaceteMeta);
						
						p.getInventory().setHelmet(capacete);
						p.getInventory().setChestplate(peitoral);
						p.getInventory().setLeggings(calca);
						p.getInventory().setBoots(bota);
						
						compass.setItemMeta(compassMeta);
						pote.setItemMeta(poteMeta);
						cogu.setItemMeta(coguMeta);
						cogu2.setItemMeta(cogu2Meta);
						sopa.setItemMeta(sopaMeta);
						espadaPvP.setItemMeta(espadaPvPMeta);
	
						p.getInventory().setItem(1, sopa);
						p.getInventory().setItem(2, sopa);
						p.getInventory().setItem(3, sopa);
						p.getInventory().setItem(4, sopa);
						p.getInventory().setItem(5, sopa);
						p.getInventory().setItem(6, sopa);
						p.getInventory().setItem(7, sopa);
						p.getInventory().setItem(8, compass);
						p.getInventory().setItem(9, sopa);
						p.getInventory().setItem(10, sopa);
						p.getInventory().setItem(11, sopa);
						p.getInventory().setItem(12, sopa);
						p.getInventory().setItem(13, pote);
						p.getInventory().setItem(14, cogu);
						p.getInventory().setItem(15, cogu2);
						p.getInventory().setItem(16, sopa);
						p.getInventory().setItem(17, sopa);
						p.getInventory().setItem(18, sopa);
						p.getInventory().setItem(19, sopa);
						p.getInventory().setItem(20, sopa);
						p.getInventory().setItem(21, sopa);
						p.getInventory().setItem(22, sopa);
						p.getInventory().setItem(23, sopa);
						p.getInventory().setItem(24, sopa);
						p.getInventory().setItem(25, sopa);
						p.getInventory().setItem(26, sopa);
						p.getInventory().setItem(27, sopa);
						p.getInventory().setItem(28, sopa);
						p.getInventory().setItem(29, sopa);
						p.getInventory().setItem(30, sopa);
						p.getInventory().setItem(31, sopa);
						p.getInventory().setItem(32, sopa);
						p.getInventory().setItem(33, sopa);
						p.getInventory().setItem(34, sopa);
						p.getInventory().setItem(35, sopa);
							
							
						p.getInventory().setItem(0, espadaPvP);
					}else if (args[0].equalsIgnoreCase("kangaroo")) {
						p.teleport(w.getSpawnLocation());
						kitName.put(p.getName(), "Kangaroo");
						p.setAllowFlight(false);
						p.setFlying(false);
						kit.add(p);
						p.getInventory().clear();
						p.getInventory().setArmorContents(null);
						p.sendMessage("§6§lKIT §fVocê selecionou o kit §6Kangaroo§f.");
						RTP.TeleportArenaRandom(p);
						ItemStack espadaPvP = new ItemStack(Material.IRON_SWORD);
						ItemMeta espadaPvPMeta = espadaPvP.getItemMeta();
						espadaPvPMeta.setDisplayName("§2Kit: §7(§2Kangaroo§7)");
						
						ItemStack kangaroo = new ItemStack(Material.FIREWORK);
						ItemMeta kangarooMeta = kangaroo.getItemMeta();
						kangarooMeta.setDisplayName("§2Kit: §7(§2Kangaroo§7)");
							
						ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
						ItemMeta sopaMeta = sopa.getItemMeta();
						sopaMeta.setDisplayName("§3Sopa");
						
						ItemStack pote = new ItemStack(Material.BOWL, 64);
						ItemMeta poteMeta = pote.getItemMeta();
						poteMeta.setDisplayName("§6Recraft");
						
						ItemStack cogu = new ItemStack(Material.RED_MUSHROOM, 64);
						ItemMeta coguMeta = cogu.getItemMeta();
						coguMeta.setDisplayName("§6Recraft");
						
						ItemStack cogu2 = new ItemStack(Material.BROWN_MUSHROOM, 64);
						ItemMeta cogu2Meta = cogu2.getItemMeta();
						cogu2Meta.setDisplayName("§6Recraft");
						
						ItemStack compass = new ItemStack(Material.COMPASS);
						ItemMeta compassMeta = compass.getItemMeta();
						compassMeta.setDisplayName("§eBússola");
						
						compass.setItemMeta(compassMeta);
						kangaroo.setItemMeta(kangarooMeta);
						pote.setItemMeta(poteMeta);
						cogu.setItemMeta(coguMeta);
						cogu2.setItemMeta(cogu2Meta);
						sopa.setItemMeta(sopaMeta);
						espadaPvP.setItemMeta(espadaPvPMeta);
						
						ItemStack capacete = new ItemStack(Material.IRON_HELMET);
						ItemMeta capaceteMeta = capacete.getItemMeta();
						capaceteMeta.setDisplayName("§eArmadura");
						
						ItemStack peitoral = new ItemStack(Material.IRON_CHESTPLATE);
						ItemMeta peitoralMeta = peitoral.getItemMeta();
						peitoralMeta.setDisplayName("§eArmadura");
						
						ItemStack calca = new ItemStack(Material.IRON_LEGGINGS);
						ItemMeta calcaMeta = calca.getItemMeta();
						calcaMeta.setDisplayName("§eArmadura");
						
						ItemStack bota = new ItemStack(Material.IRON_BOOTS);
						ItemMeta botaMeta = bota.getItemMeta();
						botaMeta.setDisplayName("§eArmadura");
						
						bota.setItemMeta(botaMeta);
						calca.setItemMeta(calcaMeta);
						peitoral.setItemMeta(peitoralMeta);
						capacete.setItemMeta(capaceteMeta);
						
						p.getInventory().setHelmet(capacete);
						p.getInventory().setChestplate(peitoral);
						p.getInventory().setLeggings(calca);
						p.getInventory().setBoots(bota);
	
						p.getInventory().setItem(1, kangaroo);
						p.getInventory().setItem(2, sopa);
						p.getInventory().setItem(3, sopa);
						p.getInventory().setItem(4, sopa);
						p.getInventory().setItem(5, sopa);
						p.getInventory().setItem(6, sopa);
						p.getInventory().setItem(7, sopa);
						p.getInventory().setItem(8, compass);
						p.getInventory().setItem(9, sopa);
						p.getInventory().setItem(10, sopa);
						p.getInventory().setItem(11, sopa);
						p.getInventory().setItem(12, sopa);
						p.getInventory().setItem(13, pote);
						p.getInventory().setItem(14, cogu);
						p.getInventory().setItem(15, cogu2);
						p.getInventory().setItem(16, sopa);
						p.getInventory().setItem(17, sopa);
						p.getInventory().setItem(18, sopa);
						p.getInventory().setItem(19, sopa);
						p.getInventory().setItem(20, sopa);
						p.getInventory().setItem(21, sopa);
						p.getInventory().setItem(22, sopa);
						p.getInventory().setItem(23, sopa);
						p.getInventory().setItem(24, sopa);
						p.getInventory().setItem(25, sopa);
						p.getInventory().setItem(26, sopa);
						p.getInventory().setItem(27, sopa);
						p.getInventory().setItem(28, sopa);
						p.getInventory().setItem(29, sopa);
						p.getInventory().setItem(30, sopa);
						p.getInventory().setItem(31, sopa);
						p.getInventory().setItem(32, sopa);
						p.getInventory().setItem(33, sopa);
						p.getInventory().setItem(34, sopa);
						p.getInventory().setItem(35, sopa);
							
							
						p.getInventory().setItem(0, espadaPvP);
					}else if (args[0].equalsIgnoreCase("gladiator")) {
						p.teleport(w.getSpawnLocation());
						kitName.put(p.getName(), "Gladiator");
						p.setAllowFlight(false);
						p.setFlying(false);
						kit.add(p);
						p.getInventory().clear();
						p.getInventory().setArmorContents(null);
						p.sendMessage("§6§lKIT §fVocê selecionou o kit §6Gladiator§f.");
						RTP.TeleportArenaRandom(p);
						ItemStack espadaPvP = new ItemStack(Material.IRON_SWORD);
						ItemMeta espadaPvPMeta = espadaPvP.getItemMeta();
						espadaPvPMeta.setDisplayName("§2Kit: §7(§2Gladiator§7)");
						
						ItemStack kangaroo = new ItemStack(Material.IRON_FENCE);
						ItemMeta kangarooMeta = kangaroo.getItemMeta();
						kangarooMeta.setDisplayName("§2Kit: §7(§2Gladiator§7)");
							
						ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
						ItemMeta sopaMeta = sopa.getItemMeta();
						sopaMeta.setDisplayName("§3Sopa");
						
						ItemStack pote = new ItemStack(Material.BOWL, 64);
						ItemMeta poteMeta = pote.getItemMeta();
						poteMeta.setDisplayName("§6Recraft");
						
						ItemStack cogu = new ItemStack(Material.RED_MUSHROOM, 64);
						ItemMeta coguMeta = cogu.getItemMeta();
						coguMeta.setDisplayName("§6Recraft");
						
						ItemStack cogu2 = new ItemStack(Material.BROWN_MUSHROOM, 64);
						ItemMeta cogu2Meta = cogu2.getItemMeta();
						cogu2Meta.setDisplayName("§6Recraft");
						
						ItemStack compass = new ItemStack(Material.COMPASS);
						ItemMeta compassMeta = compass.getItemMeta();
						compassMeta.setDisplayName("§eBússola");
						
						compass.setItemMeta(compassMeta);
						kangaroo.setItemMeta(kangarooMeta);
						pote.setItemMeta(poteMeta);
						cogu.setItemMeta(coguMeta);
						cogu2.setItemMeta(cogu2Meta);
						sopa.setItemMeta(sopaMeta);
						espadaPvP.setItemMeta(espadaPvPMeta);
						
						ItemStack capacete = new ItemStack(Material.IRON_HELMET);
						ItemMeta capaceteMeta = capacete.getItemMeta();
						capaceteMeta.setDisplayName("§eArmadura");
						
						ItemStack peitoral = new ItemStack(Material.IRON_CHESTPLATE);
						ItemMeta peitoralMeta = peitoral.getItemMeta();
						peitoralMeta.setDisplayName("§eArmadura");
						
						ItemStack calca = new ItemStack(Material.IRON_LEGGINGS);
						ItemMeta calcaMeta = calca.getItemMeta();
						calcaMeta.setDisplayName("§eArmadura");
						
						ItemStack bota = new ItemStack(Material.IRON_BOOTS);
						ItemMeta botaMeta = bota.getItemMeta();
						botaMeta.setDisplayName("§eArmadura");
						
						bota.setItemMeta(botaMeta);
						calca.setItemMeta(calcaMeta);
						peitoral.setItemMeta(peitoralMeta);
						capacete.setItemMeta(capaceteMeta);
						
						p.getInventory().setHelmet(capacete);
						p.getInventory().setChestplate(peitoral);
						p.getInventory().setLeggings(calca);
						p.getInventory().setBoots(bota);
	
						p.getInventory().setItem(1, kangaroo);
						p.getInventory().setItem(2, sopa);
						p.getInventory().setItem(3, sopa);
						p.getInventory().setItem(4, sopa);
						p.getInventory().setItem(5, sopa);
						p.getInventory().setItem(6, sopa);
						p.getInventory().setItem(7, sopa);
						p.getInventory().setItem(8, compass);
						p.getInventory().setItem(9, sopa);
						p.getInventory().setItem(10, sopa);
						p.getInventory().setItem(11, sopa);
						p.getInventory().setItem(12, sopa);
						p.getInventory().setItem(13, pote);
						p.getInventory().setItem(14, cogu);
						p.getInventory().setItem(15, cogu2);
						p.getInventory().setItem(16, sopa);
						p.getInventory().setItem(17, sopa);
						p.getInventory().setItem(18, sopa);
						p.getInventory().setItem(19, sopa);
						p.getInventory().setItem(20, sopa);
						p.getInventory().setItem(21, sopa);
						p.getInventory().setItem(22, sopa);
						p.getInventory().setItem(23, sopa);
						p.getInventory().setItem(24, sopa);
						p.getInventory().setItem(25, sopa);
						p.getInventory().setItem(26, sopa);
						p.getInventory().setItem(27, sopa);
						p.getInventory().setItem(28, sopa);
						p.getInventory().setItem(29, sopa);
						p.getInventory().setItem(30, sopa);
						p.getInventory().setItem(31, sopa);
						p.getInventory().setItem(32, sopa);
						p.getInventory().setItem(33, sopa);
						p.getInventory().setItem(34, sopa);
						p.getInventory().setItem(35, sopa);
							
							
						p.getInventory().setItem(0, espadaPvP);
					}else if (args[0].equalsIgnoreCase("fisherman")) {
						p.teleport(w.getSpawnLocation());
						kitName.put(p.getName(), "Fisherman");
						p.setAllowFlight(false);
						p.setFlying(false);
						kit.add(p);
						p.getInventory().clear();
						p.getInventory().setArmorContents(null);
						p.sendMessage("§6§lKIT §fVocê selecionou o kit §6Fisherman§f.");
						RTP.TeleportArenaRandom(p);
						ItemStack espadaPvP = new ItemStack(Material.IRON_SWORD);
						ItemMeta espadaPvPMeta = espadaPvP.getItemMeta();
						espadaPvPMeta.setDisplayName("§2Kit: §7(§2Fisherman§7)");
						
						ItemStack kangaroo = new ItemStack(Material.FISHING_ROD);
						ItemMeta kangarooMeta = kangaroo.getItemMeta();
						kangarooMeta.setDisplayName("§2Kit: §7(§2Fisherman§7)");
							
						ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
						ItemMeta sopaMeta = sopa.getItemMeta();
						sopaMeta.setDisplayName("§3Sopa");
						
						ItemStack pote = new ItemStack(Material.BOWL, 64);
						ItemMeta poteMeta = pote.getItemMeta();
						poteMeta.setDisplayName("§6Recraft");
						
						ItemStack cogu = new ItemStack(Material.RED_MUSHROOM, 64);
						ItemMeta coguMeta = cogu.getItemMeta();
						coguMeta.setDisplayName("§6Recraft");
						
						ItemStack cogu2 = new ItemStack(Material.BROWN_MUSHROOM, 64);
						ItemMeta cogu2Meta = cogu2.getItemMeta();
						cogu2Meta.setDisplayName("§6Recraft");
						
						ItemStack compass = new ItemStack(Material.COMPASS);
						ItemMeta compassMeta = compass.getItemMeta();
						compassMeta.setDisplayName("§eBússola");
						
						compass.setItemMeta(compassMeta);
						kangaroo.setItemMeta(kangarooMeta);
						pote.setItemMeta(poteMeta);
						cogu.setItemMeta(coguMeta);
						cogu2.setItemMeta(cogu2Meta);
						sopa.setItemMeta(sopaMeta);
						espadaPvP.setItemMeta(espadaPvPMeta);
						
						ItemStack capacete = new ItemStack(Material.IRON_HELMET);
						ItemMeta capaceteMeta = capacete.getItemMeta();
						capaceteMeta.setDisplayName("§eArmadura");
						
						ItemStack peitoral = new ItemStack(Material.IRON_CHESTPLATE);
						ItemMeta peitoralMeta = peitoral.getItemMeta();
						peitoralMeta.setDisplayName("§eArmadura");
						
						ItemStack calca = new ItemStack(Material.IRON_LEGGINGS);
						ItemMeta calcaMeta = calca.getItemMeta();
						calcaMeta.setDisplayName("§eArmadura");
						
						ItemStack bota = new ItemStack(Material.IRON_BOOTS);
						ItemMeta botaMeta = bota.getItemMeta();
						botaMeta.setDisplayName("§eArmadura");
						
						bota.setItemMeta(botaMeta);
						calca.setItemMeta(calcaMeta);
						peitoral.setItemMeta(peitoralMeta);
						capacete.setItemMeta(capaceteMeta);
						
						p.getInventory().setHelmet(capacete);
						p.getInventory().setChestplate(peitoral);
						p.getInventory().setLeggings(calca);
						p.getInventory().setBoots(bota);
	
						p.getInventory().setItem(1, kangaroo);
						p.getInventory().setItem(2, sopa);
						p.getInventory().setItem(3, sopa);
						p.getInventory().setItem(4, sopa);
						p.getInventory().setItem(5, sopa);
						p.getInventory().setItem(6, sopa);
						p.getInventory().setItem(7, sopa);
						p.getInventory().setItem(8, compass);
						p.getInventory().setItem(9, sopa);
						p.getInventory().setItem(10, sopa);
						p.getInventory().setItem(11, sopa);
						p.getInventory().setItem(12, sopa);
						p.getInventory().setItem(13, pote);
						p.getInventory().setItem(14, cogu);
						p.getInventory().setItem(15, cogu2);
						p.getInventory().setItem(16, sopa);
						p.getInventory().setItem(17, sopa);
						p.getInventory().setItem(18, sopa);
						p.getInventory().setItem(19, sopa);
						p.getInventory().setItem(20, sopa);
						p.getInventory().setItem(21, sopa);
						p.getInventory().setItem(22, sopa);
						p.getInventory().setItem(23, sopa);
						p.getInventory().setItem(24, sopa);
						p.getInventory().setItem(25, sopa);
						p.getInventory().setItem(26, sopa);
						p.getInventory().setItem(27, sopa);
						p.getInventory().setItem(28, sopa);
						p.getInventory().setItem(29, sopa);
						p.getInventory().setItem(30, sopa);
						p.getInventory().setItem(31, sopa);
						p.getInventory().setItem(32, sopa);
						p.getInventory().setItem(33, sopa);
						p.getInventory().setItem(34, sopa);
						p.getInventory().setItem(35, sopa);
							
							
						p.getInventory().setItem(0, espadaPvP);
					}else if (args[0].equalsIgnoreCase("monk")) {
						p.teleport(w.getSpawnLocation());
						kitName.put(p.getName(), "Monk");
						p.setAllowFlight(false);
						p.setFlying(false);
						kit.add(p);
						p.getInventory().clear();
						p.getInventory().setArmorContents(null);
						p.sendMessage("§6§lKIT §fVocê selecionou o kit §6Monk§f.");
						RTP.TeleportArenaRandom(p);
						ItemStack espadaPvP = new ItemStack(Material.IRON_SWORD);
						ItemMeta espadaPvPMeta = espadaPvP.getItemMeta();
						espadaPvPMeta.setDisplayName("§2Kit: §7(§2Monk§7)");
						
						ItemStack kangaroo = new ItemStack(Material.BLAZE_ROD);
						ItemMeta kangarooMeta = kangaroo.getItemMeta();
						kangarooMeta.setDisplayName("§2Kit: §7(§2Monk§7)");
							
						ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
						ItemMeta sopaMeta = sopa.getItemMeta();
						sopaMeta.setDisplayName("§3Sopa");
						
						ItemStack pote = new ItemStack(Material.BOWL, 64);
						ItemMeta poteMeta = pote.getItemMeta();
						poteMeta.setDisplayName("§6Recraft");
						
						ItemStack cogu = new ItemStack(Material.RED_MUSHROOM, 64);
						ItemMeta coguMeta = cogu.getItemMeta();
						coguMeta.setDisplayName("§6Recraft");
						
						ItemStack cogu2 = new ItemStack(Material.BROWN_MUSHROOM, 64);
						ItemMeta cogu2Meta = cogu2.getItemMeta();
						cogu2Meta.setDisplayName("§6Recraft");
						
						ItemStack compass = new ItemStack(Material.COMPASS);
						ItemMeta compassMeta = compass.getItemMeta();
						compassMeta.setDisplayName("§eBússola");
						
						compass.setItemMeta(compassMeta);
						kangaroo.setItemMeta(kangarooMeta);
						pote.setItemMeta(poteMeta);
						cogu.setItemMeta(coguMeta);
						cogu2.setItemMeta(cogu2Meta);
						sopa.setItemMeta(sopaMeta);
						espadaPvP.setItemMeta(espadaPvPMeta);
						
						ItemStack capacete = new ItemStack(Material.IRON_HELMET);
						ItemMeta capaceteMeta = capacete.getItemMeta();
						capaceteMeta.setDisplayName("§eArmadura");
						
						ItemStack peitoral = new ItemStack(Material.IRON_CHESTPLATE);
						ItemMeta peitoralMeta = peitoral.getItemMeta();
						peitoralMeta.setDisplayName("§eArmadura");
						
						ItemStack calca = new ItemStack(Material.IRON_LEGGINGS);
						ItemMeta calcaMeta = calca.getItemMeta();
						calcaMeta.setDisplayName("§eArmadura");
						
						ItemStack bota = new ItemStack(Material.IRON_BOOTS);
						ItemMeta botaMeta = bota.getItemMeta();
						botaMeta.setDisplayName("§eArmadura");
						
						bota.setItemMeta(botaMeta);
						calca.setItemMeta(calcaMeta);
						peitoral.setItemMeta(peitoralMeta);
						capacete.setItemMeta(capaceteMeta);
						
						p.getInventory().setHelmet(capacete);
						p.getInventory().setChestplate(peitoral);
						p.getInventory().setLeggings(calca);
						p.getInventory().setBoots(bota);
	
						p.getInventory().setItem(1, kangaroo);
						p.getInventory().setItem(2, sopa);
						p.getInventory().setItem(3, sopa);
						p.getInventory().setItem(4, sopa);
						p.getInventory().setItem(5, sopa);
						p.getInventory().setItem(6, sopa);
						p.getInventory().setItem(7, sopa);
						p.getInventory().setItem(8, compass);
						p.getInventory().setItem(9, sopa);
						p.getInventory().setItem(10, sopa);
						p.getInventory().setItem(11, sopa);
						p.getInventory().setItem(12, sopa);
						p.getInventory().setItem(13, pote);
						p.getInventory().setItem(14, cogu);
						p.getInventory().setItem(15, cogu2);
						p.getInventory().setItem(16, sopa);
						p.getInventory().setItem(17, sopa);
						p.getInventory().setItem(18, sopa);
						p.getInventory().setItem(19, sopa);
						p.getInventory().setItem(20, sopa);
						p.getInventory().setItem(21, sopa);
						p.getInventory().setItem(22, sopa);
						p.getInventory().setItem(23, sopa);
						p.getInventory().setItem(24, sopa);
						p.getInventory().setItem(25, sopa);
						p.getInventory().setItem(26, sopa);
						p.getInventory().setItem(27, sopa);
						p.getInventory().setItem(28, sopa);
						p.getInventory().setItem(29, sopa);
						p.getInventory().setItem(30, sopa);
						p.getInventory().setItem(31, sopa);
						p.getInventory().setItem(32, sopa);
						p.getInventory().setItem(33, sopa);
						p.getInventory().setItem(34, sopa);
						p.getInventory().setItem(35, sopa);
							
							
						p.getInventory().setItem(0, espadaPvP);
					}else if (args[0].equalsIgnoreCase("ajnin")) {
						if (p.hasPermission("kit.ajnin")) {
							p.teleport(w.getSpawnLocation());
							kitName.put(p.getName(), "Ajnin");
							ajnin.add(p.getName());
							p.setAllowFlight(false);
							p.setFlying(false);
							kit.add(p);
							p.getInventory().clear();
							p.getInventory().setArmorContents(null);
							p.sendMessage("§6§lKIT §fVocê selecionou o kit §6Ajnin§f.");
							RTP.TeleportArenaRandom(p);
							ItemStack espadaPvP = new ItemStack(Material.IRON_SWORD);
							ItemMeta espadaPvPMeta = espadaPvP.getItemMeta();
							espadaPvPMeta.setDisplayName("§2Kit: §7(§2Ajnin§7)");
								
							ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
							ItemMeta sopaMeta = sopa.getItemMeta();
							sopaMeta.setDisplayName("§3Sopa");
							
							ItemStack pote = new ItemStack(Material.BOWL, 64);
							ItemMeta poteMeta = pote.getItemMeta();
							poteMeta.setDisplayName("§6Recraft");
							
							ItemStack cogu = new ItemStack(Material.RED_MUSHROOM, 64);
							ItemMeta coguMeta = cogu.getItemMeta();
							coguMeta.setDisplayName("§6Recraft");
							
							ItemStack cogu2 = new ItemStack(Material.BROWN_MUSHROOM, 64);
							ItemMeta cogu2Meta = cogu2.getItemMeta();
							cogu2Meta.setDisplayName("§6Recraft");
							
							ItemStack compass = new ItemStack(Material.COMPASS);
							ItemMeta compassMeta = compass.getItemMeta();
							compassMeta.setDisplayName("§eBússola");
							
							compass.setItemMeta(compassMeta);
							pote.setItemMeta(poteMeta);
							cogu.setItemMeta(coguMeta);
							cogu2.setItemMeta(cogu2Meta);
							
							sopa.setItemMeta(sopaMeta);
							espadaPvP.setItemMeta(espadaPvPMeta);
							
							ItemStack capacete = new ItemStack(Material.IRON_HELMET);
							ItemMeta capaceteMeta = capacete.getItemMeta();
							capaceteMeta.setDisplayName("§eArmadura");
							
							ItemStack peitoral = new ItemStack(Material.IRON_CHESTPLATE);
							ItemMeta peitoralMeta = peitoral.getItemMeta();
							peitoralMeta.setDisplayName("§eArmadura");
							
							ItemStack calca = new ItemStack(Material.IRON_LEGGINGS);
							ItemMeta calcaMeta = calca.getItemMeta();
							calcaMeta.setDisplayName("§eArmadura");
							
							ItemStack bota = new ItemStack(Material.IRON_BOOTS);
							ItemMeta botaMeta = bota.getItemMeta();
							botaMeta.setDisplayName("§eArmadura");
							
							bota.setItemMeta(botaMeta);
							calca.setItemMeta(calcaMeta);
							peitoral.setItemMeta(peitoralMeta);
							capacete.setItemMeta(capaceteMeta);
							
							p.getInventory().setHelmet(capacete);
							p.getInventory().setChestplate(peitoral);
							p.getInventory().setLeggings(calca);
							p.getInventory().setBoots(bota);
		
							p.getInventory().setItem(1, sopa);
							p.getInventory().setItem(2, sopa);
							p.getInventory().setItem(3, sopa);
							p.getInventory().setItem(4, sopa);
							p.getInventory().setItem(5, sopa);
							p.getInventory().setItem(6, sopa);
							p.getInventory().setItem(7, sopa);
							p.getInventory().setItem(8, compass);
							p.getInventory().setItem(9, sopa);
							p.getInventory().setItem(10, sopa);
							p.getInventory().setItem(11, sopa);
							p.getInventory().setItem(12, sopa);
							p.getInventory().setItem(13, pote);
							p.getInventory().setItem(14, cogu);
							p.getInventory().setItem(15, cogu2);
							p.getInventory().setItem(16, sopa);
							p.getInventory().setItem(17, sopa);
							p.getInventory().setItem(18, sopa);
							p.getInventory().setItem(19, sopa);
							p.getInventory().setItem(20, sopa);
							p.getInventory().setItem(21, sopa);
							p.getInventory().setItem(22, sopa);
							p.getInventory().setItem(23, sopa);
							p.getInventory().setItem(24, sopa);
							p.getInventory().setItem(25, sopa);
							p.getInventory().setItem(26, sopa);
							p.getInventory().setItem(27, sopa);
							p.getInventory().setItem(28, sopa);
							p.getInventory().setItem(29, sopa);
							p.getInventory().setItem(30, sopa);
							p.getInventory().setItem(31, sopa);
							p.getInventory().setItem(32, sopa);
							p.getInventory().setItem(33, sopa);
							p.getInventory().setItem(34, sopa);
							p.getInventory().setItem(35, sopa);
								
								
							p.getInventory().setItem(0, espadaPvP);
						}else {
							p.sendMessage("§c§lKIT §fVocê não tem permissão para utilizar este kit.");
						}
					}else if (args[0].equalsIgnoreCase("thor")) {
						if (p.hasPermission("kit.thor")) {
							p.teleport(w.getSpawnLocation());
							kitName.put(p.getName(), "Thor");
							p.setAllowFlight(false);
							p.setFlying(false);
							kit.add(p);
							thor.add(p.getName());
							p.getInventory().clear();
							p.getInventory().setArmorContents(null);
							p.sendMessage("§6§lKIT §fVocê selecionou o kit §6Thor§f.");
							RTP.TeleportArenaRandom(p);
							ItemStack espadaPvP = new ItemStack(Material.IRON_SWORD);
							ItemMeta espadaPvPMeta = espadaPvP.getItemMeta();
							espadaPvPMeta.setDisplayName("§2Kit: §7(§2Thor§7)");
							
							ItemStack machado = new ItemStack(Material.GOLD_AXE);
							ItemMeta machadoMeta = machado.getItemMeta();
							machadoMeta.setDisplayName("§2Kit: §7(§2Thor§7)");
								
							ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
							ItemMeta sopaMeta = sopa.getItemMeta();
							sopaMeta.setDisplayName("§3Sopa");
							
							ItemStack pote = new ItemStack(Material.BOWL, 64);
							ItemMeta poteMeta = pote.getItemMeta();
							poteMeta.setDisplayName("§6Recraft");
							
							ItemStack cogu = new ItemStack(Material.RED_MUSHROOM, 64);
							ItemMeta coguMeta = cogu.getItemMeta();
							coguMeta.setDisplayName("§6Recraft");
							
							ItemStack cogu2 = new ItemStack(Material.BROWN_MUSHROOM, 64);
							ItemMeta cogu2Meta = cogu2.getItemMeta();
							cogu2Meta.setDisplayName("§6Recraft");
							
							ItemStack compass = new ItemStack(Material.COMPASS);
							ItemMeta compassMeta = compass.getItemMeta();
							compassMeta.setDisplayName("§eBússola");
							
							compass.setItemMeta(compassMeta);
							machado.setItemMeta(machadoMeta);
							pote.setItemMeta(poteMeta);
							cogu.setItemMeta(coguMeta);
							cogu2.setItemMeta(cogu2Meta);
							sopa.setItemMeta(sopaMeta);
							espadaPvP.setItemMeta(espadaPvPMeta);
							
							ItemStack capacete = new ItemStack(Material.IRON_HELMET);
							ItemMeta capaceteMeta = capacete.getItemMeta();
							capaceteMeta.setDisplayName("§eArmadura");
							
							ItemStack peitoral = new ItemStack(Material.IRON_CHESTPLATE);
							ItemMeta peitoralMeta = peitoral.getItemMeta();
							peitoralMeta.setDisplayName("§eArmadura");
							
							ItemStack calca = new ItemStack(Material.IRON_LEGGINGS);
							ItemMeta calcaMeta = calca.getItemMeta();
							calcaMeta.setDisplayName("§eArmadura");
							
							ItemStack bota = new ItemStack(Material.IRON_BOOTS);
							ItemMeta botaMeta = bota.getItemMeta();
							botaMeta.setDisplayName("§eArmadura");
							
							bota.setItemMeta(botaMeta);
							calca.setItemMeta(calcaMeta);
							peitoral.setItemMeta(peitoralMeta);
							capacete.setItemMeta(capaceteMeta);
							
							p.getInventory().setHelmet(capacete);
							p.getInventory().setChestplate(peitoral);
							p.getInventory().setLeggings(calca);
							p.getInventory().setBoots(bota);
		
							p.getInventory().setItem(1, machado);
							p.getInventory().setItem(2, sopa);
							p.getInventory().setItem(3, sopa);
							p.getInventory().setItem(4, sopa);
							p.getInventory().setItem(5, sopa);
							p.getInventory().setItem(6, sopa);
							p.getInventory().setItem(7, sopa);
							p.getInventory().setItem(8, compass);
							p.getInventory().setItem(9, sopa);
							p.getInventory().setItem(10, sopa);
							p.getInventory().setItem(11, sopa);
							p.getInventory().setItem(12, sopa);
							p.getInventory().setItem(13, pote);
							p.getInventory().setItem(14, cogu);
							p.getInventory().setItem(15, cogu2);
							p.getInventory().setItem(16, sopa);
							p.getInventory().setItem(17, sopa);
							p.getInventory().setItem(18, sopa);
							p.getInventory().setItem(19, sopa);
							p.getInventory().setItem(20, sopa);
							p.getInventory().setItem(21, sopa);
							p.getInventory().setItem(22, sopa);
							p.getInventory().setItem(23, sopa);
							p.getInventory().setItem(24, sopa);
							p.getInventory().setItem(25, sopa);
							p.getInventory().setItem(26, sopa);
							p.getInventory().setItem(27, sopa);
							p.getInventory().setItem(28, sopa);
							p.getInventory().setItem(29, sopa);
							p.getInventory().setItem(30, sopa);
							p.getInventory().setItem(31, sopa);
							p.getInventory().setItem(32, sopa);
							p.getInventory().setItem(33, sopa);
							p.getInventory().setItem(34, sopa);
							p.getInventory().setItem(35, sopa);
								
							p.getInventory().setItem(0, espadaPvP);
						}else {
							p.sendMessage("§c§lKIT §fVocê não tem permissão para utilizar este kit.");
						}
					}else if (args[0].equalsIgnoreCase("anchor")) {
						if (p.hasPermission("kit.anchor")) {
							p.teleport(w.getSpawnLocation());
							anchor.add(p.getName());
							kitName.put(p.getName(), "Anchor");
							p.setAllowFlight(false);
							p.setFlying(false);
							kit.add(p);
							p.getInventory().clear();
							p.getInventory().setArmorContents(null);
							p.sendMessage("§6§lKIT §fVocê selecionou o kit §6Anchor§f.");
							RTP.TeleportArenaRandom(p);
							ItemStack espadaPvP = new ItemStack(Material.IRON_SWORD);
							ItemMeta espadaPvPMeta = espadaPvP.getItemMeta();
							espadaPvPMeta.setDisplayName("§2Kit: §7(§2Anchor§7)");
								
							ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
							ItemMeta sopaMeta = sopa.getItemMeta();
							sopaMeta.setDisplayName("§3Sopa");
							
							ItemStack pote = new ItemStack(Material.BOWL, 64);
							ItemMeta poteMeta = pote.getItemMeta();
							poteMeta.setDisplayName("§6Recraft");
							
							ItemStack cogu = new ItemStack(Material.RED_MUSHROOM, 64);
							ItemMeta coguMeta = cogu.getItemMeta();
							coguMeta.setDisplayName("§6Recraft");
							
							ItemStack cogu2 = new ItemStack(Material.BROWN_MUSHROOM, 64);
							ItemMeta cogu2Meta = cogu2.getItemMeta();
							cogu2Meta.setDisplayName("§6Recraft");
							
							ItemStack compass = new ItemStack(Material.COMPASS);
							ItemMeta compassMeta = compass.getItemMeta();
							compassMeta.setDisplayName("§eBússola");
							
							compass.setItemMeta(compassMeta);
							pote.setItemMeta(poteMeta);
							cogu.setItemMeta(coguMeta);
							cogu2.setItemMeta(cogu2Meta);
							
							sopa.setItemMeta(sopaMeta);
							espadaPvP.setItemMeta(espadaPvPMeta);
							
							ItemStack capacete = new ItemStack(Material.IRON_HELMET);
							ItemMeta capaceteMeta = capacete.getItemMeta();
							capaceteMeta.setDisplayName("§eArmadura");
							
							ItemStack peitoral = new ItemStack(Material.IRON_CHESTPLATE);
							ItemMeta peitoralMeta = peitoral.getItemMeta();
							peitoralMeta.setDisplayName("§eArmadura");
							
							ItemStack calca = new ItemStack(Material.IRON_LEGGINGS);
							ItemMeta calcaMeta = calca.getItemMeta();
							calcaMeta.setDisplayName("§eArmadura");
							
							ItemStack bota = new ItemStack(Material.IRON_BOOTS);
							ItemMeta botaMeta = bota.getItemMeta();
							botaMeta.setDisplayName("§eArmadura");
							
							bota.setItemMeta(botaMeta);
							calca.setItemMeta(calcaMeta);
							peitoral.setItemMeta(peitoralMeta);
							capacete.setItemMeta(capaceteMeta);
							
							p.getInventory().setHelmet(capacete);
							p.getInventory().setChestplate(peitoral);
							p.getInventory().setLeggings(calca);
							p.getInventory().setBoots(bota);
		
							p.getInventory().setItem(1, sopa);
							p.getInventory().setItem(2, sopa);
							p.getInventory().setItem(3, sopa);
							p.getInventory().setItem(4, sopa);
							p.getInventory().setItem(5, sopa);
							p.getInventory().setItem(6, sopa);
							p.getInventory().setItem(7, sopa);
							p.getInventory().setItem(8, compass);
							p.getInventory().setItem(9, sopa);
							p.getInventory().setItem(10, sopa);
							p.getInventory().setItem(11, sopa);
							p.getInventory().setItem(12, sopa);
							p.getInventory().setItem(13, pote);
							p.getInventory().setItem(14, cogu);
							p.getInventory().setItem(15, cogu2);
							p.getInventory().setItem(16, sopa);
							p.getInventory().setItem(17, sopa);
							p.getInventory().setItem(18, sopa);
							p.getInventory().setItem(19, sopa);
							p.getInventory().setItem(20, sopa);
							p.getInventory().setItem(21, sopa);
							p.getInventory().setItem(22, sopa);
							p.getInventory().setItem(23, sopa);
							p.getInventory().setItem(24, sopa);
							p.getInventory().setItem(25, sopa);
							p.getInventory().setItem(26, sopa);
							p.getInventory().setItem(27, sopa);
							p.getInventory().setItem(28, sopa);
							p.getInventory().setItem(29, sopa);
							p.getInventory().setItem(30, sopa);
							p.getInventory().setItem(31, sopa);
							p.getInventory().setItem(32, sopa);
							p.getInventory().setItem(33, sopa);
							p.getInventory().setItem(34, sopa);
							p.getInventory().setItem(35, sopa);
								
								
							p.getInventory().setItem(0, espadaPvP);
						}else {
							p.sendMessage("§c§lKIT §fVocê não tem permissão para utilizar este kit.");
						}
					}else if (args[0].equalsIgnoreCase("viper")) {
						if (p.hasPermission("kit.viper")) {
							p.teleport(w.getSpawnLocation());
							viper.add(p.getName());
							kitName.put(p.getName(), "Viper");
							p.setAllowFlight(false);
							p.setFlying(false);
							kit.add(p);
							p.getInventory().clear();
							p.getInventory().setArmorContents(null);
							p.sendMessage("§6§lKIT §fVocê selecionou o kit §6Viper§f.");
							RTP.TeleportArenaRandom(p);
							ItemStack espadaPvP = new ItemStack(Material.IRON_SWORD);
							ItemMeta espadaPvPMeta = espadaPvP.getItemMeta();
							espadaPvPMeta.setDisplayName("§2Kit: §7(§2Viper§7)");
								
							ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
							ItemMeta sopaMeta = sopa.getItemMeta();
							sopaMeta.setDisplayName("§3Sopa");
							
							ItemStack pote = new ItemStack(Material.BOWL, 64);
							ItemMeta poteMeta = pote.getItemMeta();
							poteMeta.setDisplayName("§6Recraft");
							
							ItemStack cogu = new ItemStack(Material.RED_MUSHROOM, 64);
							ItemMeta coguMeta = cogu.getItemMeta();
							coguMeta.setDisplayName("§6Recraft");
							
							ItemStack cogu2 = new ItemStack(Material.BROWN_MUSHROOM, 64);
							ItemMeta cogu2Meta = cogu2.getItemMeta();
							cogu2Meta.setDisplayName("§6Recraft");
							
							ItemStack compass = new ItemStack(Material.COMPASS);
							ItemMeta compassMeta = compass.getItemMeta();
							compassMeta.setDisplayName("§eBússola");
							
							compass.setItemMeta(compassMeta);
							pote.setItemMeta(poteMeta);
							cogu.setItemMeta(coguMeta);
							cogu2.setItemMeta(cogu2Meta);
							
							sopa.setItemMeta(sopaMeta);
							espadaPvP.setItemMeta(espadaPvPMeta);
							
							ItemStack capacete = new ItemStack(Material.IRON_HELMET);
							ItemMeta capaceteMeta = capacete.getItemMeta();
							capaceteMeta.setDisplayName("§eArmadura");
							
							ItemStack peitoral = new ItemStack(Material.IRON_CHESTPLATE);
							ItemMeta peitoralMeta = peitoral.getItemMeta();
							peitoralMeta.setDisplayName("§eArmadura");
							
							ItemStack calca = new ItemStack(Material.IRON_LEGGINGS);
							ItemMeta calcaMeta = calca.getItemMeta();
							calcaMeta.setDisplayName("§eArmadura");
							
							ItemStack bota = new ItemStack(Material.IRON_BOOTS);
							ItemMeta botaMeta = bota.getItemMeta();
							botaMeta.setDisplayName("§eArmadura");
							
							bota.setItemMeta(botaMeta);
							calca.setItemMeta(calcaMeta);
							peitoral.setItemMeta(peitoralMeta);
							capacete.setItemMeta(capaceteMeta);
							
							p.getInventory().setHelmet(capacete);
							p.getInventory().setChestplate(peitoral);
							p.getInventory().setLeggings(calca);
							p.getInventory().setBoots(bota);
		
							p.getInventory().setItem(1, sopa);
							p.getInventory().setItem(2, sopa);
							p.getInventory().setItem(3, sopa);
							p.getInventory().setItem(4, sopa);
							p.getInventory().setItem(5, sopa);
							p.getInventory().setItem(6, sopa);
							p.getInventory().setItem(7, sopa);
							p.getInventory().setItem(8, compass);
							p.getInventory().setItem(9, sopa);
							p.getInventory().setItem(10, sopa);
							p.getInventory().setItem(11, sopa);
							p.getInventory().setItem(12, sopa);
							p.getInventory().setItem(13, pote);
							p.getInventory().setItem(14, cogu);
							p.getInventory().setItem(15, cogu2);
							p.getInventory().setItem(16, sopa);
							p.getInventory().setItem(17, sopa);
							p.getInventory().setItem(18, sopa);
							p.getInventory().setItem(19, sopa);
							p.getInventory().setItem(20, sopa);
							p.getInventory().setItem(21, sopa);
							p.getInventory().setItem(22, sopa);
							p.getInventory().setItem(23, sopa);
							p.getInventory().setItem(24, sopa);
							p.getInventory().setItem(25, sopa);
							p.getInventory().setItem(26, sopa);
							p.getInventory().setItem(27, sopa);
							p.getInventory().setItem(28, sopa);
							p.getInventory().setItem(29, sopa);
							p.getInventory().setItem(30, sopa);
							p.getInventory().setItem(31, sopa);
							p.getInventory().setItem(32, sopa);
							p.getInventory().setItem(33, sopa);
							p.getInventory().setItem(34, sopa);
							p.getInventory().setItem(35, sopa);
								
								
							p.getInventory().setItem(0, espadaPvP);
						}else {
							p.sendMessage("§c§lKIT §fVocê não tem permissão para utilizar este kit.");
						}
					}else if (args[0].equalsIgnoreCase("fireman")) {
						if (p.hasPermission("kit.fireman")) {
							p.teleport(w.getSpawnLocation());
							kitName.put(p.getName(), "Fireman");
							p.setAllowFlight(false);
							p.setFlying(false);
							kit.add(p);
							fireman.add(p.getName());
							p.getInventory().clear();
							p.getInventory().setArmorContents(null);
							p.sendMessage("§6§lKIT §fVocê selecionou o kit §6Fireman§f.");
							RTP.TeleportArenaRandom(p);
							ItemStack espadaPvP = new ItemStack(Material.IRON_SWORD);
							ItemMeta espadaPvPMeta = espadaPvP.getItemMeta();
							espadaPvPMeta.setDisplayName("§2Kit: §7(§2Fireman§7)");
	
							ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
							ItemMeta sopaMeta = sopa.getItemMeta();
							sopaMeta.setDisplayName("§3Sopa");
							
							ItemStack pote = new ItemStack(Material.BOWL, 64);
							ItemMeta poteMeta = pote.getItemMeta();
							poteMeta.setDisplayName("§6Recraft");
							
							ItemStack cogu = new ItemStack(Material.RED_MUSHROOM, 64);
							ItemMeta coguMeta = cogu.getItemMeta();
							coguMeta.setDisplayName("§6Recraft");
							
							ItemStack cogu2 = new ItemStack(Material.BROWN_MUSHROOM, 64);
							ItemMeta cogu2Meta = cogu2.getItemMeta();
							cogu2Meta.setDisplayName("§6Recraft");
							
							ItemStack compass = new ItemStack(Material.COMPASS);
							ItemMeta compassMeta = compass.getItemMeta();
							compassMeta.setDisplayName("§eBússola");
							
							compass.setItemMeta(compassMeta);
							pote.setItemMeta(poteMeta);
							cogu.setItemMeta(coguMeta);
							cogu2.setItemMeta(cogu2Meta);
							
							sopa.setItemMeta(sopaMeta);
							espadaPvP.setItemMeta(espadaPvPMeta);
							
							ItemStack capacete = new ItemStack(Material.IRON_HELMET);
							ItemMeta capaceteMeta = capacete.getItemMeta();
							capaceteMeta.setDisplayName("§eArmadura");
							
							ItemStack peitoral = new ItemStack(Material.IRON_CHESTPLATE);
							ItemMeta peitoralMeta = peitoral.getItemMeta();
							peitoralMeta.setDisplayName("§eArmadura");
							
							ItemStack calca = new ItemStack(Material.IRON_LEGGINGS);
							ItemMeta calcaMeta = calca.getItemMeta();
							calcaMeta.setDisplayName("§eArmadura");
							
							ItemStack bota = new ItemStack(Material.IRON_BOOTS);
							ItemMeta botaMeta = bota.getItemMeta();
							botaMeta.setDisplayName("§eArmadura");
							
							bota.setItemMeta(botaMeta);
							calca.setItemMeta(calcaMeta);
							peitoral.setItemMeta(peitoralMeta);
							capacete.setItemMeta(capaceteMeta);
							
							p.getInventory().setHelmet(capacete);
							p.getInventory().setChestplate(peitoral);
							p.getInventory().setLeggings(calca);
							p.getInventory().setBoots(bota);
		
							p.getInventory().setItem(1, sopa);
							p.getInventory().setItem(2, sopa);
							p.getInventory().setItem(3, sopa);
							p.getInventory().setItem(4, sopa);
							p.getInventory().setItem(5, sopa);
							p.getInventory().setItem(6, sopa);
							p.getInventory().setItem(7, sopa);
							p.getInventory().setItem(8, compass);
							p.getInventory().setItem(9, sopa);
							p.getInventory().setItem(10, sopa);
							p.getInventory().setItem(11, sopa);
							p.getInventory().setItem(12, sopa);
							p.getInventory().setItem(13, pote);
							p.getInventory().setItem(14, cogu);
							p.getInventory().setItem(15, cogu2);
							p.getInventory().setItem(16, sopa);
							p.getInventory().setItem(17, sopa);
							p.getInventory().setItem(18, sopa);
							p.getInventory().setItem(19, sopa);
							p.getInventory().setItem(20, sopa);
							p.getInventory().setItem(21, sopa);
							p.getInventory().setItem(22, sopa);
							p.getInventory().setItem(23, sopa);
							p.getInventory().setItem(24, sopa);
							p.getInventory().setItem(25, sopa);
							p.getInventory().setItem(26, sopa);
							p.getInventory().setItem(27, sopa);
							p.getInventory().setItem(28, sopa);
							p.getInventory().setItem(29, sopa);
							p.getInventory().setItem(30, sopa);
							p.getInventory().setItem(31, sopa);
							p.getInventory().setItem(32, sopa);
							p.getInventory().setItem(33, sopa);
							p.getInventory().setItem(34, sopa);
							p.getInventory().setItem(35, sopa);
								
								
							p.getInventory().setItem(0, espadaPvP);
						}else {
							p.sendMessage("§c§lKIT §fVoce n§o tem permissao para utilizar este kit.");
						}
					}else if (args[0].equalsIgnoreCase("snail")) {
						if (p.hasPermission("kit.snail")) {
							p.teleport(w.getSpawnLocation());
							snail.add(p.getName());
							kitName.put(p.getName(), "Snail");
							p.setAllowFlight(false);
							p.setFlying(false);
							kit.add(p);
							p.getInventory().clear();
							p.getInventory().setArmorContents(null);
							p.sendMessage("§6§lKIT §fVoce selecionou o kit §6Snail§f.");
							RTP.TeleportArenaRandom(p);
							ItemStack espadaPvP = new ItemStack(Material.IRON_SWORD);
							ItemMeta espadaPvPMeta = espadaPvP.getItemMeta();
							espadaPvPMeta.setDisplayName("§2Kit: §7(§2Snail§7)");
	
							ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
							ItemMeta sopaMeta = sopa.getItemMeta();
							sopaMeta.setDisplayName("§3Sopa");
							
							ItemStack pote = new ItemStack(Material.BOWL, 64);
							ItemMeta poteMeta = pote.getItemMeta();
							poteMeta.setDisplayName("§6Recraft");
							
							ItemStack cogu = new ItemStack(Material.RED_MUSHROOM, 64);
							ItemMeta coguMeta = cogu.getItemMeta();
							coguMeta.setDisplayName("§6Recraft");
							
							ItemStack cogu2 = new ItemStack(Material.BROWN_MUSHROOM, 64);
							ItemMeta cogu2Meta = cogu2.getItemMeta();
							cogu2Meta.setDisplayName("§6Recraft");
							
							ItemStack compass = new ItemStack(Material.COMPASS);
							ItemMeta compassMeta = compass.getItemMeta();
							compassMeta.setDisplayName("§eBússola");
							
							compass.setItemMeta(compassMeta);
							pote.setItemMeta(poteMeta);
							cogu.setItemMeta(coguMeta);
							cogu2.setItemMeta(cogu2Meta);
							
							sopa.setItemMeta(sopaMeta);
							espadaPvP.setItemMeta(espadaPvPMeta);
							
							ItemStack capacete = new ItemStack(Material.IRON_HELMET);
							ItemMeta capaceteMeta = capacete.getItemMeta();
							capaceteMeta.setDisplayName("§eArmadura");
							
							ItemStack peitoral = new ItemStack(Material.IRON_CHESTPLATE);
							ItemMeta peitoralMeta = peitoral.getItemMeta();
							peitoralMeta.setDisplayName("§eArmadura");
							
							ItemStack calca = new ItemStack(Material.IRON_LEGGINGS);
							ItemMeta calcaMeta = calca.getItemMeta();
							calcaMeta.setDisplayName("§eArmadura");
							
							ItemStack bota = new ItemStack(Material.IRON_BOOTS);
							ItemMeta botaMeta = bota.getItemMeta();
							botaMeta.setDisplayName("§eArmadura");
							
							bota.setItemMeta(botaMeta);
							calca.setItemMeta(calcaMeta);
							peitoral.setItemMeta(peitoralMeta);
							capacete.setItemMeta(capaceteMeta);
							
							p.getInventory().setHelmet(capacete);
							p.getInventory().setChestplate(peitoral);
							p.getInventory().setLeggings(calca);
							p.getInventory().setBoots(bota);
		
							p.getInventory().setItem(1, sopa);
							p.getInventory().setItem(2, sopa);
							p.getInventory().setItem(3, sopa);
							p.getInventory().setItem(4, sopa);
							p.getInventory().setItem(5, sopa);
							p.getInventory().setItem(6, sopa);
							p.getInventory().setItem(7, sopa);
							p.getInventory().setItem(8, compass);
							p.getInventory().setItem(9, sopa);
							p.getInventory().setItem(10, sopa);
							p.getInventory().setItem(11, sopa);
							p.getInventory().setItem(12, sopa);
							p.getInventory().setItem(13, pote);
							p.getInventory().setItem(14, cogu);
							p.getInventory().setItem(15, cogu2);
							p.getInventory().setItem(16, sopa);
							p.getInventory().setItem(17, sopa);
							p.getInventory().setItem(18, sopa);
							p.getInventory().setItem(19, sopa);
							p.getInventory().setItem(20, sopa);
							p.getInventory().setItem(21, sopa);
							p.getInventory().setItem(22, sopa);
							p.getInventory().setItem(23, sopa);
							p.getInventory().setItem(24, sopa);
							p.getInventory().setItem(25, sopa);
							p.getInventory().setItem(26, sopa);
							p.getInventory().setItem(27, sopa);
							p.getInventory().setItem(28, sopa);
							p.getInventory().setItem(29, sopa);
							p.getInventory().setItem(30, sopa);
							p.getInventory().setItem(31, sopa);
							p.getInventory().setItem(32, sopa);
							p.getInventory().setItem(33, sopa);
							p.getInventory().setItem(34, sopa);
							p.getInventory().setItem(35, sopa);
								
								
							p.getInventory().setItem(0, espadaPvP);
						}else {
							p.sendMessage("§c§lKIT §fVoce nao tem permissao para utilizar este kit.");
						}
					}else if (args[0].equalsIgnoreCase("confuser")) {
						if (p.hasPermission("kit.confuser")) {
							p.teleport(w.getSpawnLocation());
							kitName.put(p.getName(), "Confuser");
							p.setAllowFlight(false);
							p.setFlying(false);
							kit.add(p);
							confuser.add(p.getName());
							p.getInventory().clear();
							p.getInventory().setArmorContents(null);
							p.sendMessage("§6§lKIT §fVocê selecionou o kit §6Confuser§f.");
							RTP.TeleportArenaRandom(p);
							ItemStack espadaPvP = new ItemStack(Material.IRON_SWORD);
							ItemMeta espadaPvPMeta = espadaPvP.getItemMeta();
							espadaPvPMeta.setDisplayName("§2Kit: §7(§2Confuser§7)");
	
							ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
							ItemMeta sopaMeta = sopa.getItemMeta();
							sopaMeta.setDisplayName("§3Sopa");
							
							ItemStack pote = new ItemStack(Material.BOWL, 64);
							ItemMeta poteMeta = pote.getItemMeta();
							poteMeta.setDisplayName("§6Recraft");
							
							ItemStack cogu = new ItemStack(Material.RED_MUSHROOM, 64);
							ItemMeta coguMeta = cogu.getItemMeta();
							coguMeta.setDisplayName("§6Recraft");
							
							ItemStack cogu2 = new ItemStack(Material.BROWN_MUSHROOM, 64);
							ItemMeta cogu2Meta = cogu2.getItemMeta();
							cogu2Meta.setDisplayName("§6Recraft");
							
							ItemStack compass = new ItemStack(Material.COMPASS);
							ItemMeta compassMeta = compass.getItemMeta();
							compassMeta.setDisplayName("§eBússola");
							
							compass.setItemMeta(compassMeta);
							pote.setItemMeta(poteMeta);
							cogu.setItemMeta(coguMeta);
							cogu2.setItemMeta(cogu2Meta);
							
							sopa.setItemMeta(sopaMeta);
							espadaPvP.setItemMeta(espadaPvPMeta);
							
							ItemStack capacete = new ItemStack(Material.IRON_HELMET);
							ItemMeta capaceteMeta = capacete.getItemMeta();
							capaceteMeta.setDisplayName("§eArmadura");
							
							ItemStack peitoral = new ItemStack(Material.IRON_CHESTPLATE);
							ItemMeta peitoralMeta = peitoral.getItemMeta();
							peitoralMeta.setDisplayName("§eArmadura");
							
							ItemStack calca = new ItemStack(Material.IRON_LEGGINGS);
							ItemMeta calcaMeta = calca.getItemMeta();
							calcaMeta.setDisplayName("§eArmadura");
							
							ItemStack bota = new ItemStack(Material.IRON_BOOTS);
							ItemMeta botaMeta = bota.getItemMeta();
							botaMeta.setDisplayName("§eArmadura");
							
							bota.setItemMeta(botaMeta);
							calca.setItemMeta(calcaMeta);
							peitoral.setItemMeta(peitoralMeta);
							capacete.setItemMeta(capaceteMeta);
							
							p.getInventory().setHelmet(capacete);
							p.getInventory().setChestplate(peitoral);
							p.getInventory().setLeggings(calca);
							p.getInventory().setBoots(bota);
		
							p.getInventory().setItem(1, sopa);
							p.getInventory().setItem(2, sopa);
							p.getInventory().setItem(3, sopa);
							p.getInventory().setItem(4, sopa);
							p.getInventory().setItem(5, sopa);
							p.getInventory().setItem(6, sopa);
							p.getInventory().setItem(7, sopa);
							p.getInventory().setItem(8, compass);
							p.getInventory().setItem(9, sopa);
							p.getInventory().setItem(10, sopa);
							p.getInventory().setItem(11, sopa);
							p.getInventory().setItem(12, sopa);
							p.getInventory().setItem(13, pote);
							p.getInventory().setItem(14, cogu);
							p.getInventory().setItem(15, cogu2);
							p.getInventory().setItem(16, sopa);
							p.getInventory().setItem(17, sopa);
							p.getInventory().setItem(18, sopa);
							p.getInventory().setItem(19, sopa);
							p.getInventory().setItem(20, sopa);
							p.getInventory().setItem(21, sopa);
							p.getInventory().setItem(22, sopa);
							p.getInventory().setItem(23, sopa);
							p.getInventory().setItem(24, sopa);
							p.getInventory().setItem(25, sopa);
							p.getInventory().setItem(26, sopa);
							p.getInventory().setItem(27, sopa);
							p.getInventory().setItem(28, sopa);
							p.getInventory().setItem(29, sopa);
							p.getInventory().setItem(30, sopa);
							p.getInventory().setItem(31, sopa);
							p.getInventory().setItem(32, sopa);
							p.getInventory().setItem(33, sopa);
							p.getInventory().setItem(34, sopa);
							p.getInventory().setItem(35, sopa);
								
								
							p.getInventory().setItem(0, espadaPvP);
						}else {
							p.sendMessage("§c§lKIT §fVocê não tem permissão para utilizar este kit.");
						}
					}else if (args[0].equalsIgnoreCase("stomper")) {
						if (p.hasPermission("kit.stomper")) {
							p.teleport(w.getSpawnLocation());
							kitName.put(p.getName(), "Stomper");
							p.setAllowFlight(false);
							p.setFlying(false);
							kit.add(p);
							stomper.add(p.getName());
							p.getInventory().clear();
							p.getInventory().setArmorContents(null);
							p.sendMessage("§6§lKIT §fVocê selecionou o kit §6Stomper§f.");
							RTP.TeleportArenaRandom(p);
							ItemStack espadaPvP = new ItemStack(Material.IRON_SWORD);
							ItemMeta espadaPvPMeta = espadaPvP.getItemMeta();
							espadaPvPMeta.setDisplayName("§2Kit: §7(§2Stomper§7)");
	
							ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
							ItemMeta sopaMeta = sopa.getItemMeta();
							sopaMeta.setDisplayName("§3Sopa");
							
							ItemStack pote = new ItemStack(Material.BOWL, 64);
							ItemMeta poteMeta = pote.getItemMeta();
							poteMeta.setDisplayName("§6Recraft");
							
							ItemStack cogu = new ItemStack(Material.RED_MUSHROOM, 64);
							ItemMeta coguMeta = cogu.getItemMeta();
							coguMeta.setDisplayName("§6Recraft");
							
							ItemStack cogu2 = new ItemStack(Material.BROWN_MUSHROOM, 64);
							ItemMeta cogu2Meta = cogu2.getItemMeta();
							cogu2Meta.setDisplayName("§6Recraft");
							
							ItemStack compass = new ItemStack(Material.COMPASS);
							ItemMeta compassMeta = compass.getItemMeta();
							compassMeta.setDisplayName("§eBússola");
							
							compass.setItemMeta(compassMeta);
							pote.setItemMeta(poteMeta);
							cogu.setItemMeta(coguMeta);
							cogu2.setItemMeta(cogu2Meta);
							sopa.setItemMeta(sopaMeta);
							espadaPvP.setItemMeta(espadaPvPMeta);
							
							ItemStack capacete = new ItemStack(Material.IRON_HELMET);
							ItemMeta capaceteMeta = capacete.getItemMeta();
							capaceteMeta.setDisplayName("§eArmadura");
							
							ItemStack peitoral = new ItemStack(Material.IRON_CHESTPLATE);
							ItemMeta peitoralMeta = peitoral.getItemMeta();
							peitoralMeta.setDisplayName("§eArmadura");
							
							ItemStack calca = new ItemStack(Material.IRON_LEGGINGS);
							ItemMeta calcaMeta = calca.getItemMeta();
							calcaMeta.setDisplayName("§eArmadura");
							
							ItemStack bota = new ItemStack(Material.IRON_BOOTS);
							ItemMeta botaMeta = bota.getItemMeta();
							botaMeta.setDisplayName("§eArmadura");
							
							bota.setItemMeta(botaMeta);
							calca.setItemMeta(calcaMeta);
							peitoral.setItemMeta(peitoralMeta);
							capacete.setItemMeta(capaceteMeta);
							
							p.getInventory().setHelmet(capacete);
							p.getInventory().setChestplate(peitoral);
							p.getInventory().setLeggings(calca);
							p.getInventory().setBoots(bota);
		
							p.getInventory().setItem(1, sopa);
							p.getInventory().setItem(2, sopa);
							p.getInventory().setItem(3, sopa);
							p.getInventory().setItem(4, sopa);
							p.getInventory().setItem(5, sopa);
							p.getInventory().setItem(6, sopa);
							p.getInventory().setItem(7, sopa);
							p.getInventory().setItem(8, compass);
							p.getInventory().setItem(9, sopa);
							p.getInventory().setItem(10, sopa);
							p.getInventory().setItem(11, sopa);
							p.getInventory().setItem(12, sopa);
							p.getInventory().setItem(13, pote);
							p.getInventory().setItem(14, cogu);
							p.getInventory().setItem(15, cogu2);
							p.getInventory().setItem(16, sopa);
							p.getInventory().setItem(17, sopa);
							p.getInventory().setItem(18, sopa);
							p.getInventory().setItem(19, sopa);
							p.getInventory().setItem(20, sopa);
							p.getInventory().setItem(21, sopa);
							p.getInventory().setItem(22, sopa);
							p.getInventory().setItem(23, sopa);
							p.getInventory().setItem(24, sopa);
							p.getInventory().setItem(25, sopa);
							p.getInventory().setItem(26, sopa);
							p.getInventory().setItem(27, sopa);
							p.getInventory().setItem(28, sopa);
							p.getInventory().setItem(29, sopa);
							p.getInventory().setItem(30, sopa);
							p.getInventory().setItem(31, sopa);
							p.getInventory().setItem(32, sopa);
							p.getInventory().setItem(33, sopa);
							p.getInventory().setItem(34, sopa);
							p.getInventory().setItem(35, sopa);
								
								
							p.getInventory().setItem(0, espadaPvP);
						}else {
							p.sendMessage("§c§lKIT §fVocê não tem permissão para utilizar este kit.");
						}
					}else if (args[0].equalsIgnoreCase("poseidon")) {
						if (p.hasPermission("kit.poseidon")) {
							p.teleport(w.getSpawnLocation());
							kitName.put(p.getName(), "Poseidon");
							p.setAllowFlight(false);
							p.setFlying(false);
							kit.add(p);
							poseidon.add(p.getName());
							p.getInventory().clear();
							p.getInventory().setArmorContents(null);
							p.sendMessage("§6§lKIT §fVocê selecionou o kit §6Poseidon§f.");
							RTP.TeleportArenaRandom(p);
							ItemStack espadaPvP = new ItemStack(Material.IRON_SWORD);
							ItemMeta espadaPvPMeta = espadaPvP.getItemMeta();
							espadaPvPMeta.setDisplayName("§2Kit: §7(§2Poseidon§7)");
	
							ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
							ItemMeta sopaMeta = sopa.getItemMeta();
							sopaMeta.setDisplayName("§3Sopa");
							
							ItemStack pote = new ItemStack(Material.BOWL, 64);
							ItemMeta poteMeta = pote.getItemMeta();
							poteMeta.setDisplayName("§6Recraft");
							
							ItemStack cogu = new ItemStack(Material.RED_MUSHROOM, 64);
							ItemMeta coguMeta = cogu.getItemMeta();
							coguMeta.setDisplayName("§6Recraft");
							
							ItemStack cogu2 = new ItemStack(Material.BROWN_MUSHROOM, 64);
							ItemMeta cogu2Meta = cogu2.getItemMeta();
							cogu2Meta.setDisplayName("§6Recraft");

							ItemStack compass = new ItemStack(Material.COMPASS);
							ItemMeta compassMeta = compass.getItemMeta();
							compassMeta.setDisplayName("§eBússola");
							
							compass.setItemMeta(compassMeta);
							pote.setItemMeta(poteMeta);
							cogu.setItemMeta(coguMeta);
							cogu2.setItemMeta(cogu2Meta);
							
							sopa.setItemMeta(sopaMeta);
							espadaPvP.setItemMeta(espadaPvPMeta);
							
							ItemStack capacete = new ItemStack(Material.IRON_HELMET);
							ItemMeta capaceteMeta = capacete.getItemMeta();
							capaceteMeta.setDisplayName("§eArmadura");
							
							ItemStack peitoral = new ItemStack(Material.IRON_CHESTPLATE);
							ItemMeta peitoralMeta = peitoral.getItemMeta();
							peitoralMeta.setDisplayName("§eArmadura");
							
							ItemStack calca = new ItemStack(Material.IRON_LEGGINGS);
							ItemMeta calcaMeta = calca.getItemMeta();
							calcaMeta.setDisplayName("§eArmadura");
							
							ItemStack bota = new ItemStack(Material.IRON_BOOTS);
							ItemMeta botaMeta = bota.getItemMeta();
							botaMeta.setDisplayName("§eArmadura");
							
							bota.setItemMeta(botaMeta);
							calca.setItemMeta(calcaMeta);
							peitoral.setItemMeta(peitoralMeta);
							capacete.setItemMeta(capaceteMeta);
							
							p.getInventory().setHelmet(capacete);
							p.getInventory().setChestplate(peitoral);
							p.getInventory().setLeggings(calca);
							p.getInventory().setBoots(bota);
		
							p.getInventory().setItem(1, sopa);
							p.getInventory().setItem(2, sopa);
							p.getInventory().setItem(3, sopa);
							p.getInventory().setItem(4, sopa);
							p.getInventory().setItem(5, sopa);
							p.getInventory().setItem(6, sopa);
							p.getInventory().setItem(7, sopa);
							p.getInventory().setItem(8, compass);
							p.getInventory().setItem(9, sopa);
							p.getInventory().setItem(10, sopa);
							p.getInventory().setItem(11, sopa);
							p.getInventory().setItem(12, sopa);
							p.getInventory().setItem(13, pote);
							p.getInventory().setItem(14, cogu);
							p.getInventory().setItem(15, cogu2);
							p.getInventory().setItem(16, sopa);
							p.getInventory().setItem(17, sopa);
							p.getInventory().setItem(18, sopa);
							p.getInventory().setItem(19, sopa);
							p.getInventory().setItem(20, sopa);
							p.getInventory().setItem(21, sopa);
							p.getInventory().setItem(22, sopa);
							p.getInventory().setItem(23, sopa);
							p.getInventory().setItem(24, sopa);
							p.getInventory().setItem(25, sopa);
							p.getInventory().setItem(26, sopa);
							p.getInventory().setItem(27, sopa);
							p.getInventory().setItem(28, sopa);
							p.getInventory().setItem(29, sopa);
							p.getInventory().setItem(30, sopa);
							p.getInventory().setItem(31, sopa);
							p.getInventory().setItem(32, sopa);
							p.getInventory().setItem(33, sopa);
							p.getInventory().setItem(34, sopa);
							p.getInventory().setItem(35, sopa);
								
								
							p.getInventory().setItem(0, espadaPvP);
						}else {
						}
					}else if (args[0].equalsIgnoreCase("boxer")) {
						if (p.hasPermission("kit.boxer")) {
							p.teleport(w.getSpawnLocation());
							kitName.put(p.getName(), "Boxer");
							p.setAllowFlight(false);
							p.setFlying(false);
							kit.add(p);
							p.getInventory().clear();
							p.getInventory().setArmorContents(null);
							RTP.TeleportArenaRandom(p);
							ItemStack espadaPvP = new ItemStack(Material.QUARTZ);
							ItemMeta espadaPvPMeta = espadaPvP.getItemMeta();
							espadaPvPMeta.setDisplayName("§2Kit: §7(§2Boxer§7)");
	
							ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
							ItemMeta sopaMeta = sopa.getItemMeta();
							sopaMeta.setDisplayName("§3Sopa");
							
							ItemStack glad = new ItemStack(Material.MUSHROOM_SOUP);
							ItemMeta gladMeta = glad.getItemMeta();
							gladMeta.setDisplayName("§6Gladiator");
							
							ItemStack pote = new ItemStack(Material.BOWL, 64);
							ItemMeta poteMeta = pote.getItemMeta();
							poteMeta.setDisplayName("§6Recraft");
							
							ItemStack cogu = new ItemStack(Material.RED_MUSHROOM, 64);
							ItemMeta coguMeta = cogu.getItemMeta();
							coguMeta.setDisplayName("§6Recraft");
							
							ItemStack cogu2 = new ItemStack(Material.BROWN_MUSHROOM, 64);
							ItemMeta cogu2Meta = cogu2.getItemMeta();
							cogu2Meta.setDisplayName("§6Recraft");

							ItemStack compass = new ItemStack(Material.COMPASS);
							ItemMeta compassMeta = compass.getItemMeta();
							compassMeta.setDisplayName("§eBússola");
							
							compass.setItemMeta(compassMeta);
							pote.setItemMeta(poteMeta);
							cogu.setItemMeta(coguMeta);
							cogu2.setItemMeta(cogu2Meta);
							glad.setItemMeta(gladMeta);
							sopa.setItemMeta(sopaMeta);
							espadaPvP.setItemMeta(espadaPvPMeta);
							
							ItemStack capacete = new ItemStack(Material.IRON_HELMET);
							ItemMeta capaceteMeta = capacete.getItemMeta();
							capaceteMeta.setDisplayName("§eArmadura");
							
							ItemStack peitoral = new ItemStack(Material.IRON_CHESTPLATE);
							ItemMeta peitoralMeta = peitoral.getItemMeta();
							peitoralMeta.setDisplayName("§eArmadura");
							
							ItemStack calca = new ItemStack(Material.IRON_LEGGINGS);
							ItemMeta calcaMeta = calca.getItemMeta();
							calcaMeta.setDisplayName("§eArmadura");
							
							ItemStack bota = new ItemStack(Material.IRON_BOOTS);
							ItemMeta botaMeta = bota.getItemMeta();
							botaMeta.setDisplayName("§eArmadura");
							
							bota.setItemMeta(botaMeta);
							calca.setItemMeta(calcaMeta);
							peitoral.setItemMeta(peitoralMeta);
							capacete.setItemMeta(capaceteMeta);
							
							p.getInventory().setHelmet(capacete);
							p.getInventory().setChestplate(peitoral);
							p.getInventory().setLeggings(calca);
							p.getInventory().setBoots(bota);
		
							p.getInventory().setItem(1, sopa);
							p.getInventory().setItem(2, sopa);
							p.getInventory().setItem(3, sopa);
							p.getInventory().setItem(4, sopa);
							p.getInventory().setItem(5, sopa);
							p.getInventory().setItem(6, sopa);
							p.getInventory().setItem(7, sopa);
							p.getInventory().setItem(8, compass);
							p.getInventory().setItem(9, sopa);
							p.getInventory().setItem(10, sopa);
							p.getInventory().setItem(11, sopa);
							p.getInventory().setItem(12, sopa);
							p.getInventory().setItem(13, pote);
							p.getInventory().setItem(14, cogu);
							p.getInventory().setItem(15, cogu2);
							p.getInventory().setItem(16, sopa);
							p.getInventory().setItem(17, sopa);
							p.getInventory().setItem(18, sopa);
							p.getInventory().setItem(19, sopa);
							p.getInventory().setItem(20, sopa);
							p.getInventory().setItem(21, sopa);
							p.getInventory().setItem(22, sopa);
							p.getInventory().setItem(23, sopa);
							p.getInventory().setItem(24, sopa);
							p.getInventory().setItem(25, sopa);
							p.getInventory().setItem(26, sopa);
							p.getInventory().setItem(27, sopa);
							p.getInventory().setItem(28, sopa);
							p.getInventory().setItem(29, sopa);
							p.getInventory().setItem(30, sopa);
							p.getInventory().setItem(31, sopa);
							p.getInventory().setItem(32, sopa);
							p.getInventory().setItem(33, sopa);
							p.getInventory().setItem(34, sopa);
							p.getInventory().setItem(35, sopa);
								
								
							p.getInventory().setItem(0, espadaPvP);
							
						}else {
							p.sendMessage("§c§lKIT §fVoce n§o tem permissao para utilizar este kit.");
						}
					}else {
						p.sendMessage("§c§lKIT §fKit nao encontrado.");
					}
				}else {
					p.sendMessage("§c§lKIT §fVoce ja esta utilizando um kit.");
				}
			}
		}
		return true;
	}
	public static void setPlayerKitSecundario(Player player, String kit) {
		kit2Name.put(player.getName(), kit);
	}
	@EventHandler
	public void onPlayerStomp(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player p = (Player)e.getEntity();
		 if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
			 if (stomper.contains(p.getName())) {
				 for (Entity ent : p.getNearbyEntities(2.0D, 2.0D, 2.0D)) {
					 if ((ent instanceof Player)) {
						 Player plr = (Player)ent;
						 if (kit.contains(plr)) {
							 if (e.getDamage() <= 4.0D) {
								 e.setCancelled(true);
								 return;
							 }
							 if (plr.isSneaking()) {
									 plr.damage(6.0D, p);
							 }else {
									 plr.damage(e.getDamage(), p);
									 plr.damage(p.getFallDistance());
							 }
						 }
					 }
				 }
				 e.getEntity().getLocation().getWorld().playSound(e.getEntity().getLocation(), Sound.ANVIL_LAND, 1.0F, 1.0F);
				 e.setDamage(4.0D);
			 }
		 }
	}
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = (Player) e.getPlayer();
		if (p.getGameMode() != GameMode.CREATIVE) {
				if (e.getItemDrop().getItemStack().getType() == Material.STONE_SWORD) {
					e.setCancelled(true);
				}
				if (e.getItemDrop().getItemStack().getType() == Material.BOW) {
					e.setCancelled(true);
				}
				if (e.getItemDrop().getItemStack().getType() == Material.ARROW) {
					e.setCancelled(true);
				}
				if (e.getItemDrop().getItemStack().getType() == Material.FIREWORK) {
					e.setCancelled(true);
				}
				if (e.getItemDrop().getItemStack().getType() == Material.GOLD_AXE) {
					e.setCancelled(true);
				}
				if (e.getItemDrop().getItemStack().getType() == Material.IRON_SWORD) {
					e.setCancelled(true);
				}
				if (e.getItemDrop().getItemStack().getType() == Material.DIAMOND_SWORD) {
					e.setCancelled(true);
				}
				if (e.getItemDrop().getItemStack().getType() == Material.CARROT_ITEM) {
					e.setCancelled(true);
				}
				if (e.getItemDrop().getItemStack().getType() == Material.COMPASS) {
					e.setCancelled(true);
				}
		}
	}
	@EventHandler
	public void onDano(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getEntity();
		Player t = (Player) e.getDamager();
		
			
		if (p instanceof Player) {
			if (!net.helix.pvp.kit.All.kit.contains(p)) {
				e.setCancelled(true);
			}else if (!net.helix.pvp.kit.All.kit.contains(t)) {
				e.setCancelled(true);
			}else if (p.isFlying()) {
				e.setCancelled(true);
				t.sendMessage("§c§lDAMAGE §fEste jogador esta modo voar.");
			}
		}
	}
	@EventHandler
	public void onThorCancelDamage(EntityDamageEvent e) {
		Player p = (Player) e.getEntity();
		if (p instanceof Player) {
			if (!kit.contains(p)) {
				if (e.getCause() == DamageCause.LIGHTNING) {
					e.setCancelled(true);
				}
			}
			if (fireman.contains(p)) {
				if (e.getCause() == DamageCause.LAVA || e.getCause() == DamageCause.FIRE_TICK || e.getCause() == DamageCause.FIRE) {
					e.setCancelled(true);
				}
			}
			if (e.getCause() == DamageCause.FIRE_TICK) {
				if (!kit.contains(p)) {
					e.setCancelled(true);
				}
			}
		}
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDanoNinja(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getDamager();
		Player t = (Player) e.getEntity();
		if (p instanceof Player) {
			if (t instanceof Player) {
				if (kit.contains(t)) {
					if (ninja.contains(p.getName())) {
						ninjaPlayer.put(p, t);
					}else if (ajnin.contains(p.getName())) {
						ajninPlayer.put(p, t);
					}else if (anchor.contains(t.getName())) {
						p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 1.0F, 1.0F);
						t.setVelocity(new Vector());
						Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.plugin, new Runnable() {
							public void run() {
								t.setVelocity(new Vector());
							}
						}, 1L);
					}else if (anchor.contains(p.getName())) {
						p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 1.0F, 1.0F);
						t.setVelocity(new Vector());
						Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.plugin, new Runnable() {
							public void run() {
								t.setVelocity(new Vector());
							}
						}, 1L);
					}else if (viper.contains(p.getName())) {
						Random rand = new Random();
						int percent = rand.nextInt(100);
						if (percent <= 33) {
							t.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 160, 1));
						}
					}else if (fireman.contains(p.getName())) {
						 {
							Random rand = new Random();
							int percent = rand.nextInt(100);
							if (percent <= 27) {
								t.setFireTicks(50);
							}
						}
					}else if (snail.contains(p.getName())) {
						{
							Random rand = new Random();
							int percent = rand.nextInt(100);
							if (percent <= 33) {
								t.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 140, 0));
							}
						}
					}else if (confuser.contains(p.getName())) {
						{
							Random rand = new Random();
							int percent = rand.nextInt(100);
							if (percent <= 26) {
								t.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 300, 0));
							}
						}
					}
					
				}
			}
		}
	}
	@EventHandler
	public void onNinja(PlayerToggleSneakEvent e) {
		Player p = e.getPlayer();
		if (p.isSneaking()) {
			if (ninja.contains(p.getName())) {
				if (!ninjaDelay.contains(p.getName())) {
					Player t = ninjaPlayer.get(p);
					if (t == null) {
						p.sendMessage("§6§lNINJA §fJogador esta offline");
						return;
					}
					p.teleport(t.getLocation());
					p.sendMessage("§6§lNINJA §fVoce foi teleportado para §6" + t.getName() + "§f.");
					ninjaDelay.add(p.getName());
					Bukkit.getScheduler().scheduleSyncDelayedTask(net.helix.pvp.Main.plugin, new Runnable() {
						public void run() {
							ninjaDelay.remove(p.getName());
							p.sendMessage("§e§lNINJA §fSeu kit saiu do cooldown!");
						}
					}, 10*20);
				}else {
					p.sendMessage("§c§lNINJA §fSeu kit está em cooldown, aguarde!");
				}
			}else if (ajnin.contains(p.getName())) {
				if (!ajninDelay.contains(p.getName())) {						
					Player t = ajninPlayer.get(p);
					t.teleport(p.getLocation());
					p.sendMessage("§6§lAJNIN §fVoce puxou o jogador §6" + t.getName() + "§f.");
					ajninDelay.add(p.getName());
					Bukkit.getScheduler().scheduleSyncDelayedTask(net.helix.pvp.Main.plugin, new Runnable() {
						public void run() {
							ajninDelay.remove(p.getName());
							p.sendMessage("§e§lAJNIN §fSeu kit saiu do cooldown!");
						}
					}, 10*20);
				}else {
					p.sendMessage("§c§lAJNIN §fSeu kit está em cooldown, aguarde!");
				}
			}
		}
		if (kit2Name.get(p.getName()) == "Ninja") {
			if (!ninjaDelay.contains(p.getName())) {
				Player t = ninjaPlayer.get(p);
				p.teleport(t.getLocation());
				p.sendMessage("§6§lNINJA §fVoc§ foi teleportado para §6" + t.getName() + "§f.");
				ninjaDelay.add(p.getName());
				Bukkit.getScheduler().scheduleSyncDelayedTask(net.helix.pvp.Main.plugin, new Runnable() {
					public void run() {
						ninjaDelay.remove(p.getName());
						p.sendMessage("§e§lNINJA §fSeu kit saiu do cooldown!");
					}
				}, 10*20);
			}else {
				p.sendMessage("§c§lNINJA §fSeu kit está em cooldown, aguarde!");
			}
		}else if (kit2Name.get(p.getName()) == "Ajnin") {
			if (!ajninDelay.contains(p.getName())) {
				Player t = ajninPlayer.get(p);
				if (t == null) {
					p.sendMessage("§6§lNINJA §fJogador esta offline");
					return;
				}
				t.teleport(p.getLocation());
				p.sendMessage("§6§lAJNIN §fVoc§ puxou o jogador §6" + t.getName() + "§f.");
				ajninDelay.add(p.getName());
				Bukkit.getScheduler().scheduleSyncDelayedTask(net.helix.pvp.Main.plugin, new Runnable() {
					public void run() {
						ajninDelay.remove(p.getName());
						p.sendMessage("§e§lAJNIN §fSeu kit saiu do cooldown!");
					}
				}, 10*20);
			}else {
				p.sendMessage("§c§lAJNIN §fSeu kit está em cooldown, aguarde!");
			}
		}
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onKangarro(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Vector vector = p.getEyeLocation().getDirection();
		
		if (p.getItemInHand().getType().equals(Material.FIREWORK)) {
			e.setCancelled(true);
			if (!kDelay.contains(p.getName())) {
				kDelay.add(p.getName());
				if (!p.isSneaking()) {
					p.setFallDistance(-1.0F);
				    vector.multiply(0.5F);
					vector.setY(1.0D);
					p.setVelocity(vector);
				}else {
					p.setFallDistance(-1.0F);
					vector.multiply(2.3F);
					vector.setY(0.5D);
					p.setVelocity(vector);
				}
			}
		}else if (p.getItemInHand().getType().equals(Material.GOLD_AXE)) {
			if (thor.contains(p.getName())) {
				if (!thorDelay.contains(p.getName())) {
					Block b = e.getClickedBlock();
					
					World w = p.getWorld();
					
					Location loc = w.getHighestBlockAt(b.getLocation()).getLocation();
					p.getWorld().strikeLightning(loc);
					p.getItemInHand().setDurability((short)0);
					thorDelay.add(p.getName());
					Bukkit.getScheduler().scheduleAsyncDelayedTask(net.helix.pvp.Main.plugin, new Runnable() {
						public void run() {
							thorDelay.remove(p.getName());
							p.sendMessage("§e§lTHOR §fSeu kit nao esta mais em cooldown.");
						}
					}, 13*10);
				}else {
					p.sendMessage("§c§lTHOR §fSeu kit esta em cooldown.");
				}
			}
		}else if (p.getItemInHand().getType().equals(Material.STONE_SWORD)) {
			if (kitName.get(p.getName()) == "PvP") {
				p.getInventory().getChestplate().setDurability((short)3);
			}
		}
	}
	@EventHandler
	public void removertempo(PlayerMoveEvent event) {
	Player p = event.getPlayer();
	Block b = p.getLocation().getBlock();
	if (kDelay.contains(p.getName())) {
		if ((b.getType() != Material.AIR) || (b.getRelative(BlockFace.DOWN).getType() != Material.AIR)) {
			kDelay.remove(p.getName());
		}
	}
	if (poseidon.contains(p.getName())) {
		if (b.getType() == Material.WATER || (b.getType() == Material.STATIONARY_WATER)) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 7*20, 1));
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 9*20, 2));
		}
	}
	if (b.getType() == Material.WATER || (b.getType() == Material.STATIONARY_WATER)) {
		if (!kit.contains(p)) {
			if (p.getWorld().getName().equalsIgnoreCase("world")) {
				World spawn = Bukkit.getServer().getWorld("Lobby");
				
				p.teleport(spawn.getSpawnLocation());
			}
		}
	}
	}
	@EventHandler
	public void onFireman(EntityDamageEvent e) {
		Player p = (Player) e.getEntity();
		if (p instanceof Player) {
			if (fireman.contains(p.getName()) && (All.kitName.get(p.getName())) == "Fireman") {
				if (e.getCause() == DamageCause.FIRE || (e.getCause() == DamageCause.LAVA || (e.getCause() == DamageCause.FIRE_TICK))) {
					e.setCancelled(true);
				}
			}
			if (!kit.contains(p)) {
				if (e.getCause() == DamageCause.FALL) {
					e.setCancelled(true);
				}
			}
			if (e.getCause() == DamageCause.FALL) {
				if (kitName.get(p.getName()) == "Kangaroo") {
					if (e.getDamage() >= 7.0D) {
						e.setDamage(7.0D);
					}
				}
			}
		}
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		kit.remove(p);
		kDelay.remove(p.getName());
		ninja.remove(p.getName());
		ninjaDelay.remove(p.getName());
		ninjaPlayer.clear();
		ajnin.remove(p.getName());
		ajninDelay.remove(p.getName());
		ajninPlayer.clear();
		q.remove(p.getName());
		kitName.put(p.getName(), null);
		thor.remove(p.getName());
		thorDelay.remove(p.getName());
		anchor.remove(p.getName());
		viper.remove(p.getName());
		fireman.remove(p.getName());
		snail.remove(p.getName());
		confuser.remove(p.getName());
		stomper.remove(p.getName());
		poseidon.remove(p.getName());
		kit2Name.put(p.getName(), null);
	}
	@EventHandler
	public void ondeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if (p instanceof Player) {
			kit.remove(p);
			kDelay.remove(p.getName());
			ninja.remove(p.getName());
			ninjaDelay.remove(p.getName());
			ninjaPlayer.clear();
			ajnin.remove(p.getName());
			ajninDelay.remove(p.getName());
			ajninPlayer.clear();
			q.remove(p.getName());
			kitName.put(p.getName(), null);
			thor.remove(p.getName());
			thorDelay.remove(p.getName());
			viper.remove(p.getName());
			anchor.remove(p.getName());
			fireman.remove(p.getName());
			snail.remove(p.getName());
			confuser.remove(p.getName());
			stomper.remove(p.getName());
			poseidon.remove(p.getName());
			kit2Name.put(p.getName(), null);
		}
	}
	@EventHandler
	public void OnBlock(BlockIgniteEvent e) {
	if (e.getCause() == BlockIgniteEvent.IgniteCause.LIGHTNING) {
		e.setCancelled(true);
	}
	}
}


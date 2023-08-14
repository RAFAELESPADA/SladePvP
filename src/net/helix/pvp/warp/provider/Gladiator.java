package net.helix.pvp.warp.provider;


import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.core.bukkit.util.BuildUtil;
import net.helix.core.util.HelixCooldown;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.event.HelixPlayerDeathEvent;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.helix.pvp.kit.provider.GladiatorListener;
import net.helix.pvp.warp.HelixWarp;
import net.helix.pvp.warp.WarpDuoBattleHandle3;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Gladiator extends WarpDuoBattleHandle3 {
	private ItemStack machado = new ItemBuilder2().material(Material.STONE_AXE).name("§fMachado de pedra").build(),
			picareta = new ItemBuilder2().material(Material.STONE_PICKAXE).name("§fPicareta de Pedra").build(),
			pote = new ItemBuilder2().material(Material.BOWL).amount(64).build(),
			espada = new ItemBuilder2().material(Material.DIAMOND_SWORD).name("§fEspada de Diamante").addEnchant(Enchantment.DAMAGE_ALL).build(),
			muro = new ItemBuilder2().material(Material.COBBLE_WALL).amount(64).build(),
			sopa = new ItemBuilder2().material(Material.MUSHROOM_SOUP).build(),
			cocoa = new ItemBuilder2().material(Material.INK_SACK).durability(3).amount(64).name("§fCacau").build(),
			madeira = new ItemBuilder2().material(Material.WOOD).amount(64).build(),
			agua = new ItemBuilder2().material(Material.WATER_BUCKET).name("§fBalde de água").build(),
			lava = new ItemBuilder2().material(Material.LAVA_BUCKET).name("§fBalde de lava").build(),
			capacete = new ItemBuilder2().material(Material.IRON_HELMET).name("§fCapacete de Ferro").build(),
			peitoral = new ItemBuilder2().material(Material.IRON_CHESTPLATE).name("§fPeitoral de Ferro").build(),
			calça = new ItemBuilder2().material(Material.IRON_LEGGINGS).name("§fCalça de Ferro").build(),
			bota = new ItemBuilder2().material(Material.IRON_BOOTS).name("§fBota de Ferro").build();
			
			private Integer[] soupSlots = { 4, 5, 6, 7, 29, 30, 31, 32, 33, 34, 35};
			private Integer[] cocoaSlots = { 14, 15, 16, 23, 24, 25};
	public Gladiator() {
		super("gladiator1", "gladiator2");
		new BukkitRunnable() {
			@Override
			public void run() {
				if (fastChallenge.size() < 2) {
					return;
				}
				
				Player p1 = fastChallenge.get(0);
				Player p2 = fastChallenge.get(1);
				
				if (p1 == null || p2 == null) {
					fastChallenge.remove(p1);
					fastChallenge.remove(p2);
					return;
				}
				
				startBattle(p1, p2);
				 GladiatorListener.combateGlad.put(p1, p2);
	             GladiatorListener.combateGlad.put(p2, p1);
			}
		}.runTaskTimer(HelixPvP.getInstance(), 0, 2 * 20L);
	}
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		setItems(player);
		KitManager.getPlayer(player.getName()).removeKit();
		KitManager2.getPlayer(player.getName()).removekit2();
		player.getInventory().setHeldItemSlot(3);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		Optional<Player> targetOptional;
		if (!(targetOptional = findOpponent(player)).isPresent()) {
			return;
		}

		Player target = targetOptional.get();
		findOpponent(target);
		finalizeBattle(target);
		GladiatorListener.resetGladiatorListenerByQuit(target, player);
		HelixWarp.GLADIATOR.send(target);
		target.sendMessage("§2Seu oponente deslogou e a batalha foi encerrada.");

	}

	               
	            
	        
	        

	
	
	

	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		if (!HelixWarp.GLADIATOR.hasPlayer(player.getName()) || !event.hasItem() 
				|| !ItemBuilder.has(event.getItem(), "1v1g", "fast-challenge")) {
			return;
		}
		event.setCancelled(true);
		
		if (HelixCooldown.inCooldown(player.getName(), "1v1g-fast-challenge")) {
			player.sendMessage("§cAguarde...");
			return;
		}
		
		if (fastChallenge.contains(player)) {
			fastChallenge.remove(player);
			HelixCooldown.create(player.getName(), "1v1g-fast-challenge", TimeUnit.SECONDS, 3);
		}else {
			fastChallenge.add(player);
		}

		setItems(player);
		player.sendMessage(fastChallenge.contains(player) ? "§aVocê entrou na fila do 1v1" : "§eVocê saiu da fila do 1v1");
	}
	
	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		
		if (!(event.getRightClicked() instanceof Player)
				|| !HelixWarp.GLADIATOR.hasPlayer(player.getName()) 
				|| !HelixWarp.GLADIATOR.hasPlayer(event.getRightClicked().getName())
				|| player.getItemInHand() == null
				|| !ItemBuilder.has(player.getItemInHand(), "1v1g", "challenge")) {
			return;
		}
		event.setCancelled(true);
		Player target = (Player) event.getRightClicked();
		
		if (findOpponent(target).isPresent()) {
			player.sendMessage("§c§lGLAD §cEste jogador já está batalhando");
			return;
		}
		
		if (HelixCooldown.inCooldown(target.getName(), "1v1g-challenge-" + player.getName())) {
			startBattle(player, target);
			 GladiatorListener.combateGlad.put(player, target);
             GladiatorListener.combateGlad.put(target, player);
			return;
		}
		
		if (HelixCooldown.inCooldown(player.getName(), "1v1g-challenge-" + target.getName())) {
			player.sendMessage("§e§lGLAD §eVocê já convidou este jogador recentemente");
			return;
		}
		
		HelixCooldown.create(player.getName(), "1v1g-challenge-" + target.getName(), TimeUnit.SECONDS, 15);
		target.sendMessage("§e§lGLAD §eVocê foi convidado por " + player.getName() + " para uma batalha");
		player.sendMessage("§6§lGLAD §6Você convidou " + target.getName() + " para uma batalha");
	}
	
	@EventHandler
	public void onDeath(HelixPlayerDeathEvent event) {
		if (!HelixWarp.GLADIATOR.hasPlayer(event.getPlayer().getName())) {
			return;
		}
		Player loser = event.getPlayer();
		Player winner = findOpponent(loser).isPresent() ? findOpponent(loser).get() : event.getKiller();
		finalizeBattle(winner);
		GladiatorListener.resetGladiatorListenerByGlad(winner , loser);
		Random random = new Random();
		HelixPlayer loserHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(loser.getName());
		HelixPlayer victimHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(loser.getName());
		int loserWithdrawnCoins = random.nextInt(20 + 1 - 5) + 5;
		loserHelixPlayer.getPvp().adddeathsX1(1);
		loserHelixPlayer.getPvp().setKillstreak(0);
		loser.sendMessage("§cVocê perdeu a batalha contra " + winner.getName() + "§c.");
		if ((loserHelixPlayer.getPvp().getCoins() - loserWithdrawnCoins) >= 0) {
			loserHelixPlayer.getPvp().removeCoins(loserWithdrawnCoins);
			loser.sendMessage("§c§l[-] §c" + loserWithdrawnCoins + " coins");
		}else {
			loserHelixPlayer.getPvp().setCoins(0);
		}
		if ((victimHelixPlayer.getPvp().getXp() - 8) >= 0) {
			victimHelixPlayer.getPvp().setXp(victimHelixPlayer.getPvp().getXp() - 8);
			loser.sendMessage("§c§l[-] §c8 XP");
		}else {
			victimHelixPlayer.getPvp().setXp(0);
			loser.sendMessage("§c§l[-] " + victimHelixPlayer.getPvp().getXp() + " XP");
		}

		HelixBukkit.getInstance().getPlayerManager().getController().save(loserHelixPlayer);
		
		new BukkitRunnable() {
			@Override
			public void run() {
				HelixWarp.GLADIATOR.send(loser, true);
				HelixWarp.GLADIATOR.send(winner, true);
			}
		}.runTaskLater(HelixPvP.getInstance(), 10);
		winner.setHealth(winner.getMaxHealth());
	GladiatorListener.combateGlad.remove(winner);
	GladiatorListener.combateGlad.remove(loser);
	winner.getInventory().clear();
		winner.sendMessage("§aVocê ganhou a batalha contra " + loser.getName() + " §7(" + (event.isValidKill() ? "Conta" : "Não conta") + ")");

		if (event.isValidKill()) {
			HelixPlayer winnerHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(winner.getName());
			int winnerAddCoins = random.nextInt(20 + 1 - 10) + 10;
			winnerHelixPlayer.getPvp().addKills(1);
			victimHelixPlayer.getPvp().addDeaths(1);
			winnerHelixPlayer.getPvp().addKillstreak(1);
			winnerHelixPlayer.getPvp().addwinsX1(1);
			winnerHelixPlayer.getPvp().addWinstreakX1(1);
			winnerHelixPlayer.getPvp().addCoins(winnerAddCoins);
			winnerHelixPlayer.getPvp().setXp(winnerHelixPlayer.getPvp().getXp() + 10);
			winner.sendMessage("§6§l[+] §6" + winnerAddCoins + " coins");
			winner.sendMessage("§6§l[+] §a10XP");
			HelixBukkit.getInstance().getPlayerManager().getController().save(winnerHelixPlayer);
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onDrop(PlayerDropItemEvent event) {
		if (findOpponent(event.getPlayer()).isPresent()) {
			event.getItemDrop().remove();
		}
	}
	private void setGladInventory(Player player) {
		player.setGameMode(GameMode.SURVIVAL);
		
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);

		player.getInventory().setItem(0, espada);
		player.getInventory().setItem(1, agua);
		player.getInventory().setItem(2, lava);
		player.getInventory().setItem(3, madeira);
		player.getInventory().setItem(8, muro);
		player.getInventory().setItem(9, capacete);
		player.getInventory().setItem(10, peitoral);
		player.getInventory().setItem(11, calça);
		player.getInventory().setItem(12, bota);
		player.getInventory().setItem(17, machado);
		player.getInventory().setItem(18, capacete);
		player.getInventory().setItem(19, peitoral);
		player.getInventory().setItem(20, calça);
		player.getInventory().setItem(21, bota);
		player.getInventory().setItem(26, picareta);
		player.getInventory().setItem(27, lava);
		player.getInventory().setItem(28, lava);

		player.getInventory().setHelmet(capacete);
		player.getInventory().setChestplate(peitoral);
		player.getInventory().setLeggings(calça);
		player.getInventory().setBoots(bota);
		
		player.getInventory().setItem(13, pote);
		player.getInventory().setItem(22, pote);
		
		for (Integer slot : soupSlots) {
			 player.getInventory().setItem(slot, sopa);
		}
		
		for (Integer slot : cocoaSlots) {
		 	 player.getInventory().setItem(slot, cocoa);
		}
		player.updateInventory();
	}

	@Override
	public void sendBattleItems(Player player) {
		super.sendBattleItems(player);
		
		setGladInventory(player);
		}


	@Override
	public void setItems(Player player) {
		super.setItems(player);

		player.getInventory().setItem(3, new ItemBuilder("§bDesafiar §7(Clique)", Material.IRON_FENCE)
				.nbt("cancel-drop")
				.nbt("cancel-click")
				.nbt("1v1g", "challenge")
				.toStack()
		);
		
		player.getInventory().setItem(5, new ItemBuilder("§bProcurar jogadores: " + (fastChallenge.contains(player) ? "§aON" : "§cOFF"), Material.INK_SACK, fastChallenge.contains(player) ? 10 : 8)
				.nbt("cancel-drop")
				.nbt("cancel-click")
				.nbt("1v1g", "fast-challenge")
				.toStack()
		);
		player.updateInventory();
	}
}
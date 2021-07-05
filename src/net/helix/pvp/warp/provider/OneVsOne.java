package net.helix.pvp.warp.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.core.bukkit.util.AdminUtil;
import net.helix.core.bukkit.util.DamageUtil;
import net.helix.core.bukkit.util.DamageUtil.DamageType;
import net.helix.core.util.HelixCooldown;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.event.HelixPlayerDeathEvent;
import net.helix.pvp.event.HelixPlayerDeathEvent.Reason;
import net.helix.pvp.warp.HelixWarp;
import net.helix.pvp.warp.WarpHandle;

public class OneVsOne extends WarpHandle {
	
	private final static List<Player> fastChallenge = new ArrayList<>();
	private final static HashMap<Player, Player> battlingPlayers = new HashMap<>();
	
	public OneVsOne() {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (fastChallenge.size() < 2) {
					return;
				}
				
				Player p1 = fastChallenge.get(0);
				Player p2 = fastChallenge.get(1);
				
				if (p1 == null) {
					fastChallenge.remove(p1);
					p1 = null;
				}
				if (p2 == null) {
					fastChallenge.remove(p2);
					p2 = null;
				}
				
				if (p1 == null || p2 == null) {
					return;
				}
				
				startBattle(p1, p2);
			}
		}.runTaskTimer(HelixPvP.getInstance(), 0, 2 * 20L);
	}
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		DamageUtil.denyAllDamage(player.getName());
		setItems(player);
		player.getInventory().setHeldItemSlot(3);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		if (battlingPlayers.containsKey(player) && battlingPlayers.get(player) != null) {
			Player battlingPlayer = battlingPlayers.get(player);
			battlingPlayer.sendMessage("§cSeu oponente deslogou e a batalha foi encerrada.");
			
			show(battlingPlayer);
			HelixWarp.ONE_VS_ONE.send(battlingPlayer);
			battlingPlayers.remove(battlingPlayer);
		}
		battlingPlayers.remove(player);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		if (!HelixWarp.ONE_VS_ONE.hasPlayer(player.getName()) || !event.hasItem() 
				|| !ItemBuilder.has(event.getItem(), "1v1", "fast-challenge") 
				|| !HelixWarp.ONE_VS_ONE.hasPlayer(player.getName())) {
			return;
		}
		event.setCancelled(true);
		
		if (HelixCooldown.inCooldown(player.getName(), "fast-challenge")) {
			player.sendMessage("§cAguarde...");
			return;
		}
		
		HelixCooldown.create(player.getName(), "fast-challenge", TimeUnit.SECONDS, 3);
		if (fastChallenge.contains(player)) {
			fastChallenge.remove(player);
		}else {
			fastChallenge.add(player);
		}

		setItems(player);
		player.sendMessage(fastChallenge.contains(player) ? "§aVocê entrou na fila. Buscando oponente..." : "§cVocê saiu da fila.");
	}
	
	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		
		if (!(event.getRightClicked() instanceof Player)
				|| !HelixWarp.ONE_VS_ONE.hasPlayer(player.getName()) 
				|| !HelixWarp.ONE_VS_ONE.hasPlayer(event.getRightClicked().getName())
				|| player.getItemInHand() == null
				|| !ItemBuilder.has(player.getItemInHand(), "1v1", "challenge")) {
			return;
		}
		event.setCancelled(true);
		
		if (HelixCooldown.inCooldown(player.getName(), "challenge")) {
			player.sendMessage("§cAguarde...");
			return;
		}
		Player target = (Player) event.getRightClicked();
		
		if (HelixCooldown.inCooldown(target.getName(), "1v1-challenge-" + player.getName())) {
			startBattle(player, target);
			return;
		}
		
		if (HelixCooldown.inCooldown(player.getName(), "1v1-challenge-" + target.getName())) {
			player.sendMessage("§cVocê já convidou este jogador recentemente. Aguarde alguns instantes.");
			return;
		}
		
		HelixCooldown.create(player.getName(), "1v1-challenge-" + target.getName(), TimeUnit.SECONDS, 15);
		target.sendMessage("§aVocê foi convidado por §f" + player.getName() + " §apara uma batalha! Você tem §f15 segundos §apara aceitar.");
		player.sendMessage("§aVocê convidou §f" + target.getName() + " §apara uma batalha. Ele tem §f15 segundos §apara aceitar.");
	}
	
	@EventHandler
	public void onDeath(HelixPlayerDeathEvent event) {
		if (event.getReason() != Reason.ONE_VS_ONE || !event.hasKiller()) {
			return;
		}
		Player loser = event.getPlayer();
		Player winner = event.hasKiller() ? event.getKiller() : battlingPlayers.get(loser);
		
		battlingPlayers.remove(winner); battlingPlayers.remove(loser);
		show(winner); show(loser);
		
		Random random = new Random();
		HelixPlayer loserHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(loser.getName());
		int loserWithdrawnCoins = random.nextInt(20 + 1 - 5) + 5;
		loserHelixPlayer.getPvp().addDeaths(1);
		loserHelixPlayer.getPvp().setKillstreak(0);
		loser.sendMessage("§cVocê perdeu a batalha contra " + winner.getName() + "§c.");
		
		if ((loserHelixPlayer.getPvp().getCoins() - loserWithdrawnCoins) >= 0) {
			loserHelixPlayer.getPvp().removeCoins(loserWithdrawnCoins);
			loser.sendMessage("§c-" + loserWithdrawnCoins + " coins");
		}else {
			loserHelixPlayer.getPvp().setCoins(0);
		}
		HelixBukkit.getInstance().getPlayerManager().getData().save(loserHelixPlayer);
		
		new BukkitRunnable() {
			@Override
			public void run() {
				HelixWarp.ONE_VS_ONE.send(loser);
			}
		}.runTaskLater(HelixPvP.getInstance(), 10);
		
		winner.sendMessage("§aVocê ganhou a batalha contra §f" + loser.getName() + "§a. §7(" + (event.isValidKill() ? "Conta" : "Não conta") + ")");
		HelixWarp.ONE_VS_ONE.send(winner);
		if (event.isValidKill()) {
			HelixPlayer winnerHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(winner.getName());
			int winnerAddCoins = random.nextInt(50 + 1 - 10) + 10;
			winnerHelixPlayer.getPvp().addKills(1);
			winnerHelixPlayer.getPvp().addKillstreak(1);
			winnerHelixPlayer.getPvp().addCoins(winnerAddCoins);
			winner.sendMessage("§6+" + winnerAddCoins + " coins");
			HelixBukkit.getInstance().getPlayerManager().getData().save(winnerHelixPlayer);
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player) 
				|| (!(event.getDamager() instanceof Player))) {
			return;
		}
		
		Player victim = (Player) event.getEntity();
		Player damager = (Player) event.getDamager();
		
		if (battlingPlayers.containsKey(victim) && !battlingPlayers.get(victim).getName().equals(damager.getName())) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		battlingPlayers.forEach((p1, p2) ->  p1.hidePlayer(event.getPlayer()));
	}
	
	@EventHandler
	public void onPlayerCommandProcess(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		
		if (battlingPlayers.containsKey(player)) {
			event.setCancelled(true);
			player.sendMessage("§cVocê não pode digitar comandos enquanto está batalhando.");
		}
	}
	
	private void startBattle(Player p1, Player p2) {
		fastChallenge.remove(p1); fastChallenge.remove(p2);
		
		Optional<net.helix.core.bukkit.warp.HelixWarp> pos1 = HelixBukkit.getInstance().getWarpManager().findWarp("one_vs_one_pos1");
		Optional<net.helix.core.bukkit.warp.HelixWarp> pos2 = HelixBukkit.getInstance().getWarpManager().findWarp("one_vs_one_pos2");
		if (!pos1.isPresent() || !pos2.isPresent()) {
			setItems(p1); setItems(p2);
			p1.sendMessage("§cOcorreu um erro ao iniciar a batalha. (LOC-404)");
			p2.sendMessage("§cOcorreu um erro ao iniciar a batalha. (LOC-404)");
			return;
		}
		
		p1.teleport(pos1.get().getLocation());
		p2.teleport(pos2.get().getLocation());
		
		sendBattleItems(p1); sendBattleItems(p2);
		hide(p1, p2);
		
		battlingPlayers.put(p1, p2); battlingPlayers.put(p2, p1);
		
		DamageUtil.allowDamage(p1.getName(), DamageType.PLAYER, true);
		DamageUtil.allowDamage(p2.getName(), DamageType.PLAYER, true);
		p1.sendMessage("§aBatalhada iniciada! Oponente: §f" + p2.getName());
		p2.sendMessage("§aBatalhada iniciada! Oponente: §f" + p1.getName());
	}
	
	private void sendBattleItems(Player player) {
		player.setGameMode(GameMode.ADVENTURE);
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		player.setAllowFlight(false);
		player.setFlying(false);
		player.getInventory().setHeldItemSlot(0);
		player.getActivePotionEffects().forEach(potion -> player.removePotionEffect(potion.getType()));
		
		player.getInventory().setItem(0, new ItemBuilder("§fEspada de Pedra", Material.STONE_SWORD)
				.nbt("cancel=drop")
				.toStack()
		);
		
		for (int i = 0; i <= 7; i++) {
			player.getInventory().addItem(new ItemBuilder(Material.MUSHROOM_SOUP).toStack());
		}
	}
	
	private void setItems(Player player) {
		player.getInventory().setItem(3, new ItemBuilder("§bDesafiar jogador", Material.BLAZE_ROD)
				.nbt("cancel-drop")
				.nbt("cancel-click")
				.nbt("1v1", "challenge")
				.toStack()
		);
		
		player.getInventory().setItem(5, new ItemBuilder("§6Procurar oponente: " + (fastChallenge.contains(player) ? "§aON" : "§cOFF"), Material.INK_SACK, fastChallenge.contains(player) ? 10 : 8)
				.nbt("cancel-drop")
				.nbt("cancel-click")
				.nbt("1v1", "fast-challenge")
				.toStack()
		);
		player.updateInventory();
	}
	
	private void hide(Player p1, Player p2) {
		Bukkit.getOnlinePlayers().stream().filter(
				online -> !online.getName().equals(p1.getName()) 
					&& !online.getName().equals(p2.getName())
		).forEach(online -> {
			p1.hidePlayer(online);
			p2.hidePlayer(online);
		});
	}
	
	private void show(Player player) {
		Bukkit.getOnlinePlayers().stream().filter(
				online -> !AdminUtil.has(online.getName())
		).forEach(player::showPlayer);
	}
}

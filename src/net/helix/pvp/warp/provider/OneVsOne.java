package net.helix.pvp.warp.provider;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.core.bukkit.util.DamageUtil;
import net.helix.core.util.HelixCooldown;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.event.HelixPlayerDeathEvent;
import net.helix.pvp.warp.HelixWarp;
import net.helix.pvp.warp.WarpDuoBattleHandle;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class OneVsOne extends WarpDuoBattleHandle {
	
	public OneVsOne() {
		super("one_vs_one_pos1", "one_vs_one_pos2");
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

		Optional<Player> targetOptional;
		if (!(targetOptional = findOpponent(player)).isPresent()) {
			return;
		}

		Player target = targetOptional.get();
		findOpponent(target);
		finalizeBattle(target);

		HelixWarp.ONE_VS_ONE.send(target);
		target.sendMessage("§2Seu oponente deslogou e a batalha foi encerrada.");

	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		if (!HelixWarp.ONE_VS_ONE.hasPlayer(player.getName()) || !event.hasItem() 
				|| !ItemBuilder.has(event.getItem(), "1v1", "fast-challenge")) {
			return;
		}
		event.setCancelled(true);
		
		if (HelixCooldown.inCooldown(player.getName(), "1v1-fast-challenge")) {
			player.sendMessage("§cAguarde...");
			return;
		}
		
		if (fastChallenge.contains(player)) {
			fastChallenge.remove(player);
			HelixCooldown.create(player.getName(), "1v1-fast-challenge", TimeUnit.SECONDS, 3);
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
				|| !HelixWarp.ONE_VS_ONE.hasPlayer(player.getName()) 
				|| !HelixWarp.ONE_VS_ONE.hasPlayer(event.getRightClicked().getName())
				|| player.getItemInHand() == null
				|| !ItemBuilder.has(player.getItemInHand(), "1v1", "challenge")) {
			return;
		}
		event.setCancelled(true);
		
		Player target = (Player) event.getRightClicked();
		
		if (findOpponent(target).isPresent()) {
			player.sendMessage("§c§l1V1 §cEste jogador já está batalhando");
			return;
		}
		
		if (HelixCooldown.inCooldown(target.getName(), "1v1-challenge-" + player.getName())) {
			startBattle(player, target);
			return;
		}
		
		if (HelixCooldown.inCooldown(player.getName(), "1v1-challenge-" + target.getName())) {
			player.sendMessage("§e§l1V1 §eVocê já convidou este jogador recentemente");
			return;
		}
		
		HelixCooldown.create(player.getName(), "1v1-challenge-" + target.getName(), TimeUnit.SECONDS, 15);
		target.sendMessage("§e§l1V1 §eVocê foi convidado por " + player.getName() + " para uma batalha");
		player.sendMessage("§6§l1V1 §6Você convidou " + target.getName() + " para uma batalha");
	}
	
	@EventHandler
	public void onDeath(HelixPlayerDeathEvent event) {
		if (event.getReason() != HelixPlayerDeathEvent.Reason.ONE_VS_ONE || !event.hasKiller()) {
			return;
		}
		Player loser = event.getPlayer();
		Player winner = findOpponent(loser).isPresent() ? findOpponent(loser).get() : event.getKiller();
		finalizeBattle(winner);
		
		Random random = new Random();
		HelixPlayer loserHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(loser.getName());

		int loserWithdrawnCoins = random.nextInt(20 + 1 - 5) + 5;
		loserHelixPlayer.getPvp().addDeaths(1);
		loserHelixPlayer.getPvp().setKillstreak(0);

		loser.sendMessage("§cVocê perdeu a batalha contra " + winner.getName() + "�c.");
		
		if ((loserHelixPlayer.getPvp().getCoins() - loserWithdrawnCoins) >= 0) {
			loserHelixPlayer.getPvp().removeCoins(loserWithdrawnCoins);
			loser.sendMessage("§c§l[-] §c" + loserWithdrawnCoins + " coins");
		}else {
			loserHelixPlayer.getPvp().setCoins(0);
		}

		HelixBukkit.getInstance().getPlayerManager().getController().save(loserHelixPlayer);
		
		new BukkitRunnable() {
			@Override
			public void run() {
				HelixWarp.ONE_VS_ONE.send(loser, true);
			}
		}.runTaskLater(HelixPvP.getInstance(), 10);

		winner.setHealth(winner.getMaxHealth());
		winner.sendMessage("§aVocê ganhou a batalha contra " + loser.getName() + " §7(" + (event.isValidKill() ? "Conta" : "Não conta") + ")");
		HelixWarp.ONE_VS_ONE.send(winner, true);

		if (event.isValidKill()) {
			HelixPlayer winnerHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(winner.getName());
			int winnerAddCoins = random.nextInt(50 + 1 - 10) + 10;

			winnerHelixPlayer.getPvp().addKills(1);
			winnerHelixPlayer.getPvp().addKillstreak(1);
			winnerHelixPlayer.getPvp().addCoins(winnerAddCoins);

			winner.sendMessage("§6§l[+] §6" + winnerAddCoins + " coins");
			HelixBukkit.getInstance().getPlayerManager().getController().save(winnerHelixPlayer);
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onDrop(PlayerDropItemEvent event) {
		if (findOpponent(event.getPlayer()).isPresent()) {
			event.getItemDrop().remove();
		}
	}

	@Override
	public void sendBattleItems(Player player) {
		super.sendBattleItems(player);
		
		player.getInventory().setItem(0, new ItemBuilder("§fEspada de Diamante", Material.DIAMOND_SWORD)
				.nbt("cancel-drop")
				.toStack()
		);

		player.getInventory().setHelmet(new ItemBuilder("§6§lSLOPER§f§lPVP", Material.IRON_HELMET).toStack());
		player.getInventory().setChestplate(new ItemBuilder("§6§lSLOPER§f§lPVP", Material.IRON_CHESTPLATE).toStack());
		player.getInventory().setLeggings(new ItemBuilder("§6§lSLOPER§f§lPVP", Material.IRON_LEGGINGS).toStack());
		player.getInventory().setBoots(new ItemBuilder("§6§lSLOPER§f§lPVP", Material.IRON_BOOTS).toStack());

		for (int i = 0; i <= 7; i++) {
			player.getInventory().addItem(new ItemBuilder(Material.MUSHROOM_SOUP).toStack());
		}
	}

	@Override
	public void setItems(Player player) {
		super.setItems(player);

		player.getInventory().setItem(3, new ItemBuilder("§bDesafiar §7(Clique)", Material.BLAZE_ROD)
				.nbt("cancel-drop")
				.nbt("cancel-click")
				.nbt("1v1", "challenge")
				.toStack()
		);
		
		player.getInventory().setItem(5, new ItemBuilder("§bProcurar jogadores: " + (fastChallenge.contains(player) ? "§aON" : "§cOFF"), Material.INK_SACK, fastChallenge.contains(player) ? 10 : 8)
				.nbt("cancel-drop")
				.nbt("cancel-click")
				.nbt("1v1", "fast-challenge")
				.toStack()
		);
		player.updateInventory();
	}
}

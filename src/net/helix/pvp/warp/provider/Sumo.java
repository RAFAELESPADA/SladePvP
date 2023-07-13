package net.helix.pvp.warp.provider;


import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.core.util.HelixCooldown;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.warp.HelixWarp;
import net.helix.pvp.warp.WarpDuoBattleHandle3;

public class Sumo extends WarpDuoBattleHandle3 {
  public Sumo() {
    super("sumo_pos1", "sumo_pos2");
    (new BukkitRunnable() {
        public void run() {
          if (Sumo.fastChallenge.size() < 2)
            return; 
          Player p1 = Sumo.fastChallenge.get(0);
          Player p2 = Sumo.fastChallenge.get(1);
          if (p1 == null || p2 == null) {
            Sumo.fastChallenge.remove(p1);
            Sumo.fastChallenge.remove(p2);
            return;
          } 
          Sumo.this.startBattle(p1, p2);
        }
      }).runTaskTimer((Plugin)HelixPvP.getInstance(), 0L, 40L);
  }
  
  public void execute(Player player) {
    super.execute(player);
    setItems(player);
  }
  
  public void setItems(Player player) {
    super.setItems(player);
    player.getInventory().setItem(4, (new ItemBuilder("§aSumo", Material.APPLE))
        .lore(new String[] { "Clique para desafiar" }).nbt("cancel-drop")
        .nbt("cancel-click")
        .nbt("sumo", "challenge")
        .toStack());
    boolean searchPlayers = fastChallenge.contains(player);
    player.updateInventory();
  }
  
  @EventHandler
  public void onInteract(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    if (!HelixWarp.SUMO.hasPlayer(player.getName()) || !event.hasItem() || 
      !ItemBuilder.has(event.getItem(), "sumo", "fast-challenge"))
      return; 
    event.setCancelled(true);
    if (HelixCooldown.inCooldown(player.getName(), "sumo-fast-challenge")) {
      player.sendMessage("§cVocê está em cooldown.");
      return;
    } 
    if (fastChallenge.contains(player)) {
      fastChallenge.remove(player);
      HelixCooldown.create(player.getName(), "sumo-fast-challenge", TimeUnit.SECONDS, 3L);
    } else {
      fastChallenge.add(player);
    } 
    setItems(player);
    player.sendMessage(fastChallenge.contains(player) ? "§aVocê entrou na fila rápida" : "§aVocê saiu da fila rápida.");
  }
  
  @EventHandler
  public void onInteractEntity(PlayerInteractEntityEvent event) {
    Player player = event.getPlayer();
    if (!(event.getRightClicked() instanceof Player) || 
      !HelixWarp.SUMO.hasPlayer(player.getName()) || 
      !HelixWarp.SUMO.hasPlayer(event.getRightClicked().getName()) || 
      player.getItemInHand() == null || 
      !ItemBuilder.has(player.getItemInHand(), "sumo", "challenge"))
      return; 
    event.setCancelled(true);
    Player target = (Player)event.getRightClicked();
    if (this.battlingPlayers.containsKey(target)) {
      player.sendMessage("§cVocê já está batalhando.");
      return;
    } 
    if (HelixCooldown.inCooldown(target.getName(), "sumo-challenge-" + player.getName())) {
      startBattle(player, target);
      return;
    } 
    if (HelixCooldown.inCooldown(player.getName(), "sumo-challenge-" + target.getName())) {
      player.sendMessage("§cVocê já convidou este jogador recentemente");
      return;
    } 
    HelixCooldown.create(player.getName(), "sumo-challenge-" + target.getName(), TimeUnit.SECONDS, 15L);
    target.sendMessage("§cVocê foi convidado por " + player.getName() + " para uma batalha!");
    player.sendMessage("§eVocê convidou " + target.getName() + " para uma batalha!");
  }
  
  @EventHandler
  public void onDamage(EntityDamageByEntityEvent event) {
    if (!(event.getEntity() instanceof Player) || 
      !(event.getDamager() instanceof Player))
      return; 
    Player damager = (Player)event.getDamager();
    Player victim = (Player)event.getEntity();
    Optional<Player> targetOptional;
    if (!(targetOptional = findOpponent(damager)).isPresent())
      return; 
    Player target = targetOptional.get();
    if (target.equals(victim))
      event.setDamage(0.0D); 
  }
  
  @EventHandler
  public void onMove(PlayerMoveEvent event) {
    Player player = event.getPlayer();
    Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
    if (!HelixWarp.SUMO.hasPlayer(player.getName()))
      return; 
    if (!block.getType().toString().contains("WATER") || !findOpponent(player).isPresent())
      return; 
    Player target = findOpponent(player).get();
    Random random = new Random();
    finalizeBattle(player);
    HelixPlayer loserUser = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
    HelixWarp.SUMO.send(player);
    player.sendMessage("§cVocê perdeu a luta contra " + target.getName());
    int loserWithdrawnCoins = random.nextInt(13) + 8;
    if (loserUser.getPvp().getCoins() - loserWithdrawnCoins >= 0) {
      loserUser.getPvp().removeCoins(loserWithdrawnCoins);
      player.sendMessage("§6§l[+] §6" + loserWithdrawnCoins + " coins");
    } else {
      loserUser.getPvp().setCoins(0);
    } 
    HelixBukkit.getInstance().getPlayerManager().getController().save(loserUser);
    HelixPlayer killerUser = HelixBukkit.getInstance().getPlayerManager().getPlayer(target.getName());
    int winnerCoins = !target.hasPermission("kombo.doublexp") ? random.nextInt(56) + 25 : random.nextInt(86) + 55;
    HelixWarp.SUMO.send(target);
    target.playSound(target.getLocation(), Sound.LEVEL_UP, 10.0F, 10.0F);
    target.sendMessage("§eVocê ganhou a luta conta " + player.getName());
    killerUser.getPvp().addCoins(winnerCoins);
    killerUser.getPvp().addKills(1);
    killerUser.getPvp().addKillstreak(1);
    target.sendMessage("§6§l[+] §6" +  winnerCoins + " coins");
    HelixBukkit.getInstance().getPlayerManager().getController().save(killerUser);
  }
  
  @EventHandler
  public void onQuit(PlayerQuitEvent event) {
    Player player = event.getPlayer();
    Optional<Player> targetOptional;
    if (!(targetOptional = findOpponent(player)).isPresent())
      return; 
    Player target = targetOptional.get();
    finalizeBattle(player);
    HelixWarp.SUMO.send(target);
    target.sendMessage("§cOponente deslogou e a batalha foi encerrada");
  }
}

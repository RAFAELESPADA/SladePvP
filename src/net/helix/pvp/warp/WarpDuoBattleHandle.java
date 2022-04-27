package net.helix.pvp.warp;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.util.AdminUtil;
import net.helix.core.bukkit.util.DamageUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.*;

public abstract class WarpDuoBattleHandle extends WarpHandle {

    private final String warpPos1, warpPos2;

    public WarpDuoBattleHandle(String warpPos1, String warpPos2) {
        this.warpPos1 = warpPos1;
        this.warpPos2 = warpPos2;
    }

    public List<Player> fastChallenge = new ArrayList<>();
    protected final HashMap<Player, Player> battlingPlayers = new HashMap<>();

    public void setItems(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
    }

    public final Optional<Player> findOpponent(Player player) {
        return battlingPlayers.containsKey(player) ? Optional.of(battlingPlayers.get(player)) :
                battlingPlayers.entrySet().stream().filter(entry -> entry.getValue().equals(player)).map(Map.Entry::getKey).findFirst();
    }

    public final void finalizeBattle(Player player) {
        show(player);
        DamageUtil.denyAllDamage(player.getName());

        findOpponent(player).ifPresent(target -> {
            show(target);
            DamageUtil.denyAllDamage(target.getName());
        });

        fastChallenge.remove(player);
        battlingPlayers.entrySet().removeIf(entry -> entry.getKey().equals(player) || entry.getValue().equals(player));
    }

    public void sendBattleItems(Player player) {
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.getInventory().setHeldItemSlot(0);
        player.getActivePotionEffects().forEach(potion -> player.removePotionEffect(potion.getType()));
    }

    public void startBattle(Player p1, Player p2) {
        fastChallenge.remove(p1); fastChallenge.remove(p2);
        p1.setHealth(p1.getMaxHealth());
        p2.setHealth(p2.getMaxHealth());

        Optional<net.helix.core.bukkit.warp.HelixWarp> pos1 = HelixBukkit.getInstance().getWarpManager().findWarp(warpPos1);
        Optional<net.helix.core.bukkit.warp.HelixWarp> pos2 = HelixBukkit.getInstance().getWarpManager().findWarp(warpPos2);
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

        battlingPlayers.put(p1, p2);

        DamageUtil.allowDamage(p1.getName(), DamageUtil.DamageType.PLAYER, true);
        DamageUtil.allowDamage(p2.getName(), DamageUtil.DamageType.PLAYER, true);
    }

    public void hide(Player p1, Player p2) {
        Bukkit.getOnlinePlayers().stream().filter(
                online -> !online.getName().equals(p1.getName())
                        && !online.getName().equals(p2.getName())
        ).forEach(online -> {
            p1.hidePlayer(online);
            p2.hidePlayer(online);
        });
    }

    public void show(Player player) {
        Bukkit.getOnlinePlayers().stream().filter(
                online -> !AdminUtil.has(online.getName())
        ).forEach(player::showPlayer);
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
    public void onPlayerCommandProcess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        if (fastChallenge.contains(player) && event.getMessage().split(" ")[0].equalsIgnoreCase("spawn")) {
            fastChallenge.remove(player);
        }

        if (findOpponent(player).isPresent()) {
            event.setCancelled(true);
            player.sendMessage("§eVocê não pode digitar comandos enquanto está batalhando");
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        battlingPlayers.forEach((p1, p2)-> {
            p1.hidePlayer(event.getPlayer());
            p2.hidePlayer(event.getPlayer());
        });
    }
}

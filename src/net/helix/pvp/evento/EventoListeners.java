package net.helix.pvp.evento;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventoListeners implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void BlockBreak(BlockBreakEvent event) {
        if (!EventoUtils.evento) return;
        
        Player player = event.getPlayer();
       
        if (!EventoUtils.game.contains(player.getName())) return;
        if (!EventoUtils.build) {
            event.setCancelled(true);
        }
        event.setCancelled(!EventoUtils.blocks.contains(event.getBlock().getLocation()));
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void Block(PlayerJoinEvent event) {
        if (!EventoUtils.evento && !EventoUtils.tp) return;
        event.getPlayer().sendMessage("");
        event.getPlayer().sendMessage("");
        event.getPlayer().sendMessage("");
        event.getPlayer().sendMessage("");
        event.getPlayer().sendMessage("");
        event.getPlayer().sendMessage("");
        event.getPlayer().sendMessage("");
        event.getPlayer().sendMessage("");
        event.getPlayer().sendMessage("");
        event.getPlayer().sendMessage("");
        event.getPlayer().sendMessage("");
        event.getPlayer().sendMessage("");
        event.getPlayer().sendMessage("");
        event.getPlayer().sendMessage("");
        event.getPlayer().sendMessage("");
        event.getPlayer().sendMessage("");
        event.getPlayer().sendMessage("§aUm evento está ocorrendo!");
        event.getPlayer().sendMessage("§aUtilize §b/evento entrar §apara entrar");
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void BlockPlace(BlockPlaceEvent event) {
        if (!EventoUtils.evento) return;
        
        Player player = event.getPlayer();

        if (!EventoUtils.game.contains(player.getName())) return;
        if (!EventoUtils.build) {
            event.setCancelled(true);
        }
        EventoUtils.blocks.add(event.getBlock().getLocation());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void BucketFill(PlayerBucketFillEvent event) {
        if (!EventoUtils.evento) return;
        
        Player player = event.getPlayer();
        if (!EventoUtils.game.contains(player.getName())) return;
        if (!EventoUtils.build) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void BucketFill(PlayerQuitEvent event) {
    	 if (!EventoUtils.game.contains(event.getPlayer().getName())) return;
        
        EventoUtils.game.remove(event.getPlayer().getName());
        EventoUtils.game.remove(event.getPlayer().getName());
        EventoUtils.game.remove(event.getPlayer().getName());
        EventoUtils.game.remove(event.getPlayer().getName());
        EventoUtils.game.remove(event.getPlayer().getName());
        EventoUtils.game.remove(event.getPlayer().getName());
        EventoUtils.game.remove(event.getPlayer().getName());
        EventoUtils.game.remove(event.getPlayer().getName());
        }
    

    @EventHandler(priority = EventPriority.HIGHEST)
    public void BucketEmpty(PlayerBucketEmptyEvent event) {
        if (!EventoUtils.evento) return;
        
        Player player = event.getPlayer();

        if (!EventoUtils.game.contains(player.getName())) return;
        if (!EventoUtils.build) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void EntityDamage(EntityDamageEvent event) {
        if (!EventoUtils.evento) return;

        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();

        if (!EventoUtils.game.contains(player.getName())) return;
        if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            if (!EventoUtils.pvp) {
                event.setCancelled(true);
            }
        } else {
            if (!EventoUtils.damage) {
                event.setCancelled(true);
            }
        }
    }

}
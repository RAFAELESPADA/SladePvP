package net.helix.pvp.evento;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.warp.HelixWarp;

public class EventoListeners implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void BlockBreak(BlockBreakEvent event) {
        if (!EventoUtils.evento) return;
        
        Player player = event.getPlayer();
       
        if (!EventoUtils.game.contains(player.getName())) return;
        if (!EventoUtils.build) {
            event.setCancelled(true);
        }
        if (!EventoUtils.staff) {
        event.setCancelled(!EventoUtils.blocks.contains(event.getBlock().getLocation()));
    }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void BlockBreak2(BlockBreakEvent event) {
        if (!KitManager.getPlayer(event.getPlayer().getName()).hasKit(HelixKit.TORNADO)) return;       
        if (event.getBlock().getType() == Material.HOPPER) {
        	event.getPlayer().sendMessage(ChatColor.RED + "Não ponha o tornado no chão!");
        	event.setCancelled(true);
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void BlockBreak5(BlockBreakEvent event) {
        if (!HelixWarp.GLADIATOR.hasPlayer(event.getPlayer().getName())) return;       
        if (event.getBlock().getType() == Material.GLASS) {
        	event.setCancelled(true);
        	event.getPlayer().sendMessage(ChatColor.RED + "Dont break gladiator glass!");
        }
    }
    @EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {
	  if (event.getEntity() instanceof Arrow) {
	       Arrow arrow = (Arrow)event.getEntity();
	       arrow.remove();
	  }
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
        event.getPlayer().sendMessage("§aA event is occouring!");
        event.getPlayer().sendMessage("§aUse §b/event join §ato join");
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
    public void BlockPlacegv(BlockBreakEvent event) {
        if (!EventoUtils.evento) return;
        Player player = event.getPlayer();
        if (!EventoUtils.game.contains(player.getName())) return;
        if (!EventoUtils.blocks.contains(event.getBlock().getLocation())) {
        	player.sendMessage("§cVocê só pode quebrar blocos colocados por jogadores");
        	event.setCancelled(true);
        }
        if (EventoUtils.staff && (!(event.getBlock() == new ItemStack(Material.WOOD) || event.getBlock() == new ItemStack(Material.COBBLE_WALL) || event.getBlock() == new ItemStack(Material.WEB)))) {
        	player.sendMessage("§aVocê só pode quebrar blocos colocados por jogadores");
        	event.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void BlockPlaceV(BlockPlaceEvent event) {
     
        
        Player player = event.getPlayer();

        if (!HelixWarp.GLADIATOR.hasPlayer(player.getName())) return;
        EventoUtils.blocksV.add(event.getBlock().getLocation());
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
    public void EntityDtamage(EntityDamageByEntityEvent event) {
        if (!EventoUtils.evento) return;
if (!EventoUtils.staff) return;
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();

        if (!EventoUtils.game.contains(player.getName())) return;
        if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            if (!EventoUtils.pvp) {
                event.setCancelled(true);
            }
            if (!((Player)event.getEntity()).hasPermission("kombo.cmd.report") && !((Player)event.getDamager()).hasPermission("kombo.cmd.report"))  {
            	
            	event.setCancelled(true);
            	event.getDamager().sendMessage(ChatColor.RED + "Você não pode hitar alguém do seu time!");
            }
if (((Player)event.getEntity()).hasPermission("kombo.cmd.report") && ((Player)event.getDamager()).hasPermission("kombo.cmd.report"))  {
            	
            	event.setCancelled(true);
            	event.getDamager().sendMessage(ChatColor.RED + "Você não pode hitar alguém do seu time!");
            }
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
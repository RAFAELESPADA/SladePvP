package net.helix.pvp.kit.provider;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.Habilidade;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.HelixKit2;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.helix.pvp.warp.HelixWarp;
import net.md_5.bungee.api.ChatColor;


public final class GladiatorListener extends KitHandler
{
    private static String prefix;
    public static final HashMap<String, Location> oldLocation;
    public static final HashMap<Player, Player> combateGlad;
    public static final HashMap<String, List<Location>> blocks;
    public static int id;
    
    static {
        GladiatorListener.prefix = ChatColor.BLUE + "GLAD ";
        oldLocation = new HashMap<String, Location>();
        combateGlad = new HashMap<Player, Player>();
        blocks = new HashMap<String, List<Location>>();
        GladiatorListener.id = 0;
    }
    @Override
	public void execute(Player player) {
		super.execute(player);
		
		player.getInventory().setItem(1, new ItemBuilder("§bPuxar!", Material.IRON_FENCE)
				.nbt("kit-handler", "glad")
				.nbt("cancel-drop")
				.toStack()
		);
	}
    
    public static final void showPlayer(final Player one, final Player two) {
        one.showPlayer(two);
        two.showPlayer(one);
    }
    
    @EventHandler
    public void ender(final PlayerTeleportEvent e) {
        final Player p = e.getPlayer();
        if (GladiatorListener.combateGlad.containsKey(p) && e.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            e.setCancelled(true);
        }
    }
    
    
    @EventHandler(priority = EventPriority.MONITOR)
    public final void onGladiatorListener(final PlayerInteractEntityEvent e) {
        final Player bp = e.getPlayer();
        if (e.getRightClicked() instanceof Player) {
            final Player toGlad = (Player)e.getRightClicked();
            if (!KitManager.getPlayer(bp.getName()).hasKit(this) || !ItemBuilder.has(bp.getItemInHand(), "kit-handler", "glad") || bp.getItemInHand().getType() != Material.IRON_FENCE) {
            	return;
            }
                if (toGlad.getGameMode() == GameMode.CREATIVE) {
                    return;
                }
                if (bp.getLocation().getY() > (HelixPvP.getInstance().getConfig().getInt("GladAltura")) && EnderMageReal.isSpawn(bp.getLocation())) {
                    bp.sendMessage(String.valueOf(prefix) + " §cYou cant challenge people on spawn!");
                    return;
                }
                Entity passenger = toGlad.getPassenger();
                if (passenger != null) {
                	bp.sendMessage(String.valueOf(prefix) + " §cYou cant challenge this player!");
                	return;
                }
                if (GladiatorListener.combateGlad.containsKey(bp) || net.helixpvp.kit2.GladiatorListener.combateGlad.containsKey(bp)) {
					bp.sendMessage("§cYou are already on gladiator.");
					return;
				}
				if (KitManager.getPlayer(toGlad.getName()).hasKit(HelixKit.NEO) || KitManager2.getPlayer(toGlad.getName()).haskit2(HelixKit2.NEO)) {
					bp.playSound(bp.getLocation(), Sound.NOTE_BASS_DRUM, 15.0f, 15.0f);
					bp.sendMessage(ChatColor.RED + "You cant challenge " + ChatColor.DARK_RED + toGlad.getName() + ChatColor.RED + " because he has the kit" + ChatColor.DARK_RED + " NEO");
					return;
				}
				if (KitManager.getPlayer(toGlad.getName()).hasKit(HelixKit.JUMPER)) {
					bp.playSound(bp.getLocation(), Sound.NOTE_BASS_DRUM, 15.0f, 15.0f);
					bp.sendMessage(ChatColor.RED + "You cant challenge " + ChatColor.DARK_RED + toGlad.getName() + ChatColor.RED + " because he has the kit" + ChatColor.DARK_RED + " JUMPER");
					return;
				}
                GladiatorListener.combateGlad.put(bp, toGlad);
                GladiatorListener.combateGlad.put(toGlad, bp);
                newGladiatorListenerArena(bp, toGlad, bp.getLocation());
            }
        }
   
    
    public static final Object newGladiatorListenerArena(final Player p1, final Player p2, final Location loc) {
        if (GladiatorListener.id > 15) {
            GladiatorListener.id = 0;
        }
        ++GladiatorListener.id;
        double x = loc.getX();
        final double y = loc.getY();
        double z = loc.getZ();
        final Random random = new Random();
        double x1 = x;
        double z1 = z;
        final Location loc2 = new Location(p1.getWorld(), x1, y + 60, z1);
        final Location loc3 = new Location(p1.getWorld(), x1, y + 60.0, z1);
        final Location loc4 = new Location(p1.getWorld(), x1, y + 60.0, z1);
        loc2.getWorld().refreshChunk(loc2.getChunk().getX(), loc2.getChunk().getZ());
        final List<Location> location = new ArrayList<Location>();
        location.clear();
        for (int blockX = -9; blockX <= 9; ++blockX) {
            for (int blockZ = -9; blockZ <= 9; ++blockZ) {
                for (int blockY = -1; blockY <= 9; ++blockY) {
                    final Block b = loc2.clone().add((double)blockX, (double)blockY, (double)blockZ).getBlock();
                    if (!b.isEmpty()) {
                        final Location newLoc = new Location(p1.getWorld(), loc2.getBlockX() + x, y + 50, loc2.getBlockZ() + z);
                        return newGladiatorListenerArena(p1, p2, newLoc);
                    }
                    if (blockY == 9) {
                        location.add(loc2.clone().add((double)blockX, (double)blockY, (double)blockZ));
                    }
                    else if (blockY == -1) {
                        location.add(loc2.clone().add((double)blockX, (double)blockY, (double)blockZ));
                    }
                    else if (blockX == -9 || blockZ == -9 || blockX == 9 || blockZ == 9) {
                        location.add(loc2.clone().add((double)blockX, (double)blockY, (double)blockZ));
                    }
                }
            }
        }
        for (final Location arena : location) {
            arena.getBlock().setTypeIdAndData(20, (byte)0, true);
        }
        GladiatorListener.oldLocation.put(p1.getName(), p1.getLocation());
        GladiatorListener.oldLocation.put(p2.getName(), p2.getLocation());
        GladiatorListener.blocks.put(p1.getName(), location);
        GladiatorListener.blocks.put(p2.getName(), location);
        p1.teleport(new Location(p1.getWorld(), loc3.getX() + 5.5, loc3.getY() + 1.0, loc3.getZ() + 5.5, 140.0f, 0.0f));
        p2.teleport(new Location(p2.getWorld(), loc4.getX() - 5.5, loc4.getY() + 1.0, loc2.getZ() - 5.5, -40.0f, 0.0f));
        p1.sendMessage(String.valueOf(GladiatorListener.prefix) + "§fYou challenge §e" + p2.getName() + " §fto a 1v1 in Gladiator!");
        p2.sendMessage(String.valueOf(GladiatorListener.prefix) + "§fYou challenge §e" + p1.getName() + " §fto a 1v1 in Gladiator!");
        showPlayer(p1, p2);
        Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
        	public void run() {
        		if (combateGlad.containsKey(p1) && combateGlad.containsKey(p2)) {
        		Kangaroo.darEfeito(p1, PotionEffectType.WITHER, 9999, 1);
        		Kangaroo.darEfeito(p2, PotionEffectType.WITHER, 9999, 1);
        	}
        	}
    }, 20 * 60 *5L);
        return null;
        }
    
    public static final void resetGladiatorListenerByKill(final Player winner, final Player loser) {
        for (int i = 1; i < 5; ++i) {
            winner.teleport((Location)GladiatorListener.oldLocation.get(winner.getName()));
        }
        for (int i = 1; i < 5; ++i) {
            loser.teleport((Location)GladiatorListener.oldLocation.get(loser.getName()));
        }
        for (final PotionEffect pot : winner.getActivePotionEffects()) {
            winner.removePotionEffect(pot.getType());
        }
        for (final PotionEffect pot : loser.getActivePotionEffects()) {
            loser.removePotionEffect(pot.getType());
        }
        for (final Location loc : GladiatorListener.blocks.get(winner.getName())) {
            loc.getBlock().setType(Material.AIR);
        }
        for (final Location loc : GladiatorListener.blocks.get(loser.getName())) {
            loc.getBlock().setType(Material.AIR);
        }
        GladiatorListener.blocks.remove(winner.getName());
        GladiatorListener.oldLocation.remove(winner.getName());
        GladiatorListener.blocks.remove(loser.getName());
        GladiatorListener.oldLocation.remove(loser.getName());
        GladiatorListener.combateGlad.remove(winner);
        GladiatorListener.combateGlad.remove(loser);
        winner.sendMessage(String.valueOf(GladiatorListener.prefix) + "§fYou win the battle against §e" + loser.getName());
        loser.sendMessage(String.valueOf(GladiatorListener.prefix) + "§fYou lose the battle against §e" +  winner.getName());
    }
    
    public static final void resetGladiatorListenerByScreenshare(final Player winner, final Player loser) {
    	if (HelixWarp.GLADIATOR.hasPlayer(winner.getName())) {
    		HelixWarp.GLADIATOR.send(winner, true);
    		HelixWarp.GLADIATOR.send(loser, true);
    		return;
    	}
        for (int i = 1; i < 5; ++i) {
            winner.teleport((Location)GladiatorListener.oldLocation.get(winner.getName()));
        }
        for (final PotionEffect pot : winner.getActivePotionEffects()) {
            winner.removePotionEffect(pot.getType());
        }
        for (final PotionEffect pot : loser.getActivePotionEffects()) {
            loser.removePotionEffect(pot.getType());
        }
        for (final Location loc : GladiatorListener.blocks.get(winner.getName())) {
            loc.getBlock().setType(Material.AIR);
        }
        for (final Location loc : GladiatorListener.blocks.get(loser.getName())) {
            loc.getBlock().setType(Material.AIR);
        }
        GladiatorListener.blocks.remove(winner.getName());
        GladiatorListener.oldLocation.remove(winner.getName());
        GladiatorListener.blocks.remove(loser.getName());
        GladiatorListener.oldLocation.remove(loser.getName());
        loser.sendMessage(String.valueOf(GladiatorListener.prefix) + "§fVoc\u00ea perdeu o combate pois foi puxado para screeshare.");
        winner.sendMessage(String.valueOf(GladiatorListener.prefix) + "§e" + loser.getName() + " §ffoi puxado para screenshare.");
    }
    
    public static final void resetGladiatorListenerByQuit(final Player winner, final Player loser) {
      {
    	  if (HelixWarp.GLADIATOR.hasPlayer(winner.getName())) {
      		HelixWarp.GLADIATOR.send(winner, true);
      		HelixWarp.GLADIATOR.send(loser, true);
      		return;
      	}
        	if (winner != null) {
            winner.teleport((Location)GladiatorListener.oldLocation.get(winner.getName()));
            winner.getActivePotionEffects().forEach(potion -> winner.removePotionEffect(potion.getType()));	
        }  
        	if (loser != null) {
        		loser.getActivePotionEffects().forEach(potion -> loser.removePotionEffect(potion.getType()));	
        }
        	
        	if (blocks.get(winner.getName()) != null) {
        for (final Location loc : GladiatorListener.blocks.get(winner.getName())) {
        	if (winner != null) {
            loc.getBlock().setType(Material.AIR);
        	}
        }
        }
        	if (blocks.get(loser.getName()) != null) {
        for (final Location loc : GladiatorListener.blocks.get(loser.getName())) {
        	if (loser != null) {
            loc.getBlock().setType(Material.AIR);
        }
        }
        }
        GladiatorListener.blocks.remove(winner.getName());
        GladiatorListener.oldLocation.remove(winner.getName());
        GladiatorListener.blocks.remove(loser.getName());
        GladiatorListener.oldLocation.remove(loser.getName());
        GladiatorListener.combateGlad.remove(winner);
        GladiatorListener.combateGlad.remove(loser);
        if (winner != null) {
        winner.sendMessage(String.valueOf(GladiatorListener.prefix) + "§fThe player §e" +  loser.getName() + " §flogged out.");
    }
      }
        }
      public static final void resetGladiatorListenerByGlad(final Player winner, final Player loser) {
          {
            	if (winner != null) {
                winner.getActivePotionEffects().forEach(potion -> winner.removePotionEffect(potion.getType()));	
            }  
            	if (loser != null) {
            		loser.getActivePotionEffects().forEach(potion -> loser.removePotionEffect(potion.getType()));	
            }
            	
            	if (blocks.get(winner.getName()) != null) {
            for (final Location loc : GladiatorListener.blocks.get(winner.getName())) {
            	if (winner != null) {
                loc.getBlock().setType(Material.AIR);
            	}
            }
            }
            	if (blocks.get(loser.getName()) != null) {
            for (final Location loc : GladiatorListener.blocks.get(loser.getName())) {
            	if (loser != null) {
                loc.getBlock().setType(Material.AIR);
            }
            }
            }
            GladiatorListener.blocks.remove(winner.getName());
            GladiatorListener.oldLocation.remove(winner.getName());
            GladiatorListener.blocks.remove(loser.getName());
            GladiatorListener.oldLocation.remove(loser.getName());
            GladiatorListener.combateGlad.remove(winner);
            GladiatorListener.combateGlad.remove(loser);
            if (winner != null) {
            winner.sendMessage(String.valueOf(GladiatorListener.prefix) + "§fYou win the battle against §e" +  loser.getName());
        }
            }
            
        }
        @EventHandler
        public void PlayerMove(PlayerMoveEvent event) {
            if (event.isCancelled()) return;
            Player player = event.getPlayer();
            Block block = event.getTo().getBlock().getRelative(BlockFace.DOWN);
            final Player winner = GladiatorListener.combateGlad.get(player);
            if (!combateGlad.containsKey(player) || !combateGlad.containsKey(winner)) {
            	return;
            }
            if (!block.toString().contains("GLASS") || !block.toString().contains("AIR")) {
            	return;
            }
            resetGladiatorListenerBySpawn(winner , player);
            }
    
    
    public static final void resetGladiatorListenerBySpawn(final Player winner, final Player loser) {
    	if (HelixWarp.GLADIATOR.hasPlayer(winner.getName())) {
    		HelixWarp.GLADIATOR.send(winner, true);
    		HelixWarp.GLADIATOR.send(loser, true);
    		return;
    	}
        for (int i = 1; i < 5; ++i) {
            winner.teleport((Location)GladiatorListener.oldLocation.get(winner.getName()));
        }
        for (final PotionEffect pot : winner.getActivePotionEffects()) {
            winner.removePotionEffect(pot.getType());
        }
        for (final PotionEffect pot : loser.getActivePotionEffects()) {
            loser.removePotionEffect(pot.getType());
        }
        for (final Location loc : GladiatorListener.blocks.get(winner.getName())) {
            loc.getBlock().setType(Material.AIR);
        }
        for (final Location loc : GladiatorListener.blocks.get(loser.getName())) {
            loc.getBlock().setType(Material.AIR);
        }
        GladiatorListener.blocks.remove(winner.getName());
        GladiatorListener.oldLocation.remove(winner.getName());
        GladiatorListener.blocks.remove(loser.getName());
        GladiatorListener.oldLocation.remove(loser.getName());
        GladiatorListener.combateGlad.remove(winner);
        GladiatorListener.combateGlad.remove(loser);
        winner.sendMessage(String.valueOf(GladiatorListener.prefix) + "§fThe player §e" +  loser.getName() + " §fgoes back to spawn.");
    }
}
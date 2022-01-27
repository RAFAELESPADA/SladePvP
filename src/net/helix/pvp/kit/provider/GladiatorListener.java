package net.helix.pvp.kit.provider;


import org.bukkit.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.block.*;
import java.util.*;
import org.bukkit.potion.*;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.Habilidade;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;
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
		
		player.getInventory().setItem(1, new ItemBuilder("�bPuxar!", Material.IRON_FENCE)
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
                if (GladiatorListener.combateGlad.containsKey(bp)) {
                    bp.sendMessage(String.valueOf(prefix) + " �cVoc\u00ea ja esta na arena!");
                    return;
                }
				if (Habilidade.getAbility(toGlad) == "SteelHead") {
					bp.playSound(bp.getLocation(), Sound.NOTE_BASS_DRUM, 15.0f, 15.0f);
					bp.sendMessage(ChatColor.AQUA + "Voc� nao pode puxar " + toGlad.getName() + " porque ele esta com o kit NEO");
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
        double x1 = x + random.nextInt(55600);
        double z1 = z + random.nextInt(99954);
        final Location loc2 = new Location(p1.getWorld(), x1, y + 30, z1);
        final Location loc3 = new Location(p1.getWorld(), x1, y + 30.0, z1 + 8.0);
        final Location loc4 = new Location(p1.getWorld(), x1 - 8.0, y + 30.0, z1 - 8.0);
        loc2.getWorld().refreshChunk(loc2.getChunk().getX(), loc2.getChunk().getZ());
        final List<Location> location = new ArrayList<Location>();
        location.clear();
        for (int blockX = -10; blockX <= 10; ++blockX) {
            for (int blockZ = -10; blockZ <= 10; ++blockZ) {
                for (int blockY = -1; blockY <= 10; ++blockY) {
                    final Block b = loc2.clone().add((double)blockX, (double)blockY, (double)blockZ).getBlock();
                    if (!b.isEmpty()) {
                        x = random.nextInt(-55600);
                        z = random.nextInt(99954);
                        final Location newLoc = new Location(p1.getWorld(), loc2.getBlockX() + x, 50.0, loc2.getBlockZ() + z);
                        return newGladiatorListenerArena(p1, p2, newLoc);
                    }
                    if (blockY == 10) {
                        location.add(loc2.clone().add((double)blockX, (double)blockY, (double)blockZ));
                    }
                    else if (blockY == -1) {
                        location.add(loc2.clone().add((double)blockX, (double)blockY, (double)blockZ));
                    }
                    else if (blockX == -10 || blockZ == -10 || blockX == 10 || blockZ == 10) {
                        location.add(loc2.clone().add((double)blockX, (double)blockY, (double)blockZ));
                    }
                }
            }
        }
        for (final Location arena : location) {
            arena.getBlock().setTypeIdAndData(95, (byte)GladiatorListener.id, true);
        }
        GladiatorListener.oldLocation.put(p1.getName(), p1.getLocation());
        GladiatorListener.oldLocation.put(p2.getName(), p2.getLocation());
        GladiatorListener.blocks.put(p1.getName(), location);
        GladiatorListener.blocks.put(p2.getName(), location);
        p1.teleport(new Location(p1.getWorld(), loc3.getX() + 7.5, loc3.getY() + 1.0, loc3.getZ(), 140.0f, 0.0f));
        p2.teleport(new Location(p2.getWorld(), loc4.getX() + 0.5, loc4.getY() + 1.0, loc2.getZ() - 7.5, -40.0f, 0.0f));
        p1.sendMessage(String.valueOf(GladiatorListener.prefix) + "�fVoce desafiou o player �e" + p2.getName() + " �fpara uma batalha 1v1!");
        p2.sendMessage(String.valueOf(GladiatorListener.prefix) + "�fVoce foi desafiado pelo player �e" + p1.getName() + " �fpara uma batalha 1v1!");
        showPlayer(p1, p2);
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
        winner.sendMessage(String.valueOf(GladiatorListener.prefix) + "�fVoce venceu a batalha contra �e" + loser.getName());
        loser.sendMessage(String.valueOf(GladiatorListener.prefix) + "�fVoce perdeu a batalha contra �e" + winner.getName());
    }
    
    public static final void resetGladiatorListenerByScreenshare(final Player winner, final Player loser) {
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
        loser.sendMessage(String.valueOf(GladiatorListener.prefix) + "�fVoc\u00ea perdeu o combate pois foi puxado para screeshare.");
        winner.sendMessage(String.valueOf(GladiatorListener.prefix) + "�e" + loser.getName() + " �ffoi puxado para screenshare.");
    }
    
    public static final void resetGladiatorListenerByQuit(final Player winner, final Player loser) {
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
        winner.sendMessage(String.valueOf(GladiatorListener.prefix) + "�fO player �e" + loser.getName() + " �fdeslogou.");
    }
    
    public static final void resetGladiatorListenerBySpawn(final Player winner, final Player loser) {
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
        winner.sendMessage(String.valueOf(GladiatorListener.prefix) + "�fO player �e" + loser.getName() + " �fSaiu correndo para o spawn.");
    }
}
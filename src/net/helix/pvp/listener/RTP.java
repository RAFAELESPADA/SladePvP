package net.helix.pvp.listener;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import net.helix.pvp.HelixPvP;



public class RTP {


public static void TeleportArenaRandom(final Player p) {
    final Random dice = new Random();
    final int number = dice.nextInt(4);
    switch (number) {
        case 0: {
        	if (HelixPvP.getInstance().getConfig().getString("arena1.world") == null) {
        		return;
        	}
        	final World w = Bukkit.getServer().getWorld(HelixPvP.getInstance().getConfig().getString("arena1.world"));
            final double x = HelixPvP.getInstance().getConfig().getDouble("arena1.x");
            final double y = HelixPvP.getInstance().getConfig().getDouble("arena1.y");
            final double z = HelixPvP.getInstance().getConfig().getDouble("arena1.z");
            final Location lobby = new Location(w, x, y, z);
            lobby.setPitch((float)HelixPvP.getInstance().getConfig().getDouble("arena1.pitch"));
            lobby.setYaw((float)HelixPvP.getInstance().getConfig().getDouble("arena1.yaw"));
            p.teleport(lobby);
            break;
        }
        case 1: {
        	if (HelixPvP.getInstance().getConfig().getString("arena2.world") == null) {
        		return;
        	}
        	final World w = Bukkit.getServer().getWorld(HelixPvP.getInstance().getConfig().getString("arena2.world"));
            final double x = HelixPvP.getInstance().getConfig().getDouble("arena2.x");
            final double y = HelixPvP.getInstance().getConfig().getDouble("arena2.y");
            final double z = HelixPvP.getInstance().getConfig().getDouble("arena2.z");
            final Location lobby2 = new Location(w, x, y, z);
            lobby2.setPitch((float)HelixPvP.getInstance().getConfig().getDouble("arena2.pitch"));
            lobby2.setYaw((float)HelixPvP.getInstance().getConfig().getDouble("arena2.yaw"));
            p.teleport(lobby2);
            break;
        }
        case 2: {
        	if (HelixPvP.getInstance().getConfig().getString("arena3.world") == null) {
        		return;
        	}
        	final World w = Bukkit.getServer().getWorld(HelixPvP.getInstance().getConfig().getString("arena3.world"));
            final double x = HelixPvP.getInstance().getConfig().getDouble("arena3.x");
            final double y = HelixPvP.getInstance().getConfig().getDouble("arena3.y");
            final double z = HelixPvP.getInstance().getConfig().getDouble("arena3.z");
            final Location lobby3 = new Location(w, x, y, z);
            lobby3.setPitch((float)HelixPvP.getInstance().getConfig().getDouble("arena3.pitch"));
            lobby3.setYaw((float)HelixPvP.getInstance().getConfig().getDouble("arena3.yaw"));
            p.teleport(lobby3);
            break;
        }
        case 3: {
        	if (HelixPvP.getInstance().getConfig().getString("arena4.world") == null) {
        		return;
        	}
        	final World w = Bukkit.getServer().getWorld(HelixPvP.getInstance().getConfig().getString("arena4.world"));
            final double x = HelixPvP.getInstance().getConfig().getDouble("arena4.x");
            final double y = HelixPvP.getInstance().getConfig().getDouble("arena4.y");
            final double z = HelixPvP.getInstance().getConfig().getDouble("arena4.z");
            final Location lobby4 = new Location(w, x, y, z);
            lobby4.setPitch((float)HelixPvP.getInstance().getConfig().getDouble("arena4.pitch"));
            lobby4.setYaw((float)HelixPvP.getInstance().getConfig().getDouble("arena4.yaw"));
            p.teleport(lobby4);
            break;
        }
    }
}
}


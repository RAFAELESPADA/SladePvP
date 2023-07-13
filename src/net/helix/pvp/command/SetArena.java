package net.helix.pvp.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.helix.pvp.HelixPvP;


public class SetArena implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("No console");
            return true;
        }
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("setarena")) {
        	if (!p.hasPermission("kitpvp.setarena")){
        	  	  sender.sendMessage("§c§lERROR: §fYou dont have permission to do that");
        	  	  return true;
        	    }
                if (args.length == 0) {
                    p.sendMessage(String.valueOf("KITPVP") + "Write: /setarena (1|4)");
                    return true;
                }
                if (args[0].equalsIgnoreCase("1")) {
                    p.sendMessage(String.valueOf("KITPVP") + "§aYou seted the ARENA 1");
                    HelixPvP.getInstance().getConfig().set("arena1.x", (Object)p.getLocation().getX());
                    HelixPvP.getInstance().getConfig().set("arena1.y", (Object)p.getLocation().getY());
                    HelixPvP.getInstance().getConfig().set("arena1.z", (Object)p.getLocation().getZ());
                    HelixPvP.getInstance().getConfig().set("arena1.pitch", (Object)p.getLocation().getPitch());
                    HelixPvP.getInstance().getConfig().set("arena1.yaw", (Object)p.getLocation().getYaw());
                    HelixPvP.getInstance().getConfig().set("arena1.world", (Object)p.getLocation().getWorld().getName());
                    HelixPvP.getInstance().saveConfig();
                }
                if (args[0].equalsIgnoreCase("2")) {
                    p.sendMessage(String.valueOf("KITPVP") + "§aYou seted the ARENA 2");
                    HelixPvP.getInstance().getConfig().set("arena2.x", (Object)p.getLocation().getX());
                    HelixPvP.getInstance().getConfig().set("arena2.y", (Object)p.getLocation().getY());
                    HelixPvP.getInstance().getConfig().set("arena2.z", (Object)p.getLocation().getZ());
                    HelixPvP.getInstance().getConfig().set("arena2.pitch", (Object)p.getLocation().getPitch());
                    HelixPvP.getInstance().getConfig().set("arena2.yaw", (Object)p.getLocation().getYaw());
                    HelixPvP.getInstance().getConfig().set("arena2.world", (Object)p.getLocation().getWorld().getName());
                    HelixPvP.getInstance().saveConfig();
                }
                if (args[0].equalsIgnoreCase("3")) {
                    p.sendMessage(String.valueOf("KITPVP") + "§aYou seted the ARENA 3");
                    HelixPvP.getInstance().getConfig().set("arena3.x", (Object)p.getLocation().getX());
                    HelixPvP.getInstance().getConfig().set("arena3.y", (Object)p.getLocation().getY());
                    HelixPvP.getInstance().getConfig().set("arena3.z", (Object)p.getLocation().getZ());
                    HelixPvP.getInstance().getConfig().set("arena3.pitch", (Object)p.getLocation().getPitch());
                    HelixPvP.getInstance().getConfig().set("arena3.yaw", (Object)p.getLocation().getYaw());
                    HelixPvP.getInstance().getConfig().set("arena3.world", (Object)p.getLocation().getWorld().getName());
                    HelixPvP.getInstance().saveConfig();
                }
                if (args[0].equalsIgnoreCase("4")) {
                    p.sendMessage(String.valueOf("KITPVP") + "§aYou seted the ARENA 4");
                    HelixPvP.getInstance().getConfig().set("arena4.x", (Object)p.getLocation().getX());
                    HelixPvP.getInstance().getConfig().set("arena4.y", (Object)p.getLocation().getY());
                    HelixPvP.getInstance().getConfig().set("arena4.z", (Object)p.getLocation().getZ());
                    HelixPvP.getInstance().getConfig().set("arena4.pitch", (Object)p.getLocation().getPitch());
                    HelixPvP.getInstance().getConfig().set("arena4.yaw", (Object)p.getLocation().getYaw());
                    HelixPvP.getInstance().getConfig().set("arena4.world", (Object)p.getLocation().getWorld().getName());
                    HelixPvP.getInstance().saveConfig();
                }
            }
            else {
                p.sendMessage("§c[ERROR] You dont have permission to use that command.");
            }
        {
        return false;
    }
}
}

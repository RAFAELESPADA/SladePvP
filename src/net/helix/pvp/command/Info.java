package net.helix.pvp.command;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;

public class Info implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
        	  sender.sendMessage("§cApenas Players");
        	  return true;
        }
            final Player player = (Player) sender;
            if (!player.hasPermission("kombo.cmd.pinfo")) {
            	 player.sendMessage("§cVocê não tem permissão");
            			 return true;
            }
                if (args.length == 0) {
                    player.sendMessage("§cUtilize §e/pinfo <player>");
                    return true;
                }
                    final Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                    	HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
                				.getPlayer(target.getName());
                        player.sendMessage("§aInformações sobre o player §e" + target.getName() + "§a:");
                        player.sendMessage("§aNick: §f" + target.getName());
                        String kit = KitManager.getPlayer(helixPlayer.getName()).getKit().toString();
                        String kit2 = KitManager2.getPlayer(helixPlayer.getName()).getkit2().toString();
                        player.sendMessage("§aKit: §f" + kit);
                        player.sendMessage("§aKit2: §f" + kit2);
                        player.sendMessage("§aGameMode: §f" + target.getGameMode().name());
                        player.sendMessage("§aFly: §f" + (target.getAllowFlight() ? "§fSim" : "§cNão"));
                       
    }

					return false;
}
}
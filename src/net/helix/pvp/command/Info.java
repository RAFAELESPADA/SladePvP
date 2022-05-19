package net.helix.pvp.command;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.eazynick.api.NickManager;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.pvp.FakeAPI;
import net.helix.pvp.kit.KitManager;

public class Info implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
        	  sender.sendMessage("§cApenas Players");
        	  return true;
        }
            final Player player = (Player) sender;
            if (!player.hasPermission("kombo.cmd.pinfo")) {
            	 player.sendMessage("§cVocÃª nÃ£o tem permissÃ£o");
            			 return true;
            }
                if (args.length == 0) {
                    player.sendMessage("§cUtilize §e/pinfo <player>");
                    return true;
                }
                    final Player target = Bukkit.getPlayer(args[0]);
                    NickManager api = new NickManager(player);
                    NickManager api2 = new NickManager(target);
                    if (target != null && !api.isNicked()) {
                    	HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
                				.getPlayer(target.getName());
                        player.sendMessage("§aInformações sobre o player §e" + target.getName() + "§a:");
                        player.sendMessage("§aNick: §f" + target.getName());
                        String kit = KitManager.getPlayer(helixPlayer.getName()).getKit().toString();
                        player.sendMessage("§aKit: §f" + kit);
                        player.sendMessage("§aGameMode: §f" + target.getGameMode().name());
                        player.sendMessage("§aFly: §f" + (target.getAllowFlight() ? "§fSim" : "§cNÃ£o"));
                       
    }
                    else if (target != null && api.isNicked()) {
                    	HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
                				.getPlayer(target.getName());
                        String fake = FakeAPI.hasFake(target) ? " §e[" + api2.getNickName() + "]" : "";
                        player.sendMessage("§aInformações sobre o player §e" + target.getName() + "§a:");
                        player.sendMessage("§aNick: §f" + (helixPlayer.getName() == api2.getRealName() ? api2.getRealName() : helixPlayer.getName()));
                        player.sendMessage(ChatColor.GREEN + "Fake: " + fake);
                        String kit = KitManager.getPlayer(helixPlayer.getName()).getKit().toString();
                        player.sendMessage("§aKit: §f" + kit);
                        player.sendMessage("§aGameMode: §f" + target.getGameMode().name());
                        player.sendMessage("§aFly: §f" + (target.getAllowFlight() ? "§fSim" : "§cNÃ£o"));
                    }
					return false;
}
}
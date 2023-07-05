package net.helix.pvp.command;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;





public class GiveDeaths implements CommandExecutor { 

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("givedeaths")){
			 Player player = (Player)sender;
			    if (!player.hasPermission("admin.givedeaths")) {
			      player.sendMessage("§cSem permiss§o");
			      return true;
			    }
			
			
			if(args.length < 3){
				sender.sendMessage("§cCorrect usage: /givedeaths give/remove <Player> <Amount>");
				return true;
			}
			
			int tanto = Integer.valueOf(args[2]);
			Player t = Bukkit.getPlayer(args[1]);
			
			if(args[0].equalsIgnoreCase("give")){
				
				try{
					HelixPlayer killerHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(t.getName());
					killerHelixPlayer.getPvp().addDeaths(tanto);
					sender.sendMessage("§aYou give " + tanto + " Deaths to the player " + t.getName());
					t.sendMessage("§e" + tanto + " §aKills has been added to your account!");
				}catch(Exception e){
					sender.sendMessage("§cUse only numbers!");
				}
				
			}else{
				sender.sendMessage("§cUse /givedeaths give <Player> <Amount>");
			}
		if(args[0].equalsIgnoreCase("remove")){
				
				try{
					HelixPlayer killerHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(t.getName());
					killerHelixPlayer.getPvp().setDeaths(killerHelixPlayer.getPvp().getDeaths() - tanto);
					sender.sendMessage("§aYou remove " + tanto + " Deaths of the player " + t.getName());
					t.sendMessage("§e" + tanto + " §aDeaths has been removed from your account!");
				}catch(Exception e){
					sender.sendMessage("§cUse only numbers!");
				}
		}else{
			sender.sendMessage("§cUse /givedeaths remove <Player> <Amount>");
		}
			
			
		}
		return false;
	
}
}

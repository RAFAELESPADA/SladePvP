package net.helix.pvp.command;



import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;





public class GiveKills implements CommandExecutor { 

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("givekills")){
			 Player player = (Player)sender;
			    if (!player.hasPermission("admin.givekills")) {
			      player.sendMessage("§cSem permiss§o");
			      return true;
			    }
			
			
			if(args.length < 3){
				sender.sendMessage("§cCorrect usage: /givekills give/remove <Player> <Amount>");
				return true;
			}
			
			int tanto = Integer.valueOf(args[2]);
			Player t = Bukkit.getPlayer(args[1]);
			
			if(args[0].equalsIgnoreCase("give")){
				
				try{
					HelixPlayer killerHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(t.getName());
					killerHelixPlayer.getPvp().addKills(tanto);
					sender.sendMessage("§aYou give " + tanto + " Kills to the player " + t.getName());
					t.sendMessage("§e" + tanto + " §aKills has been added to your account!");
				}catch(Exception e){
					sender.sendMessage("§cUse only numbers!");
				}
				
			}else{
				sender.sendMessage("§cUse /givekills give <Player> <Amount>");
			}
		if(args[0].equalsIgnoreCase("remove")){
				
				try{
					HelixPlayer killerHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(t.getName());
					killerHelixPlayer.getPvp().setKills(killerHelixPlayer.getPvp().getKills() - tanto);
					sender.sendMessage("§aYou remove " + tanto + " Kills of the player " + t.getName());
					t.sendMessage("§e" + tanto + " §aKills has been removed from your account!");
				}catch(Exception e){
					sender.sendMessage("§cUse only numbers!");
				}
		}else{
			sender.sendMessage("§cUse /givekills remove <Player> <Amount>");
		}
			
			
		}
		return false;
	
}
}

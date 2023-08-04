package net.helix.pvp.command;



import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.helix.pvp.command.BukkitCommandFramework.Command;
import net.helix.pvp.command.BukkitCommandFramework.CommandArgs;

public class ChatCommand extends CommandClass {
	
	public static boolean chat = true;
	
	@Command(name = "chat")
	public void onAdmin(CommandArgs args) {
		if (!args.isPlayer())
			return;
		
		Player player = args.getPlayer();
		String[] a = args.getArgs();
		
		if (player == null)
			return;
		if (!player.hasPermission("kombo.cmd.report")) {
			player.sendMessage("§cVocê não tem autorização!");
			return;
		}
		if (a.length == 0) {
			player.sendMessage("§cUtilize /chat [on] ou [off] ou [clear]!");
			return;
		}
		
		if (a.length == 1) {
			if (a[0].equalsIgnoreCase("on")) {
				if (chat == true) {
					player.sendMessage("§aO chat já está ativo!");
					return;
				}
				chat = true;
				player.sendMessage("§fVocê ativou o §achat§f!");
				 Bukkit.broadcast("§6[STAFF] §7Chat ativado por " + player.getName() , "kombo.cmd.report");
			} else if (a[0].equalsIgnoreCase("off")) {
				if (chat == false) {
					player.sendMessage("§cO chat já está desativado!");
					return;
				}
				chat = false;
				player.sendMessage("§fVocê desativou o §cchat§f!");
				 Bukkit.broadcast("§6[STAFF] §7Chat desativado por " + player.getName() , "kombo.cmd.report");
			} else if (a[0].equalsIgnoreCase("clear")) {
				for (int i = 0; i < 100; i++)
			          Bukkit.broadcastMessage(""); 
			        Bukkit.broadcastMessage("§aChat foi limpo!");
			        Bukkit.broadcast("§6[STAFF] §7Limpo por " + player.getName() , "kombo.cmd.report");
			       
			}
		}
		
	}

}

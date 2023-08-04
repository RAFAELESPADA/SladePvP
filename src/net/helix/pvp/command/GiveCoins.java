package net.helix.pvp.command;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;



@SuppressWarnings("unused")
public class GiveCoins implements CommandExecutor {
	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("givecoins")) {
			if (!sender.hasPermission("cmd.givecoins")) {
				sender.sendMessage("Voce não tem permissao");
			} else {
				if (args.length == 0) {
					sender.sendMessage( "§c§l/givecoins [player] [amount]");
					return true;
				}
				Player target = Bukkit.getPlayerExact(args[0]);
				if ((target == null) || (!(target instanceof Player))) {
					sender.sendMessage( "§c§lThe Player is offline");
					return true;
				}
				if (isNumeric(args[1])) {
					int coins = Integer.parseInt(args[1]);
					HelixPlayer pk = HelixBukkit.getInstance().getPlayerManager().getPlayer(target.getName());
				    pk.getPvp().addCoins(coins);	    
					sender.sendMessage( "§eVocê deu para " + target.getName() + " " + coins
							+ "§bCoins");
					target.sendMessage( "§eVocê recebeu " + coins
							+ " §bCoins");
					target.sendMessage( "§6Sua conta foi atualizada!");
					
				}
			}
		}
		return false;
	}
}

		


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\Coins\Commands.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */

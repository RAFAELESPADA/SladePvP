package net.helix.pvp.command;

import java.util.concurrent.TimeUnit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import net.helix.core.util.HelixCooldown;
import net.helix.pvp.HelixPvP;

public class ReportToggle implements CommandExecutor {
	
	private final HelixPvP plugin;

	public ReportToggle (HelixPvP plugin) {
		this.plugin = plugin;
	}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		if (!sender.hasPermission("kombo.cmd.report")) {
    		sender.sendMessage("§cVocê não tem permissão para executar esse comando!");
    		return true;
    	}
		if (HelixCooldown.inCooldown(sender.getName(), "rt-cmd"))  {
			sender.sendMessage("§cAguarde " + HelixCooldown.getTime(sender.getName(), "rt-cmd") + "s para executar este comando novamente.");
			return true;
		}
		
		Player player = (Player) sender;
		boolean enable = !Report.toggle.containsKey(player.getName());
		
		if (enable) {
			Report.toggle.put(player.getName(), "ATIVADO");
		}else {
			Report.toggle.remove(player.getName());
		}
		
		HelixCooldown.create(sender.getName(), "rt-cmd", TimeUnit.SECONDS, 5);
		player.sendMessage("§bNotificação de Reports " + (enable ? "§aOFF" : "§cON"));
		return true;
	}
}


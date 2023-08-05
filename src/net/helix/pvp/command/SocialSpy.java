package net.helix.pvp.command;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import net.helix.core.util.HelixCooldown;
import net.helix.pvp.HelixPvP;

public class SocialSpy implements CommandExecutor {
	
	private final HelixPvP plugin;

	public SocialSpy (HelixPvP plugin) {
		this.plugin = plugin;
	}
	public static HashMap<String, String> toggle = new HashMap();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		if (!sender.hasPermission("kombo.cmd.report")) {
    		sender.sendMessage("§cVocê não tem permissão para executar esse comando!");
    		return true;
    	}
		if (HelixCooldown.inCooldown(sender.getName(), "rt-cmd2"))  {
			sender.sendMessage("§cAguarde " + HelixCooldown.getTime(sender.getName(), "rt-cmd2") + "s para executar este comando novamente.");
			return true;
		}
		
		Player player = (Player) sender;
		boolean enable = !toggle.containsKey(player.getName());
		
		if (enable) {
			toggle.put(player.getName(), "ATIVADO");
		}else {
			toggle.remove(player.getName());
		}
		
		HelixCooldown.create(sender.getName(), "rt-cmd2", TimeUnit.SECONDS, 5);
		player.sendMessage("§bSocialSpy " + (enable ? "§aOFF" : "§cON"));
		return true;
	}
}


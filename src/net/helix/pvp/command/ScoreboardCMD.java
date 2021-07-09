package net.helix.pvp.command;

import java.util.concurrent.TimeUnit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import net.helix.core.util.HelixCooldown;
import net.helix.pvp.HelixPvP;

public class ScoreboardCMD implements CommandExecutor {
	
	private final HelixPvP plugin;

	public ScoreboardCMD(HelixPvP plugin) {
		this.plugin = plugin;
	}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		
		if (HelixCooldown.inCooldown(sender.getName(), "scoreboard-cmd"))  {
			sender.sendMessage("§cAguarde " + HelixCooldown.getTime(sender.getName(), "scoreboard-cmd") + "s para executar este comando novamente.");
			return true;
		}
		
		Player player = (Player) sender;
		boolean enable = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) == null;
		
		if (enable) {
			plugin.getScoreboardBuilder().build(player);
		}else {
			player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		}
		
		HelixCooldown.create(sender.getName(), "scoreboard-cmd", TimeUnit.SECONDS, 5);
		player.sendMessage(enable ? "§aVocê ativou sua scoreboard." : "§eVocê desativou sua scoreboard.");
		return true;
	}

}

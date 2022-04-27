package net.helix.pvp.command;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class SkitCMD implements Listener, CommandExecutor {
	
	public HashMap<String, ItemStack[]> kit = new HashMap<String, ItemStack[]>();
	public HashMap<String, ItemStack[]> armadura = new HashMap<String, ItemStack[]>();

	private void sendHelp(CommandSender sender) {
		sender.sendMessage(new String[] {
				"§aComandos de skit:",
				"§a/skit <raio> §f- Setar seu invent§rio para os jogadores em um determinado raio."
		});
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cComando apenas para jogadores.");
			return true;
		}
		if (!sender.hasPermission("kombo.cmd.skit")) {
			sender.sendMessage("§cVocê não tem permiss§o.");
			return true;
		}
		if (args.length == 0) {
			sendHelp(sender);
			return true;
		}
		
		int radius;
		
		try {
			radius = Integer.parseInt(args[0]);
		}catch (NumberFormatException ignored) {
			sender.sendMessage("§cDigite um número válido.");
			return true;
		}
		
		if (radius == 0 || radius > 200) {
			sender.sendMessage("§cColoque um número entre 1 - 200.");
			return true;
		}
		
		Player player = (Player) sender;
		
		List<Player> nearbyPlayers = player.getNearbyEntities(radius, radius, radius).stream()
				.filter(entity -> entity instanceof Player)
				.map(entity -> Bukkit.getPlayer(entity.getName()))
				.collect(Collectors.toList());
		
		if  (nearbyPlayers.size() == 0) {
			sender.sendMessage("§cNão há jogadores em um raio de " + radius + " blocos.");
			return true;
		}
		
		nearbyPlayers.forEach(nearbyPlayer -> {
			nearbyPlayer.getInventory().setContents(player.getInventory().getContents());
			nearbyPlayer.getInventory().setArmorContents(player.getInventory().getArmorContents());
			player.updateInventory();
		});
		
		sender.sendMessage("§3Invetário aplicado para " + nearbyPlayers.size() + " jogadores");
		return true;
	}

}

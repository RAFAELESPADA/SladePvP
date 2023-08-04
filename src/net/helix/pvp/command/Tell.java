package net.helix.pvp.command;



import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


import net.helix.pvp.command.BukkitCommandFramework.Command;
import net.helix.pvp.command.BukkitCommandFramework.CommandArgs;
public class Tell extends CommandClass {

	ArrayList<UUID> tell = new ArrayList<>();

	@Command(name = "tell")
	public void onTell(CommandArgs args) {
		if (!args.isPlayer())
			return;

		Player player = args.getPlayer();
		String[] a = args.getArgs();

		if (player == null) {
			return;
		}

		if (a.length == 0) {
			player.sendMessage("§3§lTELL §fUtilize /tell <player> <mensagem>!");
			return;
		}

		if (a.length == 1) {
			if (a[0].equalsIgnoreCase("off")) {
				if (!tell.contains(player.getUniqueId())) {
					tell.add(player.getUniqueId());
					player.sendMessage("§a§lTELL §fVocê está com o tell §c§lOFF!");
				} else {
					player.sendMessage("§a§lTELL §fVocê já está com o tell §c§lOFF!");
				}
			} else if (a[0].equalsIgnoreCase("on")) {
				if (tell.contains(player.getUniqueId())) {
					tell.remove(player.getUniqueId());
					player.sendMessage("§a§lTELL §fVocê está com o tell §a§lON!");
				} else {
					player.sendMessage("§a§lTELL §fVocê já está o tell §a§lON!");
				}
			}
		}

		if (a.length > 1) {
			Player target = Bukkit.getPlayer(a[0]);

			if (target != null) {
				if (player == target) {
					player.sendMessage("§cVocê não pode falar sozinho!");
					return;
				}
				if (!tell.contains(target.getUniqueId())) {

					String message = "";

					for (int i = 1; i < a.length; i++) {
						message += a[i] + " ";
					}

					player.sendMessage("§a§lTELL §5[§f" + target.getName() + "§7 -> §fVocê] §f" + message);
					target.sendMessage(
							"§a§lTELL §5[§f" + player.getName() + " §7-> §fVocê" + "§f] " + message);

				} else {
					player.sendMessage("§a§lTELL §fO jogador está com o tell §c§lOFF!");
				}

			} else {
				player.sendMessage("§a§lTELL §fJogador offline!");
			}
		}
	}

}

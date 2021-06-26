package net.helix.pvp.command;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatCMD implements Listener, CommandExecutor {
	
	public static boolean chat1=true;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("chat")) {
			if (!sender.hasPermission("chat.usar")) {
				sender.sendMessage("§c§lCHAT §fVocê não tem permissão para executar este comando.");
				return true;
			}
			if (args.length == 0) {
				sender.sendMessage("§3§lCHAT §fUtilize §3/chat <on/off/clear>");
				return true;
			}
			if (args.length >= 1) {
				if (args[0].equalsIgnoreCase("clear")) {
					for (int i = 0; i < 1000; i++) {
						Bukkit.broadcastMessage("");
					}
					Bukkit.broadcastMessage("§6§lCHAT §fO chat do servidor foi §6§lLIMPO §fpor §6" + sender.getName() + "§f.");
				}else if (args[0].equalsIgnoreCase("on")) {
					chat1=true;
					Bukkit.broadcastMessage("§6§lCHAT §fO chat do servidor foi §a§lATIVADO §fpor §6" + sender.getName() + "§f.");
				}else if (args[0].equalsIgnoreCase("off")) {
					chat1=false;
					Bukkit.broadcastMessage("§6§lCHAT §fO chat do servidor foi §c§lDESATIVADO §fpor §6" + sender.getName() + "§f.");
				}else {
					sender.sendMessage("§3§lCHAT §fUtilize §3/chat <on/off/clear>");
					return true;
				}
			}
		}
		return true;
	}
	
	@EventHandler
	public void onCommando(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (e.getMessage().contains("http") || (e.getMessage().contains("https"))) {
			if (!p.hasPermission("divulgar.usar")) {
				e.setCancelled(true);
				p.sendMessage("§c§lCHAT §fVocê não tem permissão para divulgar em nosso servidor.");
			}
		}
		if (e.getMessage().contains(".pvp.host")) {
			if (!p.hasPermission("divulgar.usar")) {
				e.setCancelled(true);
				p.sendMessage("§c§lCHAT §fVocê não tem permissão para divulgar em nosso servidor.");
				for (Player s : Bukkit.getOnlinePlayers()) {
					if (s.hasPermission("staffchat.usar")) {
						s.sendMessage("§c§lALERTA §fO jogador §c" + p.getName() + " §fpossivelmente está divulgando: §c" + e.getMessage());
					}
				}
			}
		}
		if (e.getMessage().contains(".com.br")) {
			if (!p.hasPermission("divulgar.usar")) {
				e.setCancelled(true);
				p.sendMessage("§c§lCHAT §fVocê não tem permissão para divulgar em nosso servidor.");
				for (Player s : Bukkit.getOnlinePlayers()) {
					if (s.hasPermission("staffchat.usar")) {
						s.sendMessage("§c§lALERTA §fO jogador §c" + p.getName() + " §fpossivelmente está divulgando: §c" + e.getMessage());
					}
				}
			}
		}
		if (e.getMessage().contains(".virtus.host")) {
			if (!p.hasPermission("divulgar.usar")) {
				e.setCancelled(true);
				p.sendMessage("§c§lCHAT §fVocê não tem permissão para divulgar em nosso servidor.");
				for (Player s : Bukkit.getOnlinePlayers()) {
					if (s.hasPermission("staffchat.usar")) {
						s.sendMessage("§c§lALERTA §fO jogador §c" + p.getName() + " §fpossivelmente está divulgando: §c" + e.getMessage());
					}
				}
			}
		}
		if (e.getMessage().contains(".myserv.nu")) {
			if (!p.hasPermission("divulgar.usar")) {
				e.setCancelled(true);
				p.sendMessage("§c§lCHAT §fVocê não tem permissão para divulgar em nosso servidor.");
				for (Player s : Bukkit.getOnlinePlayers()) {
					if (s.hasPermission("staffchat.usar")) {
						s.sendMessage("§c§lALERTA §fO jogador §c" + p.getName() + " §fpossivelmente está divulgando: §c" + e.getMessage());
					}
				}
			}
		}
		if (e.getMessage().contains(".reishosting.com")) {
			if (!p.hasPermission("divulgar.usar")) {
				e.setCancelled(true);
				p.sendMessage("§c§lCHAT §fVocê não tem permissão para divulgar em nosso servidor.");
				for (Player s : Bukkit.getOnlinePlayers()) {
					if (s.hasPermission("staffchat.usar")) {
						s.sendMessage("§c§lALERTA §fO jogador §c" + p.getName() + " §fpossivelmente está divulgando: §c" + e.getMessage());
					}
				}
			}
		}
		if (e.getMessage().contains(".stevehosting")) {
			if (!p.hasPermission("divulgar.usar")) {
				e.setCancelled(true);
				p.sendMessage("§c§lCHAT §fVocê não tem permissão para divulgar em nosso servidor.");
				for (Player s : Bukkit.getOnlinePlayers()) {
					if (s.hasPermission("staffchat.usar")) {
						s.sendMessage("§c§lALERTA §fO jogador §c" + p.getName() + " §fpossivelmente está divulgando: §c" + e.getMessage());
					}
				}
			}
		}
		if (e.getMessage().contains(".mcnetwork.me")) {
			if (!p.hasPermission("divulgar.usar")) {
				e.setCancelled(true);
				p.sendMessage("§c§lCHAT §fVocê não tem permissão para divulgar em nosso servidor.");
				for (Player s : Bukkit.getOnlinePlayers()) {
					if (s.hasPermission("staffchat.usar")) {
						s.sendMessage("§c§lALERTA §fO jogador §c" + p.getName() + " §fpossivelmente está divulgando: §c" + e.getMessage());
					}
				}
			}
		}
		if (e.getMessage().contains(".com")) {
			if (!p.hasPermission("divulgar.usar")) {
				e.setCancelled(true);
				p.sendMessage("§c§lCHAT §fVocê não tem permissão para divulgar em nosso servidor.");
				for (Player s : Bukkit.getOnlinePlayers()) {
					if (s.hasPermission("staffchat.usar")) {
						s.sendMessage("§c§lALERTA §fO jogador §c" + p.getName() + " §fpossivelmente está divulgando: §c" + e.getMessage());
					}
				}
			}
		}
		if (e.getMessage().contains(".tk")) {
			if (!p.hasPermission("divulgar.usar")) {
				e.setCancelled(true);
				p.sendMessage("§c§lCHAT §fVocê não tem permissão para divulgar em nosso servidor.");
				for (Player s : Bukkit.getOnlinePlayers()) {
					if (s.hasPermission("staffchat.usar")) {
						s.sendMessage("§c§lALERTA §fO jogador §c" + p.getName() + " §fpossivelmente está divulgando: §c" + e.getMessage());
					}
				}
			}
		}
		if (e.getMessage().contains(".mcserv")) {
			if (!p.hasPermission("divulgar.usar")) {
				e.setCancelled(true);
				p.sendMessage("§c§lCHAT §fVocê não tem permissão para divulgar em nosso servidor.");
				for (Player s : Bukkit.getOnlinePlayers()) {
					if (s.hasPermission("staffchat.usar")) {
						s.sendMessage("§c§lALERTA §fO jogador §c" + p.getName() + " §fpossivelmente está divulgando: §c" + e.getMessage());
					}
				}
			}
		}
		if (e.getMessage().contains(".enxadahost")) {
			if (!p.hasPermission("divulgar.usar")) {
				e.setCancelled(true);
				p.sendMessage("§c§lCHAT §fVocê não tem permissão para divulgar em nosso servidor.");
				for (Player s : Bukkit.getOnlinePlayers()) {
					if (s.hasPermission("staffchat.usar")) {
						s.sendMessage("§c§lALERTA §fO jogador §c" + p.getName() + " §fpossivelmente está divulgando: §c" + e.getMessage());
					}
				}
			}
		}
		if (e.getMessage().contains(".batt.host")) {
			if (!p.hasPermission("divulgar.usar")) {
				e.setCancelled(true);
				p.sendMessage("§c§lCHAT §fVocê não tem permissão para divulgar em nosso servidor.");
				for (Player s : Bukkit.getOnlinePlayers()) {
					if (s.hasPermission("staffchat.usar")) {
						s.sendMessage("§c§lALERTA §fO jogador §c" + p.getName() + " §fpossivelmente está divulgando: §c" + e.getMessage());
					}
				}
			}
		}
	}
}

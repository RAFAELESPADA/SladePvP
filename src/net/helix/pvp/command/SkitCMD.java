package net.helix.pvp.command;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class SkitCMD implements Listener, CommandExecutor {
	
	public HashMap<String, ItemStack[]> kit = new HashMap<String, ItemStack[]>();
	public HashMap<String, ItemStack[]> armadura = new HashMap<String, ItemStack[]>();
	
	public boolean isInt(String s) {
		try
		{
	Integer.parseInt(s);
	return true;
		}
	catch (NumberFormatException localNumberFormatException) {}
	return false;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("skit")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§c§lSKIT §fComando apenas para jogadores.");
				return true;
			}
			Player p = (Player) sender;
			if (!p.hasPermission("skit.usar")) {
				p.sendMessage("§c§lSKIT §fVoce nao tem permissão para executar este comando.");
				return true;
			}
			if (args.length == 0) {
				p.sendMessage("§3§lSKIT §fUtilize §3/skit aplicar <raio>");
				p.sendMessage("§3§lSKIT §fUtilize §3/skit set <jogador>");
				return true;
			}
			if (args.length == 1) {
				p.sendMessage("§3§lSKIT §fUtilize §3/skit aplicar <raio>");
				p.sendMessage("§3§lSKIT §fUtilize §3/skit set <jogador>");
				return true;
			}
			if (args.length >= 2) {
				if (args[0].equalsIgnoreCase("aplicar")) {
					if (isInt(args[1])) {
						kit.put("PvP", p.getInventory().getContents());
						armadura.put("PvP", p.getInventory().getArmorContents());
						int numero = Integer.parseInt(args[1]);
						for (Entity ent : p.getNearbyEntities(numero, numero, numero)) {
							Player plr = (Player)ent;
							
							
							plr.getInventory().setArmorContents((ItemStack[])this.armadura.get("PvP"));
							plr.getInventory().setContents((ItemStack[])this.kit.get("PvP"));
						}
						p.sendMessage("§6§lSKIT §fVocê aplicou seu invent§rio em um raio de §6" + args[1] + " §fblocos!");
					}else {
						p.sendMessage("§c§lSKIT §fO argumento §c" + args[0] + " §fnão é um n§mero.");
					}
				}else if (args[0].equalsIgnoreCase("set")) {
					Player t = Bukkit.getPlayer(args[1]);
					if (t == null) {
						p.sendMessage("§c§lSKIT §fJogador nao encontrado.");
					}else {
						kit.put("PvP2", p.getInventory().getContents());
						armadura.put("PvP2", p.getInventory().getArmorContents());
						
						net.helix.pvp.kit.All.kit.add(t);
						net.helix.pvp.kit.All.ninja.remove(t.getName());
						net.helix.pvp.kit.All.anchor.remove(t.getName());
						net.helix.pvp.kit.All.fireman.remove(t.getName());
						net.helix.pvp.kit.All.ajnin.remove(t.getName());
						net.helix.pvp.kit.All.viper.remove(t.getName());
						net.helix.pvp.kit.All.thor.remove(t.getName());
						net.helix.pvp.kit.All.q.remove(t.getName());
						
						t.getInventory().setArmorContents((ItemStack[])this.armadura.get("PvP2"));
						t.getInventory().setContents((ItemStack[])this.kit.get("PvP2"));
						
						p.sendMessage("§6§lSKIT §fVoce aplicou seu inventario no jogador §6" + t.getName() + "§f.");
						
					}
				}else {
					p.sendMessage("§3§lSKIT §fUtilize §3/skit aplicar <raio>");
					p.sendMessage("§3§lSKIT §fUtilize §3/skit set <jogador>");
				}
			}
		}
		return true;
	}

}

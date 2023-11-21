package net.helix.pvp.command;


import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.PrefixNode;
import net.luckperms.api.query.QueryOptions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.tablist.SortingManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TagCommand implements CommandExecutor {

	Map<String, Long> cooldowns = new HashMap<String, Long>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
       SortingManager S =  TabAPI.getInstance().getSortingManager();
        LuckPerms api = LuckPermsProvider.get();
        TabAPI apitab = TabAPI.getInstance();
        Player player = (Player) sender;
        Set<Group> groups = api.getGroupManager().getLoadedGroups();
    	List<String> groupsList = new ArrayList<>();
        if (args.length == 0) {
        	
        	       
        	for(Group group : groups) {
        	    groupsList.add(group.getName());
        	}
Player p = (Player)sender;
if (p.hasPermission("helix.tag.manager")) {
    ChatInterativo.Comando(p.getName(), "§4§lOWNER", "/tag manager", "§eExample: §4§lOWNER §7" + p.getName());
    }
if (p.hasPermission("helix.tag.manager")) {
    ChatInterativo.Comando(p.getName(), "§5§lMANAGER", "/tag manager", "§eExample: §5§lMANAGER §7" + p.getName());
    }
                    if (p.hasPermission("helix.tag.admin")) {
                    ChatInterativo.Comando(p.getName(), "§4§lADMIN", "/tag admin", "§eExample: §c§lADMIN §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.coord")) {
                    ChatInterativo.Comando(p.getName(), "§3§lMOD+", "/tag srmod", "§eExample: §3§lSRMOD §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.invest")) {
                    ChatInterativo.Comando(p.getName(), "§a§lINVEST", "/tag invest", "§eExample: §a§lINVEST §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.mod")) {
                    ChatInterativo.Comando(p.getName(), "§2§lMOD", "/tag mod", "§eExample: §2§lMOD §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.helper")) {
                    ChatInterativo.Comando(p.getName(), "§e§lHELPER", "/tag helper", "§eExample: §e§lHELPER §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.builder")) {
                        ChatInterativo.Comando(p.getName(), "§1§lBUILDER", "/tag builder", "§eExample: §1§lBUILDER §7" + p.getName());
                        }
                    if (p.hasPermission("helix.tag.bughunter")) {
                    ChatInterativo.Comando(p.getName(), "§8§lBUGHUNTER", "/tag bughunter", "§eExample: §8§lBUGHUNTER §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.yt+")) {
                    ChatInterativo.Comando(p.getName(), "§b§lYT§6§l+", "/tag yt+", "§eExample: §b§lYT§6§l+ §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.yt")) {
                    ChatInterativo.Comando(p.getName(), "§c§lYT", "/tag yt", "§eExample: §c§lYT §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.streamer")) {
                    ChatInterativo.Comando(p.getName(), "§5§lSTREAMER", "/tag streamer", "§eExample: §5§lSTREAMER §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.creator")) {
                    ChatInterativo.Comando(p.getName(), "§b§lCREATOR", "/tag creator", "§eExample: §b§lCREATOR §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.miniyt")) {
                    ChatInterativo.Comando(p.getName(), "§c§lMINIYT", "/tag miniyt", "§eExample: §c§lMINIYT §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.beta")) {
                    ChatInterativo.Comando(p.getName(), "§e§lBETA", "/tag beta", "§eExample: §e§lBETA §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.diamond")) {
                    ChatInterativo.Comando(p.getName(), "§3§lDIAMOND", "/tag diamond", "§eExample: §3§lDIAMOND §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.emerald")) {
                    ChatInterativo.Comando(p.getName(), "§a§lEMERALD", "/tag emerald", "§eExample: §a§lEMERALD §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.gold")) {
                    ChatInterativo.Comando(p.getName(), "§6§lGOLD", "/tag gold", "§eExample: §6§lGOLD §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.iron")) {
                        ChatInterativo.Comando(p.getName(), "§f§lIRON", "/tag iron", "§eExample: §f§lIRON §7" + p.getName());
                        }
                    if (p.hasPermission("helix.tag.booster")) {
                    ChatInterativo.Comando(p.getName(), "§0§lBOOSTER", "/tag booster", "§eExample: §0§lBOOSTER §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.friend")) {
                        ChatInterativo.Comando(p.getName(), "§c§lFRIEND", "/tag friend", "§eExample: §c§lFRIEND §7" + p.getName());
                        }
                    if (p.hasPermission("helix.tag.2025")) {
                        ChatInterativo.Comando(p.getName(), "§a§l2025", "/tag 2025", "§eExample: §a§l2025 §7" + p.getName());
                        }
                    if (p.hasPermission("helix.tag.2024")) {
                        ChatInterativo.Comando(p.getName(), "§1§l2024", "/tag 2024", "§eExample: §1§l2024 §7" + p.getName());
                        }
                    if (p.hasPermission("helix.tag.2023")) {
                        ChatInterativo.Comando(p.getName(), "§b§l2023", "/tag 2023", "§eExample: §b§l2023 §7" + p.getName());
                        }
                    if (p.hasPermission("helix.tag.natal")) {
                        ChatInterativo.Comando(p.getName(), "§c§lNATAL", "/tag natal", "§eExample: §c§lNATAL §7" + p.getName());
                        }
                    if (p.hasPermission("helix.tag.winner")) {
                        ChatInterativo.Comando(p.getName(), "§2§lWINNER", "/tag winner", "§eExample: §2§lWINNER §7" + p.getName());
                        }
                    if (p.hasPermission("helix.tag.top1")) {
                    ChatInterativo.Comando(p.getName(), "§9§lTOP1", "/tag top1", "§eExample: §9§lTOP1 §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.supporter")) {
                        ChatInterativo.Comando(p.getName(), "§b§lSUPPORTER", "/tag supporter", "§eExample: §b§lSUPPORT §7" + p.getName());
                        }
                    if (p.hasPermission("helix.tag.loser")) {
                        ChatInterativo.Comando(p.getName(), "§8§lLOSER", "/tag loser", "§eExample: §8§lLOSER §7" + p.getName());
                        }
                    
                    ChatInterativo.Comando(p.getName(), "§7§lMEMBER", p.hasPermission("helix.tag.membro") ? "/tag membro" : "/tag default", "§eExample: §7" + p.getName());
                    return true;
              }

            
        


        if (!player.hasPermission("helix.tag." + args[0])) {
            player.sendMessage("§cVocê não tem permissão.");
            return true;
        }
        if (cooldowns.containsKey(player.getName() + "EPT")) {
            if (cooldowns.get(player.getName() + "EPT") > System.currentTimeMillis()) {
                long timeleft = (cooldowns.get(player.getName() + "EPT") - System.currentTimeMillis()) / 1000;
                player.sendMessage(ChatColor.RED + "Wait " + timeleft +" seconds to change your tag again!");
                return true;
            }
        }

try {
	
        String prefix = api.getGroupManager().getGroup(args[0]).getCachedData().getMetaData().getPrefix();

        // Get an OfflinePlayer object for the player

       


        // Load, modify & save the user in LuckPerms.
        api.getUserManager().modifyUser(player.getUniqueId(), (User user) -> {

            // Remove all other prefixes the user had before.
            user.data().clear(NodeType.PREFIX::matches);

            // Find the highest priority of their other prefixes
            // We need to do this because they might inherit a prefix from a parent group,
            // and we want the prefix we set to override that!
            Map<Integer, String> inheritedPrefixes = user.getCachedData().getMetaData(QueryOptions.nonContextual()).getPrefixes();
            int priority = inheritedPrefixes.keySet().stream().mapToInt(i -> i + 10).max().orElse(10);

            // Create a node to add to the player.
            Node node = PrefixNode.builder(prefix, priority).build();

            // Add the node to the user.
            user.data().add(node);

            // Tell the sender.
            cooldowns.put(player.getName() + "EPT", System.currentTimeMillis() + 7 * 1000);
            sender.sendMessage("§a§lTAG: §fYour tag has been changed to " + (args[0].equalsIgnoreCase("Membro") || (args[0].equalsIgnoreCase("default")) ? "§7§lMEMBER" : ChatColor.RESET + prefix.replace("&", "§")));
            apitab.getPlayer(player.getName()).setTemporaryGroup(args[0]);
            Bukkit.getConsoleSender().sendMessage(player.getName() + " alterou a tag para " + args[0]);
        });
} catch (NullPointerException e) {
	sender.sendMessage(ChatColor.RED + "§4§lTAG: Tag not found!");
}

        return true;
    }
}
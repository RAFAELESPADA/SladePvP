package net.helix.pvp.command;


import net.helix.core.account.HelixRole;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.api.NameTagAPI;
import net.helix.core.util.HelixCooldown;
import net.helix.core.util.HelixCooldown2;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.MetaNode;
import net.luckperms.api.node.types.PrefixNode;
import net.luckperms.api.query.QueryOptions;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lombok.AllArgsConstructor;
import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.api.tablist.SortingManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class TagCommand implements CommandExecutor {


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
if (p.hasPermission("helix.tag.dono")) {
    ChatInterativo.Comando(p.getName(), "§4§lDONO", "/tag dono", "§eExemplo: §4§lDONO §7" + p.getName());
    }        
if (p.hasPermission("helix.tag.diretor")) {
    ChatInterativo.Comando(p.getName(), "§9§lDIRETOR", "/tag diretor", "§eExemplo: §9§lDIRETOR §7" + p.getName());
    }
                    if (p.hasPermission("helix.tag.admin")) {
                    ChatInterativo.Comando(p.getName(), "§b§lADMIN", "/tag admin", "§eExemplo: §b§lADMIN §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.coord")) {
                    ChatInterativo.Comando(p.getName(), "§3§lCOORD", "/tag coord", "§eExemplo: §3§lCOORD §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.invest")) {
                    ChatInterativo.Comando(p.getName(), "§a§lINVEST", "/tag invest", "§eExemplo: §a§lINVEST §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.mod")) {
                    ChatInterativo.Comando(p.getName(), "§2§lMOD", "/tag mod", "§eExemplo: §2§lMOD §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.helper")) {
                    ChatInterativo.Comando(p.getName(), "§e§lHELPER", "/tag helper", "§eExemplo: §e§lHELPER §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.estagiario")) {
                    ChatInterativo.Comando(p.getName(), "§d§lESTÁGIARIO", "/tag estagiario", "§eExemplo: §d§lESTÁGIO §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.builder")) {
                        ChatInterativo.Comando(p.getName(), "§1§lBUILDER", "/tag builder", "§eExemplo: §1§lBUILDER §7" + p.getName());
                        }
                    if (p.hasPermission("helix.tag.bughunter")) {
                    ChatInterativo.Comando(p.getName(), "§8§lBUGHUNTER", "/tag bughunter", "§eExemplo: §8§lBUGHUNTER §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.yt+")) {
                    ChatInterativo.Comando(p.getName(), "§b§lYT§6§l+", "/tag yt+", "§eExemplo: §b§lYT§6§l+ §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.yt")) {
                    ChatInterativo.Comando(p.getName(), "§c§lYT", "/tag yt", "§eExemplo: §c§lYT §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.streamer")) {
                    ChatInterativo.Comando(p.getName(), "§5§lSTREAMER", "/tag streamer", "§eExemplo: §5§lSTREAMER §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.creator")) {
                    ChatInterativo.Comando(p.getName(), "§b§lCREATOR", "/tag creator", "§eExemplo: §b§lCREATOR §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.miniyt")) {
                    ChatInterativo.Comando(p.getName(), "§c§lMINIYT", "/tag miniyt", "§eExemplo: §c§lMINIYT §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.beta")) {
                    ChatInterativo.Comando(p.getName(), "§e§lBETA", "/tag beta", "§eExemplo: §e§lBETA §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.diamond")) {
                    ChatInterativo.Comando(p.getName(), "§3§lDIAMOND", "/tag diamond", "§eExemplo: §3§lDIAMOND §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.emerald")) {
                    ChatInterativo.Comando(p.getName(), "§a§lEMERALD", "/tag emerald", "§eExemplo: §a§lEMERALD §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.gold")) {
                    ChatInterativo.Comando(p.getName(), "§6§lGOLD", "/tag gold", "§eExemplo: §6§lGOLD §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.iron")) {
                        ChatInterativo.Comando(p.getName(), "§f§lIRON", "/tag iron", "§eExemplo: §f§lIRON §7" + p.getName());
                        }
                    if (p.hasPermission("helix.tag.booster")) {
                    ChatInterativo.Comando(p.getName(), "§0§lBOOSTER", "/tag booster", "§eExemplo: §0§lBOOSTER §7" + p.getName());
                    }
                    if (p.hasPermission("helix.tag.top1")) {
                    ChatInterativo.Comando(p.getName(), "§9§lTOP1", "/tag top1", "§eExemplo: §9§lTOP1 §7" + p.getName());
                    }
                    ChatInterativo.Comando(p.getName(), "§7§lMEMBRO", "/tag default", "§eExemplo: §7" + p.getName());
                    return true;
              }

            
        


        if (!player.hasPermission("helix.tag." + args[0])) {
            player.sendMessage("§cVocê não tem permissão.");
            return true;
        }
        if (HelixCooldown.inCooldown(player.getName(), "tag")) {
            sender.sendMessage("§cVocê está trocando de tag muito rápido, aguarde alguns intantes.");
            return true;
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
            HelixCooldown.create(player.getName(), "tag", TimeUnit.SECONDS, 7);
            sender.sendMessage("§a§lTAG: §fa sua tag foi alterada para " + (args[0].equalsIgnoreCase("Membro") || (args[0].equalsIgnoreCase("default")) ? "§7§lMEMBRO" : ChatColor.RESET + prefix.replace("&", "§")));
            apitab.getPlayer(player.getName()).setTemporaryGroup(args[0]);
            Bukkit.getConsoleSender().sendMessage(player.getName() + " alterou a tag para " + args[0]);
        });
} catch (NullPointerException e) {
	sender.sendMessage(ChatColor.RED + "§4§lTAG: Tag não encontrada!");
}

        return true;
    }
}
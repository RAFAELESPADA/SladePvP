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

            player.sendMessage("§cUtilize: /tag <Tag>");
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
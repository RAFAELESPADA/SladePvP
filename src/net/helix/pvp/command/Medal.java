package net.helix.pvp.command;


import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.pvp.listener.Medals;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Medal implements CommandExecutor {
	
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Apenas players");
            return true;
        }
        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("medalha")) {
            if (args.length == 0) {
                player.sendMessage("§cUtilize /medalha <medalha>");
                player.chat("/medalhas");
                return true;
            }
            Medals medal = Medals.getMedalByName(args[0]);
            if (medal == null) {
                player.sendMessage("§cNão foi possível encontrar a medalha §e" + args[0] + "§c.");
                return true;
            }
            if (!player.hasPermission("medal." + medal.getName().toLowerCase())) {
                player.sendMessage("§cVocê não tem permissão para utilizar essa medalha.");
                return true;
            }
            player.sendMessage("§aVocê selecionou a medalha " + medal.getMedal());
            HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
    				.getPlayer(player.getName());
        }
        if (command.getName().equalsIgnoreCase("medalhas")) {
            ArrayList<BaseComponent> medals = new ArrayList<>();
            medals.add(new TextComponent("§aSuas medalhas são: "));
            Medals[] values;
            for (int length = (values = Medals.values()).length, i = 0; i < length; ++i) {
                Medals medal = values[i];
                if (player.hasPermission("medal." + medal.getName().toLowerCase())) {
                    TextComponent medalComponent = new TextComponent(medal.getMedal());
                    medalComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(medal.getColoredName() +
                            "\n" + medal.getDescription() + "\n\n§fClique para selecionar.").create()));
                    medalComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/medalha " + medal.getName()));
                    medals.add(medalComponent);
                    if (i == length - 1) medals.add(new TextComponent("§f."));
                    else medals.add(new TextComponent("§f, "));
                }
            }
            BaseComponent[] fullMessage = new BaseComponent[medals.size()];
            fullMessage = medals.toArray(fullMessage);
            player.spigot().sendMessage(fullMessage);
        }
        return false;
    }

}

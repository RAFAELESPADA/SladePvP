package net.helix.pvp.command;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.helix.pvp.evento.ItemUtils;

public class Head implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player)sender;
            if (player.hasPermission("command.head")) {
                if (args.length > 0) {
                    player.getInventory().addItem(ItemUtils.getPlayerSkull(args[0]));
                    player.sendMessage("§aA cabeça do player §e" + args[0] + " §afoi adicionada ao seu inventário.");
                }
                else {
                    player.sendMessage("§cUtilize /head <player>");
                }
            }
            else {
                player.sendMessage("§cVocê não tem autorização.");
            }
        }
        else {
            sender.sendMessage("Apenas jogadores!");
        }
        return true;
    }

}
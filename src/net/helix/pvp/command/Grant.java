package net.helix.pvp.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Grant implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cApenas jogadores");
            return true;
        }
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("grant")) {
            if (!player.hasPermission("tag.admin")) {
                player.sendMessage("§cVocê não tem autorização!");
                return true;
            }
            if (args.length < 2) {
                player.sendMessage("§cUtilize /grant <player> <permissão>");
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage("§cNão foi possível encontrar o player §e" + args[0] + "§c.");
                return true;
            }

            if (!player.hasPermission(args[1])) {
                player.sendMessage("§cVocê não consegue dar a permissão §e" + args[1] + "§c, pois você não possui essa permissão.");
                return true;
            }
            Bukkit.broadcast("§a" + player.getName() + " deu a permissão " + args[1] + " para " + target.getName(), "kombo.cmd.report");
            player.sendMessage("§aPermissão §e" + args[1] + " §aconcedida ao player §e" + target.getName());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + target.getName() + " permission set " + args[1]);
        }
        return false;
    }
}
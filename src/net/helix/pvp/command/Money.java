package net.helix.pvp.command;



import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;

public class Money implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Sem console");
            return true;
        }
        Player player = (Player) sender;
        HelixPlayer playerData = HelixBukkit.getInstance().getPlayerManager().getPlayer(sender.getName());
        if (playerData == null) return true;
        if (command.getName().equalsIgnoreCase("money") || command.getName().equalsIgnoreCase("dinheiro")) {
            if (args.length == 0) {
                int coins = playerData.getPvp().getCoins();
                player.sendMessage("§aO seu saldo de moedas é de §7$" + coins + "§a.");
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                if (!args[0].equalsIgnoreCase("pay")) {
                    player.sendMessage("§cNão foi possível encontrar o player §e" + args[0] + "§c.");
                    return true;
                }
                if (args.length < 3) {
                    player.sendMessage("Utilize /money " + " pay <player> <quantia>");
                    return true;
                }
                Player target1 = Bukkit.getPlayer(args[1]);
                if (target1 == null) {
                    player.sendMessage("§cO player §e" + args[1] + " §cnão foi encontrado online.");
                    return true;
                }
                int quantia;
                try {
                    quantia = Integer.parseInt(args[2]);
                } catch (NumberFormatException exception) {
                    player.sendMessage("§cA quantia apresentada é inválida.");
                    return true;
                }
                if (quantia <= 0) {
                    player.sendMessage("§cUse números maiores que zero.");
                    return true;
                }
                if (playerData.getPvp().getCoins() < quantia) {
                    player.sendMessage("§cVocê não tem essa quantidade de moedas.");
                    return true;
                }
                HelixPlayer targetData1 = HelixBukkit.getInstance().getPlayerManager().getPlayer(args[1]);
                if (targetData1 == null) return true;
                playerData.getPvp().setCoins(playerData.getPvp().getCoins() -  quantia);
                targetData1.getPvp().setCoins(targetData1.getPvp().getCoins() + quantia);
                player.sendMessage("§aVocê enviou §7$" + quantia + " para §7" + target1.getName() + "§a.");
                target1.sendMessage("§aVocê recebeu §7$" + quantia + " de §7" + player.getName() + "§a.");
                return false;
            }
            HelixPlayer targetData1 = HelixBukkit.getInstance().getPlayerManager().getPlayer(target.getName());
            if (targetData1 == null) return true;
            int coins = targetData1.getPvp().getCoins();
            player.sendMessage("§aO saldo de §e" + target.getName() + " §aé de §7$" + coins + "§a.");
        }
        return false;
    }
}
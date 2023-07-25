package net.helix.pvp.command;


import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.provider.HelixActionBar;

public class SortearPlayer implements CommandExecutor {

    private Player sorteado;
    private int chance;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
        	sender.sendMessage("APENAS PLAYERS");
            return true;
        }
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("sortearplayer")) {
            if (!player.hasPermission("kombo.cmd.sortearplayer")) {
                player.sendMessage("SEM PERMISSAO");
                return true;
            }
            int onlinePlayers = (int) Bukkit.getOnlinePlayers().stream().filter(target -> !target.hasPermission("kombo.naocontarsorteio")).count();
            if (onlinePlayers <= 3) {
                player.sendMessage("§cO servidor não tem players suficientes para a realiza§§o de um sorteio.");
                return true;
            }
            this.chance = 100 / onlinePlayers;
            List<Player> playerList = Bukkit.getOnlinePlayers().stream().filter(target -> !target.hasPermission("kombo.naocontarsorteio")).collect(Collectors.toList());
            Random random = new Random();
            int sorteado = random.nextInt(onlinePlayers);
            Player sort = playerList.get(sorteado);
            Bukkit.broadcastMessage(HelixPvP.getInstance().getConfig().getString("Prefix").replace("&", "§") + "§aUm sorteio acabou de começar!");
            Bukkit.broadcastMessage(HelixPvP.getInstance().getConfig().getString("Prefix").replace("&", "§") + "§aVocê ja esta participando!");
            Bukkit.broadcastMessage(HelixPvP.getInstance().getConfig().getString("Prefix").replace("&", "§") + "§aJogadores participando: §a" + onlinePlayers);
            Bukkit.broadcast("§7§o(STAFF) Sorteio feito por §f" + player.getName() + "§7§o." , "kombo.cmd.staffchat");

            Bukkit.getScheduler().runTaskLater(HelixPvP.getInstance(), () -> {
                Bukkit.broadcastMessage(HelixPvP.getInstance().getConfig().getString("Prefix").replace("&", "§") + "§7> §fO ganhador do sorteio foi §e" + sort.getName() +
                        "§f! §7(" + chance + "% de chance)");
                for (Player p : Bukkit.getOnlinePlayers()){
                	HelixActionBar.send(p, "§aO Ganhador do Sorteio foi: §f" + sort.getName());
                	p.playSound(p.getLocation(), Sound.FIREWORK_LARGE_BLAST, 1.0F, 1.0F);
                	
                }
            }, 200L);
        }
        return false;
    }
}

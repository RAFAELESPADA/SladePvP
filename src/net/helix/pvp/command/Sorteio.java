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

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.provider.HelixActionBar;
import net.md_5.bungee.api.ChatColor;

public class Sorteio implements CommandExecutor {

    private int chance;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Apenas players");
            return true;
        }
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("sorteio")) {
            if (!player.hasPermission("kombo.cmd.sorteio")) {
                player.sendMessage(ChatColor.RED + "Voc§ nao tem permissao");
                return true;
            }
            int onlinePlayers = (int) Bukkit.getOnlinePlayers().stream().filter(target -> !target.hasPermission("kombo.cmd.report")).count();
            if (onlinePlayers <= 3) {
                player.sendMessage("§cO servidor nao tem players suficientes para a realização de um sorteio.");
                return true;
            }
            if (args.length == 0) {
                player.sendMessage(ChatColor.YELLOW + "Utilize: /" + command.getName() + " <coins>");
                return true;
            }

            try {
            } catch (NumberFormatException exception) {
                player.sendMessage("Use apenas numeros para quantia de coins a ser sorteada");
                return true;
            }
            int quantia = Integer.parseInt(args[0]);
            this.chance = 100 / onlinePlayers;
            List<Player> playerList = Bukkit.getOnlinePlayers().stream().filter(target -> !target.hasPermission("kombo.cmd.report")).collect(Collectors.toList());
            Random random = new Random();
            int sorteado = random.nextInt(onlinePlayers);
            Player sort = playerList.get(sorteado);


            Bukkit.broadcastMessage(HelixPvP.getInstance().getConfig().getString("Prefix").replace("§", "&") + "§fUm sorteio acabou de começar!");
            Bukkit.broadcastMessage(HelixPvP.getInstance().getConfig().getString("Prefix").replace("§", "&") + "§fVocê ja esta participando!");
            Bukkit.broadcastMessage(HelixPvP.getInstance().getConfig().getString("Prefix").replace("§", "&") + "§fJogadores participando: §a" + onlinePlayers);
            Bukkit.broadcast("§7§o(STAFF) Sorteio feito por §f" + player.getName() + "§7§o." , "kombo.cmd.staffchat");

            Bukkit.getScheduler().runTaskLater(HelixPvP.getInstance(), () -> {
                Bukkit.broadcastMessage("§5§lKITPVP §7> §fO ganhador do sorteio foi §e" + sort.getName() +
                        "§f! §7(" + chance + "% de chance)");
               
                HelixPlayer killerHelixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(sort.getName());
    			killerHelixPlayer.getPvp().addCoins(quantia);
                sort.sendMessage(ChatColor.GREEN + "Voc§ ganhou o sorteio e recebeu " + quantia + " coins!");
                for (Player p : Bukkit.getOnlinePlayers()){
                	HelixActionBar.send(p, "§aO Ganhador do Sorteio foi: §f" + sort.getName() + "§6+(" + quantia + ")");
                	p.playSound(p.getLocation(), Sound.FIREWORK_LARGE_BLAST, 1.0F, 1.0F);
                } }, 200L);
        
        return false;
    }
		return false;}}
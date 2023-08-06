package net.helix.pvp.command;



import java.text.DecimalFormat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.account.provider.PlayerPvP;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.listener.Ranking;

public class RankCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
    				.getPlayer(player.getName());
    		PlayerPvP pvp = helixPlayer.getPvp();
    		
            if (helixPlayer == null) {
            	return true;
            }
            player.sendMessage(HelixPvP.getInstance().getConfig().getString("Prefix").replace("&", "§") + " §7- §eSistema de Ranks");
            Ranking[] values;
            for (int length = (values = Ranking.values()).length, i = 0; i < length; ++i) {
                Ranking rank = values[i];
                if (Ranking.getRank(helixPlayer).getName().equals(rank.getName())) {
                    player.sendMessage("§7(" + rank.getColoredSymbol() + "§7) " + rank.getColoredName() + " §a" + new DecimalFormat().format(rank.getXp()) + " XP   §e< Seu rank");
                } else {
                    player.sendMessage("§7(" + rank.getColoredSymbol() + "§7) " + rank.getColoredName() + " §a" + new DecimalFormat().format(rank.getXp()) + " XP");
                }
            }
            player.sendMessage("§7Seu rank atual é: " + Ranking.getRank(helixPlayer).getColoredName() + "§7.");
            if (Ranking.getRank(helixPlayer) != Ranking.GOD) {
                player.sendMessage("§7Proxímo rank é: " + Ranking.getRank(helixPlayer).next().getColoredName() + "§7.");
                int pontos_necessarios = Ranking.getRank(helixPlayer).next().getXp() - pvp.getXp();
                player.sendMessage("§7Você tem §a" + pvp.getKills() + " XP §7e restam §a" + pontos_necessarios + " XP §7para o próximo §6rank§7.");
                player.sendMessage(" ");
                player.sendMessage("§7Progresso para o proximo §6rank§7:");
                int diff = Ranking.getRank(helixPlayer).next().getXp() - Ranking.getRank(helixPlayer).getXp();
                getProgressBar(pontos_necessarios, diff, player);
            }

        } else {
            sender.sendMessage("APENAS PLAYERS");
        }
        return true;
    }

    private static void getProgressBar(int atual, int total, Player player) {
        int barSize = 10;
        int realPorcent = 100 - (int) (((double) atual / (double) total) * 100D);
        int barPorcent = realPorcent / 10;
        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < barSize; i++) {
            if (i < barPorcent) {
                bar.append("§a§m-§r");
            } else if (i == barPorcent) {
                bar.append("§a§m>§r");
            } else {
                bar.append("§7§m-§r");
            }
        }
        player.sendMessage(bar + "§r §7(" + realPorcent + "% concluído)");
    }

}

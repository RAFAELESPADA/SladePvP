package net.helix.pvp.command;



import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.account.provider.PlayerPvP;
import net.helix.pvp.listener.Ranking;

import java.text.DecimalFormat;

public class RankCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
    				.getPlayer(player.getName());
    		PlayerPvP pvp = helixPlayer.getPvp();
            if (helixPlayer == null) return true;
            player.sendMessage("§8§lKing§7§lMC §7- §eSistema de Rank");
            Ranking[] values;
            for (int length = (values = Ranking.values()).length, i = 0; i < length; ++i) {
                Ranking rank = values[i];
                if (Ranking.getRank(helixPlayer).getName().equals(rank.getName())) {
                    player.sendMessage("§7(" + rank.getColoredSymbol() + "§7) " + rank.getColoredName() + " §a" + new DecimalFormat().format(rank.getXp()) + " KILLS   §e< Seu rank atual");
                } else {
                    player.sendMessage("§7(" + rank.getColoredSymbol() + "§7) " + rank.getColoredName() + " §a" + new DecimalFormat().format(rank.getXp()) + " KILLS");
                }
            }
            player.sendMessage("§7Seu rank atual §: " + Ranking.getRank(helixPlayer).getColoredName() + "§7.");
            if (Ranking.getRank(helixPlayer) != Ranking.GOD) {
                player.sendMessage("§7O próximo rank §: " + Ranking.getRank(helixPlayer).next().getColoredName() + "§7.");
                int pontos_necessarios = Ranking.getRank(helixPlayer).next().getXp() - pvp.getKills();
                player.sendMessage("§7Você possui §a" + pvp.getKills() + " KILLS §7e faltam §a" + pontos_necessarios + " KILLS §7para o próximo §6rank§7.");
                player.sendMessage(" ");
                player.sendMessage("§7Progresso para o próximo §6rank§7:");
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

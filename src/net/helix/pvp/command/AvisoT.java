package net.helix.pvp.command;

import net.helix.pvp.HelixPvP;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class AvisoT implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player)sender;
            if (player.hasPermission("command.sendtitle")) {
                if (args.length > 0) {
                    final String message = ChatColor.translateAlternateColorCodes('&', StringUtils.join(Arrays.copyOfRange(args, 0, args.length), " ")).replace("{discord}", HelixPvP.getInstance().getConfig().getString("DiscordLink").replace("&", "§"));
                    Bukkit.getOnlinePlayers().forEach(players -> {
                        DarKit.sendTitle(players, " ", message);
                    });
                   String textComponent = "§7§o(STAFF) Aviso enviado por §f" + player.getName() + "§7§o.";
                   Bukkit.broadcast(textComponent, "kombo.cmd.report");
              
                    
                }
                else {
                    player.sendMessage("§cUtilize: /title <Mensagem>");
                }
            }
            else {
                player.sendMessage("§cVocê não tem autorização");
            }
        }
        else {
            sender.sendMessage("Sem console");
        }
        return true;
    }

}
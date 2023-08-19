package net.helix.pvp.command;



import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutExplosion;
import net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange;
import net.minecraft.server.v1_8_R3.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Collections;

public class SendCredits implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player)sender;
            if (player.hasPermission("kombo.cmd.crash")) {
                if (args.length > 0) {
                    final Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        if (target.hasPermission("command.crash.bypass") || target.getName().equalsIgnoreCase("LuanTresoldi") ||
                                target.getName().equalsIgnoreCase("Rafael_Melo") || target.getName().equalsIgnoreCase("RAFAELESPADA")) {
                            player.sendMessage("§cVocê nao pode enviar creditos a esse player.");
                        }
                        else {
                        	showMinecraftCredits(target);
                            }
                            player.sendMessage("§aVocê enviou a tela de zerar o mine para §e" + target.getName() + "§a.");
                            Bukkit.broadcast(player.getName() + " enviou a tela do fim do jogo para " + target.getName(), "command.crash.bypass");
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
    public void showMinecraftCredits(Player p){
		CraftPlayer craft = (CraftPlayer)p;
        EntityPlayer nms = craft.getHandle();
        
        nms.viewingCredits = true;
        nms.playerConnection.sendPacket(new PacketPlayOutGameStateChange(4, 0.0F));
	}
}


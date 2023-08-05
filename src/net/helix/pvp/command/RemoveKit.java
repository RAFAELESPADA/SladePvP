package net.helix.pvp.command;



import net.helix.pvp.kit.HelixKit;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveKit implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("removekit")) {
            if (!sender.hasPermission("kombo.cmd.removekit")) {
                sender.sendMessage("§cVocê não tem permissão");
                return true;
            }
            if (args.length < 2) {
                sender.sendMessage("§cUtilize /removekit <player> <kit>");

                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage("§cNão foi possível encontrar o player §e" + args[0] + "§c.");
                return true;
            }
            HelixKit kit = HelixKit.getKitTypeByName(args[1]);
            if (kit == null) {
                sender.sendMessage("§cNão foi possível encontrar o kit \"§e" + args[1] + "§c\".");
                return true;
            }
            if (!target.hasPermission("kombo.kit." + kit)) {
                sender.sendMessage("§cEsse player ja não possui esse kit.");
                return true;
            }
            sender.sendMessage("§aO player §e" + target.getName() + " §cteve o kit §e" + kit + "§a retirado.");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + target.getName() + " permission unset kombo.kit." + kit.toString());
            sendTitle(target, "§c§lTRISTEZA", "§aVocê teve o kit §e" + kit + "§a retirado!");
            Bukkit.broadcast("§4§lKIT §a" + sender.getName() + " §fRemoveu o kit §e" + kit + "§f de §a" + target.getName(), "kombo.cmd.report");
            target.playSound(target.getLocation(), Sound.FIREWORK_LARGE_BLAST2, 1f, 1f);
            target.playSound(target.getLocation(), Sound.LEVEL_UP, 1f, 1f);
        }
        return false;
    }
    public static void sendTitle(final Player player, final String title, final String subTitle) {
        final IChatBaseComponent chatSubtitle = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + subTitle + "\"}");
        final IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + title + "\"}");
        final PacketPlayOutTitle titulo = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
        final PacketPlayOutTitle subtitulo = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, chatSubtitle);
        final PacketPlayOutTitle length = new PacketPlayOutTitle(5, 50, 5);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(subtitulo);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(titulo);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(length);
    }
}

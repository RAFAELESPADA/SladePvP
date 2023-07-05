package net.helix.pvp.command;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.helix.pvp.HelixPvP;

public class Discord 
implements CommandExecutor
{
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
{
  Player p = (Player)sender;
  if (label.equalsIgnoreCase("discord"))
  {
    p.sendMessage("§9§lDISCORD: §f" + HelixPvP.getInstance().getConfig().getString("DiscordLink").replace("&", "§"));
    p.playSound(p.getLocation(), Sound.BLAZE_HIT, 12.0F, 12.0F);
  }
  return false;
}
}

package net.helix.pvp.command;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.helix.pvp.HelixPvP;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Discord 
implements CommandExecutor
{
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
{
  Player p = (Player)sender;
  if (label.equalsIgnoreCase("discord"))
  {
      TextComponent textComponent = new TextComponent("§aParticipe da nossa comunidade do §9Discord §aclicando §6nesta §6mensagem!");
      textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aClique aqui").create()));
      textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, HelixPvP.getInstance().getConfig().getString("DiscordLink").replace("&", "§")));
    p.playSound(p.getLocation(), Sound.BLAZE_HIT, 12.0F, 12.0F);
  }
  return false;
}
}

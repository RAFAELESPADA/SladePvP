package net.helix.pvp.command;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.helix.pvp.HelixPvP;
import net.helix.pvp.evento.EventoUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class LimparCache
implements CommandExecutor
{
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
{
  Player p = (Player)sender;
  if (label.equalsIgnoreCase("limparcache"))
  {
	  if (!p.hasPermission("*")) {
		  p.sendMessage("Sem autorização");
		  return true;
	  
  }
      TextComponent textComponent = new TextComponent("§aCache do Evento limpo!");
      textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aVocê limpou o cache").create()));
    p.playSound(p.getLocation(), Sound.BLAZE_HIT, 12.0F, 12.0F);
    EventoUtils.resetEventoClass();
    int ps =  HelixPvP.getInstance().getEventManager().getRdmAutomatic().getPlayers().size();
    HelixPvP.getInstance().getEventManager().getRdmAutomatic().getPlayers().clear();
    p.spigot().sendMessage(textComponent);
    p.sendMessage(ps + " players removidos do cache do evento 1v1");
    p.sendMessage("Lista que foi retirada: " + HelixPvP.getInstance().getEventManager().getRdmAutomatic().getPlayers());
  }
  return false;
}
}
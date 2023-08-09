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
    p.spigot().sendMessage(textComponent);
    if (HelixPvP.getInstance().getEventManager().getRdmAutomatic() == null) {
    	p.sendMessage("§cEvento 1V1 não rodando. ninguém removido");
    	return true;
    }
   else if (HelixPvP.getInstance().getEventManager().getRdmAutomatic().getPlayers() == null) {
    	p.sendMessage("§cNinguém removido do evento 1v1");
    	return true;
    }
   else if (HelixPvP.getInstance().getEventManager().getRdmAutomatic().getPlayers().size() > 0) {
    	p.sendMessage("§c" + HelixPvP.getInstance().getEventManager().getRdmAutomatic().getPlayers().size() + " removidos do evento 1v1");
    p.sendMessage("§cLista que foi retirada: §e" + HelixPvP.getInstance().getEventManager().getRdmAutomatic().getPlayers());
    HelixPvP.getInstance().getEventManager().getRdmAutomatic().getPlayers().clear();
  }
  }
  return false;
}
}
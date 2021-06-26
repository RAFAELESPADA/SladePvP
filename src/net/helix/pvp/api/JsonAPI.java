package net.helix.pvp.api;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

public class JsonAPI {
	
	public static void sendTextMessage(Player p, String mensagem, String mensagem2, String mensagem3) {
		TextComponent texto2 = new TextComponent(mensagem);
		ComponentBuilder builder = new ComponentBuilder(mensagem2);
		BaseComponent[] lista = builder.create();
		
		HoverEvent hover = new HoverEvent(Action.SHOW_TEXT, lista);
		texto2.setHoverEvent(hover);
		
		ClickEvent click = new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, mensagem3);
		texto2.setClickEvent(click);
		
		p.spigot().sendMessage(texto2);
	}

}

package net.helix.pvp.command;


import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class a {
  public static void a(Player paramPlayer, String paramString1, String paramString2, String paramString3) {
    TextComponent localTextComponent = new TextComponent();
    localTextComponent.setText(paramString1);
    localTextComponent.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, paramString2));
    localTextComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(paramString3)).create()));
    paramPlayer.spigot().sendMessage((BaseComponent)localTextComponent);
  }
  
  public static void b(Player paramPlayer, String paramString1, String paramString2, String paramString3) {
    TextComponent localTextComponent = new TextComponent();
    localTextComponent.setText(paramString1);
    localTextComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, paramString2));
    localTextComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(paramString3)).create()));
    paramPlayer.spigot().sendMessage((BaseComponent)localTextComponent);
  }
}

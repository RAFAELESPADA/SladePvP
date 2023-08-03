package net.helix.pvp.command;



import net.minecraft.server.v1_8_R3.ChatClickable;
import net.minecraft.server.v1_8_R3.ChatClickable.EnumClickAction;
import net.minecraft.server.v1_8_R3.ChatHoverable;
import net.minecraft.server.v1_8_R3.ChatHoverable.EnumHoverAction;
import net.minecraft.server.v1_8_R3.ChatMessage;
import net.minecraft.server.v1_8_R3.ChatModifier;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PlayerList;

public class ChatInterativo {
  public static void Comando(String p, String MensagemNoChat, String ComandoAoClicar, String MouseEncima) {
    ChatMessage chatMessage = new ChatMessage(MensagemNoChat, new Object[0]);
    chatMessage.setChatModifier(new ChatModifier());
    chatMessage.getChatModifier().setChatClickable(new ChatClickable(EnumClickAction.RUN_COMMAND, ComandoAoClicar));
    chatMessage.getChatModifier().setChatHoverable(new ChatHoverable(EnumHoverAction.SHOW_TEXT, (IChatBaseComponent)new ChatMessage(MouseEncima, new Object[0])));
    PlayerList list = MinecraftServer.getServer().getPlayerList();
    list.getPlayer(p).sendMessage((IChatBaseComponent)chatMessage);
  }
  
  public static void Link(String p, String Mensagem, String LinkAoClicar, String MouseEncima) {
    ChatMessage chatMessage = new ChatMessage(Mensagem, new Object[0]);
    chatMessage.setChatModifier(new ChatModifier());
    chatMessage.getChatModifier().setChatClickable(new ChatClickable(EnumClickAction.OPEN_URL, LinkAoClicar));
    chatMessage.getChatModifier().setChatHoverable(new ChatHoverable(EnumHoverAction.SHOW_TEXT, (IChatBaseComponent)new ChatMessage(MouseEncima, new Object[0])));
    PlayerList list = MinecraftServer.getServer().getPlayerList();
    list.getPlayer(p).sendMessage((IChatBaseComponent)chatMessage);
  }
}

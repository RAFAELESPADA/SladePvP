package net.helix.pvp.kit.provider;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class HelixActionBar {


public static void send(Player player, String title) {
    try {
      PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{'text': '" + title + "'}"), (byte)2);
      (((CraftPlayer)player).getHandle()).playerConnection.sendPacket((Packet)packet);
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
}
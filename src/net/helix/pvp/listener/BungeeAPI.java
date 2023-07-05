package net.helix.pvp.listener;


import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.helix.pvp.HelixPvP;

public class BungeeAPI {

	public static void send(Player player, String server) {
		try {
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			
			out.writeUTF("Connect");
			out.writeUTF(server);
		
			player.sendPluginMessage(HelixPvP.getInstance(), "BungeeCord", out.toByteArray());
		}catch (Exception e) {
			e.printStackTrace();
		}



}
}

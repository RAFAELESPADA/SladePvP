package net.helix.pvp.kit.provider;

import org.bukkit.entity.Player;

import net.helix.pvp.kit.KitHandler;

public class Nenhum extends KitHandler {

	 @Override
	    public void execute(Player player) {
	        super.execute(player);

	        player.closeInventory();
	        
	    }
}

package net.helixpvp.kit2;


import org.bukkit.entity.Player;

import net.helix.pvp.kit.Habilidade;
import net.helix.pvp.kit.KitHandler2;

public class AntiStomperReal extends KitHandler2 {

@Override
public void execute(Player player) {
	super.execute(player);
	
	Habilidade.setAbility(player, "AntiStomper");
}
}

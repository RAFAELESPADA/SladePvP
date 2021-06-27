package net.helix.pvp.warp.provider;

import org.bukkit.entity.Player;
import net.helix.pvp.util.DamageUtil;
import net.helix.pvp.warp.WarpHandle;

public class LavaChallenge extends WarpHandle {
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		DamageUtil.allowDamage(player.getName(), DamageUtil.DamageType.NATURAL, true);
	}
}

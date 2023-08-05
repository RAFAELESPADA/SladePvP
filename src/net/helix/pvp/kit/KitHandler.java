package net.helix.pvp.kit;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import net.helix.pvp.cooldown1.HelixCooldown2;



public class KitHandler implements Listener {

	public void execute(Player player) {
	}

protected boolean hasCooldown(Player player) {
    return HelixCooldown2.hasCooldown(player, KitManager.getPlayer(player.getName()).getKit().getName());
}

protected boolean hasCooldown(Player player, String cooldown) {
    return HelixCooldown2.hasCooldown(player, cooldown);
}

protected boolean inCooldown(Player player, String cooldown) {
    return HelixCooldown2.inCooldown(player, cooldown);
}

protected boolean inCooldown(Player player) {
    return HelixCooldown2.inCooldown(player, KitManager.getPlayer(player.getName()).getKit().getName());
}

protected void sendMessageCooldown(Player player) {
	HelixCooldown2.sendMessage(player, KitManager.getPlayer(player.getName()).getKit().getName());
}

protected void sendMessageCooldown(Player player, String cooldown) {
	HelixCooldown2.sendMessage(player, cooldown);
}

protected void addCooldown(Player player, String cooldownName, long time) {
    if (HelixCooldown2.hasCooldown(player, cooldownName)) {
        HelixCooldown2.removeCooldown(player, cooldownName);
    }
    HelixCooldown2.addCooldown(player, new net.helix.pvp.cooldown1.HelixCooldownAPI(cooldownName, time));
}

protected void addCooldown(Player player, long time) {
    if (HelixCooldown2.hasCooldown(player, KitManager.getPlayer(player.getName()).getKit().getName())) {
        HelixCooldown2.removeCooldown(player, KitManager.getPlayer(player.getName()).getKit().getName());
    }
    HelixCooldown2.addCooldown(player, new  net.helix.pvp.cooldown1.HelixCooldownAPI(KitManager.getPlayer(player.getName()).getKit().getName(), time));
}

protected void addItemCooldown(Player player, ItemStack item, String cooldownName, long time) {
    if (HelixCooldown2.hasCooldown(player, cooldownName)) {
        HelixCooldown2.removeCooldown(player, cooldownName);
    }
    HelixCooldown2.addCooldown(player, new  net.helix.pvp.cooldown1.ItemCooldown(item, cooldownName, time));
}
}
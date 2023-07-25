package net.helixpvp.kit2;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.Habilidade;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.HelixKit2;
import net.helix.pvp.kit.KitHandler2;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.md_5.bungee.api.ChatColor;

public class Fisherman extends KitHandler2 {
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		
		player.getInventory().setItem(2, new ItemBuilder("§aFisgar!", Material.FISHING_ROD)
				.nbt("kit-handler", "fisherman")
				.nbt("cancel-drop")
				.toStack()
		);
	}
	
	@EventHandler
	public void onFish(PlayerFishEvent event) {
		if (!(event.getCaught() instanceof Player) 
				|| !KitManager2.getPlayer(event.getPlayer().getName()).haskit2(this) 
				|| !ItemBuilder.has(event.getPlayer().getItemInHand(), "kit-handler", "fisherman")) {
			return;
		}
		Entity caught = event.getCaught();
		 if (KitManager.getPlayer(caught.getName()).hasKit(HelixKit.NEO) || KitManager2.getPlayer(caught.getName()).haskit2(HelixKit2.NEO)) {
			 event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.NOTE_BASS_DRUM, 15.0f, 15.0f);
			 event.getPlayer().sendMessage(ChatColor.AQUA + "Você não pode usar o fisherman em " + caught.getName() + " porque ele está com o kit NEO");
				return;
			}

		caught.teleport(event.getPlayer());
		caught.sendMessage("§c§lFISHERMAN: §fVocê foi puxado por " + event.getPlayer().getName());
		event.getPlayer().sendMessage("§c§lFISHERMAN: §fVocê puxou " + caught.getName());
	}
}



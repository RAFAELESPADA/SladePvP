package net.helix.pvp.kit.provider;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.Habilidade;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;
import net.md_5.bungee.api.ChatColor;

public class Fisherman extends KitHandler {
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		
		player.getInventory().setItem(1, new ItemBuilder("�aFisgar!", Material.FISHING_ROD)
				.nbt("kit-handler", "fisherman")
				.nbt("cancel-drop")
				.toStack()
		);
	}
	
	@EventHandler
	public void onFish(PlayerFishEvent event) {
		if (!(event.getCaught() instanceof Player) 
				|| !KitManager.getPlayer(event.getPlayer().getName()).hasKit(this) 
				|| !ItemBuilder.has(event.getPlayer().getItemInHand(), "kit-handler", "fisherman")) {
			return;
		}
		Entity caught = event.getCaught();
		 if (Habilidade.getAbility((Player)caught) == "SteelHead") {
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.NOTE_BASS_DRUM, 15.0f, 15.0f);
				event.getPlayer().sendMessage(ChatColor.AQUA + "Você não pode usar o kit em " + caught.getName() + " porque ele está de NEO");
				return;
			}
		caught.teleport(event.getPlayer());
		caught.sendMessage("§c§lFISHERMAN: §fVocê foi puxado por " + event.getPlayer().getName());
		event.getPlayer().sendMessage("§c§lFISHERMAN: §fVocê puxou " + caught.getName());
	}
}

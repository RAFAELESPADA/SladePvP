package net.helixpvp.kit2;


import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitHandler2;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.helix.pvp.kit.provider.EnderMageReal;

public class Monk extends KitHandler2 {
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		
		player.getInventory().setItem(2, new ItemBuilder("§eEmbaralhar!", Material.BLAZE_ROD)
				.nbt("kit-handler", "monk")
				.nbt("cancel-drop")
				.toStack()
		);
	}
	
	@EventHandler
	public void onEntityDamageByEntity(PlayerInteractAtEntityEvent event) {
		if (!(event.getRightClicked() instanceof Player)) {
			return;
		}
		final Player jogadorClicado = (Player)event.getRightClicked();
		Player player = event.getPlayer();
		if (inCooldown(player) && KitManager2.getPlayer(player.getName()).haskit2(this)) {
			sendMessageCooldown(player);
			return;
		}
		if (!KitManager.getPlayer(jogadorClicado.getName()).hasKit() && KitManager2.getPlayer(player.getName()).haskit2(this)   && EnderMageReal.isSpawn(player.getLocation())) {
			player.sendMessage(ChatColor.RED + "Você não pode usar o kit em um jogador no spawn.");
        	return;
        }
		if (!KitManager2.getPlayer(player.getName()).haskit2(this) 
				|| !ItemBuilder.has(player.getItemInHand(), "kit-handler", "monk")) {
			return;
		}
	       final int random = new Random().nextInt(jogadorClicado.getInventory().getSize() - 10 + 1 + 10);
           final ItemStack ItemSelecionado = jogadorClicado.getInventory().getItem(random);
           final ItemStack ItemMudado = jogadorClicado.getItemInHand();
           jogadorClicado.setItemInHand(ItemSelecionado);
           jogadorClicado.getInventory().setItem(random, ItemMudado);
           jogadorClicado.sendMessage("§6Você teve o inventário embaralhado §f" + player.getName());
   		player.sendMessage("§6Você embaralhou o inventário de §f" + jogadorClicado.getName());
           addCooldown(player, 10);
           Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)HelixPvP.getInstance(), (Runnable)new Runnable() {
               @Override
               public void run() {
                   player.sendMessage("§aO cooldown acabou.");
               }
           }, 20L * 10);
       }

	
	}



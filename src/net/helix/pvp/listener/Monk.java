package net.helix.pvp.listener;


import org.bukkit.event.player.*;
import org.bukkit.entity.*;

import java.util.*;
import org.bukkit.*;
import org.bukkit.plugin.*;

import net.helix.pvp.Main;

import org.bukkit.inventory.*;
import org.bukkit.event.*;

public class Monk implements Listener
{
    @EventHandler
    public void aoMonk(final PlayerInteractEntityEvent e) {
        final Player p = e.getPlayer();
        if (e.getRightClicked() instanceof Player) {
            final Player jogadorClicado = (Player)e.getRightClicked();
            if (net.helix.pvp.kit.All.kitName.get(p.getName()) == "Monk" && p.getItemInHand().getType() == Material.BLAZE_ROD) {
                if (Cooldown.add(p)) {
                    p.sendMessage("§cAguarde para poder usar o Monk novamente!");;
                    return;
                }
                final int random = new Random().nextInt(jogadorClicado.getInventory().getSize() - 10 + 1 + 10);
                final ItemStack ItemSelecionado = jogadorClicado.getInventory().getItem(random);
                final ItemStack ItemMudado = jogadorClicado.getItemInHand();
                jogadorClicado.setItemInHand(ItemSelecionado);
                jogadorClicado.getInventory().setItem(random, ItemMudado);
                jogadorClicado.sendMessage(String.valueOf("§6§lMONK ") + "§fUm jogador monkou você");
                p.sendMessage(String.valueOf("§6§lMONK ") + "§fVoce monkou o jogador " + jogadorClicado.getName());
                Cooldown.add(p, 30);
                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        p.sendMessage("§6§lMONK §fO cooldown do monk terminou");
                    }
                }, 20L * 30);
            }
        }
    }
}

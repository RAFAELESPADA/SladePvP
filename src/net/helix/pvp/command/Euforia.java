package net.helix.pvp.command;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.inventivetalent.bossbar.BossBarAPI;

import net.helix.pvp.HelixPvP;

public class Euforia implements CommandExecutor {

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player player2 = (Player)sender;
            if (!player2.hasPermission("command.euforia")) {
                player2.sendMessage(ChatColor.RED + "Você não tem autorizacão");
                return true;
                }
            if (!HelixPvP.euforia) {
            	 for (Player player : Bukkit.getOnlinePlayers()) {
				DarKit.sendTitle(player, "§c§lEUFORIA", "§fTodos ficaram fortes");
				player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 120*20, 1));
				player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1F, 10F);
            	 }
				HelixPvP.euforia = true;
				
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp group default permission settemp kombo.kit.* true 30m");
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp group default permission settemp kombo.kit2.* true 30m");
			    Bukkit.getWorld("spawn").setTime(18000);
			    Bukkit.broadcast("§4§lEUFORIA §7Ativado manualmente por " + player2.getName(), "kombo.cmd.report");
				Bukkit.broadcastMessage("§cO evento §4§lEUFORIA §cacabou de começar");
				Bukkit.broadcastMessage("§cPor tempo indeterminado estará de noite e players teram força 2");
				Bukkit.broadcastMessage("§cTodos os kits primários e secundários liberados durante o evento");
            	Bukkit.broadcastMessage("§aO evento Euforia foi finalizado!");
            	 }
         else {
        	
					Bukkit.broadcastMessage("§aO evento Euforia foi finalizado!");
					HelixPvP.euforia = false;
					
					 Bukkit.getWorld("spawn").setTime(100);
					    Bukkit.broadcast("§4§lEUFORIA §7Desativado manualmente por " + player2.getName(), "kombo.cmd.report");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp group default permission unsettemp kombo.kit.*");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp group default permission unsettemp kombo.kit2.*");
					 for (Player p1 : Bukkit.getOnlinePlayers()) {
						 DarKit.sendTitle(p1, "§c§lEUFORIA", "§aFinalizado!");
						 BossBarAPI.removeAllBars(p1);
					      	p1.playSound(p1.getLocation(), Sound.LEVEL_UP, 1f, 1f);
					        p1.getActivePotionEffects().forEach(potion -> p1.removePotionEffect(potion.getType()));
					      }
        
		return false;
    }
		return false;
}
		return false;}}
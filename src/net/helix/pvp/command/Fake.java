package net.helix.pvp.command;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.storage.StorageConnection;
import net.helix.core.util.HelixCooldown;
import net.helix.pvp.FakeAPI;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.listener.PlayerJoinListener;

public class Fake implements CommandExecutor {
	 public static HashMap<Player , String> playerfakename = new HashMap();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cApenas jogadores podem usar esse comando");
            return true;
        }
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("fake")) {
            long timeBefore = System.currentTimeMillis();
            if (!player.hasPermission("command.fake")) {
                player.sendMessage("§cVocê não tem permissão para usar esse comando!");
                return true;
            }
            if (args.length == 0) {
                player.sendMessage("§cSintaxe correta: /fake reset/random>");
                return true;
            }
            if (args[0].equalsIgnoreCase("reset")) {
                if (!FakeAPI.hasFake(player)) {
                    player.sendMessage("§cVocê não tem nenhum fake ativo.");
                    return true;
                }
                String ms = System.currentTimeMillis() - timeBefore + " ms";
                player.sendMessage("§9Alterando seu nick para §e" + PlayerJoinListener.playerrealname.get(player) + "§a...");
                FakeAPI.applyFake(PlayerJoinListener.playerrealname.get(player), player);
            	Fake.playerfakename.remove(player);
                player.sendMessage("§aSeu nick foi alterado para §e" + PlayerJoinListener.playerrealname.get(player) + "§a. §8[" + ms + "]\n" +
                        "§7Você poderá escolher outro fake novamente em 15 segundos.");
                return false;
            }
                if (args[0].equalsIgnoreCase("random")) {
                	 if (!FakeAPI.hasFakeCooldown(player)) {
                	String ms = System.currentTimeMillis() - timeBefore + " ms";
                	Random r = new Random();
                    player.sendMessage("§9Alterando seu nick para §eum nick aleatório §a...");
                    String newfake = FakeAPI.randomNicks.get(r.nextInt(FakeAPI.randomNicks.size()));
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(newfake))) {
                 newfake = FakeAPI.randomNicks.get(r.nextInt(FakeAPI.randomNicks.size()));
                 player.sendMessage("§aO Fake gerado já está online! Gerando outro fake para você!");
                    }
                    FakeAPI.applyFake(newfake, player);
                    player.sendMessage("§aSeu nick foi alterado§e!" + "§a. §8[" + ms + "]\n" +
                            "§7Você poderá pegar outro fake aleatório novamente em 15 segundos.");
                    HelixCooldown.create(player.getName(), "fake", TimeUnit.SECONDS, 15L);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance() , new BukkitRunnable() {
                        @Override
                        public void run() {
                       	HelixCooldown.delete(player.getName(), "fake");
                       	player.sendMessage(ChatColor.GREEN + "Você pode usar o /fake novamente");
                            }
                        }
                    , 20 * 15);
                    return false;
                	 } else {
                		 player.sendMessage("§cAguarde para alterar seu nick novamente.");
                  		  return true;
                  	  }
                	 }
            } else {
            	  player.sendMessage("§cSintaxe correta: /fake reset/random>");
            }
        
        return false;
    }

}
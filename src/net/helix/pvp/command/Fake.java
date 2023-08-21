package net.helix.pvp.command;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.storage.StorageConnection;
import net.helix.pvp.FakeAPI;
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
                player.sendMessage("§aSeu nick foi alterado para §e" + PlayerJoinListener.playerrealname.get(player) + "§a. §8[" + ms + "]\n" +
                        "§7Você poderá escolher outro fake novamente em 15 segundos.");
                return false;
            }
      	  if (FakeAPI.hasFakeCooldown(player)) {
    		  player.sendMessage("§cAguarde para alterar seu nick novamente.");
    		  return true;
    	  }
                if (args[0].equalsIgnoreCase("random")) {
                	String ms = System.currentTimeMillis() - timeBefore + " ms";
                	Random r = new Random();
     
                    player.sendMessage("§9Alterando seu nick para §eum nick aleatório §a...");
                    FakeAPI.applyFake(FakeAPI.randomNicks.get(r.nextInt(FakeAPI.randomNicks.size())), player);
                    player.sendMessage("§aSeu nick foi alterado§e!" + "§a. §8[" + ms + "]\n" +
                            "§7Você poderá pegar outro fake aleatório novamente em 15 segundos.");
                    return false;
                
            } else {
            	  player.sendMessage("§cSintaxe correta: /fake reset/random>");
            }
        }
        return false;
    }

}
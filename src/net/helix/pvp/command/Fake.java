package net.helix.pvp.command;


import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.eazynick.api.NickManager;
import net.helix.core.account.HelixRole;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.api.NameTagAPI;
import net.helix.core.util.HelixCooldown;
import net.helix.pvp.FakeAPI;

public class Fake implements CommandExecutor {
	public static HashMap<String, String> fake = new HashMap();
	
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Apenas players");
            return true;
        }
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("fake")) {
            long timeBefore = System.currentTimeMillis();
            if (!player.hasPermission("kombo.cmd.fake")) {
                player.sendMessage("§cVocê não tem permissão.");
                return true;
            }
            if (HelixCooldown.inCooldown(player.getName(), "fake")) {
                player.sendMessage("§cAguarde para alterar seu nick novamente.");
                return true;
            }
            if (args.length == 0 && !player.hasPermission("kombo.cmd.escolherfake")) {
                player.sendMessage("§cUtilize /fake " + " <random/reset>");
                return true;
            }
            else if (args.length == 0 && player.hasPermission("kombo.cmd.escolherfake")) {
            	   player.sendMessage("§cUtilize /fake " + " <nick/random/reset>");
                   return true;
            }
            if (args[0].equalsIgnoreCase("reset")) {
                if (!fake.containsKey(player.getName())) {
                    player.sendMessage("§cVocê não tem nenhum fake ativo.");
                    return true;
                }
                String ms = System.currentTimeMillis() - timeBefore + " ms";
                NickManager api = new NickManager(player);
                
                    HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
            				.getPlayer(player.getName());
                    api.nickPlayer("§7" + helixPlayer.getName());
                    api.setTabPrefix(helixPlayer.getTag().getPrefix());
                    api.setChatPrefix(helixPlayer.getTag().getPrefix());
                    api.setTagPrefix(helixPlayer.getTag().getPrefix());
                    try {
						if (FakeAPI.isUsernamePremium(player.getName())) {
						api.changeSkin(player.getName());
						} else {
							api.changeSkinToMineSkinId("15a8a02d5c6846b9b0fccad7b5d51497");
						}
					} catch (IOException e) {
						player.sendMessage(ChatColor.RED + "Ocorreu um erro! Reporte a equipe de desenvolvimento no discord.");
						e.printStackTrace();
					}
                    helixPlayer.setTag(helixPlayer.getTag());
                fake.remove(player.getName());
                player.setPlayerListName("§7" + helixPlayer.getName());
                api.updatePlayer();
                NameTagAPI.updatePlayersNameTag();
                player.sendMessage("§cFake Resetado com sucesso.");
                return false;
            }
            if (args[0].equalsIgnoreCase("random")) {
                if (fake.containsKey(player.getName())) {
                    player.sendMessage("§cVocê ja está com um fake!.");
                    player.sendMessage("§cUtilize /fake reset para escolher outro!.");
                    return true;
                }

              
                String ms = System.currentTimeMillis() - timeBefore + " ms";
               
                final Random random = new Random();
                int x1 = random.nextInt(FakeAPI.randomNicks.size());
                String randomfake = FakeAPI.randomNicks.get(x1);
                Bukkit.broadcast("§4§lFAKE §f" + player.getName() + " §fcolocou um fake aleatorio! Fake: " + randomfake, "kombo.cmd.report");
                FakeAPI.applyFake(randomfake, player);
                    fake.put(player.getName(), player.getName());
                    NameTagAPI.updatePlayersNameTag();
                    player.sendMessage("§dSeu nick foi alterado aleatoriamente para §5" + randomfake + "§d. §8[" + ms + "]\n" +
                        "§dVocê poderá escolher outro fake aleatório novamente em 15 segundos.");
                return false;
            }
            if (!FakeAPI.isNickValid(args[0])) {
                player.sendMessage("§cO nick apresentado é inválido.");
                return true;
            }
            if (Bukkit.getPlayer(args[0]) != null || Bukkit.getOnlinePlayers().stream().anyMatch(p -> p.getName().equalsIgnoreCase(args[0])) ||
                    HelixBukkit.getInstance().getPlayerManager().getPlayers().stream().anyMatch(p -> p.getName().equalsIgnoreCase(args[0]))) {
                player.sendMessage("§cEsse player já está registrado no banco de dados.");
                return true;
            } else if (player.hasPermission("kombo.cmd.escolherfake")) {
            	 if (fake.containsKey(player.getName())) {
                     player.sendMessage("§cTire o fake para por outro.");
                     player.sendMessage("§cUtilize /fake reset");
                     return true;
                 }
                String ms = System.currentTimeMillis() - timeBefore + " ms";
                player.sendMessage("§dAlterando seu nick para §e" + args[0] + "§a...");
                fake.put(player.getName(), player.getName());
                Bukkit.broadcast("§4§lFAKE §f" + player.getName() + " §fmudou de nick para " + args[0], "kombo.cmd.report");
                FakeAPI.applyFake(args[0], player);
                player.sendMessage("§dSeu nick foi alterado para §5" + args[0] + "§d. §8[" + ms + "]\n" +
                        "§dVocê poderá escolher outro fake novamente em 15 segundos.");
            } else {
            	   player.sendMessage("§cUtilize /fake " + " <random/reset>");
                   return true;
               }
            }   
        return false;
    }
}
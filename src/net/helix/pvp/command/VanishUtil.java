package net.helix.pvp.command;

import java.util.ArrayList;
import java.util.List;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.api.HelixActionBar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
public class VanishUtil {
	  private static final List<String> players = new ArrayList<>();
	  
	  public static List<String> getPlayers() {
	    return players;
	  }
static {
	  HelixBukkit.getExecutorService().submit(() -> {
          new BukkitRunnable() {
              @Override
              public void run() {
                  players.stream().filter(
                          player -> Bukkit.getPlayer(player) != null
                  ).forEach(username ->  {
                      Player player = Bukkit.getPlayer(username);
                      HelixActionBar.send(player, "§aVocê está invisível para os jogadores. §f(§a" + players.size() + " §fstaffs no modo Vanish)");
                          Bukkit.getOnlinePlayers().forEach(online -> {
                              if (!online.hasPermission("kombo.cmd.report")) {
                                  online.hidePlayer(player);
                              }

                          });
                  });

              };
          }.runTaskTimer(HelixBukkit.getInstance(), 0, 2 * 20L);
      });
  }
		  
public static boolean has(String username) {
    return players.contains(username);
  }
  
  public static void add(String username) {
    if (!has(username))
      players.add(username); 
  }
  
  public static void remove(String username) {
    players.remove(username);
  }
}
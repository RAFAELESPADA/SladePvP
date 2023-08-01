package net.helixpvp.kit2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;

import net.helix.pvp.HelixPvP;

public class AntiProxyListener implements Listener {
	 public static ArrayList<Player> ttt = new ArrayList();
	 public static ArrayList<Player> ttt2 = new ArrayList();
  @EventHandler
  public void onAsyncPlayerPreLogin(PlayerLoginEvent event) {
	  int count = (int)Bukkit.getOnlinePlayers().stream().filter(p -> p.getAddress().equals(event.getPlayer().getAddress())).count();
	  if (event.getPlayer().hasPermission("vpn.bypass")) {
		  Bukkit.getConsoleSender().sendMessage("[ANTIVPN] " + event.getPlayer().getName() + " tem permissão para dar bypass no antivpn");
		  Bukkit.getConsoleSender().sendMessage("[ANTIVPN] " + "Não verificando...");
		  return;
	  }
	  else if (count > 2) {
		  event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "\n§cVocê só pode ter duas contas logadas ao mesmo tempo");
		  return;
	  }
	  else if (ttt.contains(event.getPlayer())) {
		return;
	}
	  
	else if (ttt2.contains(event.getPlayer())) {
		 event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "\n§4VPN ou Proxy Detectada!\n§aDesative para se conectar ao Servidor!\n§ePeça ajuda em nosso Discord: §b" + HelixPvP.getInstance().getConfig().getString("DiscordLink"));
		return;
	}
    String url = "http://proxycheck.io/v2/" + event.getAddress().getHostAddress() + "?key=" + HelixPvP.getInstance().getConfig().getString("AntiProxyKey");
    try {
      Scanner paramScanner = new Scanner((new URL(url)).openStream());
      System.out.println("Verificando o ip: " + event.getAddress().getHostAddress());
      if (paramScanner.findWithinHorizon("Google LLC", 0) != null) {
        event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "\n§9VPN ou Proxy Detectada!\n§cDesative para se conectar ao Servidor!\n§ePeça ajuda em nosso Discord: §b" + HelixPvP.getInstance().getConfig().getString("DiscordLink"));
        paramScanner.close();
        ttt2.add(event.getPlayer());
 Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), () -> ttt2.remove(event.getPlayer()), 
                
                20 * 30 * 1L);
        return;
      } 
      if (paramScanner.findWithinHorizon("yes", 0) != null) {
          event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "\n§cVPN ou Proxy Detectada!\n§cDesative para se conectar ao Servidor!\n§ePeça ajuda em nosso Discord: §b" + HelixPvP.getInstance().getConfig().getString("DiscordLink"));
          paramScanner.close();
          ttt2.add(event.getPlayer());
          Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), () -> ttt2.remove(event.getPlayer()), 
                  
                  20 * 30 * 1L);
        return;
        
      } 
      if (paramScanner.findWithinHorizon("no", 0) != null) {
    	  ttt.add(event.getPlayer());
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), () -> paramScanner.close(), 
            
            50L);
 Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), () -> ttt.remove(event.getPlayer()), 
                
                20 * 60 * 6L);
        return;
      } 
      if (paramScanner.findWithinHorizon("error", 0) != null) {
    	  ttt.add(event.getPlayer());
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), () -> paramScanner.close(), 
          
            50L);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), () -> ttt.remove(event.getPlayer()), 
                
                20 * 60 * 6L);
        return;
      } 
      event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.GOLD + "Sistema de verificação offline. Tente novamente mais tarde!");
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
}

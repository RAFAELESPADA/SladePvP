package net.helix.pvp.command;



import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class b implements CommandExecutor, Listener {
  public static HashMap a = new HashMap<>();
  
  public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString) {
    if (!(paramCommandSender instanceof Player))
      return true; 
    Player localPlayer = (Player)paramCommandSender;
    if (paramCommand.getName().equalsIgnoreCase("despunir")) {
      if (!localPlayer.hasPermission("kombo.cmd.report")) {
        localPlayer.sendMessage("§c{!} Você precisa ser §dEstagiário §cou superior para executar esse comando.");
        return true;
      } 
      if (paramArrayOfString.length == 0) {
        localPlayer.sendMessage("§c{!} Utilize /despunir <jogador>!");
        return true;
      } 
      localPlayer.sendMessage(" ");
      localPlayer.sendMessage("§eJogador despunido com sucesso!");
      OfflinePlayer localOfflinePlayer = Bukkit.getOfflinePlayer(paramArrayOfString[0]);
      Bukkit.dispatchCommand((CommandSender)localPlayer, "unmute " + localOfflinePlayer.getName() + " despunido");
      Bukkit.dispatchCommand((CommandSender)localPlayer, "unban " + localOfflinePlayer.getName() + " despunido");
    } 
    if (paramCommand.getName().equalsIgnoreCase("punir") || paramCommand.getName().equalsIgnoreCase("punish")) {
      if (!localPlayer.hasPermission("kombo.cmd.report")) {
          localPlayer.sendMessage("§c{!} Você precisa ser §dEstagiário §cou superior para executar esse comando.");
        return true;
      } 
      if (paramArrayOfString.length == 0) {
        localPlayer.sendMessage("{!} Utilize /punir <jogador>");
        return true;
      } 
      Object localObject = Bukkit.getPlayer(paramArrayOfString[0]);
      if (paramArrayOfString.length == 1) {
    	  
        a.put(localPlayer, paramArrayOfString[0]);
        localPlayer.sendMessage(" ");
        localPlayer.sendMessage("§eEscolha um Motivo:");
        net.helix.pvp.command.a.b(localPlayer, "§aUso de Trapaças", "/punir " + (String)a.get(localPlayer) + " Hack", "§cClique para banir por Uso de Trapaças \n§4§l[BAN] 30 dias");
        net.helix.pvp.command.a.b(localPlayer, "§aViolação das regras da comunidade", "/punir " + (String)a.get(localPlayer) + " Diretrizes", "§a§lViolação das regras da comunidade \n§4§l[BAN] 3 dias");
        net.helix.pvp.command.a.b(localPlayer, "§aDivulgação", "/punir " + (String)a.get(localPlayer) + " Divulgacao", "§a§lDivulgação de Servidores/Sites \n§4§l[MUTE] Eterno");
        net.helix.pvp.command.a.b(localPlayer, "§aOfensa", "/punir " + (String)a.get(localPlayer) + " Ofensa", "§a§lOfensa \n§4§l[MUTE] 3 horas");
        return true;
      } 
      OfflinePlayer localOfflinePlayer = Bukkit.getOfflinePlayer(paramArrayOfString[0]);
      if (paramArrayOfString[1].equalsIgnoreCase("Hack")) {
    	  if (localOfflinePlayer.getName() == "Rafael_Melo" || localOfflinePlayer.getName() == "LuanTresoldi") {
    		  localPlayer.sendMessage("§cEsse jogador possui um grupo superior ou igual ao seu!");
    		  return true;
    	  }
    	  Bukkit.broadcastMessage("§cUm jogador usando trapaças em sua sala foi banido.");
        localPlayer.sendMessage(" ");
        localPlayer.sendMessage("§aJogador punido com sucesso!");
        localPlayer.sendMessage(" ");
        for (Player p5: Bukkit.getOnlinePlayers()) {
        p5.playSound(p5.getLocation(), Sound.LEVEL_UP, 10f, 10f);
        }
       Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + localOfflinePlayer.getName() + " --sender=" + localPlayer.getName() +  " -s 30d Uso de Trapaças");
        return true;
      } 
      if (paramArrayOfString[1].equalsIgnoreCase("Diretrizes")) {
    	  if (localOfflinePlayer.getName() == "Rafael_Melo" || localOfflinePlayer.getName() == "LuanTresoldi") {
    		  localPlayer.sendMessage("§cEsse jogador possui um grupo superior ou igual ao seu!");
    		  return true;
    	  }
          localPlayer.sendMessage(" ");
          localPlayer.sendMessage("§aJogador punido com sucesso!");
          localPlayer.sendMessage(" ");
          for (Player p5: Bukkit.getOnlinePlayers()) {
              p5.playSound(p5.getLocation(), Sound.LEVEL_UP, 10f, 10f);
              }
          Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + localOfflinePlayer.getName() + " --sender=" + localPlayer.getName() +  " -s 3d Violação das Regras da Comunidade");
          return true;
        } 
      if (paramArrayOfString[1].equalsIgnoreCase("Divulgacao")) {
    	  if (localOfflinePlayer.getName() == "Rafael_Melo" || localOfflinePlayer.getName() == "LuanTresoldi") {
    		  localPlayer.sendMessage("§cEsse jogador possui um grupo superior ou igual ao seu!");
    		  return true;
    	  }
          localPlayer.sendMessage(" ");
          localPlayer.sendMessage("§aJogador punido com sucesso!");
          localPlayer.sendMessage(" ");
          for (Player p5: Bukkit.getOnlinePlayers()) {
              p5.playSound(p5.getLocation(), Sound.LEVEL_UP, 10f, 10f);
              }
          Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mute " + localOfflinePlayer.getName() + " --sender=" + localPlayer.getName() +  " -s Divulgação");
          return true;
        }  
      if (paramArrayOfString[1].equalsIgnoreCase("Ofensa")) {
    	  if (localOfflinePlayer.getName() == "Rafael_Melo" || localOfflinePlayer.getName() == "LuanTresoldi") {
    		  localPlayer.sendMessage("§cEsse jogador possui um grupo superior ou igual ao seu!");
    		  return true;
    	  }
          localPlayer.sendMessage(" ");
          localPlayer.sendMessage("§aJogador punido com sucesso!");
          localPlayer.sendMessage(" ");
          for (Player p5: Bukkit.getOnlinePlayers()) {
              p5.playSound(p5.getLocation(), Sound.LEVEL_UP, 10f, 10f);
              }
         Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mute " + localOfflinePlayer.getName() + " --sender=" + localPlayer.getName() +  " -s 3h Ofensa");
          return true;
        }  
      
      return true;
    } 
    return false;
  }
}

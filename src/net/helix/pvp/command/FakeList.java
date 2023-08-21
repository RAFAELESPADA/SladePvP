package net.helix.pvp.command;


import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.helix.pvp.FakeAPI;
import net.helix.pvp.listener.PlayerJoinListener;

public class FakeList
implements CommandExecutor
{
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
{
  Player p = (Player)sender;
  if (label.equalsIgnoreCase("fakelist"))
  {
	  p.sendMessage("§eLista de fakes");
	  for (Player p2 : Bukkit.getOnlinePlayers()) {
		 if (FakeAPI.hasFake(p2)) {
			 p.sendMessage("§eNick Real: " + PlayerJoinListener.playerrealname.get(p) + " §eFake:" + Fake.playerfakename.get(p));
		 } else {
			 p.sendMessage("§cNão há players de /fake online!"); 
	  }
    p.playSound(p.getLocation(), Sound.GHAST_SCREAM, 12.0F, 12.0F);
  }
}

return false;
}
}

package net.helix.pvp.command;


	import org.bukkit.Sound;
	import org.bukkit.command.Command;
	import org.bukkit.command.CommandExecutor;
	import org.bukkit.command.CommandSender;
	import org.bukkit.entity.Player;

	public class Regras 
	implements CommandExecutor
	{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
	  Player p = (Player)sender;
	  if (label.equalsIgnoreCase("regras"))
	  {
		p.sendMessage("§cLeia as regras");  
		p.sendMessage("");
		p.sendMessage("§e1º Uso de Trapaças: Usar Hack ou Cheats é extremamente proibido.");
	    p.sendMessage("§e2º Assédio: Não fazer comentários ofensivos com finalidades sexuais.");
	    p.sendMessage("§e3º Ameaças: Não utilizar discursos de ódio relacionado à agressão ou qualquer tipo de ato maldoso direcionado ao membro ameaçado.");
	    p.sendMessage("§e4º Brigas/Discussões (ofensas): Não discuta com a finalidade de ofender, seja por meio verbal ou por escrito, caso aconteça procure um responsável.");
	    p.sendMessage("§e5º Comércio: É proibido, fazer revenda/comércio em nossos chats ou chamadas, sem autorização.");
	    p.sendMessage("§e6º Discriminação: É uma ação preconceituosa em relação a uma pessoa ou grupo de pessoas. Seja por questões raciais, de gênero, orientação sexual, nacionalidade, religião entre outras, sem consentimento da mesma.");
	    p.sendMessage("§e7º Divulgação de servidores: Divulgar outro servidor sem ser a Sloper no tell de algum membro, irá ter punição de banimento permanente.");
	    p.playSound(p.getLocation(), Sound.BLAZE_HIT, 12.0F, 12.0F);
	  }
	  return false;
	}
	}

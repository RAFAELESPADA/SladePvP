package net.helix.pvp.listener;


import lombok.Getter;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.account.provider.PlayerPvP;
import net.helix.core.bukkit.api.HelixActionBar;
import net.helix.pvp.listener.PlayerDeathListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

@Getter
public enum Ranking {

    GOD("God", '✯', ChatColor.DARK_PURPLE, 9800),
    LENDARIO("Lendário", '۞', ChatColor.AQUA, 7200),
    RUBY("Ruby", '✧', ChatColor.DARK_RED, 5700),
    SAFIRA("Safira", '✪', ChatColor.BLUE, 4300),
    CRISTAL("Cristal", '❂', ChatColor.GOLD, 3500),
    ESMERALDA("Esmeralda", '✵', ChatColor.DARK_GREEN, 3000),
    DIAMANTE("Diamante", '✹', ChatColor.AQUA, 2200),
    OURO("Ouro", '✸', ChatColor.GOLD, 1500),
    PRATA("Prata", '✸', ChatColor.WHITE, 1200),
    VETERANO("Veterano", '✶', ChatColor.RED, 800),
    EXPERIENTE("Experiente", '✶', ChatColor.DARK_GRAY, 550),
    AVANCADO("Avancado", '✶', ChatColor.GOLD, 300),
    INICIANTE("Iniciante", '=', ChatColor.WHITE, 100),
    UNRANKED("Unranked", '☄', ChatColor.GRAY, 0);

    private final String name;
    private final char symbol;
    private final ChatColor color;
    private final int xp;

    public Ranking next() {
        return Ranking.values()[(this.ordinal() - 1) % Ranking.values().length];
    }
    public Ranking prev() {
        return Ranking.values()[(this.ordinal() + 1) % Ranking.values().length];
    }

    Ranking(String name, char symbol, ChatColor color, int xp) {
        this.name = name;
        this.symbol = symbol;
        this.color = color;
        this.xp = xp;
    }

    public String getColoredName() {
        return color + name;
    }

    public String getColoredSymbol() {
        return String.valueOf(color.toString() + symbol);
    }

    public String getColoredSymbolName() {
        return this.getColoredSymbol() + " " + name;
    }

    public static Ranking getRank(HelixPlayer playerData) {
        int xp = playerData.getPvp().getKills();
        for (Ranking rank : Ranking.values()) {
            if (xp >= rank.getXp())
                return rank;
        }
        return GOD;
    }

	
	   public static void checkRank(Player p) {
	        HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
	        if(helixPlayer == null) return;
			PlayerPvP pvp = helixPlayer.getPvp();

			if (pvp.getKills() == 100) {
		        p.sendMessage("Você subiu para o Ranking §aINICIANTE§f.");
	        Bukkit.broadcastMessage("§a" + p.getName() + " §fsubiu do rank UNRANKED" +  " para o Rank INICIANTE");
	        PlayerDeathListener.throwRandomFirework(p);
	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
	        	  HelixActionBar.send(p1, "§a" + p.getName() + " §fsubiu para o Rank INICIANTE");
	        }
			}
	    else if (pvp.getKills() == 300) {
	        p.sendMessage("Você subiu para o Ranking §aAVANCADO§f.");
	        Bukkit.broadcastMessage("§a" + p.getName() + " §fsubiu do rank INICIANTE" +  " para o Rank AVANÇADO");
	        PlayerDeathListener.throwRandomFirework(p);
	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
	            HelixActionBar.send(p1, "§a" + p.getName() + " §fsubiu para o Rank AVANÇADO");
	        	
	        }	        
	        }
	        else if (pvp.getKills() == 550) {
	        	PlayerDeathListener.throwRandomFirework(p);
	        p.sendMessage("Você subiu para o Ranking §aEXPERIENTE§f.");
	        Bukkit.broadcastMessage("§a" + p.getName() + " §fsubiu do rank AVANÇADO" +  " para o Rank EXPERIENTE");
	 
	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
	            HelixActionBar.send(p1, "§a" + p.getName() + " §fsubiu para o Rank EXPERIENTE");
	        }
	        }
	        else if (pvp.getKills() == 800) {
	        	PlayerDeathListener.throwRandomFirework(p);
	        p.sendMessage("Você subiu para o Ranking §aVETERANO§f.");
	        Bukkit.broadcastMessage("§a" + p.getName() + " §fsubiu do rank EXPERIENTE" +  " para o Rank VETERANO");
	    
	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
	            HelixActionBar.send(p1, "§a" + p.getName() + " §fsubiu para o Rank VETERANO");
	        }
	        }
	        else if (pvp.getKills() == 1200) {
	        	PlayerDeathListener.throwRandomFirework(p);
	        p.sendMessage("Você subiu para o Ranking §aPRATA§f.");
	        Bukkit.broadcastMessage("§a" + p.getName() + " §fsubiu do rank VETERANO" +  " para o Rank PRATA");
	       
	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
	        	 HelixActionBar.send(p1, "§a" + p.getName() + " §fsubiu para o Rank PRATA");
	        }
	        }
	        else if (pvp.getKills() == 1500) {
	        	PlayerDeathListener.throwRandomFirework(p);
	        p.sendMessage("Você subiu para o Ranking §aOURO§f.");
	        Bukkit.broadcastMessage("§a" + p.getName() + " §fsubiu do rank PRATA" +  " para o Rank OURO");
	      
	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
	        	  HelixActionBar.send(p1, "§a" + p.getName() + " §fsubiu para o Rank OURO");
	        }
	        }
	        else if (pvp.getKills() == 2200) {
	        	PlayerDeathListener.throwRandomFirework(p);
	        p.sendMessage("Você subiu para o Ranking §aDIAMANTE§f.");
	        Bukkit.broadcastMessage("§a" + p.getName() + " §fsubiu do rank OURO" +  " para o Rank DIAMANTE");
	       
	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
	        	 HelixActionBar.send(p1, "§a" + p.getName() + " §fsubiu para o Rank DIAMANTE");
	        }
	        }
	        else if (pvp.getKills() == 3000) {
	        	PlayerDeathListener.throwRandomFirework(p);
	        p.sendMessage("Você subiu para o Ranking §aESMERALDA§f.");
	        Bukkit.broadcastMessage("§a" + p.getName() + " §fsubiu do rank DIAMANTE" +  " para o Rank ESMERALDA");
	     
	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
	        	   HelixActionBar.send(p1, "§a" + p.getName() + " §fsubiu para o Rank ESMERALDA");
	        }
	        }
	        else if (pvp.getKills() == 3500) {
	        p.sendMessage("Você subiu para o Ranking §aCRISTAL§f.");
	        PlayerDeathListener.throwRandomFirework(p);
	        Bukkit.broadcastMessage("§a" + p.getName() + " §fsubiu do rank ESMERALDA" +  " para o Rank CRISTAL");
	       
	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
	        	 HelixActionBar.send(p1, "§a" + p.getName() + " §fsubiu para o Rank CRISTAL");
	        }
	        }
	        else if (pvp.getKills() == 4300) {
	        	PlayerDeathListener.throwRandomFirework(p);
	        p.sendMessage("Você subiu para o Ranking §aSAFIRA§f.");
	        Bukkit.broadcastMessage("§a" + p.getName() + " §fsubiu do rank CRISTAL" +  " para o Rank SAFIRA");
	      
	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
	        	  HelixActionBar.send(p1, "§a" + p.getName() + " §fsubiu para o Rank SAFIRA");
	        }
	        }
	        else if (pvp.getKills() == 5700) {
	        	PlayerDeathListener.throwRandomFirework(p);
	        p.sendMessage("Você subiu para o Ranking §aRUBY§f.");
	        Bukkit.broadcastMessage("§a" + p.getName() + " §fsubiu do rank SAFIRA" +  " para o Rank RUBY");
	       
	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
	        	 HelixActionBar.send(p1, "§a" + p.getName() + " §fsubiu para o Rank RUBY");
	        }
	        }
	        else if (pvp.getKills() == 7200) {
	        	PlayerDeathListener.throwRandomFirework(p);
	        p.sendMessage("Você subiu para o Ranking §aLENDÁRIO§f.");
	        Bukkit.broadcastMessage("§a" + p.getName() + " §fsubiu do rank RUBY" +  " para o Rank LENDÁRIO");
	     
	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
	        	   HelixActionBar.send(p1, "§a" + p.getName() + " §fsubiu para o Rank LENDÁRIO");
	        }
	        }
	        	
	        else if (pvp.getKills() == 9800) {
	        	PlayerDeathListener.throwRandomFirework(p);
	        p.sendMessage("Você subiu para o Ranking §aGOD§f.");
	        Bukkit.broadcastMessage("§a" + p.getName() + " §fsubiu do rank AMETISTA" +  " para o Rank GOD");
	        HelixActionBar.send(p, "§a" + p.getName() + " §fsubiu para o Rank GOD");
	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
	        }
	        }
	   }
	public int getXp() {
		return xp;
	}
	public static void checkRankt(Player player) {
		   HelixPlayer playerData = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
        if (Ranking.getRank(playerData) == Ranking.GOD) return;
        int xp = playerData.getPvp().getKills();
        for (Ranking rank : Ranking.values()) {
            if (xp >= rank.getXp())
        	HelixActionBar.send(player, "§aParabéns §eVocê alcançou o rank " + Ranking.getRank(playerData).next().getColoredName());
            player.sendMessage("§aParabéns §eVocê alcançou o rank " + Ranking.getRank(playerData).next().getColoredName());
            Bukkit.broadcastMessage("§a" + player.getName() + " §fsubiu do rank " + Ranking.getRank(playerData).getColoredName()  + " para o Rank " + Ranking.getRank(playerData).next().getColoredName());
	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
	        	HelixActionBar.send(p1, "§a" + player.getName() + " §fsubiu para o Rank " + Ranking.getRank(playerData).next().getColoredName());
	        }
	        }
            PlayerDeathListener.throwRandomFirework(player);
            PlayerDeathListener.throwRandomFirework(player);
        }
	public String getName() {
		return name;
	}
    }



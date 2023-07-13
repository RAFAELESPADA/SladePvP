package net.helix.pvp.listener;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import lombok.Getter;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.account.provider.PlayerPvP;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.provider.HelixActionBar;

@Getter
public enum Ranking {

    GOD(HelixPvP.getInstance().getConfig().getString("GodRank"), '✯', ChatColor.DARK_PURPLE, HelixPvP.getInstance().getConfig().getInt("GodKills")),
    LENDARIO(HelixPvP.getInstance().getConfig().getString("LendarioRank"), '۞', ChatColor.AQUA, HelixPvP.getInstance().getConfig().getInt("LendarioKills")),
    RUBY(HelixPvP.getInstance().getConfig().getString("RubyRank"), '✧', ChatColor.DARK_RED, HelixPvP.getInstance().getConfig().getInt("RubyKills")),
    SAFIRA(HelixPvP.getInstance().getConfig().getString("SafiraRank"), '✪', ChatColor.BLUE, HelixPvP.getInstance().getConfig().getInt("SafiraKills")),
    CRISTAL(HelixPvP.getInstance().getConfig().getString("CristalRank"), '❂', ChatColor.GOLD, HelixPvP.getInstance().getConfig().getInt("CristalKills")),
    ESMERALDA(HelixPvP.getInstance().getConfig().getString("EsmeraldaRank"), '✵', ChatColor.DARK_GREEN, HelixPvP.getInstance().getConfig().getInt("EsmeraldaKills")),
    DIAMANTE(HelixPvP.getInstance().getConfig().getString("DiamanteRank"), '✹', ChatColor.AQUA,  HelixPvP.getInstance().getConfig().getInt("DiamanteKills")),
    OURO(HelixPvP.getInstance().getConfig().getString("OuroRank"), '✸', ChatColor.GOLD, HelixPvP.getInstance().getConfig().getInt("OuroKills")),
    PRATA(HelixPvP.getInstance().getConfig().getString("PrataRank"), '✸', ChatColor.WHITE, HelixPvP.getInstance().getConfig().getInt("PrataKills")),
    VETERANO(HelixPvP.getInstance().getConfig().getString("VeteranoRank"), '✶', ChatColor.RED, HelixPvP.getInstance().getConfig().getInt("VeteranoKills")),
    EXPERIENTE(HelixPvP.getInstance().getConfig().getString("ExperienteRank"), '✶', ChatColor.DARK_GRAY, HelixPvP.getInstance().getConfig().getInt("ExperienteKills")),
    AVANCADO(HelixPvP.getInstance().getConfig().getString("AvancadoRank"), '✶', ChatColor.GOLD, HelixPvP.getInstance().getConfig().getInt("AvancadoKills")),
    INICIANTE(HelixPvP.getInstance().getConfig().getString("InicianteRank"), '=', ChatColor.WHITE, HelixPvP.getInstance().getConfig().getInt("InicianteKills")),
    UNRANKED(HelixPvP.getInstance().getConfig().getString("UnrankedRank"), '☄', ChatColor.GRAY, 0);

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

			if (pvp.getKills() == HelixPvP.getInstance().getConfig().getInt("InicianteKills")) {
		        p.sendMessage("Você upou para o rank " + HelixPvP.getInstance().getConfig().getString("InicianteRank").toUpperCase());
	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + HelixPvP.getInstance().getConfig().getString("UnrankedRank").toUpperCase() +  " para o Rank " + HelixPvP.getInstance().getConfig().getString("InicianteRank").toUpperCase());
	        PlayerDeathListener.throwRandomFirework(p);
	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
	        	  HelixActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + HelixPvP.getInstance().getConfig().getString("InicianteRank").toUpperCase());
	        }
			}
	    else if (pvp.getKills() == HelixPvP.getInstance().getConfig().getInt("AvancadoKills")) {
	    	   p.sendMessage("Você upou para o rank " + HelixPvP.getInstance().getConfig().getString("AvancadoRank").toUpperCase());
		        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + HelixPvP.getInstance().getConfig().getString("InicianteRank").toUpperCase() +  " para o Rank " + HelixPvP.getInstance().getConfig().getString("AvancadoRank").toUpperCase());
		        PlayerDeathListener.throwRandomFirework(p);
		        for (Player p1 : Bukkit.getOnlinePlayers()) {
		        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
		        	  HelixActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + HelixPvP.getInstance().getConfig().getString("AvancadoRank").toUpperCase());
	        	
	        }	        
	        }
	        else if (pvp.getKills() == HelixPvP.getInstance().getConfig().getInt("ExperienteKills")) {
	        	   p.sendMessage("Você upou para o rank " + HelixPvP.getInstance().getConfig().getString("ExperienteRank").toUpperCase());
	   	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + HelixPvP.getInstance().getConfig().getString("AvancadoRank").toUpperCase() +  " para o Rank " + HelixPvP.getInstance().getConfig().getString("ExperienteRank").toUpperCase());
	   	        PlayerDeathListener.throwRandomFirework(p);
	   	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	   	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
	   	        	  HelixActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + HelixPvP.getInstance().getConfig().getString("ExperienteRank").toUpperCase());
	        }
	        }
	        else if (pvp.getKills() == HelixPvP.getInstance().getConfig().getInt("VeteranoKills")) {
	        	   p.sendMessage("Você upou para o rank " + HelixPvP.getInstance().getConfig().getString("VeteranoRank").toUpperCase());
		   	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + HelixPvP.getInstance().getConfig().getString("ExperienteRank").toUpperCase() +  " para o Rank " + HelixPvP.getInstance().getConfig().getString("VeteranoRank").toUpperCase());
		   	        PlayerDeathListener.throwRandomFirework(p);
		   	        for (Player p1 : Bukkit.getOnlinePlayers()) {
		   	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
		   	        	  HelixActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + HelixPvP.getInstance().getConfig().getString("VeteranoRank").toUpperCase());
		        }
	        }
	        else if (pvp.getKills() == HelixPvP.getInstance().getConfig().getInt("PrataKills")) {
	        	   p.sendMessage("Você upou para o rank " + HelixPvP.getInstance().getConfig().getString("PrataRank").toUpperCase());
		   	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + HelixPvP.getInstance().getConfig().getString("VeteranoRank").toUpperCase() +  " para o Rank " + HelixPvP.getInstance().getConfig().getString("PrataRank").toUpperCase());
		   	        PlayerDeathListener.throwRandomFirework(p);
		   	        for (Player p1 : Bukkit.getOnlinePlayers()) {
		   	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
		   	        	  HelixActionBar.send(p1, "§a" + p.getName() + " §fupou para Ranking " + HelixPvP.getInstance().getConfig().getString("PrataRank").toUpperCase());
		        }
	        }
	        else if (pvp.getKills() == HelixPvP.getInstance().getConfig().getInt("OuroKills")) {
	        	 p.sendMessage("Você upou para o rank " + HelixPvP.getInstance().getConfig().getString("OuroRank").toUpperCase());
		   	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + HelixPvP.getInstance().getConfig().getString("PrataRank").toUpperCase() +  " para o Rank " + HelixPvP.getInstance().getConfig().getString("OuroRank").toUpperCase());
		   	        PlayerDeathListener.throwRandomFirework(p);
		   	        for (Player p1 : Bukkit.getOnlinePlayers()) {
		   	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
		   	        	  HelixActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + HelixPvP.getInstance().getConfig().getString("OuroRank").toUpperCase());
		        }
	        }
	        else if (pvp.getKills() == HelixPvP.getInstance().getConfig().getInt("DiamanteKills")) {
	        	 p.sendMessage("Você upou para o rank " + HelixPvP.getInstance().getConfig().getString("DiamanteRank").toUpperCase());
		   	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + HelixPvP.getInstance().getConfig().getString("OuroRank").toUpperCase() +  " para o Rank " + HelixPvP.getInstance().getConfig().getString("DiamanteRank").toUpperCase());
		   	        PlayerDeathListener.throwRandomFirework(p);
		   	        for (Player p1 : Bukkit.getOnlinePlayers()) {
		   	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
		   	        	  HelixActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + HelixPvP.getInstance().getConfig().getString("DiamanteRank").toUpperCase());
		        }
	        }
	        else if (pvp.getKills() == HelixPvP.getInstance().getConfig().getInt("EsmeraldaKills")) {
	        	 p.sendMessage("Você upou para o rank " + HelixPvP.getInstance().getConfig().getString("EsmeraldaRank").toUpperCase());
		   	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + HelixPvP.getInstance().getConfig().getString("DiamanteRank").toUpperCase() +  " para o Rank " + HelixPvP.getInstance().getConfig().getString("EsmeraldaRank").toUpperCase());
		   	        PlayerDeathListener.throwRandomFirework(p);
		   	        for (Player p1 : Bukkit.getOnlinePlayers()) {
		   	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
		   	        	  HelixActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + HelixPvP.getInstance().getConfig().getString("EsmeraldaRank").toUpperCase());
		        }
	        }
	        else if (pvp.getKills() == HelixPvP.getInstance().getConfig().getInt("CristalKills")) {
	        	 p.sendMessage("Você upou para o rank " + HelixPvP.getInstance().getConfig().getString("CristalRank").toUpperCase());
		   	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + HelixPvP.getInstance().getConfig().getString("EsmeraldaRank").toUpperCase() +  " para o Rank " + HelixPvP.getInstance().getConfig().getString("CristalRank").toUpperCase());
		   	        PlayerDeathListener.throwRandomFirework(p);
		   	        for (Player p1 : Bukkit.getOnlinePlayers()) {
		   	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
		   	        	  HelixActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + HelixPvP.getInstance().getConfig().getString("CristalRank").toUpperCase());
		        }
	        }
	        else if (pvp.getKills() == HelixPvP.getInstance().getConfig().getInt("SafiraKills")) {
	        	p.sendMessage("Você upou para o rank " + HelixPvP.getInstance().getConfig().getString("SafiraRank").toUpperCase());
	   	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + HelixPvP.getInstance().getConfig().getString("CristalRank").toUpperCase() +  " para o Rank " + HelixPvP.getInstance().getConfig().getString("SafiraRank").toUpperCase());
	   	        PlayerDeathListener.throwRandomFirework(p);
	   	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	   	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
	   	        	  HelixActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + HelixPvP.getInstance().getConfig().getString("SafiraRank").toUpperCase());
	        }
        }
	        else if (pvp.getKills() == HelixPvP.getInstance().getConfig().getInt("RubyKills")) {
	        	p.sendMessage("Você upou para o rank " + HelixPvP.getInstance().getConfig().getString("RubyRank").toUpperCase());
	   	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + HelixPvP.getInstance().getConfig().getString("SafiraRank").toUpperCase() +  " para o Rank " + HelixPvP.getInstance().getConfig().getString("RubyRank").toUpperCase());
	   	        PlayerDeathListener.throwRandomFirework(p);
	   	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	   	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
	   	        	  HelixActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + HelixPvP.getInstance().getConfig().getString("RubyRank").toUpperCase());
	        }
        }
	        else if (pvp.getKills() == HelixPvP.getInstance().getConfig().getInt("LendarioKills")) {
	        	p.sendMessage("Você upou para o rank " + HelixPvP.getInstance().getConfig().getString("LendarioRank").toUpperCase());
	   	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + HelixPvP.getInstance().getConfig().getString("RubyRank").toUpperCase() +  " para o Rank " + HelixPvP.getInstance().getConfig().getString("LendarioRank").toUpperCase());
	   	        PlayerDeathListener.throwRandomFirework(p);
	   	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	   	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
	   	        	  HelixActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + HelixPvP.getInstance().getConfig().getString("LendarioRank").toUpperCase());
	        }
        }
	        	
	        else if (pvp.getKills() == HelixPvP.getInstance().getConfig().getInt("GodKills")) {
	        	p.sendMessage("Você upou para o rank " + HelixPvP.getInstance().getConfig().getString("GodRank").toUpperCase());
	   	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + HelixPvP.getInstance().getConfig().getString("LendarioRank").toUpperCase() +  " para o Rank " + HelixPvP.getInstance().getConfig().getString("GodRank").toUpperCase());
	   	        PlayerDeathListener.throwRandomFirework(p);
	   	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	   	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
	   	        	  HelixActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + HelixPvP.getInstance().getConfig().getString("GodRank").toUpperCase());
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
        	HelixActionBar.send(player, "§aCongrats! §eYou get the rank " + Ranking.getRank(playerData).next().getColoredName());
            player.sendMessage("§aCongrats! §eYou get the rank " + Ranking.getRank(playerData).next().getColoredName());
            Bukkit.broadcastMessage("§a" + player.getName() + " §fuped from the rank " + Ranking.getRank(playerData).getColoredName()  + " to the Rank " + Ranking.getRank(playerData).next().getColoredName());
	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	        	p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 10.0f, 1f);
	        	HelixActionBar.send(p1, "§a" + player.getName() + " §fupped to the Rank " + Ranking.getRank(playerData).next().getColoredName());
	        }
	        }
            PlayerDeathListener.throwRandomFirework(player);
            PlayerDeathListener.throwRandomFirework(player);
        }
	public String getName() {
		return name;
	}
    }



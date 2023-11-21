package net.helix.pvp.evento;



import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;

import lombok.Getter;
import net.helix.pvp.HelixPvP;

@Getter
public enum EventoType {

//StaffVsPlayers
    PVP("ArenaPvP", Arrays.asList("Hello everyone! Welcome to the event &c&lArena PvP&l&f!", "At event you, players will receive a kit with items", "After a while and with players being killed everyone will be teleported to new arenas.", "Everyone will have 20 seconds to prepare.", "Now lets pass the &c&lEVENT RULES&l&f:", "&fDont try to escape the pvp arena.", "&fdont do teams more than 2 people." , "Starting event! Good luck!"), new Location(Bukkit.getWorld(HelixPvP.getInstance().getConfig().getString("ARENAMUNDO")), HelixPvP.getInstance().getConfig().getInt("EventoArenaX"), HelixPvP.getInstance().getConfig().getInt("EventoArenaY"), HelixPvP.getInstance().getConfig().getInt("EventoArenaZ"))),
    LAVA("Lava", Arrays.asList("Hello players! Welcome to lava event", "At start everyone will receive a kit.", "Everyone will need to tank the lava you will be placed!", "Soups will not be replaced during event! So use them properly", "Everyone will have 10 seconds to prepare!", "Starting event! Good luck!"), new Location(Bukkit.getWorld(HelixPvP.getInstance().getConfig().getString("LAVAMUNDO")), HelixPvP.getInstance().getConfig().getInt("EventoLavaX"), HelixPvP.getInstance().getConfig().getInt("EventoLavaY"), HelixPvP.getInstance().getConfig().getInt("EventoLavaZ"))),
    MDR("Mdr", Arrays.asList("Welcome to the event Mom of Street!", "At event start you guys will receive a kit with unlimited golden apples!", "The main objetive is to pass the street without getting killed!", "If you die uou will be desqualified.", "If you play around or disobey the event promoter you will also be disqualified.", "Everyone will have 10 seconds to prepare!", "Starting! Good luck!"), new Location(Bukkit.getWorld(HelixPvP.getInstance().getConfig().getString("MDRMUNDO")), 326877.559, 70.0000000000, 345987.734)),
    _1v1("1v1", Arrays.asList("Welcome to the event 1x1!", "At the event start you guys will be pulled in 2 in 2 to a fight", "Who wins will come back at the event!", "And who loses will go back to spawn!", "Starting event! Have fun."), new Location(Bukkit.getWorlds().get(0), -56.778D, 144.47614D, -396.517D)),
    STAFFVSPLAYERS("StaffVsPlayers", Arrays.asList("Bem vindo ao evento Staff vs Players!", "Ao inicio do evento os staffs e players receberam itens diferentes", "Staff receberá dull dima e players full couro!", "E staff batalhara contra players!", "Ja que não tem como definir um ganhador nesse evento ele será sempre 4FUN", "Começando evento! Boa sorte."), new Location(Bukkit.getWorlds().get(0), 156161.441, 81.0000, 158151.654)),
    ;//156161.441 81.0000 158151.654
    private final String name;
    private final List<String> explicacao;
    private final Location location;
    EventoType(String name, List<String> explicacao, Location location) {
        this.name = name;
        this.explicacao = explicacao;
        this.location = location;
    }

    public static EventoType getEventoByName(String name) {
        for (EventoType evento : EventoType.values()) {
        	 if (evento.getName().equalsIgnoreCase(name))
                 return evento;
         }
        return null;
    }
    public String getName() {
		return name;
	}
    public List<String> getExplicacao() {
		return explicacao;
	}
    public Location getLocation() {
		return location;
	}

    public static void explicarEvento(EventoType evento) {
        List<String> explic = evento.getExplicacao();
        int actualsec = 1;
        for (String message : explic) {
            Bukkit.getScheduler().runTaskLater(HelixPvP.getInstance(), () -> {
            	EventoUtils.getEventoPlayers().forEach(p -> {
               p.sendMessage(HelixPvP.getInstance().getConfig().getString("Prefix").replace("&", "§") + " §f" + ChatColor.translateAlternateColorCodes('&', message));
               p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
        });
            	
    }, actualsec * 20);
            actualsec += 5;
        }
}
}


// mdr 801.5 100 519.5
// lava 641.5 118 518.5
// pvp 732.5 80 521.5
// 1v1 868.5 95 457.5

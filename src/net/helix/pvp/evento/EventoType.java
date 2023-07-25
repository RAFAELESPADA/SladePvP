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


    PVP("ArenaPvP", Arrays.asList("Olá, jogadores! Sejam todos bem-vindos ao evento &c&lArena PvP&l&f!", "Ao inicio do evento, vocês jogadores receberão um kit pré-definido com certa quantia de \"mantimentos\".", "Ao decorrer do evento e a diminuição de players, todos serão puxados a novas arenas, e ter�o um novo kit.", "Todos terão 20 segundos de invencibilidade para se organizarem.", "Agora, iremos repassar as &c&lRegras do Evento&l&f:", "&fnão ultrapassem nem andem por cima das obsidians das arenas, caso contrario, sera desclassificado.", "&fnão faça times a mais do que o evento proporciona, algo a mais resultará em kick." , "Iniciando o Evento! Boa sorte a todos e divirtam-se!"), new Location(Bukkit.getWorld(HelixPvP.getInstance().getConfig().getString("ARENAMUNDO")), HelixPvP.getInstance().getConfig().getInt("EventoArenaX"), HelixPvP.getInstance().getConfig().getInt("EventoArenaY"), HelixPvP.getInstance().getConfig().getInt("EventoArenaZ"))),
    LAVA("Lava", Arrays.asList("Olá, jogadores! Sejam todos bem-vindos ao evento LAVA!", "Ao início do evento, vocês jogadores receberão um kit pré-definido com certa quantia de \"mantimentos\".", "Todos serão colocados em uma arena com um nível de lava e todos terão de \"Tankar\" o dano até o último sobreviver!", "Durante o evento o kit não será reiniciado, portanto usem todas as sopas com sabedoria!", "Todos terão 10 segundos de invencibilidade até o dano ser liberado, portanto estejam preparados!", "Iniciando o Evento! Boa sorte a todos e divirtam-se!"), new Location(Bukkit.getWorld(HelixPvP.getInstance().getConfig().getString("LAVAMUNDO")), HelixPvP.getInstance().getConfig().getInt("EventoLavaX"), HelixPvP.getInstance().getConfig().getInt("EventoLavaY"), HelixPvP.getInstance().getConfig().getInt("EventoLavaZ"))),
    MDR("Mdr", Arrays.asList("Olá, jogadores! Sejam todos bem-vindos ao evento M�e Da Rua!", "Ao in�cio do evento, voc�s jogadores receber�o um kit pr�-definido com ma�as douradas &9&lILIMITADAS&f!", "O grande objetivo desse evento � atravessar a \"Rua\" sem ser pego/a, portanto tomem cuidado com o pegador!", "Caso morra nesse evento voc� ser� desclassificado.", "Ou caso descumpra a travessia ou fique brincando tamb�m ser�o desclassificado, lembre-se de seguir as regras do promotor do evento.", "Todos ter�o 10 segundos de invencibilidade ate o dano ser liberado, portanto estejam preparados!", "Iniciando o Evento! Boa sorte a todos e divirtam-se!"), new Location(Bukkit.getWorld(HelixPvP.getInstance().getConfig().getString("MDRMUNDO")), HelixPvP.getInstance().getConfig().getInt("EventoMDRX"), HelixPvP.getInstance().getConfig().getInt("EventoMDRY"), HelixPvP.getInstance().getConfig().getInt("EventoMDRZ"))),
    _1v1("1v1", Arrays.asList("Olá, jogadores! Sejam todos bem-vindos ao evento 1x1!", "Ao in�cio do evento, voc�s jogadores receber�o um kit pr�-definido com certa quantia de \"mantimentos\".", "Ao decorrer do Evento e a diminui��o de players, todos ser�o puxados para uma arena de 1 contra 1 e ter�o que duelar at� um ser vitorioso!", "Todos ter�o 10 segundos de invencibilidade at� o PvP ser liberado, portanto estejam preparados!", "Iniciando o Evento! Boa sorte a todos e divirtam-se!"), new Location(Bukkit.getWorlds().get(0), 868.5, 95, 457.5)),
    ;
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

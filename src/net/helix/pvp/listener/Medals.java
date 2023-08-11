package net.helix.pvp.listener;



import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import lombok.Getter;

@Getter
public enum Medals {

    NENHUM("§7Nenhuma", "§7✗", "§7Sem medalhas."),
    DEV("§eCafe", "§e\u2615", "§7Medalha Vip."),
    REI("§6Rei", "§6\u269C", "§7Medalha Vip."),
    KAWAII("§dPorquinho", "§d\u267E", "§7Para quem é fofinho."),
    HOMIE("§bHomies", "§b$", "§7Para quem é casa."),
    YIN_YANG("§fYin Yang", "§f\u262F", "§7Para quem é controlado."),
    SOL("§6Sol", "§6\u263C", "§7Medalha Vip."),
    AMOR("§cAmor", "§4\u2764", "§7Medalha Vip."),
    ;

    private final String name;
    private final String medal;
    private final String description;
    Medals(String name, String medal, String description) {
        this.name = name;
        this.medal = medal;
        this.description = description;
    }

    public String getName() {
        return this.name.substring(2).replace(" ", "");
    }
    
    public String getMedal() {
        return this.medal;
    }
    public String getDescription() {
        return this.description;
    }

    public String getColoredName() {
        return this.name;
    }
    public static Medals getMedals(Player playerData) {
        for (Medals medals : Medals.values()) {
        	if (playerData.hasPermission("medal. " + medals.getName()))
                return medals;
        }
        return NENHUM;
    }
    public static List<Medals> getMedals() {
        return Arrays.asList(values());
    }
    public static Medals getDefault() {
        return NENHUM;
    }
    
    public static Medals getMedalByName(String name) {
        for (Medals medal : Medals.values()) {
            if (medal.getName().toLowerCase().contains(name.toLowerCase())) {
                return medal;
            }
        }
        return null;
    }

    public static void giveMedaltoPlayer(Player player) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set medal.");
    }

}


// ��

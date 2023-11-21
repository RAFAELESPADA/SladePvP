package net.helix.pvp;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public enum PlayerGroup {

    DONO("DONO", 0, "Dono", "helix.tag.dono", ChatColor.DARK_RED, 1),
    ADMIN("SUBDONO", 1, "S-DonoB", "tag.admin", ChatColor.RED, 2),
    COORD("DEVELOPERVF", 2, "DevVB", "helix.tag.mod+", ChatColor.DARK_AQUA, 3),
    MOD("DEVELOPER4", 2, "DevV", "helix.tag.mod", ChatColor.DARK_GREEN, 3),
   HELPER("DEVELOPER", 2, "DevA", "helix.tag.helper", ChatColor.YELLOW, 3),
   ESTAGIO("DEVELOPER2", 2, "DevM", "helix.tag.estagiario", ChatColor.LIGHT_PURPLE, 3),
    BUGHUNTER("BUG", 18, "Booster", "helix.tag.bughunter", ChatColor.DARK_GRAY, 4),
    BOOSTER("BOOSTER", 18, "Booster", "helix.tag.booster", ChatColor.BLACK, 5),
    TOP1("TOP1", 18, "Top1", "helix.tag.top1", ChatColor.BLUE, 5),
    YT("YT", 18, "YT", "helix.tag.yt", ChatColor.AQUA, 6),
    YT2("YT2", 18, "YT", "helix.tag.creator", ChatColor.AQUA, 7),
    YT3("YT3", 18, "YT", "helix.tag.miniyt", ChatColor.AQUA, 8),
    YT4("YT4", 18, "YT", "helix.tag.yt+", ChatColor.AQUA, 9),
    YT5("YT4", 18, "YT", "helix.tag.miniyt", ChatColor.AQUA, 10),
    STREAM("STREAM", 17, "EMERALD", "helix.tag.streamer", ChatColor.AQUA, 111),  
    BETA("STREAM", 17, "EMERALD", "helix.tag.beta", ChatColor.GREEN, 111), 
    EMERALD2("EMERALD2", 18, "EMERALD2", "helix.tag.diamond", ChatColor.GREEN, 11),
    EMERALD("EMERALD", 18, "EMERALD", "helix.tag.emerald", ChatColor.GREEN, 11),
    GOLD("GOLD", 18, "Gold", "helix.tag.gold", ChatColor.GREEN, 12),
    IRON("IRON", 19, "Iron", "helix.tag.iron", ChatColor.GREEN, 13),
    MEMBRO("MEMBRO", 20, "Membro", "helix.tag.membro", ChatColor.GRAY, 14);

    private final String name;
    private final String permission;
    private final ChatColor color;
    private final int priority;

    PlayerGroup(final String s, final int n, final String name, final String permission, final ChatColor color, final int priority) {
        this.name = name;
        this.permission = permission;
        this.color = color;
        this.priority = priority;
    }

    public String getName() {
        return this.name;
    }

    public String getPermission() {
        return this.permission;
    }

    public ChatColor getColor() {
        return this.color;
    }

    public String getColoredName() {
        return this.getColor() + this.getName();
    }

    public int getPriority() {
        return this.priority;
    }

    public String getBoldColoredName() {
        return this.getColor() + "§l" + this.getName();
    }

    public static PlayerGroup getByName(final String name) {
        for (PlayerGroup group : PlayerGroup.values()) {
            if (group.name().equalsIgnoreCase(name)) {
                return group;
            }
        }
        return null;
    }

    public static PlayerGroup getGroup(final Player player) {
        for (PlayerGroup group : PlayerGroup.values()) {
            if (player.hasPermission(group.getPermission())) {
                return group;
            }
        }
        return PlayerGroup.MEMBRO;
    }

    public static String getPlayerNameWithGroup(Player player) {
        PlayerGroup group = getGroup(player);
        String prefix = group.getBoldColoredName().toUpperCase();
        return prefix + group.getColor() + " " + player.getName();
    }

}

package net.helix.pvp.cooldown1;




import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;

@Getter
public class UpdateEvent2 extends Event {

    public static final HandlerList handlers = new HandlerList();
    private UpdateType type;

   

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public enum UpdateType {
        SEGUNDO, MINUTO, HORA;
    }
}

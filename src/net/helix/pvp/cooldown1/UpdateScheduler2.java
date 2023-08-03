package net.helix.pvp.cooldown1;



import org.bukkit.Bukkit;


public class UpdateScheduler2 implements Runnable {

    private int currentSeconds;

    @Override
    public void run() {
        currentSeconds++;

        Bukkit.getPluginManager().callEvent(new UpdateEvent(UpdateEvent.UpdateType.SEGUNDO));

        if (currentSeconds % 60 == 0) {
            Bukkit.getPluginManager().callEvent(new UpdateEvent(UpdateEvent.UpdateType.MINUTO));
        }

        if (currentSeconds % 3600 == 0) {
            Bukkit.getPluginManager().callEvent(new UpdateEvent(UpdateEvent.UpdateType.HORA));
        }
    }
}


package net.helix.pvp.kit.provider;


import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.helix.pvp.HelixPvP;

public class Raios {
  public static double cos(double i) {
    return Math.cos(i);
  }
  
  public static double sin(double i) {
    return Math.sin(i);
  }
  
  public static void coneEffect(final Location loc) {
    (new BukkitRunnable() {
        double phi = 0.0D;
        
        public void run() {
          this.phi += 0.39269908169872414D;
          for (double t = 0.0D; t <= 6.283185307179586D; t += 0.19634954084936207D) {
            for (double i = 0.0D; i <= 1.0D; i++) {
              double x = 0.4D * (6.283185307179586D - t) * 0.5D * 
                Raios.cos(t + this.phi + i * Math.PI);
              double y = 0.5D * t;
              double z = 0.4D * (6.283185307179586D - t) * 0.5D * 
                Raios.sin(t + this.phi + i * Math.PI);
              loc.add(x, y, z);
              ParticleEffect.HEART.display(loc, 0.0F, 0.0F, 0.0F, 0.0F, 1);
              loc.subtract(x, y, z);
            } 
          } 
          if (this.phi > 31.41592653589793D)
            cancel(); 
        }
      }).runTaskTimer((Plugin)HelixPvP.getInstance(), 0L, 3L);
  }
  
  public static void onWaterbender(final Player p , Location loc) {
    (new BukkitRunnable() {
        double phi = 0.0D;
        
        public void run() {
          this.phi += 0.3141592653589793D;
          for (double t = 0.0D; t <= 15.707963267948966D; t += 0.07853981633974483D) {
            double r = 1.2D;
            double x = r * Raios.cos(t) * Raios.sin(this.phi);
            double y = r * Raios.cos(this.phi) + 1.2D;
            double z = r * Raios.sin(t) * Raios.sin(this.phi);
            loc.add(x, y, z);
            p.playEffect(loc, Effect.WATERDRIP, 2);
            loc.subtract(x, y, z);
          } 
          if (this.phi > Math.PI)
            cancel(); 
        }
      }).runTaskTimer((Plugin)HelixPvP.getInstance(), 0L, 1L);
  }
  
  public static void onFirebender(final Player p , final Location loc) {
    (new BukkitRunnable() {
        double phi = 0.0D;
        
        public void run() {
          this.phi += 0.3141592653589793D;
          for (double t = 0.0D; t <= 15.707963267948966D; t += 0.07853981633974483D) {
            double r = 1.2D;
            double x = r * Raios.cos(t) * Raios.sin(this.phi);
            double y = r * Raios.cos(this.phi) + 1.2D;
            double z = r * Raios.sin(t) * Raios.sin(this.phi);
            loc.add(x, y, z);
            p.playEffect(loc, Effect.FLAME, 2);
            loc.subtract(x, y, z);
          } 
          if (this.phi > Math.PI)
            cancel(); 
        }
      }).runTaskTimer((Plugin)HelixPvP.getInstance(), 0L, 1L);
  }
}

package net.helix.pvp.kit.provider;

import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.HelixKit2;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
public class Gaara extends KitHandler {
	  private List<UUID> blocos;
	  LinkedHashMap<Location, Block> restore = new LinkedHashMap<Location, Block>();
	  public Gaara() {
		    this.blocos = new ArrayList<>();
		  }
@EventHandler
private void blocosGaara(EntityChangeBlockEvent evento) {
  if (this.blocos.contains(evento.getEntity().getUniqueId()))
    evento.setCancelled(true); 
}

@EventHandler
private void eventoGaara(final PlayerInteractEntityEvent e) {
  final Player p = e.getPlayer();
  if (e.getRightClicked() instanceof Player && p.getItemInHand().getType() == Material.SAND) {
	  if (!KitManager.getPlayer(p.getName()).hasKit(this)) {
		  return;
	  }
	  if (GladiatorListener.combateGlad.containsKey(p) || net.helixpvp.kit2.GladiatorListener.combateGlad.containsKey(p)) {
	  		/*  62 */         p.sendMessage(String.valueOf("§cVocê esta no Gladiator e recebeu efeito de speed"));
	  		/*     */         
	  		/*  64 */        Kangaroo.darEfeito(p, org.bukkit.potion.PotionEffectType.SPEED, 10, 1);
	  		/*     */       
	  }

		 else if (p.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura")) {
			p.sendMessage("§cNão use o gaara no spawn!");
			return;
		 }
		 else if (KitManager.getPlayer(e.getRightClicked().getName()).hasKit(HelixKit.NEO) || KitManager2.getPlayer(e.getRightClicked().getName()).haskit2(HelixKit2.NEO)) {
	        	e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.NOTE_BASS_DRUM, 15.0f, 15.0f);
				p.sendMessage(ChatColor.RED + "Você nao pode usar gaara em " + ChatColor.DARK_RED + e.getRightClicked().getName() + ChatColor.RED + " porque ele esta com o kit" + ChatColor.DARK_RED + " NEO");
				return;
		 }
  Player r = (Player) e.getRightClicked().getPassenger();
  if (r != null) {
	 p.sendMessage("§cEsse jogador não pode ser puxado!"); 
	 return;
  }
      if (hasCooldown(p)) {
        sendMessageCooldown(p);
        return;
      } 
      addCooldown(p , 15);
      Kangaroo.darEfeito(p, PotionEffectType.SPEED, 10, 1);
      (new BukkitRunnable() {
          Location[] oldLoc = null;
          
          Location[] loc = new Location[20];
          
          int contador = -1;
          
          public void run() {
            if (e.getPlayer() != null && e.getRightClicked() != null && e.getPlayer().isOnline() && ((Player)e.getRightClicked()).isOnline()) {
              if (++this.contador != 3) {
                if (this.oldLoc != null) {
                  Location[] oldLoc;
                  for (int length = (oldLoc = this.oldLoc).length, i = 0; i < length; i++) {
                    Location old = oldLoc[i];
                    Block b =  old.getBlock();
            
                    old.getBlock().setType(Material.AIR);
                  } 
                } 
                int x = e.getRightClicked().getLocation().getBlockX();
                int y = e.getRightClicked().getLocation().getBlockY();
                int z = e.getRightClicked().getLocation().getBlockZ();
                e.getRightClicked().teleport(new Location(e.getRightClicked().getWorld(), x + 0.5D, (y + 4 + this.contador), z + 0.5D));
                this.loc[0] = e.getRightClicked().getLocation().add(0.0D, -1.0D, 0.0D);
                this.loc[1] = e.getRightClicked().getLocation().add(0.0D, -1.0D, 0.0D);
                this.loc[2] = e.getRightClicked().getLocation().add(1.0D, 1.0D, 1.0D);
                this.loc[3] = e.getRightClicked().getLocation().add(-1.0D, 1.0D, -1.0D);
                this.loc[4] = e.getRightClicked().getLocation().add(1.0D, 1.0D, -1.0D);
                this.loc[5] = e.getRightClicked().getLocation().add(-1.0D, 1.0D, 1.0D);
                this.loc[6] = e.getRightClicked().getLocation().add(0.0D, 0.0D, -1.0D);
                this.loc[7] = e.getRightClicked().getLocation().add(-1.0D, 0.0D, 0.0D);
                this.loc[8] = e.getRightClicked().getLocation().add(1.0D, 0.0D, 0.0D);
                this.loc[9] = e.getRightClicked().getLocation().add(0.0D, 0.0D, 1.0D);
                this.loc[10] = e.getRightClicked().getLocation().add(0.0D, 3.0D, 0.0D);
                this.loc[11] = e.getRightClicked().getLocation().add(0.0D, 2.0D, -1.0D);
                this.loc[12] = e.getRightClicked().getLocation().add(-1.0D, 2.0D, 0.0D);
                this.loc[13] = e.getRightClicked().getLocation().add(1.0D, 2.0D, 0.0D);
                this.loc[14] = e.getRightClicked().getLocation().add(0.0D, 2.0D, 1.0D);
                this.loc[15] = e.getRightClicked().getLocation().add(0.0D, 1.0D, -1.0D);
                this.loc[16] = e.getRightClicked().getLocation().add(-1.0D, 1.0D, 0.0D);
                this.loc[17] = e.getRightClicked().getLocation().add(1.0D, 1.0D, 0.0D);
                this.loc[18] = e.getRightClicked().getLocation().add(0.0D, 1.0D, 1.0D);
                this.loc[19] = e.getRightClicked().getLocation().add(0.0D, 2.0D, 0.0D);
                Location[] loc;
                for (int length2 = (loc = this.loc).length, j = 0; j < length2; j++) {
                  Location locais = loc[j];
                  if (locais.getBlock() != null) {
                	  p.sendMessage("§cVocê não pode usar o gaara aqui"); 
                	  return;
                  }
                  Block b = locais.getBlock();
                  BlockState bs = locais.getBlock().getState();
                  locais.getBlock().setType(Material.SANDSTONE);
                } 
                this.oldLoc = this.loc;
              } else {
                this.loc[0] = e.getRightClicked().getLocation().add(0.0D, -1.0D, 0.0D);
                this.loc[1] = e.getRightClicked().getLocation().add(0.0D, -1.0D, 0.0D);
                this.loc[2] = e.getRightClicked().getLocation().add(1.0D, 1.0D, 1.0D);
                this.loc[3] = e.getRightClicked().getLocation().add(-1.0D, 1.0D, -1.0D);
                this.loc[4] = e.getRightClicked().getLocation().add(1.0D, 1.0D, -1.0D);
                this.loc[5] = e.getRightClicked().getLocation().add(-1.0D, 1.0D, 1.0D);
                this.loc[6] = e.getRightClicked().getLocation().add(0.0D, 0.0D, -1.0D);
                this.loc[7] = e.getRightClicked().getLocation().add(-1.0D, 0.0D, 0.0D);
                this.loc[8] = e.getRightClicked().getLocation().add(1.0D, 0.0D, 0.0D);
                this.loc[9] = e.getRightClicked().getLocation().add(0.0D, 0.0D, 1.0D);
                this.loc[10] = e.getRightClicked().getLocation().add(0.0D, 3.0D, 0.0D);
                this.loc[11] = e.getRightClicked().getLocation().add(0.0D, 2.0D, -1.0D);
                this.loc[12] = e.getRightClicked().getLocation().add(-1.0D, 2.0D, 0.0D);
                this.loc[13] = e.getRightClicked().getLocation().add(1.0D, 2.0D, 0.0D);
                this.loc[14] = e.getRightClicked().getLocation().add(0.0D, 2.0D, 1.0D);
                this.loc[15] = e.getRightClicked().getLocation().add(0.0D, 1.0D, -1.0D);
                this.loc[16] = e.getRightClicked().getLocation().add(-1.0D, 1.0D, 0.0D);
                this.loc[17] = e.getRightClicked().getLocation().add(1.0D, 1.0D, 0.0D);
                this.loc[18] = e.getRightClicked().getLocation().add(0.0D, 1.0D, 1.0D);
                this.loc[19] = e.getRightClicked().getLocation().add(0.0D, 2.0D, 0.0D);
                Location[] oldLoc2;
                for (int length3 = (oldLoc2 = this.oldLoc).length, k = 0; k < length3; k++) {
                  Location old = oldLoc2[k];
                  
                  old.getBlock().setType(Material.AIR);
                } 
                Location[] loc2;
                for (int length4 = (loc2 = this.loc).length, l = 0; l < length4; l++) {
                  Location locais2 = loc2[l];
                  FallingBlock bloco = locais2.getBlock().getWorld().spawnFallingBlock(locais2, Material.SAND, (byte)0);
                  Gaara.this.blocos.add(bloco.getUniqueId());
                } 
                e.getRightClicked().setFallDistance(-10.0F);
                e.getRightClicked().getWorld().createExplosion(this.loc[19], 1.5F, true);
                cancel();
              } 
            } else {
                Location[] loc3;
                for (int length5 = (loc3 = this.loc).length, n = 0; n < length5; n++) {
                  Location locais2 = loc3[n];
                  locais2.getBlock().setType(Material.AIR);
                } 
                if (this.oldLoc != null) {
                  Location[] oldLoc3;
                  for (int length6 = (oldLoc3 = this.oldLoc).length, n2 = 0; n2 < length6; n2++) {
                    Location old = oldLoc3[n2];
                    old.getBlock().setType(Material.AIR);
                  } 
                } 
                cancel();
              } 
            
              
             
          }
        }).runTaskTimer(HelixPvP.getInstance(), 0L, 8L);
      Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
            public void run() {
              p.sendMessage("§aVocê pode usar novamente");
            }
          },  300L);
    }  
}
  


}
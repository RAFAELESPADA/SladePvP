package net.helix.pvp.command;


import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.core.util.UpdateEvent;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.evento.EventoType;
import net.helix.pvp.evento.EventoUtils;
import net.helix.pvp.warp.HelixWarp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

public class RDMAutomatic implements Listener {
  private HelixPvP main;
  
  private int time;
  
  private GameType gameType;
  
  private Listener listener;
  
  public List<Player> players;
  
  private int maxPlayers;
  public static boolean iniciou;
  private boolean full;
  
  private boolean pvp;
  
  private List<Player> playersInPvp;
  
  private List<Player> specs;
  
  public RDMAutomatic() {
    this.main = HelixPvP.getInstance();
    time = 31;
    players = new ArrayList<>();
    this.gameType = GameType.STARTING;
    this.maxPlayers = 60;
    this.full = false;
    this.pvp = false;
    this.specs = new ArrayList<>();
    playersInPvp = new ArrayList<>();
    Bukkit.broadcastMessage("§cO evento 1v1 irá iniciar em 30 segundos");
  }
          @EventHandler
          public void onUpdate(UpdateEvent e) {
            if (e.getType() != UpdateEvent.UpdateType.SEGUNDO)
              return; 
            if (RDMAutomatic.this.getGameType() == RDMAutomatic.GameType.STOPPED) {
            	return;
            }
            if (!iniciou) {
            	return;
            }
            if (this.getGameType() == GameType.STARTING) {
              if (time == 30) {
            	  EventoUtils.getEventoPlayers().forEach(p2 -> {
            		  if (!players.contains(p2)) {
  			 		players.add(p2);
  	        	    Bukkit.getConsoleSender().sendMessage(p2.getName() + " teve a variável setada novamente! Players no evento: " + players.size());
            		  }
            	  });
               Bukkit.broadcastMessage("§b" + players.size() + " jogadores de " + this.maxPlayers + " no evento.");
              } 
              if (time == 15) {
                  Bukkit.broadcastMessage("§bO evento da 1v1 irá iniciar em 15 segundos");
                  Bukkit.broadcastMessage("§b" + players.size() + " jogadores de " + this.maxPlayers + " no evento.");
              } 
              if (time == 10) {
                  Bukkit.broadcastMessage("§bO evento da 1v1 irá iniciar em 10 segundos");
                  Bukkit.broadcastMessage("§b" + players.size() + " jogadores de " + this.maxPlayers + " no evento.");
              } 
              if (players.size() == 50 && time >= 50 && !this.full) {
                time = 30;
               Bukkit.broadcastMessage("§bO tempo foi alterado para 30 segundos porque o evento está quase cheio!");
                this.full = true;
              } 
              if (time <= 0) {
                this.gameType = GameType.GAMIMG;
               Bukkit.broadcastMessage("§aO evento da 1v1 iniciou!");
              } 
             time = time - 1;
            } else if (!this.pvp && this.getGameType() == GameType.GAMIMG) {
              this.queuedPlayers();
            } 
          }
          public void putInEvent(Player player) {
        	    players.add(player);
        	    Bukkit.getConsoleSender().sendMessage(player.getName() + " teve a variável setada! Players no evento: " + players.size());
        	    player.getInventory().clear();
        	    player.getInventory().setArmorContents(null);
        	    for (PotionEffect pot : player.getActivePotionEffects())
        	      player.removePotionEffect(pot.getType()); 
        	  }
          
          @EventHandler
          public void onPlayerQuit(PlayerQuitEvent e) {
            if (players.contains(e.getPlayer())) {
              players.remove(e.getPlayer());
              if (playersInPvp.contains(e.getPlayer())) {
                e.getPlayer().damage(9999.0D);
                playersInPvp.remove(e.getPlayer());
                RDMAutomatic.this.pvp = false;
                RDMAutomatic.this.broadcast("§bO jogador " + e.getPlayer().getName() + " §bfoi eliminado do evento por combate log!");
                return;
              } 
              if (RDMAutomatic.this.getGameType() == RDMAutomatic.GameType.GAMIMG)
                RDMAutomatic.this.broadcast("§bO jogador" + e.getPlayer().getName() + " saiu do servidor e foi desclassificado do evento!"); 
            } 
          }
          
          @EventHandler
          public void onPlayerDeath(PlayerDeathEvent e) {
            if (!(e.getEntity() instanceof Player))
              return; 
            if (e.getEntity().getKiller() == null)
              return; 
            Player p = e.getEntity();
            Player d = e.getEntity().getKiller();
            if ((players.contains(d) || players.contains(p)) && 
              playersInPvp.contains(d) && playersInPvp.contains(p)) {
              playersInPvp.remove(p);
              players.remove(p);
              RDMAutomatic.this.pvp = false;
              p.sendMessage("§e§lEVENTO §fVocê foi eliminado do evento pelo "  + d.getName() + "!");
              RDMAutomatic.this.broadcast("§bO jogador " + p.getName() + " foi eliminado do evento pelo "  + d.getName() + "!");
              RDMAutomatic.this.broadcast("§b"+ players.size() + " jogadores restantes.");
              EventoType ev = EventoType.getEventoByName("1v1");
			 	 Location evt = ev.getLocation();
			 	 d.teleport(evt);
			 	playersInPvp.remove(d);
			 	 d.getInventory().clear();
			 	 d.getInventory().setArmorContents(null);
              RDMAutomatic.this.broadcast("§bProcurando proximo jogador...");
            } 
          }
          
          @EventHandler(priority = EventPriority.MONITOR)
          public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
            if (!(e.getDamager() instanceof Player))
              return; 
            if (!RDMAutomatic.this.isSpec((Player)e.getDamager()))
              return; 
            e.setCancelled(true);
          }
          
          @EventHandler
          public void onPlayerCommandPreProcess(PlayerCommandPreprocessEvent e) {
            Player p = e.getPlayer();
            if (!RDMAutomatic.this.isInEvent(p))
              return; 
            if (RDMAutomatic.this.isInPvP(p)) {
              e.setCancelled(true);
              return;
            } 
            if (e.getMessage().toLowerCase().startsWith("/") && !e.getMessage().toLowerCase().contains("/tell") && !e.getMessage().toLowerCase().contains("/spawn") && !p.hasPermission("kombo.cmd.report")) {
              e.setCancelled(true);
              p.sendMessage(String.valueOf("§bUse /spawn para sair do evento!"));
              return;
            } 
          }
  
  
  public boolean isInEvent(Player player) {
    return getPlayers().contains(player);
  }
  
  public void queuedPlayers() {
    Player firstPlayer = null;
    Player secondPlayer = null;
    if (players.size() == 1) {
      Player winner = players.get(0);
      playersInPvp.clear();
      Bukkit.broadcastMessage("§eO jogador " + winner.getName() + " ganhou o evento!");
      HelixPlayer player = HelixBukkit.getInstance().getPlayerManager().getPlayer(winner.getName());
      winner.sendMessage("§aVocê ganhou 200 de xp");
      winner.sendMessage("§aVocê ganhou 1000 de coins");
      player.getPvp().addCoins(1000);
      player.getPvp().addXP(200);
	  HelixBukkit.getInstance().getPlayerManager().getController().save(player);
      destroy();
      return;
    } 
    if (players.size() == 0 && iniciou) {
      Bukkit.broadcastMessage("§cNão houve nenhum ganhador!");
      playersInPvp.clear();
      EventoUtils.getEventoPlayers().forEach(p -> {
      	net.helix.pvp.evento.EventoUtils.setEvento(false, p);
          HelixWarp.SPAWN.send(p);
          p.sendMessage("§cO evento foi finalizado.");
          p.chat("/spawn");
          p.getActivePotionEffects().forEach(ef -> p.removePotionEffect(ef.getType()));
      });
      EventoUtils.resetEventoClass();
      iniciou = false;
      destroy();
      return;
    } 
    for (Player players : players) {
      if (!players.isOnline())
        this.players.remove(players); 
    } 
    firstPlayer = null;
    secondPlayer = players.get((new Random()).nextInt(players.size()));
    if (playersInPvp.isEmpty()) {
      firstPlayer = players.get((new Random()).nextInt(players.size()));
    } else {
      firstPlayer = playersInPvp.get(0);
      playersInPvp.clear();
    } 
    while (secondPlayer.getUniqueId().equals(firstPlayer.getUniqueId()))
      secondPlayer = players.get((new Random()).nextInt(players.size())); 
    firstPlayer.closeInventory();
    secondPlayer.closeInventory();
    send1v1(firstPlayer, secondPlayer);
  }
  
  public void broadcast(String message) {
    for (Player players : players)
      players.sendMessage(String.valueOf("§b§l1V1: §f") + message); 
    for (Player players : this.specs)
      players.sendMessage(String.valueOf(("§b§l1V1: §f") + message)); 
  }
  
  public void send1v1(Player firstPlayer, Player  secondPlayer) {
    playersInPvp.clear();
    playersInPvp.add(firstPlayer);
    playersInPvp.add(secondPlayer);
    System.out.println("[EVENTO] " + firstPlayer.getName() + " VS " + secondPlayer.getName());
    broadcast("§cO jogador §e" + firstPlayer.getName() + " §cirá lutar contra o §e" +  secondPlayer.getName());
    firstPlayer.setHealth(20.0D);
    secondPlayer.setHealth(20.0D);
    firstPlayer.teleport(new Location(Bukkit.getWorld("spawn"), 654131.732D, 75.00000000D, 6161616.726D));
    firstPlayer.getEyeLocation().setYaw(180.0F);
    secondPlayer.teleport(new Location(Bukkit.getWorld("spawn"), 654131.732D, 75.00000000D, 6161583.726D));
    secondPlayer.getEyeLocation().setYaw(0.0F);
    firstPlayer.sendMessage("§eVocê irá batalhar contra o "  + secondPlayer.getName());
    secondPlayer.sendMessage("§eVocê irá batalhar contra o " + firstPlayer.getName());
    for (PotionEffect pot : firstPlayer.getActivePotionEffects())
      firstPlayer.removePotionEffect(pot.getType()); 
    for (PotionEffect pot : secondPlayer.getActivePotionEffects())
      secondPlayer.removePotionEffect(pot.getType()); 
    firstPlayer.getInventory().clear();
    firstPlayer.getInventory().setArmorContents(new ItemStack[4]);
    firstPlayer.closeInventory();
    secondPlayer.getInventory().clear();
    secondPlayer.getInventory().setArmorContents(new ItemStack[4]);
    secondPlayer.closeInventory();
	ItemStack helmet = new ItemStack(Material.IRON_HELMET);
	ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
	ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);
	ItemStack boost = new ItemStack(Material.IRON_BOOTS);
    firstPlayer.getInventory().setHelmet(helmet);
    firstPlayer.getInventory().setChestplate(chest);
    firstPlayer.getInventory().setBoots(boost);
    firstPlayer.getInventory().setLeggings(leg);
    secondPlayer.getInventory().setHelmet(helmet);
    secondPlayer.getInventory().setChestplate(chest);
    secondPlayer.getInventory().setBoots(boost);
    secondPlayer.getInventory().setLeggings(leg);
    firstPlayer.getInventory().setItem(0, new ItemBuilder("§7Espada de Diamante", Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1)
			.nbt("cancel-drop")
			.toStack()
	);
    secondPlayer.getInventory().setItem(0, new ItemBuilder("§7Espada de Diamante", Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1)
			.nbt("cancel-drop")
			.toStack()
	);
    for (int x = 0; x < 8; x++) {
      firstPlayer.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
      secondPlayer.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
    } 
    this.pvp = true;
  }
  
  public void setGameType(GameType newtr) {
	  this.gameType = newtr;
  }
  public GameType getGameType() {
	    return this.gameType;
	  }
  public List<Player> getPlayers() {
    return players;
  }
  
  public List<Player> getPlayersInPvp() {
    return playersInPvp;
  }
  
  public int getMaxPlayers() {
    return this.maxPlayers;
  }
  
  
  
  public boolean isInPvP(Player player) {
    return (playersInPvp.contains(player) && getGameType() == GameType.GAMIMG);
  }
  
  public void destroy() {
    for (Player players2 : players) {
      Player player = players2;
      desmakeVanish(player);
      players2.setAllowFlight(false);
      players2.setFlying(false);
      HelixWarp.SPAWN.send(player);
      players2.setFlying(false);
      setGameType(GameType.STOPPED);
      iniciou = false;
      players.clear();
    		Bukkit.broadcastMessage("§6O Evento 1V1 foi finalizado!");
    		
      players2.setAllowFlight(false);
      Bukkit.getConsoleSender().sendMessage("PARANDO EVENTO 1V1");
    } 
    HandlerList.unregisterAll(this.listener);
   HelixPvP.getInstance().getEventManager().setRdmAutomatic(null);
  }

  public void setMaxPlayers(int maxPlayers) {
    this.maxPlayers = maxPlayers;
  }
  
  public void setTime(int time) {
    this.time = time;
  }
  public void desmakeVanish(Player p) {
	    if (p == null) {
	      return; 
	    }
	    for (Player player : Bukkit.getOnlinePlayers()) {
	      if (!player.getName().equals(p.getName()))
	        player.showPlayer(p); 
	    } 
  }
  
  public List<Player> getSpecs() {
    return this.specs;
  }
  
  public boolean isSpec(Player p) {
    return this.specs.contains(p);
  }
  
  
  public enum GameType {
    STARTING, GAMIMG , STOPPED;
  }
}


package net.helix.pvp.command;


import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.cooldown2.UpdateEvent;
import net.helix.pvp.evento.EventoType;
import net.helix.pvp.evento.EventoUtils;
import net.helix.pvp.warp.HelixWarp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
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
  public static boolean star;
  private boolean full;
  
  private boolean pvp;
  
  private List<Player> playersInPvp;
  
  private List<Player> specs;
  public static final List<String> playersIN = new ArrayList<>();
  public RDMAutomatic() {
    this.main = HelixPvP.getInstance();
    time = 32;
    players = new ArrayList<>();
    this.gameType = GameType.STARTING;
    this.maxPlayers = 60;
    this.full = false;
    this.pvp = false;
    this.specs = new ArrayList<>();
    playersInPvp = new ArrayList<>();
  }
          @EventHandler
          public void onUpdate(UpdateEvent e) {
            if (e.getType() != UpdateEvent.UpdateType.SEGUNDO) {
              return; 
            }
            if (!iniciou) {
            	return;
            }
     
              if (time == 31 && !star) {
            	  EventoUtils.getEventoPlayers().forEach(p2 -> {
            		  if (!players.contains(p2)) {
  			 		players.add(p2);
  	        	    Bukkit.getConsoleSender().sendMessage(p2.getName() + " teve a variável setada no evento! Players no evento: " + players.size());
            		  }
            	  });
              } 
              if (time == 30 && !star) {
                  Bukkit.broadcastMessage("§bThe event will start in 30 seconds");
                  Bukkit.broadcastMessage("§b" + players.size() + " player in the " + this.maxPlayers + " event.");
              }
              if (time == 15 && !star) {
                  Bukkit.broadcastMessage("§bThe event will start in 15 seconds");
                  Bukkit.broadcastMessage("§b" + players.size() + " player in the " + this.maxPlayers + " event.");
              } 
              if (time == 10 && !star) {
                  Bukkit.broadcastMessage("§bThe event will start in 10 seconds");
                  Bukkit.broadcastMessage("§b" + players.size() + " player in the " + this.maxPlayers + " event.");
              } 
              if (players.size() == 50 && time >= 50 && !this.full && !star) {
                time = 30;
               Bukkit.broadcastMessage("§bThe time got altered to 30 seconds because the event is almost full!");
                this.full = true;
              } 
              if (time <= 0 && !star) {
                this.gameType = GameType.GAMIMG;
               Bukkit.broadcastMessage("§aThe event 1v1 started!");
               star = true;
               queuedPlayers();
               time = 32;
              } 
              if (!star) {
            	  if (time > 0) {
             time = time - 1;
             Bukkit.getConsoleSender().sendMessage("[DEBUG] EVENTO COMECARA EM " + time + " SEGUNDOS");
              }
             if (!pvp && star) {
            	 Bukkit.getConsoleSender().sendMessage("[DEBUG] ENVIADO DOIS JOGADORES PARA O COMBATE!");
             queuedPlayers();
             }
            } 
          }
          public void putInEvent2(Player player) {
        	  if (players.contains(player)) {
        		  return;
        	  }
        	    players.add(player);
        	    Bukkit.getConsoleSender().sendMessage(player.getName() + " teve a variável setada 2! Players no evento: " + players.size());
          }
          public void putInEvent(Player player) {
        	  if (players.contains(player)) {
        		  return;
        	  }
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
                pvp = false;
                RDMAutomatic.this.broadcast("§bThe player " + e.getPlayer().getName() + " §bdied by combate log!");
                return;
              } 
              if (RDMAutomatic.this.getGameType() == RDMAutomatic.GameType.GAMIMG)
                RDMAutomatic.this.broadcast("§bThe player " + e.getPlayer().getName() + " left and is eliminated!"); 
              queuedPlayers();
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
            	if (!iniciou) {
            		return;
            	}
              playersInPvp.remove(p);
              players.remove(p);
              e.getDrops().clear();
              pvp = false;
              p.sendMessage("§e§lEVENT §fYou get elimited from the event by "  + d.getName() + "!");
              RDMAutomatic.this.broadcast("§bThe player " + p.getName() + " gets eliminated by "  + d.getName() + "!");
              RDMAutomatic.this.broadcast("§b"+ players.size() + " players left.");
              EventoType ev = EventoType.getEventoByName("1v1");
			 	 Location evt = ev.getLocation();
			 	 d.teleport(evt);
			 	playersInPvp.remove(d);
			 	 d.getInventory().clear();
			 	 d.getInventory().setArmorContents(null);
			 	   Bukkit.getConsoleSender().sendMessage(d.getName() + " killed " + p.getName() + " in the event 1v1");
              RDMAutomatic.this.broadcast("§bSearching next player...");
             
              queuedPlayers();
            } 
          }
          
          @EventHandler(priority = EventPriority.MONITOR)
          public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
            if (!(e.getDamager() instanceof Player))
              return; 
            if (!RDMAutomatic.this.isSpec((Player)e.getDamager()))
              return; 
            if (!iniciou) {
            	return;
            }
            e.setCancelled(true);
          }
          
          @EventHandler
          public void onPlayerCommandPreProcess(PlayerCommandPreprocessEvent e) {
            Player p = e.getPlayer();
            if (!RDMAutomatic.this.isInEvent(p))
              return; 
            if (RDMAutomatic.this.isInPvP(p) && iniciou) {
              e.setCancelled(true);
              p.sendMessage(String.valueOf("§eDont use commands in the battle."));
              return;
            } 
            if (e.getMessage().toLowerCase().startsWith("/") && !e.getMessage().toLowerCase().contains("/tell") && !p.hasPermission("kombo.cmd.report") && iniciou) {
              e.setCancelled(true);
              p.sendMessage(String.valueOf("§bTo leave the event left the server and logs in again!"));
              return;
            } 
          }
  
  
  public boolean isInEvent(Player player) {
    return getPlayers().contains(player);
  }
  public void removeFromEvent(Player player) {
	  if (isInEvent(player)) {
	    getPlayers().remove(player);
	    Bukkit.getConsoleSender().sendMessage(player + " foi retirado do evento 1v1!");
	  }
  }

  
  public void queuedPlayers() {
    Player firstPlayer = null;
    Player secondPlayer = null;
    for (Player players : players) {
      if (!players.isOnline()) {
        this.players.remove(players); 
        System.out.println("players off, removendo variaveis de: " + players.getName());
    } 
    }
    firstPlayer = null;
    {
    	if (!players.isEmpty()) {
    secondPlayer = players.get((new Random()).nextInt(players.size()));
      firstPlayer = players.get((new Random()).nextInt(players.size()));
      System.out.println("[EVENTO] PRIMEIRO PLAYER: " + firstPlayer.getName() + " VS SEGUNDO PLAYER: " + secondPlayer.getName());
      playersInPvp.clear();
    	}
    }
      if (firstPlayer != secondPlayer) {
    firstPlayer.closeInventory();
    secondPlayer.closeInventory();
    System.out.println("[EVENTO] " + firstPlayer.getName() + " VS " + secondPlayer.getName());
    send1v1(firstPlayer, secondPlayer);
    return;
  }
      else {
    	  {
    		  if (players.size() == 1) {
    			  System.out.println("[EVENTO] " + firstPlayer.getName() + " é o ganhador!");
    			  destroy();
    			  HelixWarp.SPAWN.send(firstPlayer);
    			  EventoUtils.setEvento(false, firstPlayer);
    			  EventoUtils.resetEventoClass();
    			  Player p = firstPlayer;
    			  Bukkit.broadcastMessage("§6Event Winner §a§ §C§l " + EventoUtils.getEventoPlayersNames());
	            	Bukkit.broadcastMessage("§6Event Winner §a§ §C§l " + EventoUtils.getEventoPlayersNames());
	            	Bukkit.broadcastMessage("§6Event Winner §a§ §C§l " + EventoUtils.getEventoPlayersNames());
	            	Bukkit.broadcastMessage("§6Event Winner §a§ §C§l " + EventoUtils.getEventoPlayersNames());
	            	Bukkit.broadcastMessage("§6Event Winner §a§ §C§l " + EventoUtils.getEventoPlayersNames());
	            	Bukkit.broadcastMessage("§6Event Winner §a§ §C§l " + EventoUtils.getEventoPlayersNames());
	            	Bukkit.broadcastMessage("§6Event Winner §a§ §C§l " + EventoUtils.getEventoPlayersNames());
	            	p.setHealth(20);
	
	            	    
	            	   
	            	      p.setAllowFlight(false);
	            	      p.setFlying(false);
	            	      HelixWarp.SPAWN.send(p , true);
	            	      p.setFlying(false);
	            	      RDMAutomatic.iniciou = false;
	            	      RDMAutomatic.star = false;
	            	      	net.helix.pvp.evento.EventoUtils.setEvento(false, p);
	            	          p.sendMessage("§cThe event ended.");
	            	          p.getActivePotionEffects().forEach(ef -> p.removePotionEffect(ef.getType()));
	            	          Bukkit.broadcastMessage("§eO jogador " + p.getName() + " ganhou o evento!");
          	    	      HelixPlayer player = HelixBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
          	    	      p.sendMessage("§aYou received 200 XP");
          	    	      p.sendMessage("§aYou received 1000 of coins");
          	    	      player.getPvp().addCoins(1000);
          	    	      player.getPvp().addXP(200);
          	    		  HelixBukkit.getInstance().getPlayerManager().getController().save(player);
	            	      EventoUtils.resetEventoClass();
	            	    		Bukkit.broadcastMessage("§6O Evento 1V1 foi finalizado!");
	            	    		for (Player pg : Bukkit.getOnlinePlayers()) {
	            	    			pg.playSound(pg.getLocation(), Sound.GHAST_SCREAM, 10f, 10f);
	            	    		}
	            	      p.setAllowFlight(false);
    			  return;
    		  }
          System.out.println("[EVENTO] " + firstPlayer.getName() + " e " + secondPlayer.getName() + " SÃO IGUAIS! RODANDO TASK NOVAMENTE");
          queuedPlayers();
    	  }
      }
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
    broadcast("§cO jogador §e" + firstPlayer.getName() + " §cwill fight against §e" +  secondPlayer.getName());
    firstPlayer.setHealth(20.0D);
    secondPlayer.setHealth(20.0D);
    firstPlayer.teleport(new Location(Bukkit.getWorld("spawn"), -44.492D, 109.00000000D, -430.454D));
    firstPlayer.getEyeLocation().setYaw(180.0F);
    secondPlayer.teleport(new Location(Bukkit.getWorld("spawn"), -44.550D, 109.0000000000D, -358.578D));
    secondPlayer.getEyeLocation().setYaw(0.0F);
    firstPlayer.sendMessage("§eYou will battle against "  + secondPlayer.getName());
    secondPlayer.sendMessage("§eYou will battle against " + firstPlayer.getName());
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
      setGameType(GameType.STARTING);
      iniciou = false;
      star = false;
      players.clear();
      time = 32;
      pvp = false;
      playersInPvp.clear();
      Bukkit.getConsoleSender().sendMessage("PARANDO EVENTO 1V1");
      getPlayers().clear();
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


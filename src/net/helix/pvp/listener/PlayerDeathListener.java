package net.helix.pvp.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.inventivetalent.bossbar.BossBarAPI;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.util.HelixCooldown2;
import net.helix.pvp.event.HelixPlayerDeathEvent;
import net.helix.pvp.event.HelixPlayerDeathEvent.Reason;
import net.helix.pvp.evento.EventoUtils;
import net.helix.pvp.kit.Habilidade;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.provider.GladiatorListener;
import net.helix.pvp.kit.provider.TimeLord;
import net.helix.pvp.warp.HelixWarp;

public class PlayerDeathListener implements Listener {
	
	private final static HashMap<String, List<String>> lastKillsMap = new HashMap<>();

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		Player killer = event.getEntity().getKiller();
		Location deathLocation = player.getLocation().clone();
		boolean validKill = false;
		HelixCooldown2.removeCooldown(player , "Kit");
		BossBarAPI.removeAllBars(player);
		player.getActivePotionEffects().forEach(it -> player.removePotionEffect(it.getType()));
		
		player.spigot().respawn();
		event.setDeathMessage(null);
		event.setDroppedExp(0);
		event.getDrops().clear();
		if (killer != null) {
			List<String> lastKills = lastKillsMap.containsKey(killer.getName()) ?
					lastKillsMap.get(killer.getName()) : new ArrayList<>();
			
			
			int repeatedKills = (int) lastKills.stream().filter(
					username -> username.equalsIgnoreCase(player.getName())
			).count() + 1;
			
			int allowRepeatedKills = 2;
			if (repeatedKills <= allowRepeatedKills) {
				validKill = true;
				lastKills.add(player.getName());
				
				if (lastKills.size() >= 3) {
					lastKills.clear();
				}
			}
			lastKillsMap.put(killer.getName(), lastKills);
			   HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager().getPlayer(killer.getName());
		        if(helixPlayer == null) return;
				Ranking.checkRank(killer);
			throwRandomFirework(killer);
			if (TimeLord.playercongelados.contains(player.getName())) {
				TimeLord.playercongelados.remove(player.getName());
			}
			if (Habilidade.ContainsAbility(player)) {
			Habilidade.removeAbility(player);
			}
			{
			}
		}
		

		HelixPlayerDeathEvent helixPlayerDeathEvent2 = new HelixPlayerDeathEvent(
				player, killer, deathLocation,
				new ArrayList<>(event.getDrops()),
				Reason.ARENA,
				validKill
		);
		HelixPlayerDeathEvent helixPlayerDeathEvent5 = new HelixPlayerDeathEvent(
				player, killer, deathLocation,
				new ArrayList<>(event.getDrops()),
				Reason.FPS,
				validKill
		);
		HelixPlayerDeathEvent helixPlayerDeathEvent3 = new HelixPlayerDeathEvent(
				player, killer, deathLocation,
				new ArrayList<>(event.getDrops()),
				Reason.ONE_VS_ONE,
				validKill
		);
		HelixPlayerDeathEvent helixPlayerDeathEvent4 = new HelixPlayerDeathEvent(
				player, killer, deathLocation,
				new ArrayList<>(event.getDrops()),
				Reason.LAVA,
				validKill
		);
		 if (GladiatorListener.combateGlad.containsKey(player)) {
             final Player winner = GladiatorListener.combateGlad.get(player);
             final Player loser = player;
             GladiatorListener.resetGladiatorListenerByKill(winner, loser);
             GladiatorListener.combateGlad.remove(winner);
             GladiatorListener.combateGlad.remove(loser);
         }
		 if (net.helixpvp.kit2.GladiatorListener.combateGlad.containsKey(player)) {
             final Player winner = net.helixpvp.kit2.GladiatorListener.combateGlad.get(player);
             final Player loser = player;
             net.helixpvp.kit2.GladiatorListener.resetGladiatorListenerByKill(winner, loser);
             net.helixpvp.kit2.GladiatorListener.combateGlad.remove(winner);
             net.helixpvp.kit2.GladiatorListener.combateGlad.remove(loser);
         }
		 if (HelixWarp.ONE_VS_ONE.hasPlayer(player.getName())) {
			 Bukkit.getPluginManager().callEvent(helixPlayerDeathEvent3);	 
		 }
		 else if (HelixWarp.FPS.hasPlayer(player.getName())) {
			 Bukkit.getPluginManager().callEvent(helixPlayerDeathEvent5);	 
		 }
		 else if (HelixWarp.LAVACHALLENGE.hasPlayer(player.getName())) {
			 Bukkit.getPluginManager().callEvent(helixPlayerDeathEvent4);	 
		 }
		 else {
			 Bukkit.getPluginManager().callEvent(helixPlayerDeathEvent2);
		 }
	}
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent event) {
		ItemStack capacete0 = new ItemStack(Material.MUSHROOM_SOUP);
		ItemStack capacete1 = new ItemStack(Material.BOWL);
		ItemStack capacete2 = new ItemStack(Material.BROWN_MUSHROOM);
		ItemStack capacete3 = new ItemStack(Material.RED_MUSHROOM);
		
	if (!KitManager.getPlayer(event.getPlayer().getName()).hasKit() && event.getPlayer().getLocation().getY() > 145) {
		event.setCancelled(true);
	} else if (KitManager.getPlayer(event.getPlayer().getName()).hasKit() || event.getItem().getItemStack().equals(capacete1) || event.getItem().getItemStack().equals(capacete0) || event.getItem().getItemStack().equals(capacete2) || event.getItem().getItemStack().equals(capacete3)){
		event.setCancelled(false);
	}
	}
	 public static void throwRandomFirework(Player p) {
	        Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
	        FireworkMeta fwm = fw.getFireworkMeta();

	        //Our random generator
	        Random r = new Random();

	        //Get the type
	        int rt = r.nextInt(5) + 1;
	        FireworkEffect.Type type = FireworkEffect.Type.BALL;
	        if (rt == 2) type = FireworkEffect.Type.BALL_LARGE;
	        if (rt == 3) type = FireworkEffect.Type.BURST;
	        if (rt == 4) type = FireworkEffect.Type.CREEPER;
	        if (rt == 5) type = FireworkEffect.Type.STAR;

	        //Get our random colours
	        int r1i = r.nextInt(17) + 1;
	        int r2i = r.nextInt(17) + 1;
	        Color c1 = Color.fromRGB(r1i);
	        Color c2 = Color.fromRGB(r2i);
	        FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();

	        //Then apply the effect to the meta
	        fwm.addEffect(effect);

	        //Generate some random power and set it


	        //Create our effect with this   int rp = r.nextInt(2) + 1;
	        int rp = r.nextInt(2) + 1;
	        fwm.setPower(rp);

	        //Then apply this to our rocket
	        fw.setFireworkMeta(fwm);
	        
	    }
	 @EventHandler
		public void onDeath2(PlayerDeathEvent event) {
		 EventoUtils.setEvento(false, event.getEntity());
			if (EventoUtils.evento && EventoUtils.getEventoPlayers().size() == 1  && EventoUtils.getEventoPlayers().size() != 0 && EventoUtils.started)
		       EventoUtils.getEventoPlayers().forEach(p -> {
		    	   Bukkit.broadcastMessage("§6Vencedor do evento §a§ §C§l " + EventoUtils.getEventoPlayersNames());
		    	   Bukkit.broadcastMessage("§6Vencedor do evento §a§ §C§l " + EventoUtils.getEventoPlayersNames());
	            	Bukkit.broadcastMessage("§6Vencedor do evento §a§ §C§l " + EventoUtils.getEventoPlayersNames());
	            	Bukkit.broadcastMessage("§6Vencedor do evento §a§ §C§l " + EventoUtils.getEventoPlayersNames());
	            	Bukkit.broadcastMessage("§6Vencedor do evento §a§ §C§l " + EventoUtils.getEventoPlayersNames());
	            	Bukkit.broadcastMessage("§6Vencedor do evento §a§ §C§l " + EventoUtils.getEventoPlayersNames());
	            	Bukkit.broadcastMessage("§6Vencedor do evento §a§ §C§l " + EventoUtils.getEventoPlayersNames());
	            	Bukkit.broadcastMessage("§6Vencedor do evento §a§ §C§l " + EventoUtils.getEventoPlayersNames());
	            	p.setHealth(20);
	                p.getWorld().strikeLightning(p.getLocation());
	                p.getWorld().strikeLightning(p.getLocation());
	            	p.getWorld().strikeLightning(p.getLocation());
	            	p.getWorld().strikeLightning(p.getLocation());
	        		HelixWarp.SPAWN.send(p);
	        		EventoUtils.resetEventoClass();
		       });
	 }
		            		
		 
					    
			 
	 @EventHandler
		public void onDeath2(PlayerQuitEvent event) {
		 EventoUtils.setEvento(false, event.getPlayer());
				if (EventoUtils.evento && EventoUtils.getEventoPlayers().size() == 1 && EventoUtils.getEventoPlayers().size() != 0 && EventoUtils.started)
			       EventoUtils.getEventoPlayers().forEach(p -> {
			    	   Bukkit.broadcastMessage("§6Vencedor do evento §a§ §C§l " + EventoUtils.getEventoPlayersNames());
		            	Bukkit.broadcastMessage("§6Vencedor do evento §a§ §C§l " + EventoUtils.getEventoPlayersNames());
		            	Bukkit.broadcastMessage("§6Vencedor do evento §a§ §C§l " + EventoUtils.getEventoPlayersNames());
		            	Bukkit.broadcastMessage("§6Vencedor do evento §a§ §C§l " + EventoUtils.getEventoPlayersNames());
		            	Bukkit.broadcastMessage("§6Vencedor do evento §a§ §C§l " + EventoUtils.getEventoPlayersNames());
		            	Bukkit.broadcastMessage("§6Vencedor do evento §a§ §C§l " + EventoUtils.getEventoPlayersNames());
		            	Bukkit.broadcastMessage("§6Vencedor do evento §a§ §C§l " + EventoUtils.getEventoPlayersNames());
		            	p.setHealth(20);
		                p.getWorld().strikeLightning(p.getLocation());
		                p.getWorld().strikeLightning(p.getLocation());
		            	p.getWorld().strikeLightning(p.getLocation());
		            	p.getWorld().strikeLightning(p.getLocation());
		        		HelixWarp.SPAWN.send(p);
		        		EventoUtils.resetEventoClass();
			       });
			}
	 
	 
				
	 
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		p.setFireTicks(0);
		p.setNoDamageTicks(0);
	}
}

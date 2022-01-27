package net.helix.pvp.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.Material;

import org.bukkit.inventory.ItemStack;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.account.provider.PlayerPvP;
import net.helix.pvp.event.HelixPlayerDeathEvent;
import net.helix.pvp.event.HelixPlayerDeathEvent.Reason;
import net.helix.pvp.kit.Habilidade;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.provider.GladiatorListener;
import net.helix.pvp.kit.provider.Sonic;
import net.helix.pvp.warp.HelixWarp;

public class PlayerDeathListener implements Listener {
	
	private final static HashMap<String, List<String>> lastKillsMap = new HashMap<>();

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		Player killer = event.getEntity().getKiller();
		Location deathLocation = player.getLocation().clone();
		boolean validKill = false;

		player.getActivePotionEffects().forEach(it -> player.removePotionEffect(it.getType()));
		
		player.spigot().respawn();
		event.setDeathMessage(null);
		event.setDroppedExp(0);
		
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
			if (Habilidade.ContainsAbility(player)) {
			Habilidade.removeAbility(player);
			}
			{
			}
		}
		
		HelixPlayerDeathEvent helixPlayerDeathEvent = new HelixPlayerDeathEvent(
				player, killer, deathLocation,
				new ArrayList<>(event.getDrops()), 
				HelixWarp.ONE_VS_ONE.hasPlayer(player.getName()) ? HelixPlayerDeathEvent.Reason.ONE_VS_ONE : Reason.ARENA,
				validKill
		);
		 if (GladiatorListener.combateGlad.containsKey(player)) {
             final Player winner = GladiatorListener.combateGlad.get(player);
             final Player loser = player;
             GladiatorListener.resetGladiatorListenerByKill(winner, loser);
             GladiatorListener.combateGlad.remove(winner);
             GladiatorListener.combateGlad.remove(loser);
         }
		event.getDrops().clear();
		Bukkit.getPluginManager().callEvent(helixPlayerDeathEvent);
		
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
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		p.setFireTicks(0);
		p.setNoDamageTicks(0);
	}
}

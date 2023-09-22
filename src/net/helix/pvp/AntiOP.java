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


public class AntiOP implements Listener  {
	

	public static HelixPvP instance;
	 private ScoreboardBuilder scoreboardBuilder;
	
	
	 @EventHandler
	 /*     */   public void quickcommand3f(PlayerCommandPreprocessEvent e)
	 /*     */   {
	 /* 382 */     if (e.getMessage().contains("/op")) {
	 /* 383 */       e.setCancelled(true);
	 /* 384 */       Player p = e.getPlayer();
	 p.sendMessage("Unknown command.");
	 }
	 }
	 @EventHandler
	 /*     */   public void quickcommand393(PlayerCommandPreprocessEvent e)
	 /*     */   {
	 /* 382 */     if (e.getMessage().contains("/lp") || e.getMessage().contains("/luckperms") || e.getMessage().contains("/perms") || e.getMessage().contains("/perm")) {
	 /* 384 */       Player p = e.getPlayer();
	 if ((!HelixPvP.getInstance().getConfig().getStringList("Ops").contains(p.getName()))) {
		 e.setCancelled(true);
		 p.sendMessage("This command is blocked.");
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

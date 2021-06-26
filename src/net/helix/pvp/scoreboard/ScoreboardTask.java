package net.helix.pvp.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class ScoreboardTask extends BukkitRunnable {

	private final ScoreboardBuilder builder;
	
	public ScoreboardTask(ScoreboardBuilder builder) {
		this.builder = builder;
	}
	
	@Override
	public void run() {
		Bukkit.getOnlinePlayers().stream().filter(
				online -> online.getScoreboard().getObjective("pvp") != null
		).forEach(builder::update);
	}
}

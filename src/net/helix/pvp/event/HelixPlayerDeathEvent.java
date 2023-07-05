package net.helix.pvp.event;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class HelixPlayerDeathEvent extends Event {
	
	public enum Reason {
		ARENA, ONE_VS_ONE, FPS, LAVA, GLADIATOR;
	}
	
	private final static HandlerList handlerList = new HandlerList();
	
	private final Player player;
	private final Player killer;
	private final Location deathLocation;
	private final List<ItemStack> drops;
	private  final Reason reason;
	private final boolean validKill;
	
	
	public HelixPlayerDeathEvent(Player player, Player killer, Location deathLocation, 
			List<ItemStack> drops, Reason reason, boolean validKill) {
		this.player = player;
		this.killer = killer;
		this.deathLocation = deathLocation;
		this.drops = drops;
		this.reason = reason;
		this.validKill = validKill;
	}

	@Override
	public HandlerList getHandlers() {
		return handlerList;
	}
	
	public static HandlerList getHandlerList() {
		return handlerList;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Player getKiller() {
		return killer;
	}
	
	public boolean hasKiller() {
		return killer != null;
	}
	
	public Location getDeathLocation() {
		return deathLocation;
	}

	public List<ItemStack> getDrops() {
		return drops;
	}
	
	public Reason getReason() {
		return reason;
	}
	
	public boolean isValidKill() {
		return validKill;
	}
}
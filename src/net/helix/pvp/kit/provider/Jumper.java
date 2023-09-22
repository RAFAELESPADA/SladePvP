package net.helix.pvp.kit.provider;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.spigotmc.event.entity.EntityDismountEvent;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.core.util.CooldownFinishEvent;
import net.helix.core.util.CooldownStartEvent;
import net.helix.core.util.HelixCooldown2;
import net.helix.core.util.HelixCooldownAPI;
import net.helix.core.util.ItemCooldown;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.command.ServerTimerEvent;
import net.helix.pvp.command.ServerTimerEvent2;
import net.helix.pvp.cooldown2.UpdateEvent;
import net.helix.pvp.cooldown2.UpdateEvent2;
import net.helix.pvp.kit.Ejectable;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.minecraft.server.v1_8_R3.EntityEnderPearl;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.World;

public class Jumper extends KitHandler implements Ejectable {
	
	private final List<UUID> track = new ArrayList<>();
	private final List<UUID> moving = new ArrayList<>();

	@Override
    public void execute(Player player) {
        super.execute(player);

        player.getInventory().setItem(1, new ItemBuilder(Material.EYE_OF_ENDER)
                .displayName("�aJumper")
                .nbt("cancel-drop")
                .nbt("kit-handler", "jumper")
                .toStack()
        );
    }
	
	@Override
	public void eject(Player player) {
		track.remove(player.getUniqueId());
		moving.remove(player.getUniqueId());
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onIteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (!KitManager.getPlayer(player.getName()).hasKit()) return;
        if (!KitManager.getPlayer(event.getPlayer().getName()).hasKit(HelixKit.JUMPER)) return;
		if (GladiatorListener.combateGlad.containsKey(player)) {
			return;
		}
		if (!event.getAction().toString().contains("RIGHT"))
			return;
		 if (!event.hasItem() || !ItemBuilder.has(event.getItem(), "kit-handler", "jumper")) return;
		 event.setCancelled(true);
		if (inCooldown(event.getPlayer())) {
			sendMessageCooldown(event.getPlayer()); 
			event.setCancelled(true);
			return;
			}
		
		if (event.getPlayer().getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura") && KitManager.getPlayer(event.getPlayer().getName()).hasKit(this) && EnderMageReal.isSpawn(event.getPlayer().getLocation())) {
	        	event.getPlayer().sendMessage("§cDont use the kit here!");
	        	event.setCancelled(true);
				return;
		
		
	} else {
			launchEnderPearl(player);
			event.setCancelled(true);
			addCooldown(player, HelixPvP.getInstance().getConfig().getInt("JumperCooldown"));
		}
	}
	
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onDamage(EntityDamageEvent event) {
		if (event.isCancelled())
			return;
		if (event.getEntity() instanceof Player) {
			DamageCause cause = event.getCause();
			if (cause == DamageCause.FALL || cause == DamageCause.SUFFOCATION) {
				if (moving.contains(((Player) event.getEntity()).getUniqueId())) {
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onTimer(ServerTimerEvent event) {
		Iterator<UUID> iterator = track.iterator();
		while (iterator.hasNext()) {
			Player next = Bukkit.getPlayer(iterator.next());
			if (next == null) {
				iterator.remove();
				continue;
			}
			next.getWorld().playEffect(next.getLocation(), Effect.INSTANT_SPELL, 10);
		}
	}
	@EventHandler
	public void onUpdate2(ServerTimerEvent2 event) {
		if (event.getCurrentTick() % 2 > 0)
			return;
		for (UUID uuid : net.helix.pvp.cooldown2.HelixCooldown2.map.keySet()) {
			Player player = Bukkit.getPlayer(uuid);
			if (player != null) {
				List<net.helix.pvp.cooldown2.HelixCooldownAPI> list = net.helix.pvp.cooldown2.HelixCooldown2.map.get(uuid);
				Iterator<net.helix.pvp.cooldown2.HelixCooldownAPI> it = list.iterator();

				net.helix.pvp.cooldown2.HelixCooldownAPI found = null;
				while (it.hasNext()) {
					net.helix.pvp.cooldown2.HelixCooldownAPI cooldown = it.next();
					if (!cooldown.expired()) {
						if (cooldown instanceof net.helix.pvp.cooldown2.ItemCooldown) {
							ItemStack hand = player.getEquipment().getItemInHand();
							if (hand != null && hand.getType() != Material.AIR) {
								net.helix.pvp.cooldown2.ItemCooldown item = (net.helix.pvp.cooldown2.ItemCooldown) cooldown;
								if (hand.equals(item.getItem())) {
									item.setSelected(true);
									found = item;
									break;
								}
							}
							continue;
						}
						found = cooldown;
						continue;
					}
					it.remove();
					Bukkit.getServer().getPluginManager().callEvent(new net.helix.pvp.cooldown2.CooldownFinishEvent(player, cooldown));
					 player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
				}

				if (found != null) {
					net.helix.pvp.cooldown2.CooldownStartEvent e = new net.helix.pvp.cooldown2.CooldownStartEvent(player, found);
					Bukkit.getPluginManager().callEvent(e);
					if (!e.isCancelled()) {
						net.helix.pvp.cooldown2.HelixCooldown2.display(player, found);
					}
				} else if (list.isEmpty()) {
				
					HelixCooldown2.map.remove(uuid);
				} else {
					net.helix.pvp.cooldown2.HelixCooldownAPI cooldown = list.get(0);
					if (cooldown instanceof net.helix.pvp.cooldown2.ItemCooldown) {
						net.helix.pvp.cooldown2.ItemCooldown item = (net.helix.pvp.cooldown2.ItemCooldown) cooldown;
						if (item.isSelected()) {
							item.setSelected(false);
							
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onUpdate(ServerTimerEvent event) {
		if (event.getCurrentTick() % 2 > 0)
			return;

		for (UUID uuid : net.helix.pvp.cooldown1.HelixCooldown2.map.keySet()) {
			Player player = Bukkit.getPlayer(uuid);
			if (player != null) {
				
				List<net.helix.pvp.cooldown1.HelixCooldownAPI> list = net.helix.pvp.cooldown1.HelixCooldown2.map.get(uuid);
				Iterator<net.helix.pvp.cooldown1.HelixCooldownAPI> it = list.iterator();

				net.helix.pvp.cooldown1.HelixCooldownAPI found = null;
				while (it.hasNext()) {
					net.helix.pvp.cooldown1.HelixCooldownAPI cooldown = it.next();
					if (!cooldown.expired()) {
						if (cooldown instanceof net.helix.pvp.cooldown1.ItemCooldown) {
							ItemStack hand = player.getEquipment().getItemInHand();
							if (hand != null && hand.getType() != Material.AIR) {
								net.helix.pvp.cooldown1.ItemCooldown item = (net.helix.pvp.cooldown1.ItemCooldown) cooldown;
								if (hand.equals(item.getItem())) {
									item.setSelected(true);
									found = item;
									break;
								}
							}
							continue;
						}
						found = cooldown;
						continue;
					}
					it.remove();
					Bukkit.getServer().getPluginManager().callEvent(new net.helix.pvp.cooldown1.CooldownFinishEvent(player, cooldown));
					 player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
				}

				if (found != null) {
					net.helix.pvp.cooldown1.CooldownStartEvent e = new net.helix.pvp.cooldown1.CooldownStartEvent(player, found);
					Bukkit.getPluginManager().callEvent(e);
					if (!e.isCancelled()) {
						net.helix.pvp.cooldown1.HelixCooldown2.display(player, found);
					}
				} else if (list.isEmpty()) {
					HelixActionBar.send(player, " ");
					net.helix.pvp.cooldown1.HelixCooldown2.map.remove(uuid);
				} else {
					net.helix.pvp.cooldown1.HelixCooldownAPI cooldown = list.get(0);
					if (cooldown instanceof net.helix.pvp.cooldown1.ItemCooldown) {
						net.helix.pvp.cooldown1.ItemCooldown item = (net.helix.pvp.cooldown1.ItemCooldown) cooldown;
						if (item.isSelected()) {
							item.setSelected(false);
							HelixActionBar.send(player, " ");
						}
					}
				}
			}
		}
	}
	

	@EventHandler
	public void onDismount(EntityDismountEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			track.remove(player.getUniqueId());
			cancelMoving(player);
		}
	}
	
	public void cancelMoving(Player player) {
		new BukkitRunnable() {

			@Override
			public void run() {
				moving.remove(player.getUniqueId());
			}			
		}.runTaskLater(HelixPvP.getInstance(), HelixPvP.getInstance().getConfig().getInt("JumperCooldown"));
	}
	
	private void launchEnderPearl(Player bukkitPlayer) {
		if (GladiatorListener.combateGlad.containsKey(bukkitPlayer)) {
			return;
		}
		bukkitPlayer.setFallDistance(0);
		EntityPlayer nmsPlayer = ((CraftPlayer) bukkitPlayer).getHandle();
		JumperEnderPearl customEnder = new JumperEnderPearl(nmsPlayer.getWorld(), nmsPlayer);
		nmsPlayer.getWorld().addEntity(customEnder);
		EnderPearl bukkitEnder = (EnderPearl) customEnder.getBukkitEntity();
		bukkitEnder.setPassenger(bukkitPlayer);
		track.add(bukkitPlayer.getUniqueId());
		moving.add(bukkitPlayer.getUniqueId());
	}

	class JumperEnderPearl extends EntityEnderPearl {

		private EntityPlayer jumper;

		public JumperEnderPearl(World world, EntityPlayer entityliving) {
			super(world, entityliving);
			this.jumper = entityliving;
			shooter = null;
			shooterName = null;
		}

		@Override
		public void t_() {
			super.t_();
			if (dead) {
				getBukkitEntity().eject();
				new BukkitRunnable() {

					@Override
					public void run() {
						
						while (!getWorld().getCubes(jumper, jumper.getBoundingBox()).isEmpty() && jumper.locY < 256.0D) {
							jumper.setPosition(jumper.locX, jumper.locY + 1.0D, jumper.locZ);
						}
					}					
				}.runTaskLater(HelixPvP.getInstance(), 5);
			}
		}
	}
}

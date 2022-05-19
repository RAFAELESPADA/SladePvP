package net.helix.pvp.listener;

import java.util.ArrayList;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.KitManager;




public class Jump implements Listener {
	
	private ArrayList<String> fall = new ArrayList<String>();
	
	public void Atirar(Player p) {
		int y = 8;
		Block block = p.getLocation().getBlock().getRelative(0, -1, 0);
		if (block.getType() == Material.SPONGE) {
			Vector vector = new Vector(0, y, 0);
			p.setVelocity(vector);
			this.fall.remove(p.getName());
			 p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 4.0F, 4.0F);
			p.getPlayer().getWorld().playEffect(p.getPlayer().getLocation(), Effect.MOBSPAWNER_FLAMES, 10);
			this.fall.add(p.getName());
		}
	}
	public void Atirar2(Player p) {
		final Location loc = p.getEyeLocation();
    
        final Vector sponge = p.getLocation().getDirection().multiply(3.8).setY(0.45);
		Block block = p.getLocation().getBlock().getRelative(0, -1, 0);
		if (block.getType() == Material.SLIME_BLOCK) {
			p.setVelocity(sponge);
		    p.playEffect(loc, Effect.MOBSPAWNER_FLAMES, (Object)null);
			this.fall.remove(p.getName());
			 p.playSound(p.getLocation(), Sound.SLIME_WALK, 4.0F, 4.0F);
			this.fall.add(p.getName());
		}
	}
	@EventHandler
	private void Jumps(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Atirar(p);
		Atirar2(p);
	}
	@EventHandler
	public void RemoverDan2o(EntityDamageEvent e) 
	{
	   if (!(e.getEntity() instanceof Player)) {
           return;
       }
	   if (e.getCause() == EntityDamageEvent.DamageCause.FALL && !KitManager.getPlayer(e.getEntity().getName()).hasKit() && this.fall.contains(e.getEntity().getName()) && e.getEntity().getLocation().getY() < 150 && e.getEntity().getLocation().getX() > -1300 && e.getEntity().getLocation().getX() < -1000)  {
		   e.setCancelled(true);
		   HelixKit.findKit("PvP").ifPresent(kit -> {
				kit.send((Player)e.getEntity());
		   });
	   }
	   else if (e.getCause() == EntityDamageEvent.DamageCause.FALL && !KitManager.getPlayer(e.getEntity().getName()).hasKit() && e.getEntity().getLocation().getX() > -1300 && e.getEntity().getLocation().getX() < -1000 && e.getEntity().getLocation().getY() < 150)  {
		   e.setCancelled(true);
		   HelixKit.findKit("PvP").ifPresent(kit -> {
				kit.send((Player)e.getEntity());
		   });
	   }
	   else if (e.getCause() == EntityDamageEvent.DamageCause.FALL && !KitManager.getPlayer(e.getEntity().getName()).hasKit() && e.getEntity().getLocation().getX() > 165100 && e.getEntity().getLocation().getX() < 165200 && e.getEntity().getLocation().getZ() > 651600 && e.getEntity().getLocation().getZ() < 652000)  {
		   e.setCancelled(true);
		   Player p = (Player)e.getEntity();
		   
		   p.getInventory().setItem(0, new ItemBuilder("§fEspada de Diamante", Material.DIAMOND_SWORD)
					.nbt("cancel-drop").addEnchant(Enchantment.DAMAGE_ALL, 1)
					.toStack()
			);

		   p.getInventory().setHelmet(new ItemBuilder("§5§lSLOPER", Material.IRON_HELMET).toStack());
			p.getInventory().setChestplate(new ItemBuilder("§5§lSLOPER", Material.IRON_CHESTPLATE).toStack());
			p.getInventory().setLeggings(new ItemBuilder("§5§lSLOPER", Material.IRON_LEGGINGS).toStack());
			p.getInventory().setBoots(new ItemBuilder("§5§lSLOPER", Material.IRON_BOOTS).toStack());
			
			for (int i = 0; i < 36; i++) {
				p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
			}
			p.getInventory().setItem(13, new ItemStack(Material.BOWL, 64));
			p.getInventory().setItem(14, new ItemStack(Material.RED_MUSHROOM, 64));
			p.getInventory().setItem(15, new ItemStack(Material.BROWN_MUSHROOM, 64));
	   }
	}
	 
	   @EventHandler
		public void RemoverDano(EntityDamageEvent e) 
		{
		   if (!(e.getEntity() instanceof Player)) {
	           return;
	       }
			Player p = (Player) e.getEntity();
			if (e.getCause() == EntityDamageEvent.DamageCause.FALL && this.fall.contains(p.getName())) 
			{
				this.fall.remove(p.getName());
				e.setCancelled(true);

			}
			else if(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK)
			{
				this.fall.remove(p.getName());
			}
		}
		
	}


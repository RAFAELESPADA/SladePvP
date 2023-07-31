package net.helix.pvp.listener;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import net.helix.pvp.HelixPvP;
import net.helix.pvp.evento.SoupTypeGUI;
import net.md_5.bungee.api.ChatColor;

public class EntityCalculateDamageListener implements Listener {

	    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	    public void onDamageWoodSword(final EntityDamageByEntityEvent event) {
	        if (event.isCancelled() || !(event.getDamager() instanceof Player) || !(event.getEntity() instanceof Player)) {
	            return;
	        }
	        final Player p = (Player)event.getDamager();
	        final ItemStack item = ((Player)event.getDamager()).getItemInHand();
	        if (item != null) {
	            if (item.getType().equals((Object)Material.MUSHROOM_SOUP)) {
	                event.setDamage(1.0);
	            }
	            else if (item.getType().equals((Object)Material.WOOD_SWORD)) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() - 3.7) : (event.getDamage() - 2.0));
	            }
	            else if (item.getType().equals((Object)Material.STONE_SWORD)) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() - 4.7) : (event.getDamage() - 2.0));
	            }
	            else if (item.getType().equals((Object)Material.IRON_SWORD)) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() - 4.7) : (event.getDamage() - 1.5));
	            }
	            else if (item.getType().equals((Object)Material.DIAMOND_SWORD)) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() - 6.7) : (event.getDamage() - 2.0));
	            }
	            else if (item.getType().equals((Object)Material.GOLD_SWORD)) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() - 3.7) : (event.getDamage() - 1.0));
	            }
	            else if (item.getType().name().contains("WOOD_AXE")) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() - 3.7) : (event.getDamage() - 2.0));
	            }
	            else if (item.getType().name().contains("STONE_AXE")) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() - 3.7) : (event.getDamage() - 2.0));
	            }
	            else if (item.getType().name().contains("IRON_AXE")) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() - 3.7) : (event.getDamage() - 2.0));
	            }
	            else if (item.getType().name().contains("DIAMOND_AXE")) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() - 3.7) : (event.getDamage() - 2.0));
	            }
	            else if (item.getType().name().contains("GOLD_AXE")) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() - 3.7) : (event.getDamage() - 2.0));
	            }
	            else if (item.getType().name().contains("WOOD_PICKAXE")) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() - 3.7) : (event.getDamage() - 2.0));
	            }
	            else if (item.getType().name().contains("STONE_PICKAXE")) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() - 3.7) : (event.getDamage() - 2.0));
	            }
	            else if (item.getType().name().contains("IRON_PICKAXE")) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() - 3.7) : (event.getDamage() - 2.0));
	            }
	            else if (item.getType().name().contains("DIAMOND_PICKAXE")) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() - 3.7) : (event.getDamage() - 2.0));
	            }
	            else if (item.getType().name().contains("GOLD_PICKAXE")) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() - 3.7) : (event.getDamage() - 2.0));
	            }
	            else if (item.getType().equals((Object)Material.IRON_SPADE)) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() - 4.7) : (event.getDamage() - 2.0));
	            }
	            else if (item.getType().equals((Object)Material.DIAMOND_SPADE)) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() - 4.7) : (event.getDamage() - 2.0));
	            }
	            else if (item.getType().equals((Object)Material.DIAMOND_HOE)) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() - 3.7) : (event.getDamage() - 2.0));
	            }
	            else if (item.getType().equals((Object)Material.IRON_HOE)) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() - 3.7) : (event.getDamage() - 2.0));
	            }
	        }
	        if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
	            for (final PotionEffect effect : p.getActivePotionEffects()) {
	                if (effect.getType().equals((Object)PotionEffectType.INCREASE_DAMAGE)) {
	                    final int level = effect.getAmplifier() + 1;
	                    final double newDamage = event.getDamage(EntityDamageEvent.DamageModifier.BASE) / (level * 1.5 + 1.0) * level;
	                    final double damagePercent = newDamage / event.getDamage(EntityDamageEvent.DamageModifier.BASE);
	                    for (final EntityDamageEvent.DamageModifier dm : new EntityDamageEvent.DamageModifier[] { EntityDamageEvent.DamageModifier.ARMOR, EntityDamageEvent.DamageModifier.MAGIC, EntityDamageEvent.DamageModifier.RESISTANCE, EntityDamageEvent.DamageModifier.BLOCKING }) {
	                        if (event.isApplicable(dm)) {
	                            event.setDamage(dm, event.getDamage(dm) * damagePercent);
	                        }
	                    }
	                    event.setDamage(EntityDamageEvent.DamageModifier.BASE, newDamage);
	                }
	            }
	        }
	    }
	    
	    public static boolean isCritical(final LivingEntity e) {
	        return !e.isOnGround() && e.getFallDistance() > 0.0;
	    }
	    

	    
	


	@EventHandler(priority = EventPriority.NORMAL)
	public void banAds(PlayerChatEvent e) {
		Player p = e.getPlayer();
		if (!p.hasPermission("kombo.cmd.report")
				&& (e.getMessage().contains(".net")
				|| e.getMessage().contains("Entre agora")
				|| e.getMessage().contains("meu server")
				|| e.getMessage().contains("(.)")
				|| e.getMessage().contains(".host")
				|| e.getMessage().contains(".minesv")
				|| e.getMessage().contains(".playbr")
				|| e.getMessage().contains(".cookiehosting")
				|| e.getMessage().contains(".playmine")
				|| e.getMessage().contains(".com")
				|| e.getMessage().contains(".server")
				|| e.getMessage().contains(".me")
				|| e.getMessage().contains(".io")
				|| e.getMessage().contains(".com")
				|| e.getMessage().contains(".com.br")
				|| e.getMessage().contains(".pro")

				|| e.getMessage().contains(".nu")
				|| e.getMessage().contains(".tk")
				|| e.getMessage().contains("(,)")
				|| e.getMessage().contains(",net")
				|| e.getMessage().contains(",host")
				|| e.getMessage().contains(",minesv")
				|| e.getMessage().contains(",playbr")
				|| e.getMessage().contains(",cookiehosting")
				|| e.getMessage().contains(",playmine")
				|| e.getMessage().contains(",com")
				|| e.getMessage().contains(",server")
				|| e.getMessage().contains(",me")
				|| e.getMessage().contains(",io")
				| e.getMessage().contains(",com")
				|| e.getMessage().contains(",com.br")
				|| e.getMessage().contains(",pro")
				|| e.getMessage().contains(",nu")
				|| e.getMessage().contains(",tk"))) {
			if (e.getMessage().contains("youtube") || e.getMessage().contains("twitch") || e.getMessage().contains("sladepvp.com")) {
				return;
			}
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "Divulgação de servidores/links é proibida! ");
		}
		
	}


	  @EventHandler
	  public void achivments(PlayerAchievementAwardedEvent e) {
	    e.setCancelled(true);
	  }
	@EventHandler
	public void onEspada(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getDamager();
		Player player = (Player) e.getDamager();
		Player player2 = (Player) e.getEntity();
		if (p.getItemInHand().getType() == Material.STONE_SWORD || p.getItemInHand().getType() == Material.WOOD_SWORD || p.getItemInHand().getType() == Material.IRON_SWORD) {
			p.getItemInHand().setDurability((short)0);
			p.updateInventory();
		}
		if (e.isCancelled()) {
			return;
		}
          if (SoupTypeGUI.blood.containsKey(player.getName())) {
        	  player2.getWorld().playEffect(player2.getLocation(),  Effect.STEP_SOUND, Material.REDSTONE_BLOCK, 15);
          }
		if (p.getMaximumNoDamageTicks() > 35 || p.getMaximumNoDamageTicks() < 18) {
			p.setMaximumNoDamageTicks(20);
		}
          }


	 


	@EventHandler
	public void otnShot(EntityDamageByEntityEvent e) {
		
			
			if (e.getEntity() instanceof Player && e.getDamager() instanceof Arrow) {
				
				Player damagedPlayer = (Player) e.getEntity();
				Arrow arrow = (Arrow) e.getDamager();
				
				if (arrow.getShooter() != null && arrow.getShooter() instanceof Player) {
					
					Player shooter = (Player) arrow.getShooter();
					
					// ARROW HEALTH MESSAGE
					
					if (damagedPlayer.getName() != shooter.getName()) {
						
						new BukkitRunnable() {
							
							@Override
							public void run() {
								
								double health = Math.round(damagedPlayer.getHealth() * 10.0) / 10.0;
								
									if (health != 20.0) {	
										
										shooter.sendMessage(ChatColor.AQUA + damagedPlayer.getName() + ChatColor.YELLOW + " está com " + health + " de vida!");									
									}						
																}							
							}
							
						.runTaskLater(HelixPvP.getInstance(), 2L);
						
					}
				}
			}
		}
	
	
	
	
	 @EventHandler
	    public void Quebrar(final PlayerMoveEvent e) {
	        final Player p = e.getPlayer();
	        final ItemStack item1 = p.getItemInHand();
	        final Material material1 = Material.getMaterial(item1.getTypeId());
	        if (!material1.isBlock() && (!(material1 == Material.LEATHER_LEGGINGS)) && (!(material1 == Material.LEATHER_HELMET)) && (!(material1 == Material.IRON_HELMET)) && (!(material1 == Material.IRON_CHESTPLATE)) && (!(material1 == Material.IRON_BOOTS)) && (!(material1 == Material.IRON_LEGGINGS)) && (!(material1 == Material.LEATHER_BOOTS)) && (!(material1 == Material.GOLD_CHESTPLATE	        	)) && (!(material1 == Material.GOLD_HELMET)) && (!(material1 == Material.CHAINMAIL_CHESTPLATE)) && (!(material1 == Material.CHAINMAIL_HELMET)) && (!(material1 == Material.CHAINMAIL_BOOTS)) && (!(material1 == Material.CHAINMAIL_LEGGINGS)) && material1.getMaxDurability() >= 1 && item1.getDurability() != 0) {
	            p.getItemInHand().setDurability((short)0);
	            p.updateInventory();
	        }
	        
	        }
	 }	    

                            
                        
                

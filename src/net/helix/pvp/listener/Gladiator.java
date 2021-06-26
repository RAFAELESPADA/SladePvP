package net.helix.pvp.listener;

import org.bukkit.entity.*;
import org.bukkit.block.*;
import org.bukkit.potion.*;
import net.helix.pvp.HelixPvP;
import java.util.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.player.*;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.event.entity.*;

public class Gladiator implements Listener {
    public static ArrayList<String> gladgladiator;
    public boolean generateGLASS;
    public HashMap<String, Location> oldl;
    public static HashMap<String, String> lutando;
    public HashMap<UUID, List<Location>> blocks;
    public HashMap<Player, Location> localizacao;
    public HashMap<Location, Block> bloco;
    public HashMap<Integer, String[]> players;
    public HashMap<String, Integer> tasks;
    int nextID;
    public int id1;
    public int id2;


    static {
        Gladiator.gladgladiator = new ArrayList<String>();
        Gladiator.lutando = new HashMap<String, String>();
    }

    public Gladiator() {
        this.generateGLASS = true;
        this.oldl = new HashMap<String, Location>();
        this.blocks = new HashMap<>();
        this.localizacao = new HashMap<Player, Location>();
        this.bloco = new HashMap<Location, Block>();
        this.players = new HashMap<Integer, String[]>();
        this.tasks = new HashMap<String, Integer>();
        this.nextID = 0;
    }

    @EventHandler
    public void aoComando(final PlayerCommandPreprocessEvent e) {
        final Player p = e.getPlayer();
        if (Gladiator.lutando.containsKey(p.getName()) && e.getMessage().startsWith("/")) {
            e.setCancelled(true);
            p.sendMessage("§cVoc\u00ea n\u00e3o pode utilizar comando quanto estiver lutando");
        }
    }

    @EventHandler
    public void OnGladiatorKit(final PlayerInteractEntityEvent event) {
        final Player p = event.getPlayer();
        if (!(event.getRightClicked() instanceof Player)) {
            return;
        }
        final Player r = (Player) event.getRightClicked();
        if (p.getItemInHand().getType() != Material.IRON_FENCE || net.helix.pvp.kit.All.kitName.get(p.getName()) != "Gladiator") {
            return;
        }
        if (lutando.containsKey(p.getName()) || lutando.containsKey(r.getName())) {
            return;
        }
        int y = 60 + new Random().nextInt(120);
        final Location loc = new Location(p.getWorld(), (double) p.getLocation().getBlockX(), p.getLocation().getBlockY() + y, (double) p.getLocation().getBlockZ());
        this.localizacao.put(p, loc);
        this.localizacao.put(r, loc);
        final Location loc2 = new Location(p.getWorld(), p.getLocation().getBlockX() - 5, p.getLocation().getBlockY() + y, p.getLocation().getBlockZ() - 5);
        final Location loc3 = new Location(p.getWorld(), p.getLocation().getBlockX() + 6, p.getLocation().getBlockY() + y, p.getLocation().getBlockZ() + 6);
        final Integer currentID = this.nextID;
        ++this.nextID;
        final ArrayList<String> list = new ArrayList<String>();
        list.add(p.getName());
        list.add(r.getName());
        this.players.put(currentID, list.toArray(new String[1]));
        this.oldl.put(p.getName(), p.getLocation());
        this.oldl.put(r.getName(), r.getLocation());
        if (this.generateGLASS) {
            final List<Location> cuboid = new ArrayList<Location>();
            cuboid.clear();
            for (int bX = -8; bX <= 8; ++bX) {
                for (int bZ = -8; bZ <= 8; ++bZ) {
                    for (int bY = -1; bY <= 4; ++bY) {
                        final Block b = loc.clone().add(bX, bY, bZ).getBlock();
                        if (!b.isEmpty()) {
                            event.setCancelled(true);
                            p.sendMessage("§cVoce nao pode criar sua arena aqui");
                            return;
                        }
                        if (bY == 4) {
                            cuboid.add(loc.clone().add(bX, bY, bZ));
                        } else if (bY == -1) {
                            cuboid.add(loc.clone().add(bX, bY, bZ));
                        } else if (bX == -8 || bZ == -8 || bX == 8 || bZ == 8) {
                            cuboid.add(loc.clone().add(bX, bY, bZ));
                        }
                    }
                }
            }
            blocks.put(p.getUniqueId(), new ArrayList<>());
            blocks.get(p.getUniqueId()).addAll(cuboid);
            blocks.put(r.getUniqueId(), new ArrayList<>());
            blocks.get(r.getUniqueId()).addAll(cuboid);
            for (final Location loc4 : cuboid) {
                loc4.getBlock().setType(Material.GLASS);
                this.bloco.put(loc4, loc4.getBlock());
            }
            loc2.setYaw(-75.0f);
            p.teleport(loc2);
            loc3.setYaw(75.0f);
            r.teleport(loc3);
            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 110, 5));
            r.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 110, 5));
            p.sendMessage("§7Voce desafiou um jogador! Voce tem 5 segundos de invencibilidade!");
            p.sendMessage("§7Caso nao tenha nenhum vencedor depois de 4 minutos, voce voltara ao seu local de origem!");
            r.sendMessage("§7Voce foi desafiado! Voce tem 5 segundos de invencibilidade!");
            r.sendMessage("§7 Caso nao tenha nenhum vencedor depois de 4 minutos, voce voltara ao seu local de origem!");
            Gladiator.lutando.put(p.getName(), r.getName());
            Gladiator.lutando.put(r.getName(), p.getName());
            Gladiator.gladgladiator.add(p.getName());
            Gladiator.gladgladiator.add(r.getName());
            this.id2 = Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
                @Override
                public void run() {
                    if (Gladiator.lutando.containsKey(p.getName()) && Gladiator.lutando.get(p.getName()).equalsIgnoreCase(r.getName()) && Gladiator.lutando.containsKey(r.getName()) && Gladiator.lutando.get(r.getName()).equalsIgnoreCase(p.getName())) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 2000000, 3));
                        r.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 2000000, 3));
                    }
                }
            }, 2400L);
            this.id1 = Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
                @Override
                public void run() {
                    if (Gladiator.lutando.containsKey(p.getName()) && Gladiator.lutando.get(p.getName()).equalsIgnoreCase(r.getName()) && Gladiator.lutando.containsKey(r.getName()) && Gladiator.lutando.get(r.getName()).equalsIgnoreCase(p.getName())) {
                        removeGlad(p);
                        removeGlad(r);
                        p.sendMessage("§7O tempo do gladiator acabou e você voltou ao lugar inicial!");
                        r.sendMessage("§7O tempo do gladiator acabou e você voltou ao lugar inicial!");
                    }
                }
            }, 4800L);
        }
    }
    
    @EventHandler
    public void onTeleportENDER(PlayerTeleportEvent e) {
    	Player p = e.getPlayer();
    	if (e.getCause() == TeleportCause.ENDER_PEARL && Gladiator.lutando.containsKey(p.getName())) {
    		p.sendMessage("§7Voce nao pode usar enderpearl no gladiator!");
    		e.setCancelled(true);
    	}   	
    }

    @EventHandler
    public void onPlayerInteractGlad(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (p.getItemInHand().getType() == Material.IRON_FENCE && net.helix.pvp.kit.All.kitName.get(p.getName()) == "Gladiator") {
            e.setCancelled(true);
            p.updateInventory();
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlyaerInteract(final PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.GLASS && e.getPlayer().getGameMode() != GameMode.CREATIVE && Gladiator.lutando.containsKey(e.getPlayer().getName())) {
            e.setCancelled(true);
            e.getClickedBlock().setType(Material.BEDROCK);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
                @Override
                public void run() {
                    if (Gladiator.lutando.containsKey(e.getPlayer().getName())) {
                        e.getClickedBlock().setType(Material.GLASS);
                    }
                }
            }, 30L);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(final BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.GLASS && e.getPlayer().getGameMode() != GameMode.CREATIVE && Gladiator.lutando.containsKey(e.getPlayer().getName())) {
            e.setCancelled(true);
            e.getBlock().setType(Material.BEDROCK);
            Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance(), new Runnable() {
                @Override
                public void run() {
                    if (e.getPlayer().getGameMode() != GameMode.CREATIVE && Gladiator.lutando.containsKey(e.getPlayer().getName())) {
                        e.getBlock().setType(Material.GLASS);
                    }
                }
            }, 30L);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLeftt(final PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        removeGlad(p);
        if (Gladiator.lutando.containsKey(e.getPlayer().getName())) {
        	p.setHealth(0.0);
        	final Player t = Bukkit.getServer().getPlayer((String)Gladiator.lutando.get(p.getName()));
        	t.sendMessage("§7Seu oponente deslogou no gladiator");
        }
    }
    

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeathGladiator(final PlayerDeathEvent e) {
        removeGlad(e.getEntity());
    }

    public void removeGlad(Player player) {
        if (blocks.containsKey(player.getUniqueId())) {
            for (Location location : blocks.get(player.getUniqueId())) {
                location.getBlock().setType(Material.AIR);
            }
        }
        gladgladiator.remove(player.getName());
        blocks.remove(player.getUniqueId());
        localizacao.remove(player);
        player.removePotionEffect(PotionEffectType.WITHER);
        if (Gladiator.lutando.containsKey(player.getName())) {
            final Player t = Bukkit.getServer().getPlayer((String)Gladiator.lutando.get(player.getName()));
            if (oldl.containsKey(t.getName())) {
                t.teleport(oldl.get(t.getName()));
            }
            if (oldl.containsKey(player.getName())) {
                player.teleport(oldl.get(player.getName()));
            }
            t.removePotionEffect(PotionEffectType.WITHER);
            Gladiator.lutando.remove(t.getName());
            Gladiator.lutando.remove(player.getName());
            gladgladiator.remove(t.getName());
            blocks.remove(t.getUniqueId());
            localizacao.remove(t);
            oldl.remove(t.getName());
        }
        oldl.remove(player.getName());
    }
}


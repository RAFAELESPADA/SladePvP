package net.helix.pvp;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.helix.pvp.HelixPvP;

public class Feast extends BukkitRunnable {

    public Feast(JavaPlugin plugin) {
        runTaskTimer(plugin, 0, 20L);
    }

    // Spawn a cada X minutos
    private final int spawnEveryMinutes = HelixPvP.getInstance().getConfig().getInt("TempoFeast");

    private boolean spawned = false;
    private final World world = Bukkit.getWorld(HelixPvP.getInstance().getConfig().getString("FeastWorld"));

    private final int chestItemMinAmount = 2;
    private final int chestItemMaxAmount = 7;

    private final List<Location> chests = Arrays.asList(
            new Location(world, HelixPvP.getInstance().getConfig().getInt("Bau1X"), HelixPvP.getInstance().getConfig().getInt("Bau1Y"), HelixPvP.getInstance().getConfig().getInt("Bau1Z")),
            new Location(world, HelixPvP.getInstance().getConfig().getInt("Bau2X"), HelixPvP.getInstance().getConfig().getInt("Bau2Y"), HelixPvP.getInstance().getConfig().getInt("Bau2Z")),
            new Location(world, HelixPvP.getInstance().getConfig().getInt("Bau3X"), HelixPvP.getInstance().getConfig().getInt("Bau3Y"), HelixPvP.getInstance().getConfig().getInt("Bau3Z")),
            new Location(world, HelixPvP.getInstance().getConfig().getInt("Bau4X"), HelixPvP.getInstance().getConfig().getInt("Bau4Y"), HelixPvP.getInstance().getConfig().getInt("Bau4Z")),
            new Location(world,  HelixPvP.getInstance().getConfig().getInt("Bau5X"), HelixPvP.getInstance().getConfig().getInt("Bau5Y"), HelixPvP.getInstance().getConfig().getInt("Bau5Z")),
            new Location(world, HelixPvP.getInstance().getConfig().getInt("Bau6X"), HelixPvP.getInstance().getConfig().getInt("Bau6Y"), HelixPvP.getInstance().getConfig().getInt("Bau6Z")),
            new Location(world, HelixPvP.getInstance().getConfig().getInt("Bau7X"), HelixPvP.getInstance().getConfig().getInt("Bau7Y"), HelixPvP.getInstance().getConfig().getInt("Bau7Z")),
            new Location(world, HelixPvP.getInstance().getConfig().getInt("Bau8X"), HelixPvP.getInstance().getConfig().getInt("Bau8Y"), HelixPvP.getInstance().getConfig().getInt("Bau8Z")),
            new Location(world, HelixPvP.getInstance().getConfig().getInt("Bau9X"), HelixPvP.getInstance().getConfig().getInt("Bau9Y"), HelixPvP.getInstance().getConfig().getInt("Bau9Z")),
            new Location(world, HelixPvP.getInstance().getConfig().getInt("Bau10X"), HelixPvP.getInstance().getConfig().getInt("Bau10Y"), HelixPvP.getInstance().getConfig().getInt("Bau10Z")),
            new Location(world,  HelixPvP.getInstance().getConfig().getInt("Bau11X"), HelixPvP.getInstance().getConfig().getInt("Bau11Y"), HelixPvP.getInstance().getConfig().getInt("Bau11Z")),
            new Location(world, HelixPvP.getInstance().getConfig().getInt("Bau12X"), HelixPvP.getInstance().getConfig().getInt("Bau12Y"), HelixPvP.getInstance().getConfig().getInt("Bau12Z")),
            new Location(world, HelixPvP.getInstance().getConfig().getInt("Bau13X"), HelixPvP.getInstance().getConfig().getInt("Bau13Y"), HelixPvP.getInstance().getConfig().getInt("Bau13Z"))
        //-1210 146 212
    );

    private final List<ItemStack> items = Arrays.asList(
    		

    		new ItemStack(Material.GOLD_HELMET),
    		new ItemStack(Material.MUSHROOM_SOUP),
    		new ItemStack(Material.CHAINMAIL_CHESTPLATE),
    		new ItemStack(Material.GOLDEN_APPLE),
    		new ItemStack(Material.ARROW , 16),
    		new ItemStack(Material.POTION, 1, (short)16418),
            new ItemStack(Material.POTION, 1, (short)16387),
        	new ItemStack(Material.POTION, 1, (short)16418),
            new ItemStack(Material.POTION, 1, (short)16387),
            new ItemStack(Material.POTION, 1, (short)16456),
    		new ItemStack(Material.LEATHER_HELMET),
    		new ItemStack(Material.LEATHER_LEGGINGS),
    		new ItemStack(Material.POTION, 1 , (short)16388),
    		new ItemStack(Material.ENDER_PEARL),
    		new ItemStack(Material.EXP_BOTTLE),
    		new ItemStack(Material.IRON_AXE),
    		new ItemStack(Material.LEATHER_HELMET),
    		new ItemStack(Material.EXP_BOTTLE),
    		new ItemStack(Material.ENDER_PEARL),
    		new ItemStack(Material.EXP_BOTTLE),
    		new ItemStack(Material.EXP_BOTTLE),
    		new ItemStack(Material.ENDER_PEARL),
    		new ItemStack(Material.EXP_BOTTLE),
    		new ItemStack(Material.EXP_BOTTLE),
    		new ItemStack(Material.ENDER_PEARL),
    		new ItemStack(Material.EXP_BOTTLE),
    		new ItemStack(Material.EXP_BOTTLE),
    		new ItemStack(Material.ENDER_PEARL),
    		new ItemStack(Material.EXP_BOTTLE),
    		new ItemStack(Material.EXP_BOTTLE),
    		new ItemStack(Material.ENDER_PEARL),
    		new ItemStack(Material.ENDER_PEARL),
    		new ItemStack(Material.IRON_BOOTS),
    		new ItemStack(Material.IRON_HELMET),
    		new ItemStack(Material.ARROW , 10),
    		new ItemStack(Material.IRON_AXE),
    		new ItemStack(Material.ARROW , 10),

    		new ItemStack(Material.BOW),
    		new ItemStack(Material.BOW),
    		new ItemStack(Material.ENDER_PEARL),
    		new ItemStack(Material.MUSHROOM_SOUP),
    		new ItemStack(Material.MUSHROOM_SOUP),
    		new ItemStack(Material.MUSHROOM_SOUP),
    		new ItemStack(Material.MUSHROOM_SOUP),
    		new ItemStack(Material.MUSHROOM_SOUP),
    		new ItemStack(Material.MUSHROOM_SOUP),
    		new ItemStack(Material.MUSHROOM_SOUP),
    		new ItemStack(Material.MUSHROOM_SOUP),
    		new ItemStack(Material.MUSHROOM_SOUP),new ItemStack(Material.MUSHROOM_SOUP),
    		new ItemStack(Material.MUSHROOM_SOUP),
    		new ItemStack(Material.MUSHROOM_SOUP),
    		new ItemStack(Material.GOLDEN_APPLE, 1),
    		new ItemStack(Material.POTION, 1 , (short)373),   
    		new ItemStack(Material.POTION, 1 , (short)373),    
    		new ItemStack(Material.POTION, 1 , (short)373)    

    );

    private Long time = TimeUnit.MINUTES.toSeconds(spawnEveryMinutes);
    @Override
    public void run() {
        if (Bukkit.getOnlinePlayers().isEmpty()) return;

        time--;
        long minutes = (time / 60) * 2;
        long seconds = (time % 60);
        

        if (!spawned && minutes == 0) {
            if (seconds == 50 || seconds == 40 || seconds == 30 || seconds == 20 || seconds == 10 || seconds == 5 || seconds == 4 || seconds == 3 || seconds == 2 || seconds == 1) {
                Bukkit.broadcastMessage(ChatColor.RED + "Feast nascerá em " + seconds + " " + ((seconds > 1) ? "segundos" : "segundo"));
            }      

            if (seconds <= 0) {
            	
                Bukkit.broadcastMessage(ChatColor.GREEN +"Feast nasceu!");
                // Durado feast (em segundos)
                int spawnDurationSeconds = 20;
                world.strikeLightning(new Location(world, HelixPvP.getInstance().getConfig().getInt("RaioX"), HelixPvP.getInstance().getConfig().getInt("RaioY"), HelixPvP.getInstance().getConfig().getInt("RaioZ")));
                time = (long) spawnDurationSeconds;
                spawned = true;

                chests.forEach((chest) -> {
                   
                    chest.getBlock().setType(Material.CHEST);
                    chest.getBlock().setMetadata("PlacedBlock", new FixedMetadataValue(HelixPvP.getInstance(), true));
                    Random random = new Random();
                   
                    Inventory inventory = ((Chest)chest.getBlock().getState()).getInventory();
                    int itemsAmount = random.nextInt(chestItemMaxAmount + 1 - chestItemMinAmount) + chestItemMaxAmount;
                    for (int i = 0; i < itemsAmount; i++) {
                        int slot = random.nextInt(inventory.getSize());

                        int randomItemIndex = random.nextInt(items.size());
                        ItemStack randomItem = items.get(randomItemIndex);
                        inventory.setItem(slot, randomItem);
                    }
                    }   );
                }
                return;
            }
        if (spawned && seconds <= 0) {
            time = TimeUnit.MINUTES.toSeconds(spawnEveryMinutes);

            chests.stream().map(Location::getBlock).forEach(chest -> {
                chest.setType(Material.AIR);
            });

            spawned = false;
        }
    
       
}
}
    
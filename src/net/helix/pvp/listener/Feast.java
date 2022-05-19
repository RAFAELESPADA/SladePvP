package net.helix.pvp.listener;

import net.helix.core.bukkit.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Feast extends BukkitRunnable {

    public Feast(JavaPlugin plugin) {
        runTaskTimer(plugin, 0, 20L);
    }

    // Spawn a cada X minutos
    private final int spawnEveryMinutes = 8;

    private boolean spawned = false;
    private final World world = Bukkit.getWorlds().get(0);

    private final int chestItemMinAmount = 2;
    private final int chestItemMaxAmount = 3;

    private final List<Location> chests = Arrays.asList(
            new Location(world, 0.0, 0.0, 0.0),
            new Location(world, 1.0, 0.0, 0.0)
    );

    private final List<ItemStack> items = Arrays.asList(
            new ItemBuilder("ï¿½bEspada de ferrp!", Material.IRON_FENCE)
                    .nbt("item", "glad")
                    .nbt("cancel-drop")
                    .toStack(),
            new ItemBuilder("ï¿½bPuxar!", Material.IRON_FENCE)
                    .nbt("item", "glad")
                    .nbt("cancel-drop")
                    .toStack(),new ItemBuilder("ï¿½bPuxar!", Material.IRON_FENCE)
                    .nbt("item", "glad")
                    .nbt("cancel-drop")
                    .toStack(),new ItemBuilder("ï¿½bPuxar!", Material.IRON_FENCE)
                    .nbt("item", "glad")
                    .nbt("cancel-drop")
                    .toStack(),new ItemBuilder("ï¿½bPuxar!", Material.IRON_FENCE)
                    .nbt("item", "feast")
                    .toStack(),new ItemBuilder("ï¿½bPuxar!", Material.IRON_FENCE)
                    .nbt("item", "feast")

                    .toStack(),new ItemBuilder("ï¿½bPuxar!", Material.IRON_FENCE)
                    .nbt("item", "feast")

                    .toStack(),new ItemBuilder("ï¿½bPuxar!", Material.IRON_FENCE)
                    .nbt("item", "feast")

                    .toStack(),new ItemBuilder("ï¿½bPuxar!", Material.IRON_FENCE)
                    .nbt("item", "feast")

                    .toStack(),new ItemBuilder("ï¿½bPuxar!", Material.IRON_FENCE)
                    .nbt("item", "feat")

                    .toStack(),new ItemBuilder("ï¿½bPuxar!", Material.IRON_FENCE)
                    .nbt("item", "feast")
                    .toStack()

    );

    private Long time = TimeUnit.MINUTES.toSeconds(spawnEveryMinutes);

    @Override
    public void run() {
        if (Bukkit.getOnlinePlayers().isEmpty()) return;

        time--;
        long minutes = time / 60;
        long seconds = time % 60;

        if (!spawned && minutes == 0) {
            if (seconds == 50 || seconds == 30 || seconds == 20 || seconds == 10 || seconds == 3) {
                Bukkit.broadcastMessage("§c§lFEAST: §fO feast irÃ¡ spawn em " + seconds + " " + ((seconds > 1) ? "segundos" : "segundo"));
            }

            if (seconds <= 0) {
                Bukkit.broadcastMessage("§a§lO FEAST SPAWNOU!");
                // DuraÃ§Ã£o do feast (em segundos)
                int spawnDurationSeconds = 18;
                time = (long) spawnDurationSeconds;
                spawned = true;

                chests.forEach((chest) -> {
                    chest.getBlock().setType(Material.CHEST);
                    Random random = new Random();

                    Inventory inventory = ((Chest)chest.getBlock().getState()).getInventory();
                    int itemsAmount = random.nextInt(chestItemMaxAmount + 1 - chestItemMinAmount) + chestItemMaxAmount;

                    for (int i = 0; i < itemsAmount; i++) {
                        int slot = random.nextInt(inventory.getSize());

                        int randomItemIndex = random.nextInt(items.size());
                        ItemStack randomItem = items.get(randomItemIndex);
                        inventory.setItem(slot, randomItem);
                    }
                });

                return;
            }
        }
        if (spawned && seconds <= 0) {
            time = TimeUnit.MINUTES.toSeconds(spawnEveryMinutes);

            chests.stream().map(Location::getBlock).forEach(chest -> {
                ((Chest) chest.getState()).getInventory().clear();
                chest.setType(Material.AIR);
            });

            spawned = false;
        }
    }
}
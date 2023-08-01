package net.helix.pvp.warp.provider;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.api.HelixTitle;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.core.bukkit.warp.HelixWarp;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.evento.EventoUtils;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.helix.pvp.listener.Jump;
import net.helix.pvp.listener.PlayerDeathListener;
import net.helix.pvp.warp.WarpHandle;

public class Spawn extends WarpHandle {
  public void execute(Player player) {
    Location spawnLocation = HelixBukkit.getInstance().getWarpManager().findWarp("spawn").isPresent() ? ((HelixWarp)HelixBukkit.getInstance().getWarpManager().findWarp("spawn").get()).getLocation() : player.getWorld().getSpawnLocation();
    player.teleport(spawnLocation);
    player.getInventory().clear();
    Jump.recebeu.remove(player.getName());
    Jump.caiu.remove(player.getName());
    player.getInventory().setArmorContents(null);
    player.setGameMode(GameMode.ADVENTURE);
    player.setMaxHealth(20.0D);
    if (EventoUtils.game.contains(player.getName())) {
      EventoUtils.setEvento(false, player); 
    }
    player.setHealth(player.getMaxHealth());
    player.getActivePotionEffects().forEach(potion -> player.removePotionEffect(potion.getType()));
    player.setFireTicks(0);
    player.setFoodLevel(20);
    player.getInventory().setHeldItemSlot(0);
    player.getInventory().setItem(0, (new ItemBuilder("§aKits primários", Material.valueOf(HelixPvP.getInstance().getConfig().getString("KitsItem"))))
        .nbt("spawn-item", "kits")
        .nbt("cancel-drop")
        .nbt("cancel-click")
        .toStack());
    player.getInventory().setItem(1, (new ItemBuilder("§aKits secundários", Material.CHEST))
            .nbt("spawn-item", "kits2")
            .nbt("cancel-drop")
            .nbt("cancel-click")
            .toStack());
    player.getInventory().setItem(2, (new ItemBuilder("§eLoja", Material.valueOf(HelixPvP.getInstance().getConfig().getString("ShopItemMAT"))))
        .nbt("spawn-item", "shop")
        .nbt("cancel-drop")
        .nbt("cancel-click")
        .toStack());
    player.getInventory().setItem(6, (new ItemBuilder("§6Opções", Material.valueOf(HelixPvP.getInstance().getConfig().getString("OptionsItem"))))
            .nbt("spawn-item", "status")
            .nbt("cancel-drop")
            .nbt("cancel-click")
            .toStack());
    player.getInventory().setItem(7, (new ItemBuilder("§eStatus", Material.SKULL_ITEM)
            .nbt("spawn-item", "status")
            .nbt("cancel-drop")
            .nbt("cancel-click")
            .toStack()));
    player.getInventory().setItem(8, (new ItemBuilder("§eWarps", Material.COMPASS))
            .nbt("spawn-item", "1v1")
            .nbt("cancel-drop")
            .nbt("cancel-click")
            .toStack());
    if (PlayerDeathListener.lastKit.containsKey(player.getName())) {
    	KitManager.getPlayer(player.getName()).setKit(PlayerDeathListener.lastKit.get(player.getName()));
    	KitManager2.getPlayer(player.getName()).setkit2(PlayerDeathListener.lastKit2.get(player.getName()));
    	player.sendMessage("§aSeus kits anteriores foram re-equipados.");
    }
  }
  public static ItemStack getHead(Player player) {
      ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
      SkullMeta skull = (SkullMeta) item.getItemMeta();
      skull.setDisplayName("§aSeu perfil");
      ArrayList<String> lore = new ArrayList<String>();
      lore.add("§7Clique para ver seu perfil");
      skull.setLore(lore);
      skull.setOwner(player.getName());
      item.setItemMeta(skull);
      return item;
  }
}

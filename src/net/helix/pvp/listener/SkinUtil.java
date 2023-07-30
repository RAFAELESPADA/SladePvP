package net.helix.pvp.listener;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;

import net.helix.pvp.HelixPvP;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PacketPlayOutRespawn;
import net.minecraft.server.v1_8_R3.WorldSettings;
import net.minecraft.server.v1_8_R3.WorldSettings.EnumGamemode;

public class SkinUtil {
 
    private static Player player;
    private Collection<PotionEffect> effects;
    private Location location;
    private int slot;
 
    public SkinUtil(Player player) {
        this.player = player;
       
    }

public static void changeSkin(String data, String signature) {
    EntityPlayer ePlayer = ((CraftPlayer) player).getHandle();
    GameProfile profile = ePlayer.getProfile();
    PropertyMap pMap = profile.getProperties();
    Property property = pMap.get("textures").iterator().next();
    pMap.remove("textures", property);
    pMap.put("textures", new Property("textures", data, signature));
}
public EnumGamemode getGamemode() {
    switch (player.getGameMode()) {
    case SURVIVAL:
        return EnumGamemode.SURVIVAL;
    case CREATIVE:
        return EnumGamemode.CREATIVE;
    case SPECTATOR:
        return EnumGamemode.SPECTATOR;
    default:
        return EnumGamemode.ADVENTURE;
    }
}
public void updateSkin() {
	   effects = player.getActivePotionEffects();
	   location = player.getLocation();
	   slot = player.getInventory().getHeldItemSlot();
	   CraftWorld world = (CraftWorld) location.getWorld();
	   CraftPlayer craftPlayer = ((CraftPlayer) player);
	   CraftPlayer cp = ((CraftPlayer) player);
	   EntityPlayer entityPlayer = craftPlayer.getHandle();
	   entityPlayer.playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, craftPlayer.getHandle()));
	   entityPlayer.playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, craftPlayer.getHandle()));
	   PacketPlayOutRespawn respawn = new PacketPlayOutRespawn(cp.getHandle().getWorld().worldProvider.getDimension(),
               cp.getHandle().getWorld().getDifficulty(), cp.getHandle().getWorld().worldData.getType(),
               WorldSettings.EnumGamemode.getById(cp.getGameMode().getValue()));
	   entityPlayer.playerConnection.sendPacket(respawn);
	   player.teleport(location);
	   for (Player player : Bukkit.getOnlinePlayers()) {
           player.hidePlayer(this.player);
           player.showPlayer(this.player);
}
	   Bukkit.getScheduler().runTaskLater(HelixPvP.getInstance(), new Runnable() {public void run() {
           player.getInventory().setHeldItemSlot(slot);
           player.addPotionEffects(effects);
           player.setExp(player.getExp());
           player.setHealth(player.getHealth()-0.0001);
           player.openInventory(player.getEnderChest());
           player.closeInventory();
       }}, 2);
}
}
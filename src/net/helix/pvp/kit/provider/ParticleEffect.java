package net.helix.pvp.kit.provider;



import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public enum ParticleEffect {
  HUGE_EXPLOSION("hugeexplosion"),
  LARGE_EXPLODE("largeexplode"),
  FIREWORKS_SPARK("fireworksSpark"),
  BUBBLE(
    "bubble"),
  SUSPEND("suspend"),
  DEPTH_SUSPEND("depthSuspend"),
  TOWN_AURA("townaura"),
  CRIT(
    "crit"),
  MAGIC_CRIT("magicCrit"),
  SMOKE("smoke"),
  MOB_SPELL("mobSpell"),
  MOB_SPELL_AMBIENT(
    "mobSpellAmbient"),
  SPELL("spell"),
  INSTANT_SPELL(
    "instantSpell"),
  WITCH_MAGIC("witchMagic"),
  NOTE("note"),
  PORTAL(
    "portal"),
  ENCHANTMENT_TABLE("enchantmenttable"),
  EXPLODE("explode"),
  FLAME(
    "flame"),
  LAVA("lava"),
  FOOTSTEP("footstep"),
  SPLASH(
    "splash"),
  WAKE("wake"),
  LARGE_SMOKE("largesmoke"),
  CLOUD(
    "cloud"),
  RED_DUST("reddust"),
  SNOWBALL_POOF(
    "snowballpoof"),
  DRIP_WATER(
    "dripWater"),
  DRIP_LAVA(
    "dripLava"),
  SNOW_SHOVEL(
    "snowshovel"),
  SLIME(
    "slime"),
  HEART(
    "heart"),
  ANGRY_VILLAGER(
    "angryVillager"),
  HAPPY_VILLAGER(
    "happyVillager");
  
  private static final double MAX_RANGE = 16.0D;
  
  private static Constructor<?> packetPlayOutWorldParticles;
  
  private static Method getHandle;
  
  private static Field playerConnection;
  
  private static Method sendPacket;
  
  private static final Map<String, ParticleEffect> NAME_MAP;
  
  private final String name;
  
  static {
    NAME_MAP = new HashMap<>();
    ParticleEffect[] arrayOfParticleEffect;
    int j = (arrayOfParticleEffect = values()).length;
    for (int i = 0; i < j; i++) {
      ParticleEffect p = arrayOfParticleEffect[i];
      NAME_MAP.put(p.name, p);
    } 
    try {
      packetPlayOutWorldParticles = ReflectionHandler.getConstructor(
          ReflectionHandler.PacketType.PLAY_OUT_WORLD_PARTICLES.getPacket(), 
          new Class[] { String.class, float.class, float.class, float.class, float.class, float.class, float.class, 
            float.class, int.class });
      getHandle = ReflectionHandler.getMethod("CraftPlayer", ReflectionHandler.SubPackageType.ENTITY, "getHandle", 
          new Class[0]);
      playerConnection = ReflectionHandler.getField("EntityPlayer", 
          ReflectionHandler.PackageType.MINECRAFT_SERVER, "playerConnection");
      sendPacket = ReflectionHandler.getMethod(playerConnection.getType(), "sendPacket", new Class[] { ReflectionHandler.getClass("Packet", ReflectionHandler.PackageType.MINECRAFT_SERVER) });
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  ParticleEffect(String name) {
    this.name = name;
  }
  
  public String getName() {
    return this.name;
  }
  
  public static ParticleEffect fromName(String name) {
    if (name != null)
      for (Map.Entry<String, ParticleEffect> e : NAME_MAP.entrySet()) {
        if (((String)e.getKey()).equalsIgnoreCase(name))
          return (ParticleEffect)e.getValue(); 
      }  
    return null;
  }
  
  private static List<Player> getPlayers(Location center, double range) {
    List<Player> players = new ArrayList<>();
    String name = center.getWorld().getName();
    double squared = range * range;
    for (Player p : Bukkit.getOnlinePlayers()) {
      if (p.getWorld().getName().equals(name) && p.getLocation().distanceSquared(center) <= squared)
        players.add(p); 
    } 
    return players;
  }
  
  private static Object instantiatePacket(String name, Location center, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
    if (amount < 1)
      throw new PacketInstantiationException("Amount cannot be lower than 1"); 
    try {
      return packetPlayOutWorldParticles.newInstance(new Object[] { name, Float.valueOf((float)center.getX()), 
            Float.valueOf((float)center.getY()), Float.valueOf((float)center.getZ()), Float.valueOf(offsetX), 
            Float.valueOf(offsetY), Float.valueOf(offsetZ), Float.valueOf(speed), Integer.valueOf(amount) });
    } catch (Exception e) {
    	e.printStackTrace();
      throw new PacketInstantiationException("Packet instantiation failed", e);
      
    } 
  }
  
  private static Object instantiateIconCrackPacket(int id, Location center, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
    return instantiatePacket("iconcrack_" + id, center, offsetX, offsetY, offsetZ, speed, amount);
  }
  
  private static Object instantiateBlockCrackPacket(int id, byte data, Location center, float offsetX, float offsetY, float offsetZ, int amount) {
    return instantiatePacket("blockcrack_" + id + "_" + data, center, offsetX, offsetY, offsetZ, 0.0F, amount);
  }
  
  private static Object instantiateBlockDustPacket(int id, byte data, Location center, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
    return instantiatePacket("blockdust_" + id + "_" + data, center, offsetX, offsetY, offsetZ, speed, amount);
  }
  
  private static void sendPacket(Player p, Object packet) {
    try {
      sendPacket.invoke(playerConnection.get(getHandle.invoke(p, new Object[0])), new Object[] { packet });
    } catch (Exception e) {
      throw new PacketSendingException("Failed to send a packet to player '" + p.getName() + "'", e);
    } 
  }
  
  private static void sendPacket(Collection<Player> players, Object packet) {
    for (Player p : players)
      sendPacket(p, packet); 
  }
  
  public void display(Location center, float offsetX, float offsetY, float offsetZ, float speed, int amount, Player players) {
    sendPacket(Arrays.asList(new Player[] { players } ), instantiatePacket(this.name, center, offsetX, offsetY, offsetZ, speed, amount));
  }
  
  public void display(Location center, double range, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
    if (range > 16.0D)
      throw new IllegalArgumentException("Range cannot exceed the maximum value of 16"); 
    sendPacket(getPlayers(center, range), 
        instantiatePacket(this.name, center, offsetX, offsetY, offsetZ, speed, amount));
  }
  
  public void display(Location center, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
    display(center, 16.0D, offsetX, offsetY, offsetZ, speed, amount);
  }
  
  public static void displayIconCrack(Location center, int id, float offsetX, float offsetY, float offsetZ, float speed, int amount, Player[] players) {
    sendPacket(Arrays.asList(players), 
        instantiateIconCrackPacket(id, center, offsetX, offsetY, offsetZ, speed, amount));
  }
  
  public static void displayIconCrack(Location center, double range, int id, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
    if (range > 16.0D)
      throw new IllegalArgumentException("Range has to be lower/equal the maximum of 16"); 
    sendPacket(getPlayers(center, range), 
        instantiateIconCrackPacket(id, center, offsetX, offsetY, offsetZ, speed, amount));
  }
  
  public static void displayIconCrack(Location center, int id, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
    displayIconCrack(center, 16.0D, id, offsetX, offsetY, offsetZ, speed, amount);
  }
  
  public static void displayBlockCrack(Location center, int id, byte data, float offsetX, float offsetY, float offsetZ, int amount, Player[] players) {
    sendPacket(Arrays.asList(players), 
        instantiateBlockCrackPacket(id, data, center, offsetX, offsetY, offsetZ, amount));
  }
  
  public static void displayBlockCrack(Location center, double range, int id, byte data, float offsetX, float offsetY, float offsetZ, int amount) {
    if (range > 16.0D)
      throw new IllegalArgumentException("Range has to be lower/equal the maximum of 16"); 
    sendPacket(getPlayers(center, range), 
        instantiateBlockCrackPacket(id, data, center, offsetX, offsetY, offsetZ, amount));
  }
  
  public static void displayBlockCrack(Location center, int id, byte data, float offsetX, float offsetY, float offsetZ, int amount) {
    displayBlockCrack(center, 16.0D, id, data, offsetX, offsetY, offsetZ, amount);
  }
  
  public static void displayBlockDust(Location center, int id, byte data, float offsetX, float offsetY, float offsetZ, float speed, int amount, Player[] players) {
    sendPacket(Arrays.asList(players), 
        instantiateBlockDustPacket(id, data, center, offsetX, offsetY, offsetZ, speed, amount));
  }
  
  public static void displayBlockDust(Location center, double range, int id, byte data, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
    if (range > 16.0D)
      throw new IllegalArgumentException("Range has to be lower/equal the maximum of 16"); 
    sendPacket(getPlayers(center, range), 
        instantiateBlockDustPacket(id, data, center, offsetX, offsetY, offsetZ, speed, amount));
  }
  
  public static void displayBlockDust(Location center, int id, byte data, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
    displayBlockDust(center, 16.0D, id, data, offsetX, offsetY, offsetZ, speed, amount);
  }
  
  private static final class PacketInstantiationException extends RuntimeException {
    private static final long serialVersionUID = 3203085387160737484L;
    
    public PacketInstantiationException(String message) {}
    
    public PacketInstantiationException(String message, Throwable cause) {}
  }
  
  private static final class PacketSendingException extends RuntimeException {
    private static final long serialVersionUID = 3203085387160737484L;
    
    public PacketSendingException(String message, Throwable cause) {}
  }
}


package net.helix.pvp;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.neznamy.tab.api.TabAPI;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.util.HelixCooldown;
import net.helix.pvp.command.Fake;
import net.helix.pvp.listener.PlayerJoinListener;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.PrefixNode;
import net.luckperms.api.query.QueryOptions;

public class FakeAPI {
  public static final List<String> randomNicks = Arrays.asList(new String[] { 
        "SopaPvP", 
        "Misto_Quente", 
        "Kendrick_", 
        "Avertado", 
        "LinguiniPvP", 
        "AbacateMaligno", 
        "AveiaDoBem", 
        "Asuma_Sarutobi", 
        "KellPvP", 
        "KennyTV", 
        "Reizinkk", 
        "RataoeBom", 
        "AquilesPedra", 
        "PedroAugusto", 
        "Cabecinha44", 
        "Atomico", 
        "AlineLinda", 
        "Corote", 
        "BlueBird", 
        "SoundDrout", 
        "AmoLamber", 
        "KKPT", 
        "Lascivia", 
        "Radommi", 
        "SantanaHapki", 
        "LixoEMal", 
        "StellaRai", 
        "VictoreSte", 
        "MrPowerHD", 
        "PartneredOwner", 
        "SoundDrrt", 
        "AvisadoKK", 
        "Pedagogia", 
        "PedindoPvP", 
        "LiberaPeso", 
        "Lariiit", 
        "HidanMaldito", 
        "FoiceeBom", 
        "CefetColtec", 
        "VacaAltio", 
        "Eletricat", 
        "Arkett", 
        "AlexMarkey2", 
        "JoaoPAO", 
        "Danoninhoi", 
        "DoidoVarrru", 
        "Varuyy", 
        "ValistoPvP", 
        "YTKiko", 
        "RondoniaPvP", 
        "YumiiiPvP", 
        "MinhaMaldicao", 
        "Viniciusgf",
        "BachMen",
        "Vexionm",
        "Rarutggv",
        "Kaption",
        "Hideengg",
        "BlackPU",
        "RafaelALindo",
        "BemLindiin",
        "LagartoKA",
        "MeuCaoNegro",
        "Biiiling",
        "ShingechiY",
        "PelejaDoMal",
        "QQuuu",
        "QueQueFoi3",
        "makapaka",
        "UmBananinha",
        "Binjii",
        "LegalTYT",
        "JackRipper",
        "NarakuMal",
        "Carneeeee",
        "LugarPvP",
        "CasaTorta",
        "Veiiin55",
        "MuitiProxyt",
        "SouSmartBR",
        "IsabelaLinda",
        "AmoGarotas4",
        "PessoaGay3",
        "ThalesRoberto",
        "MuitaMaldito",
        "MilagreJesus",
        "DioDoBem",
        "Rafffff",
        "Araujo175",
        "Logaritmo5",
        "HeeyCap",
        "Quantyo", 
        "Devesry", 
        "Salgado99", 
        "Azar99", 
        "Sortudo99", 
        "MorriMatei", 
        "Ventilador", 
        "Devimm", 
        "KupimPvP", 
        "NarutoRasen", 
        "Rashenshuriken", 
        "Latiod", 
        "RomaPvP", 
        "DesignTT", 
        "HeisDead", 
        "Alraedy", 
        "PassedOn", 
        "AlvimCott", 
        "DefeatAsuma", 
        "DefeatCali", 
        "FinnHumano",
        "MeDesculpee",
        "EuCOPII",
        "UmaLingua", 
        "Retaliado", 
        "VariacaoK",
        "LerdoNao",
        "Kuryama99",
        "RobustoMuito",
        "Vem1v1L",
        "LauraLinn",
        "PapelPedra6",
        "Reitorrt", 
        "Yuuuuim", 
        "BinaryPvP", 
        "FinnHuman", 
        "JakeDog", 
        "VentoAUREO", 
        "LinguinhaBoa", 
        "LuisaPvP", 
        "Papelao2", 
        "Radnunb", 
        "wdfgtt", 
        "edftgtt6", 
        "SomDosGames", 
        "MeuMalvadinho", 
        "GariDeidara", 
        "HidanEBom", 
        "Binono", 
        "Estiliti", 
        "KapetaPvP", 
        "KP_PVP", 
        "RAntiBot", 
        "Laurixa", 
        "Laurinha", 
        "IsabellaGata", 
        "Amandinha22", 
        "Kirin", 
        "Ravioli", 
        "LincePvP", 
        "Ratao", 
        "AquelePvP", 
        "Ourinho", 
        "PatoDMinas", 
        "Kombaaa", 
        "RAFAELESPADA", 
        "Douradinho", 
        "Alecrim", 
        "Raivinha", 
        "Chuvinha", 
        "QuePeninha", 
        "MalditoPvP", 
        "PessoaMaligna", 
        "Dio_Brando", 
        "JojoJoestar", 
        "Halley", 
        "OvoEsmagado", 
        "Folha45", 
        "RitualeBom", 
        "SpongeBobLV", 
        "Frisk", 
        "Ravioli", 
        "MorteRitual", 
        "LindaPvP", 
        "sdcfvb",
        "kjtgfv",
        "45tghg",
        "sxdcfgh",
        "lytgv5", 
        "6yhgg",
        "56hgtf",
        "dddd4", 
        "UrsimDODO", 
        "PinnDyt", 
        "TorsoQuebrou", 
        "Tecnicagh" });
  
  private static void addFakeCooldown(Player playerData) {
    HelixCooldown.create(playerData.getName(), "fake", TimeUnit.SECONDS, 15L);
    Bukkit.getScheduler().scheduleSyncDelayedTask(HelixPvP.getInstance() , new BukkitRunnable() {
        @Override
        public void run() {
       	HelixCooldown.delete(playerData.getName(), "fake");
       	playerData.sendMessage(ChatColor.GREEN + "Você pode usar o /fake novamente");
            }
        }
    , 20 * 15);
  }
  public static boolean hasFakeCooldown(Player playerData) {
	    return HelixCooldown.has(playerData.getName(), "fake");
	  }
  
  public static void applyFake(String fake, Player playerData) {
      Player player = playerData.getPlayer();
      LuckPerms api = LuckPermsProvider.get();
      TabAPI apitab = TabAPI.getInstance();
      try {
          String prefix = api.getGroupManager().getGroup("default").getCachedData().getMetaData().getPrefix();

          // Get an OfflinePlayer object for the player

         

        	Fake.playerfakename.put(player, fake);
        	changeGamerProfileName(fake, player);
          // Load, modify & save the user in LuckPerms.
          api.getUserManager().modifyUser(player.getUniqueId(), (User user) -> {

              // Remove all other prefixes the user had before.
              user.data().clear(NodeType.PREFIX::matches);

              // Find the highest priority of their other prefixes
              // We need to do this because they might inherit a prefix from a parent group,
              // and we want the prefix we set to override that!
              Map<Integer, String> inheritedPrefixes = user.getCachedData().getMetaData(QueryOptions.nonContextual()).getPrefixes();
              int priority = inheritedPrefixes.keySet().stream().mapToInt(i -> i + 10).max().orElse(10);

              // Create a node to add to the player.
              Node node = PrefixNode.builder(prefix, priority).build();

              // Add the node to the user.
              user.data().add(node);

              // Tell the sender.
              Bukkit.getConsoleSender().sendMessage(player.getName() + " colocou o fake " + fake);
             
              apitab.getPlayer(player.getName()).setTemporaryGroup("default");
              addFakeCooldown(playerData);
          });
  } catch (NullPointerException e) {
  	player.sendMessage(ChatColor.RED + "§4§lFAKE: §cUm erro ocorreu!");
  }
  }
  public static boolean hasFake(Player playerData) {
      Player player = playerData.getPlayer();
      return !player.getName().equalsIgnoreCase(PlayerJoinListener.playerrealname.get(player));
  }
  
  public static void changeGamerProfileName(String name, Player player) {
    try {
      Method getHandle = player.getClass().getMethod("getHandle", 
          null);
      try {
        Class.forName("com.mojang.authlib.GameProfile");
      } catch (ClassNotFoundException e) {
        return;
      } 
      Object profile = getHandle.invoke(player, new Object[0]).getClass()
        .getMethod("getProfile", new Class[0])
        .invoke(getHandle.invoke(player, new Object[0]), new Object[0]);
      Field ff = profile.getClass().getDeclaredField("name");
      ff.setAccessible(true);
      ff.set(profile, name);
      for (Player players : Bukkit.getOnlinePlayers()) {
        players.hidePlayer(player);
        players.showPlayer(player);
      } 
    } catch (NoSuchMethodException|SecurityException|IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|NoSuchFieldException e) {
      e.printStackTrace();
    } 
  }
  
  public static boolean isNickValid(String nick) {
    Pattern p = Pattern.compile("[^a-zA-Z0-9_]");
    if (p.matcher(nick).find())
      return false; 
    return (nick.length() >= 3 && nick.length() <= 16);
  }
  
  
  private static boolean logWebhook(Player playerData, String fake) {
    Player player = playerData.getPlayer();
    DiscordWebhook webhook = new DiscordWebhook("https://discord.com/api/webhooks/974384437611626526/S1AfsIclqOP94xKM3NtS-yKAuDQV9XDkEGtWXpLjzWxdIDninboop6MFot07SjCsjEzo");
    webhook.setContent("O player **" + playerData.getName() + "** mudou seu nick para ``" + fake + "``.");
    try {
      webhook.execute();
    } catch (IOException e) {
      player.sendMessage("ao registrar log, cancelando aa equipe de desenvolvimento)");
      e.printStackTrace();
      return false;
    } 
    return true;
  }
  

  
  public static boolean isUsernamePremium(String username) throws IOException {
    URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + username);
    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
    StringBuilder result = new StringBuilder();
    String line;
    while ((line = in.readLine()) != null)
      result.append(line); 
    return !result.toString().equals("");
  }
}


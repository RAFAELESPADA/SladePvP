package net.helix.pvp;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.helix.core.util.HelixCooldown;

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
        "UrsimDODO", 
        "PinnDyt", 
        "TorsoQuebrou", 
        "Tecnica" });
  
  private static void addFakeCooldown(Player playerData) {
    HelixCooldown.create(playerData.getName(), "fake", TimeUnit.SECONDS, 15L);
  }
  
  private static void changeGamerProfileName(String name, Player player) {
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


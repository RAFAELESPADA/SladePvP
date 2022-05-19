package net.helix.pvp;


import net.helix.core.account.HelixRole;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.api.NameTagAPI;
import net.helix.core.util.HelixCooldown;
import net.helix.core.util.HelixCooldown2;
import net.helix.pvp.evento.PlayerData;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import net.dev.eazynick.api.NickManager;

public class FakeAPI {

    public static final List<String> randomNicks = Arrays.asList(
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
      "TorsoQuebrou",
      "Tecnica");



    private static void addFakeCooldown(Player playerData) {
        HelixCooldown.create(playerData.getName(), "fake", TimeUnit.SECONDS, 15);
    }

    private static void changeGamerProfileName(String name, Player player) {
        try {
            Method getHandle = player.getClass().getMethod("getHandle",
                    (Class<?>[]) null);
            try {
                Class.forName("com.mojang.authlib.GameProfile");
            } catch (ClassNotFoundException e) {
                return;
            }
            Object profile = getHandle.invoke(player).getClass()
                    .getMethod("getProfile")
                    .invoke(getHandle.invoke(player));
            Field ff = profile.getClass().getDeclaredField("name");
            ff.setAccessible(true);
            ff.set(profile, name);
            for (Player players : Bukkit.getOnlinePlayers()) {
                players.hidePlayer(player);
                players.showPlayer(player);
            }
        } catch (NoSuchMethodException | SecurityException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    public static boolean isNickValid(String nick) {
        Pattern p = Pattern.compile("[^a-zA-Z0-9_]");
        if (p.matcher(nick).find()) return false;
        return nick.length() >= 3 && nick.length() <= 16;
    }

    public static boolean hasFake(Player player) {
    	 NickManager api = new NickManager(player);
        return api.isNicked();
    }
    public static String getNick(Player player) {
   	 NickManager api = new NickManager(player);
       return api.getNickName();
   }
    
    private static boolean logWebhook(Player playerData, String fake) {
        Player player = playerData.getPlayer();
        DiscordWebhook webhook = new DiscordWebhook("https://discord.com/api/webhooks/974384437611626526/S1AfsIclqOP94xKM3NtS-yKAuDQV9XDkEGtWXpLjzWxdIDninboop6MFot07SjCsjEzo");
        webhook.setContent("O player **" + playerData.getName() + "** mudou seu nick para ``" + fake + "``.");
        try {
            webhook.execute();
        } catch (IOException e) {
            player.sendMessage("§cErro ao registrar log, cancelando ação... §7(Contate a equipe de desenvolvimento)");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void applyFake(String fake, Player player) {
    NickManager api = new NickManager(player);
       HelixPlayer helixPlayer = HelixBukkit.getInstance().getPlayerManager()
				.getPlayer(player.getName()); 
       try {
		if (!isUsernamePremium(fake.toString())) {
			logWebhook(player, fake);
			api.nickPlayer("§7" + fake);
			api.setTabPrefix("§7");
			api.setTabSuffix("§7");
			api.changeSkinToMineSkinId("15a8a02d5c6846b9b0fccad7b5d51497");
		    Bukkit.dispatchCommand(player, "tag membro");
		    api.updatePlayer();
		    NameTagAPI.updatePlayersNameTag();
		    api.setTagPrefix("§7");
			   }
		   
		 else {
			 logWebhook(player, fake);
			 api.nickPlayer("§7" + fake, fake);
			 api.changeSkin(fake);
			 Bukkit.dispatchCommand(player, "tag membro");
			 api.updatePlayer();
			 api.setTabPrefix("§7");
				api.setTabSuffix("§7");
			 NameTagAPI.updatePlayersNameTag();
			 
		 }
	} catch (IOException e) {
		player.sendMessage("§cOcorreu um erro ao trocar de fake. Contate o zEnderX5_ no Discord.");
		e.printStackTrace();
	}
        api.updatePlayer();
        addFakeCooldown(player);
    }
    public static boolean isUsernamePremium(String username) throws IOException {
        URL url = new URL("https://api.mojang.com/users/profiles/minecraft/"+username);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String line;
        StringBuilder result = new StringBuilder();
        while ((line = in.readLine())!=null){
            result.append(line);
        }
        return !result.toString().equals("");
    }
   
    
    
    }



package net.helix.pvp.inventory.listener;

import java.lang.reflect.Type;
import java.util.UUID;

import org.bukkit.ChatColor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.mojang.authlib.GameProfile;

import net.minecraft.server.v1_8_R3.ChatModifier;
import net.minecraft.server.v1_8_R3.ChatTypeAdapterFactory;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketStatusOutServerInfo;
import net.minecraft.server.v1_8_R3.ServerPing;
import net.minecraft.server.v1_8_R3.ServerPing.ServerData;
import net.minecraft.server.v1_8_R3.ServerPing.ServerPingPlayerSample;

public class PingListener{

    private static final Gson a;

    static{
        a = new GsonBuilder().registerTypeAdapter(ServerPing.ServerData.class, new OverridenServerDataSerializer())
                .registerTypeAdapter(ServerPing.ServerPingPlayerSample.class, new OverridenServerSampleSerializer())
                .registerTypeAdapter(ServerPing.class, new PingSerializer())
                .registerTypeHierarchyAdapter(IChatBaseComponent.class, new IChatBaseComponent.ChatSerializer())
                .registerTypeHierarchyAdapter(ChatModifier.class, new ChatModifier.ChatModifierSerializer())
                .registerTypeAdapterFactory(new ChatTypeAdapterFactory()).create();
    }

    public PingListener(){
        ReflectedObject.setStaticField(PacketStatusOutServerInfo.class, "a", a);
    }

    public static class PingSerializer extends ServerPing.Serializer{
        @Override
        public JsonElement a(ServerPing serverPing, Type type, JsonSerializationContext jsonSerializationContext) {

            serverPing.setMOTD(getMessage(SelectKitListener.getMotd()));
 
            ReflectedObject ping = new ReflectedObject(serverPing);
 
            ReflectedObject playerSample = ping.get("b");
            playerSample.setField("c",new GameProfile[] {new GameProfile(new UUID(0,0)/*Not sure what will hapen if set to null*/, ChatColor.RED + "Venha jogar no melhor Servidor")});

            ReflectedObject serverData = ping.get("c");
            serverData.setField("b",1); // so it will show the version.
            serverData.setField("a", ChatColor.GREEN + "KITPVP"); // if we do not set the chat color, it will be red

            return super.a(serverPing, type, jsonSerializationContext);
        }
    }

    public static IChatBaseComponent getMessage(String message) {
        return ChatSerializer.a("{\"text\": \"" + message + "\"}");
    }

    public static class OverridenServerDataSerializer extends ServerPing.ServerData.Serializer{

        @Override
        public JsonElement a(ServerData serverData, Type type, JsonSerializationContext jsonSerializationContext) {
            return super.a(serverData, type, jsonSerializationContext);
        }

    }
    public static class OverridenServerSampleSerializer extends ServerPing.ServerPingPlayerSample.Serializer {
        @Override
        public JsonElement a(ServerPingPlayerSample serverPingPlayerSample, Type type,
                JsonSerializationContext jsonSerializationContext) {
 
            JsonObject jsonObject = (JsonObject) super.a(serverPingPlayerSample, type, jsonSerializationContext);

            return jsonObject;
 
        }
    }
}


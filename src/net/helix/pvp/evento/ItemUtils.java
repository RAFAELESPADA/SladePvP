package net.helix.pvp.evento;



import org.apache.commons.codec.binary.Base64;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ItemUtils {

    public static ItemStack cocoa = new ItemStack(Material.INK_SACK, 1, (short) 3);

    public static ItemStack getCustomItemStack(Material material, String name, String lore) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        if (lore != null) {
            List<String> l = Collections.singletonList(lore);
            itemMeta.setLore(l);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack getCustomItemStack(Material material, String name, List<String> lore) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack editItemStack(ItemStack itemStack, String name, List<String> lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack getPlayerSkull(String name) {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
        meta.setOwner(name);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    
    public static ItemStack getLAVASkull() {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
        meta.setOwner("Larrxe");     
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    public static ItemStack getGladSkull() {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
        meta.setOwner("1MatiX1");     
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    public static ItemStack getSkull(String url) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        if (url == null || url.isEmpty())
            return skull;
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
       
        	GameProfile skin17147310 = new GameProfile(UUID.fromString("b48bce55-2b11-4c98-85f7-8c77ca17accd"), "skin17147310");
        	skin17147310.getProperties().put("textures", new Property("textures", "ewogICJ0aW1lc3RhbXAiIDogMTYyMTY4MTgyMjgzNiwKICAicHJvZmlsZUlkIiA6ICIwZjczMDA3NjEyNGU0NGM3YWYxMTE1NDY5YzQ5OTY3OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJPcmVfTWluZXIxMjMiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWM2NzU0MjJmZDgzNzA2Zjc3NWZhNmQ4NDkyYmUxZDg2N2Y2YzIwMzM0Y2QxOWRhZTk1ZmE0OTIzZmJmYmY0YiIKICAgIH0KICB9Cn0=", "r/PrRgCGkyQAWibFZUMsEyTy1GBz9P3ZarkSPHVQQevC1jzbMFeW4lbqAPz7410bMQ+dYDXzZWQ5j7W0OOPHH6et8CieQuAnm7C34F0llyX3cFYyjDFjIDlX/7yX8qb071oL3zyh2LNwySkHRb45Bw5wU4zMUuEVibLurrclo6N+cMcsHvRObw2x4ztCCUvr4khLNlGWIiATdtpnU4kmUFkQa0KT3eVlXSAHvL4+4XB0PWYCQWJ2LGfTiFMdwG25XE0wRZkcJcx+uMtPYqN8D3WSkZ+8IABInpXc88YicFVn1pjNKZDIACmUUjg3h3RhKEgnErnCVBmrCnCGY4F4LvtrRNW02Tc4tt5yeN2pPHzQyxeZGpmvTEmHqy4GDQvMRI/GPzQF6etn5OthUZQADadsZ5JOnWaCmivedlBfdD4+qGP4Ro+787QI5QbSbTpjt/T9h805s18ciCNZB7Ts60G6Kbc/dJYdox3xJGSiVke2f+Tf+WSrosB88Fahxuab0rxz82ipUDYb0Y7r+5LPPvdShzeK5LeNgn3nKJnUvA5Zu+aN9wopY9UFe6E/yjB2M5exHE70xMtk3+iwF2UOo408SJEA7iDZnYxnma1CPciZnfmo50IZZt3w3PF83e4JcCYIxtW5ashCgz5fGPbL6gSbI/asDYS4v+1HvLJmJmo="));
        	                                    
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        skin17147310.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta, skin17147310);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        skull.setItemMeta(skullMeta);
        return skull;
    }
    public static ItemStack getSkullGLAD(String url) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        if (url == null || url.isEmpty())
            return skull;
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
     // Skin 1ca749bb generated on Apr 18, 2023 11:45:37 AM via MineSkin.org - https://minesk.in/1ca749bb94614c808d3d2c9b886fd99f
        GameProfile skin1ca749bb = new GameProfile(UUID.fromString("4c0fd72b-eb48-4c9c-a557-d962ebb0559b"), "skin1ca749bb");
        skin1ca749bb.getProperties().put("textures", new Property("textures", "ewogICJ0aW1lc3RhbXAiIDogMTY4MTgyOTEzNzA4NCwKICAicHJvZmlsZUlkIiA6ICIzNmMxODk4ZjlhZGE0NjZlYjk0ZDFmZWFmMjQ0MTkxMyIsCiAgInByb2ZpbGVOYW1lIiA6ICJMdW5haWFuIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzY5ODQ4ZjZkYjVlZDE4NTYzMGUwNDRlNDc4YjgxZGVhZGM2M2Q2NzE5YzE5NWI5YTU2M2Y3NDU0NDZmNjFkYWYiCiAgICB9CiAgfQp9", "qp63nbk8LLtiA/W3xt8FIt+SFIOYNkpev8RCXeYpu2ruvLsu/t4n0/BTPfznklzrlpSE5pkBKD1ibQ8A5HhXI85dN9LndpjqPFbf/1+yPgQ9Q8M3UCingLOVTJYgjWgcngHHSaFEORZSdex+IVcWnQ10Gikhi5zRhVCRsoqIlAhJMS6athwr47kx4BeaCZTP8n6ADrtHIRE9I8bLLZ3TwlA0iBH0d7UDW8qkoj36ao3JJUvpOUsgdKJTYBVrZoJxQ4nNR47VFm5ei5RT4lMvabT5mHOj3wj1AozqMqPsFjCGSkZrm9W3eWsJop9cG4pYx3GPwn2aIAgoKB79ou14WDQcWaaO33KrOA/KYVO7/4PJUOE0Xg3VfZCcJku50g1xi3zncsaiEMTBQfUtyIoPCt9AFccDz+fJFir5TQvzwNzGlLyGBeOj/EI1v+1OenZhGF8A3bbhUFOm8M/YoLgYiaVnpcw1Wd2geQ+LiKCdn6Fhn1wDzAyRNmhkswwQdmGCrTsF+/gEpqJPC4pWa+UpaaB3aVMtmEFtrTmDBbX1h+L+Ofx0rjHvVhxnzUQClnDNh8GBcUowbh3EpqqvEmFF/6EuXPIN8Vr1bQRbr04bPzcLL13GKzG3WVVIP69IbGOyPM1uBg9GRFxrX5iuElvpGaDKWF3ZtAGvpPZE3BxqBCg="));
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        skin1ca749bb.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta,skin1ca749bb);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        skull.setItemMeta(skullMeta);
        return skull;
    }
    public static ItemStack getPOTGLAD(String url) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        if (url == null || url.isEmpty())
            return skull;
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
     // Skin 1ca749bb generated on Apr 18, 2023 11:45:37 AM via MineSkin.org - https://minesk.in/1ca749bb94614c808d3d2c9b886fd99f
        GameProfile skin0f244916 = new GameProfile(UUID.fromString("b688ae01-fe56-4862-ad23-71fcf49bf315"), "skin0f244916");
        skin0f244916.getProperties().put("textures", new Property("textures", "ewogICJ0aW1lc3RhbXAiIDogMTY5MjMyMTUxODQxMSwKICAicHJvZmlsZUlkIiA6ICJhODUxNzQ0MDNlNjg0MDgwYWNkODU3MzhlMjI5NGNhZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJEYVJpdmVyc09uZSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9iMmYyYmZmMmMxNDZjZTdjMTk0OWFkMzMxNjU4NzgzNTlhNDY3NTM0OTg4N2NmYWU0MjMwZTc0ZGY3MzNhZTE4IgogICAgfQogIH0KfQ==", "PJud/K3bVX2lCw9Ep6H0zoGU7HRVIJeHSApvV7jtRcJvxkTgqIhTZRWtMfP5mYFvOQrzJQWhRv9gu8bVCtdgcGtMLJyDf2QZ4W8hNray1l226PH3ah+3/CG+rQV1OwnS0BpfKfQoPKCU9AtrpH4X2zn3+HeDQoY9yxztTgDUzG8hM/oDEg9BCfrjJQrCcfl3a6xlT7H5ZZTBJ5v5n20FLeEY2ORJ8zrm51HQvvvHMHSyvFyW6c+oBzRpUpd45ElSkeswwzVQ/Ha4k+3q9hUUsb7kZHoVuA1+oortHBvmdgZkWP0JvAc+T37Xc8XvqO9O7n/m8Ectifvba8PSRa5IjYWzxIWsOPC7sXD6rVmrxLFOYm6PVN7Z0K+Vw5Qyb6HIJmf8xwUU/ablrthZS5nY+r0QJQh5bBS8OTa+Nc6Dd8c+x0a06RQjY1BKcFMTTeiGLs86lbZiaxnFZETYE2sdjRiqhcRfz/CgriQ3sW6F3Wfzo1nXPmJRV39yKu3/KXgjqHh7AuH6eBIuFnht6a5hgAo+zX4185gEbpF/Niu04Srgk8l1ejDNcBQA6Y4fjskLINrRaoIDLjxA5bSLB2R2NHEIN3T1xjcW5Bw5w6rYg8+kW7T1nFwSG5FeLVjxKKDqluHZZN9KooqNUMstp+oNj74dpTvDwbOdY4AruYH2Z+Y="));
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        skin0f244916.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta,skin0f244916);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        skull.setItemMeta(skullMeta);
        return skull;
    }
    //https://minesk.in/6999f662f537468ab71aa3310d00499d
    //GameProfile skin6999f662 = new GameProfile(UUID.fromString("b20d35e8-ed22-41d9-a43d-3210d6dded14"), "skin6999f662");
    //skin6999f662.getProperties().put("textures", new Property("textures", "ewogICJ0aW1lc3RhbXAiIDogMTY2MDYxMjIzMDQyMiwKICAicHJvZmlsZUlkIiA6ICIwZjFjOTU0NDBmNjY0NjdkOGIzMzhiMzkwZjc3YjU2NCIsCiAgInByb2ZpbGVOYW1lIiA6ICJUYW5UYW5KaWFuZyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9iYTNkMmM0OWRmM2RlYmYzOWY5NWIzZDBkY2U4MTIzZDk0YTdhZDVkMTA2MTZiODdkMDhlNTA0MjVlMDcyNTU3IgogICAgfQogIH0KfQ==", "EBGuBHF4bgLGP7IUlrVNIPibROAy8C8snUhWP6zBqXzOIFLZBVxR+ma8CJG1mkx3BLGP+mEVPQFl4b4vS0mBzxw4BYXQfM9dJeyUM32dJuQoxXQ5K4g3cqeY8RbuhsRVy+/cF2GhhIb+IFwWSOJk29mZ866bTxHrzyg3DOSqHe23pVLW5tQ2PHevE46KQ+O8QPSpIEqHHd52ySV+wyqC2Y29ujD3gosOxCy1gxakDGFRULYXZhjNU5zBLeHarwW5UUHifuO6dRaARWbg7cOLZtdGDKTlGkFtSPuy98eCbiPfqdARWmFAEbPE7EyUvVM0FMNxHNnsTQtBYmWu2NiCDzZAgF3XsUh5Zl+wxqncw+7qe1Mq4I99/6M1LTsrc7Fpg5zgz1d7kmTDySSCg5veV8cUPsQNqE1aAXltynauzlEx1VU0KlvtRW66Ob67+V7+Qqdp9Cmg9vdL5bwa3YbhfLiEZsoQ/H4GJeoDX6hf7lPmZ3pBVbBuSBS2T7HihIIAk1tNl/qOsn2f6LwKnF+hZsOB2B8YB1HBRqtTIwJG1zW8XbWqJRxm5bh7zTEAK9e4c/kfraD8Zzsj5mjsigmrIRPQoYMs46Q6/CYAaNIvkr/eZpZJmB7fV8JdM/M1hv/+1z/+qz54a29zhtUOF48jmmt2PyXA6xhib0o78PRKQzI="));
    //
    public static ItemStack getSumo(String url) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        if (url == null || url.isEmpty())
            return skull;
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

     // Skin 45077d60 generated on Jun 21, 2021 3:48:18 PM via MineSkin.org - https://minesk.in/45077d608bb6450a9a6ec98e4a6eb279
        GameProfile skin45077d60 = new GameProfile(UUID.fromString("92f0dbc7-06a5-41b4-a0f2-7a6b5daaa49f"), "skin45077d60");
        skin45077d60.getProperties().put("textures", new Property("textures", "ewogICJ0aW1lc3RhbXAiIDogMTYyNDMwMTI5NzEwMSwKICAicHJvZmlsZUlkIiA6ICJmNDY0NTcxNDNkMTU0ZmEwOTkxNjBlNGJmNzI3ZGNiOSIsCiAgInByb2ZpbGVOYW1lIiA6ICJSZWxhcGFnbzA1IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzI1YTU2M2E0MTg0YzVlYWFjMzMwMTg5MzhkNmNjODMwZGQyM2Y3Y2E1MzlhMmNhZjc5MGM4YjJiZGRhYjI5MmUiCiAgICB9CiAgfQp9", "Pu/PgIr9BIJnAxTn1FirltG+4Fhue9xyKcvUP3ISjDHcQrYxaJ8Rj4wyAYHrnzhzbPTCtIirXCPR1lUMcBQ/zT3yPD0/u7QK45/JN8vm4qwd92wstwiasir361VkB+IhCs/Nf7lIScfsKnrz7lcb/gXOB3ZXpybwDhi52Ketl6HMvOAwyZLlW2+9u4pXJevaEeKFRtOAJSXHY9lC92re2HYfJVUVkLoAU62SVpDhzRgPFdCgxIplADUQqwGDj9wB2jkLjqVbvVIH1zjQveAnOhfExvAM70GOM/gEHIyFv8WVDDxKEdak0lLq4+qeinEbZ53FO81uuJmZsW0+pdiqe7C0fUxQWlkI8V124GOMHtxUrwBWwvQ5d319igeRikukVootz1qJdFMC0C6sUpWpQetbylqshzug8AFljmDVveLyKDGqOEmA1sg8khv30SDQYY7rC8L/LFgMMAlh6CY/IPTSkRuqQ2jHjGB/xCbywnBWsnBznTP+VutRnocWuwKMCMgp7uaQAaxcgxsGra7g5PTl4hWrWNHC4/3AdhvlAyzPecwnNGfbAtIEQN1u7EgGieobs7JRBCUU1tNl4jvKrEF3WKhtozJl5qXqwK7hFH2JcsS6xXFr2zwEVMhARaUe3rb8d+V21x8NzPYfi2O6WjSj1BgEhuYX01XxMseiqjw="));
        
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        skin45077d60 .getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta,skin45077d60 );
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        skull.setItemMeta(skullMeta);
        return skull;
    }
    public static ItemStack getFish(String url) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        if (url == null || url.isEmpty())
            return skull;
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();



        	GameProfile skin6999f662 = new GameProfile(UUID.fromString("b20d35e8-ed22-41d9-a43d-3210d6dded14"), "skin6999f662");
        	skin6999f662.getProperties().put("textures", new Property("textures", "ewogICJ0aW1lc3RhbXAiIDogMTY2MDYxMjIzMDQyMiwKICAicHJvZmlsZUlkIiA6ICIwZjFjOTU0NDBmNjY0NjdkOGIzMzhiMzkwZjc3YjU2NCIsCiAgInByb2ZpbGVOYW1lIiA6ICJUYW5UYW5KaWFuZyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9iYTNkMmM0OWRmM2RlYmYzOWY5NWIzZDBkY2U4MTIzZDk0YTdhZDVkMTA2MTZiODdkMDhlNTA0MjVlMDcyNTU3IgogICAgfQogIH0KfQ==", "EBGuBHF4bgLGP7IUlrVNIPibROAy8C8snUhWP6zBqXzOIFLZBVxR+ma8CJG1mkx3BLGP+mEVPQFl4b4vS0mBzxw4BYXQfM9dJeyUM32dJuQoxXQ5K4g3cqeY8RbuhsRVy+/cF2GhhIb+IFwWSOJk29mZ866bTxHrzyg3DOSqHe23pVLW5tQ2PHevE46KQ+O8QPSpIEqHHd52ySV+wyqC2Y29ujD3gosOxCy1gxakDGFRULYXZhjNU5zBLeHarwW5UUHifuO6dRaARWbg7cOLZtdGDKTlGkFtSPuy98eCbiPfqdARWmFAEbPE7EyUvVM0FMNxHNnsTQtBYmWu2NiCDzZAgF3XsUh5Zl+wxqncw+7qe1Mq4I99/6M1LTsrc7Fpg5zgz1d7kmTDySSCg5veV8cUPsQNqE1aAXltynauzlEx1VU0KlvtRW66Ob67+V7+Qqdp9Cmg9vdL5bwa3YbhfLiEZsoQ/H4GJeoDX6hf7lPmZ3pBVbBuSBS2T7HihIIAk1tNl/qOsn2f6LwKnF+hZsOB2B8YB1HBRqtTIwJG1zW8XbWqJRxm5bh7zTEAK9e4c/kfraD8Zzsj5mjsigmrIRPQoYMs46Q6/CYAaNIvkr/eZpZJmB7fV8JdM/M1hv/+1z/+qz54a29zhtUOF48jmmt2PyXA6xhib0o78PRKQzI="));
        
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        skin6999f662  .getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta,skin6999f662  );
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        skull.setItemMeta(skullMeta);
        return skull;
    }
    public static ItemStack getKB(String url) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        if (url == null || url.isEmpty())
            return skull;
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();



        GameProfile skin06bea19d = new GameProfile(UUID.fromString("59f2ee5f-3bb9-455b-9d71-a27b40b37e7e"), "skin06bea19d");
        skin06bea19d.getProperties().put("textures", new Property("textures", "ewogICJ0aW1lc3RhbXAiIDogMTYwNDQ5MjYyMzQxOCwKICAicHJvZmlsZUlkIiA6ICJlNzkzYjJjYTdhMmY0MTI2YTA5ODA5MmQ3Yzk5NDE3YiIsCiAgInByb2ZpbGVOYW1lIiA6ICJUaGVfSG9zdGVyX01hbiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS85ODI1ZTk3NTZjM2VhNjZjOGNjZmEyZmU4MDU4MzRmNGZhMDVhMGY0ODc0NTY3NTljMzY0MjcwODkwNTE0NzZlIgogICAgfQogIH0KfQ==", "RLLeCZVT2zj4DhvrVl4fTVy4BdjyK02tTX5u3PT0gNcbluSRDNUjwG+G/HttDVtPpyQuVOq/8aAFT/xdu+BZaiI3q3830BWqVe+CbSJuHorHPbPskJRV8+L+Ra6rJLA9LJQh5li5EZCwiAwqBSQwqycIU7032xjtoPjfOXio0gAnqKLffTEc2irDq4KzZGH9WZjYJrEfS9eCkQ1780jl96oEordJT940G/mgiB+XZdg1RQs+YuDprGvX1ILVZluoN+SFWji9l3aPmktwGoP9K5NDJr1Yjqqtr0QcXUvB3bqs4EDeYe7+NXCtE4eW1bupr22dKO9EERyrL4oOm8K1lgmFRupeSH66R0Ny5Io8NrscZSSH1XZncnj6SZfYskJWRNN//F9BxM86hL0MEPtloAB9o0oJWLyoaXszMb1iJC9RKbvK3c2nAdyjWtcWijRA5muEZszzOX5XngHlFPAYWDI33EuTvqbth3GqZ7kid24Zly6TQbSCrd30EiNTeXYTJ1T5Fx1GRIzEYW+60qf3f2e59SlcBf4D7eA4btIaCAvfx2bKxbZQsdsHKDphpxsr7nizA8iRroo37nBiJ97gYUF2GsL03bLuge4cFLROpb4oaKGyldPZ4cHq33b8sbFs0eQUiS9selDsyx8G8MrvKfhYACpjyBv+ZLlWa+QMF4s="));
        
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        skin06bea19d .getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta,skin06bea19d );
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        skull.setItemMeta(skullMeta);
        return skull;
    }
    public static ItemStack get1v1(String url) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        if (url == null || url.isEmpty())
            return skull;
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();



        GameProfile skinf608bffb = new GameProfile(UUID.fromString("ceef8f44-560c-4bf0-adc5-21b6216278e0"), "skinf608bffb");
        skinf608bffb.getProperties().put("textures", new Property("textures", "ewogICJ0aW1lc3RhbXAiIDogMTYzMjQ5MTA3OTg4MCwKICAicHJvZmlsZUlkIiA6ICJkZTE0MGFmM2NmMjM0ZmM0OTJiZTE3M2Y2NjA3MzViYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJTUlRlYW0iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2JhZDA5NjVhZDg5YzZmOGNhZDgyNTZlZmQ1MDY5MDA5YzQ1OTQ3MmM0MWVjN2RmODRiMTE1OWQ2ZjU1NWNhOSIKICAgIH0KICB9Cn0=", "pmbgj1e7QxuElhHRQbTtM9MKtai1Wg+8n+8bgLqu19b83njG5yHfwb3VjGVqwBQjY21zAeXrBX94OUkqfyq6KdIV9QQbTkOKWVI9ydVR61mv8RkbcKHz7Ef+WNNFfglTeKPFqbj5mGR63hhwYZ5momOnOfGX/iihA97GF/IulnyuX/NA5IQhsuebcWDj1uXpRBsDrbIwSCByCphd2h0lNBdUTsZq3feNcZFem4gXx4h64IV627W5lpS7uQzH3AP+TzWj1rmDHYOksGIDwejIuoadtC3+DxOFCPjoQQL6fjT+EtNpga+PMKo8AMW5UxIjakpD7CNUHxh/9GDC7q41TJjI0uyEKEKn9wdX5RhM6/ABsllxzlmXl4lNqrwzSfuwIfAkYepHyMWgog9WLW4w+2aJoc5MKciBwbTcFxGLHsOXh63Gyv1zT8BsvcgesMCE74I2bIr60mBF0NDBHnPsa7EaTwTOcQXTkplcEH7519n1/CPYUdpvS6dsFacUTWyiEIOnvFqH8jcs8T8MfqDSvJJBx5Ra61ZHzFai5YJcDroOjKI86CoU4ExfcdF+g4k9ognOoEdX/YhfV1wk4wI8MGldrz/OMva6mMfKIGTsU4rODoH2GBE5fUhbau7Ih29/W5qEWEfRkZh1A5WW/uJtH57plHA3LLAIwzdbMBoIRY0="));
        
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        skinf608bffb  .getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta,skinf608bffb  );
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        skull.setItemMeta(skullMeta);
        return skull;
    }
    public static ItemStack getSkullLAVA(String url) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        if (url == null || url.isEmpty())
            return skull;
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        GameProfile skinad3c5b5f = new GameProfile(UUID.fromString("60f17bb6-1cc4-43f1-8bea-740276370654"), "skinad3c5b5f");
        skinad3c5b5f.getProperties().put("textures", new Property("textures", "ewogICJ0aW1lc3RhbXAiIDogMTYyOTk4OTgzMzM4MiwKICAicHJvZmlsZUlkIiA6ICI4MWU4ZWU1MTE1ZmE0NzU5OTU1YTAzMThkNTNhNjViYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJDb25zaWRlclRoYXQiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzllOGYwNTYyZTI5ZDMwMmJkMzZiOGVmMGJlYmZkZDhiNjI2M2Y5MGFjMDI2NzJhMTg1ZjJiYmNjNWZkYWFiZiIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9", "AShn8IZg4CtJ1LnRe9apVJAE0gFagMUdLzN2SxiMcc5x0j1eFmPL5jGUGDhj+r+wh9oHKAc5iSPexRqHESRIMgUaq57BD7OqV2PbPoOE55jfY2AiYQ+6PcO3rJPyU/DCRHS50kkkWFXr4hA+5bB6WugfErW/TwbeJQEKBUMW25+PyT7z1R11oPs1xPCSuhfHyjKgqCRZb32ZFPI8TYNNDdzQSySxjHz+I9KeH7h/B35GvF5w86BKz1YLo2iunM8Ye8JoB7C91xpmLFQoqT+zngkxiPGK2PpfGtKSGjcc6sxt2rr9L50rJbcP5NlIG8AtPu8T/NADu/s9PnIgr5NKGFaoFSpdOEgGj+LG4WdKjNsJIMAkIAuJnE01oVdnPGOtU4E8zgTTbrg1tfXigmtDU885kXm15pg74SDDZwNoIIxsYceM+UaUPwJK0D0Tpxn984Jud5ZeUDXdMk41AM6KIUUMkNccA6av5FVkeGzFnM/KHDSes439S0fzCpziEwda63NFKLDq+mfJjn2wL+/d9fmpNkZdNXBwyTY11/tt9w96FC7Yta8JPIfKPcMwBpDZY+DEzYLJhf+xbyoFGYJxRRj4QpOCPXbeipXhNxZb4W+yLakKzOMQnR2UpH89CCo42l1beb6ya6Hjtk/Q+8Arno3BJrmKQPG/bilHaJZntxM="));
        skinad3c5b5f.getProperties().put("textures", new Property("textures", "ewogICJ0aW1lc3RhbXAiIDogMTY5MjMyMTA0Njk3MCwKICAicHJvZmlsZUlkIiA6ICJhZjQwMzQxMTk1YTg0ZDcwODFkNWNmNDE3MDM5ODJmNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJBdHJ4dSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9mOTVmMmNlNWEzYWQ5MThkNjY1NWFkOTE1M2I0MTQ3Yzc3ZGM5YzRiMTIzMTRmM2VlZWJmZDUxMzJkYTQzYTdiIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=", "k+LBW0AT3ddcevCBISmyK05COCo+zDWPiUBnQQrznu0kQJ4AuBh25SCXFPVZTTAFH2k4y6CDPZEjAYRI4MwZsfbo2N2zpcf5dWyN0XrcazOtiHQCAJ4KB0HgjsRErk/7ogLfvimMZ5FdAplvfZqUxQBhDGVzNhAtoyxVM1ChGS/RclzAzjHqTyKxqn7bg7Gui8j7/n2W0fqrK9zGop41Nk7B9cDqJ1jEDgUCQRopXQtbqZQCPK7/uN1cqUzpD/65fTcbLPJoEzZC3UQMhBAWrS1RTar1jvnmfAwctmfQLgCHSuSyvqXJgVZdUeYWl96KOGzfO3h6GaJlFPJmbFhpHLw89JOBp+WkdeG3m7+rHKCWHmZdvH5ovHScdBT+Io6/wjQ4qOA0KvkdNrJF7kgSwXOb5vuV83O0s1+XX8SyAsKRfSitlnSzL3X8Iqx5amFU+OUGCwiv3KnRey2rlof2o2jarCJdsxA63kRcVFzQjygPPgk4bD4l6RaY/xg7nH0pUA9HOm/ZY7t0gbhwT4us8S//1/5UmFQuoMO38bYHldZ2UICPX0YTSiTbtQiqPDFbhwG2ZLcvgqWJnFC1Sce79xOYjnzS8zsMI6d8urBQBqhLDjZAqZCgibe7w7UD0eb0p/Aw1XbNOMEiGKbLur/q9n/0/S+3S/Vn0PvL6Kn9PaY="));
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        skinad3c5b5f.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta, skinad3c5b5f);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        skull.setItemMeta(skullMeta);
        return skull;
    }
}
package net.helix.pvp.kit;





import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class Habilidade2
{
    public static Map<String, String> powerMap;
    
    static {
        Habilidade2.powerMap = new HashMap<String, String>();
    }
    
    public static String NomeDoKit(final String original) {
        if (original.length() == 0) {
            return original;
        }
        return String.valueOf(original.substring(0, 1).toUpperCase()) + original.substring(1);
    }
    
    public static String getAbility(final Player player) {
        if (!Habilidade2.powerMap.containsKey(player.getName())) {
            Habilidade2.powerMap.put(player.getName(), "None");
        }
        return Habilidade2.powerMap.get(player.getName());
    }
    
    public static void setAbility(final Player player, final String ability) {
        Habilidade2.powerMap.put(player.getName(), ability);
       
        
    }
    public static boolean ContainsAbility(final Player player) {
    
    		 if (Habilidade2.getAbility(player) != "None") {
    			 return true;
    	            
    	        }
			return false;
    	
		
    }

    
    

    	
    	


    

    		
    	
    	
        
    
    
    public static void removeAbility(final Player p) {
        Habilidade2.powerMap.remove(p.getName());
        
    }
}


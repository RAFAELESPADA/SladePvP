package net.helix.pvp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import com.besaba.revonline.pastebinapi.Pastebin;
import com.besaba.revonline.pastebinapi.impl.factory.PastebinFactory;
import com.besaba.revonline.pastebinapi.response.Response;

public class Checker {
    public static Connection con = null;

    public static ConsoleCommandSender sc = Bukkit.getConsoleSender();

    private static Checker instance;

    public static Checker getInstance() {
        return instance;
    }

    public Checker() {
        instance = this;
    }

    


    protected String getIpTabela(String IpDaHost) {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("SELECT * FROM `checker` WHERE `ip` = ?");
            stm.setString(1, IpDaHost);
            ResultSet rs = stm.executeQuery();
            if (rs.next())
                return rs.getString("ip");
            return "";
        } catch (SQLException e) {
            return "";
        }
    }

    public static List<String> getKeys() {
        PreparedStatement stm = null;
        List<String> tops = new ArrayList<>();
        try {
            stm = con.prepareStatement("SELECT `keyServer` FROM `checker`");
            ResultSet rs = stm.executeQuery();
            while (rs.next())
                tops.add(rs.getString("keyServer"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tops;
    }

    public boolean checkKey() {
    	final PastebinFactory factory = new PastebinFactory();
    	final Pastebin pastebin = factory.createPastebin("JIt0qy3w_R3EAmR9qfi71DsAE9PkQjUx");
    	final String pasteKey = "a0gwpfQj";
    	final Response<String> pasteResponse = pastebin.getRawPaste(pasteKey);
    	if (pasteResponse.hasError()) {
    	  System.out.println("ERROR CHECKING IPS");
    	  return false;
    	}
    	if (!pasteResponse.get().toString().contains(getIpLocalHost() + ":" + Bukkit.getPort())) {
    	  System.out.println(pasteResponse.get().toString());
      	  System.out.println("YOUR LICENCE IS INVALID");
      	System.out.println("YOUR IP ADRESS: " + getIpLocalHost() + ":" + Bukkit.getPort());
      	 System.out.println("CONTACT THE DEVELOPER Rafael Melo#0001");
      	System.out.println("AND PROVIDES HIM WITH YOU IP ADRESS AND PORT");
      	  return false;
      	}
    	
		return true;
    }


                
 


    protected String getIpLocalHost() {
        try {
            return (new BufferedReader(new InputStreamReader((new URL("http://checkip.amazonaws.com")).openStream())))
                    .readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
package net.helix.pvp;



	import java.util.Random;
	import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
	import org.bukkit.Sound;
	import org.bukkit.command.Command;
	import org.bukkit.command.CommandExecutor;
	import org.bukkit.command.CommandSender;
	import org.bukkit.entity.Player;
	import org.bukkit.event.EventHandler;
	import org.bukkit.event.Listener;
	import org.bukkit.event.inventory.InventoryClickEvent;
	import org.bukkit.inventory.Inventory;
	import org.bukkit.inventory.ItemStack;
	import org.bukkit.inventory.meta.ItemMeta;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.core.bukkit.account.provider.PlayerPvP;

	public class Caixas
	  implements Listener, CommandExecutor
	{
	  public static ItemStack vidro;
	  public static ItemStack fly;
	  public static ItemMeta flymeta;
	  public static ItemMeta vidrometa;
	  public static ItemStack vidro2;
	  public static ItemMeta vidro2meta;
	  public static ItemStack tag;
	  public static ItemMeta tagmeta;
	  public static ItemStack resetkdr;
	  public static ItemMeta resetkdrmeta;
	  public static ItemStack todososkits;
	  public static ItemMeta todososkitsmeta;
	  public static ItemStack randomkit;
	  public static ItemMeta randomkitmeta;
	  public static ItemStack randomkit3;
	  public static ItemMeta randomkit3meta;
	  public static ItemStack randomkit2;
	  public static ItemMeta randomkit2meta;
	  
	  public Caixas() {}

	  @EventHandler
	  public void warps(InventoryClickEvent e)
	  {
	    if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null))
	    {
	      Inventory inv = e.getInventory();
	      Player p = (Player)e.getWhoClicked();
	      if (inv.getTitle().equals("§8Crates"))
	      {
	        p.playSound(p.getLocation(), Sound.LEVEL_UP, 5.0F, 5.0F);
	        e.setCancelled(true);
	        p.closeInventory();
	      }
	      HelixPlayer playerData = HelixBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
	      if (e.getCurrentItem().isSimilar(todososkits)) {
	        if (playerData.getPvp().getCoins() < 50000)
	        {
	          p.sendMessage( "You dont have enought money");
	        }
	        else
	        {
	          playerData.getPvp().getCoins();
	          
	          playerData.getPvp().removeCoins( 50000);
	          p.sendMessage( "§7Voce Comprou §aTodos Os Kits§7 Aproveite");
	          Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add kit.*");
	          
	        }
	      }
	      if (e.getCurrentItem().isSimilar(fly)) {
	        if (playerData.getPvp().getCoins() < 7000)
	        {
	          p.sendMessage( "You dont have enought money");
	        }
	        else
	        {
	          playerData.getPvp().getCoins();
	          
	          playerData.getPvp().removeCoins( 7000);
	          p.sendMessage( "Comprou §aFly§7 Aproveite");
	          Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add kitpvp.fly");
	          
	        }
	      }
	      if (e.getCurrentItem().isSimilar(tag)) {
	        if (playerData.getPvp().getCoins() < 4000)
	        {
	          p.sendMessage( "You dont have enought money");
	        }
	        else
	        {
	          playerData.getPvp().getCoins();
	          
	          playerData.getPvp().removeCoins( 4000);
	          p.sendMessage( "§7Voce Comprou §aTag MC§7 Aproveite");
	          Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add tag.mc");
	          
	        }
	      }
	      if (e.getCurrentItem().isSimilar(randomkit2)) {
	        if (playerData.getPvp().getCoins() < 5000)
	        {
	          p.sendMessage("§a§lCRATE §fNo sufficient funds");
	        }
	        else
	        {
	        	playerData.getPvp().removeCoins( 5000);
	          
	          randomprata(p);
	        }
	      }
	      if (e.getCurrentItem().isSimilar(randomkit3)) {
	        if (playerData.getPvp().getCoins() < 8000)
	        {
	          p.sendMessage("§a§lCRATE §fNo sufficient funds");
	        }
	        else
	        {
	        	 playerData.getPvp().removeCoins( 8000);
	          
	          randomouro(p);
	        }
	      }
	      if (e.getCurrentItem().isSimilar(randomkit)) {
	        if (playerData.getPvp().getCoins() < 3000)
	        {
	          p.sendMessage("§a§lCRATE §fNo sufficient funds");
	        }
	        else
	        {
	          playerData.getPvp().removeCoins(3000);
	          randombronze(p);
	        }
	      }
	    }
	  }
	  
	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
	    if (!(sender instanceof Player)) {
	      return true;
	    }
	    Player p = (Player)sender;
	    if (cmd.getName().equalsIgnoreCase("crates"))
	    {
	      Inventory lojadeoutros = Bukkit.createInventory(p, 27, "§8Crates");
	      
	      vidro = new ItemStack(Material.THIN_GLASS, 1, (short)14);
	      vidrometa = vidro.getItemMeta();
	      vidrometa.setDisplayName("§cCrates");
	      vidro.setItemMeta(vidrometa);
	      
	      vidro2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)13);
	      vidro2meta = vidro2.getItemMeta();
	      vidro2meta.setDisplayName("§6Crates");
	      vidro2.setItemMeta(vidro2meta);
	      
	      randomkit = new ItemStack(Material.getMaterial(336));
	      randomkitmeta = randomkit.getItemMeta();
	      randomkitmeta.setDisplayName("§6Bronze Crate §2Coins >> §a3000");
	      randomkit.setItemMeta(randomkitmeta);
	      
	      randomkit2 = new ItemStack(Material.IRON_INGOT);
	      randomkit2meta = randomkit2.getItemMeta();
	      randomkit2meta.setDisplayName("§7Silver Crate §2Coins >> §a5000");
	      randomkit2.setItemMeta(randomkit2meta);
	      
	      randomkit3 = new ItemStack(Material.GOLD_INGOT);
	      randomkit3meta = randomkit3.getItemMeta();
	      randomkit3meta.setDisplayName("§eGold Crate §2Coins >> §a8000");
	      randomkit3.setItemMeta(randomkit3meta);
	      for (int i = 0; i != 27; i++)
	      {
	        lojadeoutros.setItem(i, vidro);
	        lojadeoutros.setItem(11, randomkit);
	        lojadeoutros.setItem(13, randomkit2);
	        lojadeoutros.setItem(15, randomkit3);
	        lojadeoutros.setItem(10, vidro2);
	        lojadeoutros.setItem(12, vidro2);
	        lojadeoutros.setItem(9, vidro2);
	        lojadeoutros.setItem(14, vidro2);
	        lojadeoutros.setItem(16, vidro2);
	        lojadeoutros.setItem(17, vidro2);
	      }
	      p.openInventory(lojadeoutros);
	      return true;
	    }
	    return true;
	  }
	  
	  public void randombronze(Player p)
	  {
	    Random r = new Random();
	    int o = r.nextInt(14);
	    HelixPlayer playerData = HelixBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
	    PlayerPvP p3 = playerData.getPvp();
	    Bukkit.broadcastMessage(ChatColor.GREEN + p.getName() + " OPENED A BRONZE CRATE!");
	    p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
	    if (o == 0)
	    {
	      p.sendMessage("§a§lCRATE §fYou received the kit Ninja");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.ninja");
	    }
	    else if (o == 1)
	    {
	      p.sendMessage("§a§lCRATES §fYou received §a500§f Coins");
	      p3.addCoins(500);
	    }
	    else if (o == 2)
	    {
	      p.sendMessage("§a§lCAIXA  §fYou received §a600§f Coins");
	      p3.addCoins(600);
	    }
	    else if (o == 3)
	    {
	      p.sendMessage("§a§lCRATE §fYou win the kit Barbarian");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.barbarian");
	    }
	    else if (o == 4)
	    {
	      p.sendMessage("§a§lCRATE  §fYou win the kit Endermage");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.endermage");
	    }
	    else if (o == 5)
	    {
	      p.sendMessage("§a§lCRATE §fYou win the Kit Thresh and §a500§f Coins");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.thresh");
	      p3.addCoins( 500);
	    }
	    else if (o == 6)
	    {
	      p.sendMessage("§a§lCRATE §fYou win the kit sonic");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.sonic");
	      
	    }
	    else if (o == 7)
	    {
	      p.sendMessage("§a§lCRATE §fYou win the kit Anchor");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.anchor");
	    }
	    else if (o == 8)
	    {
	      p.sendMessage("§a§lCRATE §fYou win §a1000§f Coins");
	      p3.addCoins(1000);
	    }
	    else if (o == 9)
	    {
	      p.sendMessage("§a§lCRATE §fYou win §a900§f Coins");
	      p3.addCoins(900);
	    }
	    else if (o == 10)
	    {
	      p.sendMessage("§a§lCRATE §fYou win 300 XP");
	      p3.addXP(300);
	    }
	    else if (o == 11)
	    {
	      p.sendMessage("§a§lCRATE §fYou win the secondary kit stomper");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit2.stomper");
	    }
	    else if (o == 12)
	    {
	      p.sendMessage("§a§lCRATE §fYou win the kit snail");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.snail");
	    }
	      else if (o == 13)
		    {
		      p.sendMessage("§a§lCRATE §cYou win nothing");
		      p.sendMessage("§c§lMore luck next time!");
		      
	      if ((o == 7) || 
	        (o == 8) || 
	        (o == 9) || 
	        (o == 10) || 
	        (o == 12)) {}
	    }
	  }
	  
	  public void randomprata(Player p)
	  {
	    Random r = new Random();
	    Bukkit.broadcastMessage(ChatColor.GREEN + p.getName() + " OPENED A SILVER CRATE!");
	    p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
	    HelixPlayer playerData = HelixBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
	    PlayerPvP p3 = playerData.getPvp();
	    int o = r.nextInt(16);
	    if (o == 0)
	    {
	      p.sendMessage("§a§lCRATE §fYou received kit Grappler");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.grappler");
	    }
	    else if (o == 1)
	    {
	      p.sendMessage("§a§lCRATE §fYou win §a7000§f Coins");
	      p3.addCoins(7000);
	    }
	    else if (o == 2)
	    {
	      p.sendMessage("§a§lCRATE §fYou win §a6000§f Coins");
	      p3.addCoins(6000);
	    }
	    else if (o == 3)
	    {
	      p.sendMessage("§a§lCRATE §fYou win the kit gladiator");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.gladiator");
	    }
	    else if (o == 4)
	    {
	      p.sendMessage("§a§lCRATE §fYou win permission to talk in colours and doublexp/doublecoins");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.doublexp");
	    }
	    else if (o == 5)
	    {
	      p.sendMessage("§a§lCRATE §fYou win kit Deshfire and §a500§f Coins");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.deshfire");
	      p3.addCoins(500);
	    }
	    else if (o == 6)
	    {
	      p.sendMessage("§a§lCRATE §f§lYou win kit Viper and §a1000§f Coins");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.viper");
	      p3.addCoins(500);
	    }
	    else if (o == 7)
	    {
	        p.sendMessage("§a§lCRATE §f§lYou win kit Gladiator");
	    	 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.gladiator");
		    
	    }
	    else if (o == 8)
	    {
	        p.sendMessage("§a§lCRATE §f§lYou win kit MilkMan");
	    	 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.milkman");
	    }
	    else if (o == 9)
	    {
	        p.sendMessage("§a§lCRATE §f§lYou win kit secondary Gladiator");
	    	 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit2.gladiator");
	    }
	    else if (o == 10)
	    {
	        p.sendMessage("§a§lCRATE §f§lYou win kit Meteor");
	    	 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.meteor");
	    }
	    else if (o == 11)
	    {
	    	 p.sendMessage("§a§lCRATE §f§lYou win kit Sonic and Deshfire");
	    	 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.sonic");
	    	 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.deshfire");	    }
	    else if (o == 12)
	    {
	    	 p.sendMessage("§a§lCRATE §f§lYou win kit Anchor");
	    	 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.anchor");
	      
	    }
	    else if (o == 13)
	    {
	    	 p.sendMessage("§a§lCRATE §f§lYou win kit Barbarian");
	    	 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.barbarian");
	    }
	    else if (o == 14)
	    {
	    	 p.sendMessage("§a§lCRATE §f§lYou win 550XP");
p3.addXP(550);
	    }
	    else if (o == 15)
	    {
	    	 p.sendMessage("§a§lCRATE §f§lYou win 750XP");
	    	 p3.addXP(750);
	    }
	    else if (o == 16)
	    {
	    	 p.sendMessage("§a§lCRATE §f§lYou win 300XP");
	    	 p3.addXP(300);
	    }
	    else if (o == 17)
	    {
	      p.sendMessage("§a§lCRATE §f§l You win Kit Monk");
	    }
	    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.monk");
	    
	  
	    
	    if ((o != 7) && 
	      (o != 8) && 
	      (o != 9) && 
	      (o != 10) && 
	      (o != 11) && 
	      (o != 12) && 
	      (o != 13)) {}
	  }
	  
	  public void randomouro(Player p)
	  {
	    Random r = new Random();
	    int o = r.nextInt(17);
	    HelixPlayer playerData = HelixBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
	    PlayerPvP p3 = playerData.getPvp();
	    Bukkit.broadcastMessage(ChatColor.YELLOW + p.getName() + " OPENED A GOLD CRATE!");
	    p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
	    if (o == 0)
	    {
	      p.sendMessage("§a§lCRATE §fYou win kit secondary WaterBender");
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit2.waterbender");
	    }
	    else if (o == 1)
	    {
		      p.sendMessage("§a§lCRATE §fYou win kit secondary Anchor");
		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit2.anchor");
	    }
	    else if (o == 2)
	    {
		      p.sendMessage("§a§lCRATE §fYou win kit secondary Leech");
		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit2.leech");
	    }
	    else if (o == 3)
	    {
		      p.sendMessage("§a§lCRATE §fYou win permission to talk in colours and doublexp/doublecoins");
		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.doublexp");
	    }
	    else if (o == 4)
	    {
		      p.sendMessage("§a§lCRATE §fYou win all primary kits for 1 week");
		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission settemp kombo.kit.* true 7d");
	    }
	    else if (o == 5)
	    {
	      p.sendMessage("§a§lCRATE §fYou win 900XP");
	      p3.addXP(900);
	    }
	    else if (o == 6)
	    {
	    	p.sendMessage("§a§lCRATE §fYou win all secondary kits for 1 week");
		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission settemp kombo.kit2.* true 7d");
	    }
	    else if (o == 7)
	    {
	      p.sendMessage("§a§lCRATE §fYou win the secondary kit NEO");

	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit2.neo");
	    }
	    else if (o == 8)
	    {
	    	 p.sendMessage("§a§lCRATE §fYou win the secondary kit Avatar");

		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit2.avatar");
	    }
	    else if (o == 9)
	    {
	    	 p.sendMessage("§a§lCRATE §fYou win the secondary kit Boxer");

		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit2.boxer");
	    }
	    else if (o == 10)
	    {
	    	 p.sendMessage("§a§lCRATE §fYou win the secondary kit Ninja");

		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit2.ninja");
	    }
	    else if (o == 11)
	    {
	    	 p.sendMessage("§a§lCRATE §fYou win the secondary kit Ninja");

		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit2.ninja");
	    }
	    else if (o == 12)
	    {
	    	 p.sendMessage("§a§lCRATE §fYou win the kit Timelord");

		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.timelord");
	    }
	    else if (o == 13)
	    {
	    	 p.sendMessage("§a§lCRATE §fYou win the secondary kit Grappler");

		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit2.grappler");
	    }
	    else if (o == 14)
	    {
	    	 p.sendMessage("§a§lCRATE §fYou win the kit Fisherman");

		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set kombo.kit.fisherman");
	    }
	    else if (o == 15)
	    {
	      p.sendMessage("§a§lYou win 6000 of XP");
	      p3.addXP(6000);
	      
	    }
	    else if (o == 16)
	    {
	    	p.sendMessage("§a§lYou win 4000 of XP");
		      p3.addXP(4000);
	      
	    }
	    else if (o == 17)
	    {
	      p.sendMessage("§a§lYou win a permanent Group Winner!");
	      
	      
	      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " parent add winner");
	      
	      
	    }
	    else if (o == 18)
	    {
		      p.sendMessage("§a§lYou win the tag Iron (Only the tag, no perks)!");
		      
		      
		      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set helix.tag.iron");
	      if ((o == 7) || 
	        (o == 8) || 
	        (o == 9) || 
	        (o == 10) || 
	        (o == 11) || 
	        (o == 12) || 
	        (o == 13) ||
	        (o == 14) ||
	        (o == 15) ||
	        (o == 16) ||
	        (o == 17) ||
	        (o == 18)) {}
	    }
	  }
	}


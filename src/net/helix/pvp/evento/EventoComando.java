package net.helix.pvp.evento;



import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.helix.core.bukkit.util.AdminUtil;
import net.helix.pvp.command.VanishUtil;
import net.helix.pvp.kit.Habilidade;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.helix.pvp.warp.HelixWarp;


public class EventoComando implements CommandExecutor {

    private static void sendHelp(Player player) {
        if (player.hasPermission("kombo.cmd.evento")) {
            player.sendMessage("§5§lKITPVP §7- §eEvent system");
            player.sendMessage(" ");
            player.sendMessage("§e/event join §7- §fJoin a event.");
            player.sendMessage("§e/event leave §7- §fLeave the event.");
            player.sendMessage("§e/event spec §7- §fSpectate the event.");
            player.sendMessage(" ");
            player.sendMessage("§e/event build §7- §fToggles build.");
            player.sendMessage("§e/event cleararena §7- §fClear the arena.");
            player.sendMessage("§e/event damage §7- §fToggle the no pvp damage.");
            player.sendMessage("§e/event effect <effect/clear> <amplifier> <seconds> <player/all>§7- §fAdd effects to the event players.");
            player.sendMessage("§e/event explain <event> §7- §fExplain a event automatically.");
            player.sendMessage("§e/event kick <player> §7- §fKick a player from the event.");
            player.sendMessage("§e/event participantes §7- §fList all players in the event.");
            player.sendMessage("§e/event pvp §7- §fToggle the pvp damage.");
            player.sendMessage("§e/event setspecloc §7- §fSete a localização que os espectadores aparecerão.");
            player.sendMessage("§e/event skit <player/all> §7- §fSet the event kit.");
            player.sendMessage("§e/event specs §7- §fEnable/Disable spectators.");
            player.sendMessage("§e/event start §7- §fStart a event.");
            player.sendMessage("§e/event stop §7- §fStop a event.");
            player.sendMessage("§e/event toggle §7- §fEnable/Disable players joining in a occouring event.");
            player.sendMessage("§e/event tpall §7- §fTeleport all event players to you.");
            player.sendMessage("§e/event tpto <evento> §7- §fTeleport all players to predefined locations.");
            player.sendMessage("§e/event whitelist <add/remove/list> <player> §7- §fRelease players to join the event one by one.");
            player.sendMessage(" ");
        } else {
            player.sendMessage("§5§lKITPVP §7- §eEvent system");
            player.sendMessage(" ");
            player.sendMessage("§e/event join §7- §fJoin a event.");
            player.sendMessage("§e/event leave §7- §fLeave the event.");
            player.sendMessage("§e/event spec §7- §fSpectate the event.");
            player.sendMessage(" ");
        }
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players, No console!");
            return true;
        }
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("event")) {
            if (args.length == 0) {
                sendHelp(player);
                return false;
            }

            if (!player.hasPermission("kombo.cmd.evento")) {
                if (!EventoUtils.evento) {
                    player.sendMessage("§cThe event room is closed.");
                    return true;
                }
                switch (args[0].toLowerCase()) {
                    case "join":
                        if (EventoUtils.game.contains(player.getName())) {
                            player.sendMessage("§cYou are already on event.");
                            return true;
                        }
                        if (!HelixWarp.SPAWN.hasPlayer(player.getName())) {
                            player.sendMessage("§cYou need to be on spawn to join the event.");
                            player.sendMessage("§cWrite /spawn.");
                            return true;
                        }
                        if (VanishUtil.has(player.getName()) || AdminUtil.has(player.getName())) {
                        	player.sendMessage("§cLeave the admin or vanish before entering the event.");
                
                            return true;
                        }
                        if (!EventoUtils.tp) {
                            if (EventoUtils.whitelist.contains(player.getUniqueId())) {
     
                                player.teleport(EventoUtils.mainArena);
                                player.sendMessage("§aYou joined by event whitelist");
                                EventoUtils.whitelist.remove(player.getUniqueId());
                                player.getInventory().clear();
                                KitManager.getPlayer(player.getName()).removeKit();
                                KitManager2.getPlayer(player.getName()).removekit2();
                                Habilidade.removeAbility(player);
                            } else {
                                player.sendMessage("§cThe event room is closed for now. But can open at any time");
                            }
                            return true;
                        }
                        EventoUtils.setEvento(true, player);
               
                        player.setAllowFlight(false);
                        player.teleport(EventoUtils.mainArena);
                        player.getInventory().clear();
                        player.getInventory().setArmorContents(null);
                        player.sendMessage("§aYou joined the event.");
                        KitManager.getPlayer(player.getName()).removeKit();
                        Habilidade.removeAbility(player);
                        break;
                    case "leave":
                        if (!EventoUtils.game.contains(player.getName())) {
                            player.sendMessage("§cYou are not in event.");
                            return true;
                        }
                        EventoUtils.setEvento(false, player); // OLHA DC
                        HelixWarp.SPAWN.send(player);
                        player.sendMessage("§cYou leave the event.");
                        break;
                    case "spec":
                        if (EventoUtils.game.contains(player.getName())) {
                            player.sendMessage("§cYou are in the event.");
                            return true;
                        }
                        if (!(KitManager.getPlayer(player.getName()).hasKit()) || Habilidade.ContainsAbility(player)) {
                            player.sendMessage("§cRemove your kit to join the event.");
                            return true;
                        }
                        if (!EventoUtils.specs) {
                            player.sendMessage("§cSpectators is disabled for now.");
                            return true;
                        }
                        player.teleport(EventoUtils.specLoc);
                        player.sendMessage("§aVocê está espectando o evento.");
                        break;
                    default:
                        player.sendMessage("§cCant find the option §e" + args[0] + "§c.");
                        break;
                }
            }
            else {
                if (args[0].equalsIgnoreCase("start")) {
                    if (EventoUtils.evento) {
                        player.sendMessage("§cThe event room is already open.");
                        return true;
                    }
                    EventoUtils.evento = true;
                    EventoUtils.damage = false;
                    player.sendMessage("§aYou open the event room.");
                    EventoUtils.whitelist.add(player.getUniqueId());
                    EventoUtils.setEvento(true, player);
                    player.teleport(EventoUtils.mainArena);
                    player.getInventory().clear();
                    player.sendMessage("§aYou joined the event.");
                    KitManager.getPlayer(player.getName()).removeKit();
                    KitManager2.getPlayer(player.getName()).removekit2();
                    Bukkit.broadcastMessage("§cA event started.");
                    Bukkit.broadcastMessage("§cUse /event join");
                    Bukkit.broadcastMessage("§cTo join.");
                    for (Player p : Bukkit.getOnlinePlayers()) {
                    	p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 1f);
                    }
                    player.setGameMode(GameMode.CREATIVE);
                }
                else if (args[0].equalsIgnoreCase("stop")) {
                    if (!EventoUtils.evento) {
                        player.sendMessage("§cThe event room is already closed.");
                        return true;
                    }
                    EventoUtils.evento = false;
                    player.sendMessage("§aYou closed the event room.");
                    EventoUtils.getEventoPlayers().forEach(p -> {
                    	net.helix.pvp.evento.EventoUtils.setEvento(false, p);
                        HelixWarp.SPAWN.send(p);
                        p.sendMessage("§cThe event ended.");
                        p.chat("/spawn");
                        p.getActivePotionEffects().forEach(ef -> p.removePotionEffect(ef.getType()));
                    });
                    EventoUtils.resetEventoClass();
                }
                else {
                    if (!EventoUtils.evento) {
                        player.sendMessage("§cThe event room is not open.");
                        return true;
                    }
                    switch (args[0].toLowerCase()) {
                        case "join":
                            if (EventoUtils.game.contains(player.getName())) {
                                player.sendMessage("§cYou are already in the event.");
                                return true;
                            }
                            if (!HelixWarp.SPAWN.hasPlayer(player.getName())) {
                                player.sendMessage("§cYou need to be in the spawn to join.");
                                player.sendMessage("§cWrite /spawn.");
                                return true;
                            }
                            if (VanishUtil.has(player.getName()) || AdminUtil.has(player.getName())) {
                            	player.sendMessage("§cLeave the admin or vanish before entering the event.");
                    
                                return true;
                            }
                            if (!EventoUtils.tp) {
                                if (EventoUtils.whitelist.contains(player.getUniqueId())) {
                                    EventoUtils.setEvento(true, player);
                                    player.teleport(EventoUtils.mainArena);
                                    player.sendMessage("§aYou joined the event by the whitelist");
                                    player.getInventory().clear();
                                    EventoUtils.whitelist.remove(player.getUniqueId());
                                    KitManager.getPlayer(player.getName()).removeKit();
                                    KitManager2.getPlayer(player.getName()).removekit2();
                                } else {
                                    player.sendMessage("§cThe event room is closed. Wait to it open again.");
                                }
                                return true;
                            }
                            EventoUtils.setEvento(true, player);
                            player.teleport(EventoUtils.mainArena);
                            player.getInventory().clear();
                            player.sendMessage("§aYou joined the event.");
                            KitManager.getPlayer(player.getName()).removeKit();
                            KitManager2.getPlayer(player.getName()).removekit2();
                            break;
                        case "leave":
                            if (!EventoUtils.game.contains(player.getName())) {
                                player.sendMessage("§cYou are not in the event.");
                                return true;
                            }
                            EventoUtils.setEvento(false, player);
                            HelixWarp.SPAWN.send(player);
                            player.sendMessage("§cYou left the event.");
                            break;
                        case "spec":
                            if (EventoUtils.game.contains(player.getName())) {
                                player.sendMessage("§cYou are in the event.");
                                return true;
                            }
                            if (!(KitManager.getPlayer(player.getName()).hasKit() && Habilidade.ContainsAbility(player))) {
                                player.sendMessage("§cYou cant have kits selected to join the event.");
                                return true;
                            }
                            if (!EventoUtils.specs) {
                                player.sendMessage("§cSpectators disabled.");
                                return true;
                            }
                            player.teleport(EventoUtils.specLoc);
                            player.sendMessage("§aYou are now spectating.");
                            break;
                        case "build":
                            if (EventoUtils.build) {
                                player.sendMessage("§cYou disabled the build on the event.");
                                EventoUtils.build = false;
                            } else {
                                player.sendMessage("§aYou enabled the build on the event.");
                                EventoUtils.build = true;
                            }
                            break;
                        case "cleararena":
                            EventoUtils.clearBlocks();
                            player.sendMessage("§aYou cleared the event arena. §7(Remove the obsidians, cobblestones, water and lava manually)");
                            player.sendMessage("§aTIP: use //drain to remove the liquids more quickly.");
                            break;
                        case "damage":
                            if (EventoUtils.damage) {
                                player.sendMessage("§cYou disabled the damage. §7(Remember to disable the §4pvp§7 too)");
                                EventoUtils.damage = false;
                            } else {
                                player.sendMessage("§aYou enabled the damage. §7(Remember to enable the §4pvp§7 too)");
                                EventoUtils.damage = true;
                            }
                            break;
                        case "effect":
                            if (args.length == 2) {
                                if (args[1].equalsIgnoreCase("clear")) {
                                    EventoUtils.getEventoPlayers().forEach(p -> p.getActivePotionEffects().forEach(ef -> p.removePotionEffect(ef.getType())));
                                    player.sendMessage("§aYou cleared all players active effects on the event.");
                                    return false;
                                }
                                else player.sendMessage("§cUse /evento effect <effect/clear> <amplifier> <seconds> <player/all>");
                                return false;
                            }
                            if (args.length < 5) {
                                sendHelp(player);
                                return true;
                            }
                            PotionEffectType potionEffectType = EventoUtils.getPotionEffectTypeByName(args[1]);
                            if (potionEffectType == null) {
                                player.sendMessage("§cInvalid effect.");
                                return true;
                            }
                            int amplif;
                            int secs;
                            try {
                                amplif = Integer.parseInt(args[2]);
                                secs = Integer.parseInt(args[3]);
                            } catch (NumberFormatException exception) {
                                sendHelp(player);
                                return true;
                            }
                            if (args[4].equalsIgnoreCase("all")) {
                                EventoUtils.getEventoPlayers().forEach(p -> p.addPotionEffect(new PotionEffect(potionEffectType, secs * 20, amplif - 1)));
                                player.sendMessage("§aEffect §e"+potionEffectType.getName() + " " + amplif + " §aapplied to all event players for §e" + secs + " seconds§a.");
                                return false;
                            } else {
                                Player target = Bukkit.getPlayer(args[4]);
                                if (target == null) {
                                    player.sendMessage("§cWe cant find the player §e" + args[4] + "§c.");
                                    return true;
                                }
                                if (target == player) {
                                    player.sendMessage("§cYou cant give effects to yourself.");
                                }
                                if (!EventoUtils.game.contains(target.getName())) {
                                    player.sendMessage("§cThis player is not on a event.");
                                    return true;
                                }
                                target.addPotionEffect(new PotionEffect(potionEffectType, secs * 20, amplif));
                                player.sendMessage("§aEfeito §e"+potionEffectType.getName() + " " + amplif + " §aaplicado para §e" + target.getName() + " §apor §e" + secs + " segundos§a.");
                            }
                            break;
                        case "explain":
                            if (args.length < 2) {
                                player.sendMessage("§cChoose a event to explain");
                                return true;
                            }
                            EventoType evento = EventoType.getEventoByName(args[1]);
                            if (evento == null) {
                                player.sendMessage("§cEvent option invalid.");
                                return true;
                            }
                            player.sendMessage("§aStarting the explanation of the event §e" + evento.getName().toUpperCase() + "§a...");
                            EventoType.explicarEvento(evento);
                            break;
                        case "kick":
                            if (args.length < 2) {
                                sendHelp(player);
                                return true;
                            }
                            Player target = Bukkit.getPlayer(args[1]);
                            if (target == null) {
                                player.sendMessage("§cWe cant find the player §e" + args[1] + "§c.");
                                return true;
                            }
                            if (target == player) {
                                player.sendMessage("§cYou cant kick yourself.");
                        
                                return true;
                            }
                            if (!EventoUtils.game.contains(target.getName())) {
                                player.sendMessage("§cThis player is not on the event.");
                                return true;
                            }
                           net.helix.pvp.evento.EventoUtils.setEvento(false, player);
                           net.helix.pvp.evento.EventoUtils.setEvento(false, player);
                           HelixWarp.SPAWN.send(player);
                            target.sendMessage("§cYou get kicked from the event.");
                            player.sendMessage("§aYou kicked §e" + target.getName() + " §afrom the evento.");
                            break;
                        case "players":
                            int size = EventoUtils.getEventoPlayersNames().size();
                            player.sendMessage("§aThe event has §e" + size + " players§a, whom are: §7" + StringUtils.join(EventoUtils.getEventoPlayersNames(), "§a, §7"));
                            break;
                        case "pvp":
                            if (EventoUtils.pvp) {
                                player.sendMessage("§cYou disabled the pvp. §7(Remember to disable the §4damage§7)");
                                EventoUtils.pvp = false;
                            } else {
                                player.sendMessage("§aYou enabled the pvp. §7(Remember to enable §4damage§7)");
                                EventoUtils.pvp = true;
                            }
                            break;
                        case "setspecloc":
                            EventoUtils.specLoc = player.getLocation();
                            player.sendMessage("§aSpectators location seted.");
                            break;
                        case "skit":
                            if (args.length < 2) {
                                sendHelp(player);
                                return true;
                            }
                            if (args[1].equalsIgnoreCase("all")) {
                                EventoUtils.getEventoPlayers().forEach(p -> {
                                    if (p == player) return;
                                    p.closeInventory();
                                    p.getInventory().setArmorContents(player.getInventory().getArmorContents());
                                    p.getInventory().setContents(player.getInventory().getContents());
                                    p.sendMessage("§aYou received the event kit.");
                                });
                                player.sendMessage("§aAll players received the event kit.");
                                return false;
                            }
                            Player t = Bukkit.getPlayer(args[1]);
                            if (t == null) {
                                player.sendMessage("§cWe cant find the player §e" + args[1] + "§c.");
                                return true;
                            }
                            t.closeInventory();
                            t.getInventory().setArmorContents(player.getInventory().getArmorContents());
                            t.getInventory().setContents(player.getInventory().getContents());
                            t.sendMessage("§cYou received the event kit.");
                            player.sendMessage("§aThe player §e" + t.getName() + " §areceived the event kit.");
                            break;
                        case "specs":
                            if (!EventoUtils.specs) {
                                if (EventoUtils.specLoc == null) {
                                    player.sendMessage("§cAntes de habilitar espectadores escolha a localização de spawn deles.");
                                    return true;
                                }
                                EventoUtils.specs = true;
                                player.sendMessage("§aVocê habilitou os espectadores.");
                            } else {
                                EventoUtils.specs = false;
                                player.sendMessage("§cVocê desabilitou os espectadores.");
                            }
                            break;
                        case "toggle":
                            if (!EventoUtils.tp) {
                                player.sendMessage("§aYou enabled the joins of new players in the event.");
                                EventoUtils.tp = true;
                            } else {
                                player.sendMessage("§cYou disabled the joins of new players in the event.");
                                EventoUtils.tp = false;
                            }
                            break;
                        case "tpall":
                            EventoUtils.getEventoPlayers().forEach(p -> p.teleport(player.getLocation()));
                            player.sendMessage("§aYou teleported all players that is in the event to you.");
                            break;
                        case "tpto":
                            if (args.length < 2) {
                                player.sendMessage("§cChoose a event type.");
                                return true;
                            }
                            EventoType ev = EventoType.getEventoByName(args[1]);
                            if (ev == null) {
                                player.sendMessage("§cEvent option invalid.");
                                return true;
                            }
                            EventoUtils.started = true;
                            EventoUtils.getEventoPlayers().forEach(p -> p.teleport(ev.getLocation()));
                            break;
                        case "whitelist":
                            if (args.length < 3) {
                                if (args[1].equalsIgnoreCase("list")) {
                                    player.sendMessage("§aWhitelisted players in the event: §7" + StringUtils.join(EventoUtils.getWhitelistPlayersNames(), "§a, §7"));
                                    return false;
                                }
                                sendHelp(player);
                                return true;
                            }
                            Player tt = Bukkit.getPlayer(args[2]);
                            if (tt == null) {
                                player.sendMessage("§eWe cant find the player §e" + args[2] + "§c.");
                                return true;
                            }
                            if (args[1].equalsIgnoreCase("add")) {
                                if (EventoUtils.whitelist.contains(tt.getUniqueId())) {
                                    player.sendMessage("§cThe player §e" + tt.getName() + " §cis already on whitelist.");
                                    return true;
                                }
                                EventoUtils.whitelist.add(tt.getUniqueId());
                                player.sendMessage("§aThe player §e" + tt.getName() + " §aget added on event whitelist.");
                                return true;
                            } else if (args[1].equalsIgnoreCase("remove")) {
                                if (!EventoUtils.whitelist.contains(tt.getUniqueId())) {
                                    player.sendMessage("§cThe player §e" + tt.getName() + " §cis not on whitelist.");
                                    return true;
                                }
                                EventoUtils.whitelist.remove(tt.getUniqueId());
                                player.sendMessage("§aThe player §e" + tt.getName() + " §agets §cremoved §afrom event whitelist.");
                                return false;
                            } else {
                                player.sendMessage("§cWe cant find this option.");
                                return false;
                            }
                        default:
                            sendHelp(player);
                            player.sendMessage("§cWe cant find the command option §e" + args[0] + "§c.");
                            break;
                    }
                }
            }
        }
        return false;
    }
}
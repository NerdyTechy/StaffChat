package me.nerdytechy.staffchat.listeners;

import me.nerdytechy.staffchat.StaffChat;
import me.nerdytechy.staffchat.api.AdminChatMessageSent;
import me.nerdytechy.staffchat.api.BuildChatMessageSent;
import me.nerdytechy.staffchat.api.DeveloperChatMessageSent;
import me.nerdytechy.staffchat.api.StaffChatMessageSent;
import me.nerdytechy.staffchat.utils.ChatChannel;
import me.nerdytechy.staffchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    private static final StaffChat plugin = StaffChat.getInstance();

    public PlayerChatListener(StaffChat plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public static void onChat(AsyncPlayerChatEvent e){
        Player plr = e.getPlayer();
        String message = e.getMessage();

        if (StaffChat.playerChatChannels.containsKey(plr)){

            if (!(StaffChat.playerChatChannels.get(plr) == ChatChannel.Public)){
                e.setCancelled(true);
            }

            switch (StaffChat.playerChatChannels.get(plr)){
                case Staff:
                    StaffChatMessageSent staffChatMessageSentEvent = new StaffChatMessageSent(plr, message);
                    Bukkit.getPluginManager().callEvent(staffChatMessageSentEvent);
                    if (staffChatMessageSentEvent.isCancelled()){
                        return;
                    }

                    for (Player current : Bukkit.getOnlinePlayers()){
                        if (current.hasPermission("staffchat.staffchat") || (current.hasPermission("staffchat.*"))){
                            current.sendMessage(Utils.chat(plugin.getConfig().getString("chat-format.staff-chat").replace("%player%", plr.getName()).replace("%message%", message)));
                        }
                    }
                    return;
                case Admin:
                    AdminChatMessageSent adminChatMessageSentEvent = new AdminChatMessageSent(plr, message);
                    Bukkit.getPluginManager().callEvent(adminChatMessageSentEvent);
                    if (adminChatMessageSentEvent.isCancelled()){
                        return;
                    }

                    for (Player current : Bukkit.getOnlinePlayers()){
                        if (current.hasPermission("staffchat.adminchat") || (current.hasPermission("staffchat.*"))){
                            current.sendMessage(Utils.chat(plugin.getConfig().getString("chat-format.admin-chat").replace("%player%", plr.getName()).replace("%message%", message)));
                        }
                    }
                    return;
                case Build:
                    BuildChatMessageSent buildChatMessageSentEvent = new BuildChatMessageSent(plr, message);
                    Bukkit.getPluginManager().callEvent(buildChatMessageSentEvent);
                    if (buildChatMessageSentEvent.isCancelled()){
                        return;
                    }

                    for (Player current : Bukkit.getOnlinePlayers()){
                        if (current.hasPermission("staffchat.buildchat") || (current.hasPermission("staffchat.*"))){
                            current.sendMessage(Utils.chat(plugin.getConfig().getString("chat-format.build-chat").replace("%player%", plr.getName()).replace("%message%", message)));
                        }
                    }
                    return;
                case Dev:
                    DeveloperChatMessageSent devChatMessageSentEvent = new DeveloperChatMessageSent(plr, message);
                    Bukkit.getPluginManager().callEvent(devChatMessageSentEvent);
                    if (devChatMessageSentEvent.isCancelled()){
                        return;
                    }

                    for (Player current : Bukkit.getOnlinePlayers()){
                        if (current.hasPermission("staffchat.devchat") || (current.hasPermission("staffchat.*"))){
                            current.sendMessage(Utils.chat(plugin.getConfig().getString("chat-format.dev-chat").replace("%player%", plr.getName()).replace("%message%", message)));
                        }
                    }
                    return;
            }
        }

    }

}

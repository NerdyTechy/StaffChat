package me.nerdytechy.staffchat.commands;

import me.nerdytechy.staffchat.StaffChat;
import me.nerdytechy.staffchat.api.AdminChatMessageSent;
import me.nerdytechy.staffchat.utils.ChatChannel;
import me.nerdytechy.staffchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class AdminChatCommand implements CommandExecutor {

    private final StaffChat plugin;

    public AdminChatCommand(StaffChat plugin){
        this.plugin = plugin;
        plugin.getCommand("adminchat").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("staffchat.adminchat") && !sender.hasPermission("staffchat.*")){
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("messages.no-permission")));
            return false;
        }

        if (args.length == 0){
            if (StaffChat.playerChatChannels.containsKey(sender)){
                if (!(StaffChat.playerChatChannels.get(sender) == ChatChannel.Admin)){
                    StaffChat.playerChatChannels.remove(sender);
                    StaffChat.playerChatChannels.put(sender, ChatChannel.Admin);
                    sender.sendMessage(Utils.chat(plugin.getConfig().getString("messages.channel-admin")));
                } else{
                    StaffChat.playerChatChannels.remove(sender);
                    StaffChat.playerChatChannels.put(sender, ChatChannel.Public);
                    sender.sendMessage(Utils.chat(plugin.getConfig().getString("messages.channel-public")));
                }
            } else{
                StaffChat.playerChatChannels.put(sender, ChatChannel.Admin);
                sender.sendMessage(Utils.chat(plugin.getConfig().getString("messages.channel-admin")));
            }
            return true;
        }

        AdminChatMessageSent event = new AdminChatMessageSent(sender, Arrays.toString(args));

        Bukkit.getPluginManager().callEvent(event);

        if (event.isCancelled()){
            return false;
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (String s : args){
            stringBuilder.append(s);
            stringBuilder.append(" ");
        }

        String message = stringBuilder.toString();

        for (Player current : Bukkit.getOnlinePlayers()){
            if (current.hasPermission("staffchat.adminchat") || current.hasPermission("staffchat.*")){
                current.sendMessage(Utils.chat(plugin.getConfig().getString("chat-format.admin-chat")
                        .replace("%player%", sender.getName())
                        .replace("%message%", message)));
            }
        }

        return false;
    }
}

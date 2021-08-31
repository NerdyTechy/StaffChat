package me.nerdytechy.staffchat.commands;

import me.nerdytechy.staffchat.StaffChat;
import me.nerdytechy.staffchat.api.BuildChatMessageSent;
import me.nerdytechy.staffchat.utils.ChatChannel;
import me.nerdytechy.staffchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class BuildChatCommand implements CommandExecutor {

    private final StaffChat plugin;

    public BuildChatCommand(StaffChat plugin){
        this.plugin = plugin;
        plugin.getCommand("buildchat").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("staffchat.buildchat") && !sender.hasPermission("staffchat.*")){
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("messages.no-permission")));
            return false;
        }

        if (args.length == 0){
            if (StaffChat.playerChatChannels.containsKey(sender)){
                if (!(StaffChat.playerChatChannels.get(sender) == ChatChannel.Build)){
                    StaffChat.playerChatChannels.remove(sender);
                    StaffChat.playerChatChannels.put(sender, ChatChannel.Build);
                    sender.sendMessage(Utils.chat(plugin.getConfig().getString("messages.channel-build")));
                } else{
                    StaffChat.playerChatChannels.remove(sender);
                    StaffChat.playerChatChannels.put(sender, ChatChannel.Public);
                    sender.sendMessage(Utils.chat(plugin.getConfig().getString("messages.channel-public")));
                }
            } else{
                StaffChat.playerChatChannels.put(sender, ChatChannel.Build);
                sender.sendMessage(Utils.chat(plugin.getConfig().getString("messages.channel-build")));
            }
            return true;
        }

        BuildChatMessageSent event = new BuildChatMessageSent(sender, Arrays.toString(args));

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
            if (current.hasPermission("staffchat.buildchat") || current.hasPermission("staffchat.*")){
                current.sendMessage(Utils.chat(plugin.getConfig().getString("chat-format.build-chat")
                        .replace("%player%", sender.getName())
                        .replace("%message%", message)));
            }
        }

        return false;
    }
}

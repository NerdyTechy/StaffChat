package me.nerdytechy.staffchat.commands;

import me.nerdytechy.staffchat.StaffChat;
import me.nerdytechy.staffchat.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StaffChatReloadCommand implements CommandExecutor {

    private final StaffChat plugin;

    public StaffChatReloadCommand(StaffChat plugin){
        this.plugin = plugin;
        plugin.getCommand("staffchatreload").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("staffchat.reload") && !sender.hasPermission("staffchat.*")){
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("messages.no-permission")));
            return false;
        }

        plugin.reloadConfig();
        sender.sendMessage(Utils.chat("&aThe plugin has reloaded successfully."));
        return true;
    }
}

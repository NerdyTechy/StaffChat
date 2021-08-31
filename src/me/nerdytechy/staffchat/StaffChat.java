package me.nerdytechy.staffchat;

import me.nerdytechy.staffchat.commands.*;
import me.nerdytechy.staffchat.listeners.PlayerChatListener;
import me.nerdytechy.staffchat.listeners.PlayerJoinListener;
import me.nerdytechy.staffchat.utils.ChatChannel;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class StaffChat extends JavaPlugin {

    private static StaffChat instance;
    public static HashMap<CommandSender, ChatChannel> playerChatChannels = new HashMap<>();

    @Override
    public void onEnable() {

        int pluginId = 12661;
        Metrics metrics = new Metrics(this, pluginId);

        new UpdateChecker(this, 95859);

        setInstance(this);

        saveDefaultConfig();

        new AdminChatCommand(this);
        new BuildChatCommand(this);
        new DeveloperChatCommand(this);
        new StaffChatCommand(this);
        new StaffChatReloadCommand(this);

        new PlayerChatListener(this);
        new PlayerJoinListener(this);
    }

    public static StaffChat getInstance(){
        return instance;
    }
    public static void setInstance(StaffChat instance){
        StaffChat.instance = instance;
    }

}

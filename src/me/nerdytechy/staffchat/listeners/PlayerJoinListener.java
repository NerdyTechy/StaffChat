package me.nerdytechy.staffchat.listeners;

import me.nerdytechy.staffchat.StaffChat;
import me.nerdytechy.staffchat.UpdateChecker;
import me.nerdytechy.staffchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final StaffChat plugin;

    public PlayerJoinListener(StaffChat plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player plr = e.getPlayer();

        if (!plugin.getConfig().getBoolean("update-notification")){
            return;
        }

        if (plr.hasPermission("staffchat.*") || plr.isOp()){

            if (UpdateChecker.updateAvailable){
                plr.sendMessage(Utils.chat("&c&lStaffChat &r&8> &fUpdate Available!"));
                plr.sendMessage(Utils.chat("&c&lStaffChat &r&8> &7Download from: &fhttps://www.spigotmc.org/resources/95859/"));
            }
            return;


        }

    }

}

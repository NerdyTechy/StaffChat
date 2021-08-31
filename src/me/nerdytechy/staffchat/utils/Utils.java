package me.nerdytechy.staffchat.utils;

import org.bukkit.ChatColor;

public class Utils {

    public static String chat(String s){
        return String.valueOf(ChatColor.translateAlternateColorCodes('&', s));
    }

    public static String chatBungee(String s){
        return String.valueOf(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', s));
    }

}

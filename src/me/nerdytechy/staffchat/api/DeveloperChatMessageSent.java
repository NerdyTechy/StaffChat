package me.nerdytechy.staffchat.api;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DeveloperChatMessageSent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private static String message;
    private static CommandSender sender;
    private static boolean isCancelled;

    public DeveloperChatMessageSent(CommandSender sender, String message){
        DeveloperChatMessageSent.sender = sender;
        DeveloperChatMessageSent.message = message;
        isCancelled = false;
    }

    public static CommandSender getSender(){
        return sender;
    }

    public static String getMessage(){
        return message;
    }

    public static boolean isCancelled() {
        return isCancelled;
    }

    public static void setCancelled(boolean isCancelled) {
        DeveloperChatMessageSent.isCancelled = isCancelled;
    }


    /**
     *  Bukkit Event Methods
     */

    @Override
    public HandlerList getHandlers(){
        return HANDLERS;
    }

    public static HandlerList getHandlerList(){
        return HANDLERS;
    }

}

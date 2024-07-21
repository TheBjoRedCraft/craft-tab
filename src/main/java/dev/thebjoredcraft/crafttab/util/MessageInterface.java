package dev.thebjoredcraft.crafttab.util;

import dev.thebjoredcraft.craftcloudsingle.CraftCloud;
import dev.thebjoredcraft.crafttab.CraftTablist;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class MessageInterface {
    public Component deserialize(String message){
        return MiniMessage.miniMessage().deserialize(message);
    }

    public void send(Audience player, String message){
        player.sendMessage(this.deserialize(CraftTablist.getInstance().getPrefix() + message));
    }

    public void send(ConsoleCommandSender player, String message){
        player.sendMessage(this.deserialize(CraftTablist.getInstance().getPrefix() + message));
    }

    public void send(Audience player, String message, boolean noPrefix){
        player.sendMessage(this.deserialize(message));
    }

    public void send(Audience player, String message, Player target){
        player.sendMessage(this.deserialize(CraftTablist.getInstance().getPrefix() + message.replace("%target%", target.getName())));
    }
    public void send(Audience player, String message, CommandSender target){
        player.sendMessage(this.deserialize(CraftTablist.getInstance().getPrefix() + message.replace("%target%", target.getName())));
    }

    public void broadcast(String message){
        Bukkit.getOnlinePlayers().forEach(player -> this.send(player, message));
    }

    public void broadcast(String message, Player target){
        Bukkit.getOnlinePlayers().forEach(player -> this.send(player, message, target));
    }

    public String hover(String text, String hoverText){
        return "<hover:show_text:'" + hoverText + "'>" + text + "</hover>";
    }

    public String suggest(String text, String command){
        return "<click:suggest_command:'"+ command + "'>" + text + "</click>";
    }

    public String command(String text, String command){
        return "<click:run_command:'" + command + "'>" + text + "</click>";
    }

    public static Component deserializeStatic(String message){
        return MiniMessage.miniMessage().deserialize(message);
    }

    public static void sendStatic(Audience player, String message){
        player.sendMessage(deserializeStatic(CraftTablist.getInstance().getPrefix() + message));
    }

    public static void sendStatic(Audience player, String message, boolean noPrefix){
        player.sendMessage(deserializeStatic(message));
    }

    public static void sendStatic(Audience player, String message, Player target){
        player.sendMessage(deserializeStatic(CraftTablist.getInstance().getPrefix() + message.replace("%target%", target.getName())));
    }
    public static void sendStatic(Audience player, String message, CommandSender target){
        player.sendMessage(deserializeStatic(CraftTablist.getInstance().getPrefix() + message.replace("%target%", target.getName())));
    }

    public static void broadcastStatic(String message){
        Bukkit.getOnlinePlayers().forEach(player -> sendStatic(player, message));
    }

    public static void broadcastStatic(String message, Player target){
        Bukkit.getOnlinePlayers().forEach(player -> sendStatic(player, message, target));
    }

    public static String hoverStatic(String text, String hoverText){
        return "<hover:show_text:'" + hoverText + "'>" + text + "</hover>";
    }

    public static String suggestStatic(String text, String command){
        return "<click:suggest_command:'"+ command + "'>" + text + "</click>";
    }

    public static String commandStatic(String text, String command){
        return "<click:run_command:'" + command + "'>" + text + "</click>";
    }

    public String gradient(String from, String to, String message){
        return "<gradient:" + from + ":" + to + ">" + message + "</gradient>";
    }
}
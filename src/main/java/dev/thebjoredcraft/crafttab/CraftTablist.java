package dev.thebjoredcraft.crafttab;

import dev.thebjoredcraft.crafttab.manager.TablistManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class CraftTablist extends JavaPlugin {
    @Getter
    public static TablistManager tablistManager;
    @Getter
    public static CraftTablist instance;

    @Override
    public void onEnable() {
        this.saveConfig();

        instance = this;
        tablistManager = new TablistManager();
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static String replacePlaceholder(Player player, String message){
        return message
                .replace("%player%", player.getName())
                .replace("%server%", player.getServer().getName())
                .replace("%world%", player.getWorld().getName())
                .replace("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()))
                .replace("%max-online%", String.valueOf(Bukkit.getMaxPlayers()))
                .replace("%ip%", Bukkit.getIp())
                .replace("%version%", Bukkit.getMinecraftVersion())
                .replace("%ping%", String.valueOf(player.getPing()))
                .replace("%health%", String.valueOf(player.getHealth()))
                .replace("%health-scale%", String.valueOf(player.getHealthScale()))
                .replace("%max-health%", String.valueOf(player.getMaxHealth()))
                .replace("%level%", String.valueOf(player.getLevel()))
                .replace("%time%", String.valueOf(player.getPlayerTime()));
    }
}

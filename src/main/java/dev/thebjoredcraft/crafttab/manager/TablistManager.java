package dev.thebjoredcraft.crafttab.manager;

import dev.thebjoredcraft.crafttab.CraftTablist;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TablistManager {
    String header = CraftTablist.getPlugin(CraftTablist.class).getConfig().getString("TablistHeader", "");
    String footer = CraftTablist.getPlugin(CraftTablist.class).getConfig().getString("TablistFooter", "");
    String nameSeparator = CraftTablist.getPlugin(CraftTablist.class).getConfig().getString("NameSeparator", "");

    public void update(Player player){
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        LuckPerms lp = LuckPermsProvider.get();
        Group group = lp.getGroupManager().getGroup(LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(player).getPrimaryGroup());

        String prefix = lp.getPlayerAdapter(Player.class).getUser(player).getCachedData().getMetaData().getPrefix();
        String suffix = lp.getPlayerAdapter(Player.class).getUser(player).getCachedData().getMetaData().getSuffix();

        if(group == null){
            return;
        }

        Team team = board.getTeam(group.getName());

        if(team == null){
            team = board.registerNewTeam(group.getName());
            if(prefix != null){
                team.prefix(MiniMessage.miniMessage().deserialize(prefix + nameSeparator));
            }

            if(suffix != null){
                team.suffix(MiniMessage.miniMessage().deserialize(" " + suffix));
            }
        }

        this.updateTeams(player, team);

        player.sendPlayerListHeaderAndFooter(MiniMessage.miniMessage().deserialize(header.replace("%ping%", String.valueOf(player.getPing())).replace("%server%", player.getServer().getName())), MiniMessage.miniMessage().deserialize(footer.replace("%online%", String.valueOf(Bukkit.getOnlinePlayers().size())).replace("%max%", String.valueOf(Bukkit.getMaxPlayers())).replace("%tps%", new DecimalFormat("#.00").format(Bukkit.getTPS()[0]) + " " + new DecimalFormat("#.00").format(Bukkit.getTPS()[1]) +  " " + new DecimalFormat("#.00").format(Bukkit.getTPS()[2]))));

        if(group.getCachedData().getMetaData().getPrefix() == null){
            player.playerListName(MiniMessage.miniMessage().deserialize(player.getName()));
        }else {
            player.playerListName(MiniMessage.miniMessage().deserialize(prefix + nameSeparator + player.getName()));
        }
    }

    public List<Team> getLPGroupTeams(){
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        LuckPerms lp = LuckPermsProvider.get();
        List<Team> teams = new ArrayList<>();

        for(Group group : lp.getGroupManager().getLoadedGroups()){
            Team team = board.getTeam(group.getName());

            if(team == null){
                team = board.registerNewTeam(group.getName());
                teams.add(team);
            }
        }
        return teams;
    }

    public void updateTeams(Player player, Team finalteam){
        this.getLPGroupTeams().forEach(currentTeam -> {
            if(currentTeam.hasPlayer(player)){
                currentTeam.removePlayer(player);
            }
        });

        finalteam.addPlayer(player);
    }
}

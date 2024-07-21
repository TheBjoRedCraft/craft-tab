package dev.thebjoredcraft.crafttab.listener;

import dev.thebjoredcraft.crafttab.CraftTablist;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.event.EventBus;
import net.luckperms.api.event.group.GroupDataRecalculateEvent;
import org.bukkit.Bukkit;

public class LuckPermsListener {
    public LuckPermsListener(CraftTablist plugin, LuckPerms luckPerms) {
        EventBus eventBus = luckPerms.getEventBus();

        eventBus.subscribe(plugin, GroupDataRecalculateEvent.class, this::onGroupDataRecalculate);
    }

    private void onGroupDataRecalculate(GroupDataRecalculateEvent event) {
        Bukkit.getOnlinePlayers().forEach(player -> CraftTablist.getTablistManager().update(player));
    }
}

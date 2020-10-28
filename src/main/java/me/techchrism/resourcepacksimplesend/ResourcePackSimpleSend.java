package me.techchrism.resourcepacksimplesend;

import me.techchrism.resourcepackapi.ResourcePackAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ResourcePackSimpleSend extends JavaPlugin implements Listener
{
    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(this, this);
    }
    
    @EventHandler(ignoreCancelled = true)
    private void onPlayerJoin(PlayerJoinEvent event)
    {
        ResourcePackAPI.recompilePack(event.getPlayer());
    }
    
    @EventHandler
    public void resourcePackEvent(PlayerResourcePackStatusEvent event)
    {
        if(event.getStatus() == PlayerResourcePackStatusEvent.Status.DECLINED)
        {
            Bukkit.getScheduler().runTaskLater(this, () ->
                    event.getPlayer().kickPlayer("You must accept the resource pack"), 20L);
        }
    }
}

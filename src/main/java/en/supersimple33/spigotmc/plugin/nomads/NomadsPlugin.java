package en.supersimple33.spigotmc.plugin.nomads;

import en.supersimple33.spigotmc.plugin.nomads.commands.*;
import en.supersimple33.spigotmc.plugin.nomads.event.MovementListener;
import org.bukkit.plugin.java.JavaPlugin;

public class NomadsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("enrich").setExecutor(new EnrichCommand());
        getServer().getPluginManager().registerEvents(new MovementListener(this), this);
        getLogger().info("Added the 'enrich' command.");
    }
}
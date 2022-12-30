package en.supersimple33.spigotmc.plugin.nomads;

import en.supersimple33.spigotmc.plugin.nomads.event.MovementListener;

import java.util.List;

// import com.mysql.cj.jdbc.MysqlDataSource;

import org.bukkit.plugin.java.JavaPlugin;

public class NomadsPlugin extends JavaPlugin {

    List<String> disallowedWorlds;

    @Override
    public void onEnable() {
        // Config setup
        this.saveDefaultConfig();
        disallowedWorlds = this.getConfig().getStringList("disallowed-worlds");
        getLogger().info("Disallowed worlds: " + disallowedWorlds);

        // Storage setup


        // Goofy Stuff
        getServer().getPluginManager().registerEvents(new MovementListener(this), this);
        getLogger().info("Added the 'enrich' command.");

        // Meat and Potatoes
        new MainLoop(this).runTaskTimer(this, 0, 20);
    }
}

// Lookup for each chunk whether a given player has a value for their time spent in that chunk
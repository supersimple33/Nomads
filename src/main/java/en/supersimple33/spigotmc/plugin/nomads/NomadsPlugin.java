package en.supersimple33.spigotmc.plugin.nomads;

import en.supersimple33.spigotmc.plugin.nomads.event.MovementListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

public class NomadsPlugin extends JavaPlugin {

    private final String dataFile = getDataFolder() + File.separator + "data";

    List<String> disallowedWorlds;
    HashMap<LocalDifficulty, Integer> localDifficulties;

    @Override
    public void onEnable() {
        super.onEnable(); // needed?
        // localDifficulties = new HashMap<>();
        // LocalDifficulty ld = new LocalDifficulty(0, 0, UUID.randomUUID());
        // localDifficulties.put(ld, 8);

        // File setup
        this.saveDefaultConfig();
        getLogger().info("Loading local difficulties...");
        this.readData();

        // Grab data from config
        disallowedWorlds = this.getConfig().getStringList("disallowed-worlds");
        getLogger().info("Disallowed worlds: " + disallowedWorlds);

        // Goofy Stuff
        getServer().getPluginManager().registerEvents(new MovementListener(this), this);
        getLogger().info("Added the 'enrich' command.");

        // Meat and Potatoes
        new MainLoop(this).runTaskTimer(this, 0, 20);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        getLogger().info("Saving Local Difficulties");
        this.saveData();
    }

    private void readData() {
        try (
            FileInputStream fileStream = new FileInputStream(dataFile);
            ObjectInputStream inputStream = new ObjectInputStream(fileStream)
        ) {
            localDifficulties = (HashMap<LocalDifficulty, Integer>) inputStream.readObject(); // TODO: add catchable type check here
        } catch (FileNotFoundException e) {
            getLogger().info("No data file was found. Creating one now.");
            localDifficulties = new HashMap<>();
            this.saveData();
        } catch (IOException | ClassNotFoundException e) { // does ClassNotFound run?
            e.printStackTrace();
        }
    }

    // Save the localDifficulties HashMap to a file
    private void saveData() {
        try (
            FileOutputStream fileStream = new FileOutputStream(dataFile); 
            ObjectOutputStream objStream = new ObjectOutputStream(fileStream)
        ) {
            objStream.writeObject(localDifficulties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Lookup for each chunk whether a given player has a value for their time spent in that chunk
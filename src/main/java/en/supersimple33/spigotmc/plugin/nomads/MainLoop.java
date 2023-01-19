package en.supersimple33.spigotmc.plugin.nomads;
import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Player;

public class MainLoop extends BukkitRunnable { // Should pass the logger/plugin?

	private final Logger logger;
	private final List<String> disallowedWorlds;
	private final HashMap<LocalDifficulty, Integer> localDifficulties;
	
	public MainLoop(NomadsPlugin plugin) {
		super();
		this.localDifficulties = plugin.localDifficulties;
		this.logger = plugin.getLogger();
		this.disallowedWorlds = plugin.getConfig().getStringList("disallowed-worlds");
	}

    @Override
    public void run() {
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			Location loc = player.getLocation();
			World world = loc.getWorld();
			// Guard
			if (disallowedWorlds.contains(world.getName()) || world.getDifficulty() == Difficulty.PEACEFUL) { // Should we tick on peaceful worlds? I guess not.
				continue;
			}
			Chunk chunk = loc.getChunk();

			// Update local difficulty
			LocalDifficulty ld = new LocalDifficulty(chunk.getX(), chunk.getZ(), player.getUniqueId());
			int old = localDifficulties.getOrDefault(ld, 0);
			localDifficulties.put(ld, old + 1);

			// print chunk coords
			Bukkit.broadcastMessage("Player " + player.getDisplayName() + " is in chunk " + chunk.getX() + ", " + chunk.getZ() + " difficulty was " + old);
		}
    }
}

// Bukkit.broadcastMessage(player.getDisplayName());

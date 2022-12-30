package en.supersimple33.spigotmc.plugin.nomads;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Player;

public class MainLoop extends BukkitRunnable { // Should pass the logger/plugin?

	private final Logger logger;
	private final List<String> disallowedWorlds;
	
	public MainLoop(NomadsPlugin plugin) {
		super();
		this.logger = plugin.getLogger();
		this.disallowedWorlds = plugin.getConfig().getStringList("disallowed-worlds");
	}

    @Override
    public void run(){
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			Location loc = player.getLocation();
			World world = loc.getWorld();
			// Guard
			if (disallowedWorlds.contains(world.getName())) {
				continue;
			}

			Chunk chunk = loc.getChunk();
			//print chunk coords
			Bukkit.broadcastMessage("Player " + player.getDisplayName() + " is in chunk " + chunk.getX() + ", " + chunk.getZ());
		}
    }
}

// Bukkit.broadcastMessage(player.getDisplayName());

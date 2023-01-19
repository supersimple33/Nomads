package en.supersimple33.spigotmc.plugin.nomads;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

// We can either use a Class or a Record, both will work nearly the same its just a question of performance

public record LocalDifficulty(int x, int z, UUID playerUUID) implements Serializable {
	// static int loaded = 0;
	public LocalDifficulty {
		// loaded++;
	}
}

// public class LocalDifficulty {
// 	private final int x; // not mutable after start
// 	private final int z;
// 	private final UUID playerUUID;

// 	private final long chunkID;
// 	private final int hash;

// 	private int timeSpent; //decay?

// 	public LocalDifficulty(int x, int z, UUID playerUUID) {
// 		this.x = x;
// 		this.z = z;
// 		this.playerUUID = playerUUID;
// 		this.timeSpent = 0;
// 		this.chunkID = (long) x & 0xffffffffL | ((long) z & 0xffffffffL) << 32;

// 		// Not in love with this hash
// 		this.hash = Objects.hash(chunkID, Objects.hash(playerUUID.getLeastSignificantBits(), playerUUID.getMostSignificantBits()));
// 	}

// 	@Override
// 	public int hashCode() {
// 		return this.hash; // collide if x, z, playerUUID are the same. timeSpent is ignored
// 	}

// 	@Override
// 	public boolean equals(Object obj) {
// 		if (this == obj) {
// 			return true;
// 		}
// 		if (obj == null) {
// 			return false;
// 		}
// 		if (getClass() != obj.getClass()) {
// 			return false;
// 		}

// 		LocalDifficulty other = (LocalDifficulty) obj;
// 		if (this.x != other.x) {
// 			return false;
// 		}
// 		if (this.z != other.z) {
// 			return false;
// 		}
// 		if (this.playerUUID != other.playerUUID) {
// 			return false;
// 		}
// 		return true;
// 	}
// }

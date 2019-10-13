import com.ericlam.mc.rankcal.PlayerData;

import java.util.Random;
import java.util.UUID;

public class PlayerDataImpl implements PlayerData {

    private UUID uuid;
    private double score;

    public PlayerDataImpl() {
        this.uuid = UUID.randomUUID();
        this.score = new Random().nextInt(1000);
    }

    @Override
    public UUID getPlayerUniqueId() {
        return uuid;
    }

    @Override
    public double getScore() {
        return score;
    }

    @Override
    public int compareTo(PlayerData o) {
        return Double.compare(this.score, o.getScore());
    }
}

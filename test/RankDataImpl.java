import com.ericlam.mc.rankcal.RankData;

public class RankDataImpl implements RankData {

    private String id;
    private String display;
    private int ladder;
    private double origScore = 0.0;
    private double nScore = 0.0;

    public RankDataImpl(int ladder, String id, String display) {
        this.id = id;
        this.display = display;
        this.ladder = ladder;
    }

    @Override
    public String getId() {
        return id;
    }

    public double getOrigScore() {
        return origScore;
    }

    public void setOrigScore(double origScore) {
        this.origScore = origScore;
    }

    public double getnScore() {
        return nScore;
    }

    public void setnScore(double nScore) {
        this.nScore = nScore;
    }

    @Override
    public String getRankDisplay() {
        return display;
    }

    @Override
    public int compareTo(RankData o) {
        return Integer.compare(this.ladder, ((RankDataImpl)o).ladder);
    }
}

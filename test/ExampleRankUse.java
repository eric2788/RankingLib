import com.ericlam.mc.rankcal.PlayerData;
import com.ericlam.mc.rankcal.RankData;
import com.ericlam.mc.rankcal.RankDataManager;
import com.ericlam.mc.rankcal.RankLibAPI;
import com.ericlam.mc.rankcal.implement.RankingLib;
import com.ericlam.mc.rankcal.utils.AdvMath;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class ExampleRankUse {
    public static void main(String[] args) {

        launchServer();

        List<PlayerData> dataList = new LinkedList<>();
        for (int i = 0; i < 300; i++) { //simulate with 300 fake players
            dataList.add(new PlayerDataImpl());
        }

        String grade = "ABCDEFG";

        TreeSet<RankData> rankData = new TreeSet<>();
        for (int i = 0; i < grade.length(); i++) {
            char g = grade.charAt(i);
            rankData.add(new RankDataImpl(grade.length() - i, g + "", "Level " + g));
        }

        RankLibAPI api = RankingLib.getRankAPI();

        api.registerCalculator("z-score", (playerData, ladderRanks, arrayData) -> {
            double v = playerData.getScore();
            double result = AdvMath.round(2, (v - arrayData.getMean()) / arrayData.getSd());
            int minScore = (int) -Math.floor((int) (ladderRanks.size() / 2));
            int scoreIndex = ((int) result) - minScore;
            if (scoreIndex < 0) {
                return ladderRanks.get(0);
            } else if (scoreIndex > ladderRanks.size()) {
                return ladderRanks.get(ladderRanks.size() - 1);
            }
            RankDataImpl rank = (RankDataImpl) ladderRanks.get(scoreIndex);
            rank.setnScore(result);
            rank.setOrigScore(v);
            return rank;
        });


        api.registerCalculator("min-max", (playerData, ladderRanks, arrayData) -> {
            double min = Arrays.stream(arrayData.getScores()).reduce(Math::min).orElseThrow(() -> new IllegalStateException("cannot find min score"));
            double max = Arrays.stream(arrayData.getScores()).reduce(Math::max).orElseThrow(() -> new IllegalStateException("cannot find max score"));
            double v = playerData.getScore();
            double result = (v - min) / (max - min) * (ladderRanks.size() - 1) + 0;
            RankDataImpl rank = (RankDataImpl) ladderRanks.get((int) Math.round(result));
            rank.setnScore(result);
            rank.setOrigScore(v);
            return rank;
        });

        RankDataManager manager = api.getFactory().addPlayers(dataList).registerRanks(rankData).build();

        manager.doCalculate("z-score").whenComplete((map, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
            }
        });

        System.out.println("Data updated");
        System.out.println("Size: " + manager.getRankDataMap().size());
        manager.getRankDataMap().forEach((id, data) -> {
            RankDataImpl rank = (RankDataImpl) data;
            System.out.println(id + ": " + data.getRankDisplay() + "(" + rank.getOrigScore() + ")(" + rank.getnScore() + ")");
        });


    }

    private static void launchServer() {
        RankingLib lib = new RankingLib();
        lib.onLoad();
    }

}

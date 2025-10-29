package com.waduclay.kata;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class FakeScoreBoard implements ScoreBoard{
    private final HashMap<Player, Score> scores = new HashMap<>();
    private Score player1Score;
    private Score player2Score;
    @Override
    public Map<Player, Score> getScores() {
        return scores;
    }

    @Override
    public void updateScore(Player player) {
        Score score = scores.getOrDefault(player, Score.LOVE);
        int newScoreOrdinal;
        if (score.equals(Score.THREE_POINTS)){
            //hdghd

            System.out.println("newScoreOrdinal = " + score);
        }
        if (!isDeuce() && score.equals(Score.THREE_POINTS)) {
            newScoreOrdinal = score.getPoint() + 2;
        } else {
            newScoreOrdinal = score.getPoint() + 1;
        }

        Score newScore = Score.values()[newScoreOrdinal];
        scores.put(player, newScore);
    }

    @Override
    public void init(Player player1, Player player2) {
        scores.put(player1, Score.LOVE);
        scores.put(player2, Score.LOVE);
    }

    @Override
    public boolean isDeuce() {
        List<Score> values = scores.values().stream().toList();
        return values.get(0).equals(values.get(1));
    }
}

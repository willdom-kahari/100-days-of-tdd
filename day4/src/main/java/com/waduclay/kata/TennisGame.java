package com.waduclay.kata;


import java.util.Map;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class TennisGame {
    private final ScoreBoard scoreBoard;

    public TennisGame(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public TennisGame start(Player player1, Player player2) {
        scoreBoard.init(player1, player2);
        return this;
    }

    public Map<Player, Score> scores() {
        return scoreBoard.getScores();
    }

    public void score(Player sam) {
        scoreBoard.updateScore(sam);
    }

    public boolean isDeuce() {
        return scoreBoard.isDeuce();
    }
}

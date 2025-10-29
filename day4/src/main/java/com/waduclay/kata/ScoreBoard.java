package com.waduclay.kata;


import java.util.Map;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public interface ScoreBoard {
    Map<Player, Score> getScores();
    void updateScore(Player player);
    void init(Player player1, Player player2);
    boolean isDeuce();
}

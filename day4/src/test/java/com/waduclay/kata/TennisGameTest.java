package com.waduclay.kata;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
@ExtendWith(MockitoExtension.class)
public class TennisGameTest {


    Player john;
    private TennisGame game;
    private Player sam;

    @BeforeEach
    void setUp() {
        ScoreBoard scoreBoard = new FakeScoreBoard();
        game = new TennisGame(scoreBoard);
        sam = new Player1();
        john = new Player2();
    }


    @Test
    void whenGameStartsAllPlayersMustBeAtLove() {
        // Arrange

        TennisGame current = game.start(sam, john);

        // Act
        Map<Player, Score> scores = current.scores();

        // Assert
        assertThat(scores.get(sam)).isEqualTo(Score.LOVE);


    }

    @Test
    void whenAPlayerScoresTheirScoreMustIncrease() {
        TennisGame current = game.start(sam, john);
        current.score(sam);
        Map<Player, Score> scores = current.scores();
        Score score = scores.get(sam);

        assertThat(score).isEqualTo(Score.POINT);

        boolean isDeuce = current.isDeuce();
        assertThat(isDeuce).isFalse();
    }

    @Test
    void mustResetToDeuceIfAPlayerWith3PointsScoresIfTheOtherHasAnAdvantage(){
        TennisGame current = game.start(sam, john);
        current.score(sam);
        current.score(john);
        current.score(sam);
        current.score(john);
        current.score(sam);
        current.score(john);
        current.score(sam);
        current.score(john);

        Map<Player, Score> scores = current.scores();
        assertThat(scores.get(sam)).isEqualTo(Score.ADVANTAGE);
        assertThat(scores.get(john)).isEqualTo(Score.ADVANTAGE);


    }
}

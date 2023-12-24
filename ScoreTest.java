import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ScoreTest {

    private Score score;
    private Player player1;
    private Player player2;
    private Board board;

    @BeforeEach
    void setUp() {
        score = new Score();
        player1 = new Player(1);
        player2 = new Player(2);
    }

    // Test default constructor
    @Test
    void testDefaultConstructor() {
        int[] scores = score.getScores();
        assertEquals(0, scores[0]);
        assertEquals(0, scores[1]);
        assertEquals(0, score.getMatchLength());
        assertEquals(1, score.getCubeOwner().getPlayerNumber()); // Default cube owner is player no 1
    }

    // Test set and get match length
    @Test
    void testSetAndGetMatchLength() {
        score.setMatchLength(7);
        assertEquals(7, score.getMatchLength());
    }


    // Test doubleAccepted logic
    @Test
    void testDoubleAccepted() {
        score.doubleAccepted(player1, player2);
        assertEquals(2, score.getCubeOwner().getPlayerNumber()); // Cube owner should change to player no 2
    }

}

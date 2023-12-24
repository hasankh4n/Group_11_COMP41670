import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;
    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp() {
        player1 = new Player(1);
        player2 = new Player(2);
        board = new Board(player1, player2);
    }

    // Test initialization of the board
    @Test
    void testBoardInitialization() {
        assertNotNull(board.getScore()); // Ensure score is initialized
        assertEquals(Board.NUM_POINTS, board.pointIndex.size()); // Check if all points are initialized
    }

    // Test getPipCount for players
    @Test
    void testGetPipCount() {
        //pip count for each player should be specific values, defined by starting counters
        int initialPipCountPlayer1 = 167; // =  initial pip count for player 1
        int initialPipCountPlayer2 = 167; // = initial pip count for player 2
        assertEquals(initialPipCountPlayer1, board.getPipCount(player1));
        assertEquals(initialPipCountPlayer2, board.getPipCount(player2));
    }

    // Test if the game is over
    @Test
    void testIsGameOver() {
        // At start game should not be over
        assertFalse(board.isGameOver(player1, player2));
        
    }

  
}

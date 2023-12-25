import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Group Number: 11
 * @author James Duke, Hasan Khan
 * GitHub IDs: jamesduke488, hasankh4n
 * @version 1.1
 */

class PlayerTest {

    // Test Player constructor with player no 1
    @Test
    void testPlayerConstructorNumber1() {
        Player player1 = new Player(1);
        assertEquals(1, player1.getPlayerNumber());
        assertEquals(CounterType.PLAYER1, player1.getCounterType());
        assertEquals(ConsoleColors.WHITE_BACKGROUND, player1.getColour());
        assertNull(player1.getPlayerName());
    }

    // Test Player constructor with player no 2
    @Test
    void testPlayerConstructorNumber2() {
        Player player2 = new Player(2);
        assertEquals(2, player2.getPlayerNumber());
        assertEquals(CounterType.PLAYER2, player2.getCounterType());
        assertEquals(ConsoleColors.RED_BACKGROUND, player2.getColour());
        assertNull(player2.getPlayerName());
    }

    // Test Player constructor with an invalid player number
    @Test
    void testPlayerConstructorInvalidNumber() {
        Player invalidPlayer = new Player(3);
    }

    // Test setting and getting player name
    @Test
    void testSetAndGetPlayerName() {
        Player player = new Player(1);
        player.setPlayerName("Hasan");
        assertEquals("Hasan", player.getPlayerName());
    }
}

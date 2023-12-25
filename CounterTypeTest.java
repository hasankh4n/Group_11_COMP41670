
//tests enum constants are correctly initialized & toString() method behaves as expected
// As enums are fixed, testing doesn't require extensive cases, as such it can be simple as here

/**
 * Group Number: 11
 * @author James Duke, Hasan Khan
 * GitHub IDs: jamesduke488, hasankh4n
 * @version 1.1
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CounterTypeTest {

    // Test PLAYER1 constant
    @Test
    void testPlayer1Constant() {
        assertEquals(ConsoleColors.WHITE + "X", CounterType.PLAYER1.toString());
    }

    // Test PLAYER2 constant
    @Test
    void testPlayer2Constant() {
        assertEquals(ConsoleColors.RED + "O", CounterType.PLAYER2.toString());
    }
}

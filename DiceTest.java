import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiceTest {

    private Dice dice;

    // initializes a new dice object bebfore each test
    @BeforeEach
    void setUp() {
        dice = new Dice();
    }

    // Test to check dice faces are within the valid range (1 to 6)
    @Test
    void testDiceInitialFacesRange() {
        int[] faces = dice.getFaces();
        assertTrue(faces[0] >= 1 && faces[0] <= Dice.NUM_FACES); // Face 1 
        assertTrue(faces[1] >= 1 && faces[1] <= Dice.NUM_FACES); // Face 2 
    }

    // Test to check dice faces are within the valid range AFTER rolling also
    @Test
    void testRollFacesRange() {
        dice.roll();
        int[] faces = dice.getFaces();
        assertTrue(faces[0] >= 1 && faces[0] <= Dice.NUM_FACES); 
        assertTrue(faces[1] >= 1 && faces[1] <= Dice.NUM_FACES); 
    }

    // Test to check if the numMoves is set correctly for a double (when both dice faces are the same)
    @Test
    void testNumMovesForDouble() {
        dice.roll();
        int[] faces = dice.getFaces();
        if (faces[0] == faces[1]) {
            assertEquals(Dice.DOUBLE, dice.getMoves()); // numMoves should be DOUBLE when both faces are the same
        } else {
            assertEquals(1, dice.getMoves()); // 1 in any other case
        }
    }

    // Test to check numMoves is = 1 when the dice faces are not the same
    @Test
    void testNumMovesForNonDouble() {
        int[] faces = dice.getFaces();
        if (faces[0] != faces[1]) {
            assertEquals(1, dice.getMoves()); 
        }
    }

}

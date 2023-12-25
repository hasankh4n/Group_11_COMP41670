import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// These tests for the Display class, with methods that interact with the console would involve testing strategies such as
// 'faking' user inputs and outputs in the testing environment / dependency injection , which is beyond the scope of unit testing 
// As such, this test class is a starting point to implement these features if we were to refactor the Display class to include them

/**
 * Group Number: 11
 * @author James Duke, Hasan Khan
 * GitHub IDs: jamesduke488, hasankh4n
 * @version 1.1
 */

class DisplayTest {

    private Display display;

    @BeforeEach
    void setUp() {
        display = new Display();
    }

    // Testing info() method
    @Test
    void testInfo() {
        // This method prints to the console and does not return a value
        // Testing this method would typically involve capturing console output
    }

   
    // Test the displayCommandNotPossible() method
    @Test
    void testDisplayCommandNotPossible() {
       
    }

    // Test the displayGameOver() method
    @Test
    void testDisplayGameOver() {
        
    }

    // Test the displayQuit() method
    @Test
    void testDisplayQuit() {
        
    }
}

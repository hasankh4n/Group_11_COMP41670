import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    // Testing default constructor
    @Test
    void testDefaultConstructor() {
        Point point = new Point();
        assertTrue(point.getCounters().isEmpty());
    }

    // Testing constructor with initial counters
    @Test
    void testConstructorWithInitialCounters() {
        Counter counter = new Counter(CounterType.PLAYER1);
        Point point = new Point(3, counter);

        assertEquals(3, point.getCounters().size());
        for (Counter c : point.getCounters()) {
            assertEquals(counter, c); // Ensure each counter in the stack is the same as the one provided
        }
    }

    // Testing canTake method
    @Test
    void testCanTake() {
        Point point = new Point();
        Counter counter = new Counter(CounterType.PLAYER1);
        // testing if canTake returns false when the stack is empty:
        assertFalse(point.canTake(counter));
    }

    // Testing getCounters method
    @Test
    void testGetCounters() {
        Point point = new Point();
        Counter counter = new Counter(CounterType.PLAYER1);
        point.getCounters().push(counter);

        assertEquals(1, point.getCounters().size());
        assertEquals(counter, point.getCounters().peek());
    }

    // Testing getSpecCounter method
    @Test
    void testGetSpecCounter() {
        Counter counter1 = new Counter(CounterType.PLAYER1);
        Counter counter2 = new Counter(CounterType.PLAYER2);
        Point point = new Point();
        point.getCounters().push(counter1);
        point.getCounters().push(counter2);

        assertEquals(counter2, point.getSpecCounter(1)); // Index 1 should return the second counter
    }
}

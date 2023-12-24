import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CounterTest {

    // Testing getType method
    @Test
    void testCounterConstructorAndGetType() {
        CounterType type = CounterType.PLAYER1;
        Counter counter = new Counter(type);

        assertEquals(type, counter.getType());
    }

    // Testing toString method
    @Test
    void testToString() {
        Counter counterPlayer1 = new Counter(CounterType.PLAYER1);
        Counter counterPlayer2 = new Counter(CounterType.PLAYER2);

        assertEquals(CounterType.PLAYER1.toString(), counterPlayer1.toString());
        assertEquals(CounterType.PLAYER2.toString(), counterPlayer2.toString());
    }
}

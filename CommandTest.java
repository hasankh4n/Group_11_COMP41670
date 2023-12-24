import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    // TChecking quit command
    @Test
    void testCommandTypeQuit() {
        Command quitCommand = new Command("Q");
        assertTrue(quitCommand.quit()); // Should return true for quit command
        //other command types return false
        assertFalse(quitCommand.roll());
        assertFalse(quitCommand.pip());
        assertFalse(quitCommand.hint());
        assertFalse(quitCommand.offerdouble());
    }

    // checking roll command
    @Test
    void testCommandTypeRoll() {
        Command rollCommand = new Command("R");
        assertTrue(rollCommand.roll()); // Should return true for roll command
        //other command types return false
        assertFalse(rollCommand.quit());
        assertFalse(rollCommand.pip());
        assertFalse(rollCommand.hint());
        assertFalse(rollCommand.offerdouble());
    }

    // checking pip command
    @Test
    void testCommandTypePip() {
        Command pipCommand = new Command("P");
        assertTrue(pipCommand.pip()); // Should return true for pip command
        //other command types return false
        assertFalse(pipCommand.quit());
        assertFalse(pipCommand.roll());
        assertFalse(pipCommand.hint());
        assertFalse(pipCommand.offerdouble());
    }

    // checking hint comand 
    @Test
    void testCommandTypeHint() {
        Command hintCommand = new Command("H");
        assertTrue(hintCommand.hint()); // Should return true for hint command
        //other command types return false
        assertFalse(hintCommand.quit());
        assertFalse(hintCommand.roll());
        assertFalse(hintCommand.pip());
        assertFalse(hintCommand.offerdouble());
    }

    // checking double command
    @Test
    void testCommandTypeDouble() {
        Command doubleCommand = new Command("D");
        assertTrue(doubleCommand.offerdouble()); // Should return true for double command
        // other command types return false
        assertFalse(doubleCommand.quit());
        assertFalse(doubleCommand.roll());
        assertFalse(doubleCommand.pip());
        assertFalse(doubleCommand.hint());
    }

    // Checking input validity
    @Test
    void testIsValidMethod() {
        // Testing valid commands
        assertTrue(Command.isValid("Q"));
        assertTrue(Command.isValid("R"));
        assertTrue(Command.isValid("P"));
        assertTrue(Command.isValid("H"));
        assertTrue(Command.isValid("D"));
        // Testing an invalid command
        assertFalse(Command.isValid("A"));
        // Testing a completely unrelated string
        assertFalse(Command.isValid("XYZ"));
        // Testing a valid pattern as per regex (if applicable)
        assertTrue(Command.isValid("R1Q2"));
    }
}

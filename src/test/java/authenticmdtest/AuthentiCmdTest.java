package authenticmdtest;

import authenticmdtest.implementation.*;
import com.undeadscythes.authenticmd.service.*;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * @author UndeadScythes
 */
public class AuthentiCmdTest {
    private TheProgram program;

    @Before
    public void init() {
        program = new TheProgram();
    }

    @Test
    public void testSetServices() {
        program.setServices(Arrays.asList(new String[]{Help.class.getName()}), true);
        assertEquals(3, program.getServices().size());
    }

    @Test
    public void testCmdExecute() {
        program.setServices(new ArrayList<String>(0), true);
        assertFalse(program.executeCmds("", new String[]{"quit"}));
        assertTrue(program.executeCmds("", new String[]{"help"}));
    }
}

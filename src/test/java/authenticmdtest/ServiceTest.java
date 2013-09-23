package authenticmdtest;

import authenticmdtest.implementation.*;
import com.undeadscythes.authenticmd.service.*;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * @author UndeadScythes
 */
public class ServiceTest  {
    private TheProgram program;

    @Before
    public void init() {
        program = new TheProgram();
    }

    @Test
    public void testHelpRun() {
        program.setServices(Arrays.asList(new String[]{Help.class.getName()}), false);
        assertTrue("run", new Help().run(program, new String[]{""}));
    }

    @Test
    public void testQuitRun() {
        program.setServices(Arrays.asList(new String[]{Quit.class.getName()}), false);
        assertFalse("run", new Quit().run(program, new String[]{""}));
    }
}

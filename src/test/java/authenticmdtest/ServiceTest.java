package authenticmdtest;

import authenticmdtest.implementation.TheProgram;
import com.undeadscythes.authenticmd.exception.TerminationRequest;
import com.undeadscythes.authenticmd.service.Help;
import com.undeadscythes.authenticmd.service.Quit;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
        assertTrue(new Help().run(program, new String[]{""}));
    }

    @Test(expected = TerminationRequest.class)
    public void testQuitRun() throws TerminationRequest {
        program.setServices(Arrays.asList(new String[]{Quit.class.getName()}), false);
        new Quit().run(program, new String[]{""});
    }
}

package authenticmdtest;

import authenticmdtest.implementation.TheProgram;
import com.undeadscythes.authenticmd.exception.TerminationRequest;
import com.undeadscythes.authenticmd.service.Help;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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

    @Test(expected = TerminationRequest.class)
    public void testCmdExecute() throws TerminationRequest {
        program.setServices(new ArrayList<String>(0), true);
        program.executeCmds("", new String[]{"help"});
        program.executeCmds("", new String[]{"quit"});
    }
}

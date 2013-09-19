package authenticmdtest;

import authenticmdtest.implementation.*;
import com.undeadscythes.authenticmd.service.*;
import java.util.*;
import static java.util.Arrays.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * @author UndeadScythes
 */
public class AuthentiCmdTest {
    @Test
    public void testSetServices() {
        TheProgram impl = new TheProgram();
        impl.setServices(asList(new String[]{Help.class.getName()}), true);
        assertEquals("services", 3, impl.getServices().size());
    }

    @Test
    public void testCmdExecute() {
        TheProgram impl = new TheProgram();
        impl.setServices(new ArrayList<String>(0), true);
        assertFalse("false", impl.executeCmds("", new String[]{"quit"}));
        assertTrue("true", impl.executeCmds("", new String[]{"help"}));
    }
}

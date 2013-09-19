package authenticmdtest;

import authenticmdtest.implementation.*;
import com.undeadscythes.authenticmd.service.*;
import static java.util.Arrays.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * @author UndeadScythes
 */
public class ServiceTest  {
    @Test
    public void testHelpRun() {
        final TheProgram impl = new TheProgram();
        impl.setServices(asList(new String[]{Help.class.getName()}), false);
        assertTrue("run", new Help().run(impl, new String[]{""}));
    }

    @Test
    public void testQuitRun() {
        final TheProgram impl = new TheProgram();
        impl.setServices(asList(new String[]{Help.class.getName()}), false);
        assertFalse("run", new Quit().run(impl, new String[]{""}));
    }
}

package authenticmdtest;

import com.undeadscythes.authenticmd.exception.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * @author UndeadScythes
 */
public class ExceptionTest {
    private ServiceNotFoundException exception = new ServiceNotFoundException(new String[]{"test1", "test2"});

    @Test(expected = ServiceNotFoundException.class)
    public void testThrow() throws ServiceNotFoundException {
        assertNotNull("construct", exception);
        throw exception;
    }

    @Test
    public void testCatch() {
        try {
            throw exception;
        } catch (ServiceNotFoundException ex) {
            assertEquals("message", "Cannot find service for command test1 test2.", ex.getMessage());
        }
    }
}

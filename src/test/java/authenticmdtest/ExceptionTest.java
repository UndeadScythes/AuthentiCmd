package authenticmdtest;

import com.undeadscythes.authenticmd.exception.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * @author UndeadScythes
 */
public class ExceptionTest {
    private static final ServiceNotFoundException EXCEPTION = new ServiceNotFoundException(new String[]{"test1", "test2"});

    @Test(expected = ServiceNotFoundException.class)
    public void testThrow() throws ServiceNotFoundException {
        assertNotNull(EXCEPTION);
        throw EXCEPTION;
    }

    @Test
    public void testCatch() {
        try {
            throw EXCEPTION;
        } catch (ServiceNotFoundException ex) {
            assertEquals("Cannot find service for command test1 test2.", ex.getMessage());
        }
    }
}

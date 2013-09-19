package authenticmdtest.implementation;

import com.undeadscythes.authenticmd.*;
import com.undeadscythes.tipscript.*;
import static java.util.logging.Logger.*;

/**
 * @author UndeadScythes
 */
public class TheProgram extends AuthentiCmd {
    public TheProgram() {
        super(System.in, new TipScript(getLogger("test"), ""));
    }
}

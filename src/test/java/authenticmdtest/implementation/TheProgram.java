package authenticmdtest.implementation;

import com.undeadscythes.authenticmd.*;
import com.undeadscythes.tipscript.*;
import java.util.logging.*;

/**
 * @author UndeadScythes
 */
public class TheProgram extends AuthentiCmd {
    public TheProgram() {
        super(System.in, new TipScript(Logger.getLogger("test"), ""));
    }
}

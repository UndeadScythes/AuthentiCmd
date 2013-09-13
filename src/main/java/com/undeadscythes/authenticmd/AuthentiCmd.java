package com.undeadscythes.authenticmd;

import com.undeadscythes.authenticmd.exception.*;
import com.undeadscythes.authenticmd.service.*;
import com.undeadscythes.authenticmd.validator.*;
import com.undeadscythes.tipscript.*;
import java.io.*;
import static java.lang.Class.*;
import static java.util.Collections.*;
import java.util.*;
import static org.apache.commons.lang3.ArrayUtils.*;

/**
 * @author UndeadScythes
 */
public class AuthentiCmd {
    /**
     * Ease of access to command line output specified on construction.
     */
    public final TipScript output;

    private final BufferedReader reader;
    private final List<Service> services = new ArrayList<Service>(1);

    /**
     *
     * @param input
     * @param output
     */
    protected AuthentiCmd(final InputStream input, final TipScript output) {
        reader = new BufferedReader(new InputStreamReader(input));
        this.output = output;
    }

    /**
     *
     * @param serviceClassPaths
     * @param defaultCmds
     */
    public void setServices(final List<String> serviceClassPaths, final boolean defaultCmds) {
        for (String service : serviceClassPaths) {
            try {
                services.add((Service)forName(service).newInstance());
            } catch (ClassNotFoundException ex) {
                output.println("Unimplemented service: " + service);
            } catch (InstantiationException ex) {
                output.println("Cannot instantiate service: " + service);
            } catch (IllegalAccessException ex) {
                output.println("Illegal access: " + service);
            }
        }
        if (defaultCmds) {
            services.add(new Help());
            services.add(new Quit());
        }
    }

    /**
     *
     * @param message
     * @param cmds
     * @return False if execution should escape the response loop
     */
    public boolean executeCmds(final String message, final String[] cmds) {
        output.println(message);
        for (String arg : removeElement(cmds, "")) {
            final String[] args = arg.split(" ");
            try {
                if (!executeCmd(args, "Unrecognized command '" + arg + "'.", arg.length())) return false;
            } catch (ServiceNotFoundException ex) {}
        }
        return true;
    }

    /**
     *
     * @param prompt
     * @param error
     * @param accuracy
     * @return False if execution should escape the response loop
     */
    public boolean getCommand(final String prompt, final String error, final int accuracy) {
        do {
            if (!prompt.isEmpty()) {
                output.println(prompt);
            }
            output.print();
            final String[] args = getString().split(" ");
            try {
                return executeCmd(args, error, accuracy);
            } catch (ServiceNotFoundException ex) {}
        } while(true);
    }

    private boolean executeCmd(final String[] args, final String error, final int accuracy) throws ServiceNotFoundException {
        for (Service service : services) {
            String name = service.getClass().getSimpleName();
            if (name.equalsIgnoreCase(args[0]) || args[0].toLowerCase().startsWith(name.substring(0, accuracy).toLowerCase())) {
                return service.run(this, subarray(args, 1, args.length));
            }
        }
        output.println(error);
        throw new ServiceNotFoundException(args);
    }

    /**
     *
     * @param prompt
     * @param accuracy
     */
    public void getCommand(final String prompt, final int accuracy) {
        getCommand(prompt, "Unrecognized command, try again.", accuracy);
    }

    /**
     *
     * @return Immutable list
     */
    public List<Service> getServices() {
        return unmodifiableList(services);
    }

    /**
     *
     * @param val
     * @param prompt
     * @param error
     * @return Valid response
     */
    public String getResponse(final Validator val, final String prompt, final String error) {
        output.println(prompt);
        output.print();
        String string;
        for (string = getString(); val != null && !val.isValid(string); string = getString()) {
            output.println(error);
            output.print();
        }
        return string;
    }

    /**
     *
     * @return User input
     */
    public String getString() {
        try {
            return reader.readLine();
        } catch (IOException ex) {
            throw new UnsupportedOperationException("Cannot read from input stream.", ex);
        }
    }
}

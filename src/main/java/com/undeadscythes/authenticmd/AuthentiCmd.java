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
public abstract class AuthentiCmd {
    /**
     * Ease of access to command line output specified on construction.
     */
    public final TipScript output;

    private final BufferedReader reader;
    private final List<Service> services = new ArrayList<Service>(1);

    /**
     * @param input {@link InputStream} that will be used to collect user input.
     * @param output {@link TipScript} that will be used to display feedback.
     */
    protected AuthentiCmd(final InputStream input, final TipScript output) {
        reader = new BufferedReader(new InputStreamReader(input));
        this.output = output;
    }

    /**
     * Set the services that this {@link AuthentiCmd} has access to.
     *
     * @param serviceClassPaths The class paths of each service
     * @param defaultCmds True to also load {@link Help} and {@link Quit}
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
     * Execute a list of commands, useful for running command line arguments.
     *
     * @param message Message to display before execution
     * @param cmds Commands with arguments
     * @return False if execution should escape the response loop
     */
    public boolean executeCmds(final String message, final String[] cmds) {
        if (!message.isEmpty()) output.println(message);
        for (String arg : removeElement(cmds, "")) {
            final String[] args = arg.split(" ");
            try {
                if (!executeCmd(args, "Unrecognized command '" + arg + "'.", 0)) return false;
            } catch (ServiceNotFoundException ex) {}
        }
        return true;
    }

    /**
     * Get and execute a user command.
     *
     * @param prompt Prompt to display before cursor, does not add a newline
     * @param error Message to display if no service is matched
     * @param accuracy Number of characters to match
     * @return False if the program should quit
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
            if (name.equalsIgnoreCase(args[0]) || (accuracy > 0 ? args[0].toLowerCase().startsWith(name.substring(0, accuracy).toLowerCase()) : false)) {
                return service.run(this, subarray(args, 1, args.length));
            }
        }
        output.println(error);
        throw new ServiceNotFoundException(args);
    }

    /**
     * Get and execute a user command with a default error message.
     *
     * @see AuthentiCmd#getCommand
     */
    public boolean getCommand(final String prompt, final int accuracy) {
        return getCommand(prompt, "Unrecognized command, try again.", accuracy);
    }

    /**
     * @return Immutable list
     */
    public List<Service> getServices() {
        return unmodifiableList(services);
    }

    /**
     * Get a user string response and validate it.
     *
     * @param val {@link Validator} to use to validate the string
     * @param prompt Prompt to display before cursor, does not add a newline
     * @param error Message to display if no service is matched
     * @return Validated response
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

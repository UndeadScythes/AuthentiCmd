package com.undeadscythes.authenticmd;

import com.undeadscythes.authenticmd.exception.ServiceNotFoundException;
import com.undeadscythes.authenticmd.exception.TerminationRequest;
import com.undeadscythes.authenticmd.service.*;
import com.undeadscythes.authenticmd.validator.Validator;
import com.undeadscythes.tipscript.TipScript;
import java.io.*;
import java.util.*;
import org.apache.commons.lang3.ArrayUtils;

/**
 * AuthentiCmd is designed to be extended to provide structure, on its own this
 * class only provides access to user input and validation.
 *
 * @author UndeadScythes
 */
public class AuthentiCmd {
    private final TipScript output;
    private final BufferedReader reader;
    private final List<Service> services = new ArrayList<Service>(0);

    /**
     * Specify a default {@link InputStream} and {@link TipScript} to use.
     */
    protected AuthentiCmd(final InputStream input, final TipScript output) {
        reader = new BufferedReader(new InputStreamReader(input));
        this.output = output;
    }

    /**
     * Set the {@link Service}s that this {@link AuthentiCmd} has access to.
     *
     * @param serviceClassPaths The full class paths of each {@link Service}
     * @param defaultCmds True to also load {@link Help} and {@link Quit}
     */
    public void setServices(final List<String> serviceClassPaths, final boolean defaultCmds) {
        for (String service : serviceClassPaths) {
            try {
                services.add((Service)Class.forName(service).newInstance());
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
     * Load the {@link Service Services} in the given {@link List}.
     */
    public void setServices(final List<Service> services) {
        this.services.addAll(services);
    }

    /**
     * Execute a list of commands, useful for running command line arguments.
     *
     * @param message Message to display before execution
     */
    public void executeCmds(final String message, final String[] cmds) throws TerminationRequest {
        if (!message.isEmpty()) output.println(message);
        for (String arg : ArrayUtils.removeElement(cmds, "")) {
            final String[] args = arg.split(" ");
            try {
                executeCmd(args, "Unrecognized command '" + arg + "'.", 0);
            } catch (ServiceNotFoundException ex) {}
        }
    }

    /**
     * Get and execute a user command.
     *
     * @param prompt Prompt to display before cursor, does not add a newline
     * @param error Message to display if no service is matched
     * @param accuracy Number of characters to match
     * @return A boolean representing whether the service executed successfully
     */
    public boolean getCommand(final String prompt, final String error, final int accuracy) throws TerminationRequest {
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

    private boolean executeCmd(final String[] args, final String error, final int accuracy) throws ServiceNotFoundException, TerminationRequest {
        for (Service service : services) {
            final String name = service.getClass().getSimpleName();
            if (name.equalsIgnoreCase(args[0]) || (accuracy > 0 ? args[0].toLowerCase().startsWith(name.substring(0, accuracy).toLowerCase()) : false)) {
                return service.run(this, ArrayUtils.subarray(args, 1, args.length));
            }
        }
        output.println(error);
        throw new ServiceNotFoundException(args);
    }

    /**
     * Get and execute a user command with a default error message "Unrecognized
     * command, try again.".
     *
     * @see AuthentiCmd#getCommand(String, String, int) getCommand(String, String, int)
     */
    public boolean getCommand(final String prompt, final int accuracy) throws TerminationRequest {
        return getCommand(prompt, "Unrecognized command, try again.", accuracy);
    }

    /**
     * Get an immutable list of {@link Service}s currently loaded.
     */
    public List<Service> getServices() {
        return Collections.unmodifiableList(services);
    }

    /**
     * Get a user string response and validate it using a {@link Validator}.
     *
     * @param prompt Prompt to display before cursor, does not add a newline
     * @param error Message to display if response is invalid
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
     * Get a single user input.
     */
    public String getString() {
        try {
            return reader.readLine();
        } catch (IOException ex) {
            throw new UnsupportedOperationException("Cannot read from input stream.", ex);
        }
    }

    /**
     * Get the command line output {@link TipScript} specified on construction.
     */
    public TipScript getOutput() {
        return output;
    }
}

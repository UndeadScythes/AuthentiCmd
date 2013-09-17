package com.undeadscythes.authenticmd.exception;

import static org.apache.commons.lang3.StringUtils.*;

/**
 * Thrown when a service cannot be found matching a user's input.
 *
 * @author UndeadScythes
 */
public class ServiceNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * @param args User input that generated this exception
     */
    public ServiceNotFoundException(final String[] args) {
        super("Cannot find service for command " + join(args, " ") + ".");
    }
}

package com.undeadscythes.authenticmd.exception;

import static org.apache.commons.lang3.StringUtils.*;

/**
 * @author UndeadScythes
 */
public class ServiceNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     *
     * @param args
     */
    public ServiceNotFoundException(final String[] args) {
        super("Cannot find service for command " + join(args, " ") + ".");
    }
}

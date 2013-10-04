package com.undeadscythes.authenticmd.exception;

import com.undeadscythes.authenticmd.service.Service;

/**
 * Thrown when a {@link Service} requests a termination.
 *
 * @author UndeadScythes
 */
public class TerminationRequest extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Provide the {@link Service} that requested the termination.
     */
    public TerminationRequest(final Service service) {
        super("A process termination has been requested by the service '" + service.getClass().getName() + "'.");
    }
}

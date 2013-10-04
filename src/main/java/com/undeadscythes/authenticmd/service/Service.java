package com.undeadscythes.authenticmd.service;

import com.undeadscythes.authenticmd.AuthentiCmd;
import com.undeadscythes.authenticmd.exception.TerminationRequestException;

/**
 * Provides a particular service to the user.
 *
 * @author UndeadScythes
 */
public abstract class Service {
    /**
     * This method will mainly be called by the parent {@link AuthentiCmd} to
     * execute a service.
     *
     * @return False if the {@link Service} did not complete successfully
     */
    public abstract boolean run(final AuthentiCmd program, final String[] args) throws TerminationRequestException;

    /**
     * Convenience method for supplying only a single argument.
     *
     * @see #run(AuthentiCmd, String[]) run(AuthentiCmd, String[])
     */
    public boolean run(final AuthentiCmd program, final String arg) throws TerminationRequestException {
        return run(program, new String[]{arg});
    }
}

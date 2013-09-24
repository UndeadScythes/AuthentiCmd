package com.undeadscythes.authenticmd.service;

import com.undeadscythes.authenticmd.*;

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
     * @return False if the parent {@link AuthentiCmd} needs to quit
     */
    public abstract boolean run(final AuthentiCmd program, final String[] args);

    /**
     * Convenience method for supplying only a single argument.
     *
     * @see #run(AuthentiCmd, String[]) run(AuthentiCmd, String[])
     */
    public boolean run(final AuthentiCmd program, final String arg) {
        return run(program, new String[]{arg});
    }
}

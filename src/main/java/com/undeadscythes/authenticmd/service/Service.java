package com.undeadscythes.authenticmd.service;

import com.undeadscythes.authenticmd.*;

/**
 * Provides a particular service to the user.
 *
 * @author UndeadScythes
 */
public interface Service {
    /**
     * This method will mainly be called by the parent {@link AuthentiCmd} to
     * execute a service.
     *
     * @return False if the parent {@link AuthentiCmd} needs to quit
     */
    boolean run(AuthentiCmd program, String[] args);
}

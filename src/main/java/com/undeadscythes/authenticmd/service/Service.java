package com.undeadscythes.authenticmd.service;

import com.undeadscythes.authenticmd.*;

/**
 * Provides a particular service to the user.
 *
 * @author UndeadScythes
 */
public interface Service {
    /**
     * @param program Parent {@link AuthentiCmd} that is executing this
     * {@link Service}
     * @param args Arguments to send to the service
     * @return False if the parent {@link AuthentiCmd} needs to quit
     */
    boolean run(AuthentiCmd program, String[] args);
}

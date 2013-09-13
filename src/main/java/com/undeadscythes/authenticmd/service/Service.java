package com.undeadscythes.authenticmd.service;

import com.undeadscythes.authenticmd.*;

/**
 * Provides a particular service to the user.
 *
 * @author UndeadScythes
 */
public interface Service {
    /**
     * 
     * @param program
     * @param args
     * @return False if execution should escape the response loop
     */
    boolean run(AuthentiCmd program, String[] args);
}

package com.undeadscythes.authenticmd.service;

import com.undeadscythes.authenticmd.*;

/**
 * Provides the user with the ability to escape the response loop.
 *
 * @author UndeadScythes
 */
public class Quit implements Service {
    public boolean run(final AuthentiCmd program, final String[] args) {
        return false;
    }
}

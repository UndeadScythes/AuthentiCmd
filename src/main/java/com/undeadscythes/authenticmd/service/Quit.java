package com.undeadscythes.authenticmd.service;

import com.undeadscythes.authenticmd.*;

/**
 * A default service that will always return false.
 *
 * @author UndeadScythes
 */
public class Quit extends Service {
    @Override
    public boolean run(final AuthentiCmd program, final String[] args) {
        return false;
    }
}

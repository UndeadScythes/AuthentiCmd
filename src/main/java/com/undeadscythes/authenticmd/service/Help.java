package com.undeadscythes.authenticmd.service;

import com.undeadscythes.authenticmd.*;

/**
 * Provides the user with access to a list of commands for services that are
 * currently available.
 *
 * @author UndeadScythes
 */
public class Help extends Service {
    @Override
    public boolean run(final AuthentiCmd program, final String[] args) {
        program.output.println("Available commands:");
        for (Service service : program.getServices()) {
            final String name = service.getClass().getSimpleName();
            program.output.println("- " + name);
        }
        return true;
    }
}

package com.undeadscythes.authenticmd.service;

import com.undeadscythes.authenticmd.AuthentiCmd;

/**
 * Provides the user with access to a list of commands for services that are
 * currently available.
 *
 * @author UndeadScythes
 */
public class Help extends Service {
    @Override
    public boolean run(final AuthentiCmd program, final String[] args) {
        program.getOutput().println("Available commands:");
        for (Service service : program.getServices()) {
            final String name = service.getClass().getSimpleName();
            program.getOutput().println("- " + name);
        }
        return true;
    }
}

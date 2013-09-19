package com.undeadscythes.authenticmd.validator;

/**
 * Used to ensure that a given response is valid within its own context.
 *
 * @author UndeadScythes
 */
public interface Validator {
    /**
     * Return true if the given response is valid.
     */
    boolean isValid(String response);
}

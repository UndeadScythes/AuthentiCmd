package com.undeadscythes.authenticmd.validator;

/**
 * Used to ensure that a given response is valid within its own context.
 *
 * @author UndeadScythes
 */
public interface Validator {
    /**
     *
     * @param response
     * @return True if the response is valid
     */
    boolean isValid(String response);
}

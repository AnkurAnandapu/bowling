package com.game.bowling.error.exception;

/**
 * This Exception is thrown when invocation parameter is invalid.
 */
public class InvalidParameterException extends RuntimeException {

    public InvalidParameterException(String message) {
        super(message);
    }
}

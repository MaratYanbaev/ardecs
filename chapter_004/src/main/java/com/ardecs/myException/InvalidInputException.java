package com.ardecs.myException;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 02.08.2019
 */
public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

package com.ardecs.myException;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 22.08.2019
 */
public class DuplicateNameOfModelException extends Exception {
    public DuplicateNameOfModelException(String msg) {
        super(msg);
    }
}
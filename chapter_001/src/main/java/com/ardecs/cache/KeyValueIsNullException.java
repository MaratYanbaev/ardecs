package com.ardecs.cache;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 28.03.2019
 */

/**
 * Исключение выбрасывается, если ключ и/или значение равны нуль
 */
public class KeyValueIsNullException extends RuntimeException {

    public KeyValueIsNullException(String message) {
        super(message);
    }
}

package com.ardecs.myException;

import com.fasterxml.jackson.databind.util.StdDateFormat;

import java.util.Date;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 02.08.2019
 *
 * My error response structure.
 */
public class ErrorDetails {
    private String time;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        this.time = new StdDateFormat().format(timestamp);
        this.message = message;
        this.details = details;
    }

    public String getTimestamp() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}

package com.ardecs.myException;

import java.util.Date;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 02.08.2019
 *
 * My error response structure.
 */
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}

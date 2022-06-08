package com.exceptions;

public class NoUsersFound extends RuntimeException{
    public NoUsersFound() {
    }

    public NoUsersFound(String message) {
        super(message);
    }

    public NoUsersFound(String message, Throwable cause) {
        super(message, cause);
    }

    public NoUsersFound(Throwable cause) {
        super(cause);
    }

    public NoUsersFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

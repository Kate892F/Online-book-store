package com.bookstore.repository.exception;

public class DataProcessingException extends RuntimeException {

    public DataProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}

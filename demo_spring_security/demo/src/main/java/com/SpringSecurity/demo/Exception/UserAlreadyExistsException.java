package com.SpringSecurity.demo.Exception;

/**
 * Thrown when an attempt is made to register a user
 * with a username that already exists.
 *
 * Using a custom exception keeps error handling explicit
 * and avoids abusing IllegalArgumentException.
 */
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

package com.udea.usermembershipservice.domain.exception;

public class InvalidIdException extends RuntimeException {
    public InvalidIdException() {
        super("Id person must be a positive integer and cannot contain letters.");
    }

}

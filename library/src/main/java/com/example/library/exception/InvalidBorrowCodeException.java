package com.example.library.exception;

public class InvalidBorrowCodeException extends RuntimeException {
    public InvalidBorrowCodeException(String message) { super(message); }
}

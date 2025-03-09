package com.example.book.exception;

public class BookNotFoundException extends BookException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public BookNotFoundException(String message) {
        super(message);
    }


    public BookNotFoundException(String message, Exception reference) {
        super(message, reference);
    }

}

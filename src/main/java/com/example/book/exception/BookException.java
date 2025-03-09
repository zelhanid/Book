package com.example.book.exception;

public class BookException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    Exception reference = new Exception();

    public BookException(String message) {
        super(message);
    }

    public BookException(String message, Exception reference) {
        super(message);
        this.reference = reference;
    }


}

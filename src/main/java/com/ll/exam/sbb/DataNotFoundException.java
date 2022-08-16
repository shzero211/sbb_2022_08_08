package com.ll.exam.sbb;

public class DataNotFoundException extends RuntimeException{
    private static final long serialVersionUID=1L;
    public DataNotFoundException(String message) {
        super(message);
    }
}

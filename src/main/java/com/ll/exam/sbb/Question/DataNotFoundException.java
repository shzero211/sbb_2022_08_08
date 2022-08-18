package com.ll.exam.sbb.Question;

public class DataNotFoundException extends RuntimeException{
    private static final long serialVersionUID=1L;
    public DataNotFoundException(String message) {
        super(message);
    }
}

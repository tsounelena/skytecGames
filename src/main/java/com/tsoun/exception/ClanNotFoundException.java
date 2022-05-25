package com.tsoun.exception;

public class ClanNotFoundException extends RuntimeException{

    public ClanNotFoundException(String message) {
        super(message);
    }
}

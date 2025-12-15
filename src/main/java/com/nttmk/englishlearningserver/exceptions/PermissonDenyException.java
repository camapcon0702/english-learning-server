package com.nttmk.englishlearningserver.exceptions;

public class PermissonDenyException extends RuntimeException {
    public PermissonDenyException(String message){
        super(message);
    }
}

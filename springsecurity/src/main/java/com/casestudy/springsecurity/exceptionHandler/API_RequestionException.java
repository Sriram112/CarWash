package com.casestudy.springsecurity.exceptionHandler;

public class API_RequestionException extends RuntimeException{
    public API_RequestionException(String message,Throwable cause){
        super(message, cause);
    }
    public API_RequestionException(String message){
        super(message);
    }
}

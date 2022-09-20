package com.casestudy.user.exceptionHandler;

public class API_exceptionHandler extends RuntimeException{

    public API_exceptionHandler(String message,Throwable cause){
        super(message, cause);
    }

    public API_exceptionHandler(String message){
        super(message);
    }
}

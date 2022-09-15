package com.casestudy.order.exceptionHandlers;

public class APIRequestException extends RuntimeException{
    public APIRequestException(String message, Throwable cause){
        super(message, cause);
    }

    public APIRequestException(String message){
        super(message);
    }
}

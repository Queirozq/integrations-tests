package com.MQueiroz.integrations.services.exceptions;

public class EmailException extends RuntimeException{

    public EmailException (String msg){
        super(msg);
    }
}
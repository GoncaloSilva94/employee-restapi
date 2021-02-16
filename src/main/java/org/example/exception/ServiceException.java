package org.example.exception;

public class ServiceException extends RuntimeException{

    public ServiceException(String message){

        super(message);                         //throws serviceException when cannot find Object attribute
    }
}

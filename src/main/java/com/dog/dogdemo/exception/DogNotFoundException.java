package com.dog.dogdemo.exception;

public class DogNotFoundException extends RuntimeException{
    public DogNotFoundException(String message){
        super(message);
    }
}

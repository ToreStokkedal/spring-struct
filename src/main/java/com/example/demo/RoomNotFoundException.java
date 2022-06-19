package com.example.demo;

public class RoomNotFoundException extends RuntimeException{
    
    public RoomNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public RoomNotFoundException() {
        super();
    }
}
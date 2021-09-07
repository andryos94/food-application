package com.healthy.food.util;

public class InvalidFilteringRequestException extends Exception{
    private String message;

    public InvalidFilteringRequestException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.ronial.exceptions;

import lombok.Builder;
import lombok.Getter;

@Getter
public class InfrastructureException extends RuntimeException{
    private final String message;
    public InfrastructureException(Class<?> clazz,String message) {
        super(message);
        this.message = toMessage(clazz,message);
    }
    private String toMessage(Class<?> clazz,String message){
        return clazz.getSimpleName() + " : " + message;
    }
}

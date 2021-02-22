package com.projectweb.transportassistant.model.exeptions;

public class InvalidGradException extends RuntimeException{
    public InvalidGradException() {super("Category with city not found");
    }
}

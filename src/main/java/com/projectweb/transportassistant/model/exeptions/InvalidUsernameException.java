package com.projectweb.transportassistant.model.exeptions;

public class InvalidUsernameException extends RuntimeException{
    public InvalidUsernameException() {super("User not found!");
    }
}

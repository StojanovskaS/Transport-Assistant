package com.projectweb.transportassistant.model.exeptions;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String message) {
        super(String.format("User with this username : %s already exists! ",message));
    }
}

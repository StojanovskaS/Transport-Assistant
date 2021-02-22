package com.projectweb.transportassistant.model.exeptions;

public class InvalidUsercCredentialException extends RuntimeException{
    public InvalidUsercCredentialException() {
        super("You need to fill all required boxes!!!");
    }
}

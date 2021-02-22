package com.projectweb.transportassistant.model.exeptions;

public class PasswordsdoNotMatchException extends RuntimeException{
    public PasswordsdoNotMatchException() {
        super("Password and repeatedpassword don't mach!");
    }
}

package com.projectweb.transportassistant.model.exeptions;

public class InvalidCategoryNameException extends RuntimeException {
    public InvalidCategoryNameException() {
        super("Samo ISTOCNA,ZAPADNA,SREDNA Makedonija postoi !!!");
    }
}

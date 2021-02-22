package com.projectweb.transportassistant.model.exeptions;

public class InvalidInterstedPartIDfromPostException extends RuntimeException{
    public InvalidInterstedPartIDfromPostException() {
        super("Ivlaid id for InterestedPart find by ID");
    }
}

package com.talentsprint.cycleshop.exception;

public class CycleNotFoundException extends RuntimeException {
	
    public CycleNotFoundException() {
        super("Cycle not found");
    }

    public CycleNotFoundException(String message) {
        super(message);
    }
    
}

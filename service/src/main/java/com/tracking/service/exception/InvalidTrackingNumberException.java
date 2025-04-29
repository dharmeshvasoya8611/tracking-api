package com.tracking.service.exception;

public class InvalidTrackingNumberException extends Exception {

    /** */
    private static final long serialVersionUID = 1L;

    public InvalidTrackingNumberException(String m) {
        super(m);
    }
}

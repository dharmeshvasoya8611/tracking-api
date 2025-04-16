package com.tracking.web.model;

public class ValidationMessage {

    private boolean valid;

    private String message;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ValidationMessage [valid=" + valid + ", message=" + message + "]";
    }
}

package com.tracking.service.model;

public class TrackingNumber {

    private String number;

    private String createdAt;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "TrackingNumber [number=" + number + ", createdAt=" + createdAt + "]";
    }
}

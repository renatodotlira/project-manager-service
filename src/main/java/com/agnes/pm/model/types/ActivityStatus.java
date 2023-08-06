package com.agnes.pm.model.types;

public enum ActivityStatus {

    TODO("todo"),
    DOING("doing"),
    RELEASING("releasing"),
    BLOCKED("blocked"),
    CANCELED("canceled"),
    REVIEW("review"),
    DONE("done");

    private final String status;

    ActivityStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}

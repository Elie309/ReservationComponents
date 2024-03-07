package com.elie309.reservation.models;

public enum Status {

    NOT_CONFIRMED("not confirmed"),
    CANCELLED("cancelled"),
    CONFIRMED("confirmed"),
    DID_NOT_COME("didn't come"),
    DONE("done");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

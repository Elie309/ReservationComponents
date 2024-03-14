package com.elie309.reservation.models;

import java.util.ArrayList;
import java.util.List;

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

    public static List<String> getAllStatusValues() {
        List<String> statusValues = new ArrayList<>();
        for (Status status : Status.values()) {
            statusValues.add(status.getValue());
        }
        return statusValues;
    }


}

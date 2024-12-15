package com.example.parking.model;

public class WrappedLongValue {
    private Long value;

    public WrappedLongValue() {
    }

    public WrappedLongValue(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}

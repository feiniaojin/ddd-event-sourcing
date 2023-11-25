package com.feiniaojin.ddd.eventsourcing.domain;

public class ProductId {
    private final String value;

    public ProductId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

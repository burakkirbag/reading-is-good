package com.burakkirbag.readingisgood.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataNotFoundException extends RuntimeException {

    private final String key;
    private final String[] args;

    public DataNotFoundException(String key) {
        super(key);
        this.key = key;
        args = new String[0];
    }

    public DataNotFoundException(String key, String... args) {
        super(key);
        this.key = key;
        this.args = args;
    }
}
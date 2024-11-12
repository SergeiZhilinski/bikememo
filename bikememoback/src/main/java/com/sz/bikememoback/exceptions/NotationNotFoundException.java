package com.sz.bikememoback.exceptions;

public class NotationNotFoundException extends RuntimeException {
    public NotationNotFoundException(Long id) {
        super(String.format("Notation %s Not Found", id));
    }
}

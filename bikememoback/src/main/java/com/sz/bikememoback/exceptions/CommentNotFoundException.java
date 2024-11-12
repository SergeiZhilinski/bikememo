package com.sz.bikememoback.exceptions;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(Long id) {
        super(String.format("Notation %s Not Found", id));
    }
}

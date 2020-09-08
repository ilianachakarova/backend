package com.example.redditclone.exceptions;

public class VoteNotFoundException extends RuntimeException{

    public VoteNotFoundException(String message) {
        super(message);
    }

    public VoteNotFoundException() {
        super();
    }
}

package com.rooms.occupancy.manager.exception;

public class FileNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FileNotFoundException(final String msg) {
        super("Potential Guests fle not found: " + msg);
    }

}

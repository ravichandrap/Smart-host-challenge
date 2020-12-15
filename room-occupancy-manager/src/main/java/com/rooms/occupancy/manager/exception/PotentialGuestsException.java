package com.rooms.occupancy.manager.exception;

public class PotentialGuestsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PotentialGuestsException(String msg) {
        super("Potential Guests not found in resource: " + msg);
    }

}

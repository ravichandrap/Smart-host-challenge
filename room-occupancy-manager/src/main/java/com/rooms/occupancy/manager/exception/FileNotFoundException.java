package com.rooms.occupancy.manager.exception;
/**
 * Custom exception when file not found ( Guests details file)
 */
public class FileNotFoundException extends RuntimeException {

    public FileNotFoundException(final String msg) {
        super("Potential Guests fle not found: " + msg);
    }
}

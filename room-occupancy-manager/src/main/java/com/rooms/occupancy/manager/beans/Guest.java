package com.rooms.occupancy.manager.beans;

import java.util.List;
import java.util.Optional;

public class Guest {
    private List<Long> guests;

    Guest(List<Long> guests) {
        this.guests = guests;
    }

    public static Optional<Guest> of(List<Long> guests) {
        if(guests == null || guests.isEmpty())
            return Optional.empty();
        return Optional.of(new Guest(guests));
    }

    public List<Long> getGuests() {
        return guests;
    }

    public void setGuests(List<Long> guests) {
        this.guests = guests;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "guests=" + guests +
                '}';
    }
}

package com.rooms.occupancy.manager.service;

import com.rooms.occupancy.manager.beans.Guest;
import com.rooms.occupancy.manager.beans.Occupancy;
import com.rooms.occupancy.manager.beans.RoomRequest;
import com.rooms.occupancy.manager.util.RoomUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomManagerService {

    @Value("${room.separate.price}")
    Long separatePrice;

    public Optional<Occupancy> getOccupancy(RoomRequest room) {
        Optional<Guest> guests = RoomUtil.getPotentialGuests();
        if(guests.isEmpty())
            return Optional.empty();

        Guest guest = guests.get();
        List<Long> guestList = guest.getGuests();



        return Optional.of(Occupancy.of(2L,3L));
    }
}

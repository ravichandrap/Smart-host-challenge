package com.rooms.occupancy.manager.service;

import com.rooms.occupancy.manager.beans.Occupancy;
import com.rooms.occupancy.manager.beans.RoomRequest;
import org.springframework.stereotype.Service;

@Service
public class RoomManagerService {

    public Occupancy getOccupancy(RoomRequest room) {
        return Occupancy.of(2L,3L);
    }
}

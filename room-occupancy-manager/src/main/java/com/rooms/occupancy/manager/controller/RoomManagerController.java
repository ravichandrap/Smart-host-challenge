package com.rooms.occupancy.manager.controller;

import com.rooms.occupancy.manager.beans.OccupancyManager;
import com.rooms.occupancy.manager.beans.RoomRequest;
import com.rooms.occupancy.manager.service.RoomManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/room")
public class RoomManagerController {

    RoomManagerController(RoomManagerService service) {
        this.service = service;
    }
    private final RoomManagerService service;

    @PostMapping
    public OccupancyManager occupancy(@RequestBody RoomRequest room) {
        return service.getOccupancy(room);
    }

    /*
    TO-DO
    1. JSON file read mock input guests data
    2. API Call for
        (input) Free Premium rooms: 3
        (input) Free Economy rooms: 3
    3. Service Class to calculate Occupancy
    4.
     */
}

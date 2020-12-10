package com.rooms.occupancy.manager.controller;

import com.rooms.occupancy.manager.beans.Occupancy;
import com.rooms.occupancy.manager.beans.RoomRequest;
import com.rooms.occupancy.manager.service.RoomManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/room")
public class RoomManagerController {

    @Autowired
    private RoomManagerService service;

    @PostMapping
    public Occupancy occupancy(@RequestBody RoomRequest room) {
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

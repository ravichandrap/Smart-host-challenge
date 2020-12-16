package com.rooms.occupancy.manager.controller;

import com.rooms.occupancy.manager.beans.OccupancyManager;
import com.rooms.occupancy.manager.beans.RoomRequest;
import com.rooms.occupancy.manager.service.RoomManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room")
public class RoomManagerController {
    private static final Logger LOG = LoggerFactory.getLogger(RoomManagerController.class);

    RoomManagerController(final RoomManagerService service) {
        this.service = service;
    }
    private final RoomManagerService service;

    @PostMapping
    public OccupancyManager occupancy(@RequestBody final RoomRequest room) {
        LOG.info("occupancy method in RoomManagerController");
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

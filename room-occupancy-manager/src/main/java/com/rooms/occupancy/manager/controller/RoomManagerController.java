package com.rooms.occupancy.manager.controller;

import com.rooms.occupancy.manager.beans.OccupancyManager;
import com.rooms.occupancy.manager.beans.RoomRequest;
import com.rooms.occupancy.manager.service.RoomManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RoomManagerController {
    private static final Logger LOG = LoggerFactory.getLogger(RoomManagerController.class);
    private final RoomManagerService service;

    RoomManagerController(final RoomManagerService service) {
        this.service = service;
    }

    @PostMapping("/room")
    public Optional<OccupancyManager> occupancy(@RequestBody final RoomRequest room) {
        LOG.info("occupancy method in RoomManagerController");
        return service.getOccupancy(room);
    }

}

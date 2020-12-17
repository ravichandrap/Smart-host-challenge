package com.rooms.occupancy.manager.controller;

import com.rooms.occupancy.manager.beans.OccupancyManager;
import com.rooms.occupancy.manager.beans.RoomRequest;
import com.rooms.occupancy.manager.service.RoomManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RoomManagerController {
    private static final Logger LOG = LoggerFactory.getLogger(RoomManagerController.class);
    private final RoomManagerService service;

    //Constructor injection
    protected RoomManagerController(final RoomManagerService service) {
        this.service = service;
    }

    /**
     * Calculate Occupancy of rooms and guest.
     * @param room Contains of number of rooms of Premium and Economy
     * @return rooms, guests occupancy and price details
     */
    @Operation(summary = "Calculate how many rooms of each category will be occupied and how much money will make in total")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Occupied room and price details",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = OccupancyManager.class)) })
    })
    @PostMapping("/room")
    public Optional<OccupancyManager> occupancy(
            @Parameter(description = "Number of Premium and Economy rooms")
            @RequestBody final RoomRequest room) {
        LOG.info("Occupancy method in RoomManagerController");
        return service.getOccupancy(room);
    }

}

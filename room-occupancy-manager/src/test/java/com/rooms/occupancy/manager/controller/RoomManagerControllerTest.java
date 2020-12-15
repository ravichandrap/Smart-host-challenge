package com.rooms.occupancy.manager.controller;

import com.rooms.occupancy.manager.beans.Occupancy;
import com.rooms.occupancy.manager.beans.OccupancyManager;
import com.rooms.occupancy.manager.beans.RoomRequest;
import com.rooms.occupancy.manager.service.RoomManagerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomManagerControllerTest {

    @Mock
    RoomManagerService service;

    @InjectMocks
    RoomManagerController controller = new RoomManagerController();

    @Test
    void testOccupancy() throws IOException {
        RoomRequest roomRequest
                = RoomRequest.of(7, 5);
//        OccupancyManager occupancy = OccupancyManager.of(6, 4);

//        Mockito.when(service.getOccupancy(roomRequest))
//                .thenReturn(occupancy);
        OccupancyManager occupancyResponse = controller.occupancy(roomRequest);
        assertNotNull(occupancyResponse);

    }
}
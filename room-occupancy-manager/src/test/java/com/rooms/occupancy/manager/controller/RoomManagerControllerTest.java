package com.rooms.occupancy.manager.controller;

import com.rooms.occupancy.manager.beans.OccupancyManager;
import com.rooms.occupancy.manager.beans.RoomRequest;
import com.rooms.occupancy.manager.service.RoomManagerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomManagerControllerTest {

    @Mock
    RoomManagerService service;

    @InjectMocks
    RoomManagerController controller = new RoomManagerController(service);

    @Test
    void testOccupancy() throws IOException {
        RoomRequest roomRequest
                = RoomRequest.of(7, 5);
        OccupancyManager occupancyManager =
                OccupancyManager.of(189, 4, 6, 1054);
        Mockito.when(service.getOccupancy(roomRequest))
                .thenReturn(occupancyManager);
        OccupancyManager occupancyResponse = controller.occupancy(roomRequest);
        assertNotNull(occupancyResponse);

    }
}
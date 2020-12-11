package com.rooms.occupancy.manager.controller;

import com.rooms.occupancy.manager.beans.Occupancy;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomManagerControllerTest {

    @Mock
    RoomManagerService service;

    @InjectMocks
    RoomManagerController controller = new RoomManagerController();

    @Test
    void testOccupancy() {
        RoomRequest roomRequest
                = RoomRequest.of(7L, 5L);
        Occupancy occupancy = Occupancy.of(6L, 4L);

        Mockito.when(service.getOccupancy(roomRequest))
                .thenReturn(Optional.of(occupancy));
        ResponseEntity<Occupancy> occupancyResponse = controller.occupancy(roomRequest);
        assertEquals(HttpStatus.OK, occupancyResponse.getStatusCode());

    }
}
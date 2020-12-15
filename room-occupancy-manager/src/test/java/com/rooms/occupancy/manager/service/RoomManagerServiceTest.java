package com.rooms.occupancy.manager.service;

import com.rooms.occupancy.manager.beans.Occupancy;
import com.rooms.occupancy.manager.beans.OccupancyManager;
import com.rooms.occupancy.manager.beans.RoomRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomManagerServiceTest {

    RoomManagerService service = new RoomManagerService();

    @Test
    void testGetOccupancy() throws IOException {
        RoomRequest roomRequest = RoomRequest.of(4, 5);
        OccupancyManager occupancy = service.getOccupancy(roomRequest);
        assertNotNull(occupancy);

    }

}
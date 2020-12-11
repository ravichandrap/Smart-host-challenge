package com.rooms.occupancy.manager.service;

import com.rooms.occupancy.manager.beans.Occupancy;
import com.rooms.occupancy.manager.beans.RoomRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomManagerServiceTest {

    RoomManagerService service = new RoomManagerService();

    @Test
    void testGetOccupancy() {
        RoomRequest roomRequest = RoomRequest.of(4L, 5L);
        Optional<Occupancy> occupancy = service.getOccupancy(roomRequest);
        assertFalse(occupancy.isEmpty());

        Occupancy occ = occupancy.get();
        assertEquals(3L, occ.getEconomy());
    }

}
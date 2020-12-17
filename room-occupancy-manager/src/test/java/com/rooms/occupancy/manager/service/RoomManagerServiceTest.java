package com.rooms.occupancy.manager.service;

import com.rooms.occupancy.manager.beans.OccupancyManager;
import com.rooms.occupancy.manager.beans.RoomRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomManagerServiceTest {

    private final RoomManagerService service = new RoomManagerService();

    @Test
    void testGetOccupancy() throws IOException {//NOPMD
        final RoomRequest roomRequest = new RoomRequest(3, 3);
        final OccupancyManager om =
                new OccupancyManager(167, 3, 3, 738);
        OccupancyManager occupancy = service.getOccupancy(roomRequest).get();
        assertNotNull(occupancy);
        assertEquals(3, occupancy.getAllocatedPremiumRooms());
        assertEquals(om.getAllocatedEconomyRooms(), om.getAllocatedEconomyRooms(), "Good");
        assertEquals(om.getTotalEconomyPrice(), occupancy.getTotalEconomyPrice());
        assertEquals(om.getTotalPremiumPrice(), occupancy.getTotalPremiumPrice());
        assertEquals(om.getTotalEconomyPrice()+om.getTotalPremiumPrice(), occupancy.getTotalEconomyPrice()+occupancy.getTotalPremiumPrice());
    }

    @Test
    void testGetOccupancy2() throws IOException {
        RoomRequest roomRequest = new RoomRequest(7, 5);
        OccupancyManager om =
                new OccupancyManager(189, 4, 6, 1054);
        OccupancyManager occupancy = service.getOccupancy(roomRequest).get();
        assertNotNull(occupancy);
        assertEquals(om.getAllocatedPremiumRooms(), occupancy.getAllocatedPremiumRooms());
        assertEquals(om.getAllocatedEconomyRooms(), om.getAllocatedEconomyRooms());
        assertEquals(om.getTotalEconomyPrice(), occupancy.getTotalEconomyPrice());
        assertEquals(om.getTotalPremiumPrice(), occupancy.getTotalPremiumPrice());
        assertEquals(om.getTotalEconomyPrice()+om.getTotalPremiumPrice(), occupancy.getTotalEconomyPrice()+occupancy.getTotalPremiumPrice());
    }

    @Test
    void testGetOccupancy3() throws IOException {
        RoomRequest roomRequest = new RoomRequest(2, 7);
        OccupancyManager om =
                new OccupancyManager(189, 4, 2, 583);
        OccupancyManager occupancy = service.getOccupancy(roomRequest).get();
        assertNotNull(occupancy);
        assertEquals(om.getAllocatedPremiumRooms(), occupancy.getAllocatedPremiumRooms());
        assertEquals(om.getAllocatedEconomyRooms(), om.getAllocatedEconomyRooms());
        assertEquals(om.getTotalEconomyPrice(), occupancy.getTotalEconomyPrice());
        assertEquals(om.getTotalPremiumPrice(), occupancy.getTotalPremiumPrice());
        assertEquals(om.getTotalEconomyPrice()+om.getTotalPremiumPrice(), occupancy.getTotalEconomyPrice()+occupancy.getTotalPremiumPrice());
    }

    @Test
    void testGetOccupancy4() throws IOException {
        RoomRequest roomRequest = new RoomRequest(7, 1);
        final OccupancyManager om =
                new OccupancyManager(99, 1, 7, 1099);
        final OccupancyManager occupancy = service.getOccupancy(roomRequest).get();
        assertNotNull(occupancy);
        assertEquals(om.getAllocatedPremiumRooms(), occupancy.getAllocatedPremiumRooms());
        assertEquals(om.getAllocatedEconomyRooms(), om.getAllocatedEconomyRooms());
        assertEquals(om.getTotalEconomyPrice(), occupancy.getTotalEconomyPrice());
        assertEquals(om.getTotalPremiumPrice(), occupancy.getTotalPremiumPrice());
        assertEquals(om.getTotalEconomyPrice()+om.getTotalPremiumPrice(), occupancy.getTotalEconomyPrice()+occupancy.getTotalPremiumPrice());
    }

    @Test
    void testGetOccupancy5() throws IOException {
        RoomRequest roomRequest = new RoomRequest(0, 1);
        final OccupancyManager om =
                new OccupancyManager(99, 1, 0, 0);
        final OccupancyManager occupancy = service.getOccupancy(roomRequest).get();
        assertNotNull(occupancy);
        assertEquals(om.getAllocatedPremiumRooms(), occupancy.getAllocatedPremiumRooms());
        assertEquals(om.getAllocatedEconomyRooms(), om.getAllocatedEconomyRooms());
        assertEquals(om.getTotalEconomyPrice(), occupancy.getTotalEconomyPrice());
        assertEquals(om.getTotalPremiumPrice(), occupancy.getTotalPremiumPrice());
        assertEquals(om.getTotalEconomyPrice()+om.getTotalPremiumPrice(), occupancy.getTotalEconomyPrice()+occupancy.getTotalPremiumPrice());
    }


    @Test
    void testGetOccupancy6() throws IOException {
        RoomRequest roomRequest = new RoomRequest(1, 0);
        final OccupancyManager om =
                new OccupancyManager(0, 0, 1, 374);
        final OccupancyManager occupancy = service.getOccupancy(roomRequest).get();
        assertNotNull(occupancy);
        assertEquals(om.getAllocatedPremiumRooms(), occupancy.getAllocatedPremiumRooms());
        assertEquals(om.getAllocatedEconomyRooms(), om.getAllocatedEconomyRooms());
        assertEquals(om.getTotalEconomyPrice(), occupancy.getTotalEconomyPrice());
        assertEquals(om.getTotalPremiumPrice(), occupancy.getTotalPremiumPrice());
        assertEquals(om.getTotalEconomyPrice()+om.getTotalPremiumPrice(), occupancy.getTotalEconomyPrice()+occupancy.getTotalPremiumPrice());
    }

    @Test
    void testGetOccupancy7() throws IOException {
        RoomRequest roomRequest = new RoomRequest(0, 0);
        Optional<OccupancyManager> occupancy = service.getOccupancy(roomRequest);
        assertTrue(occupancy.isEmpty());
    }


}
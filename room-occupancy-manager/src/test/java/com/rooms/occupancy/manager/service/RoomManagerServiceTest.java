package com.rooms.occupancy.manager.service;

import com.rooms.occupancy.manager.beans.OccupancyManager;
import com.rooms.occupancy.manager.beans.RoomRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomManagerServiceTest {

    RoomManagerService service = new RoomManagerService();
    @Test
    void testGetOccupancy() throws IOException {
        RoomRequest roomRequest = RoomRequest.of(3, 3);
        OccupancyManager om =
                OccupancyManager.of(167, 3, 3, 738);
        OccupancyManager occupancy = service.getOccupancy(roomRequest);
        assertNotNull(occupancy);
        assertEquals(om.getAllocatedPremiumRooms(), occupancy.getAllocatedPremiumRooms());
        assertEquals(om.getAllocatedEconomyRooms(), om.getAllocatedEconomyRooms());
        assertEquals(om.getTotalEconomyPrice(), occupancy.getTotalEconomyPrice());
        assertEquals(om.getTotalPremiumPrice(), occupancy.getTotalPremiumPrice());
        assertEquals(om.getGrandTotal(), occupancy.getGrandTotal());
    }

    @Test
    void testGetOccupancy2() throws IOException {
        RoomRequest roomRequest = RoomRequest.of(7, 5);
        OccupancyManager om =
                OccupancyManager.of(189, 4, 6, 1054);
        OccupancyManager occupancy = service.getOccupancy(roomRequest);
        assertNotNull(occupancy);
        assertEquals(om.getAllocatedPremiumRooms(), occupancy.getAllocatedPremiumRooms());
        assertEquals(om.getAllocatedEconomyRooms(), om.getAllocatedEconomyRooms());
        assertEquals(om.getTotalEconomyPrice(), occupancy.getTotalEconomyPrice());
        assertEquals(om.getTotalPremiumPrice(), occupancy.getTotalPremiumPrice());
        assertEquals(om.getGrandTotal(), occupancy.getGrandTotal());
    }

    @Test
    void testGetOccupancy3() throws IOException {
        RoomRequest roomRequest = RoomRequest.of(2, 7);
        OccupancyManager om =
                OccupancyManager.of(189, 4, 2, 583);
        OccupancyManager occupancy = service.getOccupancy(roomRequest);
        assertNotNull(occupancy);
        assertEquals(om.getAllocatedPremiumRooms(), occupancy.getAllocatedPremiumRooms());
        assertEquals(om.getAllocatedEconomyRooms(), om.getAllocatedEconomyRooms());
        assertEquals(om.getTotalEconomyPrice(), occupancy.getTotalEconomyPrice());
        assertEquals(om.getTotalPremiumPrice(), occupancy.getTotalPremiumPrice());
        assertEquals(om.getGrandTotal(), occupancy.getGrandTotal());
    }

    @Test
    void testGetOccupancy4() throws IOException {
        RoomRequest roomRequest = RoomRequest.of(7, 1);
        final OccupancyManager om =
                OccupancyManager.of(99, 1, 7, 1099);
        final OccupancyManager occupancy = service.getOccupancy(roomRequest);
        assertNotNull(occupancy);
        assertEquals(om.getAllocatedPremiumRooms(), occupancy.getAllocatedPremiumRooms());
        assertEquals(om.getAllocatedEconomyRooms(), om.getAllocatedEconomyRooms());
        assertEquals(om.getTotalEconomyPrice(), occupancy.getTotalEconomyPrice());
        assertEquals(om.getTotalPremiumPrice(), occupancy.getTotalPremiumPrice());
        assertEquals(om.getGrandTotal(), occupancy.getGrandTotal());
    }

    @Test
    void testGetOccupancy5() throws IOException {
        RoomRequest roomRequest = RoomRequest.of(0, 1);
        final OccupancyManager om =
                OccupancyManager.of(99, 1, 0, 0);
        final OccupancyManager occupancy = service.getOccupancy(roomRequest);
        assertNotNull(occupancy);
        assertEquals(om.getAllocatedPremiumRooms(), occupancy.getAllocatedPremiumRooms());
        assertEquals(om.getAllocatedEconomyRooms(), om.getAllocatedEconomyRooms());
        assertEquals(om.getTotalEconomyPrice(), occupancy.getTotalEconomyPrice());
        assertEquals(om.getTotalPremiumPrice(), occupancy.getTotalPremiumPrice());
        assertEquals(om.getGrandTotal(), occupancy.getGrandTotal());
    }


    @Test
    void testGetOccupancy6() throws IOException {
        RoomRequest roomRequest = RoomRequest.of(1, 0);
        final OccupancyManager om =
                OccupancyManager.of(0, 0, 1, 374);
        final OccupancyManager occupancy = service.getOccupancy(roomRequest);
        assertNotNull(occupancy);
        assertEquals(om.getAllocatedPremiumRooms(), occupancy.getAllocatedPremiumRooms());
        assertEquals(om.getAllocatedEconomyRooms(), om.getAllocatedEconomyRooms());
        assertEquals(om.getTotalEconomyPrice(), occupancy.getTotalEconomyPrice());
        assertEquals(om.getTotalPremiumPrice(), occupancy.getTotalPremiumPrice());
        assertEquals(om.getGrandTotal(), occupancy.getGrandTotal());
    }

    @Test
    void testGetOccupancy7() throws IOException {
        RoomRequest roomRequest = RoomRequest.of(0, 0);
        final OccupancyManager om =
                OccupancyManager.of(0, 0, 0, 0);
        final OccupancyManager occupancy = service.getOccupancy(roomRequest);
        assertNotNull(occupancy);
        assertEquals(om.getAllocatedPremiumRooms(), occupancy.getAllocatedPremiumRooms());
        assertEquals(om.getAllocatedEconomyRooms(), om.getAllocatedEconomyRooms());
        assertEquals(om.getTotalEconomyPrice(), occupancy.getTotalEconomyPrice());
        assertEquals(om.getTotalPremiumPrice(), occupancy.getTotalPremiumPrice());
        assertEquals(om.getGrandTotal(), occupancy.getGrandTotal());
    }


}
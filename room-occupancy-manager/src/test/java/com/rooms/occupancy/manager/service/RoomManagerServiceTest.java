package com.rooms.occupancy.manager.service;

import com.rooms.occupancy.manager.beans.OccupancyManager;
import com.rooms.occupancy.manager.beans.RoomRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomManagerServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(RoomManagerServiceTest.class);


    RoomManagerService service = new RoomManagerService();

    @Test
    void testGetOccupancy() throws IOException {
        //Guests: 22, 23, 45, 99, 100, 101, 115, 155, 209, 374

        RoomRequest roomRequest = RoomRequest.of(3, 3);
        OccupancyManager occupancyManager =
                OccupancyManager.of(167, 3, 3, 738);
        OccupancyManager occupancy = service.getOccupancy(roomRequest);
        print(occupancy);
        print(occupancyManager);
        LOG.debug("------------------------------------");
        assertNotNull(occupancy);

    }

    @Test
    void testGetOccupancy2() throws IOException {
        //Guests: 22, 23, 45, 99, 100, 101, 115, 155, 209, 374

        RoomRequest roomRequest = RoomRequest.of(7, 5);
        OccupancyManager occupancyManager =
                OccupancyManager.of(189, 4, 6, 1054);
        OccupancyManager occupancy = service.getOccupancy(roomRequest);
        print(occupancy);
        print(occupancyManager);
        LOG.debug("------------------------------------");
        assertNotNull(occupancy);
    }

    @Test
    void testGetOccupancy3() throws IOException {
        //Guests: 22, 23, 45, 99, 100, 101, 115, 155, 209, 374

        RoomRequest roomRequest = RoomRequest.of(2, 7);
        OccupancyManager occupancyManager =
                OccupancyManager.of(189, 4, 2, 583);
        OccupancyManager occupancy = service.getOccupancy(roomRequest);
        print(occupancy);
        print(occupancyManager);
        LOG.debug("------------------------------------");
        assertNotNull(occupancy);
    }

    @Test
    void testGetOccupancy4() throws IOException {
        //Guests: 22, 23, 45, 99, 100, 101, 115, 155, 209, 374

        RoomRequest roomRequest = RoomRequest.of(7, 1);
        final OccupancyManager occupancyManager =
                OccupancyManager.of(45, 1, 7, 1153);
        final OccupancyManager occupancy = service.getOccupancy(roomRequest);
        print(occupancy);
        print(occupancyManager);
        LOG.debug("------------------------------------");
        assertNotNull(occupancy);
//        assertEquals(occupancy.getAllocatedPremiumRooms(), );
    }

    private static void print(final OccupancyManager occupancy) {
        LOG.debug("Premium: Rooms:{}, Price:{} ; Economy: Rooms:{}, Price:{} %n",
                occupancy.getAllocatedPremiumRooms(),
                occupancy.getTotalPremiumPrice(),
                occupancy.getAllocatedEconomyRooms(),
                occupancy.getTotalEconomyPrice());
    }

}
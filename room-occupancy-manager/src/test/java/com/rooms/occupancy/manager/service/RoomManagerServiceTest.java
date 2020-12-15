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
        //Guests: 22, 23, 45, 99, 100, 101, 115, 155, 209, 374

        RoomRequest roomRequest = RoomRequest.of(3, 3);
        OccupancyManager occupancyManager =
                OccupancyManager.of(167, 3, 3, 738);
        OccupancyManager occupancy = service.getOccupancy(roomRequest);
        print(occupancy);
        print(occupancyManager);
        System.out.println("------------------------------------");
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
        System.out.println("------------------------------------");
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
        System.out.println("------------------------------------");
        assertNotNull(occupancy);
    }

    @Test
    void testGetOccupancy4() throws IOException {
        //Guests: 22, 23, 45, 99, 100, 101, 115, 155, 209, 374

        RoomRequest roomRequest = RoomRequest.of(7, 1);
        OccupancyManager occupancyManager =
                OccupancyManager.of(45, 1, 7, 1153);
        OccupancyManager occupancy = service.getOccupancy(roomRequest);
        print(occupancy);
        print(occupancyManager);
        System.out.println("------------------------------------");
        assertNotNull(occupancy);
//        assertEquals(occupancy.getAllocatedPremiumRooms(), );
    }

    private static void print(OccupancyManager occupancy) {
        System.out.printf("Premium: Rooms:%d, Price:%d ; Economy: Rooms:%d, Price:%d %n",
                occupancy.getAllocatedPremiumRooms(),
                occupancy.getTotalPremiumPrice(),
                occupancy.getAllocatedEconomyRooms(),
                occupancy.getTotalEconomyPrice());
    }

}
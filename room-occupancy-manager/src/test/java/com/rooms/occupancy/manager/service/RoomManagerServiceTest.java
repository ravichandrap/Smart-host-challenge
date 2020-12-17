package com.rooms.occupancy.manager.service;

import com.rooms.occupancy.manager.beans.OccupancyManager;
import com.rooms.occupancy.manager.beans.RoomRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomManagerServiceTest {

    private final RoomManagerService service = new RoomManagerService();

    @Test
    void test_Get_Occupancy_Both_Rooms_Equal()  {
        final Optional<OccupancyManager> optional = service.getOccupancy(new RoomRequest(3, 3));
        if(optional.isPresent()) {
            final OccupancyManager occupancy = optional.get();
            assertEquals(3, occupancy.getAllocatedPremiumRooms(), "Premium rooms allocate is equal");
            assertEquals(3, occupancy.getAllocatedEconomyRooms(), "Economy rooms allocate is equal");
            assertEquals(167, occupancy.getTotalEconomyPrice(), "Total Economy price cost");
            assertEquals(738, occupancy.getTotalPremiumPrice(), "Total Premium price cost");
        }
    }

    @Test
    void test_Get_Occupancy_Premium_More_than_Economy()  {
        final Optional<OccupancyManager> optional = service.getOccupancy(new RoomRequest(7, 5));
        if(optional.isPresent()) {
            final OccupancyManager occupancy = optional.get();
            assertEquals(6, occupancy.getAllocatedPremiumRooms(), "Premium rooms allocate is equal");
            assertEquals(4, occupancy.getAllocatedEconomyRooms(), "Economy rooms allocate is equal");
            assertEquals(189, occupancy.getTotalEconomyPrice(), "Total Economy price cost");
            assertEquals(1054, occupancy.getTotalPremiumPrice(), "Total Premium price cost");
        }
    }

    @Test
    void test_Get_Occupancy_Economy_More_than_Premium()  {
        final Optional<OccupancyManager> optional = service.getOccupancy(new RoomRequest(2, 7));
        if(optional.isPresent()) {
            final OccupancyManager occupancy = optional.get();
            assertEquals(2, occupancy.getAllocatedPremiumRooms(), "Premium rooms allocate is equal");
            assertEquals(4, occupancy.getAllocatedEconomyRooms(), "Economy rooms allocate is equal");
            assertEquals(189, occupancy.getTotalEconomyPrice(), "Total Economy price cost");
            assertEquals(583, occupancy.getTotalPremiumPrice(), "Total Premium price cost");
        }
    }

    @Test
    void test_Get_Occupancy()  {
        final Optional<OccupancyManager> optional = service.getOccupancy(new RoomRequest(7, 1));
        if(optional.isPresent()) {
            final OccupancyManager occupancy = optional.get();
            assertEquals(99, occupancy.getTotalEconomyPrice(), "Total Economy price cost");
            assertEquals(1, occupancy.getAllocatedEconomyRooms(), "Economy rooms allocate is equal");
            assertEquals(7, occupancy.getAllocatedPremiumRooms(), "Premium rooms allocate is equal");
            assertEquals(1099, occupancy.getTotalPremiumPrice(), "Total Premium price cost");
        }
    }

    @Test
    void test_Get_Occupancy_with_zero_premium_one_economy_rooms()  {
        final Optional<OccupancyManager> optional = service.getOccupancy(new RoomRequest(0, 1));
        if(optional.isPresent()) {
            final OccupancyManager occupancy = optional.get();
            assertEquals(0, occupancy.getTotalPremiumPrice(), "Total Premium price cost");
            assertEquals(0, occupancy.getAllocatedPremiumRooms(), "Premium rooms allocate is equal");
            assertEquals(1, occupancy.getAllocatedEconomyRooms(), "Economy rooms allocate is equal");
            assertEquals(99, occupancy.getTotalEconomyPrice(), "Total Economy price cost");
        }
    }

    @Test
    void test_Get_Occupancy_with_one_premium_zero_economy()  {
        final Optional<OccupancyManager> optional = service.getOccupancy(new RoomRequest(1, 0));
        if(optional.isPresent()) {
            final OccupancyManager occupancy = optional.get();
            assertEquals(0, occupancy.getAllocatedEconomyRooms(), "Economy rooms allocate is equal");
            assertEquals(0, occupancy.getTotalEconomyPrice(), "Total Economy price cost");
            assertEquals(1, occupancy.getAllocatedPremiumRooms(), "Premium rooms allocate is equal");
            assertEquals(374, occupancy.getTotalPremiumPrice(), "Total Premium price cost");
        }
    }

    @Test
    void test_Get_Occupancy_With_Zero_Rooms()  {
        final Optional<OccupancyManager> occupancy = service.getOccupancy(new RoomRequest(0, 0));
        assertTrue(occupancy.isEmpty(), "Occupancy is empty when rooms are not available");
    }

}
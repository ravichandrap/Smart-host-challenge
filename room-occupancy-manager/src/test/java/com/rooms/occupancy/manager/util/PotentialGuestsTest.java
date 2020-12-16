package com.rooms.occupancy.manager.util;

import com.rooms.occupancy.manager.beans.PotentialGuest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

@SpringBootTest
class PotentialGuestsTest {

    @InjectMocks
    private PotentialGuests util;

    @Test
    void testGetPotentialGuests() throws IOException {
        PotentialGuest potentialGuests = PotentialGuests.getPotentialGuests();
        assertNotNull(potentialGuests);
    }
}
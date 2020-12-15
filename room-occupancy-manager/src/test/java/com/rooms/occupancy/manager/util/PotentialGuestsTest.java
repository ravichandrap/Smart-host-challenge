package com.rooms.occupancy.manager.util;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class PotentialGuestsTest {

    @InjectMocks
    private PotentialGuests util;

    @Test
    void testGetPotentialGuests() throws IOException {
        PotentialGuests.getPotentialGuests();
    }
}
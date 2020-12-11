package com.rooms.occupancy.manager.util;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomUtilTest {

    @InjectMocks
    private RoomUtil util;

    @Test
    void testGetPotentialGuests() throws IOException {
        RoomUtil.getPotentialGuests();
    }
}
package com.rooms.occupancy.manager.util;

import com.rooms.occupancy.manager.beans.PotentialGuest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@SpringBootTest
class PotentialGuestUtilsTest {

    @Test
    void testGetPotentialGuests() {
        final Optional<PotentialGuest> potentialGuests = PotentialGuestUtils.getPotentialGuests();
        assertTrue(potentialGuests.isPresent(), "Check guest object present or not");
    }

    @Test
    void test_size_of_economy_premium_rooms() {
        final Optional<PotentialGuest> potentialGuests = PotentialGuestUtils.getPotentialGuests();
        assertTrue(potentialGuests.isPresent(), "Check guest object present or not");
        PotentialGuest guests = potentialGuests.get();
        assertTrue(guests.getEconomy().size() > 0, "Check Economy guest are present or not" );
        assertTrue(guests.getPremium().size() > 0, "Check Premium guest are present or not" );
    }
}
package com.rooms.occupancy.manager.service;

import com.rooms.occupancy.manager.beans.OccupancyManager;
import com.rooms.occupancy.manager.beans.PotentialGuest;
import com.rooms.occupancy.manager.beans.RoomRequest;
import com.rooms.occupancy.manager.util.PotentialGuests;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class RoomManagerService {

    public OccupancyManager getOccupancy(RoomRequest room) throws IOException {
        PotentialGuest potentialGuests = PotentialGuests.getPotentialGuests();
        return calculateOccupancy(room, potentialGuests);
    }

    public OccupancyManager calculateOccupancy(RoomRequest room, PotentialGuest potentialGuests) {
        List<Integer> premiumGuests = potentialGuests.getPremium();
        List<Integer> economyGuests = potentialGuests.getEconomy();
        int economyGuestsCount = economyGuests.size();

        int allocatedPremiumRooms = calculatePremium(room, potentialGuests);

        //Economy
        /*
        1. rooms is equal to guests
        2. rooms are less than guests
        3. rooms are more than guests
        3.1: allocate remaining rooms to economy guest if present.
         */
        int totalEconomyPrice;
        int allocatedEconomyRooms = 0;
        int totalPremiumPrice = filterGuests(premiumGuests, allocatedPremiumRooms);

        if (economyGuestsCount == room.getEconomyRooms()) {
            allocatedEconomyRooms = room.getEconomyRooms();
            totalEconomyPrice = economyGuests
                    .stream()
                    .reduce(0, Integer::sum);
        } else if (room.getEconomyRooms() < economyGuestsCount) {
            allocatedEconomyRooms = room.getEconomyRooms();
            final int premiumGuestsCount = ((premiumGuests == null || premiumGuests.isEmpty()) ? 0 : premiumGuests.size());
            totalEconomyPrice = filterGuests(economyGuests, room.getEconomyRooms());
            int remainingEconomyGuests = economyGuestsCount - room.getEconomyRooms();
            int remainingPremiumRooms = room.getPremiumRooms() - premiumGuestsCount;

            if (remainingPremiumRooms > 0 && remainingEconomyGuests > 0) {
                int outBound = allocatedEconomyRooms + Math.min(remainingPremiumRooms, economyGuestsCount);

                for (int i = allocatedEconomyRooms; i < outBound; i++) {
                    totalPremiumPrice += economyGuests.get(i);
                    allocatedPremiumRooms++;
                }
            }

        } else {
            allocatedEconomyRooms = room.getEconomyRooms();
            totalEconomyPrice = economyGuests
                    .stream()
                    .limit(room.getEconomyRooms())
                    .reduce(0, Integer::sum);
        }
        return OccupancyManager.of(totalEconomyPrice,
                allocatedEconomyRooms,
                allocatedPremiumRooms,
                totalPremiumPrice);
    }

    private int calculatePremium(RoomRequest room, PotentialGuest potentialGuests) {
        final int premiumSize = potentialGuests.getPremium().size();
        if (room.getPremiumRooms() <= premiumSize)
            return room.getPremiumRooms();
        else if (room.getPremiumRooms() > premiumSize)
            return premiumSize;
        return 0;
    }

    private int filterGuests(List<Integer> list,
                             int filterEnd) {
        if (list == null || list.isEmpty())
            return 0;

        if (filterEnd == 0)
            return list.stream().reduce(0, Integer::sum);

        return list.subList(0, filterEnd).stream()
                .reduce(0, Integer::sum);
    }
}



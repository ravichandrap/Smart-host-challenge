package com.rooms.occupancy.manager.service;

import com.rooms.occupancy.manager.beans.OccupancyManager;
import com.rooms.occupancy.manager.beans.PotentialGuest;
import com.rooms.occupancy.manager.beans.RoomRequest;
import com.rooms.occupancy.manager.util.PotentialGuests;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomManagerService {

    public OccupancyManager getOccupancy(RoomRequest room)  {
        PotentialGuest potentialGuests = PotentialGuests.getPotentialGuests();
        return calculateOccupancy(room, potentialGuests);
    }

    public OccupancyManager calculateOccupancy(RoomRequest room, PotentialGuest potentialGuests) {
        List<Integer> premiumGuests = potentialGuests.getPremium();
        List<Integer> economyGuests = potentialGuests.getEconomy();
        int economyGuestsCount = economyGuests.size();

        int allocatedPremiumRooms = calculatePremiumRooms(room, potentialGuests);

        //Economy
        /*
        1. rooms is equal to guests
        2. rooms are less than guests
        3. rooms are more than guests
        3.1: allocate remaining rooms to economy guest if present.
         */
        int totalEconomyPrice;
        int allocatedEconomyRooms = 0;
        int totalPremiumPrice = calculateGuestPrice(premiumGuests, allocatedPremiumRooms);


        if (economyGuestsCount == room.getEconomyRooms()) {
            allocatedEconomyRooms = room.getEconomyRooms();
//            totalEconomyPrice = economyGuests
//                    .stream()
//                    .reduce(0, Integer::sum);
            totalEconomyPrice = calculateGuestPrice(economyGuests, 0);
        } else if (room.getEconomyRooms() < economyGuestsCount) {
            allocatedEconomyRooms = room.getEconomyRooms();
            totalEconomyPrice = calculateGuestPrice(economyGuests, room.getEconomyRooms());

            final int premiumGuestsCount = ((premiumGuests == null || premiumGuests.isEmpty()) ? 0 : premiumGuests.size());
            int remainingEconomyGuests = economyGuestsCount - room.getEconomyRooms();
            int remainingPremiumRooms = room.getPremiumRooms() - premiumGuestsCount;

            // Allocating remaining premium rooms to economy guests.
            if (remainingPremiumRooms > 0 && remainingEconomyGuests > 0) {
                int outBound = allocatedEconomyRooms + Math.min(remainingPremiumRooms, economyGuestsCount);

                for(Integer eg: economyGuests.subList(allocatedEconomyRooms, outBound)) {
                    totalPremiumPrice += eg;
                    allocatedPremiumRooms++;
                }

//                for (int i = allocatedEconomyRooms; i < outBound; i++) {
//                    totalPremiumPrice += economyGuests.get(i);
//                    allocatedPremiumRooms++;
//                }
            }

        } else {
            allocatedEconomyRooms = economyGuestsCount;//room.getEconomyRooms();
//            totalEconomyPrice = economyGuests
//                    .stream()
//                    .limit(room.getEconomyRooms())
//                    .reduce(0, Integer::sum);
            totalEconomyPrice = calculateGuestPrice(economyGuests, allocatedEconomyRooms);
        }


        return OccupancyManager.of(totalEconomyPrice,
                allocatedEconomyRooms,
                allocatedPremiumRooms,
                totalPremiumPrice);
    }

    private int calculatePremiumRooms(RoomRequest room, PotentialGuest potentialGuests) {
        final int premiumSize = potentialGuests.getPremium().size();
        return room.getPremiumRooms() <= premiumSize ? room.getPremiumRooms() : premiumSize;
    }

    private int calculateGuestPrice(List<Integer> guests, int limit) {
        if (guests == null || guests.isEmpty())
            return 0;

        if (limit == 0 || guests.size() == limit)
            return guests.stream().reduce(0, Integer::sum);

        return guests.subList(0, limit)
                .stream()
                .reduce(0, Integer::sum);
    }
}



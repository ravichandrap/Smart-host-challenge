package com.rooms.occupancy.manager.service;

import com.rooms.occupancy.manager.beans.OccupancyManager;
import com.rooms.occupancy.manager.beans.RoomRequest;
import com.rooms.occupancy.manager.util.RoomType;
import com.rooms.occupancy.manager.util.PotentialGuests;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class RoomManagerService {


    public OccupancyManager getOccupancy(RoomRequest room) throws IOException {
        Map<RoomType, List<Integer>> guestMap = PotentialGuests.getPotentialGuests();

        return calculateOccupancy(room, guestMap);
    }

    public OccupancyManager calculateOccupancy(RoomRequest room, Map<RoomType, List<Integer>> guestMap) {
        List<Integer> premiumGuests = guestMap.get(RoomType.PREMIUM);
        List<Integer> economyGuests = guestMap.get(RoomType.ECONOMY);

        int premiumGuestsCount = premiumGuests.size();
        int economyGuestsCount = economyGuests.size();

        //total rooms allocate
        int allocatedPremiumRooms = 0;
        int allocatedEconomyRooms = 0;

        //total amount
        int totalPremiumPrice;
        int totalEconomyPrice;
        int remainingPremiumRooms = 0;

        if(room.getPremiumRooms() <= premiumGuestsCount) {
            allocatedPremiumRooms = room.getPremiumRooms();
        } else if(room.getPremiumRooms() > premiumGuestsCount) {
            allocatedPremiumRooms = premiumGuestsCount;
            remainingPremiumRooms = room.getPremiumRooms() - premiumGuestsCount;
        }

        totalPremiumPrice = premiumGuests
                            .stream()
                            .limit(allocatedPremiumRooms)
                            .reduce(0, Integer::sum);
        //Economy
        /*
        1. rooms is equal to guests
        2. rooms are less than guests
        3. rooms are more than guests
        3.1: allocate remaining rooms to economy guest if present.
         */
        if(economyGuestsCount == room.getEconomyRooms()) {
            allocatedEconomyRooms = room.getEconomyRooms();
            totalEconomyPrice = economyGuests
                                .stream()
                                .reduce(0, Integer::sum);
        } else if(room.getEconomyRooms() < economyGuestsCount) {
            //
            allocatedEconomyRooms = room.getEconomyRooms();
            totalEconomyPrice = economyGuests
                                .stream()
                                .limit(room.getEconomyRooms())
                                .reduce(0, Integer::sum);
            int remainingEconomyGuests =  economyGuestsCount - room.getEconomyRooms();

            if(remainingPremiumRooms > 0 && remainingEconomyGuests > 0) {

                int outBound = allocatedEconomyRooms+ Math.min(remainingPremiumRooms, economyGuestsCount);

                for(int i=allocatedEconomyRooms;i<outBound;i++) {
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
        return OccupancyManager.of(totalEconomyPrice, allocatedEconomyRooms,allocatedPremiumRooms, totalPremiumPrice);
    }
}



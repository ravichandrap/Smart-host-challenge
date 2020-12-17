package com.rooms.occupancy.manager.service;

import com.rooms.occupancy.manager.beans.OccupancyManager;
import com.rooms.occupancy.manager.beans.PotentialGuest;
import com.rooms.occupancy.manager.beans.RoomRequest;
import com.rooms.occupancy.manager.exception.PotentialGuestsException;
import com.rooms.occupancy.manager.util.PotentialGuestUtils;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class RoomManagerService {

    public Optional<OccupancyManager> getOccupancy(final RoomRequest room)  {
        if(room.getEconomyRooms() <= 0 && room.getPremiumRooms() <= 0) {
            return Optional.empty();
        }

        final Optional<PotentialGuest> potentialGuests = PotentialGuestUtils.getPotentialGuests();
        if(potentialGuests.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(calculateOccupancy(room, potentialGuests.get()));
        }
    }

    /**
     * Calculate the Occupancy for given input rooms and potential guests.
     * @param room Number of available rooms
     * @param potentialGuests Guests waiting for rooms allotment.
     * @return Occupency Manager contains total price and allotted rooms.
     */
    public OccupancyManager calculateOccupancy(final RoomRequest room, final PotentialGuest potentialGuests) {
        final OccupancyManager om = new OccupancyManager();

        if(room.getPremiumRooms() > 0) {
            om.setAllocatedPremiumRooms(calculatePremiumRooms(room.getPremiumRooms(), potentialGuests));
            om.setTotalPremiumPrice(calculateGuestPrice(potentialGuests.getPremium(), om.getAllocatedPremiumRooms()));
        }

        if(room.getEconomyRooms() <= 0) {
            return om;
        }

        /*
         * This condition check the room and guest are equal or not,
         * if yes calculate the price and allocated rooms
         */
        if (potentialGuests.getEconomy().size() == room.getEconomyRooms()) {
            om.setAllocatedEconomyRooms(room.getEconomyRooms());
            om.setTotalEconomyPrice(calculateGuestPrice(potentialGuests.getEconomy(), 0));
        } else if (room.getEconomyRooms() < potentialGuests.getEconomy().size()) {
            /*
             * this Condition satisfies when less rooms then guest.
             * Calculate the rooms and price for Economy,
             * if Premium rooms are available and Economy guests are still waiting for rooms.
             *  Allocate Premium rooms to the Economy guest and calculate price and rooms
             */
            om.setTotalEconomyPrice(calculateGuestPrice(potentialGuests.getEconomy(), room.getEconomyRooms()));
            om.setAllocatedEconomyRooms(room.getEconomyRooms());
            final int premiumGuestsCount = potentialGuests.getPremium() == null || potentialGuests.getPremium().isEmpty() ? 0 : potentialGuests.getPremium().size();
            final int remainingEconomyGuests = potentialGuests.getEconomy().size() - room.getEconomyRooms();
            final int remainingPremiumRooms = room.getPremiumRooms() - premiumGuestsCount;

            // Allocating remaining premium rooms to economy guests.
            if (remainingPremiumRooms > 0 && remainingEconomyGuests > 0) {
                final int outBound = room.getEconomyRooms() + Math.min(remainingPremiumRooms, potentialGuests.getEconomy().size());

                for(final Integer eg: potentialGuests.getEconomy().subList(room.getEconomyRooms(), outBound)) {
                    om.setTotalPremiumPrice(om.getTotalPremiumPrice() + eg);
                    om.setAllocatedPremiumRooms(om.getAllocatedPremiumRooms() + 1);
                }
            }

        } else {
            /*
             * Calculate Economy allocated rooms and price when less guest then rooms.
             */
            om.setAllocatedEconomyRooms(potentialGuests.getEconomy().size());
            om.setTotalEconomyPrice(calculateGuestPrice(potentialGuests.getEconomy(), om.getAllocatedEconomyRooms()));
        }

        return om;
    }

    /**
     * Calculate the occupancy Premium rooms based on the input Premium rooms and Premium guest.
     *
     * @param rooms Number of Premium rooms
     * @param potentialGuests Premium Guests
     * @return Number of room allocated
     *
     */
    private int calculatePremiumRooms(final int rooms, final PotentialGuest potentialGuests) {
        final int premiumSize = potentialGuests.getPremium().size();
        return Math.min(rooms, premiumSize);
    }

    /**
     * Calculate total price for given guest price filter by limit .
     * @param guests guest price list
     * @param limit out bound limit
     * @return calculate the sum of all guests with limit
     */
    private int calculateGuestPrice(final List<Integer> guests, final int limit) {
        if (guests == null || guests.isEmpty()) {
            return 0;
        }

        if (limit == 0 || guests.size() == limit) {
            return guests.stream().reduce(0, Integer::sum);
        }

        return guests.subList(0, limit)
                .stream()
                .reduce(0, Integer::sum);
    }
}



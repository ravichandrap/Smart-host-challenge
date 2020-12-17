package com.rooms.occupancy.manager.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OccupancyManager {
    private int totalEconomyPrice;
    private int allocatedEconomyRooms;
    private int allocatedPremiumRooms;
    private int totalPremiumPrice;
}

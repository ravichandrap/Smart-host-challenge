package com.rooms.occupancy.manager.beans;

public class OccupancyManager {
    private int totalEconomyPrice;
    private int allocatedEconomyRooms;
    private int allocatedPremiumRooms;
    private int totalPremiumPrice;

    private OccupancyManager(final int totalEconomyPrice, final int allocatedEconomyRooms, final int allocatedPremiumRooms, final int totalPremiumPrice) {
        this.totalEconomyPrice = totalEconomyPrice;
        this.allocatedEconomyRooms = allocatedEconomyRooms;
        this.allocatedPremiumRooms = allocatedPremiumRooms;
        this.totalPremiumPrice = totalPremiumPrice;
    }

    public static OccupancyManager of(final int totalEconomyPrice,
                                      final int allocatedEconomyRooms,
                                      final int allocatedPremiumRooms,
                                      final int totalPremiumPrice) {

        return new OccupancyManager(totalEconomyPrice,
                allocatedEconomyRooms,
                allocatedPremiumRooms,
                totalPremiumPrice);
    }

    public int getTotalEconomyPrice() {
        return totalEconomyPrice;
    }

    public void setTotalEconomyPrice(final int totalEconomyPrice) {
        this.totalEconomyPrice = totalEconomyPrice;
    }

    public int getAllocatedEconomyRooms() {
        return allocatedEconomyRooms;
    }

    public void setAllocatedEconomyRooms(final int allocatedEconomyRooms) {
        this.allocatedEconomyRooms = allocatedEconomyRooms;
    }

    public int getAllocatedPremiumRooms() {
        return allocatedPremiumRooms;
    }

    public void setAllocatedPremiumRooms(final int allocatedPremiumRooms) {
        this.allocatedPremiumRooms = allocatedPremiumRooms;
    }

    public int getTotalPremiumPrice() {
        return totalPremiumPrice;
    }

    public void setTotalPremiumPrice(final int totalPremiumPrice) {
        this.totalPremiumPrice = totalPremiumPrice;
    }

    public int getGrandTotal() {
        return this.totalEconomyPrice + this.totalPremiumPrice;
    }

}

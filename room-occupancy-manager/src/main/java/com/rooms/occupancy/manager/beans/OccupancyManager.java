package com.rooms.occupancy.manager.beans;

public class OccupancyManager {
    private int totalEconomyPrice;
    private int allocatedEconomyRooms;
    private int allocatedPremiumRooms;
    private int totalPremiumPrice;
    private int grandTotal;

    private OccupancyManager(int totalEconomyPrice, int allocatedEconomyRooms, int allocatedPremiumRooms, int totalPremiumPrice) {
        this.totalEconomyPrice = totalEconomyPrice;
        this.allocatedEconomyRooms = allocatedEconomyRooms;
        this.allocatedPremiumRooms = allocatedPremiumRooms;
        this.totalPremiumPrice = totalPremiumPrice;
    }
    public static OccupancyManager of(int totalEconomyPrice,
                                      int allocatedEconomyRooms,
                                      int allocatedPremiumRooms,
                                      int totalPremiumPrice) {

        OccupancyManager om = new OccupancyManager(totalEconomyPrice,
                                allocatedEconomyRooms,
                                allocatedPremiumRooms,
                                totalPremiumPrice);
        om.grandTotal = totalEconomyPrice + totalPremiumPrice;
        return om;
    }

    public int getTotalEconomyPrice() {
        return totalEconomyPrice;
    }

    public void setTotalEconomyPrice(int totalEconomyPrice) {
        this.totalEconomyPrice = totalEconomyPrice;
    }

    public int getAllocatedEconomyRooms() {
        return allocatedEconomyRooms;
    }

    public void setAllocatedEconomyRooms(int allocatedEconomyRooms) {
        this.allocatedEconomyRooms = allocatedEconomyRooms;
    }

    public int getAllocatedPremiumRooms() {
        return allocatedPremiumRooms;
    }

    public void setAllocatedPremiumRooms(int allocatedPremiumRooms) {
        this.allocatedPremiumRooms = allocatedPremiumRooms;
    }

    public int getTotalPremiumPrice() {
        return totalPremiumPrice;
    }

    public void setTotalPremiumPrice(int totalPremiumPrice) {
        this.totalPremiumPrice = totalPremiumPrice;
    }

    public int getGrandTotal() {
        return grandTotal;
    }

}

package com.rooms.occupancy.manager.beans;

public class OccupancyManager {
    private Integer totalEconomyPrice = 0;
    private Integer allocatedEconomyRooms = 0;
    private Integer allocatedPremiumRooms = 0;
    private Integer totalPremiumPrice = 0;
    public OccupancyManager() {

    }
    public static OccupancyManager empty() {
        return new OccupancyManager(0,0,0,0);

    }

    public OccupancyManager(final Integer totalEconomyPrice, final Integer allocatedEconomyRooms, final Integer allocatedPremiumRooms, final Integer totalPremiumPrice) {
        this.totalEconomyPrice = totalEconomyPrice;
        this.allocatedEconomyRooms = allocatedEconomyRooms;
        this.allocatedPremiumRooms = allocatedPremiumRooms;
        this.totalPremiumPrice = totalPremiumPrice;
    }

    public static OccupancyManager of(final Integer totalEconomyPrice,
                                      final Integer allocatedEconomyRooms,
                                      final Integer allocatedPremiumRooms,
                                      final Integer totalPremiumPrice) {

        return new OccupancyManager(totalEconomyPrice,
                allocatedEconomyRooms,
                allocatedPremiumRooms,
                totalPremiumPrice);
    }

    public Integer getTotalEconomyPrice() {
        return totalEconomyPrice;
    }

    public void setTotalEconomyPrice(final Integer totalEconomyPrice) {
        this.totalEconomyPrice = totalEconomyPrice;
    }

    public Integer getAllocatedEconomyRooms() {
        return allocatedEconomyRooms;
    }

    public void setAllocatedEconomyRooms(final Integer allocatedEconomyRooms) {
        this.allocatedEconomyRooms = allocatedEconomyRooms;
    }

    public Integer getAllocatedPremiumRooms() {
        return allocatedPremiumRooms;
    }

    public void setAllocatedPremiumRooms(final Integer allocatedPremiumRooms) {
        this.allocatedPremiumRooms = allocatedPremiumRooms;
    }

    public Integer getTotalPremiumPrice() {
        return totalPremiumPrice;
    }

    public void setTotalPremiumPrice(final Integer totalPremiumPrice) {
        this.totalPremiumPrice = totalPremiumPrice;
    }

    public Integer getGrandTotal() {
        return this.totalEconomyPrice + this.totalPremiumPrice;
    }

}

package com.rooms.occupancy.manager.beans;

public class RoomRequest {
    private Long premiumRooms;
    private Long economyRooms;

    public RoomRequest(Long premiumRooms, Long economyRooms) {
        this.premiumRooms = premiumRooms;
        this.economyRooms = economyRooms;
    }
    public static RoomRequest of(Long premiumRooms, Long economyRooms) {
        return new RoomRequest(premiumRooms, economyRooms);
    }

    public Long getPremiumRooms() {
        return premiumRooms;
    }

    public void setPremiumRooms(Long premiumRooms) {
        this.premiumRooms = premiumRooms;
    }

    public Long getEconomyRooms() {
        return economyRooms;
    }

    public void setEconomyRooms(Long economyRooms) {
        this.economyRooms = economyRooms;
    }

    @Override
    public String toString() {
        return "RoomRequest{" +
                "premiumRooms=" + premiumRooms +
                ", economyRooms=" + economyRooms +
                '}';
    }
}

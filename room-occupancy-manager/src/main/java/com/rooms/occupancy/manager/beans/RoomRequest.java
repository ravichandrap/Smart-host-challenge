package com.rooms.occupancy.manager.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomRequest {
    private Integer premiumRooms;
    private Integer economyRooms;
}

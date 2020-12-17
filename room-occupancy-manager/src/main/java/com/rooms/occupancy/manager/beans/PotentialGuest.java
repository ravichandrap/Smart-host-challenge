package com.rooms.occupancy.manager.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PotentialGuest {
    private List<Integer> premium;
    private List<Integer> economy;
}

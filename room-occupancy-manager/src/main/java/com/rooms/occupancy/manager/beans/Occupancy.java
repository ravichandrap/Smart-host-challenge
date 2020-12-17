package com.rooms.occupancy.manager.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Occupancy {
    private Integer premium;
    private Integer economy;
}

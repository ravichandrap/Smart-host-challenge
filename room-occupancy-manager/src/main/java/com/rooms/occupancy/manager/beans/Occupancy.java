package com.rooms.occupancy.manager.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public class Occupancy {
    private Long premium;
    private Long economy;

    Occupancy(Long premium,
              Long economy) {
        this.premium = premium;
        this.economy = economy;
    }
    public static Occupancy of(Long premium,
                               Long economy) {
    return new Occupancy(premium, economy);
    }

    public Long getPremium() {
        return premium;
    }

    public void setPremium(Long premium) {
        this.premium = premium;
    }

    public Long getEconomy() {
        return economy;
    }

    public void setEconomy(Long economy) {
        this.economy = economy;
    }

    @Override
    public String toString() {
        return "Occupancy{" +
                "premium=" + premium +
                ", economy=" + economy +
                '}';
    }
}

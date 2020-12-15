package com.rooms.occupancy.manager.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public class Occupancy {
    private Integer premium;
    private Integer economy;

    Occupancy(Integer premium,
              Integer economy) {
        this.premium = premium;
        this.economy = economy;
    }
    public static Occupancy of(Integer premium,
                               Integer economy) {
    return new Occupancy(premium, economy);
    }

    public Integer getPremium() {
        return premium;
    }

    public void setPremium(Integer premium) {
        this.premium = premium;
    }

    public Integer getEconomy() {
        return economy;
    }

    public void setEconomy(Integer economy) {
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

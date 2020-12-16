package com.rooms.occupancy.manager.beans;

public class Occupancy {
    private Integer premium;
    private Integer economy;

    Occupancy(final Integer premium,
              final Integer economy) {
        this.premium = premium;
        this.economy = economy;
    }

    public static Occupancy of(final Integer premium,
                               final Integer economy) {
        return new Occupancy(premium, economy);
    }

    public Integer getPremium() {
        return premium;
    }

    public void setPremium(final Integer premium) {
        this.premium = premium;
    }

    public Integer getEconomy() {
        return economy;
    }

    public void setEconomy(final Integer economy) {
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

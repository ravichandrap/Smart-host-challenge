package com.rooms.occupancy.manager.beans;

import java.util.List;

public class PotentialGuest {
    List<Integer> premium;
    List<Integer> economy;

    private PotentialGuest(final List<Integer> premium, final List<Integer> economy) {
        this.economy = economy;
        this.premium = premium;
    }

    public static PotentialGuest of(final List<Integer> premium, final List<Integer> economy) {
        return new PotentialGuest(premium, economy);
    }

    public List<Integer> getPremium() {
        return premium;
    }

    public void setPremium(final List<Integer> premium) {
        this.premium = premium;
    }

    public List<Integer> getEconomy() {
        return economy;
    }

    public void setEconomy(final List<Integer> economy) {
        this.economy = economy;
    }
}

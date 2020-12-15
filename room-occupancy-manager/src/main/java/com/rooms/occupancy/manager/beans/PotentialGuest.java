package com.rooms.occupancy.manager.beans;

import java.util.List;

public class PotentialGuest {
    List<Integer> premium = null;
    List<Integer> economy = null;

    private PotentialGuest(List<Integer> premium, List<Integer> economy) {
       this.economy = economy;
       this.premium = premium;
    }
    public static PotentialGuest of(List<Integer> premium, List<Integer> economy) {
       return new PotentialGuest(premium, economy);
    }

    public List<Integer> getPremium() {
        return premium;
    }

    public void setPremium(List<Integer> premium) {
        this.premium = premium;
    }

    public List<Integer> getEconomy() {
        return economy;
    }

    public void setEconomy(List<Integer> economy) {
        this.economy = economy;
    }
}

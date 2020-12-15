package com.rooms.occupancy.manager.util;

import com.google.gson.*;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class PotentialGuests {

    private static final String GUESTS = "guests";
    private static final String FILE_NAME = "potential-guests.json";
    private static final int PREMIUM_START_PRICE = 100;
    private static Map<RoomType, List<Integer>> potentialGuests = null;

    private PotentialGuests() {}


    public static Map<RoomType, List<Integer>> getPotentialGuests()
            throws IOException {
        if(potentialGuests == null) {
            potentialGuests = readGuestData();
        }
        return potentialGuests;
    }

    private static Map<RoomType, List<Integer>> readGuestData() throws IOException {

        JsonArray guestsArray = readFile().get();
        List<Integer> premium = new ArrayList<>();
        List<Integer> economy = new ArrayList<>();

        for(JsonElement element: guestsArray) {
            int value = element.getAsInt();
            if(PREMIUM_START_PRICE > value) {
                economy.add(value);
            } else {
                premium.add(value);
            }
        }
        Map<RoomType, List<Integer>> guestMap = new HashMap<>(2);
        premium.sort(Collections.reverseOrder());
        economy.sort(Collections.reverseOrder());

        guestMap.put(RoomType.PREMIUM, premium);
        guestMap.put(RoomType.ECONOMY, economy);
        return guestMap;
    }

    private static Optional<JsonArray> readFile() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(FILE_NAME));
        JsonObject parser = JsonParser.parseReader(reader)
                                        .getAsJsonObject();
        JsonElement element = parser.get(GUESTS);
        //TO-DO :Exception handling
        return element == null
                ? Optional.empty()
                :Optional.of(element.getAsJsonArray());
    }
}

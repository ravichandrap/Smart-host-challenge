package com.rooms.occupancy.manager.util;

import com.google.gson.*;
import com.rooms.occupancy.manager.exception.FileNotFoundException;
import com.rooms.occupancy.manager.exception.PotentialGuestsException;

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

    public static Map<RoomType, List<Integer>> getPotentialGuests() {
        if(potentialGuests == null)
            potentialGuests = readGuestData();
        return potentialGuests;
    }

    private static Map<RoomType, List<Integer>> readGuestData() {
        Optional<JsonArray> optional = readFile();
        JsonArray guestsArray = optional.orElseThrow(() -> new PotentialGuestsException(FILE_NAME));
        return getRoomTypeListMap(guestsArray);
    }

    private static Map<RoomType, List<Integer>> getRoomTypeListMap(JsonArray guestsArray) {
        List<Integer> premium = new ArrayList<>();
        List<Integer> economy = new ArrayList<>();

        guestsArray.forEach(element -> {
            final int value = element.getAsInt();
            if (PREMIUM_START_PRICE > value) {
                economy.add(value);
            } else {
                premium.add(value);
            }
        });
        Map<RoomType, List<Integer>> guestMap = new EnumMap<>(RoomType.class);
        premium.sort(Collections.reverseOrder());
        economy.sort(Collections.reverseOrder());

        guestMap.put(RoomType.PREMIUM, premium);
        guestMap.put(RoomType.ECONOMY, economy);
        return guestMap;
    }

    private static Optional<JsonArray> readFile() {
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileNotFoundException(FILE_NAME);
        }

        JsonObject parser = JsonParser.parseReader(reader)
                                        .getAsJsonObject();
        JsonElement element = parser.get(GUESTS);
        if(element == null)
           throw new PotentialGuestsException(FILE_NAME);

        return Optional.of(element.getAsJsonArray());
    }
}

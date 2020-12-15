package com.rooms.occupancy.manager.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rooms.occupancy.manager.beans.PotentialGuest;
import com.rooms.occupancy.manager.exception.FileNotFoundException;
import com.rooms.occupancy.manager.exception.PotentialGuestsException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class PotentialGuests {

    private static final String GUESTS = "guests";
    private static final String FILE_NAME = "potential-guests.json";
    private static final int PREMIUM_START_PRICE = 100;
    private static final AtomicReference<PotentialGuest> potentialGuests = new AtomicReference<>();

    private PotentialGuests() {
    }

    public static PotentialGuest getPotentialGuests() {
        if (potentialGuests.get() == null)
            potentialGuests.set(readGuestData());
        return potentialGuests.get();
    }

    private static PotentialGuest readGuestData() {
        Optional<JsonArray> optional = readFile();
        JsonArray guestsArray = optional.orElseThrow(() -> new PotentialGuestsException(FILE_NAME));
        return getRoomTypeListMap(guestsArray);
    }

    private static PotentialGuest getRoomTypeListMap(JsonArray guestsArray) {
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
        premium.sort(Collections.reverseOrder());
        economy.sort(Collections.reverseOrder());

        return PotentialGuest.of(premium, economy);
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
        if (element == null)
            throw new PotentialGuestsException(FILE_NAME);

        return Optional.of(element.getAsJsonArray());
    }
}

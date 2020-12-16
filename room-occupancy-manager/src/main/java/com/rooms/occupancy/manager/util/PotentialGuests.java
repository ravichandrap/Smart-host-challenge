package com.rooms.occupancy.manager.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rooms.occupancy.manager.beans.PotentialGuest;
import com.rooms.occupancy.manager.exception.FileNotFoundException;
import com.rooms.occupancy.manager.exception.PotentialGuestsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PotentialGuests {

    private static final Logger LOG = LoggerFactory.getLogger(PotentialGuests.class);

    private static final String GUESTS = "guests";
    private static final String FILE_NAME = "potential-guests.json";
    private static final int PREMIUM_START_PRICE = 100;
    private static final PotentialGuest ptlGuests = readGuestData();

    private PotentialGuests() {
    }

    public static PotentialGuest getPotentialGuests() {
        return ptlGuests;
    }

    private static PotentialGuest readGuestData() {
        final Optional<JsonArray> optional = readFile();
        final JsonArray guestsArray = optional.orElseThrow(() -> new PotentialGuestsException(FILE_NAME));
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

        sort(premium);
        sort(economy);

        LOG.debug("Premium Guest: {} and Economy Guests: {}", premium, economy);
        LOG.debug("Premium Guest: {} and Economy Guests: {}", premium.stream().reduce(0, Integer::sum),
                economy.stream().reduce(0, Integer::sum));
        return PotentialGuest.of(premium, economy);
    }

    private static void sort(List<Integer> list) {
        list.sort(Collections.reverseOrder());
    }

    private static Optional<JsonArray> readFile() {
        LOG.debug("Read file, File name:{}", FILE_NAME);
        Reader reader;
        try {
            reader = Files.newBufferedReader(Paths.get(FILE_NAME));
        } catch (IOException e) {
            LOG.error("ERROR:readFile {} : {} ", FILE_NAME, e.getMessage());
            throw new FileNotFoundException(FILE_NAME+" : "+e.getMessage());
        }

        JsonObject parser = JsonParser.parseReader(reader)
                .getAsJsonObject();
        JsonElement element = parser.get(GUESTS);
        if (element == null)
            throw new PotentialGuestsException(FILE_NAME);

        return Optional.of(element.getAsJsonArray());
    }
}

package com.rooms.occupancy.manager.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rooms.occupancy.manager.beans.PotentialGuest;
import com.rooms.occupancy.manager.exception.PotentialGuestsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public final class PotentialGuestUtils {

    private static final Logger LOG = LoggerFactory.getLogger(PotentialGuestUtils.class);

    //Constant of json key
    private static final String GUESTS = "guests";
    //Guests file name contains Premium and Economy guests
    private static final String FILE_NAME = "potential-guests.json";
    //This PRICE separator of Premium and Economy guests
    private static final int PRICE = 100;
    //Holds Premium and Economy guests
    private static Optional<PotentialGuest> ptlGuests = readGuestData();

    private PotentialGuestUtils() {}
    public static Optional<PotentialGuest> getPotentialGuests() {
        return ptlGuests;
    }

    /**
     * Read the Guest data from file.
     * @return Guest Object contains Premium and Economy guests.
     */
    private static Optional<PotentialGuest> readGuestData() {
        final Optional<JsonArray> optional = readFile();
        if(optional.isEmpty()) {
            throw new PotentialGuestsException("Something went wrong!");
        }
        return getGuestDetails(optional);
    }

    /**
     * Get Premium and Economy guests list from json array.
     * @param guestsOptional Optional guest JSON Array.
     * @return Guest Object contains Premium and Economy guests.
     */
    private static Optional<PotentialGuest> getGuestDetails(final Optional<JsonArray> guestsOptional) {
        LOG.info("Read Guests in getRoomTypeListMap method of PotentialGuestUtils");
        final JsonArray guestsArray = guestsOptional.orElseThrow(()->new PotentialGuestsException("No guest found"));

        final List<Integer> premium = new ArrayList<>();
        final List<Integer> economy = new ArrayList<>();

        for(final JsonElement element: guestsArray) {
            final int value = element.getAsInt();
            if (PRICE > value) {
                economy.add(value);
            } else {
                premium.add(value);
            }
        }

        if(!premium.isEmpty()) {
            sort(premium);
        }

        if(!premium.isEmpty()) {
            sort(economy);
        }
        return Optional.of(new PotentialGuest(premium, economy));
    }

    /**
     * Sort the list of Integers in reverse order.
     * @param list
     */
    private static void sort(final List<Integer> list) {
        list.sort(Collections.reverseOrder());
    }

    /**
     * Read guests file from local.
     * @return JSON Array contains guest.
     */
    private static Optional<JsonArray> readFile() {
        LOG.debug("Read file, File name:{}", FILE_NAME);
        try(Reader reader = Files.newBufferedReader(Paths.get(FILE_NAME))) {
            final JsonObject parser = JsonParser.parseReader(reader)
                    .getAsJsonObject();
            final JsonElement element = parser.get(GUESTS);
            return element == null ? Optional.empty() : Optional.of(element.getAsJsonArray());
        } catch (IOException e) {
            LOG.error("ERROR in guests file reading! : {}", e.getMessage());
            return Optional.empty();
        }
    }
}

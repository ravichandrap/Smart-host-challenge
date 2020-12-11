package com.rooms.occupancy.manager.util;

import com.google.gson.*;
import com.rooms.occupancy.manager.beans.Guest;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomUtil {

    public static Optional<Guest> getPotentialGuests() {
        try {
            Optional<List<Long>> guestData = readGuestData();
            if(guestData.isPresent()) {
                guestData.get().forEach(System.out::println);
                return Guest.of(guestData.get());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static Optional<List<Long>> readGuestData() throws IOException {

        Optional<JsonArray> guests = readFile();
        if(guests.isEmpty())
            return Optional.empty();

        JsonArray guestsArray = guests.get();
        List<Long> guestList = new ArrayList<>(guestsArray.size());
        for(JsonElement element: guestsArray)
            guestList.add(element.getAsLong());

        return Optional.of(guestList);
    }

    private static Optional<JsonArray> readFile() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("potential-guests.json"));
        JsonObject parser = JsonParser.parseReader(reader)
                                        .getAsJsonObject();
        JsonElement element = parser.get("guests");

        return element == null
                ? Optional.empty()
                :Optional.of(element.getAsJsonArray());
    }


}

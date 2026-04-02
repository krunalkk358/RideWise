package com.airtribe.ridewise.service;

import com.airtribe.ridewise.model.Rider;
import com.airtribe.ridewise.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class RiderService {
    private final List<Rider> riders = new ArrayList<>();

    public Rider registerRider(String name, double location) {
        String id = IdGenerator.generateRiderId();
        Rider rider = new Rider(id, name, location);
        riders.add(rider);
        return rider;
    }

    public Rider getRiderById(String id) {
        for (Rider rider : riders) {
            if (rider.getId().equals(id)) {
                return rider;
            }
        }
        return null;
    }

    public List<Rider> getAllRiders() {
        return new ArrayList<>(riders);
    }
}


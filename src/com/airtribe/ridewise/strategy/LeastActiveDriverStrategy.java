package com.airtribe.ridewise.strategy;

import com.airtribe.ridewise.exception.NoDriverAvailableException;
import com.airtribe.ridewise.model.Driver;
import com.airtribe.ridewise.model.Rider;

import java.util.List;

/**
 * Finds the available driver with the fewest completed rides,
 * distributing work more evenly across drivers.
 */
public class LeastActiveDriverStrategy implements RideMatchingStrategy {

    @Override
    public Driver findDriver(Rider rider, List<Driver> drivers) {
        Driver leastActive = null;
        int minRides = Integer.MAX_VALUE;

        for (Driver driver : drivers) {
            if (!driver.isAvailable()) {
                continue;
            }
            if (driver.getTotalRidesCompleted() < minRides) {
                minRides = driver.getTotalRidesCompleted();
                leastActive = driver;
            }
        }

        if (leastActive == null) {
            throw new NoDriverAvailableException("No available driver found for rider: " + rider.getName());
        }
        return leastActive;
    }
}


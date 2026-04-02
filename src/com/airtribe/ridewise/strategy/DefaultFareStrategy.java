package com.airtribe.ridewise.strategy;

import com.airtribe.ridewise.model.Ride;

/**
 * Default fare calculation: base fare + per-km rate.
 * Base fare = 50, Rate = 10 per km.
 */
public class DefaultFareStrategy implements FareStrategy {

    private static final double BASE_FARE = 50.0;
    private static final double PER_KM_RATE = 10.0;

    @Override
    public double calculateFare(Ride ride) {
        return BASE_FARE + (ride.getDistance() * PER_KM_RATE);
    }
}


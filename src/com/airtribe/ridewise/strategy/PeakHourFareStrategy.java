package com.airtribe.ridewise.strategy;

import com.airtribe.ridewise.model.Ride;

/**
 * Peak hour fare calculation: applies a 1.5x surge multiplier
 * on top of the default fare.
 * Base fare = 50, Rate = 10 per km, Surge multiplier = 1.5.
 */
public class PeakHourFareStrategy implements FareStrategy {

    private static final double BASE_FARE = 50.0;
    private static final double PER_KM_RATE = 10.0;
    private static final double SURGE_MULTIPLIER = 1.5;

    @Override
    public double calculateFare(Ride ride) {
        double normalFare = BASE_FARE + (ride.getDistance() * PER_KM_RATE);
        return normalFare * SURGE_MULTIPLIER;
    }
}

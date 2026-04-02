package com.airtribe.ridewise.service;

import com.airtribe.ridewise.exception.NoDriverAvailableException;
import com.airtribe.ridewise.model.*;
import com.airtribe.ridewise.strategy.FareStrategy;
import com.airtribe.ridewise.strategy.RideMatchingStrategy;
import com.airtribe.ridewise.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Core service that orchestrates ride lifecycle.
 * Strategies are injected via constructor — DIP & OCP compliant.
 */
public class RideService {
    private final RideMatchingStrategy matchingStrategy;
    private final FareStrategy fareStrategy;
    private final DriverService driverService;
    private final List<Ride> rides = new ArrayList<>();

    public RideService(RideMatchingStrategy matchingStrategy, FareStrategy fareStrategy, DriverService driverService) {
        this.matchingStrategy = matchingStrategy;
        this.fareStrategy = fareStrategy;
        this.driverService = driverService;
    }

    /**
     * Request a ride for a given rider and distance.
     * Automatically finds and assigns a driver using the matching strategy.
     */
    public Ride requestRide(Rider rider, double distance) {
        String rideId = IdGenerator.generateRideId();
        Ride ride = new Ride(rideId, rider, distance);

        List<Driver> availableDrivers = driverService.getAvailableDrivers();
        if (availableDrivers.isEmpty()) {
            ride.setStatus(RideStatus.CANCELLED);
            rides.add(ride);
            throw new NoDriverAvailableException("No drivers are currently available.");
        }

        Driver assignedDriver = matchingStrategy.findDriver(rider, availableDrivers);
        ride.setDriver(assignedDriver);
        ride.setStatus(RideStatus.ASSIGNED);
        assignedDriver.setAvailable(false);

        rides.add(ride);
        return ride;
    }

    /**
     * Complete a ride: calculates fare, marks driver available, updates stats.
     */
    public FareReceipt completeRide(String rideId) {
        Ride ride = getRideById(rideId);
        if (ride == null) {
            throw new IllegalArgumentException("Ride not found: " + rideId);
        }
        if (ride.getStatus() != RideStatus.ASSIGNED) {
            throw new IllegalStateException("Ride " + rideId + " cannot be completed. Current status: " + ride.getStatus());
        }

        ride.setStatus(RideStatus.COMPLETED);

        Driver driver = ride.getDriver();
        driver.setAvailable(true);
        driver.incrementRidesCompleted();

        double fare = fareStrategy.calculateFare(ride);
        return new FareReceipt(rideId, fare);
    }

    /**
     * Cancel a ride that is either REQUESTED or ASSIGNED.
     */
    public void cancelRide(String rideId) {
        Ride ride = getRideById(rideId);
        if (ride == null) {
            throw new IllegalArgumentException("Ride not found: " + rideId);
        }
        if (ride.getStatus() == RideStatus.COMPLETED || ride.getStatus() == RideStatus.CANCELLED) {
            throw new IllegalStateException("Ride " + rideId + " cannot be cancelled. Current status: " + ride.getStatus());
        }

        if (ride.getDriver() != null) {
            ride.getDriver().setAvailable(true);
        }
        ride.setStatus(RideStatus.CANCELLED);
    }

    public Ride getRideById(String rideId) {
        for (Ride ride : rides) {
            if (ride.getId().equals(rideId)) {
                return ride;
            }
        }
        return null;
    }

    public List<Ride> getAllRides() {
        return new ArrayList<>(rides);
    }
}

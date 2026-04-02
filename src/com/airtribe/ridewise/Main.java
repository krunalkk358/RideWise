package com.airtribe.ridewise;

import com.airtribe.ridewise.exception.NoDriverAvailableException;
import com.airtribe.ridewise.model.*;
import com.airtribe.ridewise.service.*;
import com.airtribe.ridewise.strategy.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static RiderService riderService;
    private static DriverService driverService;
    private static RideService rideService;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // --- Strategy selection at startup ---
        RideMatchingStrategy matchingStrategy = selectMatchingStrategy(scanner);
        FareStrategy fareStrategy = selectFareStrategy(scanner);

        // --- Initialize services (DIP: depend on interfaces) ---
        riderService = new RiderService();
        driverService = new DriverService();
        rideService = new RideService(matchingStrategy, fareStrategy, driverService);

        System.out.println("\n========================================");
        System.out.println("   Welcome to RideWise 🚗");
        System.out.println("========================================");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addRider(scanner);
                case "2" -> addDriver(scanner);
                case "3" -> viewAvailableDrivers();
                case "4" -> requestRide(scanner);
                case "5" -> completeRide(scanner);
                case "6" -> viewRides();
                case "7" -> {
                    System.out.println("Thank you for using RideWise. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    // -------------------------------------------------------------------------
    // Strategy selectors (called once at startup)
    // -------------------------------------------------------------------------

    private static RideMatchingStrategy selectMatchingStrategy(Scanner scanner) {
        System.out.println("\nSelect Ride Matching Strategy:");
        System.out.println("  1. Nearest Driver");
        System.out.println("  2. Least Active Driver");
        System.out.print("Enter choice (1/2): ");
        String choice = scanner.nextLine().trim();
        if ("2".equals(choice)) {
            System.out.println(">> Using: Least Active Driver Strategy");
            return new LeastActiveDriverStrategy();
        }
        System.out.println(">> Using: Nearest Driver Strategy");
        return new NearestDriverStrategy();
    }

    private static FareStrategy selectFareStrategy(Scanner scanner) {
        System.out.println("\nSelect Fare Calculation Strategy:");
        System.out.println("  1. Default Fare");
        System.out.println("  2. Peak Hour Fare (1.5x surge)");
        System.out.print("Enter choice (1/2): ");
        String choice = scanner.nextLine().trim();
        if ("2".equals(choice)) {
            System.out.println(">> Using: Peak Hour Fare Strategy");
            return new PeakHourFareStrategy();
        }
        System.out.println(">> Using: Default Fare Strategy");
        return new DefaultFareStrategy();
    }

    // -------------------------------------------------------------------------
    // Menu
    // -------------------------------------------------------------------------

    private static void printMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add Rider");
        System.out.println("2. Add Driver");
        System.out.println("3. View Available Drivers");
        System.out.println("4. Request Ride");
        System.out.println("5. Complete Ride");
        System.out.println("6. View Rides");
        System.out.println("7. Exit");
        System.out.print("Choose an option: ");
    }

    // -------------------------------------------------------------------------
    // Menu actions
    // -------------------------------------------------------------------------

    private static void addRider(Scanner scanner) {
        System.out.print("Enter rider name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        double location = readDouble(scanner, "Enter rider location (numeric): ");
        if (Double.isNaN(location)) return;

        Rider rider = riderService.registerRider(name, location);
        System.out.println("Rider registered: " + rider);
    }

    private static void addDriver(Scanner scanner) {
        System.out.print("Enter driver name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        double location = readDouble(scanner, "Enter driver location (numeric): ");
        if (Double.isNaN(location)) return;

        Driver driver = driverService.registerDriver(name, location);
        System.out.println("Driver registered: " + driver);
    }

    private static void viewAvailableDrivers() {
        List<Driver> available = driverService.getAvailableDrivers();
        if (available.isEmpty()) {
            System.out.println("No drivers are currently available.");
            return;
        }
        System.out.println("Available Drivers:");
        for (Driver d : available) {
            System.out.println("  " + d);
        }
    }

    private static void requestRide(Scanner scanner) {
        System.out.print("Enter rider ID (e.g. RIDER-1): ");
        String riderId = scanner.nextLine().trim();
        Rider rider = riderService.getRiderById(riderId);
        if (rider == null) {
            System.out.println("Rider not found: " + riderId);
            return;
        }

        double distance = readDouble(scanner, "Enter ride distance (km): ");
        if (Double.isNaN(distance) || distance <= 0) {
            System.out.println("Invalid distance.");
            return;
        }

        try {
            Ride ride = rideService.requestRide(rider, distance);
            System.out.println("Ride created & driver assigned: " + ride);
        } catch (NoDriverAvailableException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void completeRide(Scanner scanner) {
        System.out.print("Enter ride ID (e.g. RIDE-1): ");
        String rideId = scanner.nextLine().trim();

        try {
            FareReceipt receipt = rideService.completeRide(rideId);
            System.out.println("Ride completed!");
            System.out.println("  " + receipt);
        } catch (IllegalArgumentException | IllegalStateException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void viewRides() {
        List<Ride> rides = rideService.getAllRides();
        if (rides.isEmpty()) {
            System.out.println("No rides yet.");
            return;
        }
        System.out.println("All Rides:");
        for (Ride r : rides) {
            System.out.println("  " + r);
        }
    }

    // -------------------------------------------------------------------------
    // Helper
    // -------------------------------------------------------------------------

    private static double readDouble(Scanner scanner, String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number: " + input);
            return Double.NaN;
        }
    }
}


# RideWise — Class Model

## Package Overview

```
com.airtribe.ridewise
├── Main.java                          // Console entry point
├── model/
│   ├── Rider.java                     // Rider entity
│   ├── Driver.java                    // Driver entity
│   ├── Ride.java                      // Ride entity (links Rider ↔ Driver)
│   ├── FareReceipt.java              // Value object — fare details
│   ├── RideStatus.java               // Enum: REQUESTED, ASSIGNED, COMPLETED, CANCELLED
│   └── VehicleType.java              // Enum: BIKE, AUTO, CAR
├── strategy/
│   ├── RideMatchingStrategy.java     // Interface — driver selection
│   ├── NearestDriverStrategy.java    // Impl — closest driver
│   ├── LeastActiveDriverStrategy.java// Impl — fewest rides completed
│   ├── FareStrategy.java            // Interface — fare calculation
│   ├── DefaultFareStrategy.java     // Impl — flat rate
│   └── PeakHourFareStrategy.java    // Impl — 1.5× surge
├── service/
│   ├── RiderService.java            // Register & lookup riders
│   ├── DriverService.java           // Register, availability & lookup drivers
│   └── RideService.java             // Ride lifecycle orchestration
├── exception/
│   └── NoDriverAvailableException.java
└── util/
    └── IdGenerator.java             // Thread-safe ID generation
```

## Class Details

### Model Layer

| Class | Responsibility |
|---|---|
| `Rider` | Holds rider profile & location |
| `Driver` | Holds driver profile, location, availability, ride count |
| `Ride` | Represents a single ride (links Rider → Driver, tracks status) |
| `FareReceipt` | Immutable receipt generated when a ride is completed |

### Strategy Layer

| Interface | Implementations | Purpose |
|---|---|---|
| `RideMatchingStrategy` | `NearestDriverStrategy`, `LeastActiveDriverStrategy` | Decide **which** driver to assign |
| `FareStrategy` | `DefaultFareStrategy`, `PeakHourFareStrategy` | Decide **how much** to charge |

### Service Layer

| Class | Responsibility |
|---|---|
| `RiderService` | CRUD for riders |
| `DriverService` | CRUD for drivers + availability management |
| `RideService` | Orchestrates ride request → assignment → completion/cancellation. Depends on `RideMatchingStrategy` and `FareStrategy` (injected via constructor). |


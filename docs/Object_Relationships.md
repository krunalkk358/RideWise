# RideWise — Object Relationships

## Relationship Summary

| From | To | Type | Description |
|---|---|---|---|
| `Rider` | `Ride` | Association | A rider can have many rides |
| `Driver` | `Ride` | Association | A driver is assigned to a ride |
| `Ride` | `FareReceipt` | Composition | A completed ride produces a receipt |
| `RideService` | `RideMatchingStrategy` | Composition (injected) | Strategy for driver selection |
| `RideService` | `FareStrategy` | Composition (injected) | Strategy for fare calculation |
| `RideService` | `DriverService` | Dependency (injected) | Fetches available drivers |

## Diagram (Text)

```
┌──────────┐         ┌──────────┐
│  Rider   │◄────────│   Ride   │────────►┌──────────┐
└──────────┘  rider   │          │ driver  │  Driver  │
                      │  status  │         └──────────┘
                      │ distance │
                      └────┬─────┘
                           │ completes
                           ▼
                     ┌─────────────┐
                     │ FareReceipt │
                     │  rideId     │
                     │  amount     │
                     │ generatedAt │
                     └─────────────┘
```

## Service Composition

```
┌────────────────────────────────────────────┐
│               RideService                   │
│                                             │
│  ┌─────────────────────┐                    │
│  │ «interface»         │                    │
│  │ RideMatchingStrategy│◄─── NearestDriver  │
│  │                     │◄─── LeastActive    │
│  └─────────────────────┘                    │
│                                             │
│  ┌─────────────────────┐                    │
│  │ «interface»         │                    │
│  │ FareStrategy        │◄─── DefaultFare    │
│  │                     │◄─── PeakHourFare   │
│  └─────────────────────┘                    │
│                                             │
│  ┌─────────────────────┐                    │
│  │ DriverService       │ (dependency)       │
│  └─────────────────────┘                    │
└────────────────────────────────────────────┘
```

## Key Design Decisions

1. **Composition over Inheritance** — Strategies are composed into `RideService` via constructor injection, not inherited.
2. **Interface-based contracts** — `RideMatchingStrategy` and `FareStrategy` are thin interfaces with a single method each.
3. **Immutable IDs** — Entity IDs are `final` and auto-generated, preventing accidental mutation.
4. **Status lifecycle** — `RideStatus` enum enforces a clear state machine: `REQUESTED → ASSIGNED → COMPLETED / CANCELLED`.
# RideWise — Requirements

## A. Functional Requirements

1. **Register Riders** — Add new riders with a name and location.
2. **Register Drivers** — Add new drivers with a name and current location.
3. **Show Available Drivers** — List all drivers that are currently not on a ride.
4. **Request a Ride** — A rider requests a ride with a given distance.
5. **Match Ride to Driver** — The system assigns a driver using a pluggable strategy:
   - *NearestDriverStrategy* — picks the closest available driver.
   - *LeastActiveDriverStrategy* — picks the driver with the fewest completed rides.
6. **Calculate Fare** — Fare is computed using a pluggable pricing strategy:
   - *DefaultFareStrategy* — base fare (₹50) + ₹10/km.
   - *PeakHourFareStrategy* — 1.5× surge on the default fare.
7. **Track Ride Status** — Every ride moves through a lifecycle:
   - `REQUESTED → ASSIGNED → COMPLETED` (happy path)
   - `REQUESTED / ASSIGNED → CANCELLED`

## B. Non-Functional Requirements

| Requirement | How it is achieved |
|---|---|
| Easily extendable pricing | `FareStrategy` interface — add new implementations without touching existing code |
| Easily change driver matching | `RideMatchingStrategy` interface — swap at startup |
| Low coupling | Services depend on interfaces, not concrete classes |
| Maintainable & readable | Single-purpose classes, clear naming, layered architecture |

## C. Domain Entities

| Entity | Key Fields |
|---|---|
| `Rider` | id, name, location |
| `Driver` | id, name, currentLocation, available, totalRidesCompleted |
| `Ride` | id, rider, driver, distance, status |
| `FareReceipt` | rideId, amount, generatedAt |

### Enums

- `RideStatus` — REQUESTED, ASSIGNED, COMPLETED, CANCELLED
- `VehicleType` — BIKE, AUTO, CAR (extensibility placeholder)


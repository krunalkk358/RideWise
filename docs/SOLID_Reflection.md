# RideWise — SOLID Principles Reflection

## S — Single Responsibility Principle (SRP)

Each class has **one reason to change**:

| Class | Single Responsibility |
|---|---|
| `RiderService` | Managing rider registration and lookup |
| `DriverService` | Managing driver registration, availability, and lookup |
| `RideService` | Orchestrating the ride lifecycle |
| `NearestDriverStrategy` | Finding the closest available driver |
| `DefaultFareStrategy` | Computing fare with a flat-rate formula |
| `IdGenerator` | Generating unique IDs |

If fare logic changes, only the fare strategy changes — not `RideService`.

---

## O — Open/Closed Principle (OCP)

The system is **open for extension, closed for modification**:

- To add a new matching algorithm (e.g., `HighestRatedDriverStrategy`), create a new class implementing `RideMatchingStrategy`. No existing code is modified.
- To add a new pricing model (e.g., `DistanceSlabFareStrategy`), create a new class implementing `FareStrategy`. Again, zero changes to `RideService`.

---

## L — Liskov Substitution Principle (LSP)

Any implementation of `RideMatchingStrategy` or `FareStrategy` can be substituted without breaking the system:

```java
// Both work identically from RideService's perspective:
new RideService(new NearestDriverStrategy(), new DefaultFareStrategy(), driverService);
new RideService(new LeastActiveDriverStrategy(), new PeakHourFareStrategy(), driverService);
```

The contract (interface) guarantees behavioral compatibility.

---

## I — Interface Segregation Principle (ISP)

Interfaces are **small and focused**:

- `RideMatchingStrategy` has **one method**: `findDriver()`
- `FareStrategy` has **one method**: `calculateFare()`

No class is forced to implement methods it doesn't need.

---

## D — Dependency Inversion Principle (DIP)

High-level modules depend on **abstractions**, not concrete classes:

```java
public class RideService {
    private final RideMatchingStrategy matchingStrategy;  // interface
    private final FareStrategy fareStrategy;              // interface
    ...
}
```

`RideService` never knows (or cares) which concrete strategy it's using. Strategies are injected via the constructor.

---

## Additional Design Principles

### DRY (Don't Repeat Yourself)
- Ride allocation logic lives in one place (`RideService.requestRide()`).
- ID generation is centralized in `IdGenerator`.

### KISS (Keep It Simple, Stupid)
- Entities are plain Java classes with simple fields and getters.
- No unnecessary frameworks or abstractions.

### YAGNI (You Aren't Gonna Need It)
- `VehicleType` enum exists as a placeholder but is not wired in until needed.
- No database, no REST layer — console-only MVP.

### Law of Demeter
- Services talk to their direct collaborators only.
- `RideService` calls `driverService.getAvailableDrivers()` — it does **not** reach into `driverService.drivers.get(0).setAvailable(...)`.


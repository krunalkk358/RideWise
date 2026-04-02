# RideWise - Ride-Sharing System 🚗

A console-based ride-sharing application (Uber/Ola style) demonstrating high-quality **Low-Level Design (LLD)** and advanced software engineering principles. Built with **Java**, showcasing **SOLID principles**, **Design Patterns**, and **Clean Architecture**.

---

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Architecture & Design Patterns](#architecture--design-patterns)
- [Installation & Setup](#installation--setup)
- [How to Run](#how-to-run)
- [Step-by-Step Usage Guide](#step-by-step-usage-guide)
- [Project Structure](#project-structure)
- [Key Components](#key-components)
- [Design & Architecture Examples](#design--architecture-examples)
- [SOLID Principles Applied](#solid-principles-applied)
- [FAQ & Troubleshooting](#faq--troubleshooting)

---

## 🎯 Project Overview

**RideWise** is an educational project that demonstrates professional-grade software architecture for a ride-sharing platform. It allows users to:

- **Register Riders** - Create rider profiles with location
- **Register Drivers** - Add drivers with availability status
- **Request Rides** - Match riders with available drivers
- **View Available Drivers** - List drivers ready for rides
- **Complete Rides** - Finalize rides and generate fare receipts
- **Track Ride Status** - Monitor ride lifecycle
- **Choose Matching Strategy** - Select driver allocation algorithm (Nearest/Least Active)
- **Choose Pricing Strategy** - Select fare calculation model (Default/Peak Hour)

### 🎓 Learning Objectives

This project teaches:

1. **SOLID Principles**
   - Single Responsibility Principle (SRP)
   - Open/Closed Principle (OCP)
   - Liskov Substitution Principle (LSP)
   - Interface Segregation Principle (ISP)
   - Dependency Inversion Principle (DIP)

2. **Design Patterns**
   - **Strategy Pattern** - Flexible ride matching & fare calculation
   - **Service Layer Pattern** - Business logic separation
   - **Dependency Injection** - Loose coupling

3. **Design Principles**
   - Composition over Inheritance
   - Law of Demeter
   - DRY (Don't Repeat Yourself)
   - KISS (Keep It Simple, Stupid)
   - YAGNI (You Aren't Gonna Need It)

4. **LLD Skills**
   - Entity modeling and relationships
   - Concern separation into layers
   - Designing scalable extension points
   - High cohesion & low coupling

---

## ✨ Features

### Core Features
- ✅ Rider registration with location tracking
- ✅ Driver registration with availability management
- ✅ Real-time driver matching
- ✅ Multiple ride matching strategies
- ✅ Multiple pricing strategies
- ✅ Fare receipt generation
- ✅ Ride status tracking (REQUESTED → ASSIGNED → COMPLETED)
- ✅ Error handling with custom exceptions

### Advanced Features
- ✅ Pluggable strategies (Strategy Pattern)
- ✅ Composition-based architecture
- ✅ Service layer abstraction
- ✅ Stateful ride lifecycle management
- ✅ Driver statistics tracking

---

## 🏗️ Architecture & Design Patterns

### Design Pattern: Strategy Pattern

**What is it?**
The Strategy Pattern allows selecting an algorithm's behavior at runtime without modifying the client code.

**How is it used in RideWise?**

#### 1. Ride Matching Strategy
```
RideMatchingStrategy (Interface)
├── NearestDriverStrategy (finds driver closest to rider)
└── LeastActiveDriverStrategy (finds driver with fewest completed rides)
```

#### 2. Fare Calculation Strategy
```
FareStrategy (Interface)
├── DefaultFareStrategy (base fare + per-km rate)
└── PeakHourFareStrategy (1.5x surge during peak hours)
```

**Why this matters:**
- Add new strategies without modifying `RideService`
- Strategies are interchangeable at runtime
- Follows **Open/Closed Principle** (OCP)

---

## 📦 Installation & Setup

### Prerequisites

- **Java Development Kit (JDK)** 8 or higher
  - Download from: https://www.oracle.com/java/technologies/downloads/
  - Verify installation: `java -version`

- **Windows Command Prompt or PowerShell**

### Step 1: Verify Java Installation

```powershell
java -version
javac -version
```

Expected output:
```
java version "1.8.0_202" (or higher)
```

### Step 2: Navigate to Project Directory

```powershell
cd "d:\AirTribe\Airtribe-Projects\ride-wise app DB\ridewise\ridewise"
```

### Step 3: Compile the Project

Compile all Java source files to the `out` directory:

```powershell
javac -d out `
  src/com/airtribe/ridewise/*.java `
  src/com/airtribe/ridewise/model/*.java `
  src/com/airtribe/ridewise/service/*.java `
  src/com/airtribe/ridewise/strategy/*.java `
  src/com/airtribe/ridewise/exception/*.java `
  src/com/airtribe/ridewise/util/*.java
```

**Note:** The backtick (`) in PowerShell continues commands on the next line.

Alternatively, if you prefer a single line:

```powershell
javac -d out src/com/airtribe/ridewise/*.java src/com/airtribe/ridewise/model/*.java src/com/airtribe/ridewise/service/*.java src/com/airtribe/ridewise/strategy/*.java src/com/airtribe/ridewise/exception/*.java src/com/airtribe/ridewise/util/*.java
```

**Verify compilation:**
```powershell
dir out\com\airtribe\ridewise
```

You should see compiled `.class` files.

---

## ▶️ How to Run

### Start the Application

```powershell
cd "d:\AirTribe\Airtribe-Projects\ride-wise app DB\ridewise\ridewise"
java -cp out com.airtribe.ridewise.Main
```

### Expected Output

The application will display:

```
Select Ride Matching Strategy:
  1. Nearest Driver
  2. Least Active Driver
Enter choice (1/2): 
```

Then:

```
Select Fare Calculation Strategy:
  1. Default Fare
  2. Peak Hour Fare (1.5x surge)
Enter choice (1/2):
```

Then:

```
========================================
   Welcome to RideWise 🚗
========================================

--- Main Menu ---
1. Add Rider
2. Add Driver
3. View Available Drivers
4. Request Ride
5. Complete Ride
6. View Rides
7. Exit
Choose an option:
```

---

## 📖 Step-by-Step Usage Guide

### Complete Example Workflow

#### **Step 1: Select Strategies**

When the app starts, choose your strategies:

```
Select Ride Matching Strategy:
  1. Nearest Driver
  2. Least Active Driver
Enter choice (1/2): 1
>> Using: Nearest Driver Strategy
```

```
Select Fare Calculation Strategy:
  1. Default Fare
  2. Peak Hour Fare (1.5x surge)
Enter choice (1/2): 1
>> Using: Default Fare Strategy
```

#### **Step 2: Register a Rider**

From the main menu, select option **1**:

```
Choose an option: 1
Enter rider name: John Doe
Enter rider location (numeric): 10
Rider registered: Rider{id='RIDER-1', name='John Doe', location=10.0}
```

**What happens behind the scenes:**
- `RiderService.registerRider()` creates a new `Rider` object
- `IdGenerator.generateRideId()` creates unique ID like "RIDER-1"
- Rider is stored in memory

#### **Step 3: Register Multiple Drivers**

Select option **2** to add drivers:

```
Choose an option: 2
Enter driver name: Alice
Enter driver location (numeric): 8
Driver registered: Driver{id='DRIVER-1', name='Alice', location=8.0, available=true, ridesCompleted=0}
```

Register another driver:

```
Choose an option: 2
Enter driver name: Bob
Enter driver location (numeric): 15
Driver registered: Driver{id='DRIVER-2', name='Bob', location=15.0, available=true, ridesCompleted=0}
```

#### **Step 4: View Available Drivers**

Select option **3**:

```
Choose an option: 3
Available Drivers:
  Driver{id='DRIVER-1', name='Alice', location=8.0, available=true, ridesCompleted=0}
  Driver{id='DRIVER-2', name='Bob', location=15.0, available=true, ridesCompleted=0}
```

#### **Step 5: Request a Ride**

Select option **4**:

```
Choose an option: 4
Enter rider ID (e.g. RIDER-1): RIDER-1
Enter ride distance (km): 5
Ride created & driver assigned: Ride{id='RIDE-1', rider=Rider{id='RIDER-1', name='John Doe', location=10.0}, driver=Driver{id='DRIVER-1', name='Alice', location=8.0, available=false, ridesCompleted=0}, distance=5.0, status=ASSIGNED}
```

**What happens:**
1. `RideService.requestRide()` is called
2. Matching strategy (`NearestDriverStrategy`) finds `Alice` (distance = |8-10| = 2 km) instead of `Bob` (distance = |15-10| = 5 km)
3. Alice is marked as `available=false`
4. Ride status is set to `ASSIGNED`

#### **Step 6: View All Rides**

Select option **6**:

```
Choose an option: 6
All Rides:
  Ride{id='RIDE-1', rider=Rider{id='RIDER-1', name='John Doe', location=10.0}, driver=Driver{id='DRIVER-1', name='Alice', location=8.0, available=false, ridesCompleted=0}, distance=5.0, status=ASSIGNED}
```

#### **Step 7: Complete a Ride**

Select option **5**:

```
Choose an option: 5
Enter ride ID (e.g. RIDE-1): RIDE-1
Ride completed!
  FareReceipt{rideId='RIDE-1', amount=100.0, generatedAt=2026-04-02T22:35:12.123456}
```

**What happens:**
1. `RideService.completeRide()` is called
2. Fare is calculated: **50 (base) + 5 km × 10 (per-km rate) = 100**
3. Driver `Alice` is marked as `available=true`
4. Driver's `ridesCompleted` is incremented to 1
5. Ride status is set to `COMPLETED`

#### **Step 8: Verify Updated Driver Status**

Select option **3** again:

```
Choose an option: 3
Available Drivers:
  Driver{id='DRIVER-1', name='Alice', location=8.0, available=true, ridesCompleted=1}
  Driver{id='DRIVER-2', name='Bob', location=15.0, available=true, ridesCompleted=0}
```

Notice:
- Alice is now available again
- Alice's `ridesCompleted` increased to 1

#### **Step 9: Exit**

Select option **7**:

```
Choose an option: 7
Thank you for using RideWise. Goodbye!
```

---

## 📁 Project Structure

```
ridewise/
├── README.md                          # This file
├── docs/
│   ├── Requirements.md                # Functional requirements
│   ├── Class_Model.md                 # Class diagram documentation
│   ├── Object_Relationships.md         # Entity relationships
│   └── SOLID_Reflection.md             # SOLID principles analysis
│
├── src/
│   └── com/airtribe/ridewise/
│       ├── Main.java                  # Console application entry point
│       ├── exception/
│       │   └── NoDriverAvailableException.java
│       ├── model/
│       │   ├── Rider.java             # Rider entity
│       │   ├── Driver.java            # Driver entity
│       │   ├── Ride.java              # Ride entity
│       │   ├── FareReceipt.java        # Fare receipt entity
│       │   ├── RideStatus.java         # RideStatus enum
│       │   └── VehicleType.java        # VehicleType enum
│       ├── service/
│       │   ├── RiderService.java       # Rider business logic
│       │   ├── DriverService.java      # Driver business logic
│       │   └── RideService.java        # Ride orchestration & matching
│       ├── strategy/
│       │   ├── RideMatchingStrategy.java     # Interface for driver matching
│       │   ├── NearestDriverStrategy.java    # Find nearest driver
│       │   ├── LeastActiveDriverStrategy.java # Find least active driver
│       │   ├── FareStrategy.java             # Interface for fare calculation
│       │   ├── DefaultFareStrategy.java      # Default pricing
│       │   └── PeakHourFareStrategy.java     # Peak hour pricing
│       └── util/
│           └── IdGenerator.java       # Unique ID generation
│
└── out/                               # Compiled .class files (auto-generated)
```

---

## 🔑 Key Components

### 1. **Entities (Model Layer)**

| Class | Purpose | Key Attributes |
|-------|---------|-----------------|
| `Rider` | Represents a passenger | id, name, location |
| `Driver` | Represents a driver | id, name, location, available, totalRidesCompleted |
| `Ride` | Represents a ride booking | id, rider, driver, distance, status |
| `FareReceipt` | Represents charge | rideId, amount, generatedAt |
| `RideStatus` (Enum) | Ride states | REQUESTED, ASSIGNED, COMPLETED, CANCELLED |
| `VehicleType` (Enum) | Vehicle categories | BIKE, AUTO, CAR |

### 2. **Services (Business Logic Layer)**

| Service | Responsibility | Methods |
|---------|-----------------|---------|
| `RiderService` | Manage riders | registerRider(), getRiderById() |
| `DriverService` | Manage drivers | registerDriver(), getAvailableDrivers(), updateAvailability() |
| `RideService` | Orchestrate rides | requestRide(), completeRide(), cancelRide(), getAllRides() |

### 3. **Strategies (Extension Points)**

#### Ride Matching Strategy
```java
public interface RideMatchingStrategy {
    Driver findDriver(Rider rider, List<Driver> drivers);
}
```

**Implementations:**
- `NearestDriverStrategy` - Finds the driver with minimum distance to rider
- `LeastActiveDriverStrategy` - Finds the driver with least completed rides

#### Fare Calculation Strategy
```java
public interface FareStrategy {
    double calculateFare(Ride ride);
}
```

**Implementations:**
- `DefaultFareStrategy` - Base fare (50) + Per-km rate (10)
- `PeakHourFareStrategy` - Default fare × 1.5 surge multiplier

### 4. **Utilities**

| Utility | Purpose |
|---------|---------|
| `IdGenerator` | Generates unique IDs for riders, drivers, and rides |
| `NoDriverAvailableException` | Custom exception for no available drivers |

---

## 🏛️ Design & Architecture Examples

### Example 1: How Strategies Work (Open/Closed Principle)

**Problem:** We need to add a new driver matching strategy (e.g., "Random Driver") without modifying existing code.

**Solution with Strategy Pattern:**

```java
// Create a new implementation of RideMatchingStrategy
public class RandomDriverStrategy implements RideMatchingStrategy {
    @Override
    public Driver findDriver(Rider rider, List<Driver> drivers) {
        int index = new Random().nextInt(drivers.size());
        return drivers.get(index);
    }
}
```

**Usage in Main.java:**
```java
// No changes to RideService needed!
RideMatchingStrategy strategy = new RandomDriverStrategy();
RideService rideService = new RideService(strategy, fareStrategy, driverService);
```

✅ **Benefits:**
- `RideService` doesn't change (OCP compliance)
- Strategy is pluggable at startup
- Easy to test each strategy independently

### Example 2: Service Layer Dependency Injection

**Why it matters:**
The `RideService` depends on interfaces, not concrete classes:

```java
public class RideService {
    private final RideMatchingStrategy matchingStrategy;  // Interface, not concrete!
    private final FareStrategy fareStrategy;               // Interface, not concrete!
    private final DriverService driverService;
    
    // Constructor Injection - DIP compliance
    public RideService(RideMatchingStrategy matchingStrategy, 
                       FareStrategy fareStrategy, 
                       DriverService driverService) {
        this.matchingStrategy = matchingStrategy;
        this.fareStrategy = fareStrategy;
        this.driverService = driverService;
    }
}
```

✅ **Benefits:**
- `RideService` has no knowledge of strategy implementations
- Easy to swap strategies at runtime
- Perfect for testing (mock strategies can be injected)
- Follows **Dependency Inversion Principle** (DIP)

### Example 3: Composition Over Inheritance

**Why not inheritance?**

```java
// ❌ Bad: Ride tied to a specific strategy
public class Ride extends NearestDriverStrategy { ... }
```

**Why composition?**

```java
// ✅ Good: RideService composes strategies
public class RideService {
    private RideMatchingStrategy matchingStrategy;  // Composition!
    private FareStrategy fareStrategy;               // Composition!
    
    // Strategies can be changed without modifying Ride
}
```

✅ **Benefits:**
- More flexible and reusable
- Strategies are decoupled from Ride
- Multiple strategies can be used in the same app

---

## 🎓 SOLID Principles Applied

### 1. **Single Responsibility Principle (SRP)**

Each class has ONE reason to change:

| Class | Single Responsibility |
|-------|--------------------------|
| `RiderService` | Managing riders only |
| `DriverService` | Managing drivers only |
| `RideService` | Orchestrating ride lifecycle |
| `NearestDriverStrategy` | Finding nearest driver |
| `DefaultFareStrategy` | Calculating default fare |

✅ **Benefit:** Easy to test, modify, and maintain each class independently.

### 2. **Open/Closed Principle (OCP)**

Classes are **open for extension**, **closed for modification**:

```
RideService is CLOSED for modification (we don't edit it)
├── Strategy Pattern OPENS it for extension
    ├── Add NearestDriverStrategy ✓
    ├── Add LeastActiveDriverStrategy ✓
    ├── Add RandomDriverStrategy ✓ (new, without modifying RideService)
    └── Add YourCustomStrategy ✓ (future strategies)
```

### 3. **Liskov Substitution Principle (LSP)**

All strategy implementations are substitutable:

```java
// This works with ANY RideMatchingStrategy
Driver driver = matchingStrategy.findDriver(rider, drivers);

// Whether strategy is:
RideMatchingStrategy s1 = new NearestDriverStrategy();      // ✓
RideMatchingStrategy s2 = new LeastActiveDriverStrategy(); // ✓
RideMatchingStrategy s3 = new RandomDriverStrategy();       // ✓
```

✅ **Contract:** All strategies must return a valid `Driver` or throw `NoDriverAvailableException`.

### 4. **Interface Segregation Principle (ISP)**

Clients depend only on methods they use:

```java
// ✅ Good: Focused, minimal interface
public interface RideMatchingStrategy {
    Driver findDriver(Rider rider, List<Driver> drivers);
}

// ❌ Bad: Fat interface with unrelated methods
public interface RideMatchingStrategy {
    Driver findDriver(Rider rider, List<Driver> drivers);
    void sendNotification(String message);  // Unrelated!
    void calculateFare(Ride ride);          // Unrelated!
}
```

### 5. **Dependency Inversion Principle (DIP)**

High-level modules depend on abstractions, not low-level modules:

```
RideService (High-level)
    ↓ depends on (abstractions)
     ├── RideMatchingStrategy (Interface)
     └── FareStrategy (Interface)
         ↓ implemented by (low-level)
         ├── NearestDriverStrategy
         ├── DefaultFareStrategy
         └── Custom strategies...
```

✅ **Benefit:** RideService doesn't depend on concrete strategies; it only knows the interface.

---

## 📊 Ride Lifecycle Diagram

```
┌─────────────────────────────────────────────────────────┐
│                  RIDER REQUESTS RIDE                     │
└──────────────────────────┬────────────────────────────────┘
                           │
                    Status: REQUESTED
                           │
                           ↓
              Matching Strategy Finds Driver
        (NearestDriverStrategy OR LeastActiveDriverStrategy)
                           │
                    Status: ASSIGNED
                           │
                           ↓
                    Ride in Progress
                           │
                    Status: COMPLETED
                           │
                           ↓
              Fare Calculation Strategy Applied
        (DefaultFareStrategy OR PeakHourFareStrategy)
                           │
                           ↓
                    FARE RECEIPT GENERATED
                           │
                    Driver Available Again
```

---

## 🐛 FAQ & Troubleshooting

### Q1: "Command not recognized: javac"
**Solution:** Java Development Kit (JDK) is not installed or not in PATH.
- Download JDK from: https://www.oracle.com/java/technologies/downloads/
- Add JAVA_HOME to environment variables

### Q2: "NoDriverAvailableException" when requesting a ride
**Solution:** No drivers are available (all are busy).
- Add more drivers using option 2
- Complete existing rides to free up drivers

### Q3: "Rider not found: RIDER-1"
**Solution:** The rider ID doesn't exist.
- First register a rider (option 1)
- Use the correct rider ID shown in registration output

### Q4: "Invalid distance" when requesting a ride
**Solution:** Distance must be a positive number.
- Enter a number > 0 (e.g., 5, 10.5, 25)
- Don't enter negative, zero, or non-numeric values

### Q5: How do I use different strategies?
**Solution:** Strategies are selected at application startup:
1. Start the application
2. Choose matching strategy (1 or 2)
3. Choose pricing strategy (1 or 2)
4. The entire session uses your chosen strategies

### Q6: Can I change strategies during a session?
**Current:** No, strategies are selected at startup.
**Future Enhancement:** Modify `Main.java` to add runtime strategy switching.

### Q7: How do I add my own matching strategy?
**Steps:**
1. Create a new class (e.g., `MyCustomStrategy.java`)
2. Implement `RideMatchingStrategy` interface
3. Override `findDriver()` method with your logic
4. Place it in `src/com/airtribe/ridewise/strategy/`
5. Compile: `javac -d out src/com/airtribe/ridewise/strategy/MyCustomStrategy.java`
6. Modify `Main.java` to allow selection of your strategy

**Example:**
```java
public class MyCustomStrategy implements RideMatchingStrategy {
    @Override
    public Driver findDriver(Rider rider, List<Driver> drivers) {
        // Your matching logic here
        return drivers.get(0);  // Simple example
    }
}
```

### Q8: Where is data stored?
**Answer:** Currently data is stored in memory (Lists in services).
**Note:** Data is lost when application closes.
**Future Enhancement:** Add database persistence (SQL, NoSQL, etc.)

### Q9: Why console-based and not GUI?
**Reason:** Focus on LLD design principles, not UI frameworks.
**Future Enhancement:** Add Swing/JavaFX GUI while keeping service layer unchanged.

### Q10: Can multiple users use the system simultaneously?
**Current:** No, it's single-user console application.
**Future Enhancement:** Refactor as REST API (Spring Boot) for multi-user support.

---

## 💡 Next Steps & Enhancements

### Phase 1: Core Enhancements
- [ ] Add ride cancellation functionality
- [ ] Implement driver ratings system
- [ ] Add ride history for riders and drivers
- [ ] Implement surge pricing based on demand

### Phase 2: Persistence
- [ ] Integrate SQL database (MySQL/PostgreSQL)
- [ ] Add data persistence layer
- [ ] Implement transaction management

### Phase 3: API & Web
- [ ] Convert to REST API (Spring Boot)
- [ ] Add user authentication & authorization
- [ ] Deploy on cloud (AWS/Azure/GCP)
- [ ] Create web dashboard for monitoring

### Phase 4: Advanced
- [ ] Real-time tracking (WebSocket)
- [ ] Notification system (SMS/Email)
- [ ] Payment integration
- [ ] Analytics & reporting

---

## 📚 Learning Resources

### Design Patterns
- [Strategy Pattern - Refactoring Guru](https://refactoring.guru/design-patterns/strategy)
- [Dependency Injection - Martin Fowler](https://martinfowler.com/articles/injection.html)

### SOLID Principles
- [S.O.L.I.D Principles - Uncle Bob](https://blog.cleancoder.com/uncle-bob/2020/10/18/Solid-Relevance.html)
- [SOLID in Java - Baeldung](https://www.baeldung.com/solid-principles)

### Low-Level Design
- [LLD Guide - System Design Primer](https://github.com/donnemartin/system-design-primer)
- [Java OOP Concepts](https://docs.oracle.com/javase/tutorial/java/concepts/)

---

## 📝 License

This project is for educational purposes only. Feel free to use, modify, and distribute for learning.

---

## 👤 Author

Created as a demonstration of professional-grade Java architecture and Low-Level Design (LLD) principles.

---

## 🤝 Contributing

To improve this project:
1. Fork the repository
2. Create a feature branch
3. Make enhancements
4. Submit a pull request

---

## ⭐ Key Takeaways

1. **Strategy Pattern enables flexibility** - Swap strategies at runtime without code changes
2. **Services provide abstraction** - Business logic separate from presentation
3. **Dependency Injection reduces coupling** - Classes depend on interfaces, not implementations
4. **SOLID principles improve maintainability** - Code is easier to test, modify, and extend
5. **Composition beats inheritance** - More flexible and reusable design
6. **Interface segregation** - Clients depend only on methods they actually use

---

**Happy Coding! 🚀**


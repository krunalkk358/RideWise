# RideWise - Quick Start Guide ⚡

Get the RideWise application running in **5 minutes**!

---

## ✅ Prerequisites Check

Before starting, ensure you have Java installed:

```powershell
java -version
javac -version
```

If these commands fail, [download Java JDK](https://www.oracle.com/java/technologies/downloads/).

---

## 🚀 Quick Start (5 Steps)

### Step 1: Open Terminal/PowerShell

```powershell
# Windows PowerShell
cd "d:\AirTribe\Airtribe-Projects\ride-wise app DB\ridewise\ridewise"
```

### Step 2: Compile the Project

```powershell
javac -d out `
  src/com/airtribe/ridewise/*.java `
  src/com/airtribe/ridewise/model/*.java `
  src/com/airtribe/ridewise/service/*.java `
  src/com/airtribe/ridewise/strategy/*.java `
  src/com/airtribe/ridewise/exception/*.java `
  src/com/airtribe/ridewise/util/*.java
```

**Single Line Alternative:**
```powershell
javac -d out src/com/airtribe/ridewise/*.java src/com/airtribe/ridewise/model/*.java src/com/airtribe/ridewise/service/*.java src/com/airtribe/ridewise/strategy/*.java src/com/airtribe/ridewise/exception/*.java src/com/airtribe/ridewise/util/*.java
```

### Step 3: Run the Application

```powershell
java -cp out com.airtribe.ridewise.Main
```

### Step 4: Select Your Strategies

**Choose Matching Strategy:**
```
Select Ride Matching Strategy:
  1. Nearest Driver
  2. Least Active Driver
Enter choice (1/2): 1
```

**Choose Fare Strategy:**
```
Select Fare Calculation Strategy:
  1. Default Fare
  2. Peak Hour Fare (1.5x surge)
Enter choice (1/2): 1
```

### Step 5: Play with the Menu!

```
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

## 💨 Complete Example Workflow (Copy-Paste Ready)

### Sample Interaction Script

When prompted, enter exactly these values to test the complete flow:

#### Add Rider
```
Menu: 1
Name: Alice
Location: 10
```

#### Add Second Rider
```
Menu: 1
Name: Bob
Location: 25
```

#### Add Driver 1
```
Menu: 2
Name: John
Location: 8
```

#### Add Driver 2
```
Menu: 2
Name: Sarah
Location: 22
```

#### View Available Drivers
```
Menu: 3
```
**Output:** Both drivers are available

#### Request Ride (Alice)
```
Menu: 4
Rider ID: RIDER-1
Distance: 5
```
**Note:** John (at location 8) will be assigned (nearest to Alice at 10)

#### View Rides
```
Menu: 6
```
**Output:** Shows RIDE-1 with ASSIGNED status

#### Request Ride (Bob)
```
Menu: 4
Rider ID: RIDER-2
Distance: 3
```
**Note:** Sarah (at location 22) will be assigned (John is busy)

#### Check Available Drivers Again
```
Menu: 3
```
**Output:** No drivers available (both are busy)

#### Complete First Ride
```
Menu: 5
Ride ID: RIDE-1
```
**Output:** Fare Receipt with calculated amount

**Calculation:** 50 (base) + 5 km × 10 (rate) = **₹100**

#### View All Rides
```
Menu: 6
```
**Output:** RIDE-1 shows COMPLETED status

#### Exit
```
Menu: 7
```

---

## 🎯 What Each Option Does

| Menu | Action | Example |
|------|--------|---------|
| **1** | Register new rider | Alice books for ride |
| **2** | Register new driver | John starts driving |
| **3** | See available drivers | Check who can take rides |
| **4** | Request a ride | Alice wants to go 5km |
| **5** | Complete a ride | Mark ride as done, get fare |
| **6** | View all rides | See complete history |
| **7** | Exit | Close application |

---

## 📊 Expected Fare Calculations

### Default Fare Strategy
```
Formula: Base Fare + (Distance × Per-km Rate)
         =    50    +  (km   ×    10)

Examples:
  1 km  → 50 + 10     = ₹60
  5 km  → 50 + 50     = ₹100
  10 km → 50 + 100    = ₹150
```

### Peak Hour Fare Strategy
```
Formula: Default Fare × 1.5 (Surge Multiplier)

Examples:
  1 km  → 60 × 1.5   = ₹90
  5 km  → 100 × 1.5  = ₹150
  10 km → 150 × 1.5  = ₹225
```

---

## 🎮 Interactive Menu Prompts

### Menu Prompt
```
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

### Add Rider Prompts
```
Enter rider name: 
Enter rider location (numeric): 
```

### Add Driver Prompts
```
Enter driver name: 
Enter driver location (numeric): 
```

### Request Ride Prompts
```
Enter rider ID (e.g. RIDER-1): 
Enter ride distance (km): 
```

### Complete Ride Prompts
```
Enter ride ID (e.g. RIDE-1): 
```

---

## ✨ Key Features to Try

### 1. **Nearest Driver Strategy**
- Add drivers at different locations
- Request a ride and see the nearest driver assigned
- Compare with Least Active Driver option

### 2. **Least Active Driver Strategy**
- Add multiple drivers
- Complete rides with each driver
- Request new ride to see if system picks least active driver

### 3. **Peak Hour Surge Pricing**
- Note the calculated fares
- Compare Default vs. Peak Hour pricing
- Verify 1.5× surge multiplier

### 4. **Ride Status Tracking**
- Track ride from ASSIGNED → COMPLETED
- See driver availability change
- View ride history

### 5. **Error Handling**
- Try requesting ride with invalid rider ID
- Try completing non-existent ride
- Try entering invalid distances (negative, text)
- See how app handles errors gracefully

---

## 🔧 Troubleshooting

### Problem: "Command not recognized: javac"
```powershell
# Solution: JDK not installed
# Download from: https://www.oracle.com/java/technologies/downloads/
```

### Problem: "Main class not found"
```powershell
# Verify compilation:
dir out\com\airtribe\ridewise
# Should show .class files exist
```

### Problem: "No drivers available"
```powershell
# You need to:
# 1. Add drivers first (Menu 2)
# 2. Make sure they haven't been assigned to rides yet
# 3. Try requesting with different rider location
```

### Problem: Invalid input errors
```powershell
# Check your input:
# - Rider/Driver IDs use format: RIDER-1, DRIVER-1, RIDE-1
# - Locations must be numbers (e.g., 10, 25.5)
# - Distance must be > 0
```

---

## 📋 Summary of IDs

As you interact with the system:

- **Rider IDs:** RIDER-1, RIDER-2, RIDER-3, ...
- **Driver IDs:** DRIVER-1, DRIVER-2, DRIVER-3, ...
- **Ride IDs:** RIDE-1, RIDE-2, RIDE-3, ...

You'll see these IDs printed when registering or creating records.

---

## 🎓 Understanding the Output

### When Registering Rider
```
Rider registered: Rider{id='RIDER-1', name='Alice', location=10.0}
```
- ID: RIDER-1 (auto-generated)
- Use this for requesting rides

### When Assigning Ride
```
Ride created & driver assigned: Ride{id='RIDE-1', rider=Rider{...}, driver=Driver{...}, distance=5.0, status=ASSIGNED}
```
- Status: ASSIGNED (driver found and assigned)
- Distance: 5.0 (your input)
- Use RIDE-1 for completing ride

### When Completing Ride
```
Ride completed!
  FareReceipt{rideId='RIDE-1', amount=100.0, generatedAt=2026-04-02T22:35:12.123456}
```
- Amount: 100.0 (calculated fare)
- generatedAt: Timestamp when ride completed

---

## 🚀 Pro Tips

1. **Keep a notepad** - Write down IDs as you create records
2. **Test both strategies** - Run app twice with different selections
3. **Monitor driver availability** - Watch how drivers become unavailable after ride assignment
4. **Check status transitions** - See rides move through ASSIGNED → COMPLETED
5. **Test error scenarios** - Try invalid inputs to see error handling

---

## ⏭️ Next: Read the Full README

For detailed architecture, design patterns, SOLID principles, and FAQs, read:

```
README.md
```

This quick start covers **running the app**. The main README covers:
- Complete architecture explanation
- Design pattern details
- SOLID principles breakdown
- Step-by-step workflow explanations
- Troubleshooting FAQs
- Future enhancements

---

## 💬 Questions?

Refer to **README.md** (Section: FAQ & Troubleshooting) for detailed answers!

---

**Enjoy exploring RideWise! 🚗✨**


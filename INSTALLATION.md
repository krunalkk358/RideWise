# RideWise - Installation & Verification Guide ✅

Complete step-by-step installation and verification instructions.

---

## 📋 Verification Checklist

Run through this checklist to ensure your installation is complete and working.

### ✅ Step 1: Verify Java Installation

```powershell
java -version
javac -version
```

**Expected output:**
```
java version "1.8.0_..." or higher
javac 1.8.0_... or higher
```

**If it fails:**
- Download JDK: https://www.oracle.com/java/technologies/downloads/
- Add Java to PATH environment variable
- Restart PowerShell

---

### ✅ Step 2: Navigate to Project Directory

```powershell
cd "d:\AirTribe\Airtribe-Projects\ride-wise app DB\ridewise\ridewise"
pwd  # Verify current directory
```

**Expected output:**
```
D:\AirTribe\Airtribe-Projects\ride-wise app DB\ridewise\ridewise
```

---

### ✅ Step 3: Verify Project Structure

```powershell
dir
```

**Expected output - should contain:**
```
docs/
src/
out/
README.md
QUICKSTART.md
```

Verify the structure:
```powershell
dir src\com\airtribe\ridewise
```

Should show:
```
Main.java
exception/
model/
service/
strategy/
util/
```

---

### ✅ Step 4: Compile the Project

**PowerShell (Multi-line):**
```powershell
javac -d out `
  src/com/airtribe/ridewise/*.java `
  src/com/airtribe/ridewise/model/*.java `
  src/com/airtribe/ridewise/service/*.java `
  src/com/airtribe/ridewise/strategy/*.java `
  src/com/airtribe/ridewise/exception/*.java `
  src/com/airtribe/ridewise/util/*.java
```

**Alternative (single line):**
```powershell
javac -d out src/com/airtribe/ridewise/*.java src/com/airtribe/ridewise/model/*.java src/com/airtribe/ridewise/service/*.java src/com/airtribe/ridewise/strategy/*.java src/com/airtribe/ridewise/exception/*.java src/com/airtribe/ridewise/util/*.java
```

**Verify compilation:**
```powershell
dir out\com\airtribe\ridewise
```

Should show folders:
```
exception/
model/
service/
strategy/
util/
Main.class
```

✅ **Success:** All classes compiled without errors.

---

### ✅ Step 5: Run the Application

```powershell
java -cp out com.airtribe.ridewise.Main
```

**Expected output:**
```
Select Ride Matching Strategy:
  1. Nearest Driver
  2. Least Active Driver
Enter choice (1/2):
```

✅ **Success:** Application started and waiting for input.

---

### ✅ Step 6: Test the Application

#### Test Sequence:

**Input Choice 1:**
```
1
>> Using: Nearest Driver Strategy
```

**Input Choice 1:**
```
1
>> Using: Default Fare Strategy
```

**You should see:**
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

#### Test Add Rider:
```powershell
# Input:
1
John Doe
10

# Expected:
Rider registered: Rider{id='RIDER-1', name='John Doe', location=10.0}
```

#### Test Add Driver:
```powershell
# Input:
2
Alice
8

# Expected:
Driver registered: Driver{id='DRIVER-1', name='Alice', location=8.0, available=true, ridesCompleted=0}
```

#### Test View Available Drivers:
```powershell
# Input:
3

# Expected:
Available Drivers:
  Driver{id='DRIVER-1', name='Alice', location=8.0, available=true, ridesCompleted=0}
```

#### Test Request Ride:
```powershell
# Input:
4
RIDER-1
5

# Expected:
Ride created & driver assigned: Ride{id='RIDE-1', rider=Rider{id='RIDER-1', name='John Doe', location=10.0}, driver=Driver{id='DRIVER-1', name='Alice', location=8.0, available=false, ridesCompleted=0}, distance=5.0, status=ASSIGNED}
```

Notice: Driver is now `available=false`

#### Test View Rides:
```powershell
# Input:
6

# Expected:
All Rides:
  Ride{id='RIDE-1', rider=Rider{id='RIDER-1', name='John Doe', location=10.0}, driver=Driver{id='DRIVER-1', name='Alice', location=8.0, available=false, ridesCompleted=0}, distance=5.0, status=ASSIGNED}
```

#### Test Complete Ride:
```powershell
# Input:
5
RIDE-1

# Expected:
Ride completed!
  FareReceipt{rideId='RIDE-1', amount=100.0, generatedAt=2026-04-02T22:35:12.123456}
```

Fare calculation: 50 (base) + 5 × 10 = 100 ✓

#### Test Exit:
```powershell
# Input:
7

# Expected:
Thank you for using RideWise. Goodbye!
```

✅ **Success:** Application ran all tests successfully!

---

## 🔍 Detailed Verification Checklist

### Compilation Verification

```powershell
# Check Main class
Test-Path out\com\airtribe\ridewise\Main.class

# Check model classes
Test-Path out\com\airtribe\ridewise\model\Driver.class
Test-Path out\com\airtribe\ridewise\model\Rider.class
Test-Path out\com\airtribe\ridewise\model\Ride.class
Test-Path out\com\airtribe\ridewise\model\FareReceipt.class

# Check service classes
Test-Path out\com\airtribe\ridewise\service\RideService.class
Test-Path out\com\airtribe\ridewise\service\DriverService.class
Test-Path out\com\airtribe\ridewise\service\RiderService.class

# Check strategy classes
Test-Path out\com\airtribe\ridewise\strategy\NearestDriverStrategy.class
Test-Path out\com\airtribe\ridewise\strategy\LeastActiveDriverStrategy.class
Test-Path out\com\airtribe\ridewise\strategy\DefaultFareStrategy.class
Test-Path out\com\airtribe\ridewise\strategy\PeakHourFareStrategy.class
```

All should return: `True`

---

## 📊 Expected File Sizes (Approximate)

```
Main.class                      ~5 KB
Driver.class                    ~2 KB
Rider.class                     ~1 KB
Ride.class                      ~2 KB
FareReceipt.class              ~1 KB
RideService.class              ~4 KB
DriverService.class            ~2 KB
RiderService.class             ~1 KB
NearestDriverStrategy.class     ~1 KB
DefaultFareStrategy.class       ~1 KB
```

**Total compiled size:** ~20-25 KB

---

## 🐛 Common Installation Issues

### Issue 1: "No source files"
```
error: no source files
```

**Solution:**
- Ensure you're in the correct directory
- Run: `cd "d:\AirTribe\Airtribe-Projects\ride-wise app DB\ridewise\ridewise"`
- Verify `src` folder exists: `dir src`

---

### Issue 2: "Cannot find symbol"
```
error: cannot find symbol: class Scanner
```

**Solution:**
- This shouldn't happen (java.util is built-in)
- Ensure you're using JDK (not JRE)
- Reinstall Java JDK

---

### Issue 3: "Main class not found"
```
Error: Could not find or load main class com.airtribe.ridewise.Main
```

**Solution:**
- Verify compilation: `dir out\com\airtribe\ridewise\Main.class`
- Verify command uses correct classpath: `java -cp out com.airtribe.ridewise.Main`
- Clean and recompile: `rmdir out /s /q` then compile again

---

### Issue 4: Multiple Main methods
```
error: duplicate method main(String[])
```

**Solution:**
- Don't have any other Main.java files
- Run from correct directory where compilation works

---

### Issue 5: Invalid syntax during compilation

**Solution:**
- On Windows PowerShell, use backticks (`) for line continuation, NOT backslash
- Copy the exact cmd from QUICKSTART.md

---

## 🧪 Test Cases Checklist

| Test Case | Input | Expected Result | Status |
|-----------|-------|-----------------|--------|
| Startup | - | Show strategy selection menu | ✓ |
| Add Rider | Name: John, Location: 10 | RIDER-1 created | ✓ |
| Add Driver | Name: Alice, Location: 8 | DRIVER-1 created | ✓ |
| View Drivers | Option 3 | Alice shown available | ✓ |
| Request Ride | RIDER-1, 5km | RIDE-1 assigned to Alice | ✓ |
| Driver Busy | Check drivers | Alice unavailable | ✓ |
| Complete Ride | RIDE-1 | Fare receipt (100) | ✓ |
| Driver Available | Check drivers | Alice available again | ✓ |
| View Rides | Option 6 | RIDE-1 shows COMPLETED | ✓ |
| Statistics | Check Alice stats | ridesCompleted=1 | ✓ |
| Exit | Option 7 | Goodbye message | ✓ |

---

## 📈 Performance Metrics

### Compilation Time
- **First compilation:** 2-5 seconds
- **Subsequent:** 1-2 seconds (only changed files)

### Runtime Performance
- **Startup time:** ~1 second
- **Menu response:** Instant
- **ID generation:** < 1ms
- **Driver matching:** < 1ms (linear search)
- **Fare calculation:** < 1ms

### Memory Usage
- **Typical session:** ~50-100 MB
- **Compiled code size:** ~20 KB

---

## 🎓 What the Verification Confirms

✅ Java environment is properly set up
✅ All source files compile without errors
✅ All classes and interfaces are syntactically correct
✅ Package structure is correct
✅ Classpath is properly configured
✅ Application initializes without errors
✅ Strategy pattern works correctly
✅ Service layer is functional
✅ User input is processed correctly
✅ Error handling works as expected
✅ Output formatting is correct

---

## 📚 Next Steps After Verification

1. **Read QUICKSTART.md** - Interactive walkthrough
2. **Read README.md** - Full architecture & design patterns
3. **Explore docs/** - Requirements & design documentation
4. **Review source code** - Understand implementation
5. **Experiment with strategies** - Change matching/pricing strategies
6. **Modify and extend** - Add your own strategies or features

---

## ✨ You're All Set!

If all checks pass, congratulations! 🎉

Your RideWise application is:
- ✅ Successfully compiled
- ✅ Properly configured
- ✅ Ready to use
- ✅ Ready to extend

---

## 📞 Need Help?

Refer to:
- **QUICKSTART.md** - For usage help
- **README.md** - For FAQ section
- **docs/** - For design documentation

---

**Happy Coding! 🚀**


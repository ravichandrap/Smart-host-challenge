# Smart-host-challenge
Requirements
Build a room occupancy optimization tool for one of our hotel clients! Our customer has a certain number of rooms each night, as well as potential guests that would like to book a room for that night.

Our hotel clients have two different categories of rooms: Premium and Economy. Our hotels want their customers to be satisfied: they will not book a customer willing to pay EUR 100 or more for the night into an Economy room. But they will book lower paying customers into Premium rooms if these rooms would be empty and all Economy rooms will be filled by low paying customers. Highest paying customers below EUR 100 will get preference for the “upgrade”. Customers always only have one specific price they are willing to pay for the night.

Please build a small API that provides an interface for hotels to enter the numbers of Premium and Economy rooms that are available for the night and then tells them immediately how many rooms of each category will be occupied and how much money they will make in total. Potential guests are represented by an array of numbers that is their willingness to pay for the night.

Use the following raw JSON file/structure as mock data for potential guests in your tests: • [23, 45, 155, 374, 22, 99, 100, 101, 115, 209]


# Testing with jUnit
```aidl
    ./gradlew clean test - Unix
    gradle.bat clean test - Windows
```

# Testing with Swagger 
Run the following gradle command to start the application.
```$xslt
    ./gradlew bootRun - Unix
    gradle.bat bootRun - Windows
```
Open Swagger ui in browser.
[Swagger UI](http://localhost:8089/swagger-ui/#/room-manager-controller/occupancyUsingPOST)

**Input Date (Request body)**
```$xslt
{
  "economyRooms": 5,
  "premiumRooms": 7
}
```

**Output Date (Response body)**
```$xslt
{
  "totalEconomyPrice": 189,
  "allocatedEconomyRooms": 4,
  "allocatedPremiumRooms": 6,
  "totalPremiumPrice": 1054,
  "total": 1243
}
```
**Test 1**

• (input)  Premium rooms (premiumRooms): 3

• (input) Economy rooms (economyRooms): 3

• (output) Usage Premium (totalPremiumPrice): 3 

• (output) Usage Economy (totalEconomyPrice): 3

• (output) Usage Premium (allocatedPremiumRooms):EUR 738

• (output) Usage Economy (allocatedEconomyRooms):EUR 167 

• (output) Usage Total (total): EUR 905 


**Test 2**

• (input)  Premium rooms (premiumRooms): 7

• (input) Economy rooms (economyRooms): 5

• (output) Usage Premium (totalPremiumPrice): 6

• (output) Usage Economy (totalEconomyPrice): 4

• (output) Usage Premium (allocatedPremiumRooms):EUR 1054

• (output) Usage Economy (allocatedEconomyRooms):EUR 189

• (output) Usage Total (total): EUR 1243 


**Test 3**

• (input)  Premium rooms (premiumRooms): 2

• (input) Economy rooms (economyRooms): 7

• (output) Usage Premium (totalPremiumPrice): 2

• (output) Usage Economy (totalEconomyPrice): 4

• (output) Usage Premium (allocatedPremiumRooms):EUR 583

• (output) Usage Economy (allocatedEconomyRooms):EUR 189

• (output) Usage Total (total): EUR 772 


**Test 4**

• (input)  Premium rooms (premiumRooms): 7

• (input) Economy rooms (economyRooms): 1

• (output) Usage Premium (totalPremiumPrice): 7

• (output) Usage Economy (totalEconomyPrice): 1

• (output) Usage Premium (allocatedPremiumRooms):EUR 1099

• (output) Usage Economy (allocatedEconomyRooms):EUR 99

• (output) Usage Total (total): EUR 1198 


**Test 5**

• (input)  Premium rooms (premiumRooms): 0

• (input) Economy rooms (economyRooms): 0

• (output) Usage Premium (totalPremiumPrice): 0

• (output) Usage Economy (totalEconomyPrice): 0

• (output) Usage Premium (allocatedPremiumRooms):EUR 0

• (output) Usage Economy (allocatedEconomyRooms):EUR 0

• (output) Usage Total (total): EUR 0 

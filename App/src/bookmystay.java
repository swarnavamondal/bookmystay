/**
 * HotelBookingApp demonstrates centralized room inventory management.
 * <p>
 * This use case introduces the use of HashMap to store and manage room availability
 * in a single, consistent structure rather than scattered variables.
 * </p>
 *
 * Author: YourName
 * Version: 1.2
 */

import java.util.HashMap;
import java.util.Map;

public class HotelBookingApp {

    public static void main(String[] args) {
        System.out.println("=======================================");
        System.out.println("     Welcome to Hotel Booking System    ");
        System.out.println("               Version 1.2              ");
        System.out.println("=======================================");

        // Initialize room objects
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Initialize centralized room inventory
        RoomInventory inventory = new RoomInventory();
        inventory.registerRoom(singleRoom.getType(), 10);
        inventory.registerRoom(doubleRoom.getType(), 5);
        inventory.registerRoom(suiteRoom.getType(), 2);

        // Display current inventory state
        System.out.println("\n--- Current Room Inventory ---\n");
        inventory.displayInventory();

        // Update inventory: e.g., 2 single rooms booked
        System.out.println("\nBooking 2 Single Rooms...");
        inventory.updateAvailability(singleRoom.getType(), -2);

        System.out.println("\n--- Updated Room Inventory ---\n");
        inventory.displayInventory();

        System.out.println("\nApplication execution completed.");
    }
}

/**
 * Abstract class representing a general room.
 */
abstract class Room {
    protected String type;
    protected int beds;
    protected double pricePerNight;

    public String getType() {
        return type;
    }

    public String getDetails() {
        return "Room Type: " + type + ", Beds: " + beds + ", Price/Night: $" + pricePerNight;
    }
}

/**
 * Concrete class for Single Room.
 */
class SingleRoom extends Room {
    public SingleRoom() {
        this.type = "Single Room";
        this.beds = 1;
        this.pricePerNight = 50.0;
    }
}

/**
 * Concrete class for Double Room.
 */
class DoubleRoom extends Room {
    public DoubleRoom() {
        this.type = "Double Room";
        this.beds = 2;
        this.pricePerNight = 90.0;
    }
}

/**
 * Concrete class for Suite Room.
 */
class SuiteRoom extends Room {
    public SuiteRoom() {
        this.type = "Suite Room";
        this.beds = 3;
        this.pricePerNight = 150.0;
    }
}

/**
 * RoomInventory manages room availability using a centralized HashMap.
 */
class RoomInventory {
    private Map<String, Integer> inventoryMap;

    public RoomInventory() {
        inventoryMap = new HashMap<>();
    }

    /**
     * Register a room type with its initial availability.
     * @param roomType the type of room
     * @param count initial number of available rooms
     */
    public void registerRoom(String roomType, int count) {
        inventoryMap.put(roomType, count);
    }

    /**
     * Retrieve availability for a specific room type.
     * @param roomType the type of room
     * @return number of available rooms
     */
    public int getAvailability(String roomType) {
        return inventoryMap.getOrDefault(roomType, 0);
    }

    /**
     * Update the availability of a room type.
     * Positive count increases availability; negative decreases it.
     * @param roomType the type of room
     * @param change number of rooms to add or subtract
     */
    public void updateAvailability(String roomType, int change) {
        int current = inventoryMap.getOrDefault(roomType, 0);
        int updated = current + change;
        if (updated < 0) {
            System.out.println("Error: Not enough rooms available for " + roomType);
        } else {
            inventoryMap.put(roomType, updated);
        }
    }

    /**
     * Display all room types with their current availability.
     */
    public void displayInventory() {
        for (Map.Entry<String, Integer> entry : inventoryMap.entrySet()) {
            System.out.println("Room Type: " + entry.getKey() + " | Available: " + entry.getValue());
        }
    }
}
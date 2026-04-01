/**
 * HotelBookingApp demonstrates room search and availability check.
 * <p>
 * This use case introduces read-only access to inventory for guests,
 * ensuring system state remains unchanged while providing accurate information.
 * </p>
 *
 * Author: YourName
 * Version: 1.3
 */

import java.util.ArrayList;
import java.util.List;

public class HotelBookingApp {

    public static void main(String[] args) {
        System.out.println("=======================================");
        System.out.println("     Welcome to Hotel Booking System    ");
        System.out.println("               Version 1.3              ");
        System.out.println("=======================================");

        // Initialize room objects
        List<Room> rooms = new ArrayList<>();
        rooms.add(new SingleRoom());
        rooms.add(new DoubleRoom());
        rooms.add(new SuiteRoom());

        // Initialize centralized room inventory
        RoomInventory inventory = new RoomInventory();
        inventory.registerRoom("Single Room", 10);
        inventory.registerRoom("Double Room", 0); // simulate fully booked
        inventory.registerRoom("Suite Room", 2);

        // Perform read-only search
        SearchService searchService = new SearchService(inventory, rooms);
        System.out.println("\n--- Available Rooms for Guests ---\n");
        searchService.displayAvailableRooms();

        System.out.println("\nApplication execution completed.");
    }
}

/**
 * Service to handle guest room search without modifying inventory.
 */
class SearchService {
    private RoomInventory inventory;
    private List<Room> rooms;

    public SearchService(RoomInventory inventory, List<Room> rooms) {
        this.inventory = inventory;
        this.rooms = rooms;
    }

    /**
     * Displays all rooms with availability greater than zero.
     */
    public void displayAvailableRooms() {
        boolean anyAvailable = false;

        for (Room room : rooms) {
            int availableCount = inventory.getAvailability(room.getType());
            if (availableCount > 0) {
                System.out.println(room.getDetails() + " | Available: " + availableCount);
                anyAvailable = true;
            }
        }

        if (!anyAvailable) {
            System.out.println("No rooms are currently available.");
        }
    }
}

/* --- Room and RoomInventory classes reused from Use Case 3 --- */
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

class SingleRoom extends Room {
    public SingleRoom() {
        this.type = "Single Room";
        this.beds = 1;
        this.pricePerNight = 50.0;
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        this.type = "Double Room";
        this.beds = 2;
        this.pricePerNight = 90.0;
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        this.type = "Suite Room";
        this.beds = 3;
        this.pricePerNight = 150.0;
    }
}

import java.util.HashMap;
import java.util.Map;

class RoomInventory {
    private Map<String, Integer> inventoryMap;

    public RoomInventory() {
        inventoryMap = new HashMap<>();
    }

    public void registerRoom(String roomType, int count) {
        inventoryMap.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return inventoryMap.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int change) {
        int current = inventoryMap.getOrDefault(roomType, 0);
        int updated = current + change;
        if (updated < 0) {
            System.out.println("Error: Not enough rooms available for " + roomType);
        } else {
            inventoryMap.put(roomType, updated);
        }
    }

    public void displayInventory() {
        for (Map.Entry<String, Integer> entry : inventoryMap.entrySet()) {
            System.out.println("Room Type: " + entry.getKey() + " | Available: " + entry.getValue());
        }
    }
}
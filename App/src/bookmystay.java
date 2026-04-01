/**
 * HotelBookingApp demonstrates basic room types and their static availability.
 * <p>
 * This use case introduces object-oriented modeling using abstraction,
 * inheritance, and polymorphism before introducing data structures.
 * </p>
 *
 * @author YourName
 * @version 1.1
 */
public class HotelBookingApp {

    public static void main(String[] args) {
        System.out.println("=======================================");
        System.out.println("     Welcome to Hotel Booking System    ");
        System.out.println("               Version 1.1              ");
        System.out.println("=======================================");

        // Initialize room objects
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Static availability for each room type
        int availableSingleRooms = 10;
        int availableDoubleRooms = 5;
        int availableSuiteRooms = 2;

        // Display room details and availability
        System.out.println("\n--- Room Types & Availability ---\n");

        System.out.println(singleRoom.getDetails() + " | Available: " + availableSingleRooms);
        System.out.println(doubleRoom.getDetails() + " | Available: " + availableDoubleRooms);
        System.out.println(suiteRoom.getDetails() + " | Available: " + availableSuiteRooms);

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

    /**
     * Get details of the room.
     * @return formatted string containing room type, beds, and price.
     */
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
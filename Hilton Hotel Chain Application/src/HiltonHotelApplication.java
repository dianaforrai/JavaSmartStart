import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class HiltonHotelApplication {
    private static final Scanner scanner = new Scanner(System.in);
    private static final HotelService hotelService = new HotelService();
    private static final GuestService guestService = new GuestService();
    private static final RoomService roomService = new RoomService();
    private static final ReservationService reservationService = new ReservationService();

    public static void main(String[] args) {
        System.out.println("Welcome to Hilton Hotel Chain Management System");
        System.out.println("==============================================");

        // Initialize database
        DatabaseConnection.createTables();

        while (true) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");

            try {
                switch (choice) {
                    case 1 -> addHotelMenu();
                    case 2 -> viewHotelMenu();
                    case 3 -> addRoomMenu();
                    case 4 -> viewRoomsMenu();
                    case 5 -> addGuestMenu();
                    case 6 -> makeReservationMenu();
                    case 7 -> cancelReservationMenu();
                    case 8 -> viewReservationsMenu();
                    case 9 -> {
                        System.out.println("Thank you for using Hilton Hotel Management System!");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
                System.out.println("Please try again.");
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    private static void displayMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("HILTON HOTEL MANAGEMENT SYSTEM - MAIN MENU");
        System.out.println("=".repeat(50));
        System.out.println("1. Add Hotel Details");
        System.out.println("2. View Hotel Details");
        System.out.println("3. Add Room Details");
        System.out.println("4. View Available Rooms");
        System.out.println("5. Add Guest Details");
        System.out.println("6. Make Reservation");
        System.out.println("7. Cancel Reservation");
        System.out.println("8. View Reservations");
        System.out.println("9. Exit");
        System.out.println("=".repeat(50));
    }
    private static void addHotelMenu() {
        System.out.println("\n--- Add Hotel Details ---");

        String name = getStringInput("Enter hotel name: ");
        String location = getStringInput("Enter hotel location (city, state, country): ");

        Hotel hotel = new Hotel(0, name, location);

        if (hotelService.addHotel(hotel)) {
            System.out.println("✓ Hotel added successfully!");
        } else {
            System.out.println("✗ Failed to add hotel. Please try again.");
        }
    }
    private static void viewHotelMenu() {
        System.out.println("\n--- Hotel Details ---");

        int hotelId = getIntInput("Enter hotel ID to view details: ");
        Hotel hotel = hotelService.getHotel(hotelId);

        if (hotel != null) {
            System.out.println("\nHotel Information:");
            System.out.println("-".repeat(40));
            System.out.println(hotel);

            // Show rooms, guests, and reservations summary
            List<Room> rooms = roomService.getRoomsForHotel(hotelId);
            List<Guest> guests = guestService.getGuestsForHotel(hotelId);
            List<Reservation> reservations = reservationService.getReservationsForHotel(hotelId);

            System.out.println("\nSummary:");
            System.out.println("Total Rooms: " + rooms.size());
            System.out.println("Total Guests: " + guests.size());
            System.out.println("Total Reservations: " + reservations.size());
        } else {
            System.out.println("✗ Hotel not found with ID: " + hotelId);
        }
    }
    private static void addRoomMenu() {
        System.out.println("\n--- Add Room Details ---");

        // First show available hotels
        List<Hotel> hotels = hotelService.getAllHotels();
        if (hotels.isEmpty()) {
            System.out.println("✗ No hotels found. Please add a hotel first.");
            return;
        }

        System.out.println("\nAvailable Hotels:");
        for (Hotel hotel : hotels) {
            System.out.println(hotel);
        }

        int hotelId = getIntInput("\nEnter hotel ID: ");
        if (hotelService.getHotel(hotelId) == null) {
            System.out.println("✗ Invalid hotel ID!");
            return;
        }

        int roomNumber = getIntInput("Enter room number: ");
        String type = getStringInput("Enter room type (single/double/suite): ");

        Room room = new Room(roomNumber, type, true, hotelId);

        if (roomService.addRoom(room)) {
            System.out.println("✓ Room added successfully!");
        } else {
            System.out.println("✗ Failed to add room. Room might already exist.");
        }
    }
    private static void viewRoomsMenu() {
        System.out.println("\n--- Available Rooms ---");

        int hotelId = getIntInput("Enter hotel ID: ");
        List<Room> rooms = roomService.getRoomsForHotel(hotelId);

        if (rooms.isEmpty()) {
            System.out.println("✗ No rooms found for hotel ID: " + hotelId);
            return;
        }

        System.out.println("\nRooms in Hotel ID " + hotelId + ":");
        System.out.println("-".repeat(60));
        for (Room room : rooms) {
            System.out.println(room);
        }
    }
    private static void addGuestMenu() {
        System.out.println("\n--- Add Guest Details ---");

        // First show available hotels
        List<Hotel> hotels = hotelService.getAllHotels();
        if (hotels.isEmpty()) {
            System.out.println("✗ No hotels found. Please add a hotel first.");
            return;
        }

        System.out.println("\nAvailable Hotels:");
        for (Hotel hotel : hotels) {
            System.out.println(hotel);
        }

        int hotelId = getIntInput("\nEnter hotel ID: ");
        if (hotelService.getHotel(hotelId) == null) {
            System.out.println("✗ Invalid hotel ID!");
            return;
        }

        String name = getStringInput("Enter guest name: ");

        String email;
        do {
            email = getStringInput("Enter guest email: ");
            if (!ValidationUtil.isValidEmail(email)) {
                System.out.println("✗ Invalid email format! Please try again.");
            }
        } while (!ValidationUtil.isValidEmail(email));

        String phone;
        do {
            phone = getStringInput("Enter guest phone number: ");
            if (!ValidationUtil.isValidPhone(phone)) {
                System.out.println("✗ Invalid phone format! Please enter 10-15 digits.");
            }
        } while (!ValidationUtil.isValidPhone(phone));

        Guest guest = new Guest(0, name, email, phone, hotelId);

        if (guestService.addGuest(guest)) {
            System.out.println("✓ Guest added successfully!");
        } else {
            System.out.println("✗ Failed to add guest. Email might already exist.");
        }
    }
    private static void makeReservationMenu() {
        System.out.println("\n--- Make Reservation ---");

        int hotelId = getIntInput("Enter hotel ID: ");

        // Show available rooms
        List<Room> availableRooms = roomService.getRoomsForHotel(hotelId);
        availableRooms.removeIf(room -> !room.isAvailable());

        if (availableRooms.isEmpty()) {
            System.out.println("✗ No available rooms in this hotel.");
            return;
        }

        System.out.println("\nAvailable Rooms:");
        for (Room room : availableRooms) {
            System.out.println(room);
        }

        // Show guests for this hotel
        List<Guest> guests = guestService.getGuestsForHotel(hotelId);
        if (guests.isEmpty()) {
            System.out.println("✗ No guests found for this hotel. Please add a guest first.");
            return;
        }

        System.out.println("\nGuests:");
        for (Guest guest : guests) {
            System.out.println(guest);
        }

        int guestId = getIntInput("\nEnter guest ID: ");
        int roomNumber = getIntInput("Enter room number: ");

        // Validate room is available
        Room selectedRoom = roomService.getRoom(roomNumber, hotelId);
        if (selectedRoom == null || !selectedRoom.isAvailable()) {
            System.out.println("✗ Room is not available!");
            return;
        }

        LocalDate checkInDate;
        do {
            String checkInStr = getStringInput("Enter check-in date (YYYY-MM-DD): ");
            checkInDate = ValidationUtil.parseDate(checkInStr);
            if (checkInDate == null || checkInDate.isBefore(LocalDate.now())) {
                System.out.println("✗ Invalid date! Please enter a valid future date.");
            }
        } while (checkInDate == null || checkInDate.isBefore(LocalDate.now()));

        LocalDate checkOutDate;
        do {
            String checkOutStr = getStringInput("Enter check-out date (YYYY-MM-DD): ");
            checkOutDate = ValidationUtil.parseDate(checkOutStr);
            if (checkOutDate == null || !checkOutDate.isAfter(checkInDate)) {
                System.out.println("✗ Invalid date! Check-out must be after check-in date.");
            }
        } while (checkOutDate == null || !checkOutDate.isAfter(checkInDate));

        Reservation reservation = new Reservation(0, guestId, roomNumber, checkInDate,
                checkOutDate, LocalDate.now(), "confirmed", hotelId);

        if (reservationService.makeReservation(reservation)) {
            System.out.println("✓ Reservation made successfully!");
            System.out.println("Reservation Details:");
            System.out.println("Guest ID: " + guestId);
            System.out.println("Room: " + roomNumber);
            System.out.println("Check-in: " + checkInDate);
            System.out.println("Check-out: " + checkOutDate);
            System.out.println("Status: confirmed");
        } else {
            System.out.println("✗ Failed to make reservation. Please try again.");
        }
    }
    private static void cancelReservationMenu() {
        System.out.println("\n--- Cancel Reservation ---");

        int hotelId = getIntInput("Enter hotel ID: ");

        // Show reservations for this hotel
        List<Reservation> reservations = reservationService.getReservationsForHotel(hotelId);
        reservations.removeIf(r -> r.getStatus().equals("cancelled"));

        if (reservations.isEmpty()) {
            System.out.println("✗ No active reservations found for this hotel.");
            return;
        }

        System.out.println("\nActive Reservations:");
        System.out.println("-".repeat(80));
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }

        int reservationId = getIntInput("\nEnter reservation ID to cancel: ");

        if (reservationService.cancelReservation(reservationId)) {
            System.out.println("✓ Reservation cancelled successfully!");
            System.out.println("The room is now available for booking.");
        } else {
            System.out.println("✗ Failed to cancel reservation. Please check the reservation ID.");
        }
    }

    private static void viewReservationsMenu() {
        System.out.println("\n--- View Reservations ---");

        int hotelId = getIntInput("Enter hotel ID: ");
        List<Reservation> reservations = reservationService.getReservationsForHotel(hotelId);

        if (reservations.isEmpty()) {
            System.out.println("✗ No reservations found for hotel ID: " + hotelId);
            return;
        }

        System.out.println("\nReservations for Hotel ID " + hotelId + ":");
        System.out.println("-".repeat(80));

        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }

        System.out.println("\nReservation Summary:");
        long activeReservations = reservations.stream()
                .filter(r -> r.getStatus().equals("confirmed"))
                .count();
        long cancelledReservations = reservations.stream()
                .filter(r -> r.getStatus().equals("cancelled"))
                .count();

        System.out.println("Total Reservations: " + reservations.size());
        System.out.println("Active Reservations: " + activeReservations);
        System.out.println("Cancelled Reservations: " + cancelledReservations);
    }
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        while (input.isEmpty()) {
            System.out.print("Input cannot be empty. " + prompt);
            input = scanner.nextLine().trim();
        }
        return input;
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input! Please enter a valid number.");
            }
        }
    }
}




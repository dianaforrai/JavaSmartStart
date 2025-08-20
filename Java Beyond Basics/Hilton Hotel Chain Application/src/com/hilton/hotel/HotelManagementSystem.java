// HotelManagementSystem.java - Complete Main Application with Menu
package com.hilton.hotel;

import com.hilton.hotel.model.*;
import com.hilton.hotel.service.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class HotelManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final HotelService hotelService = new HotelService();
    private static final RoomService roomService = new RoomService();
    private static final GuestService guestService = new GuestService();
    private static final ReservationService reservationService = new ReservationService();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        System.out.println("Welcome to Hilton Hotel Management System");

        while (true) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");

            try {
                switch (choice) {
                    case 1:
                        addHotel();
                        break;
                    case 2:
                        viewHotelDetails();
                        break;
                    case 3:
                        addRoom();
                        break;
                    case 4:
                        viewRoomsForHotel();
                        break;
                    case 5:
                        addGuest();
                        break;
                    case 6:
                        viewGuestsForHotel();
                        break;
                    case 7:
                        makeReservation();
                        break;
                    case 8:
                        cancelReservation();
                        break;
                    case 9:
                        viewReservationsForHotel();
                        break;
                    case 10:
                        viewAvailableRooms();
                        break;
                    case 0:
                        System.out.println("Thank you for using Hilton Hotel Management System!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== HILTON HOTEL MANAGEMENT SYSTEM ===");
        System.out.println("1. Add Hotel");
        System.out.println("2. View Hotel Details");
        System.out.println("3. Add Room");
        System.out.println("4. View Rooms for Hotel");
        System.out.println("5. Add Guest");
        System.out.println("6. View Guests for Hotel");
        System.out.println("7. Make Reservation");
        System.out.println("8. Cancel Reservation");
        System.out.println("9. View Reservations for Hotel");
        System.out.println("10. View Available Rooms");
        System.out.println("0. Exit");
        System.out.println("======================================");
    }

    private static void addHotel() throws SQLException {
        System.out.println("\n--- Add Hotel ---");
        String name = getStringInput("Enter hotel name: ");
        String location = getStringInput("Enter hotel location: ");

        Hotel hotel = new Hotel(name, location);
        hotelService.addHotel(hotel);
    }

    private static void viewHotelDetails() throws SQLException {
        System.out.println("\n--- View Hotel Details ---");
        int hotelId = getIntInput("Enter hotel ID: ");

        Hotel hotel = hotelService.getHotel(hotelId);
        if (hotel != null) {
            System.out.println(hotel);

            // Display rooms, guests, and reservations
            List<Room> rooms = roomService.getRoomsForHotel(hotelId);
            List<Guest> guests = guestService.getGuestsForHotel(hotelId);
            List<Reservation> reservations = reservationService.getReservationsForHotel(hotelId);

            System.out.println("\nRooms: " + rooms.size());
            System.out.println("Guests: " + guests.size());
            System.out.println("Reservations: " + reservations.size());
        } else {
            System.out.println("Hotel not found!");
        }
    }

    private static void addRoom() throws SQLException {
        System.out.println("\n--- Add Room ---");
        int roomNumber = getIntInput("Enter room number: ");
        String type = getStringInput("Enter room type (single/double/suite): ");
        int hotelId = getIntInput("Enter hotel ID: ");

        Room room = new Room(roomNumber, type, true, hotelId);
        roomService.addRoom(room);
    }

    private static void viewRoomsForHotel() throws SQLException {
        System.out.println("\n--- View Rooms for Hotel ---");
        int hotelId = getIntInput("Enter hotel ID: ");

        List<Room> rooms = roomService.getRoomsForHotel(hotelId);
        if (rooms.isEmpty()) {
            System.out.println("No rooms found for this hotel.");
        } else {
            rooms.forEach(System.out::println);
        }
    }

    private static void addGuest() throws SQLException {
        System.out.println("\n--- Add Guest ---");
        String name = getStringInput("Enter guest name: ");
        String email = getStringInput("Enter guest email: ");
        String phone = getStringInput("Enter guest phone: ");
        int hotelId = getIntInput("Enter hotel ID: ");

        Guest guest = new Guest(name, email, phone, hotelId);
        guestService.addGuest(guest);
    }

    private static void viewGuestsForHotel() throws SQLException {
        System.out.println("\n--- View Guests for Hotel ---");
        int hotelId = getIntInput("Enter hotel ID: ");

        List<Guest> guests = guestService.getGuestsForHotel(hotelId);
        if (guests.isEmpty()) {
            System.out.println("No guests found for this hotel.");
        } else {
            guests.forEach(System.out::println);
        }
    }

    private static void makeReservation() throws SQLException {
        System.out.println("\n--- Make Reservation ---");
        int guestId = getIntInput("Enter guest ID: ");
        int hotelId = getIntInput("Enter hotel ID: ");

        // Check if hotel has available rooms
        List<Room> availableRooms = roomService.getAvailableRoomsForHotel(hotelId);
        if (availableRooms.isEmpty()) {
            System.out.println("No available rooms in this hotel!");
            return;
        }

        System.out.println("Available rooms:");
        availableRooms.forEach(System.out::println);

        String checkInStr = getStringInput("Enter check-in date (yyyy-MM-dd): ");
        String checkOutStr = getStringInput("Enter check-out date (yyyy-MM-dd): ");

        try {
            Date checkInDate = dateFormat.parse(checkInStr);
            Date checkOutDate = dateFormat.parse(checkOutStr);
            Date reservationDate = new Date();

            Reservation reservation = new Reservation(guestId, hotelId, checkInDate,
                    checkOutDate, reservationDate, "confirmed");
            reservationService.makeReservationForRoom(reservation);

            // Update room availability (simplified - first available room)
            roomService.updateRoomAvailability(availableRooms.get(0).getRoomNumber(), false);

        } catch (ParseException e) {
            System.err.println("Invalid date format. Please use yyyy-MM-dd");
        }
    }

    private static void cancelReservation() throws SQLException {
        System.out.println("\n--- Cancel Reservation ---");
        int hotelId = getIntInput("Enter hotel ID: ");

        // Display reservations for the hotel
        List<Reservation> reservations = reservationService.getReservationsForHotel(hotelId);
        if (reservations.isEmpty()) {
            System.out.println("No reservations found for this hotel.");
            return;
        }

        System.out.println("Current reservations:");
        reservations.forEach(System.out::println);

        int reservationId = getIntInput("Enter reservation ID to cancel: ");
        reservationService.cancelRoomReservation(reservationId);

        // Note: In a complete implementation, you would also free up the room
        // by setting its availability back to true
    }

    private static void viewReservationsForHotel() throws SQLException {
        System.out.println("\n--- View Reservations for Hotel ---");
        int hotelId = getIntInput("Enter hotel ID: ");

        List<Reservation> reservations = reservationService.getReservationsForHotel(hotelId);
        if (reservations.isEmpty()) {
            System.out.println("No reservations found for this hotel.");
        } else {
            System.out.println("Reservations for Hotel ID " + hotelId + ":");
            reservations.forEach(System.out::println);
        }
    }

    private static void viewAvailableRooms() throws SQLException {
        System.out.println("\n--- View Available Rooms ---");
        int hotelId = getIntInput("Enter hotel ID: ");

        List<Room> availableRooms = roomService.getAvailableRoomsForHotel(hotelId);
        if (availableRooms.isEmpty()) {
            System.out.println("No available rooms found for this hotel.");
        } else {
            System.out.println("Available rooms for Hotel ID " + hotelId + ":");
            availableRooms.forEach(System.out::println);
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
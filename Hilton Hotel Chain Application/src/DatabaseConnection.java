import java.sql.*;
import java.util.*;
import java.time.LocalDate;

import static java.sql.DriverManager.getConnection;

class Hotel {
    private int hotelId;
    private String name;
    private String location;

    public Hotel(int hotelId, String name, String location) {
        this.hotelId = hotelId;
        this.name = name;
        this.location = location;
    }

    public int getHotelId() {
        return hotelId;
    }
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return String.format("Hotel ID: %d | Name: %s | Location: %s", hotelId, name, location);
    }
}

class Guest {
    private int guestId;
    private String name;
    private String email;
    private String phone;
    private int hotelId;

    public Guest(int guestId, String name, String email, String phone, int hotelId) {
        this.guestId = guestId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.hotelId = hotelId;
    }

    // Getters and Setters
    public int getGuestId() {
        return guestId;
    }
    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getHotelId() {
        return hotelId;
    }
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString() {
        return String.format("Guest ID: %d | Name: %s | Email: %s | Phone: %s | Hotel ID: %d",
                guestId, name, email, phone, hotelId);
    }
}

class Room {
    private int roomNumber;
    private String type;
    private boolean available;
    private int hotelId;

    public Room(int roomNumber, String type, boolean available, int hotelId) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.available = available;
        this.hotelId = hotelId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public int getHotelId() {
        return hotelId;
    }
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString() {
        return String.format("Room: %d | Type: %s | Available: %s | Hotel ID: %d",
                roomNumber, type, available ? "Yes" : "No", hotelId);
    }
}

class Reservation {
    private int reservationId;
    private int guestId;
    private int roomNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDate reservationDate;
    private String status;
    private int hotelId;

    public Reservation(int reservationId, int guestId, int roomNumber, LocalDate checkInDate,
                       LocalDate checkOutDate, LocalDate reservationDate, String status, int hotelId) {
        this.reservationId = reservationId;
        this.guestId = guestId;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.reservationDate = reservationDate;
        this.status = status;
        this.hotelId = hotelId;
    }

    public int getReservationId() {
        return reservationId;
    }
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }
    public int getGuestId() {
        return guestId;
    }
    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }
    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    public LocalDate getReservationDate() {
        return reservationDate;
    }
    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getHotelId() {
        return hotelId;
    }
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString() {
        return String.format("Reservation ID: %d | Guest ID: %d | Room: %d | Check-in: %s | Check-out: %s | Status: %s",
                reservationId, guestId, roomNumber, checkInDate, checkOutDate, status);
    }
}
public class databaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/hilton_hotel";
    private static final String USERNAME = "postgres"; // Default PostgreSQL username
    private static final String PASSWORD = "Hugo2002."; // Change this to your actual password

    // Connection pool settings
    private static final int CONNECTION_TIMEOUT = 30; // seconds
    private static final int MAX_RETRIES = 3;


    public static Connection getConnection() throws SQLException {
        int attempts = 0;
        SQLException lastException = null;

        while (attempts < MAX_RETRIES) {
            try {
                Class.forName("org.postgresql.Driver");

                Properties props = new Properties();
                props.setProperty("user", USERNAME);
                props.setProperty("password", PASSWORD);
                props.setProperty("ssl", "false");
                props.setProperty("loginTimeout", String.valueOf(CONNECTION_TIMEOUT));
                props.setProperty("socketTimeout", "30");

                Connection conn = DriverManager.getConnection(URL, props);

                if (conn != null && !conn.isClosed()) {
                    System.out.println("✓ Database connection established successfully");
                    return conn;
                }

            } catch (ClassNotFoundException e) {
                throw new SQLException("PostgreSQL JDBC driver not found. Please add postgresql dependency to your project.", e);
            } catch (SQLException e) {
                lastException = e;
                attempts++;

                if (attempts < MAX_RETRIES) {
                    System.err.println("Connection attempt " + attempts + " failed. Retrying...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new SQLException("Connection interrupted", ie);
                    }
                }
            }
        }

        throw new SQLException("Failed to connect to database after " + MAX_RETRIES + " attempts. " +
                "Please check: 1) PostgreSQL is running, 2) Database 'hilton_hotel' exists, " +
                "3) Username/password are correct", lastException);
    }
}

public static void createTables() {
    System.out.println("Creating database tables...");

    String[] createTableQueries = {
            // Hotel table - must be created first (referenced by other tables)
            """
            CREATE TABLE IF NOT EXISTS Hotel (
                hotel_id SERIAL PRIMARY KEY,
                name VARCHAR(255) NOT NULL UNIQUE,
                location TEXT NOT NULL,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
            """,

            // Guests table - references Hotel
            """
            CREATE TABLE IF NOT EXISTS Guests (
                guest_id SERIAL PRIMARY KEY,
                name VARCHAR(255) NOT NULL,
                email VARCHAR(255) UNIQUE NOT NULL,
                phone VARCHAR(20) NOT NULL,
                hotel_id INTEGER NOT NULL,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (hotel_id) REFERENCES Hotel(hotel_id) ON DELETE CASCADE,
                CONSTRAINT valid_email CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$'),
                CONSTRAINT valid_phone CHECK (phone ~ '^[+]?[0-9\\s\\-\\(\\)]{10,20}$')
            )
            """,

            // Room table - references Hotel
            """
            CREATE TABLE IF NOT EXISTS Room (
                room_number INTEGER NOT NULL,
                type VARCHAR(50) NOT NULL,
                available BOOLEAN DEFAULT TRUE,
                price_per_night DECIMAL(10,2) DEFAULT 0.00,
                hotel_id INTEGER NOT NULL,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                PRIMARY KEY (room_number, hotel_id),
                FOREIGN KEY (hotel_id) REFERENCES Hotel(hotel_id) ON DELETE CASCADE,
                CONSTRAINT valid_room_type CHECK (type IN ('single', 'double', 'suite', 'deluxe', 'presidential')),
                CONSTRAINT valid_price CHECK (price_per_night >= 0)
            )
            """,

            // Reservation table - references Guests, Room, and Hotel
            """
            CREATE TABLE IF NOT EXISTS Reservation (
                reservation_id SERIAL PRIMARY KEY,
                guest_id INTEGER NOT NULL,
                room_number INTEGER NOT NULL,
                hotel_id INTEGER NOT NULL,
                checkInDate DATE NOT NULL,
                checkOutDate DATE NOT NULL,
                reservationDate DATE NOT NULL DEFAULT CURRENT_DATE,
                status VARCHAR(50) DEFAULT 'confirmed',
                total_amount DECIMAL(10,2) DEFAULT 0.00,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (guest_id) REFERENCES Guests(guest_id) ON DELETE CASCADE,
                FOREIGN KEY (room_number, hotel_id) REFERENCES Room(room_number, hotel_id) ON DELETE CASCADE,
                FOREIGN KEY (hotel_id) REFERENCES Hotel(hotel_id) ON DELETE CASCADE,
                CONSTRAINT valid_dates CHECK (checkOutDate > checkInDate),
                CONSTRAINT valid_reservation_date CHECK (reservationDate <= checkInDate),
                CONSTRAINT valid_status CHECK (status IN ('confirmed', 'cancelled', 'completed', 'no-show')),
                CONSTRAINT valid_amount CHECK (total_amount >= 0)
            )
            """
    };

    // Create indexes for better performance
    String[] createIndexQueries = {
            "CREATE INDEX IF NOT EXISTS idx_guests_hotel ON Guests(hotel_id)",
            "CREATE INDEX IF NOT EXISTS idx_guests_email ON Guests(email)",
            "CREATE INDEX IF NOT EXISTS idx_room_hotel ON Room(hotel_id)",
            "CREATE INDEX IF NOT EXISTS idx_room_available ON Room(available)",
            "CREATE INDEX IF NOT EXISTS idx_reservation_guest ON Reservation(guest_id)",
            "CREATE INDEX IF NOT EXISTS idx_reservation_hotel ON Reservation(hotel_id)",
            "CREATE INDEX IF NOT EXISTS idx_reservation_dates ON Reservation(checkInDate, checkOutDate)",
            "CREATE INDEX IF NOT EXISTS idx_reservation_status ON Reservation(status)"
    };

    Connection conn = null;
    try {
        conn = getConnection();
        conn.setAutoCommit(false); // Start transaction

        // Create tables
        for (int i = 0; i < createTableQueries.length; i++) {
            try (PreparedStatement stmt = conn.prepareStatement(createTableQueries[i])) {
                stmt.execute();
                System.out.println("✓ Table " + (i + 1) + " created successfully");
            }
        }

        // Create indexes
        for (String indexQuery : createIndexQueries) {
            try (PreparedStatement stmt = conn.prepareStatement(indexQuery)) {
                stmt.execute();
            }
        }

        conn.commit(); // Commit transaction
        System.out.println("✓ All database tables and indexes created successfully!");

    } catch (SQLException e) {
        System.err.println("Error creating tables: " + e.getMessage());
        if (conn != null) {
            try {
                conn.rollback(); // Rollback on error
                System.out.println("Transaction rolled back");
            } catch (SQLException rollbackEx) {
                System.err.println("Error during rollback: " + rollbackEx.getMessage());
            }
        }

        // Print detailed error information
        printConnectionTroubleshooting();

    } finally {
        if (conn != null) {
            try {
                conn.setAutoCommit(true); // Reset auto-commit
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}

private static Connection getConnection() {
    try {
        return databaseConnection.getConnection();
    } catch (SQLException e) {
        System.err.println("Failed to establish database connection: " + e.getMessage());
        printConnectionTroubleshooting();
        return null;
    }
}

public static boolean testConnection() {
    System.out.println("Testing database connection...");

    try (Connection conn = getConnection()) {
        if (conn != null && !conn.isClosed()) {
            DatabaseMetaData metaData = conn.getMetaData();
            System.out.println("✓ Connected to: " + metaData.getDatabaseProductName());
            System.out.println("✓ Version: " + metaData.getDatabaseProductVersion());
            System.out.println("✓ Database URL: " + metaData.getURL());
            System.out.println("✓ Connection test successful!");
            return true;
        }
    } catch (SQLException e) {
        System.err.println("Database connection test failed!");
        System.err.println("Error: " + e.getMessage());
        printConnectionTroubleshooting();
        return false;
    }

    return false;
}

private static void printConnectionTroubleshooting() {
    System.err.println("\n🔧 TROUBLESHOOTING TIPS:");
    System.err.println("1. Make sure PostgreSQL server is running");
    System.err.println("2. Verify database 'hilton_hotel' exists:");
    System.err.println("   psql -U postgres -c \"CREATE DATABASE hilton_hotel;\"");
    System.err.println("3. Check username and password are correct");
    System.err.println("4. Ensure PostgreSQL is accepting connections on localhost:5432");
    System.err.println("5. Add PostgreSQL JDBC dependency to your project:");
    System.err.println("   <dependency>");
    System.err.println("     <groupId>org.postgresql</groupId>");
    System.err.println("     <artifactId>postgresql</artifactId>");
    System.err.println("     <version>42.6.0</version>");
    System.err.println("   </dependency>");
}

public static void closeConnection(Connection conn) {
    if (conn != null) {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}

public static void main(String[] args) {
    System.out.println("=== Hilton Hotel Database Setup ===\n");

    // Test connection
    if (testConnection()) {
        // Create tables if connection is successful
        createTables();
    } else {
        System.err.println("Cannot proceed with table creation due to connection issues.");
        System.exit(1);
    }

    System.out.println("\n=== Database setup complete! ===");
}

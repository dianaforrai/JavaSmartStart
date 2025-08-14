import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/hilton_hotel";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "Hugo2002.";
    private static final int CONNECTION_TIMEOUT = 30;
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

        throw new SQLException("Failed to connect to database after " + MAX_RETRIES + " attempts.", lastException);
    }

    public static void createTables() {
        System.out.println("Creating database tables...");

        String[] createTableQueries = {
                """
            CREATE TABLE IF NOT EXISTS Hotel (
                hotel_id SERIAL PRIMARY KEY,
                name VARCHAR(255) NOT NULL UNIQUE,
                location TEXT NOT NULL,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
            """,
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

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);

            for (String query : createTableQueries) {
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.execute();
                }
            }

            conn.commit();
            System.out.println("✓ All database tables created successfully!");
        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());
        }
    }

    public static boolean testConnection() {
        System.out.println("Testing database connection...");
        try (Connection conn = getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("✓ Connection test successful!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Database connection test failed: " + e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("=== Hilton Hotel Database Setup ===");

        if (testConnection()) {
            createTables();
        } else {
            System.err.println("Cannot proceed with table creation due to connection issues.");
        }

        System.out.println("=== Database setup complete! ===");
    }
}
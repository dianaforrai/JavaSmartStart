DROP TABLE IF EXISTS Reservation CASCADE;
DROP TABLE IF EXISTS Room CASCADE;
DROP TABLE IF EXISTS Guest CASCADE;
DROP TABLE IF EXISTS Hotel CASCADE;

CREATE TABLE Hotel (
    hotel_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location TEXT NOT NULL
);

CREATE TABLE Guest (
    guest_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    hotel_id INTEGER NOT NULL,
    FOREIGN KEY (hotel_id) REFERENCES Hotel(hotel_id) ON DELETE CASCADE
);

CREATE TABLE Room (
    room_number SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL, -- e.g., 'single', 'double', 'suite'
    available BOOLEAN NOT NULL DEFAULT TRUE,
    hotel_id INTEGER NOT NULL,
    FOREIGN KEY (hotel_id) REFERENCES Hotel(hotel_id) ON DELETE CASCADE
);

CREATE TABLE Reservation (
    reservation_id SERIAL PRIMARY KEY,
    guest_id INTEGER NOT NULL,
    hotel_id INTEGER NOT NULL,
    checkInDate DATE NOT NULL,
    checkOutDate DATE NOT NULL,
    reservationDate DATE NOT NULL DEFAULT CURRENT_DATE,
    status VARCHAR(50) NOT NULL DEFAULT 'confirmed', -- e.g., 'confirmed', 'cancelled', 'completed'
    FOREIGN KEY (guest_id) REFERENCES Guest(guest_id) ON DELETE CASCADE,
    FOREIGN KEY (hotel_id) REFERENCES Hotel(hotel_id) ON DELETE CASCADE,
    CONSTRAINT valid_dates CHECK (checkOutDate > checkInDate)
);

CREATE INDEX idx_guest_email ON Guest(email);
CREATE INDEX idx_guest_hotel ON Guest(hotel_id);
CREATE INDEX idx_room_hotel ON Room(hotel_id);
CREATE INDEX idx_room_available ON Room(available);
CREATE INDEX idx_reservation_guest ON Reservation(guest_id);
CREATE INDEX idx_reservation_hotel ON Reservation(hotel_id);
CREATE INDEX idx_reservation_dates ON Reservation(checkInDate, checkOutDate);
CREATE INDEX idx_reservation_status ON Reservation(status);

INSERT INTO Hotel (name, location) VALUES 
('Grand Palace Hotel', 'New York, NY, USA'),
('Seaside Resort', 'Miami, FL, USA'),
('Mountain View Lodge', 'Denver, CO, USA');

INSERT INTO Guest (name, email, phone, hotel_id) VALUES 
('John Smith', 'john.smith@email.com', '+1-555-0101', 1),
('Jane Doe', 'jane.doe@email.com', '+1-555-0102', 1),
('Bob Johnson', 'bob.johnson@email.com', '+1-555-0103', 2),
('Alice Williams', 'alice.williams@email.com', '+1-555-0104', 3);

INSERT INTO Room (type, available, hotel_id) VALUES 
('single', TRUE, 1),
('double', TRUE, 1),
('suite', FALSE, 1),
('single', TRUE, 2),
('double', TRUE, 2),
('suite', TRUE, 3);

INSERT INTO Reservation (guest_id, hotel_id, checkInDate, checkOutDate, status) VALUES 
(1, 1, '2024-09-15', '2024-09-20', 'confirmed'),
(2, 1, '2024-09-18', '2024-09-22', 'confirmed'),
(3, 2, '2024-09-10', '2024-09-15', 'completed'),
(4, 3, '2024-09-25', '2024-09-30', 'confirmed');

SELECT table_name 
FROM information_schema.tables 
WHERE table_schema = 'public';

SELECT g.guest_id, g.name, g.email, g.phone, h.name as hotel_name, h.location
FROM Guest g
JOIN Hotel h ON g.hotel_id = h.hotel_id;

SELECT r.reservation_id, g.name as guest_name, h.name as hotel_name, 
       r.checkInDate, r.checkOutDate, r.reservationDate, r.status
FROM Reservation r
JOIN Guest g ON r.guest_id = g.guest_id
JOIN Hotel h ON r.hotel_id = h.hotel_id
ORDER BY r.reservationDate DESC;

SELECT h.name as hotel_name, r.room_number, r.type, r.available
FROM Room r
JOIN Hotel h ON r.hotel_id = h.hotel_id
WHERE r.available = TRUE
ORDER BY h.name, r.room_number;
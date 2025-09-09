package com.globallogic.booking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @NotNull
    private Long flightId;

    @NotNull
    private Long passengerId;

    @NotNull
    private LocalDate bookingDate;

    @NotNull
    @Positive
    private BigDecimal totalPrice;

    // Constructors
    public Booking() {}

    public Booking(Long flightId, Long passengerId, LocalDate bookingDate, BigDecimal totalPrice) {
        this.flightId = flightId;
        this.passengerId = passengerId;
        this.bookingDate = bookingDate;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }

    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }

    public Long getPassengerId() { return passengerId; }
    public void setPassengerId(Long passengerId) { this.passengerId = passengerId; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }

    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
}
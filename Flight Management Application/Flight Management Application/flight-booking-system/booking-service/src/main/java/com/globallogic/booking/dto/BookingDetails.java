package com.globallogic.booking.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookingDetails {
    private Long bookingId;
    private Flight flight;
    private Passenger passenger;
    private LocalDate bookingDate;
    private BigDecimal totalPrice;

    // Constructors
    public BookingDetails() {}

    public BookingDetails(Long bookingId, Flight flight, Passenger passenger,
                          LocalDate bookingDate, BigDecimal totalPrice) {
        this.bookingId = bookingId;
        this.flight = flight;
        this.passenger = passenger;
        this.bookingDate = bookingDate;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }

    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }

    public Passenger getPassenger() { return passenger; }
    public void setPassenger(Passenger passenger) { this.passenger = passenger; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }

    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
}
package com.globallogic.booking.controller;

import com.globallogic.booking.dto.BookingDetails;
import com.globallogic.booking.entity.Booking;
import com.globallogic.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDetails> getBookingDetails(@PathVariable Long bookingId) {
        BookingDetails bookingDetails = bookingService.getBookingDetails(bookingId);
        if (bookingDetails != null) {
            return ResponseEntity.ok(bookingDetails);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/passengerId/{passengerId}")
    public ResponseEntity<BookingDetails> getBookingDetailsByPassengerId(@PathVariable Long passengerId) {
        // This endpoint fetches booking details for a specific passenger
        // For simplicity, returning the first booking found
        List<Booking> bookings = bookingService.getAllBookings();
        for (Booking booking : bookings) {
            if (booking.getPassengerId().equals(passengerId)) {
                BookingDetails details = bookingService.getBookingDetails(booking.getBookingId());
                return ResponseEntity.ok(details);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Booking createBooking(@Valid @RequestBody Booking booking) {
        return bookingService.saveBooking(booking);
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long bookingId,
                                                 @Valid @RequestBody Booking booking) {
        return bookingService.getBookingById(bookingId)
                .map(existingBooking -> {
                    booking.setBookingId(bookingId);
                    return ResponseEntity.ok(bookingService.saveBooking(booking));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {
        if (bookingService.getBookingById(bookingId).isPresent()) {
            bookingService.deleteBooking(bookingId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
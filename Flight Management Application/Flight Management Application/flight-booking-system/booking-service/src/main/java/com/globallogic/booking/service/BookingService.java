package com.globallogic.booking.service;

import com.globallogic.booking.client.FlightServiceClient;
import com.globallogic.booking.client.PassengerServiceClient;
import com.globallogic.booking.dto.BookingDetails;
import com.globallogic.booking.dto.Flight;
import com.globallogic.booking.dto.Passenger;
import com.globallogic.booking.entity.Booking;
import com.globallogic.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightServiceClient flightServiceClient;

    @Autowired
    private PassengerServiceClient passengerServiceClient;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId);
    }

    public BookingDetails getBookingDetails(Long bookingId) {
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            Flight flight = flightServiceClient.getFlightById(booking.getFlightId());
            Passenger passenger = passengerServiceClient.getPassengerById(booking.getPassengerId());

            return new BookingDetails(
                    booking.getBookingId(),
                    flight,
                    passenger,
                    booking.getBookingDate(),
                    booking.getTotalPrice()
            );
        }
        return null;
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}
package com.hilton.hotel.model;

import java.util.Date;

public class Reservation {
    private int reservationId;
    private int guestId;
    private int hotelId;
    private Date checkInDate;
    private Date checkOutDate;
    private Date reservationDate;
    private String status;

    // Constructors
    public Reservation() {}

    public Reservation(int guestId, int hotelId, Date checkInDate, Date checkOutDate, Date reservationDate, String status) {
        this.guestId = guestId;
        this.hotelId = hotelId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.reservationDate = reservationDate;
        this.status = status;
    }

    public Reservation(int reservationId, int guestId, int hotelId, Date checkInDate, Date checkOutDate, Date reservationDate, String status) {
        this.reservationId = reservationId;
        this.guestId = guestId;
        this.hotelId = hotelId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.reservationDate = reservationDate;
        this.status = status;
    }

    // Getters and Setters
    public int getReservationId() { return reservationId; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }

    public int getGuestId() { return guestId; }
    public void setGuestId(int guestId) { this.guestId = guestId; }

    public int getHotelId() { return hotelId; }
    public void setHotelId(int hotelId) { this.hotelId = hotelId; }

    public Date getCheckInDate() { return checkInDate; }
    public void setCheckInDate(Date checkInDate) { this.checkInDate = checkInDate; }

    public Date getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(Date checkOutDate) { this.checkOutDate = checkOutDate; }

    public Date getReservationDate() { return reservationDate; }
    public void setReservationDate(Date reservationDate) { this.reservationDate = reservationDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", guestId=" + guestId +
                ", hotelId=" + hotelId +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", reservationDate=" + reservationDate +
                ", status='" + status + '\'' +
                '}';
    }
}
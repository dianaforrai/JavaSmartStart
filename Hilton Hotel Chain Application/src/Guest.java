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
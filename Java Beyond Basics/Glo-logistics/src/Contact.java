class Contact {
    private String email;
    private String phone;
    private String name;

    public Contact(String email, String phone, String name) {
        this.email = email;
        this.phone = phone;
        this.name = name;
    }

    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getName() { return name; }
}
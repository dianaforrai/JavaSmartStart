public class Customer {
    private int custId;
    private String customerName;
    private String contactNumber;
    private String address;

    public Customer(int custId, String customerName, String contactNumber, String address) {
        this.custId = custId;
        this.customerName = customerName;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    public int getCustId() { return custId; }
    public String getCustomerName() { return customerName; }
    public String getContactNumber() { return contactNumber; }
    public String getAddress() { return address; }
}

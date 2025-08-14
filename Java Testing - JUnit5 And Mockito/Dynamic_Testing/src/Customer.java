public class Customer {
    private final String customerName;

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    public CharSequence getName() {
        return "Default Name";
    }
}

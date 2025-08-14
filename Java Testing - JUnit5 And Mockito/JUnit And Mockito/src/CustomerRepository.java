public interface CustomerRepository {
    void addCustomer(Customer customer);
    Customer getCustomerById(int custId);
    void updateCustomer(Customer customer);
    void deleteCustomer(int custId);
}

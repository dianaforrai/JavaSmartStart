public class CustomerRepository {
}
public interface CustomerRepository {
    void addCustomer(Customer customer);
    Customer getCustomerById(int id);
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);
}

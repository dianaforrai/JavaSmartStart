public class CustomerServiceImpl {
}
public class CustomerServiceImpl {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addCustomer(Customer customer) {
        if (customer.getCustomerName() == null || customer.getCustomerName().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }
        customerRepository.addCustomer(customer);
    }

    public void updateCustomer(Customer customer) {
        if (customer.getCustomerName() == null || customer.getCustomerName().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }
        customerRepository.updateCustomer(customer);
    }

    public void deleteCustomer(int id) {
        Customer existing = customerRepository.getCustomerById(id);
        if (existing == null) {
            throw new IllegalStateException("Customer not found for deletion");
        }
        customerRepository.deleteCustomer(id);
    }
}

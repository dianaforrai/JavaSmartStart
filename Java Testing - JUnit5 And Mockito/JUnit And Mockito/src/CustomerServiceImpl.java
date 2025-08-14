public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addCustomer(Customer customer) {
        customerRepository.addCustomer(customer);
    }

    public Customer getCustomerById(int custId) {
        return customerRepository.getCustomerById(custId);
    }

    public void updateCustomer(Customer customer) {
        customerRepository.updateCustomer(customer);
    }

    public void deleteCustomer(int custId) {
        customerRepository.deleteCustomer(custId);
    }
}

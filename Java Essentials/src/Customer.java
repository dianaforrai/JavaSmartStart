import com.gl.app.model.CustomerRepository;
import com.gl.app.model.CustomerService;

public class Customer {
    private int custId;
    private String customerName;
    private String contactNumber;
    private String address;

    // Constructor
    public Customer(int custId, String customerName, String contactNumber, String address) {
        this.custId = custId;
        this.customerName = customerName;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    // Getters
    public int getCustId() { return custId; }
    public String getCustomerName() { return customerName; }
    public String getContactNumber() { return contactNumber; }
    public String getAddress() { return address; }
}

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
}


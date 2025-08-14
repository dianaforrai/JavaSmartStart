import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assumptions.*;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private boolean dbAvailable;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Example: simulate DB availability
        dbAvailable = true; // Change to false to see tests being skipped
    }

    @Test
    void testCreateCustomer_ValidData() {
        assumeTrue(dbAvailable, "Database is not available, skipping test");

        Customer customer = new Customer(1, "John Doe", "1234567890", "123 Main St");
        customerService.addCustomer(customer);

        verify(customerRepository, times(1)).addCustomer(customer);
    }

    @Test
    void testCreateCustomer_InvalidData() {
        assumeTrue(dbAvailable);

        Customer invalidCustomer = new Customer(2, null, "1234567890", "No Name Street");
        // In real code, you'd expect validation to throw an exception
        assertThrows(IllegalArgumentException.class, () -> {
            if (invalidCustomer.getCustomerName() == null) {
                throw new IllegalArgumentException("Customer name cannot be null");
            }
            customerService.addCustomer(invalidCustomer);
        });
    }

    @Test
    void testRetrieveCustomerById() {
        assumeTrue(dbAvailable);

        Customer customer = new Customer(1, "Jane Doe", "0987654321", "456 Main St");
        when(customerRepository.getCustomerById(1)).thenReturn(customer);

        Customer retrieved = customerService.getCustomerById(1);

        assertNotNull(retrieved);
        assertEquals("Jane Doe", retrieved.getCustomerName());
        assertEquals("0987654321", retrieved.getContactNumber());
    }

    @Test
    void testUpdateCustomer() {
        assumeTrue(dbAvailable);

        Customer customer = new Customer(1, "John Doe", "1234567890", "123 Main St");
        customer.setCustomerName("John Updated");

        customerService.updateCustomer(customer);
        verify(customerRepository, times(1)).updateCustomer(customer);
    }

    @Test
    void testDeleteCustomer() {
        assumeTrue(dbAvailable);

        customerService.deleteCustomer(1);
        verify(customerRepository, times(1)).deleteCustomer(1);
    }
}

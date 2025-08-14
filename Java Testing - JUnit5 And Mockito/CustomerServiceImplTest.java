import com.gl.app.model.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCustomer() {
        Customer customer = new Customer(1, "John Doe", "1234567890", "123 Main St");

        // Call method
        customerService.addCustomer(customer);

        // Verify repository interaction
        verify(customerRepository, times(1)).addCustomer(customer);
    }

    @Test
    void testGetCustomerById() {
        Customer customer = new Customer(1, "John Doe", "1234567890", "123 Main St");

        // Mock behavior
        when(customerRepository.getCustomerById(1)).thenReturn(customer);

        // Call method
        Customer retrieved = customerService.getCustomerById(1);

        // Assertions
        assertNotNull(retrieved);
        assertEquals(1, retrieved.getCustId());
        assertEquals("John Doe", retrieved.getCustomerName());
        assertEquals("1234567890", retrieved.getContactNumber());
        assertEquals("123 Main St", retrieved.getAddress());
    }
}

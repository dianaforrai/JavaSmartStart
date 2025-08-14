import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository; // Mocking the database dependency

    @InjectMocks
    private CustomerService customerService; // Class under test

    private Customer testCustomer;

    @BeforeEach
    void setup() {
        testCustomer = new Customer(1L, "John Doe", "john@example.com");
    }

    // a) Add New Customer
    @Test
    void testAddNewCustomer() {
        when(customerRepository.save(testCustomer)).thenReturn(testCustomer);

        Customer addedCustomer = customerService.addCustomer(testCustomer);

        assertNotNull(addedCustomer);
        assertEquals("John Doe", addedCustomer.getName());
        verify(customerRepository, times(1)).save(testCustomer); // Verify save was called
    }

    // b) Check Existing Customer
    @Test
    void testGetCustomerById() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));

        Customer foundCustomer = customerService.getCustomerById(1L);

        assertNotNull(foundCustomer);
        assertEquals(testCustomer.getName(), foundCustomer.getName());
        verify(customerRepository, times(1)).findById(1L); // Verify method call
    }

    // c) Error Handling - Database Failure
    @Test
    void testGetCustomerByIdDatabaseFailure() {
        when(customerRepository.findById(1L)).thenThrow(new RuntimeException("DB failure"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            customerService.getCustomerById(1L);
        });

        assertEquals("DB failure", exception.getMessage());
        verify(customerRepository, times(1)).findById(1L); // Ensure method was called
    }
}

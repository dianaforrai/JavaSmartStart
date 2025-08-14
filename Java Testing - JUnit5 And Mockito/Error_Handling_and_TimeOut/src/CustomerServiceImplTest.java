import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    @Test
    void testAddCustomerWithInvalidName_UsingTryCatch() {
        CustomerRepository repository = mock(CustomerRepository.class);
        CustomerServiceImpl service = new CustomerServiceImpl(repository);
        Customer invalidCustomer = new Customer(1, "", "1234567890", "Some Address");

        try {
            service.addCustomer(invalidCustomer);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Customer name cannot be empty", e.getMessage());
        }
    }

    @Test
    void testUpdateCustomerWithEmptyName_UsingAssertThrows() {
        CustomerRepository repository = mock(CustomerRepository.class);
        CustomerServiceImpl service = new CustomerServiceImpl(repository);
        Customer invalidCustomer = new Customer(2, "", "9876543210", "Another Address");

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> service.updateCustomer(invalidCustomer)
        );
        assertEquals("Customer name cannot be empty", ex.getMessage());
    }

    @Test
    void testDeleteNonExistingCustomer() {
        CustomerRepository repository = mock(CustomerRepository.class);
        when(repository.getCustomerById(999)).thenReturn(null);

        CustomerServiceImpl service = new CustomerServiceImpl(repository);

        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> service.deleteCustomer(999)
        );
        assertEquals("Customer not found for deletion", ex.getMessage());
    }

    @Test
    @Timeout(2) // Ensure method runs under 2 seconds
    void testPerformance_DeleteCustomer() {
        CustomerRepository repository = mock(CustomerRepository.class);
        when(repository.getCustomerById(1))
                .thenReturn(new Customer(1, "John Doe", "12345", "Main Street"));

        CustomerServiceImpl service = new CustomerServiceImpl(repository);

        // This should be quick and not exceed timeout
        service.deleteCustomer(1);

        verify(repository, times(1)).deleteCustomer(1);
    }
}

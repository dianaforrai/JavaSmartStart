import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @ParameterizedTest
    @ValueSource(strings = {"Alice", "Bob", "Charlie"})
    void testValidCustomerNames(String name) {
        Customer customer = new Customer();
        customer.setCustomerName(name);
        assertNotNull(customer.getCustomerName());
        assertTrue(customer.getCustomerName().length() > 0);
    }
}

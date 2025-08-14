import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    static Stream<Customer> customerProvider() {
        return Stream.of(
                new Customer(1, "Alice", "1234567890", "101 Main St"),
                new Customer(2, "Bob", "9876543210", "202 Elm St"),
                new Customer(3, "Charlie", "5555555555", "303 Oak St")
        );
    }

    @ParameterizedTest
    @MethodSource("customerProvider")
    void testCustomerObjects(Customer customer) {
        assertNotNull(customer.getCustomerName());
        assertNotNull(customer.getContactNumber());
        assertNotNull(customer.getAddress());
    }
}

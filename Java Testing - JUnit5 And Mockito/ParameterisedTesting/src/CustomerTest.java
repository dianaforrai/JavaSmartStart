import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @ParameterizedTest
    @CsvSource({
            "1, Alice, 1234567890, 101 Main St",
            "2, Bob, 9876543210, 202 Elm St",
            "3, Charlie, 5555555555, 303 Oak St"
    })
    void testCreateCustomer(int id, String name, String contact, String address) {
        Customer customer = new Customer();
        customer.setCustId(id);
        customer.setCustomerName(name);
        customer.setContactNumber(contact);
        customer.setAddress(address);

        assertEquals(id, customer.getCustId());
        assertEquals(name, customer.getCustomerName());
        assertEquals(contact, customer.getContactNumber());
        assertEquals(address, customer.getAddress());
    }
}

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Collectors;

class CustomerTest {

    // Example dynamic test method using @ParameterizedTest
    @ParameterizedTest
    @ValueSource(strings = {"Alice", "Bob", "Charlie"})
    void dynamicTestCustomerNameIsNotEmpty(String customerName) {
        Customer customer = new Customer(customerName);
        dynamicTestForCustomer(customer);
    }

    // Helper method to define the dynamic test logic
    void dynamicTestForCustomer(Customer customer) {
        assertFalse(customer.getName().isEmpty(), "Customer name should not be empty");
    }

    // Optional: Using TestFactory to generate dynamic tests programmatically
    @TestFactory
    List<DynamicTest> generateDynamicTestsForCustomers() {
        List<Customer> customers = List.of(
                new Customer("Alice"),
                new Customer("Bob"),
                new Customer("Charlie")
        );

        return customers.stream()
                .map(customer -> DynamicTest.dynamicTest(
                        "Checking customer name: " + customer.getName(),
                        () -> dynamicTestForCustomer(customer)
                ))
                .collect(Collectors.toList());
    }
}

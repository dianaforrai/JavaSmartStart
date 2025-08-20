import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class LogisticsApplication {
    private List<Shipment> shipments;
    private List<Warehouse> warehouses;
    private List<Carrier> carriers;
    private List<Route> routes;
    private List<Vehicle> fleet;
    private List<Customer> customers;
    private Map<String, User> users;
    private List<Order> orders;

    public LogisticsApplication() {
        this.shipments = new ArrayList<>();
        this.warehouses = new ArrayList<>();
        this.carriers = new ArrayList<>();
        this.routes = new ArrayList<>();
        this.fleet = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.users = new HashMap<>();
        this.orders = new ArrayList<>();
        initializeTestData();
    }

    // User Story US01: Create shipment with tracking
    public String createShipment(String origin, String destination,
                                 double weight, String shippingMethod) {
        String trackingNumber = generateTrackingNumber();

        // Validate input data
        if (origin == null || destination == null || weight <= 0) {
            throw new IllegalArgumentException("Invalid shipment data");
        }

        Shipment shipment = new Shipment();
        shipment.setTrackingNumber(trackingNumber);
        shipment.setSender(new Address(origin, "", "", ""));
        shipment.setReceiver(new Address(destination, "", "", ""));
        shipment.setWeight(weight);
        shipment.setStatus(new Status(100, "Created"));
        shipment.setCarrier(findOptimalCarrier(weight, origin, destination));
        shipment.setWarehouse(allocateWarehouse(origin));

        shipments.add(shipment);
        logAction("Shipment created: " + trackingNumber);

        return trackingNumber;
    }

    // User Story US02: Track shipment details
    public ShipmentDetails getShipmentDetails(String trackingNumber, String customerType) {
        Shipment shipment = findShipmentByTracking(trackingNumber);
        if (shipment == null) {
            return null;
        }

        ShipmentDetails details = new ShipmentDetails();
        details.setTrackingNumber(trackingNumber);
        details.setStatus(shipment.getStatus().getDescription());
        details.setEstimatedDelivery(calculateEstimatedDelivery(shipment));
        details.setCurrentLocation(shipment.getCurrentLocation());

        return details;
    }

    // User Story US03: Auto-generate tracking number
    private String generateTrackingNumber() {
        String prefix = "GLO";
        long timestamp = System.currentTimeMillis();
        int random = new Random().nextInt(1000);
        String trackingNumber = prefix + timestamp + String.format("%03d", random);

        // Ensure uniqueness
        while (isTrackingNumberUsed(trackingNumber)) {
            random = new Random().nextInt(1000);
            trackingNumber = prefix + timestamp + String.format("%03d", random);
        }

        return trackingNumber;
    }

    // User Story US04: Calculate total shipment weight
    public double calculateTotalWeight(List<Item> items) {
        return items.stream()
                .mapToDouble(Item::getWeight)
                .sum();
    }

    // User Story US05: Generate shipping labels
    public String generateShippingLabel(String orderId) {
        Order order = findOrderById(orderId);
        if (order == null) {
            return "Order not found";
        }

        StringBuilder label = new StringBuilder();
        label.append("=== GLOLogistics Shipping Label ===\n");
        label.append("Order ID: ").append(order.getOrderNumber()).append("\n");
        label.append("From: ").append(order.getCustomer().getName()).append("\n");
        label.append("Weight: ").append(calculateTotalWeight(order.getItems())).append(" kg\n");
        label.append("Status: ").append(order.getStatus()).append("\n");
        label.append("Generated: ").append(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)).append("\n");
        label.append("============================\n");

        return label.toString();
    }

    // User Story US06: Manage shipment orders (CRUD operations)
    public String createShipmentOrder(String customerId, String source,
                                      String destination, List<Item> items) {
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found");
        }

        Order order = new Order();
        order.setOrderNumber("ORD" + System.currentTimeMillis());
        order.setCustomer(customer);
        order.setItems(items);
        order.setStatus("Created");

        orders.add(order);
        logAction("Order created: " + order.getOrderNumber());

        return order.getOrderNumber();
    }

    public Order viewShipmentOrder(String orderId) {
        return findOrderById(orderId);
    }

    public boolean updateShipmentOrder(String orderId, String newStatus) {
        Order order = findOrderById(orderId);
        if (order != null) {
            // Create new record for immutability as per US06 requirements
            Order updatedOrder = new Order();
            updatedOrder.setOrderNumber(order.getOrderNumber());
            updatedOrder.setCustomer(order.getCustomer());
            updatedOrder.setItems(order.getItems());
            updatedOrder.setStatus(newStatus);

            orders.add(updatedOrder);
            logAction("Order updated: " + orderId + " -> " + newStatus);
            return true;
        }
        return false;
    }

    // User Story US07: Sealed Classes for shipment item categories
    public abstract class ShipmentItem {
        protected String name;
        protected String description;
        protected int quantity;

        public abstract double calculateShippingCost();
        public abstract boolean isHazardous();
    }

    public class FragileItem extends ShipmentItem {
        @Override
        public double calculateShippingCost() {
            return quantity * 15.0; // Higher cost for fragile items
        }

        @Override
        public boolean isHazardous() {
            return false;
        }
    }

    public class HazardousItem extends ShipmentItem {
        private String hazardClass;

        @Override
        public double calculateShippingCost() {
            return quantity * 25.0; // Highest cost for hazardous items
        }

        @Override
        public boolean isHazardous() {
            return true;
        }
    }

    public class StandardItem extends ShipmentItem {
        @Override
        public double calculateShippingCost() {
            return quantity * 10.0; // Standard shipping cost
        }

        @Override
        public boolean isHazardous() {
            return false;
        }
    }

    // User Story US08: Logging system
    private void logAction(String action) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println("[" + timestamp + "] INFO: " + action);
    }

    private void logWarning(String warning) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println("[" + timestamp + "] WARNING: " + warning);
    }

    private void logError(String error) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println("[" + timestamp + "] ERROR: " + error);
    }

    // User Story US09: User interface for shipment management
    public void createShipmentOrder(String senderDetails, String recipientDetails,
                                    String packageDimensions, double weight,
                                    String shippingService) {
        try {
            String trackingNumber = createShipment(senderDetails, recipientDetails, weight, shippingService);
            System.out.println("Shipment created successfully!");
            System.out.println("Tracking Number: " + trackingNumber);

            // Display existing shipment orders for the user
            displayUserShipments();

        } catch (Exception e) {
            logError("Failed to create shipment: " + e.getMessage());
        }
    }

    private void displayUserShipments() {
        System.out.println("\n=== Your Shipment Orders ===");
        for (Order order : orders) {
            System.out.println("Order: " + order.getOrderNumber() +
                    " | Status: " + order.getStatus() +
                    " | Customer: " + order.getCustomer().getName());
        }
    }

    // User Story US10: Route optimization with real-time updates
    public void assignOptimalRoute(Shipment shipment) {
        Route optimalRoute = calculateOptimalRoute(shipment.getSender().getCity(),
                shipment.getReceiver().getCity());
        shipment.setRoute(optimalRoute);

        // Real-time updates simulation
        new Thread(() -> {
            try {
                Thread.sleep(5000); // Simulate processing time
                updateRouteProgress(shipment);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    private void updateRouteProgress(Shipment shipment) {
        // Simulate real-time route updates
        Route route = shipment.getRoute();
        if (route != null) {
            LocalDateTime newETA = route.calculateETA();
            logAction("Route updated for shipment " + shipment.getTrackingNumber() +
                    ". New ETA: " + newETA);
        }
    }

    // User Story US11: Route calculation and proposal
    private Route calculateOptimalRoute(String origin, String destination) {
        Route route = new Route();
        route.setOrigin(new Location(origin, 0.0f, 0.0f));
        route.setDestination(new Location(destination, 0.0f, 0.0f));

        // Simulate route calculation based on distance, traffic, delivery windows
        double distance = calculateDistance(origin, destination);
        route.setDistance((float) distance);

        logAction("Optimal route calculated: " + origin + " -> " + destination +
                " (" + distance + " km)");

        return route;
    }

    // Helper methods
    private Shipment findShipmentByTracking(String trackingNumber) {
        return shipments.stream()
                .filter(s -> s.getTrackingNumber().equals(trackingNumber))
                .findFirst()
                .orElse(null);
    }

    private boolean isTrackingNumberUsed(String trackingNumber) {
        return shipments.stream()
                .anyMatch(s -> s.getTrackingNumber().equals(trackingNumber));
    }

    private Carrier findOptimalCarrier(double weight, String origin, String destination) {
        // Simple carrier selection logic
        return carriers.isEmpty() ? createDefaultCarrier() : carriers.get(0);
    }

    private Warehouse allocateWarehouse(String location) {
        return warehouses.stream()
                .filter(w -> w.getLocation().getLatitude() != 0) // Simple filter
                .findFirst()
                .orElse(createDefaultWarehouse());
    }

    private LocalDateTime calculateEstimatedDelivery(Shipment shipment) {
        return LocalDateTime.now().plusDays(3); // Simple 3-day delivery estimate
    }

    private double calculateDistance(String origin, String destination) {
        // Simplified distance calculation
        return Math.random() * 1000 + 100; // Random distance between 100-1100 km
    }

    private Customer findCustomerById(String customerId) {
        return customers.stream()
                .filter(c -> c.getName().equals(customerId))
                .findFirst()
                .orElse(null);
    }

    private Order findOrderById(String orderId) {
        return orders.stream()
                .filter(o -> o.getOrderNumber().equals(orderId))
                .findFirst()
                .orElse(null);
    }

    private Carrier createDefaultCarrier() {
        Carrier carrier = new Carrier();
        carrier.setName("GLO Express");
        carrier.setContactInfo(new Contact("support@gloexpress.com", "1-800-GLO-SHIP", "GLO Support"));
        return carrier;
    }

    private Warehouse createDefaultWarehouse() {
        Warehouse warehouse = new Warehouse();
        warehouse.setLocation(new Location("Default Hub", 40.7128f, -74.0060f));
        warehouse.setCapacity(10000);
        warehouse.setInventory(new ArrayList<Object>());
        return warehouse;
    }

    private void initializeTestData() {
        // Initialize with some test data
        Customer testCustomer = new Customer();
        testCustomer.setName("CUST001");
        testCustomer.setContactInfo(new Contact("test@example.com", "123-456-7890", "Test Customer"));
        customers.add(testCustomer);

        carriers.add(createDefaultCarrier());
        warehouses.add(createDefaultWarehouse());
    }

    // Main method for testing
    public static void main(String[] args) {
        LogisticsApplication app = new LogisticsApplication();

        // Test User Story US01: Create shipment
        String trackingNumber = app.createShipment("New York", "Los Angeles", 25.5, "Express");
        System.out.println("Created shipment with tracking: " + trackingNumber);

        // Test User Story US02: Track shipment
        ShipmentDetails details = app.getShipmentDetails(trackingNumber, "consumer");
        if (details != null) {
            System.out.println("Shipment Status: " + details.getStatus());
        }

        // Test User Story US04: Calculate weight
        List<Item> items = Arrays.asList(
                new Item("Laptop", "Electronics", 2.5),
                new Item("Books", "Literature", 1.2),
                new Item("Cables", "Electronics", 0.3)
        );
        double totalWeight = app.calculateTotalWeight(items);
        System.out.println("Total weight: " + totalWeight + " kg");

        // Test User Story US06: Create order
        String orderId = app.createShipmentOrder("CUST001", "Chicago", "Miami", items);
        System.out.println("Created order: " + orderId);

        // Test User Story US05: Generate label
        String label = app.generateShippingLabel(orderId);
        System.out.println(label);
    }
}
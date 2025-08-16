import java.util.*;


public class OnlineShoppingPlatform {

    public static void main(String[] args) {
        System.out.println("=== Online Shopping Platform Demo ===\n");


        Item laptop = new Item("Gaming Laptop", 899.99);
        Item mouse = new Item("Wireless Mouse", 29.99);
        Item keyboard = new Item("Mechanical Keyboard", 79.99);
        Item monitor = new Item("4K Monitor", 299.99);
        Item headphones = new Item("Noise Cancelling Headphones", 199.99);


        System.out.println("=== Demo 1: Standard Online Store ===");
        OnlineStore store = new OnlineStore("TechMart");


        store.addItemToCart(laptop);
        store.addItemToCart(mouse);
        store.addItemToCart(keyboard);


        System.out.println("\nTesting different discount types:");
        System.out.printf("Original total: $%.2f%n", store.calculateTotalPrice());
        System.out.printf("With 15%% discount: $%.2f%n", store.calculateTotalWithDiscount("percentage", 15.0));
        System.out.printf("With $50 fixed discount: $%.2f%n", store.calculateTotalWithDiscount("fixed", 50.0));
        System.out.printf("With tiered discount: $%.2f%n", store.calculateTotalWithDiscount("tiered", 0));
        System.out.printf("With standard discount: $%.2f%n", store.calculateTotalWithDiscount("standard", 0));


        store.checkout("percentage", 10.0);


        System.out.println("\n=== Demo 2: BOGO Offer ===");
        OnlineStore bogoStore = new OnlineStore("BOGO Electronics");
        bogoStore.addItemToCart(new Item("Phone Case", 19.99));
        bogoStore.addItemToCart(new Item("Screen Protector", 12.99));
        bogoStore.addItemToCart(new Item("Car Charger", 15.99));
        bogoStore.addItemToCart(new Item("Power Bank", 39.99));

        bogoStore.checkout("bogo", 0);

        System.out.println("\n=== Demo 3: Premium Store ===");
        PremiumStore premiumStore = new PremiumStore(true, "winter");

        premiumStore.addItemToCart(monitor);
        premiumStore.addItemToCart(headphones);
        premiumStore.addItemToCart(keyboard);

        premiumStore.premiumCheckout();

        System.out.println("\n=== Demo 4: Direct Static Method Usage ===");
        List<Item> sampleItems = Arrays.asList(
                new Item("Item A", 25.00),
                new Item("Item B", 15.00),
                new Item("Item C", 35.00),
                new Item("Item D", 20.00)
        );

        double total = sampleItems.stream().mapToDouble(Item::getPrice).sum();

        System.out.printf("Sample cart total: $%.2f%n", total);
        System.out.printf("20%% discount: $%.2f%n",
                DiscountCalculator.calculatePercentageDiscount(total, 20.0));
        System.out.printf("BOGO discount: $%.2f%n",
                DiscountCalculator.calculateBuyOneGetOneFree(sampleItems));
        System.out.printf("Tiered discount: $%.2f%n",
                DiscountCalculator.calculateTieredDiscount(total));
        System.out.printf("$15 fixed discount: $%.2f%n",
                DiscountCalculator.calculateFixedAmountDiscount(total, 15.0));

        System.out.println("\n=== Demo 5: Error Handling ===");
        try {
            DiscountCalculator.calculatePercentageDiscount(100.0, 150.0); // Invalid percentage
        } catch (IllegalArgumentException e) {
            System.out.println("Error caught: " + e.getMessage());
        }

        OnlineStore errorStore = new OnlineStore("Error Test Store");
        errorStore.addItemToCart(null); // Try to add null item
        errorStore.removeItemFromCart(new Item("Non-existent", 10.0)); // Try to remove non-existent item
    }
}
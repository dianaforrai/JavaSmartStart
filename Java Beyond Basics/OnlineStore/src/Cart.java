import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Cart {
    private List<Item> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return new ArrayList<>(items); // Return defensive copy
    }

    protected void setItems(List<Item> items) {
        this.items = items;
    }

    public int getItemCount() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public String toString() {
        return "Cart{items=" + items + "}";
    }
}
interface DiscountCalculator {

    static double calculatePercentageDiscount(double totalPrice, double discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100");
        }
        return totalPrice * (discountPercentage / 100.0);
    }

    static double calculateBuyOneGetOneFree(List<Item> items) {
        if (items.isEmpty()) return 0.0;

        List<Item> sortedItems = items.stream()
                .sorted(Comparator.comparingDouble(Item::getPrice))
                .collect(Collectors.toList());

        double discount = 0.0;
        for (int i = 1; i < sortedItems.size(); i += 2) {
            discount += sortedItems.get(i).getPrice();
        }

        return discount;
    }

    static double calculateFixedAmountDiscount(double totalPrice, double discountAmount) {
        return Math.min(discountAmount, totalPrice); // Don't exceed total price
    }

    static double calculateTieredDiscount(double totalPrice) {
        if (totalPrice >= 200) {
            return totalPrice * 0.15; // 15% for orders over $200
        } else if (totalPrice >= 100) {
            return totalPrice * 0.10; // 10% for orders over $100
        } else if (totalPrice >= 50) {
            return totalPrice * 0.05; // 5% for orders over $50
        }
        return 0.0;
    }

    default double calculateStandardDiscount(double totalPrice, List<Item> items) {
        if (totalPrice >= 75) {
            return calculatePercentageDiscount(totalPrice, 10.0);
        }
        return 0.0;
    }

    default double calculateLoyaltyDiscount(double totalPrice, boolean isLoyaltyMember) {
        if (isLoyaltyMember && totalPrice >= 25) {
            return calculatePercentageDiscount(totalPrice, 5.0); // 5% loyalty discount
        }
        return 0.0;
    }

    default double calculateSeasonalDiscount(double totalPrice, String season) {
        double discountRate = switch (season.toLowerCase()) {
            case "spring" -> 8.0;
            case "summer" -> 12.0;
            case "fall", "autumn" -> 10.0;
            case "winter" -> 15.0;
            default -> 0.0;
        };

        return totalPrice >= 60 ? calculatePercentageDiscount(totalPrice, discountRate) : 0.0;
    }
}

interface OrderProcessor extends DiscountCalculator {

    Cart getCart();

    default boolean addItemToCart(Item item) {
        if (item == null) {
            System.out.println("Cannot add null item to cart");
            return false;
        }

        Cart cart = getCart();
        cart.getItems().add(item);
        System.out.println("Added to cart: " + item);
        return true;
    }

    default boolean removeItemFromCart(Item item) {
        if (item == null) {
            System.out.println("Cannot remove null item from cart");
            return false;
        }

        Cart cart = getCart();
        boolean removed = cart.getItems().remove(item);

        if (removed) {
            System.out.println("Removed from cart: " + item);
        } else {
            System.out.println("Item not found in cart: " + item);
        }

        return removed;
    }

    default double calculateTotalPrice() {
        Cart cart = getCart();
        return cart.getItems().stream()
                .mapToDouble(Item::getPrice)
                .sum();
    }

    default double calculateTotalWithDiscount(String discountType, double discountValue) {
        double totalPrice = calculateTotalPrice();
        double discount = 0.0;

        switch (discountType.toLowerCase()) {
            case "percentage":
                discount = DiscountCalculator.calculatePercentageDiscount(totalPrice, discountValue);
                break;
            case "fixed":
                discount = DiscountCalculator.calculateFixedAmountDiscount(totalPrice, discountValue);
                break;
            case "bogo":
                discount = DiscountCalculator.calculateBuyOneGetOneFree(getCart().getItems());
                break;
            case "tiered":
                discount = DiscountCalculator.calculateTieredDiscount(totalPrice);
                break;
            case "standard":
                discount = calculateStandardDiscount(totalPrice, getCart().getItems());
                break;
            default:
                System.out.println("Unknown discount type: " + discountType);
        }

        return Math.max(0, totalPrice - discount);
    }

    default void displayCartSummary() {
        Cart cart = getCart();
        System.out.println("\n=== Cart Summary ===");

        if (cart.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        System.out.println("Items in cart:");
        cart.getItems().forEach(item -> System.out.println("  " + item));
        System.out.printf("Total items: %d%n", cart.getItemCount());
        System.out.printf("Subtotal: $%.2f%n", calculateTotalPrice());
    }

    default void checkout(String discountType, double discountValue) {
        displayCartSummary();

        if (getCart().isEmpty()) {
            System.out.println("Cannot checkout with empty cart");
            return;
        }

        double originalTotal = calculateTotalPrice();
        double finalTotal = calculateTotalWithDiscount(discountType, discountValue);
        double savings = originalTotal - finalTotal;

        System.out.printf("Discount applied (%s): -$%.2f%n", discountType, savings);
        System.out.printf("Final total: $%.2f%n", finalTotal);
        System.out.println("Thank you for your purchase!");
    }
}
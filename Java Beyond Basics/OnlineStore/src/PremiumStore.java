import java.util.List;

class PremiumStore implements OrderProcessor {
    private Cart cart;
    private boolean isLoyaltyMember;
    private String currentSeason;

    public PremiumStore(boolean isLoyaltyMember, String currentSeason) {
        this.cart = new Cart();
        this.isLoyaltyMember = isLoyaltyMember;
        this.currentSeason = currentSeason;
    }

    @Override
    public Cart getCart() {
        return cart;
    }

    // Enhanced checkout with multiple discount types
    public void premiumCheckout() {
        displayCartSummary();

        if (getCart().isEmpty()) {
            System.out.println("Cannot checkout with empty cart");
            return;
        }

        double originalTotal = calculateTotalPrice();

        // Apply multiple discounts
        double standardDiscount = calculateStandardDiscount(originalTotal, getCart().getItems());
        double loyaltyDiscount = calculateLoyaltyDiscount(originalTotal, isLoyaltyMember);
        double seasonalDiscount = calculateSeasonalDiscount(originalTotal, currentSeason);

        // Take the best discount
        double bestDiscount = Math.max(Math.max(standardDiscount, loyaltyDiscount), seasonalDiscount);
        double finalTotal = originalTotal - bestDiscount;

        System.out.println("\n=== Premium Checkout ===");
        System.out.printf("Standard discount available: $%.2f%n", standardDiscount);
        System.out.printf("Loyalty discount available: $%.2f%n", loyaltyDiscount);
        System.out.printf("Seasonal (%s) discount available: $%.2f%n", currentSeason, seasonalDiscount);
        System.out.printf("Best discount applied: $%.2f%n", bestDiscount);
        System.out.printf("Final total: $%.2f%n", finalTotal);
    }

    // Override standard discount for premium store
    @Override
    public double calculateStandardDiscount(double totalPrice, List<Item> items) {
        // Premium store offers better standard discount
        if (totalPrice >= 50) {
            return DiscountCalculator.calculatePercentageDiscount(totalPrice, 15.0);
        }
        return 0.0;
    }
}

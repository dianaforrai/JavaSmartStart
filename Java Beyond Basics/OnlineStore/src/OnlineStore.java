import java.util.List;

class OnlineStore implements OrderProcessor {
    private Cart cart;
    private String storeName;

    public OnlineStore(String storeName) {
        this.cart = new Cart();
        this.storeName = storeName;
    }

    @Override
    public Cart getCart() {
        return cart;
    }

    public String getStoreName() {
        return storeName;
    }

    @Override
    public double calculateStandardDiscount(double totalPrice, List<Item> items) {
        if (totalPrice >= 60) {
            return DiscountCalculator.calculatePercentageDiscount(totalPrice, 12.0);
        }
        return 0.0;
    }
}
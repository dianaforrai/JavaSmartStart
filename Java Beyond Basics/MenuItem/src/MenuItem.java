
class MenuItem {
    private String name;
    private double price;
    private String category;

    // Constructor
    public MenuItem(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    // Setter for price (useful for applying discounts)
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("MenuItem{name='%s', price=%.2f, category='%s'}",
                name, price, category);
    }
}

// Part 2: Functional Interface for categorizing menu items
@FunctionalInterface
interface CategorizeMenuItem {
    String categorize(MenuItem item);
}

// Part 3: Functional Interface for applying special offers
@FunctionalInterface
interface ApplySpecialOffer {
    MenuItem applyOffer(MenuItem item);
}

// Custom exception for unknown categories
class UnknownCategoryException extends RuntimeException {
    public UnknownCategoryException(String message) {
        super(message);
    }
}